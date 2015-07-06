/**   
* @Title: MySQLQuotaTogetherDAOImpl.java 
* @Package com.tyyd.ywpt.dao.core.collect.together.mysql.impl 
* @Description:  
* @author wangyu   
* @date 2014-9-23 上午9:45:19 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.together.mysql.impl;

import java.util.HashMap;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.core.collect.together.mysql.MySQLQuotaTogetherDAO;

/**
 * @author wangyu
 */
@SuppressWarnings("rawtypes")
public class MySQLQuotaTogetherDAOImpl extends TyydBaseDAO implements MySQLQuotaTogetherDAO {

	
	private final static String hour_context_space = "com.tyyd.ywpt.dao.core.collect.together.mysql.dataobject.MysqlQuotaTogetherHourDomain";
	private final static String day_context_space = "com.tyyd.ywpt.dao.core.collect.together.mysql.dataobject.MysqlQuotaTogetherDayDomain";
	private final static String week_context_space = "com.tyyd.ywpt.dao.core.collect.together.mysql.dataobject.MysqlQuotaTogetherWeekDomain";
	private final static String month_context_space = "com.tyyd.ywpt.dao.core.collect.together.mysql.dataobject.MysqlQuotaTogetherMonthDomain";
	
	
	
	@Override
	public void mysqlQuotaHoursTogether(String startDate, String endDate) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("startDate", startDate);
		parameterMap.put("endDate", endDate);
		
		this.getSqlSessionTemplate().insert(hour_context_space +".add_mysql_quota_together_hours", parameterMap);
	}

	@Override
	public void mysqlQuotaDayTogether(String startDate, String endDate) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("startDate", startDate);
		parameterMap.put("endDate", endDate);
		
		this.getSqlSessionTemplate().insert(day_context_space +".add_mysql_quota_together_day", parameterMap);
	}

	@Override
	public void mysqlQuotaWeekTogether(String startDate, String endDate) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("startDate", startDate);
		parameterMap.put("endDate", endDate);
		
		this.getSqlSessionTemplate().insert(week_context_space +".add_mysql_quota_together_week", parameterMap);
	}

	@Override
	public void mysqlQuotaMonthTogether(String startDate, String endDate) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("startDate", startDate);
		parameterMap.put("endDate", endDate);
		
		this.getSqlSessionTemplate().insert(month_context_space +".add_mysql_quota_together_month", parameterMap);
	}

	@Override
	public void clearMySQLQuotaHoursTogether(String fromDate) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("fromDate", fromDate);
		
		this.getSqlSessionTemplate().delete(hour_context_space+".clear_dirty_data",parameterMap);
	}

	@Override
	public void clearMySQLQuotaDayTogether(String fromDate) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("fromDate", fromDate);
		
		this.getSqlSessionTemplate().delete(day_context_space+".clear_dirty_data",parameterMap);
	}

	@Override
	public void clearMySQLQuotaWeekTogether(String fromDate) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("fromDate", fromDate);
		
		this.getSqlSessionTemplate().delete(week_context_space+".clear_dirty_data",parameterMap);
	}

	@Override
	public void clearMySQLQuotaMonthTogether(String fromDate) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("fromDate", fromDate);
		
		this.getSqlSessionTemplate().delete(month_context_space+".clear_dirty_data",parameterMap);
	}

}
