/**   
* @Title: BaseLineMetaDataCalManager.java 
* @Package com.tyyd.ywpt.schedule.baseline.strategy 
* @Description:  
* @author wangyu   
* @date 2015-3-10 下午6:21:05 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.baseline.strategy;

/**
 * @author wangyu
 *
 */
public interface BaseLineMetaDataCalStrategyManager {

	/**
	 * 计算收集数据
	 * @param monitorId
	 * @param quotaId
	 */
	public void calMetaData(String monitorId,String quotaId);
}
