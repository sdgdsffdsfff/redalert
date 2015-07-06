/**   
* @Title: ScheduleJobImpl.java 
* @Package com.tyyd.ywpt.biz.quartzschedule.impl 
* @Description:  
* @author wangyu   
* @date 2014-6-30 上午11:04:46 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.quartzschedule.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;

import com.tyyd.ywpt.biz.quartzschedule.ScheduleJob;
import com.tyyd.ywpt.biz.quartzschedule.dataobject.QuartzScheduleJobBO;

/**
 * @author wangyu
 *
 */
public class ScheduleJobImpl implements ScheduleJob{

	
	public static final Logger LOGGER = Logger.getLogger(ScheduleJobImpl.class);  
	@Resource
	private Scheduler scheduler;
	
	/**
	 * 一次性加载所有任务
	 */
	public void loadSchedule(List<QuartzScheduleJobBO> jobList){
		for (QuartzScheduleJobBO job : jobList) {
			boolean flag = addTask(job);
			if(!flag){
				LOGGER.error("启用任务失败:"+job.toString());
				//发送mail,今后做,先预留
			}
		}
	}
	
	
	
	/**
	 * 添加一个任务
	 * @param job
	 */
	public boolean addTask(QuartzScheduleJobBO job) {
		
		if(StringUtils.isBlank(job.getBeanId()) 
				|| StringUtils.isBlank(job.getJobName()) 
				|| StringUtils.isBlank(job.getJobGroup())){
			LOGGER.error("未注册成功"+job.toString());
			return false;
		}
		
		
		
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(),job.getJobGroup());

		// 获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
		CronTrigger trigger = null;
		try {
			trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			
		} catch (SchedulerException e) {
			LOGGER.error("未注册成功"+job.toString(),e);
			return false;
		}

		if (null == trigger) {
			JobDetail jobDetail = JobBuilder
					.newJob(job.getJobClass())
					.withIdentity(job.getJobName(), job.getJobGroup()).build();

			jobDetail.getJobDataMap().put("taskData", job);
			

			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
					.cronSchedule(job.getCronExpression());

			// 按新的cronExpression表达式构建一个新的trigger
			trigger = TriggerBuilder.newTrigger()
					.withIdentity(job.getJobName(), job.getJobGroup())
					.withSchedule(scheduleBuilder).build();

			try {
				scheduler.scheduleJob(jobDetail, trigger);
			} catch (SchedulerException e) {
				LOGGER.error("调度异常", e);
				return false;
			}

		} else {
			// Trigger已存在，那么更新相应的定时设置
			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
					.cronSchedule(job.getCronExpression());

			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
					.withSchedule(scheduleBuilder).build();

			// 按新的trigger重新设置job执行
			try {
				scheduler.rescheduleJob(triggerKey, trigger);
			} catch (SchedulerException e) {
				LOGGER.error("调度异常", e);
				return false;
			}
		}
		
		
		return true;
	}
	
	
	
	/**
	 * 计划中的任务
	 */
	public List<QuartzScheduleJobBO> scheduleTask() {
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		List<QuartzScheduleJobBO> jobList = new ArrayList<QuartzScheduleJobBO>();
		try {
			Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);

			for (JobKey jobKey : jobKeys) {
				List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
				for (Trigger trigger : triggers) {
					JobDetail jobDetail = scheduler.getJobDetail(jobKey);
					QuartzScheduleJobBO dataMap = (QuartzScheduleJobBO)jobDetail.getJobDataMap().get("taskData");
					QuartzScheduleJobBO job = new QuartzScheduleJobBO();
					job.setJobName(jobKey.getName());
			        job.setJobGroup(jobKey.getGroup());
			        job.setDesc("触发器:" + trigger.getKey());
			        job.setBeanId(dataMap.getBeanId());
			        job.setDomainId(dataMap.getDomainId());
			        job.setTaskType(dataMap.getTaskType());
			        job.setJobId(dataMap.getJobId());
			        
			        TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
			        job.setJobStatus(triggerState.name());
			        
			        if (trigger instanceof CronTrigger) {
			        	CronTrigger cronTrigger = (CronTrigger) trigger;
			            String cronExpression = cronTrigger.getCronExpression();
			            job.setCronExpression(cronExpression);
			        }
					
					job.setJobStatus("Normal");
			        jobList.add(job);
				}
			}

		} catch (SchedulerException e) {
			LOGGER.error("调度异常", e);
		}

		return jobList;
	}
	
	
	/**
	 * 正在运行的任务
	 */
	public List<QuartzScheduleJobBO> runningTask() {
		List<QuartzScheduleJobBO> jobList = null;
		try {
			List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
			jobList = new ArrayList<QuartzScheduleJobBO>(executingJobs.size());
			for (JobExecutionContext executingJob : executingJobs) {
				
				JobDetail jobDetail = executingJob.getJobDetail();
				QuartzScheduleJobBO job = (QuartzScheduleJobBO)jobDetail.getJobDataMap().get("taskData");
				Trigger trigger = executingJob.getTrigger();
			    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
			    job.setJobStatus(triggerState.name());
			    
			    if (trigger instanceof CronTrigger) {
			        CronTrigger cronTrigger = (CronTrigger) trigger;
			        String cronExpression = cronTrigger.getCronExpression();
			        job.setCronExpression(cronExpression);
			    }
			    jobList.add(job);
				
				
			}
		} catch (SchedulerException e) {
			LOGGER.error("调度异常", e);
		}
		
		return jobList;
	}
	
	
	
	/**
	 * 暂停任务
	 * @param scheduleJob
	 */
	public void pauseTask(QuartzScheduleJobBO scheduleJob){
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		try {
			scheduler.pauseJob(jobKey);
		} catch (SchedulerException e) {
			LOGGER.error("调度异常", e);
		}
	}
	
	
	/**
	 * 恢复任务
	 * @param scheduleJob
	 */
	public void resumeTask(QuartzScheduleJobBO scheduleJob){
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		try {
			scheduler.resumeJob(jobKey);
		} catch (SchedulerException e) {
			LOGGER.error("调度异常", e);
		}
	}
	
	/**
	 * 删除任务
	 * @param scheduleJob
	 */
	public boolean delTask(String jobName,String jobGroup){
		boolean flag = Boolean.FALSE;
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		try {
			scheduler.deleteJob(jobKey);
			flag = Boolean.TRUE;
		} catch (SchedulerException e) {
			LOGGER.error("调度异常", e);
		}
		return flag;
	}
	
	
	/**
	 * 立即运行，但必须先注册了
	 * @param scheduleJob
	 */
	public void immediatelyRunTask(QuartzScheduleJobBO scheduleJob){
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		try {
			scheduler.triggerJob(jobKey);
		} catch (SchedulerException e) {
			LOGGER.error("调度异常", e);
		}
	}



	@Override
	public boolean isExistsTask(String jobName, String jobGroup) {
		boolean flag = Boolean.FALSE;
		if(StringUtils.isBlank(jobName) || StringUtils.isBlank(jobGroup)){
			return flag;
		}
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		JobDetail jobDetail;
		try {
			jobDetail = scheduler.getJobDetail(jobKey);
			if(jobDetail != null){
				flag = Boolean.TRUE;
			}
		} catch (SchedulerException e) {
			LOGGER.error("任务不存在[jobname="+jobName+",jobGroup="+jobGroup+"]",e);
		}
		
		
		return flag;
	}

	
}
