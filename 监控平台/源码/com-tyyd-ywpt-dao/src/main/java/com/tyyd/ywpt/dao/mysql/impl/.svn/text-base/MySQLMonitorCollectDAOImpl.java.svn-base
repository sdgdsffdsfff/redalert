/**   
* @Title: MySQLMonitorCollectDAOImpl.java 
* @Package com.tyyd.ywpt.dao.mysql.impl 
* @Description:  
* @author wangyu   
* @date 2014-7-3 上午9:31:39 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.mysql.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.mysql.MySQLMonitorCollectDAO;
import com.tyyd.ywpt.dao.mysql.dataobject.MySQLStatDomain;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class MySQLMonitorCollectDAOImpl extends TyydBaseDAO implements MySQLMonitorCollectDAO{

	private static final String SPACE = "com.tyyd.ywpt.dao.mysql.dataobject.MySQLStatDomain";
	
	@Override
	public List<MySQLStatDomain> listMySQLStats(String[] variables) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("variables", variables);
		return this.getSqlSessionTemplate().selectList(SPACE + ".get_myql_stats_by_dbid", parameterMap);
	}

	
	@Override
	public String getCurrentDate() {
		return this.getSqlSessionTemplate().selectOne(SPACE + ".get_current_date");
	}

	
	
}
