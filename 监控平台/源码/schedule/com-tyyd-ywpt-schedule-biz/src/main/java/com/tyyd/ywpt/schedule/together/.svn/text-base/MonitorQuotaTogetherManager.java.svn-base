/**   
* @Title: MonitorQuotaTogetherManager.java 
* @Package com.tyyd.ywpt.schedule.together 
* @Description:  
* @author wangyu   
* @date 2014-9-23 上午10:15:01 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.together;

/**
 * @author wangyu
 *
 */
public interface MonitorQuotaTogetherManager {

	/**
	 * 采集统计
	 * @param startDate
	 * @param endDate
	 * @param sysType 系统类型
	 * @param statType 统计类型,0: 历史迁移,1: 4小时任务,2:日任务,3:周任务
	 */
	public void quotaTogether(String startDate,String endDate,Integer sysType,Integer statType);
	
	
	
	
	/**
	 * 清理脏数据
	 * @param deleteFromDate
	 * @param sysType
	 * @param statType
	 */
	public void clearQuotaTogether(String deleteFromDate,Integer sysType,Integer statType);
}
