/**   
* @Title: TaskMonitorManager.java 
* @Package com.tyyd.ywpt.biz.quartzschedule 
* @Description:  
* @author wangyu   
* @date 2014-11-6 下午4:46:49 
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
public interface TaskMonitorManager {

	
	/**
	 * 已经注册的任务
	 * @return
	 */
	public List<QuartzScheduleJobBO> regTasks();
	
	
	/**
	 * 正在运行的任务
	 * @return
	 */
	public List<QuartzScheduleJobBO> runningTasks();
	
	
	/**
	 * 运行的情况
	 * @return
	 */
	public List<QuartzScheduleJobBO> runTaskInfo(String srvId);
	
	
	public List<QuartzScheduleJobBO> allJobs(); 
	
	
	
}
