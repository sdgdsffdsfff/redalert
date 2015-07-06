/**   
* @Title: DBAHisTablesDAOImpl.java 
* @Package com.tyyd.ywpt.dao.dba.his.impl 
* @Description:  
* @author wangyu   
* @date 2015-2-4 上午11:23:29 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.dba.his.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.dba.his.DBAHisTablesDAO;
import com.tyyd.ywpt.dao.dba.his.dataobject.DBAStatSnapDomain;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class DBAHisTablesDAOImpl extends TyydBaseDAO implements DBAHisTablesDAO {

	private final static String context = "com.tyyd.ywpt.dao.dba.his.dataobject.DBAStatSnapDomain";
	
	@Override
	public void moveDBAHisTable(String srcTableName, String destTableName,
			Long dbId, Long snapId, Long instanceId) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("srcTableName", srcTableName);
		map.put("destTableName", destTableName);
		map.put("dbId", dbId);
		map.put("snapId", snapId);
		map.put("instanceId", instanceId);
		
		//先在目标表中添加snap的数据
		this.getSqlSessionTemplate().insert(context+".dest_table_save",map);
		
		//再清理源数据
		this.getSqlSessionTemplate().delete(context+".src_table_delete",map);
		
	}

	@Override
	public List<DBAStatSnapDomain> listHisSnap() {
		return this.getSqlSessionTemplate().selectList(context+".list_his_snap");
	}
	
	
	

}
