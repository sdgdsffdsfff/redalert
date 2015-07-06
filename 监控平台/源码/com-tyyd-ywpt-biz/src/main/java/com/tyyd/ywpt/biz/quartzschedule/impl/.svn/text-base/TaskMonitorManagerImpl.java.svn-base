/**   
* @Title: TaskMonitorManagerImpl.java 
* @Package com.tyyd.ywpt.biz.quartzschedule.impl 
* @Description:  
* @author wangyu   
* @date 2014-11-6 下午4:48:47 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.quartzschedule.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import com.tyyd.ywpt.biz.dict.SysTypeEnum;
import com.tyyd.ywpt.biz.quartzschedule.QuotaScheduleBizConfigManager;
import com.tyyd.ywpt.biz.quartzschedule.ScheduleJob;
import com.tyyd.ywpt.biz.quartzschedule.TaskMonitorManager;
import com.tyyd.ywpt.biz.quartzschedule.dataobject.QuartzScheduleJobBO;
import com.tyyd.ywpt.biz.util.DateUtils;
import com.tyyd.ywpt.dao.configration.schedule.DeamonScheduleconfigDAO;
import com.tyyd.ywpt.dao.core.schedule.tasklog.TaskJobLogDAO;
import com.tyyd.ywpt.dao.core.schedule.tasklog.dataobject.TaskJobLogDomain;

/**
 * @author wangyu
 *
 */
public class TaskMonitorManagerImpl implements TaskMonitorManager {

	
	private final static String HESSIAN_PATTERN = "http://%s:%s/remote/regeditServer";
	
	private ScheduleJob scheduleJob;
	
	private String serverId;
	
	private QuotaScheduleBizConfigManager quotaScheduleBizConfigManager;
	
	private TaskJobLogDAO taskJobLogDAO;

	
	private DeamonScheduleconfigDAO deamonScheduleconfigDAO;
	
	
	
	@Override
	public List<QuartzScheduleJobBO> regTasks() {
		return scheduleJob.scheduleTask();
	}

	@Override
	public List<QuartzScheduleJobBO> runningTasks() {
		return scheduleJob.runningTask();
	}

	@Override
	public List<QuartzScheduleJobBO> runTaskInfo(String srvId) {
		
		if(StringUtils.isBlank(srvId)){
			srvId = serverId;
		}
		
		//当前服务的定义
		List<QuartzScheduleJobBO> serverJobs = quotaScheduleBizConfigManager.listQuartzConfig(srvId, SysTypeEnum.server.getVal());
		List<QuartzScheduleJobBO> mysqlJobs = quotaScheduleBizConfigManager.listQuartzConfig(srvId, SysTypeEnum.MySQL.getVal());
		List<QuartzScheduleJobBO> oracleJobs = quotaScheduleBizConfigManager.listQuartzConfig(srvId, SysTypeEnum.Oracle.getVal());
		List<QuartzScheduleJobBO> keepAlivedJobs = quotaScheduleBizConfigManager.listQuartzConfig(srvId, SysTypeEnum.KEEPALIVED.getVal());
		List<TaskJobLogDomain> list = taskJobLogDAO.listTaskJobByDaemon(srvId);
		
		Map<String,TaskJobLogDomain> taskMap = convertToMap(list);
		
		
		List<QuartzScheduleJobBO> noRegTask = new ArrayList<QuartzScheduleJobBO>();
		
		noRegTask.addAll(setRunTime(serverJobs,taskMap));
		noRegTask.addAll(setRunTime(mysqlJobs,taskMap));
		noRegTask.addAll(setRunTime(oracleJobs,taskMap));
		noRegTask.addAll(setRunTime(keepAlivedJobs,taskMap));
		
		return noRegTask;
	}

	/**
	 * @param list
	 */
	private Map<String,TaskJobLogDomain> convertToMap(List<TaskJobLogDomain> list) {
		Map<String,TaskJobLogDomain> taskMap = new HashMap<String,TaskJobLogDomain>();
		if(CollectionUtils.isNotEmpty(list)){
			for(TaskJobLogDomain job : list){
				String key = job.getJobId();
				taskMap.put(key, job);
			}
		}
		return taskMap;
	}



	/**
	 * @param serverJobs
	 * @param list
	 * @return
	 */
	private List<QuartzScheduleJobBO> setRunTime(
			List<QuartzScheduleJobBO> serverJobs, Map<String,TaskJobLogDomain> map) {
		List<QuartzScheduleJobBO> result = new ArrayList<QuartzScheduleJobBO>();
		
		if(CollectionUtils.isNotEmpty(serverJobs)){
			for(QuartzScheduleJobBO job : serverJobs){
				if(MapUtils.isNotEmpty(map)){
					String jobId = job.getJobId();
					//如果不存在，设置为空
					if(map.containsKey(jobId)){
						TaskJobLogDomain jobDomain = map.get(jobId);
						String date = DateUtils.showTime(jobDomain.getGmtCreated(), "yyyy-MM-dd HH:mm:ss");
						job.setLastRunDate(date);
					}else{
						job.setLastRunDate("No Run");
					}
					result.add(job);
				}
			}
		}
		return result;
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
	 * @return the taskJobLogDAO
	 */
	public TaskJobLogDAO getTaskJobLogDAO() {
		return taskJobLogDAO;
	}

	/**
	 * @param taskJobLogDAO the taskJobLogDAO to set
	 */
	public void setTaskJobLogDAO(TaskJobLogDAO taskJobLogDAO) {
		this.taskJobLogDAO = taskJobLogDAO;
	}
	
	

	@Override
	public List<QuartzScheduleJobBO> allJobs() {
		
		//当前服务的定义
		List<QuartzScheduleJobBO> serverJobs = quotaScheduleBizConfigManager.listQuartzConfig(SysTypeEnum.server.getVal());
		List<QuartzScheduleJobBO> mysqlJobs = quotaScheduleBizConfigManager.listQuartzConfig(SysTypeEnum.MySQL.getVal());
		List<QuartzScheduleJobBO> oracleJobs = quotaScheduleBizConfigManager.listQuartzConfig(SysTypeEnum.Oracle.getVal());
		List<QuartzScheduleJobBO> keepAlivedJobs = quotaScheduleBizConfigManager.listQuartzConfig(SysTypeEnum.KEEPALIVED.getVal());
		List<TaskJobLogDomain> list = taskJobLogDAO.listTaskJobByDaemon();
		
		Map<String,TaskJobLogDomain> taskMap = convertToMap(list);
		
		List<QuartzScheduleJobBO> noRegTask = new ArrayList<QuartzScheduleJobBO>();
		
		noRegTask.addAll(setRunTime(serverJobs,taskMap));
		noRegTask.addAll(setRunTime(mysqlJobs,taskMap));
		noRegTask.addAll(setRunTime(oracleJobs,taskMap));
		noRegTask.addAll(setRunTime(keepAlivedJobs,taskMap));
		
		return noRegTask;
		
	}

	
	
	

}
