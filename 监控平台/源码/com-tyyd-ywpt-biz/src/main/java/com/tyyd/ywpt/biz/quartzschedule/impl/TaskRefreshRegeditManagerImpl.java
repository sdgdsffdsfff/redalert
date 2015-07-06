/**   
* @Title: TaskRefreshRegeditManagerImpl.java 
* @Package com.tyyd.ywpt.biz.quartzschedule.impl 
* @Description:  
* @author wangyu   
* @date 2014-11-14 下午3:58:12 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.quartzschedule.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tyyd.ywpt.biz.bean.DynamicHostBeanRegisterBinding;
import com.tyyd.ywpt.biz.bean.impl.CustomerBeanListFactory;
import com.tyyd.ywpt.biz.dict.JobTypePrefixEnum;
import com.tyyd.ywpt.biz.dict.RegeditBeanTypeEnum;
import com.tyyd.ywpt.biz.dict.SysTypeEnum;
import com.tyyd.ywpt.biz.quartzschedule.ScheduleJob;
import com.tyyd.ywpt.biz.quartzschedule.TaskRefreshRegeditManager;
import com.tyyd.ywpt.biz.quartzschedule.dataobject.QuartzScheduleJobBO;
import com.tyyd.ywpt.biz.quartzschedule.dataobject.RegeditTaskSourceDomain;
import com.tyyd.ywpt.biz.quartzschedule.dataobject.TaskRefreshResponseDTO;
import com.tyyd.ywpt.biz.ssh.invoke.ServerSshImpl;
import com.tyyd.ywpt.biz.ssh.invoke.keepalived.impl.KeepAlivedSshImpl;
import com.tyyd.ywpt.biz.ssh.invoke.mysql.impl.MySQLSshImpl;
import com.tyyd.ywpt.biz.ssh.invoke.oracle.impl.OracleSshImpl;
import com.tyyd.ywpt.dao.configration.db.DbConfigDAO;
import com.tyyd.ywpt.dao.configration.db.dataobject.DbConfigDomain;
import com.tyyd.ywpt.dao.configration.host.HostConfigDAO;
import com.tyyd.ywpt.dao.configration.host.dataobject.HostConfigDomain;
import com.tyyd.ywpt.dao.configration.hostdiralert.HostDirAlertConfigDAO;
import com.tyyd.ywpt.dao.configration.hostdiralert.dataobject.HostDirAlertConfigDomain;
import com.tyyd.ywpt.dao.configration.job.JavaJobDefDAO;
import com.tyyd.ywpt.dao.configration.job.dataobject.JavaJobDefDomain;
import com.tyyd.ywpt.dao.configration.privsip.MonitorIpPrivsDAO;
import com.tyyd.ywpt.dao.configration.schedule.DeamonScheduleconfigDAO;
import com.tyyd.ywpt.dao.configration.schedule.dataobject.DeamonScheduleconfigDomain;
import com.tyyd.ywpt.tools.bean.SpringContextHolder;
import com.tyyd.ywpt.tools.ds.dataobject.DynamicDataSourceDomain;
import com.tyyd.ywpt.tools.ds.dataobject.DynamicDataSourceDomain.dbEnum;
import com.tyyd.ywpt.tools.ds.impl.SingleDataSourceBeanRegUtils;

/**
 * @author wangyu
 *
 */
public class TaskRefreshRegeditManagerImpl implements TaskRefreshRegeditManager{

	public static final Logger LOGGER = Logger.getLogger(TaskRefreshRegeditManagerImpl.class);
	
	@Resource
	private MonitorIpPrivsDAO MonitorIpPrivsDAO;
	
	@Resource
	private HostConfigDAO hostConfigDAO;
	
	@Resource
	private DbConfigDAO dbConfigDAO;
	
	@Resource
	private HostDirAlertConfigDAO hostDirAlertConfigDAO;
	
	@Resource
	private DynamicHostBeanRegisterBinding dynamicHostBeanRegisterBinding;
	
	@Resource
	private ScheduleJob scheduleJob;
	
