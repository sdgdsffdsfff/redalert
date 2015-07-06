/**   
* @Title: BaseLineCalMetaDataFacade.java 
* @Package com.tyyd.ywpt.schedule.baseline.facade 
* @Description:  
* @author wangyu   
* @date 2015-3-10 下午7:29:01 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.baseline.facade.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.tyyd.ywpt.schedule.baseline.facade.BaseLineMonitorCalMetaDataFacade;
import com.tyyd.ywpt.schedule.baseline.handler.BaseLineMonitorHandler;

/**
 * @author wangyu
 *
 */
public class BaseLineMonitorCalMetaDataFacadeImpl implements BaseLineMonitorCalMetaDataFacade {

	public static final Logger LOGGER = Logger.getLogger(BaseLineMonitorCalMetaDataFacadeImpl.class);
	
	@Resource
	private BaseLineMonitorHandler baseLineMonitorHandler;
	
	public void doCalMetaData(String monitorId,Integer configType,String quotaId){
		LOGGER.info("开始计算基线...");
		long start = System.currentTimeMillis();
		this.baseLineMonitorHandler.handler(monitorId, configType, quotaId);
		LOGGER.info(String.format("计算基线结束,本次计算总计花费%s秒 ",(System.currentTimeMillis()-start)/1000));
	}
}
