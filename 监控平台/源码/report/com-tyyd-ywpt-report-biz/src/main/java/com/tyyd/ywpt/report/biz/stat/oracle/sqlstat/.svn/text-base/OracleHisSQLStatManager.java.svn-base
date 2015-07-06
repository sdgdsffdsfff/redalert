/**   
 * @Title: OracleHisSQLStatManager.java 
 * @Package com.tyyd.ywpt.report.biz.stat.oracle.sqlstat 
 * @Description:  
 * @author wangyu   
 * @date 2014-12-3 下午2:37:02 
 * @CopyRight 天翼阅读
 * @version V1.0   
 */
package com.tyyd.ywpt.report.biz.stat.oracle.sqlstat;

import java.util.List;

import com.tyyd.ywpt.dao.stat.oracle.dataobject.OracleDbaHisActiveSessEventDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.OracleDbaHisActiveSessSampleDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.OracleDbaSqlStatListDomain;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleGeneralReportDataBO;

/**
 * @author wangyu
 * 
 */
public interface OracleHisSQLStatManager {

	/**
	 * SQL Stat
	 * @param beginTime
	 * @param endTime
	 * @param statType
	 * @param databaseId
	 * @param instanceId
	 * @param sqlId
	 * @return
	 */
	public MultipleGeneralReportDataBO listSQLStatReportData(
			String beginTime, String endTime, String[] statType, Long databaseId,
			Long instanceId, String sqlId);
	
	
	
	/**
	 * sampleTime
	 * @param beginTime
	 * @param endTime
	 * @param dbId
	 * @param minCounts
	 * @return
	 */
	public List<OracleDbaHisActiveSessSampleDomain> listSampleTimes(String beginTime,String endTime,String dbId,Integer minCounts);
	

	
	/**
	 * 事件
	 * @param sampleTime
	 * @param dbId
	 * @return
	 */
	public List<OracleDbaHisActiveSessEventDomain> listEventsBySampleTime(String sampleTime, String dbId);
	
	
	/**
	 * 根据sqlid查询最近的状况
	 * @param sqlId
	 * @param databaseId
	 * @param instanceId
	 * @param times
	 * @return
	 */
	public List<OracleDbaSqlStatListDomain> listOracleSqlStatBySqlId(String sqlId,String dbId,Integer times);
	
	
}
