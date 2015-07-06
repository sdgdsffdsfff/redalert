/**   
* @Title: OracleQuotaDayHisDAOImpl.java 
* @Package com.tyyd.ywpt.dao.core.collect.his.oracle.impl 
* @Description:  
* @author wangyu   
* @date 2014-9-1 下午1:58:12 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.his.oracle.impl;

import java.util.HashMap;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.core.collect.his.oracle.OracleQuotaDayHisDAO;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class OracleQuotaDayHisDAOImpl extends TyydBaseDAO implements
		OracleQuotaDayHisDAO {
	
	private final static String context_space = "com.tyyd.ywpt.dao.core.collect.his.oracle.dataobject.OracleQuotaDayHisDomain";
	private final static String oracle_quota_collect_day_space = "com.tyyd.ywpt.dao.core.collect.even.oracle.dataobject.OracleQuotaCollectDayDomain";
	
	
	@Override
	public void moveHisData(String gmtCreatedDate) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("gmtCreatedDate", gmtCreatedDate);
		this.getSqlSessionTemplate().insert(context_space+".move_his_data", parameterMap);
		this.getSqlSessionTemplate().insert(oracle_quota_collect_day_space+".del_his_data", parameterMap);
	}


}
