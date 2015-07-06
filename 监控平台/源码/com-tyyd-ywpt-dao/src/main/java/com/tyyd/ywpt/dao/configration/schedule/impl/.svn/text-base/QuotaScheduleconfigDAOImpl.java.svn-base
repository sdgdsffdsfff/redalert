/**   
 * @Title: QuotaScheduleconfigDAOImpl.java 
 * @Package com.tyyd.ywpt.dao.configration.schedule.impl 
 * @Description:  
 * @author wangyu   
 * @date 2014-6-22 上午11:23:15 
 * @CopyRight 天翼阅读
 * @version V1.0   
 */
package com.tyyd.ywpt.dao.configration.schedule.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.configration.schedule.QuotaMonitorConfigDAO;
import com.tyyd.ywpt.dao.configration.schedule.dataobject.QuotaScheduleconfigDomain;

/**
 * @author wangyu
 * 
 */
@SuppressWarnings("rawtypes")
public class QuotaScheduleconfigDAOImpl extends TyydBaseDAO implements
		QuotaMonitorConfigDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.configration.schedule.dataobject.QuotaScheduleconfigDomain";

	@Override
	public void addQuotaScheduleconfig(QuotaScheduleconfigDomain domain) {
		this.getSqlSessionTemplate().insert(
				context_space + ".add_quota_schedule_config", domain);
	}

	@Override
	public void deleteQuotaScheduleconfig(String id) {
		this.getSqlSessionTemplate().delete(
				context_space + ".delete_quota_schedule_config", id);
	}


	@Override
	public List<QuotaScheduleconfigDomain> listQuotaScheduleconfigByMonitorId(
			String monitorId, Integer configType) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("configType", configType);
		parameterMap.put("monitorId", monitorId);
		return this.getSqlSessionTemplate().selectList(
				context_space + ".list_quota_schedule_config_by_monitor_id",
				parameterMap);
	}



}
