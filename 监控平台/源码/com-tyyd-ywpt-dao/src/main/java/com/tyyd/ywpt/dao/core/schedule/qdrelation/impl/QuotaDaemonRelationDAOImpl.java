/**   
* @Title: QuotaDaemonRelationDAOImpl.java 
* @Package com.tyyd.ywpt.dao.core.schedule.qdrelation.impl 
* @Description:  
* @author wangyu   
* @date 2014-6-24 下午5:13:21 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.schedule.qdrelation.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.base.dataobject.PageQuery;
import com.tyyd.ywpt.dao.core.schedule.qdrelation.QuotaDaemonRelationDAO;
import com.tyyd.ywpt.dao.core.schedule.qdrelation.dataobject.QuotaDaemonRelationDomain;

/**
 * @author wangyu
 *
 */
public class QuotaDaemonRelationDAOImpl extends TyydBaseDAO<List<QuotaDaemonRelationDomain>> implements
		QuotaDaemonRelationDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.core.schedule.qdrelation.dataobject.QuotaDaemonRelationDomain";
	
	@Override
	public void addQuotaDaemonRelation(QuotaDaemonRelationDomain domain) {
		this.getSqlSessionTemplate().insert(context_space+".add_quota_daemon_relation", domain);
	}

	@Override
	public void deleteQuotaDaemonRelation(QuotaDaemonRelationDomain domain) {
		this.getSqlSessionTemplate().delete(context_space+".delete_quota_daemon_relation", domain);
	}

	@Override
	public PageQuery<List<QuotaDaemonRelationDomain>> listQuotaDaemonRelation(
			PageQuery<List<QuotaDaemonRelationDomain>> pageQuery) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		return this.selectPages(context_space+".list_quota_daemon_relation", pageQuery, parameterMap);
	}

}
