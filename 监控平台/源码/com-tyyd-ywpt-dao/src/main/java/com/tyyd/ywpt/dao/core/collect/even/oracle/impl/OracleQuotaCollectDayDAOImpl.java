/**   
* @Title: OracleQuotaCollectDayDAOImpl.java 
* @Package com.tyyd.ywpt.dao.core.collect.even.oracle.impl 
* @Description:  
* @author wangyu   
* @date 2014-6-25 上午11:55:44 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.even.oracle.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.core.collect.even.oracle.OracleQuotaCollectDayDAO;
import com.tyyd.ywpt.dao.core.collect.even.oracle.dataobject.OracleQuotaCollectDayDomain;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class OracleQuotaCollectDayDAOImpl extends TyydBaseDAO implements
		OracleQuotaCollectDayDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.core.collect.even.oracle.dataobject.OracleQuotaCollectDayDomain";
	
	@Override
	public void addOracleQuotaCollectDay(OracleQuotaCollectDayDomain domain) {
		this.getSqlSessionTemplate().insert(context_space+".add_oracle_quota_collect_day", domain);
	}

	@Override
	public void moveOracleQuotaCollectDay(String startDate, String endDate) {
		
	}

	@Override
	public List<OracleQuotaCollectDayDomain> listOracleQuotaCollectDay(
			String startDate, String endDate, String hostId, String dbId,
			String quotaId) {
		
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("startCreatedDate", startDate);
		parameter.put("endCreatedDate", endDate);
		parameter.put("hostId", hostId);
		parameter.put("quotaId", quotaId);
		parameter.put("dbId", dbId);
		return this.getSqlSessionTemplate().selectList(context_space+".list_oracle_quota_collect_day", parameter);
	}

	@Override
	public OracleQuotaCollectDayDomain getLastOracleQuotaCollectDay(
			String hostId, String dbId, String quotaId) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("hostId", hostId);
		parameter.put("quotaId", quotaId);
		parameter.put("dbId", dbId);
		return this.getSqlSessionTemplate().selectOne(context_space+".get_last_oracle_quota_collect", parameter);
	}

	@Override
	public List<OracleQuotaCollectDayDomain> listUnCompletedOracleQuotaCollectDay() {
		return this.getSqlSessionTemplate().selectList(context_space +".list_uncompleted_oracle_quota_collect_data");
	}

	@Override
	public void closeUnCompletedOracleQuota(String id) {
		 this.getSqlSessionTemplate().update(context_space+".close_uncompleted_quota_oracle_collect_data_by_id", id);
	}

	@Override
	public void closeNormalUnCompletedQuotaCollectData() {
		this.getSqlSessionTemplate().update(context_space+".close_normal_uncompleted_task");
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.even.oracle.OracleQuotaCollectDayDAO#listReportData(java.lang.String, java.lang.Integer)
	 */
	@Override
	public List<OracleQuotaCollectDayDomain> listReportData(String dbId,String quotaId,
			Integer pageSize,String startDate,String endDate) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("dbId", dbId);
		parameter.put("quotaId", quotaId);
		parameter.put("pageSize", pageSize);
		parameter.put("startDate", startDate);
		parameter.put("endDate", endDate);
		
		return this.getSqlSessionTemplate().selectList(context_space+".list_report_data", parameter);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.even.oracle.OracleQuotaCollectDayDAO#listComplexQuotaReportData(java.lang.String, java.lang.String[], java.lang.Integer)
	 */
	@Override
	public List<OracleQuotaCollectDayDomain> listComplexQuotaReportData(
			String dbId, String[] quotaId, Integer pageSize,String startDate,String endDate) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("dbId", dbId);
		parameter.put("quotaId", quotaId);
		parameter.put("pageSize", pageSize);
		parameter.put("startDate", startDate);
		parameter.put("endDate", endDate);
		
		return this.getSqlSessionTemplate().selectList(context_space+".list_complex_quota_report_data", parameter);
	}

	@Override
	public void closeNoThresholdTasks() {
		this.getSqlSessionTemplate().update(context_space+".close_no_threshold_task");
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.even.oracle.OracleQuotaCollectDayDAO#getOracleMonitorUnCompletedData()
	 */
	@Override
	public List<OracleQuotaCollectDayDomain> getOracleMonitorUnCompletedData() {
		return this.getSqlSessionTemplate().selectList(context_space+".list_oracle_monitor_uncompleted_limit_id");
	}

	@Override
	public OracleQuotaCollectDayDomain getOracleMonitorUnCompletedObj(String id) {
		return this.getSqlSessionTemplate().selectOne(context_space+".get_oracle_monitor_uncompleted_obj",id);
	}

	@Override
	public OracleQuotaCollectDayDomain getOracleMonitorDataById(String id) {
		return this.getSqlSessionTemplate().selectOne(context_space+".get_oracle_monitor_data_by_id",id);
	}

}
