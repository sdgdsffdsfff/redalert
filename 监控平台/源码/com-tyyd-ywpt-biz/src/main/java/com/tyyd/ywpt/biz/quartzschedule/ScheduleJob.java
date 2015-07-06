/**   
* @Title: ScheduleJob.java 
* @Package com.tyyd.ywpt.biz.quartzschedule 
* @Description:  
* @author wangyu   
* @date 2014-6-30 下午1:27:05 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.quartzschedule;

import java.util.List;

import com.tyyd.ywpt.biz.quartzschedule.dataobject.QuartzScheduleJobBO;

/**
 * @author wangyu
 *
 */
public interface ScheduleJob {
	
	
	/**
	 * 一次性加载所有任务
	 */
	public void loadSchedule(List<QuartzScheduleJobBO> jobList);

	
	
	/**
	 * 添加一个任务
	 * @param job
	 */
	public boolean addTask(QuartzScheduleJobBO job);
	
	
	
	/**
	 * 计划中的任务
	 */
	public List<QuartzScheduleJobBO> scheduleTask();
	
	
	/**
	 * 正在运行的任务
	 */
	public List<QuartzScheduleJobBO> runningTask();
	
	
	/**
	 * 暂停任务,慎用
	 * @param scheduleJob
	 */
	public void pauseTask(QuartzScheduleJobBO scheduleJob);
	
	/**
	 * 恢复任务,慎用
	 * @param scheduleJob
	 */
	public void resumeTask(QuartzScheduleJobBO scheduleJob);
	
	/**
	 * 删除任务
	 * @param scheduleJob
	 */
	public boolean delTask(String jobName,String jobGroup);
	
	/**
	 * 立即运行，但必须先注册了
	 * @param scheduleJob
	 */
	public void immediatelyRunTask(QuartzScheduleJobBO scheduleJob);
	
	
	/**
	 * 是否存在老任务
	 * @param jobName
	 * @param jobGroup
	 * @return
	 */
	public boolean isExistsTask(String jobName,String jobGroup);
	
}
