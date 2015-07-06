/**   
* @Title: DbTableSubLogDAOImpl.java 
* @Package com.tyyd.ywpt.dao.fbfk.impl 
* @Description:  
* @author wangyu   
* @date 2015-4-15 下午4:06:26 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.fbfk.impl;

import java.util.List;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.fbfk.DbTableSubLogDAO;
import com.tyyd.ywpt.dao.fbfk.dataobject.DbTableSubLogDomain;

/**
 * @author wangyu
 *
 */
public class DbTableSubLogDAOImpl extends TyydBaseDAO implements
		DbTableSubLogDAO {

	private static final String context = "com.tyyd.ywpt.dao.fbfk.dataobject.DbTableSubLogDomain";
	
	
	@Override
	public void addDbTableSubLog(DbTableSubLogDomain log) {
		this.getSqlSessionTemplate().insert(context+".add_db_table_sub_log", log);
	}

	@Override
	public List<DbTableSubLogDomain> listDbTableSubLog(Long taskId) {
		return this.getSqlSessionTemplate().selectList(context+".list_db_table_sub_log",taskId);
	}


}
