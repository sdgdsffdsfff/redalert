/**   
* @Title: DBAHisTablesScheduleManager.java 
* @Package com.tyyd.ywpt.schedule.his.dba 
* @Description:  
* @author wangyu   
* @date 2015-2-4 下午2:15:44 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.his.dba;

/**
 * @author wangyu
 *
 */
public interface DBAHisTablesScheduleManager {

	/**
	 * 清理旧数据
	 */
	public void doClearHisDataTask();
}
