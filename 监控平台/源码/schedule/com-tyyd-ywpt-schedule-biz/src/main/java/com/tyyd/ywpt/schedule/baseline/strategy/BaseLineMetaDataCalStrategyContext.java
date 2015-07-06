/**   
* @Title: BaseLineMetaDataCalStrategyContext.java 
* @Package com.tyyd.ywpt.schedule.baseline.strategy 
* @Description:  
* @author wangyu   
* @date 2015-3-10 下午6:22:48 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.baseline.strategy;

/**
 * @author wangyu
 *
 */
public class BaseLineMetaDataCalStrategyContext {

	private BaseLineMetaDataCalStrategyManager manager = null;
	
	/**
	 * 赋予一个策略
	 * @param manager
	 */
	public BaseLineMetaDataCalStrategyContext(BaseLineMetaDataCalStrategyManager _manager) {
		this.manager = _manager;
	}
	
	
	/**
	 * 执行具体的算法
	 * @param monitorId
	 * @param quotaId
	 */
	public void calculate(String monitorId,String quotaId){
		if(null == this.manager){
			return;
		}
		
		this.manager.calMetaData(monitorId, quotaId);
	}
	
}
