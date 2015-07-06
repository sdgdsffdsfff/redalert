/**   
* @Title: QuotaModelDAO.java 
* @Package com.tyyd.ywpt.dao.core.quota 
* @Description:  
* @author wangyu   
* @date 2014-6-23 上午9:33:18 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.quota;

import java.util.List;

import com.tyyd.ywpt.dao.base.dataobject.PageQuery;
import com.tyyd.ywpt.dao.core.quota.dataobject.QuotaModelDomain;

/**
 * @author wangyu
 *
 */
public interface QuotaModelDAO {

	
	/**
	 * 添加一个指标定义
	 * @param domain
	 */
	public void addQuotaModel(QuotaModelDomain domain);
	
	
	/**
	 * 删除指标
	 * @param id
	 */
	public void deleteQuotaModel(String id);
	
	
	/**
	 * 更新指标
	 * @param domain
	 */
	public void updateQuotaModel(QuotaModelDomain domain);
	
	
	/**
	 * 查询指标详情
	 * @param id
	 * @return
	 */
	public QuotaModelDomain getQuotaModelById(String id);
	
	
	/**
	 * 指标列表
	 * @param pageQuery
	 * @param sysCategory
	 * @param quotaCategory
	 * @return
	 */
	public PageQuery<List<QuotaModelDomain>> listQuotaModel(PageQuery<List<QuotaModelDomain>> pageQuery,Integer sysCategory,Integer quotaCategory);
	
	
	/**
	 * 需要发短信的指标列表
	 * @return
	 */
	public List<QuotaModelDomain> listPhoneSentQuota();
	
	

	/**
	 * 某类指标集合
	 * @param configType
	 * @param quotaCategory
	 * @return
	 */
	public List<QuotaModelDomain> listCategoryQuotas(Integer configType,Integer quotaCategory);
}