	@Resource
	private DeamonScheduleconfigDAO deamonScheduleconfigDAO;
	
	@Resource
	private JavaJobDefDAO javaJobDefDAO;
	
	@Resource
	private SingleDataSourceBeanRegUtils singleDataSourceBeanRegUtils;
	
	@Resource
	private CustomerBeanListFactory customerBeanListFactory;
	
	private static final Integer DEFAULT_SSH_PORT = 22;
	
	@SuppressWarnings("rawtypes")
	@Override
	public TaskRefreshResponseDTO regeditTask(String newJobId,
			String newJobName, String newJobGroup, String cronExpression,String newbeanId,
			String monitorId, String configType, Integer jobType,
			String oldJobId, String oldJobName, String oldJobGroup, String mdkey) {

		//TODO 检查握手是否成功
		TaskRefreshResponseDTO dto = checkPrivsHand(mdkey);
		if(!dto.isSuccess()){
			LOGGER.error("签名失败，请检查签名");
			return dto;
		}
		
		//TODO 检查bean是否被定义
		RegeditTaskSourceDomain sourceBean = checkBeanType(monitorId,configType,jobType,newbeanId);
		
		//TODO 检查内存中的bean是否存在
		boolean isExists = checkBeanExists(newbeanId);
		
		//TODO 如果不存在，重新注册Bean
		if(!isExists){
			boolean isSuccess = regeditMonitorBean(newbeanId,sourceBean);
			if(!isSuccess){
				LOGGER.error(newbeanId+"注册失败,请检查对应的配置");
				return retResponse(false,"新增任务对应的SSH节点，注册失败，请检查主机配置信息");
			}
		}
		
		//TODO 检查上次的oldJobID是否存在
		boolean isExistsOldTask = checkJobExists(oldJobName,oldJobGroup);
		
		//TODO 如果老的job存在，并且新老JOBid不同，则是业务有问题 (这段判断逻辑暂时留着，以后可能会去掉)
		if(isExistsOldTask && !newJobName.equals(oldJobName)){
			
			dto.setRemark("请检查需注册及删除的JobName是否一致");
			return dto;
		}
		
		
		//TODO 删除上次的JOB
		if(isExistsOldTask){
			boolean removeFlag = removeTask(oldJobName,oldJobGroup);
			if(!removeFlag){
				LOGGER.error("["+oldJobName+","+oldJobGroup+"]"+"任务删除失败");
				return retResponse(false,"删除失败,请检查oldJobName["+oldJobName+"]");
			}
		}
		
		
		//TODO 检查newJobName是否已经注册
		boolean isExistsNewTask = checkJobExists(newJobName,newJobGroup);
		if(isExistsNewTask){
			return retResponse(false,"请检查newJobName,oldJobName["+oldJobName+"]已经被remove，请重新注册");
		}
		
		
		//增加新增的JOB
		QuartzScheduleJobBO newJobBO = getQuartzScheduleJobBO(newJobId,
				newJobName, newJobGroup, newbeanId, monitorId, configType,
				jobType, cronExpression);
		
		boolean regeditFlag = regeditNewTaskJob(newJobBO);
		if(!regeditFlag){
			return retResponse(false,"注册任务失败，请检查新任务的配置");
		}
		
		dto.setSuccess(true);
		return dto;
	}

