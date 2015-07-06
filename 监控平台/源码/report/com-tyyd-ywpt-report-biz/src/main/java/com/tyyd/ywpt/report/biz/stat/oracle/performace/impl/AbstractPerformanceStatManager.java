/**   
* @Title: AbstractPerformanceStatManager.java 
* @Package com.tyyd.ywpt.report.biz.stat.oracle.performace.impl 
* @Description:  
* @author wangyu   
* @date 2015-5-13 下午3:52:26 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.stat.oracle.performace.impl;

import javax.annotation.Resource;

import com.tyyd.ywpt.dao.configration.db.DbConfigDAO;
import com.tyyd.ywpt.dao.core.collect.even.oracle.OracleQuotaCollectDayDAO;
import com.tyyd.ywpt.dao.stat.oracle.OraclePerformanceStatDAO;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.OracleSnapShotDomain;

/**
 * @author wangyu
 *
 */
public abstract class AbstractPerformanceStatManager {

	@Resource
	protected OraclePerformanceStatDAO oraclePerformanceStatDAO;
	
	@Resource
	protected OracleQuotaCollectDayDAO oracleQuotaCollectDayDAO;
	
	@Resource
	protected DbConfigDAO dbConfigDAO;
	
	
	protected OracleSnapShotDomain getSnapId(Long dbId,Long instanceId,String quotaTime) {
		OracleSnapShotDomain oracleSnapShotDomain = oraclePerformanceStatDAO.getOracleSnapShotDomain(dbId, instanceId, quotaTime);
		return oracleSnapShotDomain;
	}
	
	
	protected OracleSnapShotDomain loadLastSnapId(Long dbId,Long instanceId,Long snapId) {
		OracleSnapShotDomain oracleSnapShotDomain = oraclePerformanceStatDAO.lastOracleSnapShotDomain(dbId, instanceId, snapId);
		return oracleSnapShotDomain;
	}


	
	
}
