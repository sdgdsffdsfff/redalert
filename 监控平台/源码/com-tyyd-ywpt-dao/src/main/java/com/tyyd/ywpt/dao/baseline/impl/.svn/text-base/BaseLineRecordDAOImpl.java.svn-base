/**   
* @Title: BaseLineRecordDAOImpl.java 
* @Package com.tyyd.ywpt.dao.baseline.impl 
* @Description:  
* @author wangyu   
* @date 2015-3-10 上午11:10:35 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.baseline.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.baseline.BaseLineRecordDAO;
import com.tyyd.ywpt.dao.baseline.dataobject.BaseLineRecordDomain;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class BaseLineRecordDAOImpl extends TyydBaseDAO implements
		BaseLineRecordDAO {
	
	private final static String context = "com.tyyd.ywpt.dao.baseline.dataobject.BaseLineRecordDomain";

	@Override
	public void saveHostTotalTypeBaseLine(String hostId, String quotaId) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("hostId", hostId);
		parameter.put("quotaId", quotaId);
		
		this.getSqlSessionTemplate().insert(context+".save_host_total_type_base_line", parameter);
	}

	@Override
	public void saveHostDirectCompareBaseLine(String hostId, String quotaId) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("hostId", hostId);
		parameter.put("quotaId", quotaId);
		
		this.getSqlSessionTemplate().insert(context+".save_host_direct_compare_base_line", parameter);
	}

	@Override
	public void saveOracleTotalTypeBaseLine(String dbId, String quotaId) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("dbId", dbId);
		parameter.put("quotaId", quotaId);
		
		this.getSqlSessionTemplate().insert(context+".save_oracle_total_type_base_line", parameter);
	}

	@Override
	public void saveOracleDirectCompareBaseLine(String dbId, String quotaId) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("dbId", dbId);
		parameter.put("quotaId", quotaId);
		
		this.getSqlSessionTemplate().insert(context+".save_oracle_direct_compare_base_line", parameter);
	}

	@Override
	public void saveMySQLTotalTypeBaseLine(String dbId, String quotaId) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("dbId", dbId);
		parameter.put("quotaId", quotaId);
		
		this.getSqlSessionTemplate().insert(context+".save_mysql_total_type_base_line", parameter);
	}

	@Override
	public void saveMySQLDirectCompareBaseLine(String dbId, String quotaId) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("dbId", dbId);
		parameter.put("quotaId", quotaId);
		
		this.getSqlSessionTemplate().insert(context+".save_mysql_direct_compare_base_line", parameter);
	}

	@Override
	public List<BaseLineRecordDomain> listBaseLineRecord(String monitorId,
			Integer configType, String quotaId) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("monitorId", monitorId);
		parameter.put("configType", configType);
		parameter.put("quotaId", quotaId);
		
		
		return this.getSqlSessionTemplate().selectList(context+".list_base_line_record",parameter);
	}

	@Override
	public void delBaseLineRecord(String monitorId, Integer configType,
			String quotaId) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("monitorId", monitorId);
		parameter.put("configType", configType);
		parameter.put("quotaId", quotaId);
		
		this.getSqlSessionTemplate().delete(context+".del_base_line_record", parameter);
	}

}
