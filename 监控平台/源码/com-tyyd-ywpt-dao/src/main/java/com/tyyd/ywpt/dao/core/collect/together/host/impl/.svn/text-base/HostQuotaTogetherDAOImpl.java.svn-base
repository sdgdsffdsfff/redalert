/**   
* @Title: HostQuotaTogetherDAOImpl.java 
* @Package com.tyyd.ywpt.dao.core.collect.together.host.impl 
* @Description:  
* @author wangyu   
* @date 2014-9-23 上午9:31:20 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.together.host.impl;

import java.util.HashMap;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.core.collect.together.host.HostQuotaTogetherDAO;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class HostQuotaTogetherDAOImpl extends TyydBaseDAO implements HostQuotaTogetherDAO {

	private final static String hour_context_space = "com.tyyd.ywpt.dao.core.collect.together.host.dataobject.HostQuotaTogetherHourDomain";
	private final static String day_context_space = "com.tyyd.ywpt.dao.core.collect.together.host.dataobject.HostQuotaTogetherDayDomain";
	private final static String week_context_space = "com.tyyd.ywpt.dao.core.collect.together.host.dataobject.HostQuotaTogetherWeekDomain";
	private final static String month_context_space = "com.tyyd.ywpt.dao.core.collect.together.host.dataobject.HostQuotaTogetherMonthDomain";
	
	
	
	@Override
	public void hostQuotaHoursTogether(String startDate, String endDate) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("startDate", startDate);
		parameterMap.put("endDate", endDate);
		
		this.getSqlSessionTemplate().insert(hour_context_space +".add_host_quota_together_hours", parameterMap);
	}

	@Override
	public void hostQuotaDayTogether(String startDate, String endDate) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("startDate", startDate);
		parameterMap.put("endDate", endDate);
		
		this.getSqlSessionTemplate().insert(day_context_space +".add_host_quota_together_day", parameterMap);
	}

	@Override
	public void hostQuotaWeekTogether(String startDate, String endDate) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("startDate", startDate);
		parameterMap.put("endDate", endDate);
		
		this.getSqlSessionTemplate().insert(week_context_space +".add_host_quota_together_week", parameterMap);
	}

	@Override
	public void hostQuotaMonthTogether(String startDate, String endDate) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("startDate", startDate);
		parameterMap.put("endDate", endDate);
		
		this.getSqlSessionTemplate().insert(month_context_space +".add_host_quota_together_month", parameterMap);
	}

	@Override
	public void clearHostQuotaHoursTogether(String fromDate) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("fromDate", fromDate);
		
		this.getSqlSessionTemplate().delete(hour_context_space+".clear_dirty_data",parameterMap);
	}

	@Override
	public void clearHostQuotaDayTogether(String fromDate) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("fromDate", fromDate);
		
		this.getSqlSessionTemplate().delete(day_context_space+".clear_dirty_data",parameterMap);
	}

	@Override
	public void clearHostQuotaWeekTogether(String fromDate) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("fromDate", fromDate);
		
		this.getSqlSessionTemplate().delete(week_context_space+".clear_dirty_data",parameterMap);
	}

	@Override
	public void clearHostQuotaMonthTogether(String fromDate) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("fromDate", fromDate);
		
		this.getSqlSessionTemplate().delete(month_context_space+".clear_dirty_data",parameterMap);
	}

}
