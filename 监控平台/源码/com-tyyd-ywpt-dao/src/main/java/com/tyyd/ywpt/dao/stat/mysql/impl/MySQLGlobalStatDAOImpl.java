/**   
* @Title: MySQLGlobalStatDAOImpl.java 
* @Package com.tyyd.ywpt.dao.stat.mysql.impl 
* @Description:  
* @author wangyu   
* @date 2014-12-11 下午2:33:08 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.stat.mysql.impl;

import java.util.List;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.stat.mysql.MySQLGlobalStatDAO;
import com.tyyd.ywpt.dao.stat.mysql.dataobject.MySQLGlobalStatDomain;

/**
 * @author wangyu
 *
 */
public class MySQLGlobalStatDAOImpl extends TyydBaseDAO implements MySQLGlobalStatDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.stat.mysql.dataobject.MySQLGlobalStatDomain";
	
	@Override
	public List<MySQLGlobalStatDomain> listMySQLGlobalStat() {
		return this.getSqlSessionTemplate().selectList(context_space+".list_mysql_global_stat");
	}

}
