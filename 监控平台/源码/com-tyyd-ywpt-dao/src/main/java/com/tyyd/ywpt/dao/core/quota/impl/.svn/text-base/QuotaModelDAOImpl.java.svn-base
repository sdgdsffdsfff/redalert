/**   
* @Title: QuotaModelDAOImpl.java 
* @Package com.tyyd.ywpt.dao.core.quota.impl 
* @Description:  
* @author wangyu   
* @date 2014-6-23 上午9:47:31 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.quota.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.base.dataobject.PageQuery;
import com.tyyd.ywpt.dao.core.quota.QuotaModelDAO;
import com.tyyd.ywpt.dao.core.quota.dataobject.QuotaModelDomain;

/**
 * @author wangyu
 *
 */
public class QuotaModelDAOImpl extends TyydBaseDAO<List<QuotaModelDomain>> implements QuotaModelDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.core.quota.dataobject.QuotaModelDomain";
	
	@Override
	public void addQuotaModel(QuotaModelDomain domain) {
		this.getSqlSessionTemplate().insert(context_space +".add_quota_model", domain);
	}

	@Override
	public void deleteQuotaModel(String id) {
		this.getSqlSessionTemplate().delete(context_space +".delete_quota_model", id);
	}

	@Override
	public void updateQuotaModel(QuotaModelDomain domain) {
		this.getSqlSessionTemplate().update(context_space +".update_quota_model", domain);
	}

	@Override
	public QuotaModelDomain getQuotaModelById(String id) {
		return this.getSqlSessionTemplate().selectOne(context_space +".get_quota_model_by_id", id);
	}

	@Override
	public PageQuery<List<QuotaModelDomain>> listQuotaModel(
			PageQuery<List<QuotaModelDomain>> pageQuery, Integer sysCategory,
			Integer quotaCategory) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("sysCategory", sysCategory);
		parameterMap.put("quotaCategory", quotaCategory);
		return this.selectPages(context_space +".list_quota_model", pageQuery, parameterMap);
	}

	@Override
	public List<QuotaModelDomain> listPhoneSentQuota() {
		return this.getSqlSessionTemplate().selectList(context_space + ".list_phone_sent_quota");
	}

	@Override
	public List<QuotaModelDomain> listCategoryQuotas(Integer configType,
			Integer quotaCategory) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("sysCategory", configType);
		parameterMap.put("quotaCategory", quotaCategory);
		return this.getSqlSessionTemplate().selectList(context_space +".list_category_quotas", parameterMap);
	}

}
