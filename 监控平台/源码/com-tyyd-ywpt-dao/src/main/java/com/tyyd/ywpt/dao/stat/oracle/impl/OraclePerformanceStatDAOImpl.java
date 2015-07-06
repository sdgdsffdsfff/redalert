/**   
* @Title: OraclePerformanceStatDAOImpl.java 
* @Package com.tyyd.ywpt.dao.stat.oracle.impl 
* @Description:  
* @author wangyu   
* @date 2015-5-12 下午3:19:48 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.stat.oracle.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.stat.oracle.OraclePerformanceStatDAO;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.OracleSnapShotDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.snap.OracleSnapActiceSessionDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.snap.OracleSnapEventClassDetailDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.snap.OracleSnapEventSQLDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.snap.OracleSnapTopSQLDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.snap.OracleSnapWaitEventDomain;

/**
 * @author wangyu
 *
 */
public class OraclePerformanceStatDAOImpl extends TyydBaseDAO implements OraclePerformanceStatDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.stat.oracle.dataobject.snap.OracleSnapActiceSessionDomain";
	
	
	@Override
	public List<OracleSnapTopSQLDomain> listTopSQL(Long snapId,
			Long dataBaseId, Long instanceId,String sortColumn,String sortType) {
		
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("snapId", snapId);
		parameter.put("dataBaseId", dataBaseId);
		parameter.put("instanceId", instanceId);
		parameter.put("sortColumn", sortColumn);
		parameter.put("sortType", sortType);
		return this.getSqlSessionTemplate().selectList(context_space+".list_top_sql", parameter);
	}

	@Override
	public String getSqlTextById(String sqlId, Long dataBaseId,
			Long instanceId) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("sqlId", sqlId);
		parameter.put("dataBaseId", dataBaseId);
		parameter.put("instanceId", instanceId);
		
		
		return this.getSqlSessionTemplate().selectOne(context_space+".get_sql_text_by_id", parameter);
	}

	@Override
	public List<OracleSnapActiceSessionDomain> listActiceSession(Long snapId,
			Long dataBaseId, Long instanceId) {
		
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("snapId", snapId);
		parameter.put("dataBaseId", dataBaseId);
		parameter.put("instanceId", instanceId);
		
		
		return this.getSqlSessionTemplate().selectList(context_space+".list_actice_session", parameter);
	}

	@Override
	public List<OracleSnapWaitEventDomain> totalWaitEvent(Long snapId,
			Long dataBaseId, Long instanceId,Long lastSnapId) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("snapId", snapId);
		parameter.put("dataBaseId", dataBaseId);
		parameter.put("instanceId", instanceId);
		parameter.put("lastSnapId", lastSnapId);
		
		
		return this.getSqlSessionTemplate().selectList(context_space+".total_wait_event", parameter);
	}

	@Override
	public List<OracleSnapEventClassDetailDomain> loadEventByClass(
			Long snapId, Long dataBaseId, Long instanceId, String waitClass,Long lastSnapId) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("snapId", snapId);
		parameter.put("dataBaseId", dataBaseId);
		parameter.put("instanceId", instanceId);
		parameter.put("waitClass", waitClass);
		parameter.put("lastSnapId", lastSnapId);
		
		return this.getSqlSessionTemplate().selectList(context_space+".load_event_by_class", parameter);
	}

	@Override
	public List<OracleSnapEventSQLDomain> loadEventSql(Long snapId,
			Long dataBaseId, Long instanceId, String eventName) {
		
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("snapId", snapId);
		parameter.put("dataBaseId", dataBaseId);
		parameter.put("instanceId", instanceId);
		parameter.put("eventName", eventName);
		
		return this.getSqlSessionTemplate().selectList(context_space+".load_event_sql", parameter);
	}

	@Override
	public OracleSnapShotDomain getOracleSnapShotDomain(Long dbId,
			Long instanceId, String quotaTime) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("quotaTime", quotaTime);
		parameter.put("dbId", dbId);
		parameter.put("instanceId", instanceId);
		
		return this.getSqlSessionTemplate().selectOne(context_space+".get_oracle_snap_shot", parameter);
	}

	@Override
	public OracleSnapShotDomain lastOracleSnapShotDomain(Long dbId,
			Long instanceId, Long snapId) {
		
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("snapId", snapId);
		parameter.put("dbId", dbId);
		parameter.put("instanceId", instanceId);
		
		return this.getSqlSessionTemplate().selectOne(context_space+".last_oracle_snap_shot", parameter);
		
	}

}
