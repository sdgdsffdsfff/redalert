/**   
* @Title: OracleSQLStatDAOImpl.java 
* @Package com.tyyd.ywpt.dao.stat.oracle.impl 
* @Description:  
* @author wangyu   
* @date 2014-12-3 下午2:05:05 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.stat.oracle.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.stat.oracle.OracleSQLStatDAO;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.OracleDbaHisActiveSessEventDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.OracleDbaHisActiveSessSampleDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.OracleDbaHisSQLStatDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.OracleDbaSqlStatListDomain;

/**
 * @author wangyu
 *
 */
public class OracleSQLStatDAOImpl extends TyydBaseDAO implements OracleSQLStatDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.stat.oracle.dataobject.OracleDbaHisSQLStatDomain";
	private final static String sample_context_space = "com.tyyd.ywpt.dao.stat.oracle.dataobject.OracleDbaHisActiveSessSampleDomain";
	private final static String event_context_space = "com.tyyd.ywpt.dao.stat.oracle.dataobject.OracleDbaHisActiveSessEventDomain";
	
	@Override
	public List<OracleDbaHisSQLStatDomain> listOracleSqlStat(String beginTime,
			String endTime, String statType, Long databaseId, Long instanceId,
			String sqlId) {
		
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("beginTime", beginTime);
		parameter.put("endTime", endTime);
		parameter.put("statType", statType);
		parameter.put("databaseId", databaseId);
		parameter.put("instanceId", instanceId);
		parameter.put("sqlId", sqlId);
		
		return this.getSqlSessionTemplate().selectList(context_space+".list_report_sql_stat", parameter);
	}

	@Override
	public List<OracleDbaHisActiveSessSampleDomain> listSampleTimes(
			String beginTime, String endTime, Long databaseId, Long instanceId,
			Integer minCounts) {
		
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("beginTime", beginTime);
		parameter.put("endTime", endTime);
		parameter.put("databaseId", databaseId);
		parameter.put("instanceId", instanceId);
		parameter.put("minCounts", minCounts);
		
		return this.getSqlSessionTemplate().selectList(sample_context_space+".list_sample_time", parameter);
	}

	@Override
	public List<OracleDbaHisActiveSessEventDomain> listEventsBySampleTime(
			String sampleTime, Long databaseId, Long instanceId) {
		
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("sampleTime", sampleTime);
		parameter.put("databaseId", databaseId);
		parameter.put("instanceId", instanceId);
		
		return this.getSqlSessionTemplate().selectList(event_context_space+".list_events_by_sample_time", parameter);
	}

	@Override
	public List<OracleDbaSqlStatListDomain> listOracleSqlStatBySqlId(
			String sqlId, Long databaseId, Long instanceId, Integer times) {
		
		
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("times", times);
		parameter.put("databaseId", databaseId);
		parameter.put("instanceId", instanceId);
		parameter.put("sqlId", sqlId);
		
		return this.getSqlSessionTemplate().selectList(context_space+".list_sql_run_status", parameter);
	}

}
