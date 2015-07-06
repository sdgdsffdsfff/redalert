/**   
* @Title: GeneralQuotaTogetherDAOImpl.java 
* @Package com.tyyd.ywpt.dao.core.collect.together.general.impl 
* @Description:  
* @author wangyu   
* @date 2014-9-25 上午11:28:59 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.together.general.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.core.collect.together.general.GeneralQuotaTogetherDAO;
import com.tyyd.ywpt.dao.core.collect.together.general.dataobject.GeneralQuotaTogetherDomain;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class GeneralQuotaTogetherDAOImpl extends TyydBaseDAO implements GeneralQuotaTogetherDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.core.collect.together.general.dataobject.GeneralQuotaTogetherDomain";
	
	@Override
	public List<GeneralQuotaTogetherDomain> listReportData(String hostId,String dbId,
			String quotaId, Integer pageSize,String tabName) {
		
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("hostId", hostId);
		parameter.put("dbId", dbId);
		parameter.put("quotaId", quotaId);
		parameter.put("pageSize", pageSize);
		parameter.put("quota_tab", tabName);
		
		return this.getSqlSessionTemplate().selectList(context_space+".list_report_data", parameter);
	}

	@Override
	public List<GeneralQuotaTogetherDomain> listComplexQuotaReportData(
			String hostId, String dbId, String[] quotaId, Integer pageSize,String tabName) {
		
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("hostId", hostId);
		parameter.put("dbId", dbId);
		parameter.put("quotaId", quotaId);
		parameter.put("pageSize", pageSize);
		parameter.put("quota_tab", tabName);
		return this.getSqlSessionTemplate().selectList(context_space+".list_hits_quota_data", parameter);
	}

}
