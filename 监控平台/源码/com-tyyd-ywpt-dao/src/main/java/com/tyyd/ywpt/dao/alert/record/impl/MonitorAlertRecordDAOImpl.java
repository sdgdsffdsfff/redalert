/**   
* @Title: MonitorAlertRecordDAOImpl.java 
* @Package com.tyyd.ywpt.dao.alert.record.impl 
* @Description:  
* @author wangyu   
* @date 2014-10-16 上午10:57:51 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.alert.record.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.alert.record.MonitorAlertRecordDAO;
import com.tyyd.ywpt.dao.alert.record.dataobject.MoitorAlertRecordDomain;
import com.tyyd.ywpt.dao.base.dataobject.PageQuery;

/**
 * @author wangyu
 *
 */
public class MonitorAlertRecordDAOImpl extends TyydBaseDAO<List<MoitorAlertRecordDomain>> implements MonitorAlertRecordDAO {

	public final static String context_space = "com.tyyd.ywpt.dao.alert.record.dataobject.MoitorAlertRecordDomain";
	
	
	@Override
	public void addMonitorAlertRecord(MoitorAlertRecordDomain domain) {
		this.getSqlSessionTemplate().insert(context_space +".add_moitor_alert_record", domain);
	}

	@Override
	public void deleteMonitorAlertRecord(Long id) {
		this.getSqlSessionTemplate().delete(context_space +".del_monitor_alert_record", id);
	}

	@Override
	public List<MoitorAlertRecordDomain> listMonitorAlertRecord(
			String monitorId, Integer configType,Integer noticeLevel) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("monitorId", monitorId);
		parameterMap.put("sysType", configType);
		parameterMap.put("noticeLevel", noticeLevel);
		
		return this.getSqlSessionTemplate().selectList(context_space+".list_monitor_alert_record",parameterMap);
	}

	@Override
	public boolean isExistMonitorAlertRecord(MoitorAlertRecordDomain domain) {
		Integer size = (Integer)this.getSqlSessionTemplate().selectOne(context_space+".is_exist_monitor_alert_record", domain);
		if(size > 0){
			return true;
		}
		return false;
	}

	@Override
	public List<MoitorAlertRecordDomain> listMonitorPandectInfo(
			 Integer configType) {
		List<MoitorAlertRecordDomain> result = new ArrayList<MoitorAlertRecordDomain>();
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("dbType", configType);

		//主机
		if(configType.intValue() == 1){
			result = this.getSqlSessionTemplate().selectList(context_space +".list_pandect_host_monitor_alert");
		}else{
			result = this.getSqlSessionTemplate().selectList(context_space +".list_pandect_db_monitor_alert",parameterMap);
		}
		return result;
	}

	@Override
	public PageQuery<List<MoitorAlertRecordDomain>> listHostPageQuery(
			PageQuery<List<MoitorAlertRecordDomain>> pageQuery, String hostId,String startDate,String endDate) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("hostId", hostId);
		parameterMap.put("startDate", startDate);
		parameterMap.put("endDate", endDate);
		
		return this.selectPages(context_space+".list_host_pagequery", pageQuery, parameterMap);
	}

	@Override
	public PageQuery<List<MoitorAlertRecordDomain>> listDbPageQuery(
			PageQuery<List<MoitorAlertRecordDomain>> pageQuery, String dbId,
			Integer dbType,String startDate,String endDate) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("dbId", dbId);
		parameterMap.put("dbType", dbType);
		parameterMap.put("startDate", startDate);
		parameterMap.put("endDate", endDate);
		
		return this.selectPages(context_space+".list_db_pageQuery", pageQuery, parameterMap);
	}

	@Override
	public PageQuery<List<MoitorAlertRecordDomain>> listAllPageQuery(
			PageQuery<List<MoitorAlertRecordDomain>> pageQuery, String queryDay) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("lastQueryDay", queryDay);
		return this.selectPages(context_space+".list_monitor_pageQuery", pageQuery, parameterMap);
	}

	@Override
	public void updateLastTime(MoitorAlertRecordDomain domain) {
		this.getSqlSessionTemplate().update(context_space+".update_last_modifytime", domain);
	}

}
