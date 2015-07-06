/**   
* @Title: DbTableSpaceDAOImpl.java 
* @Package com.tyyd.ywpt.dao.core.collect.datafile.impl 
* @Description:  
* @author wangyu   
* @date 2014-6-23 下午2:56:53 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.datafile.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.core.collect.datafile.DbTableSpaceDAO;
import com.tyyd.ywpt.dao.core.collect.datafile.dataobject.DbTableSpaceDomain;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class DbTableSpaceDAOImpl extends TyydBaseDAO implements DbTableSpaceDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.core.collect.datafile.dataobject.DbTableSpaceDomain";
	
	@Override
	public void addDbTableSpace(DbTableSpaceDomain domain) {
		this.getSqlSessionTemplate().insert(context_space +".add_db_tablespace", domain);
	}

	@Override
	public List<DbTableSpaceDomain> listDbTableSpace(String monitorId) {
		return this.getSqlSessionTemplate().selectList(context_space +".list_db_table_space", monitorId);
	}

	@Override
	public void deleteDbTableSpace(String monitorId) {
		this.getSqlSessionTemplate().delete(context_space +".delete_db_tablespace", monitorId);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.datafile.DbTableSpaceDAO#totalTbsInfo(java.lang.String)
	 */
	@Override
	public DbTableSpaceDomain totalTbsInfo(String dbId) {
		return this.getSqlSessionTemplate().selectOne(context_space +".total_tbs_info", dbId);
	}

	@Override
	public void updateDbTableSpaceInfo(String dbId, String tbsName,
			Float usedTbs, Float maxTbs, Float usePercent) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("dbId", dbId);
		parameter.put("tbsName", tbsName);
		parameter.put("usedTbs", usedTbs);
		parameter.put("maxTbs", maxTbs);
		parameter.put("usePercent", usePercent);
		this.getSqlSessionTemplate().update(context_space+".update_tbs_info",parameter);
	}

	@Override
	public DbTableSpaceDomain getDbTableSpaceMonitorById(String dbId,
			String tbsName) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("dbId", dbId);
		parameter.put("tbsName", tbsName);
		
		return this.getSqlSessionTemplate().selectOne(context_space+".get_tbs_monitor_by_id",parameter);
	}


}
