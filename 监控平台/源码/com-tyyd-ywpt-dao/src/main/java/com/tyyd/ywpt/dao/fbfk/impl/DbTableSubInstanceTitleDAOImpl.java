/**   
* @Title: DbTableSubInstanceTitleDAOImpl.java 
* @Package com.tyyd.ywpt.dao.fbfk.impl 
* @Description:  
* @author wangyu   
* @date 2015-4-15 下午4:06:04 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.fbfk.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.fbfk.DbTableSubInstanceTitleDAO;
import com.tyyd.ywpt.dao.fbfk.dataobject.DbTableSubInstanceTitleDomain;

/**
 * @author wangyu
 *
 */
public class DbTableSubInstanceTitleDAOImpl extends TyydBaseDAO implements
		DbTableSubInstanceTitleDAO {

	private static final String context = "com.tyyd.ywpt.dao.fbfk.dataobject.DbTableSubInstanceTitleDomain";
	
	
	@Override
	public List<DbTableSubInstanceTitleDomain> listDbTableSubInstanceTitleById(
			Integer dbTableSubClassifyId) {
		return this.getSqlSessionTemplate().selectList(context+".list_db_table_sub_instance_title_by_id", dbTableSubClassifyId);
	}


	@Override
	public DbTableSubInstanceTitleDomain getDbTableSubInstanceTitleByDbId(
			Integer dbTableSubClassifyId, String dbId) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("dbTableSubClassifyId", dbTableSubClassifyId);
		parameterMap.put("dbId", dbId);
		return this.getSqlSessionTemplate().selectOne(context+".get_db_table_sub_instance_title_by_dbid",parameterMap);
	}

}
