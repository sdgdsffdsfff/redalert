/**   
* @Title: OracleQuotaTogetherDAOImpl.java 
* @Package com.tyyd.ywpt.dao.core.collect.together.oracle.impl 
* @Description:  
* @author wangyu   
* @date 2014-9-23 上午10:02:11 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.together.oracle.impl;

import java.util.HashMap;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.core.collect.together.oracle.OracleQuotaTogetherDAO;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class OracleQuotaTogetherDAOImpl extends TyydBaseDAO implements OracleQuotaTogetherDAO {

	private final static String hour_context_space = "com.tyyd.ywpt.dao.core.collect.together.oracle.dataobject.OracleQuotaTogetherHourDomain";
	private final static String day_context_space = "com.tyyd.ywpt.dao.core.collect.together.oracle.dataobject.OracleQuotaTogetherDayDomain";
	private final static String week_context_space = "com.tyyd.ywpt.dao.core.collect.together.oracle.dataobject.OracleQuotaTogetherWeekDomain";
	private final static String month_context_space = "com.tyyd.ywpt.dao.core.collect.together.oracle.dataobject.OracleQuotaTogetherMonthDomain";
	
	
	@Override
	public void oracleQuotaHoursTogether(String startDate, String endDate) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("startDate", startDate);
		parameterMap.put("endDate", endDate);
		
		this.getSqlSessionTemplate().insert(hour_context_space +".add_oracle_quota_together_hours", parameterMap);
	}

	@Override
	public void oracleQuotaDayTogether(String startDate, String endDate) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("startDate", startDate);
		parameterMap.put("endDate", endDate);
		
		this.getSqlSessionTemplate().insert(day_context_space +".add_oracle_quota_together_day", parameterMap);
	}

	@Override
	public void oracleQuotaWeekTogether(String startDate, String endDate) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("startDate", startDate);
		parameterMap.put("endDate", endDate);
		
		this.getSqlSessionTemplate().insert(week_context_space +".add_oracle_quota_together_week", parameterMap);
	}

	@Override
	public void oracleQuotaMonthTogether(String startDate, String endDate) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("startDate", startDate);
		parameterMap.put("endDate", endDate);
		
		this.getSqlSessionTemplate().insert(month_context_space +".add_oracle_quota_together_month", parameterMap);
	}

	@Override
	public void clearOracleQuotaHoursTogether(String fromDate) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("fromDate", fromDate);
		
		this.getSqlSessionTemplate().delete(hour_context_space+".clear_dirty_data",parameterMap);
	}

	@Override
	public void clearOracleQuotaDayTogether(String fromDate) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("fromDate", fromDate);
		
		this.getSqlSessionTemplate().delete(day_context_space+".clear_dirty_data",parameterMap);
	}

	@Override
	public void clearOracleQuotaWeekTogether(String fromDate) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("fromDate", fromDate);
		
		this.getSqlSessionTemplate().delete(week_context_space+".clear_dirty_data",parameterMap);
	}

	@Override
	public void clearOracleQuotaMonthTogether(String fromDate) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("fromDate", fromDate);
		
		this.getSqlSessionTemplate().delete(month_context_space+".clear_dirty_data",parameterMap);
	}

}
