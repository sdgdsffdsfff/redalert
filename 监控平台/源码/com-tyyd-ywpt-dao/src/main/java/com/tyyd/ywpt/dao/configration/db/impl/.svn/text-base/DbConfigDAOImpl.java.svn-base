/**   
* @Title: DbConfigDAOImpl.java 
* @Package com.tyyd.ywpt.dao.configration.db.impl 
* @Description:  
* @author wangyu   
* @date 2014-6-20 下午4:13:55 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.configration.db.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.base.dataobject.PageQuery;
import com.tyyd.ywpt.dao.configration.db.DbConfigDAO;
import com.tyyd.ywpt.dao.configration.db.dataobject.DbConfigDomain;

/**
 * @author wangyu
 *
 */
public class DbConfigDAOImpl extends TyydBaseDAO<List<DbConfigDomain>> implements DbConfigDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.configration.db.dataobject.DbConfigDomain";
	
	@Override
	public void addDataBaseConfig(DbConfigDomain domain) {
		this.getSqlSessionTemplate().insert(context_space +".add_database_config", domain);
	}

	@Override
	public void delDataBaseConfig(String dbId) {
		this.getSqlSessionTemplate().update(context_space +".del_database_config", dbId);
	}

	@Override
	public DbConfigDomain getDataBaseConfig(String dbId) {
		return this.getSqlSessionTemplate().selectOne(context_space +".get_database_config", dbId);
	}

	@Override
	public PageQuery<List<DbConfigDomain>> listDataBaseConfig(
			PageQuery<List<DbConfigDomain>> pageQuery, String ipAddr) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("ipAddr", ipAddr);
		return this.selectPages(context_space+".list_database_config", pageQuery, parameterMap);
	}

	@Override
	public void updateDataBaseConfig(DbConfigDomain domain) {
		this.getSqlSessionTemplate().update(context_space+".update_database_config", domain);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.configration.db.DbConfigDAO#updateDataBaseMonitorStatus(com.tyyd.ywpt.dao.configration.db.dataobject.DbConfigDomain)
	 */
	@Override
	public void updateDataBaseMonitorStatus(DbConfigDomain domain) {
		this.getSqlSessionTemplate().update(context_space+".update_database_monitor", domain);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.configration.db.DbConfigDAO#listDbConfigByServerId(java.lang.String, java.lang.String)
	 */
	@Override
	public List<DbConfigDomain> listDbConfigByServerId(String serverId,
			String dbType) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("serverId", serverId);
		parameterMap.put("dbType", dbType);
		
		return this.getSqlSessionTemplate().selectList(context_space+".list_db_by_map",parameterMap);
	}


	@Override
	public List<DbConfigDomain> listNormalDbConfig(Integer dbType) {
		
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("dbType", dbType);
		
		return this.getSqlSessionTemplate().selectList(context_space+".list_normal_db",parameterMap);
	}

}