	/**
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private QuartzScheduleJobBO getQuartzScheduleJobBO(String newJobId,String newJobName,
			String newJobGroup, String newbeanId, String monitorId,
			String configType, Integer jobType,String cronExpression) {
		QuartzScheduleJobBO newJob = new QuartzScheduleJobBO();
		newJob.setBeanId(newbeanId);
		newJob.setJobName(newJobName);
		newJob.setJobGroup(newJobGroup);
		newJob.setJobId(newJobId);
		newJob.setTaskType(String.valueOf(configType));
		newJob.setCronExpression(cronExpression);
		newJob.setDomainId(monitorId);
		
		
		JavaJobDefDomain javaJobDefDomain = javaJobDefDAO.getJobDefByJobId(Long.valueOf(jobType));
		if(javaJobDefDomain!=null){
			String pakageDef = javaJobDefDomain.getPackClass();
			Class cl = null;
			try {
				cl = Class.forName(pakageDef);
			} catch (ClassNotFoundException e) {
				LOGGER.error("["+pakageDef+"]class转化失败", e);
			}
			newJob.setJobClass(cl);
		}
		
		
		return newJob;
	}

	/**
	 * @param newbeanId
	 * @param sourceBean
	 */
	@SuppressWarnings("rawtypes")
	private boolean regeditMonitorBean(String newbeanId,
			RegeditTaskSourceDomain sourceBean) {
		boolean isSuccess = Boolean.FALSE;
		if(sourceBean.getBeanType().intValue() == RegeditBeanTypeEnum.datasource_oracle.getVal().intValue() 
				|| sourceBean.getBeanType().intValue() == RegeditBeanTypeEnum.datasource_mysql.getVal().intValue()){
			
			//TODO 数据源方式
			DbConfigDomain dbConfig = (DbConfigDomain)sourceBean.getBeanObj();
			
			//TODO 转化成数据源对象
			DynamicDataSourceDomain domain = getDynamicDataSourceDomain(dbConfig,sourceBean.getBeanType());
			
			//TODO 注册Bean
			singleDataSourceBeanRegUtils.regDataSource(newbeanId, domain);
			
			//TODO 注册自定义Bean
			customerBeanListFactory.beanDefinitionBuild(newbeanId,domain);
			
			//TODO 启动数据源
			singleDataSourceBeanRegUtils.startDataSource();
			
			isSuccess = Boolean.TRUE;
		}else{
		
			//配置SSH的变量，SSH方式
			Map<String,Object> propertyMap = new HashMap<String,Object>();
			if(sourceBean.getConfigType().intValue() == SysTypeEnum.server.getVal().intValue()){
				HostConfigDomain domain = (HostConfigDomain)sourceBean.getMonitorObj();
				propertyMap.put("connectIpAddr", domain.getIpAddr());
				propertyMap.put("connectUserName", domain.getUserAccount());
				propertyMap.put("connectPassword", domain.getUserPasswd());
				propertyMap.put("connectPort", DEFAULT_SSH_PORT);
			}else{
				DbConfigDomain domain = (DbConfigDomain)sourceBean.getMonitorObj();
				propertyMap.put("connectIpAddr", domain.getIpAddr());
				propertyMap.put("connectUserName", domain.getHostUserName());
				propertyMap.put("connectPassword", domain.getHostUserPasswd());
				propertyMap.put("connectPort", DEFAULT_SSH_PORT);
			}
			
			
			//TODO 配置bean对应的操作类
			Class registerBeanClass = null;
			if(sourceBean.getConfigType().intValue() == SysTypeEnum.server.getVal().intValue()){
				registerBeanClass = ServerSshImpl.class;
				
			}else if(sourceBean.getConfigType().intValue() == SysTypeEnum.Oracle.getVal().intValue()){
				registerBeanClass = OracleSshImpl.class;
				
			}else if(sourceBean.getConfigType().intValue() == SysTypeEnum.MySQL.getVal().intValue()){
				registerBeanClass = MySQLSshImpl.class;
				
			}else if(sourceBean.getConfigType().intValue() == SysTypeEnum.KEEPALIVED.getVal().intValue()){
				registerBeanClass = KeepAlivedSshImpl.class;
				
			}
			
			isSuccess = dynamicHostBeanRegisterBinding.regSimpleBean(newbeanId, registerBeanClass, sourceBean.getBeanObj(), propertyMap);
			
		}
		return isSuccess;
	}

