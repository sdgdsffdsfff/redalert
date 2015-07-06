/**   
* @Title: DbTableSubClassifyDAOImpl.java 
* @Package com.tyyd.ywpt.dao.fbfk.impl 
* @Description:  
* @author wangyu   
* @date 2015-4-15 下午3:56:28 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.fbfk.impl;

import java.util.List;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.fbfk.DbTableSubClassifyDAO;
import com.tyyd.ywpt.dao.fbfk.dataobject.DbTableSubClassifyDomain;

/**
 * @author wangyu
 *
 */
public class DbTableSubClassifyDAOImpl extends TyydBaseDAO implements
		DbTableSubClassifyDAO {

	
	private static final String context = "com.tyyd.ywpt.dao.fbfk.dataobject.DbTableSubClassifyDomain";
	
	@Override
	public List<DbTableSubClassifyDomain> listDbTableSubClassify() {
		return this.getSqlSessionTemplate().selectList(context+".list_db_table_sub_classify");
	}

	@Override
	public DbTableSubClassifyDomain getDbTableSubClassifyById(
			Integer dbTableSubClassifyId) {
		return this.getSqlSessionTemplate().selectOne(context+".get_db_table_sub_classify_by_id", dbTableSubClassifyId);
	}

}
