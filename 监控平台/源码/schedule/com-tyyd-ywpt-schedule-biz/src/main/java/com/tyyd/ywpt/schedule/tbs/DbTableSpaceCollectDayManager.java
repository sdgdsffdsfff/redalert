/**   
* @Title: DbTableSpaceCollectDayManager.java 
* @Package com.tyyd.ywpt.schedule.tbs 
* @Description:  
* @author wangyu   
* @date 2015-6-1 下午1:28:08 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.tbs;

/**
 * @author wangyu
 *
 */
public interface DbTableSpaceCollectDayManager {

	/**
	 * 生成某一个dbId的数据 
	 * @param hostId
	 */
	public void doDataBaseTask(String dbId);
	
	/**
	 * 调度任务
	 */
	public void doTask();
	
}
