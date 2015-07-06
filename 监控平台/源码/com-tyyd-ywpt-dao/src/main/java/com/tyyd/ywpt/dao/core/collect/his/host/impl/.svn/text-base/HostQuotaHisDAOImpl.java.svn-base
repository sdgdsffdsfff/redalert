/**   
* @Title: HostQuotaHisDAOImpl.java 
* @Package com.tyyd.ywpt.dao.core.collect.his.host.impl 
* @Description:  
* @author wangyu   
* @date 2014-9-1 上午9:48:53 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.his.host.impl;

import java.util.HashMap;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.core.collect.his.host.HostQuotaHisDAO;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class HostQuotaHisDAOImpl extends TyydBaseDAO implements HostQuotaHisDAO{

	private final static String context_space = "com.tyyd.ywpt.dao.core.collect.his.host.dataobject.HostQuotaDayHisDomain";
	private final static String host_quota_collect_day_space = "com.tyyd.ywpt.dao.core.collect.even.host.dataobject.HostQuotaCollectDayDomain";
	
	@Override
	public void moveHisData(String gmtCreatedDate) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("gmtCreatedDate", gmtCreatedDate);
		this.getSqlSessionTemplate().insert(context_space+".move_his_data", parameterMap);
		this.getSqlSessionTemplate().insert(host_quota_collect_day_space+".del_his_data", parameterMap);
	}

}
