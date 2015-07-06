/**   
* @Title: AbstractBaseLineHandler.java 
* @Package com.tyyd.ywpt.schedule.baseline.handler 
* @Description:  
* @author wangyu   
* @date 2015-3-10 下午6:42:22 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.baseline.handler;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tyyd.ywpt.dao.baseline.BaseLineRecordDAO;
import com.tyyd.ywpt.schedule.baseline.strategy.BaseLineMetaDataCalStrategyContext;
import com.tyyd.ywpt.schedule.baseline.strategy.BaseLineMetaDataCalStrategyManager;
import com.tyyd.ywpt.tools.bean.SpringContextHolder;

/**
 * @author wangyu
 *
 */
public abstract class AbstractBaseLineHandler {

	public static final Logger LOGGER = Logger.getLogger(AbstractBaseLineHandler.class);
	
	private AbstractBaseLineHandler nextHandler;
	
	/**
	 * 获取具体的策略计算
	 * @param monitorId
	 * @param configType
	 * @param quotaId
	 * @param quotaCalType
	 */
	public final void baseLineCalHandler(String monitorId,Integer configType,String quotaId,Integer quotaCalType){
		
		//检查有入参错误
		if(!this.checkParameter(monitorId, configType, quotaId, quotaCalType)){
			return;
		}
		
		//判断主机类型及指标类型
		if(this.getMonitorType().intValue() == configType.intValue() 
				&& this.getQuotaCalType().intValue() == quotaCalType.intValue()){
			
			LOGGER.info(String.format("选择了 %s 算法 ", this.getCalStrategy().getClass().getName()));
			
			//清理历史
			this.delHistoryMetaData(monitorId, configType, quotaId);
			
			//干活
			this.calMetaData(monitorId, configType, quotaId);
		}else{
			//判断不是这个handler，丢个下个handler
			if(this.nextHandler != null){
				this.nextHandler.baseLineCalHandler(monitorId, configType, quotaId, quotaCalType);
			}
		}
		
		
	}
	
	
	/**
	 * @param monitorId
	 * @param configType
	 * @param quotaId
	 */
	private void delHistoryMetaData(String monitorId, Integer configType,
			String quotaId) {
		BaseLineRecordDAO baseLineRecordDAO = SpringContextHolder.getBean("baseLineRecordDAO");
		baseLineRecordDAO.delBaseLineRecord(monitorId, configType, quotaId);
	}


	/**
	 * 检查入参
	 * @param monitorId
	 * @param configType
	 * @param quotaId
	 * @param quotaCalType
	 * @return
	 */
	private boolean checkParameter(String monitorId,Integer configType,String quotaId,Integer quotaCalType){
		if(StringUtils.isBlank(monitorId) 
				|| StringUtils.isBlank(quotaId) 
				|| null == configType
				|| null == quotaCalType){
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}
	
	
	protected void doCal(String monitorId,String quotaId){
		BaseLineMetaDataCalStrategyManager strategy = getCalStrategy(); 
		BaseLineMetaDataCalStrategyContext context = new BaseLineMetaDataCalStrategyContext(strategy);
		context.calculate(monitorId, quotaId);
	}
	
	/**
	 * 配置下个处理者
	 * @param _handler
	 */
	public void setNextHandler(AbstractBaseLineHandler _handler){
		this.nextHandler = _handler;
	}
	
	/**
	 * 获取对应的策略
	 * @return
	 */
	protected abstract BaseLineMetaDataCalStrategyManager getCalStrategy() ;


	/**
	 * 赋予监控机的类型
	 * @return
	 */
	protected abstract Integer getMonitorType();
	
	
	/**
	 * 赋予指标的计算类型
	 * @return
	 */
	protected abstract Integer getQuotaCalType();
	
	
	/**
	 * 具体计算方法
	 * @param monitorId
	 * @param configType
	 * @param quotaId
	 */
	protected abstract void calMetaData(String monitorId,Integer configType,String quotaId);
	
}
