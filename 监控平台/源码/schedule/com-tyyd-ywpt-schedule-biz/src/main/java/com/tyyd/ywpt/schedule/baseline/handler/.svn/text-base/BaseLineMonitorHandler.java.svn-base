/**   
* @Title: BaseLineMonitorHandler.java 
* @Package com.tyyd.ywpt.schedule.baseline.handler 
* @Description:  
* @author wangyu   
* @date 2015-3-10 下午7:32:30 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.baseline.handler;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;

import com.tyyd.ywpt.biz.dict.QuotaThresholdTypeEnum;
import com.tyyd.ywpt.dao.core.quota.QuotaModelDAO;
import com.tyyd.ywpt.dao.core.quota.dataobject.QuotaModelDomain;

/**
 * @author wangyu
 *
 */
public class BaseLineMonitorHandler implements InitializingBean{

	@Resource
	private AbstractBaseLineHandler serverDirectCompareBaseLineHandler;
	
	@Resource
	private AbstractBaseLineHandler serverTotalTypeBaseLineHandler;
	
	@Resource
	private AbstractBaseLineHandler mySQLDirectCompareBaseLineHandler;
	
	@Resource
	private AbstractBaseLineHandler mySQLTotalTypeBaseLineHandler;
	
	@Resource
	private AbstractBaseLineHandler oracleDirectCompareBaseLineHandler;
	
	@Resource
	private AbstractBaseLineHandler oracleTotalTypeBaseLineHandler;
	
	@Resource
	private QuotaModelDAO quotaModelDAO;

	@Override
	public void afterPropertiesSet() throws Exception {
		
		this.serverDirectCompareBaseLineHandler
				.setNextHandler(this.serverTotalTypeBaseLineHandler);
		
		this.serverTotalTypeBaseLineHandler
				.setNextHandler(this.mySQLDirectCompareBaseLineHandler);

		this.mySQLDirectCompareBaseLineHandler
				.setNextHandler(this.mySQLTotalTypeBaseLineHandler);
		
		this.mySQLTotalTypeBaseLineHandler
				.setNextHandler(this.oracleDirectCompareBaseLineHandler);

		this.oracleDirectCompareBaseLineHandler
				.setNextHandler(this.oracleTotalTypeBaseLineHandler);
	}
	
	
	public void handler(String monitorId,Integer configType,String quotaId){
		Integer quotaCalType = calQuotaType(monitorId,configType,quotaId);
		this.serverDirectCompareBaseLineHandler.baseLineCalHandler(monitorId, configType, quotaId, quotaCalType);
	}


	/**
	 * 获得指标的类型
	 * @param monitorId
	 * @param configType
	 * @param quotaId
	 * @return
	 */
	private Integer calQuotaType(String monitorId, Integer configType,
			String quotaId) {
		
		QuotaModelDomain domain = quotaModelDAO.getQuotaModelById(quotaId);
		if(null == domain || null == domain.getCalType()){
			return null;
		}
		
		if(domain.getCalType().intValue() == QuotaThresholdTypeEnum.compare_big_warn.getType() 
				|| domain.getCalType().intValue() == QuotaThresholdTypeEnum.compare_small_warn.getType()){
			return QuotaThresholdTypeEnum.compare_big_warn.getType();
		}else if(domain.getCalType().intValue() == QuotaThresholdTypeEnum.d_value_warn.getType()){
			return QuotaThresholdTypeEnum.d_value_warn.getType();
		}
		
		return null;
	}
	
	
	
	
}
