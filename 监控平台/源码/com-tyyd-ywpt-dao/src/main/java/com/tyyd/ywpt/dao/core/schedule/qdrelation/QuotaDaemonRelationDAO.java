/**   
* @Title: QuotaDaemonRelationDAO.java 
* @Package com.tyyd.ywpt.dao.core.schedule.qdrelation 
* @Description:  
* @author wangyu   
* @date 2014-6-24 下午5:09:38 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.schedule.qdrelation;

import java.util.List;

import com.tyyd.ywpt.dao.base.dataobject.PageQuery;
import com.tyyd.ywpt.dao.core.schedule.qdrelation.dataobject.QuotaDaemonRelationDomain;

/**
 * @author wangyu
 *
 */
public interface QuotaDaemonRelationDAO {

	
	/**
	 * 添加一个指标与进程的关系
	 * @param domain
	 */
	public void addQuotaDaemonRelation(QuotaDaemonRelationDomain domain);
	
	
	/**
	 * 删除关系
	 * @param domain
	 */
	public void deleteQuotaDaemonRelation(QuotaDaemonRelationDomain domain);
	
	/**
	 * 关系维护
	 * @param pageQuery
	 * @return
	 */
	public PageQuery<List<QuotaDaemonRelationDomain>> listQuotaDaemonRelation(PageQuery<List<QuotaDaemonRelationDomain>> pageQuery);
}
