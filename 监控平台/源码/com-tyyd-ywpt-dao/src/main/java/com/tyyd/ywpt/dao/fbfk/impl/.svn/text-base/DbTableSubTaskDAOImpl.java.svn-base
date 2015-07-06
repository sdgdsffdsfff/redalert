/**   
* @Title: DbTableSubTaskDAOImpl.java 
* @Package com.tyyd.ywpt.dao.fbfk.impl 
* @Description:  
* @author wangyu   
* @date 2015-4-15 下午4:06:51 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.fbfk.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.fbfk.DbTableSubTaskDAO;
import com.tyyd.ywpt.dao.fbfk.dataobject.DbTableSubTaskDomain;

/**
 * @author wangyu
 *
 */
public class DbTableSubTaskDAOImpl extends TyydBaseDAO implements
		DbTableSubTaskDAO {

	private static final String context = "com.tyyd.ywpt.dao.fbfk.dataobject.DbTableSubLogDomain";
	
	@Override
	public List<DbTableSubTaskDomain> listDbTableSubTasks() {
		return this.getSqlSessionTemplate().selectList(context+".list_db_table_sub_tasks");
	}

	@Override
	public void updateDbTableSubTaskByTaskId(Long taskId,Integer status) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("taskId", taskId);
		parameterMap.put("status", status);
		this.getSqlSessionTemplate().update(context+".update_db_table_sub_task_by_task_id", parameterMap);
	}

}