	/**
	 * @param dbConfig
	 * @return
	 */
	private DynamicDataSourceDomain getDynamicDataSourceDomain(
			DbConfigDomain dbConfig,Integer beanType) {

		DynamicDataSourceDomain dynamicDataSourceDomain = new DynamicDataSourceDomain();
		dynamicDataSourceDomain.setPort(dbConfig.getPort());
		dynamicDataSourceDomain.setIpAddr(dbConfig.getIpAddr());
		dynamicDataSourceDomain.setUserName(dbConfig.getDbUserName());
		dynamicDataSourceDomain.setPasswd(dbConfig.getDbPasswd());
		dynamicDataSourceDomain.setInstanceId(dbConfig.getDbName());
		
		if(beanType.intValue() == RegeditBeanTypeEnum.datasource_oracle.getVal().intValue()){
			dynamicDataSourceDomain.setDbType(dbEnum.Oracle.getDatabase());
			
		}else if(beanType.intValue() == RegeditBeanTypeEnum.datasource_mysql.getVal().intValue()){
			dynamicDataSourceDomain.setDbType(dbEnum.MySQL.getDatabase());
			
		}
		
		dynamicDataSourceDomain.setHostId(dbConfig.getHostId());
		dynamicDataSourceDomain.setDbId(dbConfig.getDbId());
		
		return dynamicDataSourceDomain;
	}

	/**
	 * @param monitorId
	 * @param configType
	 * @param jobType
	 */
	private RegeditTaskSourceDomain checkBeanType(String monitorId, String configType,
			Integer jobType,String newbeanId) {
		
		return convertRegeditTaskSourceDomain(monitorId,Integer.valueOf(configType),jobType,newbeanId);
	}
	
	
	private RegeditTaskSourceDomain convertRegeditTaskSourceDomain(String monitorId,Integer configType,Integer jobType,String newbeanId){
		RegeditTaskSourceDomain domain = new RegeditTaskSourceDomain();
		Integer beanType = JobTypePrefixEnum.getEnum(jobType);
		String beanPrefix = RegeditBeanTypeEnum.getEnum(beanType);
		
		if(beanType == null || beanType.intValue() == 0){
			return null;
		}
		
		if(beanType.intValue() == RegeditBeanTypeEnum.server.getVal().intValue()){
			HostConfigDomain monitorObj = getServer(monitorId);
			domain.setConfigType(SysTypeEnum.server.getVal());
			domain.setMonitorObj(monitorObj);
			domain.setBeanObj(monitorObj);
			
		}else if(beanType.intValue() == RegeditBeanTypeEnum.Oracle.getVal().intValue()){
			DbConfigDomain monitorObj = getDb(monitorId);
			domain.setConfigType(SysTypeEnum.Oracle.getVal());
			domain.setMonitorObj(monitorObj);
			domain.setBeanObj(monitorObj);
			
		}else if(beanType.intValue() == RegeditBeanTypeEnum.MySQL.getVal().intValue()){
			DbConfigDomain monitorObj = getDb(monitorId);
			domain.setConfigType(SysTypeEnum.MySQL.getVal());
			domain.setMonitorObj(monitorObj);
			domain.setBeanObj(monitorObj);
			
		}else if(beanType.intValue() == RegeditBeanTypeEnum.keepalived.getVal().intValue()){
			DbConfigDomain monitorObj = getDb(monitorId);
			domain.setConfigType(SysTypeEnum.KEEPALIVED.getVal());
			domain.setMonitorObj(monitorObj);
			domain.setBeanObj(monitorObj);
			
		}else if(beanType.intValue() == RegeditBeanTypeEnum.alert_oracle.getVal().intValue()){
			
			String alertId = newbeanId.replace(beanPrefix, StringUtils.EMPTY);
			HostDirAlertConfigDomain dirDomain = getHostDirAlert(alertId);
			DbConfigDomain monitorObj = getDb(monitorId);
			
			domain.setConfigType(SysTypeEnum.Oracle.getVal());
			domain.setMonitorObj(monitorObj);
			domain.setBeanObj(dirDomain);
			
		}else if(beanType.intValue() == RegeditBeanTypeEnum.alert_mysql.getVal().intValue()){
			
			String alertId = newbeanId.replace(beanPrefix, StringUtils.EMPTY);
			HostDirAlertConfigDomain dirDomain = getHostDirAlert(alertId);
			DbConfigDomain monitorObj = getDb(monitorId);
			
			domain.setConfigType(SysTypeEnum.MySQL.getVal());
			domain.setMonitorObj(monitorObj);
			domain.setBeanObj(dirDomain);
			
		}else if(beanType.intValue() == RegeditBeanTypeEnum.alert_keepalived.getVal().intValue()){
			
			String alertId = newbeanId.replace(beanPrefix, StringUtils.EMPTY);
			HostDirAlertConfigDomain dirDomain = getHostDirAlert(alertId);
			DbConfigDomain monitorObj = getDb(monitorId);
			
			domain.setConfigType(SysTypeEnum.KEEPALIVED.getVal());
			domain.setMonitorObj(monitorObj);
			domain.setBeanObj(dirDomain);
			
		}else if(beanType.intValue() == RegeditBeanTypeEnum.datasource_oracle.getVal().intValue()){
			DbConfigDomain monitorObj = getDb(monitorId);
			domain.setConfigType(SysTypeEnum.Oracle.getVal());
			domain.setMonitorObj(monitorObj);
			domain.setBeanObj(monitorObj);
			
		}else if(beanType.intValue() == RegeditBeanTypeEnum.datasource_mysql.getVal().intValue()){
			DbConfigDomain monitorObj = getDb(monitorId);
			domain.setConfigType(SysTypeEnum.MySQL.getVal());
			domain.setMonitorObj(monitorObj);
			domain.setBeanObj(monitorObj);
			
		}
		
		domain.setJobType(jobType);
		domain.setMonitorId(monitorId);
		domain.setBeanType(beanType);
		
		return domain;
	}
	
