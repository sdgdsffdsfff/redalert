/**   
* @Title: TaskProcessDAOImpl.java 
* @Package com.tyyd.ywpt.dao.core.schedule.process.impl 
* @Description:  
* @author wangyu   
* @date 2014-6-24 下午12:57:38 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.schedule.process.impl;

import java.util.HashMap;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.core.schedule.process.TaskProcessDAO;
import com.tyyd.ywpt.dao.core.schedule.process.dataobject.TaskProcessDomain;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class TaskProcessDAOImpl extends TyydBaseDAO implements TaskProcessDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.core.schedule.process.dataobject.TaskProcessDomain";
	
	
	@Override
	public void addTaskProcess(TaskProcessDomain domain) {
		this.getSqlSessionTemplate().insert(context_space +".add_task_process", domain);
	}

	@Override
	public void delTaskProcess(String hostId, String dbId, Integer sysType,
			String processId, Integer daemonType, Integer isHeartBeat) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("hostId", hostId);
		parameter.put("dbId", dbId);
		parameter.put("sysType", sysType);
		parameter.put("processId", processId);
		parameter.put("daemonType", daemonType);
		parameter.put("isHeartBeat", isHeartBeat);
		this.getSqlSessionTemplate().delete(context_space +".del_task_process", parameter);
	}

	@Override
	public TaskProcessDomain getTaskProcess(String hostId, String dbId,
			Integer sysType, String processId, Integer daemonType,
			Integer isHeartBeat) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("hostId", hostId);
		parameter.put("dbId", dbId);
		parameter.put("sysType", sysType);
		parameter.put("processId", processId);
		parameter.put("daemonType", daemonType);
		parameter.put("isHeartBeat", isHeartBeat);
		
		return this.getSqlSessionTemplate().selectOne(context_space +".get_task_process", parameter);
	}

}
