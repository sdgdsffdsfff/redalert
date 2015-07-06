/**   
* @Title: MySQLDbStatExtendsDAOImpl.java 
* @Package com.tyyd.ywpt.dao.configration.db.impl 
* @Description:  
* @author wangyu   
* @date 2014-12-10 下午1:51:21 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.configration.db.impl;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.configration.db.MySQLDbStatExtendsDAO;
import com.tyyd.ywpt.dao.configration.db.dataobject.MySQLDbStatExtendsDomain;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class MySQLDbStatExtendsDAOImpl extends TyydBaseDAO implements MySQLDbStatExtendsDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.configration.db.dataobject.MySQLDbStatExtendsDomain";
	
	@Override
	public void addMySQLDbStatExtends(MySQLDbStatExtendsDomain domain) {
		this.getSqlSessionTemplate().insert(context_space +".add_mysql_db_stat_extends", domain);
	}

	@Override
	public void updateMySQLDbStatExtends(MySQLDbStatExtendsDomain domain) {
		this.getSqlSessionTemplate().update(context_space +".update_mysql_db_stat_extends", domain);
	}

	@Override
	public MySQLDbStatExtendsDomain getMySQLDbStatExtendsDomain(String dbId) {
		return this.getSqlSessionTemplate().selectOne(context_space +".get_mysql_db_stat_extends", dbId);
	}

}
