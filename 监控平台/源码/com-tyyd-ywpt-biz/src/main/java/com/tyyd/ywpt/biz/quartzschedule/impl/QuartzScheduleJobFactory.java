/**   
* @Title: QuartzScheduleJobFactory.java 
* @Package com.tyyd.ywpt.biz.quartzschedule.impl 
* @Description:  
* @author wangyu   
* @date 2014-6-30 上午10:37:17 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.quartzschedule.impl;

import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.tyyd.ywpt.biz.quartzschedule.dataobject.QuartzScheduleJobBO;

/**
 * 
 * http://blog.ticeng.com/178.html
 * @author wangyu
 *
 */

@DisallowConcurrentExecution
public class QuartzScheduleJobFactory implements Job {

	public static final Logger LOGGER = Logger.getLogger(QuartzScheduleJobFactory.class);  
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		LOGGER.info("任务成功运行");
		QuartzScheduleJobBO scheduleJob = (QuartzScheduleJobBO)context.getMergedJobDataMap().get("taskData");
		LOGGER.info("任务名称 = [" + scheduleJob.getJobName() + ", beanId= "+ scheduleJob.getBeanId() + "]");
		
	}


}
