/**   
* @Title: AbstractQuotaScheduleQuartzTask.java 
* @Package com.tyyd.ywpt.biz.quartzschedule.impl 
* @Description:  
* @author wangyu   
* @date 2014-7-1 下午3:31:42 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.quartzschedule.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.tyyd.ywpt.biz.bean.dataobject.DynamicBeanBO;
import com.tyyd.ywpt.biz.bean.impl.CustomerBeanListFactory;
import com.tyyd.ywpt.biz.quartzschedule.dataobject.QuartzScheduleJobBO;
import com.tyyd.ywpt.biz.ssh.invoke.ServerSsh;
import com.tyyd.ywpt.biz.ssh.invoke.keepalived.KeepAlivedSsh;
import com.tyyd.ywpt.biz.ssh.invoke.mysql.MySQLSsh;
import com.tyyd.ywpt.biz.ssh.invoke.oracle.OracleSsh;
import com.tyyd.ywpt.biz.util.CustomerPropertiesUtils;
import com.tyyd.ywpt.dao.configration.db.DbConfigDAO;
import com.tyyd.ywpt.dao.configration.host.HostConfigDAO;
import com.tyyd.ywpt.dao.core.collect.heartbeat.HeartbeatMonitorDAO;
import com.tyyd.ywpt.dao.core.schedule.tasklog.TaskJobLogDAO;
import com.tyyd.ywpt.dao.core.schedule.tasklog.dataobject.TaskJobLogDomain;
import com.tyyd.ywpt.tools.bean.SpringContextHolder;
import com.tyyd.ywpt.tools.ds.impl.DynamicDataSourceContextHolder;

/**
 * @author wangyu
 *
 */
public abstract class AbstractQuotaScheduleQuartzTask implements Job{

	public static final Logger LOGGER = Logger.getLogger(AbstractQuotaScheduleQuartzTask.class);  
	
	private String serverId;
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		// TODO 以后改成Log4j输出
		LOGGER.info("任务开始运行...");
		QuartzScheduleJobBO scheduleJob = (QuartzScheduleJobBO)context.getMergedJobDataMap().get("taskData");
		String logout = "任务名称 = [" + scheduleJob.getJobName() + ", beanId= "
				+ scheduleJob.getBeanId() + ",Class="
				+ scheduleJob.getJobClass() + ",状态="
				+ scheduleJob.getJobStatus() + ",DESC="+scheduleJob.getDesc()+"]";
		
		LOGGER.info(logout);
		
		
		if(!isHealthDomain(scheduleJob)){
			LOGGER.error("beanId="+scheduleJob.getBeanId()+"的domain是空,请检查自定义管理Bean中心,本次任务跳过");
			return;
		}
		
		//运行任务
		doTask(scheduleJob);
		
		//重置数据源
		afterResetDS();
		
		//获取serverId
		loadServerId();
		
		//记录日志
		if(StringUtils.isNotBlank(getServerId())){
			takeLog(scheduleJob);
		}
		
	}
	
	/**
	 * @param scheduleJob
	 */
	private void takeLog(QuartzScheduleJobBO scheduleJob) {
		String beanId = scheduleJob.getBeanId();
		String jobId = scheduleJob.getJobId();
		String monitorId = scheduleJob.getDomainId();
		String taskType = scheduleJob.getTaskType();
		
		TaskJobLogDomain domain = new TaskJobLogDomain();
		domain.setBeanId(beanId);
		domain.setJobId(jobId);
		domain.setMonitorId(monitorId);
		domain.setServerId(serverId);
		domain.setSysType(Integer.valueOf(taskType));
		getTaskJobLogDAO().addTaskJobLog(domain);
		
		LOGGER.info("添加调度任务记录,beanName=" + scheduleJob.getJobName() + "beanId="
				+ beanId + ",taskType=" + taskType + ",monitorId=" + monitorId
				+ ",jobId=" + jobId);
	}

	public abstract void doTask(QuartzScheduleJobBO scheduleJob);
	
	@SuppressWarnings("rawtypes")
	private boolean isHealthDomain(QuartzScheduleJobBO scheduleJob){
		DynamicBeanBO beanDomain = getCustomerBeanListFactory().getBeanProperty(scheduleJob.getBeanId());
		if(beanDomain == null){
			return false;
		}
		return true;
	}
	
	/**
	 * 获取自定义的Bean
	 * @return
	 */
	public CustomerBeanListFactory getCustomerBeanListFactory(){
		return SpringContextHolder.getBean("customerBeanListFactory");
	} 
	
	/**
	 * 采集服务器的SSH server配置
	 * @return
	 */
	public ServerSsh getServerSsh(){
		return SpringContextHolder.getBean("serverSsh");
	}
	
	/**
	 * 采集服务器的SSH Oracle配置
	 * @return
	 */
	public OracleSsh getOracleSsh(){
		return SpringContextHolder.getBean("oracleSsh");
	}
	
	/**
	 * 采集服务器的SSH MySQL配置
	 * @return
	 */
	public MySQLSsh getMySQLSsh(){
		return SpringContextHolder.getBean("mySQLSsh");
	}
	
	
	/**
	 * KeepAlived配置
	 * @return
	 */
	public KeepAlivedSsh getKeepAlivedSsh(){
		return SpringContextHolder.getBean("keepAlivedSsh");
	}
	
	/**
	 * 获取DB的配置DAO
	 * @return
	 */
	protected DbConfigDAO getDbConfigDAO(){
		return SpringContextHolder.getBean("dbConfigDAO");
	}
	
	
	/**
	 * 获取主机配置的DAO
	 * @return
	 */
	protected HostConfigDAO getHostConfigDAO(){
		return SpringContextHolder.getBean("hostConfigDAO");
	}
	
	
	/**
	 * 心跳监测的日志记录DAO
	 * @return
	 */
	protected HeartbeatMonitorDAO getHeartbeatMonitorDAO(){
		return SpringContextHolder.getBean("heartbeatMonitorDAO");
	}

	/**
	 * @return the serverId
	 */
	public String getServerId() {
		return serverId;
	}

	/**
	 * @param serverId the serverId to set
	 */
	public void loadServerId() {
		CustomerPropertiesUtils utils = SpringContextHolder.getBean("customerPropertiesUtils");
		this.serverId = utils.getProperty("com.tyyd.ywpt.server.id");
	}
	
	/**
	 * 记录任务历史
	 * @return
	 */
	private TaskJobLogDAO getTaskJobLogDAO(){
		return SpringContextHolder.getBean("taskJobLogDAO");
	}
	
	
	protected void afterResetDS(){
		DynamicDataSourceContextHolder.setDataSource("dataSource");
		LOGGER.info("当前数据源是:default数据源");
	}
}
