/**   
* @Title: BaseLineMetaDataCalStrategyManagerImpl.java 
* @Package com.tyyd.ywpt.schedule.baseline.strategy.server 
* @Description:  
* @author wangyu   
* @date 2015-3-10 下午6:23:15 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.baseline.strategy.server;

import javax.annotation.Resource;

import com.tyyd.ywpt.dao.baseline.BaseLineRecordDAO;
import com.tyyd.ywpt.schedule.baseline.strategy.BaseLineMetaDataCalStrategyManager;

/**
 * @author wangyu
 *
 */
public class ServerTotalTypeBaseLineMetaDataCalStrategyImpl implements
		BaseLineMetaDataCalStrategyManager {

	@Resource
	private BaseLineRecordDAO baseLineRecordDAO;
	
	@Override
	public void calMetaData(String monitorId, String quotaId) {
		this.baseLineRecordDAO.saveHostTotalTypeBaseLine(monitorId, quotaId);
	}

}