	private HostConfigDomain getServer(String hostId){
		return hostConfigDAO.getHostById(hostId);
	}
	
	
	private DbConfigDomain getDb(String dbId){
		return dbConfigDAO.getDataBaseConfig(dbId);
	}
	
	
	private HostDirAlertConfigDomain getHostDirAlert(String id){
		return hostDirAlertConfigDAO.getHostDirAlertConfig(id);
	}
	
	
	
	/**
	 * @param newJobId
	 */
	private boolean regeditNewTaskJob(QuartzScheduleJobBO job) {
		return scheduleJob.addTask(job);
	}

	/**
	 * @param oldJobId
	 */
	private boolean removeTask(String jobName,String jobGroup) {
		return scheduleJob.delTask(jobName, jobGroup);
	}

	/**
	 * @param oldJobId
	 * @return
	 */
	private boolean checkJobExists(String jobName,String jobGroup) {
		return scheduleJob.isExistsTask(jobName, jobGroup);
	}



	/**
	 * @param beanId
	 * @param beanType
	 * @return
	 */
	private boolean checkBeanExists(String newbeanId) {
		Object obj = SpringContextHolder.getBean(newbeanId);
		if(obj != null){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/**
	 * @param mdkey
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private TaskRefreshResponseDTO checkPrivsHand(String mdkey) {
		//在授权表中找到对应的IP做MD5算法
		TaskRefreshResponseDTO dto = new TaskRefreshResponseDTO();
		boolean isExists = MonitorIpPrivsDAO.isExistsIp(mdkey);
		if(isExists){
			dto.setSuccess(true);
		}else{
			dto.setSuccess(false);
			dto.setRemark("未授权应用，不能操作");
		}
		
		return dto;
	}

	
	@SuppressWarnings("rawtypes")
	private TaskRefreshResponseDTO retResponse(boolean isSuccess,String remark){
		TaskRefreshResponseDTO dto = new TaskRefreshResponseDTO();
		dto.setSuccess(isSuccess);
		dto.setRemark(remark);
		return dto;
	}

	
	@SuppressWarnings("rawtypes")
	@Override
	public TaskRefreshResponseDTO delTask(String jobId, String mdkey) {
		
		//TODO 检查握手是否成功
		TaskRefreshResponseDTO dto = checkPrivsHand(mdkey);
		if(!dto.isSuccess()){
			return dto;
		}
		
		DeamonScheduleconfigDomain domain = deamonScheduleconfigDAO.getScheduleconfigById(jobId);
		boolean flag = removeTask(domain.getJobName(), domain.getJobGroup());
		if(!flag){
			return retResponse(false,"删除失败,请检查jobId["+jobId+"]");
		}
		
		dto.setSuccess(true);
		return dto;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public TaskRefreshResponseDTO addBean(String monitorId, Integer configType,Integer jobType,
		 String newbeanId, String mdkey) {
		
		//TODO 检查握手是否成功
		TaskRefreshResponseDTO dto = checkPrivsHand(mdkey);
		if(!dto.isSuccess()){
			return dto;
		}
		
		RegeditTaskSourceDomain domain = convertRegeditTaskSourceDomain(monitorId, configType,jobType, newbeanId);
		
		boolean isExists = checkBeanExists(newbeanId);
		if(isExists){
			//先删除bean
			boolean removeFlag = dynamicHostBeanRegisterBinding.removeBean(newbeanId);
			if(!removeFlag){
				return retResponse(false,"删除旧的SSH注册信息失败，请检查主机配置信息");
			}
		}
		
		//注册Bean
		boolean isSuccess = regeditMonitorBean(newbeanId,domain);
		if(!isSuccess){
			return retResponse(false,"新增任务对应的SSH节点，注册失败，请检查主机配置信息");
		}
		
		dto.setSuccess(isSuccess);
		return dto;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public TaskRefreshResponseDTO delBean(String newbeanId,
			String mdkey) {
		
		//TODO 检查握手是否成功
		TaskRefreshResponseDTO dto = checkPrivsHand(mdkey);
		if(!dto.isSuccess()){
			return dto;
		}
		
		boolean isExists = checkBeanExists(newbeanId);
		if(isExists){
			//先删除bean
			boolean removeFlag = dynamicHostBeanRegisterBinding.removeBean(newbeanId);
			if(!removeFlag){
				return retResponse(false,"删除旧的SSH注册信息失败，请检查主机配置信息");
			}
		}
		
		//如果是数据源类型,则刷新dataSource
		if(newbeanId.startsWith("datasource_oracle_") 
				|| newbeanId.startsWith("datasource_mysql_")){
			singleDataSourceBeanRegUtils.startDataSource();
		}
		
		dto.setSuccess(true);
		return dto;
	}

	@Override
	public String sayHello(String userName) {
		return "this is regedit Sever,welcome :" + userName;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public TaskRefreshResponseDTO existsBean(String newbeanId, String mdkey) {
		//TODO 检查握手是否成功
		TaskRefreshResponseDTO dto = checkPrivsHand(mdkey);
		if(!dto.isSuccess()){
			return dto;
		}
		
		boolean isExists = checkBeanExists(newbeanId);
		dto.setRemark("查询成功，Bean的状态是:"+isExists);
		dto.setSuccess(true);
		
		return dto;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean isPrivsRole(String mdkey) {
		TaskRefreshResponseDTO dto = checkPrivsHand(mdkey);
		if(!dto.isSuccess()){
			LOGGER.error("签名失败，请检查签名");
			return false;
		}
		
		return true;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public TaskRefreshResponseDTO delHangStatusTask(String jobId, String mdkey) {
		//TODO 检查握手是否成功
		TaskRefreshResponseDTO dto = checkPrivsHand(mdkey);
		if(!dto.isSuccess()){
			return dto;
		}
		
		DeamonScheduleconfigDomain domain = deamonScheduleconfigDAO.getHangScheduleconfigById(jobId);
		if(domain != null){
			boolean flag = removeTask(domain.getJobName(), domain.getJobGroup());
			if(!flag){
				return retResponse(false,"删除失败,请检查jobId["+jobId+"]");
			}
		}
		dto.setSuccess(true);
		return dto;
	}
	
}
