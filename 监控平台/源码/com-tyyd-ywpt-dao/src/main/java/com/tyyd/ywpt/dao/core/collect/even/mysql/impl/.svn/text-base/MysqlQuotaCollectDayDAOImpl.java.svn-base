/**   
* @Title: MysqlQuotaCollectDayDAOImpl.java 
* @Package com.tyyd.ywpt.dao.core.collect.even.mysql.impl 
* @Description:  
* @author wangyu   
* @date 2014-6-25 上午10:57:58 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.even.mysql.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.core.collect.even.mysql.MysqlQuotaCollectDayDAO;
import com.tyyd.ywpt.dao.core.collect.even.mysql.dataobject.MysqlQuotaCollectDayDomain;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class MysqlQuotaCollectDayDAOImpl extends TyydBaseDAO implements
		MysqlQuotaCollectDayDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.core.collect.even.mysql.dataobject.MysqlQuotaCollectDayDomain";
	
	
	@Override
	public void addMysqlQuotaCollectDay(MysqlQuotaCollectDayDomain domain) {
		this.getSqlSessionTemplate().insert(context_space+".add_mysql_quota_collect_day", domain);
	}

	@Override
	public void moveMysqlQuotaCollectDay(String startDate, String endDate) {
		
	}

	@Override
	public List<MysqlQuotaCollectDayDomain> listMysqlQuotaCollectDay(
			String startDate, String endDate, String hostId, String dbId,
			String quotaId) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("startCreatedDate", startDate);
		parameter.put("endCreatedDate", endDate);
		parameter.put("hostId", hostId);
		parameter.put("quotaId", quotaId);
		parameter.put("dbId", dbId);
		return this.getSqlSessionTemplate().selectList(context_space+".list_mysql_quota_collect_day", parameter);
	}

	@Override
	public MysqlQuotaCollectDayDomain getLastMysqlQuotaCollectDay(String hostId,
			String dbId, String quotaId) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("hostId", hostId);
		parameter.put("quotaId", quotaId);
		parameter.put("dbId", dbId);
		return this.getSqlSessionTemplate().selectOne(context_space+".get_last_mysql_quota_collect", parameter);
	}

	@Override
	public List<MysqlQuotaCollectDayDomain> listUnCompletedMySQLQuotaCollectData() {
		return this.getSqlSessionTemplate().selectList(context_space + ".list_uncompleted_mysql_quota_collect_data");
	}

	@Override
	public void closeUnCompletedTask(String id) {
		this.getSqlSessionTemplate().update(context_space +".close_uncompleted_quota_mysql_collect_data_by_id", id);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.even.mysql.MysqlQuotaCollectDayDAO#closeNormalUnCompletedQuotaCollectData()
	 */
	@Override
	public void closeNormalUnCompletedQuotaCollectData() {
		this.getSqlSessionTemplate().update(context_space+".close_normal_uncompleted_task");
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.even.mysql.MysqlQuotaCollectDayDAO#listReportData(java.lang.String, java.lang.Integer)
	 */
	@Override
	public List<MysqlQuotaCollectDayDomain> listReportData(String dbId,String quotaId,
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
	 * @see com.tyyd.ywpt.dao.core.collect.even.mysql.MysqlQuotaCollectDayDAO#listComplexQuotaReportData(java.lang.String, java.lang.String[], java.lang.Integer)
	 */
	@Override
	public List<MysqlQuotaCollectDayDomain> listComplexQuotaReportData(
			String dbId, String[] quotaId, Integer pageSize,String startDate,String endDate) {
		
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("dbId", dbId);
		parameter.put("quotaId", quotaId);
		parameter.put("pageSize", pageSize);
		parameter.put("startDate", startDate);
		parameter.put("endDate", endDate);
		
		return this.getSqlSessionTemplate().selectList(context_space+".list_complex_quota_report_data", parameter);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.even.mysql.MysqlQuotaCollectDayDAO#listHitsQuotaData(java.lang.String, java.lang.Integer)
	 */
	@Override
	public List<MysqlQuotaCollectDayDomain> listHitsQuotaData(String dbId,
			Integer pageSize,String startDate,String endDate) {
		
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("dbId", dbId);
		parameter.put("pageSize", pageSize);
		parameter.put("startDate", startDate);
		parameter.put("endDate", endDate);
		
		return this.getSqlSessionTemplate().selectList(context_space+".list_hits_quota_data", parameter);
	}

	@Override
	public void closeNoThresholdTasks() {
		this.getSqlSessionTemplate().update(context_space+".close_no_threshold_task");
	}

	@Override
	public Float getNoCalQuotaValue(String dbId, String quotaId) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("dbId", dbId);
		parameter.put("quotaId", quotaId);
		
		return (Float)this.getSqlSessionTemplate().selectOne(context_space+".get_no_cal_quota_value", parameter);
	}

	@Override
	public Float getNeedCalQuotaValue(String dbId, String quotaId) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("dbId", dbId);
		parameter.put("quotaId", quotaId);
		
		return (Float)this.getSqlSessionTemplate().selectOne(context_space+".get_need_cal_quota_value", parameter);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.even.mysql.MysqlQuotaCollectDayDAO#getMySQLMonitorUnCompletedData()
	 */
	@Override
	public List<MysqlQuotaCollectDayDomain> getMySQLMonitorUnCompletedData() {
		return this.getSqlSessionTemplate().selectList(context_space+".list_mysql_monitor_uncompleted_limit_id");
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.even.mysql.MysqlQuotaCollectDayDAO#getMySQLMonitorUnCompletedObj(java.lang.String)
	 */
	@Override
	public MysqlQuotaCollectDayDomain getMySQLMonitorUnCompletedObj(String id) {
		return this.getSqlSessionTemplate().selectOne(context_space+".get_mysql_monitor_uncompleted_obj", id);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.even.mysql.MysqlQuotaCollectDayDAO#getMysqlMonitorDataById(java.lang.String)
	 */
	@Override
	public MysqlQuotaCollectDayDomain getMysqlMonitorDataById(String id) {
		return this.getSqlSessionTemplate().selectOne(context_space+".get_mysql_monitor_data_by_id",id);
	}

}
