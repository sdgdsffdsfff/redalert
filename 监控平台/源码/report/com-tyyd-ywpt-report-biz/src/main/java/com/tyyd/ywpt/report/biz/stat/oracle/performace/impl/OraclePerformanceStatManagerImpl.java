/**   
* @Title: OraclePerformanceStatManagerImpl.java 
* @Package com.tyyd.ywpt.report.biz.stat.oracle.performace.impl 
* @Description:  
* @author wangyu   
* @date 2015-5-13 下午5:01:48 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.stat.oracle.performace.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tyyd.ywpt.biz.util.DateUtils;
import com.tyyd.ywpt.dao.configration.db.dataobject.DbConfigDomain;
import com.tyyd.ywpt.dao.core.collect.even.oracle.dataobject.OracleQuotaCollectDayDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.OracleSnapShotDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.snap.OracleSnapActiceSessionDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.snap.OracleSnapEventClassDetailDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.snap.OracleSnapEventSQLDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.snap.OracleSnapTopSQLDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.snap.OracleSnapWaitEventDomain;
import com.tyyd.ywpt.report.biz.stat.oracle.performace.OraclePerformanceStatManager;

/**
 * @author wangyu
 *
 */
public class OraclePerformanceStatManagerImpl extends AbstractPerformanceStatManager implements OraclePerformanceStatManager{

	@Override
	public OracleSnapShotDomain getSnap(
			String oracleQuotaCollectId) {

		//获取指标对象
		OracleQuotaCollectDayDomain oracleQuotaCollectDayDomain = oracleQuotaCollectDayDAO.getOracleMonitorDataById(oracleQuotaCollectId);
		Date gmtCreated = oracleQuotaCollectDayDomain.getGmtCreated();
		String quotaTime = DateUtils.DateToString(gmtCreated, "yyyy-MM-dd HH:mm:ss");

		//获取数据库信息
		DbConfigDomain dbConfigDomain = dbConfigDAO.getDataBaseConfig(oracleQuotaCollectDayDomain.getDbId());
		Long dataBaseId = dbConfigDomain.getOraDataBaseId();
		Long instanceId = dbConfigDomain.getOraInstanceId();
		
		if(dataBaseId == null || instanceId == null){
			return null;
		}
		
		//获取当前快照
		OracleSnapShotDomain oracleSnapShotDomain = this.getSnapId(dataBaseId, instanceId, quotaTime);
		if(oracleSnapShotDomain == null){
			return null;
		}
		
		//获取上一个快照
		
		OracleSnapShotDomain lastOracleSnapShotDomain = this.loadLastSnapId(dataBaseId, instanceId, oracleSnapShotDomain.getSnapId());
		if(lastOracleSnapShotDomain == null){
			return null;
		}
		
		
		oracleSnapShotDomain.setLastSnapId(lastOracleSnapShotDomain.getSnapId());
		oracleSnapShotDomain.setMonitorId(oracleQuotaCollectDayDomain.getDbId());
		oracleSnapShotDomain.setSampleTime(quotaTime);
		return oracleSnapShotDomain;
	}

	@Override
	public List<OracleSnapTopSQLDomain> getTopSQL(Long snapId,Long dataBaseId,Long instanceId,String sortColumn,String sortType) {
		
		sortColumn = getSortColumn(sortColumn);
		sortType = getSortType(sortType);
		
		return oraclePerformanceStatDAO.listTopSQL(snapId, dataBaseId, instanceId,sortColumn,sortType);
	}

	
	/**
	 * @param sortType
	 * @return
	 */
	private String getSortType(String sortType) {
		String result = "asc";
		
		//sortType
		if(StringUtils.isBlank(sortType)){
			sortType = StringUtils.EMPTY;
		}
		
		if(sortType.equals("0")){
			result = "asc";
		}
		
		if(sortType.equals("1")){
			result = "desc";
		}
		
		return result;
	}

	/**
	 * @param sortColumn
	 * @return
	 */
	private String getSortColumn(String sortColumn) {
		String result = "";
		if(StringUtils.isBlank(sortColumn)){
			sortColumn = StringUtils.EMPTY;
		}
		
		//获取column
		sortColumn = sortColumn.replace("sql_", "");
		
		result = OraclePerformanceTopSQLEnum.getColumn(sortColumn);
		return result;
	}
	
	@Override
	public List<OracleSnapActiceSessionDomain> listActiceSession(Long snapId,
			Long dataBaseId, Long instanceId) {
		return oraclePerformanceStatDAO.listActiceSession(snapId, dataBaseId, instanceId);
	}

	@Override
	public List<OracleSnapWaitEventDomain> totalWaitEvent(Long snapId,
			Long dataBaseId, Long instanceId,Long lastSnapId) {
		return oraclePerformanceStatDAO.totalWaitEvent(snapId, dataBaseId, instanceId, lastSnapId);
	}

	@Override
	public List<OracleSnapEventClassDetailDomain> loadEventByClass(
			Long snapId, Long dataBaseId, Long instanceId, String waitClass,Long lastSnapId) {
		return oraclePerformanceStatDAO.loadEventByClass(snapId, dataBaseId, instanceId, waitClass, lastSnapId);
	}

	@Override
	public List<OracleSnapEventSQLDomain> loadEventSql(Long snapId,
			Long dataBaseId, Long instanceId, String eventName) {
		return oraclePerformanceStatDAO.loadEventSql(snapId, dataBaseId, instanceId, eventName);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.report.biz.stat.oracle.performace.OraclePerformanceStatManager#getSqlText(java.lang.String, java.lang.Long, java.lang.Long)
	 */
	@Override
	public String getSqlText(String sqlId, Long dataBaseId, Long instanceId) {
		return oraclePerformanceStatDAO.getSqlTextById(sqlId, dataBaseId, instanceId);
	}

}
