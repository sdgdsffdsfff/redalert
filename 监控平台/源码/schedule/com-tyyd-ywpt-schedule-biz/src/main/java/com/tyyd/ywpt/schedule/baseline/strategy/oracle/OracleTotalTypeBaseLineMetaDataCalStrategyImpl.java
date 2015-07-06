/**   
* @Title: OracleTotalTypeBaseLineMetaDataCalStrategyImpl.java 
* @Package com.tyyd.ywpt.schedule.baseline.strategy.oracle 
* @Description:  
* @author wangyu   
* @date 2015-3-10 下午6:25:00 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.baseline.strategy.oracle;

import javax.annotation.Resource;

import com.tyyd.ywpt.dao.baseline.BaseLineRecordDAO;
import com.tyyd.ywpt.schedule.baseline.strategy.BaseLineMetaDataCalStrategyManager;

/**
 * @author wangyu
 *
 */
public class OracleTotalTypeBaseLineMetaDataCalStrategyImpl implements
		BaseLineMetaDataCalStrategyManager {

	
	@Resource
	private BaseLineRecordDAO baseLineRecordDAO;
	
	
	@Override
	public void calMetaData(String monitorId, String quotaId) {
		baseLineRecordDAO.saveOracleTotalTypeBaseLine(monitorId, quotaId);
	}

}
