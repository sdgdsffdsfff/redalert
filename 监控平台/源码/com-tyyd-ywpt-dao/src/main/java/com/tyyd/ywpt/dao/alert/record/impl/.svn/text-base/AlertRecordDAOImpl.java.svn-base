/**   
* @Title: AlertRecordDAOImpl.java 
* @Package com.tyyd.ywpt.dao.alert.record.impl 
* @Description:  
* @author wangyu   
* @date 2014-6-19 下午1:06:42 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.alert.record.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.alert.record.AlertRecordDAO;
import com.tyyd.ywpt.dao.alert.record.dataobject.AlertRecordDomain;
import com.tyyd.ywpt.dao.base.dataobject.PageQuery;

/**
 * @author wangyu
 *
 */
public class AlertRecordDAOImpl extends TyydBaseDAO<List<AlertRecordDomain>> implements
		AlertRecordDAO {

	public final static String context_space = "com.tyyd.ywpt.dao.alert.record.dataobject.AlertRecordDomain";
	
	
	@Override
	public void addAlertRecord(AlertRecordDomain domain) {
		this.getSqlSessionTemplate().insert(context_space +".add_alert_record", domain);
	}

	@Override
	public void updateAlertRecordTaskStatus(Long id) {
		this.getSqlSessionTemplate().update(context_space +".update_alert_record", id);
	}

	@Override
	public PageQuery<List<AlertRecordDomain>> listAlertRecord(
			PageQuery<List<AlertRecordDomain>> pageQuery, String startQueryDate,String endQueryDate,String hostId,String dbId,Integer sysType,Integer isCompleted) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("startQueryDate", startQueryDate);
		parameterMap.put("endQueryDate", endQueryDate);
		parameterMap.put("hostId", hostId);
		parameterMap.put("dbId", dbId);
		parameterMap.put("sysType", sysType);
		parameterMap.put("isCompleted", isCompleted);
		return this.selectPages(context_space +".list_alert_record", pageQuery, parameterMap);
	}

	@Override
	public AlertRecordDomain getAlertRecordById(String id) {
		return this.getSqlSessionTemplate().selectOne(context_space +".get_alert_record_by_id", id);
	}

	@Override
	public List<AlertRecordDomain> getUnCompleteTaskLimit(Integer limitSize) {
		return this.getSqlSessionTemplate().selectList(context_space +".list_no_complete_alert", limitSize);
	}

	@Override
	public List<AlertRecordDomain> listAlertRecordByHost(String hostId,
			Integer limitSize,String startDate,String endDate) {
		
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("hostId", hostId);
		parameterMap.put("limitSize", limitSize);
		parameterMap.put("startDate", startDate);
		parameterMap.put("endDate", endDate);
		
		return this.getSqlSessionTemplate().selectList(context_space +".list_alert_record_by_host", parameterMap);
	}

	@Override
	public List<AlertRecordDomain> listAlertRecordByDb(String dbId,
			Integer dbType, Integer limitSize,String startDate,String endDate) {
		
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("dbId", dbId);
		parameterMap.put("dbType", dbType);
		parameterMap.put("limitSize", limitSize);
		parameterMap.put("startDate", startDate);
		parameterMap.put("endDate", endDate);
		
		return this.getSqlSessionTemplate().selectList(context_space +".list_alert_record_by_db", parameterMap);
	}

	@Override
	public List<AlertRecordDomain> listAlertRecordByAllLooking(
			String startDate, Integer limitSize) {
		
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("startDate", startDate);
		parameterMap.put("limitSize", limitSize);
		
		return this.getSqlSessionTemplate().selectList(context_space +".list_alert_record_by_all_looking", parameterMap);
		
	}

	@Override
	public String getLimitGmtCreatedDate(Integer limitSize) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("limitSize", limitSize);
		return this.getSqlSessionTemplate().selectOne(context_space +".get_limit_gmtdate", parameterMap);
	}

}
