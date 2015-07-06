/**   
* @Title: DbDatafilesDAOImpl.java 
* @Package com.tyyd.ywpt.dao.core.collect.datafile.impl 
* @Description:  
* @author wangyu   
* @date 2014-6-23 下午2:04:39 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.datafile.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.core.collect.datafile.DbDatafilesDAO;
import com.tyyd.ywpt.dao.core.collect.datafile.dataobject.DbDatafilesDomain;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class DbDatafilesDAOImpl extends TyydBaseDAO implements DbDatafilesDAO{
	
	private final static String context_space = "com.tyyd.ywpt.dao.core.collect.datafile.dataobject.DbDatafilesDomain";
	
	@Override
	public void addDatafile(DbDatafilesDomain domain) {
		this.getSqlSessionTemplate().insert(context_space +".add_datafile", domain);
	}

	@Override
	public List<DbDatafilesDomain> listDbDatafiles(String startDate,
			String endDate, String tbsName,String dbId) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("startQueryDate", startDate);
		parameter.put("endQueryDate", endDate);
		parameter.put("tbsName", tbsName);
		parameter.put("dbId", dbId);
		return this.getSqlSessionTemplate().selectList(context_space +".list_db_datafiles", parameter);
	}

}
