/**   
* @Title: DiskMonitorUpdateManager.java 
* @Package com.tyyd.ywpt.schedule.disk 
* @Description:  
* @author wangyu   
* @date 2015-6-3 下午5:30:50 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.disk;

/**
 * @author wangyu
 *
 */
public interface DiskMonitorUpdateManager {

	/**
	 * 更新磁盘数据
	 */
	public void doTask();
	
	/**
	 * 更新单个磁盘数据
	 */
	public void doSingleTask(String hostId);
}
