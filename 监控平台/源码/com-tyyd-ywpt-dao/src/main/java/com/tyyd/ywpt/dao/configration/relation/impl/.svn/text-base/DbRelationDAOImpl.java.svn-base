/**   
* @Title: DbRelationDAOImpl.java 
* @Package com.tyyd.ywpt.dao.configration.relation.impl 
* @Description:  
* @author wangyu   
* @date 2014-6-21 下午7:14:29 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.configration.relation.impl;

import java.util.List;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.configration.relation.DbRelationDAO;
import com.tyyd.ywpt.dao.configration.relation.dataobject.DbRelationDomain;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class DbRelationDAOImpl extends TyydBaseDAO implements DbRelationDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.configration.relation.dataobject.DbRelationDomain";
	
	@Override
	public void addDataBaseRelation(DbRelationDomain domain) {
		this.getSqlSessionTemplate().insert(context_space +".add_data_base_relation",domain);
	}

	@Override
	public List<DbRelationDomain> listDbRelation(String batchId) {
		return this.getSqlSessionTemplate().selectList(context_space+".list_db_relation", batchId);
	}

	@Override
	public void deleteDataBaseRelation(String batchId) {
		this.getSqlSessionTemplate().delete(context_space +".delete_data_base_relation",batchId);
	}

}
