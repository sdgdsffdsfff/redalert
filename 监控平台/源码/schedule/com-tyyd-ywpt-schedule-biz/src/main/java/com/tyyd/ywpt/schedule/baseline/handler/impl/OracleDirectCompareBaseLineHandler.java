/**   
* @Title: OracleDirectCompareBaseLineHandler.java 
* @Package com.tyyd.ywpt.schedule.baseline.handler.impl 
* @Description:  
* @author wangyu   
* @date 2015-3-10 下午6:57:19 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.baseline.handler.impl;

import javax.annotation.Resource;

import com.tyyd.ywpt.biz.dict.QuotaThresholdTypeEnum;
import com.tyyd.ywpt.biz.dict.SysTypeEnum;
import com.tyyd.ywpt.schedule.baseline.handler.AbstractBaseLineHandler;
import com.tyyd.ywpt.schedule.baseline.strategy.BaseLineMetaDataCalStrategyManager;

/**
 * @author wangyu
 *
 */
public class OracleDirectCompareBaseLineHandler extends AbstractBaseLineHandler {
	
	@Resource
	private BaseLineMetaDataCalStrategyManager oracleDirectCompareBaseLineMetaDataCalStrategy;
	
	
	@Override
	protected Integer getMonitorType() {
		return SysTypeEnum.Oracle.getVal();
	}

	@Override
	protected Integer getQuotaCalType() {
		return QuotaThresholdTypeEnum.compare_big_warn.getType();
	}

	@Override
	protected void calMetaData(String monitorId, Integer configType,
			String quotaId) {
		doCal(monitorId,quotaId);
	}

	
	@Override
	protected BaseLineMetaDataCalStrategyManager getCalStrategy() {
		return this.oracleDirectCompareBaseLineMetaDataCalStrategy;
	}

}
