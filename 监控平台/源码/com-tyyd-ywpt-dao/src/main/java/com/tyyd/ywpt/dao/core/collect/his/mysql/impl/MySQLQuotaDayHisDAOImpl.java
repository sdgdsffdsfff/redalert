/**   
* @Title: MySQLQuotaDayHisDAOImpl.java 
* @Package com.tyyd.ywpt.dao.core.collect.his.mysql.impl 
* @Description:  
* @author wangyu   
* @date 2014-9-1 上午11:22:36 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.his.mysql.impl;

import java.util.HashMap;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.core.collect.his.mysql.MySQLQuotaDayHisDAO;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class MySQLQuotaDayHisDAOImpl extends TyydBaseDAO implements MySQLQuotaDayHisDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.core.collect.his.mysql.dataobject.MysqlQuotaDayHisDomain";
	private final static String mysql_quota_collect_day_space = "com.tyyd.ywpt.dao.core.collect.even.mysql.dataobject.MysqlQuotaCollectDayDomain";
	
	
	@Override
	public void moveHisData(String gmtCreatedDate) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("gmtCreatedDate", gmtCreatedDate);
		this.getSqlSessionTemplate().insert(context_space+".move_his_data", parameterMap);
		this.getSqlSessionTemplate().insert(mysql_quota_collect_day_space+".del_his_data", parameterMap);
	}

}
