/**   
* @Title: JobTest.java 
* @Package com.tyyd.ywpt.biz.job 
* @Description:  
* @author wangyu   
* @date 2014-6-30 上午10:56:47 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.job;

import java.util.HashMap;
import java.util.Map;

import com.tyyd.ywpt.biz.quartzschedule.dataobject.QuartzScheduleJobBO;

/**
 * @author wangyu
 *
 */
public class JobTest {

	private static Map<String, QuartzScheduleJobBO> jobMap = new HashMap<String, QuartzScheduleJobBO>();
	
	
	static {
		for (int i = 0; i < 5; i++) {
			QuartzScheduleJobBO job = new QuartzScheduleJobBO();
			job.setJobId("10001" + i);
			job.setJobName("data_import" + i);
			job.setJobGroup("dataWork");
			job.setJobStatus("1");
			job.setCronExpression("0/5 * * * * ?");
			job.setDesc("数据导入任务");
			addJob(job);
		}
	}
	
	
	/**
	 * 添加任务
	 * @param scheduleJob
	 */
	public static void addJob(QuartzScheduleJobBO scheduleJob) {
		jobMap.put(scheduleJob.getJobGroup() + "_" + scheduleJob.getJobName(), scheduleJob);
	}
	
	
	
	public static Map<String, QuartzScheduleJobBO> getTestData(){
		return jobMap;
	}
}
