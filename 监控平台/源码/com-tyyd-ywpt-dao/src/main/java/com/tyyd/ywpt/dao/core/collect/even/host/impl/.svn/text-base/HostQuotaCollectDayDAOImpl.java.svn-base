/**   
* @Title: HostQuotaCollectDayDAOImpl.java 
* @Package com.tyyd.ywpt.dao.core.collect.even.host.impl 
* @Description:  
* @author wangyu   
* @date 2014-6-25 上午10:08:20 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.even.host.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.core.collect.even.host.HostQuotaCollectDayDAO;
import com.tyyd.ywpt.dao.core.collect.even.host.dataobject.HostQuotaCollectDayDomain;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class HostQuotaCollectDayDAOImpl extends TyydBaseDAO implements
		HostQuotaCollectDayDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.core.collect.even.host.dataobject.HostQuotaCollectDayDomain";
	
	@Override
	public void addHostQuotaCollectDay(HostQuotaCollectDayDomain domain) {
		this.getSqlSessionTemplate().insert(context_space+".add_host_quota_collect_day", domain);
	}

	@Override
	public void moveHostQuotaCollectDay(String startCreatedDate,
			String endCreatedDate) {

	}

	@Override
	public List<HostQuotaCollectDayDomain> listHostQuotaCollectDay(
			String startCreatedDate, String endCreatedDate, String hostId,
			String quotaId) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("startCreatedDate", startCreatedDate);
		parameter.put("endCreatedDate", endCreatedDate);
		parameter.put("hostId", hostId);
		parameter.put("quotaId", quotaId);
		return this.getSqlSessionTemplate().selectList(context_space+".list_host_quota_collect_day", parameter);
	}

	@Override
	public HostQuotaCollectDayDomain getLastHostQuotaCollect(String hostId,
			String quotaId) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("hostId", hostId);
		parameter.put("quotaId", quotaId);
		
		return this.getSqlSessionTemplate().selectOne(context_space+".get_last_host_quota_collect", parameter);
	}

	@Override
	public List<HostQuotaCollectDayDomain> listUnCompletedQuotaCollectData() {
		return this.getSqlSessionTemplate().selectList(context_space+".list_uncompleted_quota_collect_data");
	}

	@Override
	public void closeUnCompletedQuotaCollectDataById(String id) {
		this.getSqlSessionTemplate().update(context_space +".close_uncompleted_quota_collect_data_by_id", id);
		
	}

	@Override
	public void closeNormalUnCompletedQuotaCollectData() {
		this.getSqlSessionTemplate().update(context_space+".close_normal_uncompleted_task");
	}

	@Override
	public List<HostQuotaCollectDayDomain> listReportData(String hostId,String quotaId,
			Integer pageSize,String startDate,String endDate) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("hostId", hostId);
		parameter.put("quotaId", quotaId);
		parameter.put("pageSize", pageSize);
		parameter.put("startDate", startDate);
		parameter.put("endDate", endDate);
		
		return this.getSqlSessionTemplate().selectList(context_space+".list_report_data", parameter);
	}

	@Override
	public void closeNoThresholdTasks() {
		 this.getSqlSessionTemplate().update(context_space+".close_no_threshold_task");
	}

	@Override
	public List<HostQuotaCollectDayDomain> getHostMonitorUnCompletedData() {
		return this.getSqlSessionTemplate().selectList(context_space+".list_host_monitor_uncompleted_limit_id");
	}

	@Override
	public HostQuotaCollectDayDomain getHostMonitorUnCompletedObj(String id) {
		return this.getSqlSessionTemplate().selectOne(context_space+".get_host_monitor_uncompleted_obj",id);
	}

}
