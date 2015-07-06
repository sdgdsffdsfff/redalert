/**   
* @Title: StatisticsRecordLog.java 
* @Package com.tyyd.ywpt.dao.core.collect.together.log 
* @Description:  
* @author wangyu   
* @date 2014-9-22 上午9:26:00 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.together.log;

import com.tyyd.ywpt.dao.core.collect.together.log.dataobject.StatisticsRecordLogDomain;

/**
 * @author wangyu
 *
 */
public interface StatisticsRecordLogDAO {


	/**
	 * 添加执行记录
	 * @param log
	 */
	public void addStatisticsRecordLog(StatisticsRecordLogDomain log);
	
	
	/**
	 * 更新处理时间
	 * @param workTime
	 * @param sysType
	 * @param statType
	 */
	public void updateStatisticsRecordLog(String workTime,Integer sysType,Integer statType);
	
	
	/**
	 * 获取执行记录
	 * @param sysType
	 * @param statType
	 */
	public StatisticsRecordLogDomain getStatisticsRecord(Integer sysType,Integer statType);
	
}
