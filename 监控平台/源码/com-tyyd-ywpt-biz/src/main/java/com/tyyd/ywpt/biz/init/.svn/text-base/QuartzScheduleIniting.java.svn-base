/**   
* @Title: QuartzScheduleIniting.java 
* @Package com.tyyd.ywpt.biz.init 
* @Description:  
* @author wangyu   
* @date 2014-7-9 下午5:47:38 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.init;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import com.tyyd.ywpt.biz.dict.SysTypeEnum;
import com.tyyd.ywpt.biz.quartzschedule.QuotaScheduleBizConfigManager;
import com.tyyd.ywpt.biz.quartzschedule.ScheduleJob;
import com.tyyd.ywpt.biz.quartzschedule.dataobject.QuartzScheduleJobBO;
import com.tyyd.ywpt.biz.quartzschedule.impl.invoke.server.SshClearDeadJobInvokeScheduleJob;

/**
 * @author wangyu
 * 定时任务统一注册中心
 */
public class QuartzScheduleIniting implements InitializingBean {

	public static final Logger LOGGER = Logger.getLogger(QuartzScheduleIniting.class);  
	
	private QuotaScheduleBizConfigManager quotaScheduleBizConfigManager;
	
	private String serverId;
	
	private ScheduleJob scheduleJob;
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
		//主机类指标
		List<QuartzScheduleJobBO> serverJobs = quotaScheduleBizConfigManager.listQuartzConfig(serverId, SysTypeEnum.server.getVal());
		addScheduleTasks(serverJobs);
		LOGGER.info("注册server schedule job size : "+serverJobs.size());
		//System.out.println("注册server schedule job size : "+serverJobs.size());
		
		//MySQL指标
		List<QuartzScheduleJobBO> mysqlJobs = quotaScheduleBizConfigManager.listQuartzConfig(serverId, SysTypeEnum.MySQL.getVal());
		addScheduleTasks(mysqlJobs);
		LOGGER.info("注册mysql schedule job size : "+mysqlJobs.size());
		//System.out.println("注册mysql schedule job size : "+mysqlJobs.size());
		
		//Oracle类指标
		List<QuartzScheduleJobBO> oracleJobs = quotaScheduleBizConfigManager.listQuartzConfig(serverId, SysTypeEnum.Oracle.getVal());
		addScheduleTasks(oracleJobs);
		
		LOGGER.info("注册oracle schedule job size : "+oracleJobs.size());
		//System.out.println("注册oracle schedule job size : "+oracleJobs.size());
		
		//KeepAlived类指标
		List<QuartzScheduleJobBO> keepAlivedJobs = quotaScheduleBizConfigManager.listQuartzConfig(serverId, SysTypeEnum.KEEPALIVED.getVal());
		addScheduleTasks(keepAlivedJobs);
		LOGGER.info("注册keepAlived schedule job size : "+keepAlivedJobs.size());
		
		
		//添加一个自定义清理僵死JOB的任务
		QuartzScheduleJobBO clearJob = new QuartzScheduleJobBO();
		clearJob.setBeanId("clear_dead_job_local_bean");
		clearJob.setJobName("Clear_dead_Job");
		clearJob.setJobGroup("Clear_dead_Job_Group");
		clearJob.setCronExpression("10 0/15 * * * ?");
		clearJob.setJobClass(SshClearDeadJobInvokeScheduleJob.class);
		scheduleJob.addTask(clearJob);
		
		List<QuartzScheduleJobBO> planTaskList = scheduleJob.scheduleTask();
		for(QuartzScheduleJobBO task : planTaskList){
			LOGGER.info("计划中的要执行的任务 ："+task.toString());
		}
	}

	
	private void addScheduleTasks(List<QuartzScheduleJobBO> list){
		if(list == null){
			return ;
		}
		scheduleJob.loadSchedule(list);
	}
	
	
	
	/**
	 * @return the quotaScheduleBizConfigManager
	 */
	public QuotaScheduleBizConfigManager getQuotaScheduleBizConfigManager() {
		return quotaScheduleBizConfigManager;
	}

	/**
	 * @param quotaScheduleBizConfigManager the quotaScheduleBizConfigManager to set
	 */
	public void setQuotaScheduleBizConfigManager(
			QuotaScheduleBizConfigManager quotaScheduleBizConfigManager) {
		this.quotaScheduleBizConfigManager = quotaScheduleBizConfigManager;
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
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}


	/**
	 * @return the scheduleJob
	 */
	public ScheduleJob getScheduleJob() {
		return scheduleJob;
	}


	/**
	 * @param scheduleJob the scheduleJob to set
	 */
	public void setScheduleJob(ScheduleJob scheduleJob) {
		this.scheduleJob = scheduleJob;
	}
	
	
	

}
