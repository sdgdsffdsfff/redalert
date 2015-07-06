/**   
* @Title: HeartbeatMonitorDAOImpl.java 
* @Package com.tyyd.ywpt.dao.core.collect.heartbeat.impl 
* @Description:  
* @author wangyu   
* @date 2014-6-23 下午5:44:02 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.heartbeat.impl;

import java.util.List;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.core.collect.heartbeat.HeartbeatMonitorDAO;
import com.tyyd.ywpt.dao.core.collect.heartbeat.dataobject.HeartbeatMonitorDomain;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class HeartbeatMonitorDAOImpl extends TyydBaseDAO implements
		HeartbeatMonitorDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.core.collect.heartbeat.dataobject.HeartbeatMonitorDomain";
	
	@Override
	public void addHeartbeatMonitor(HeartbeatMonitorDomain domain) {
		this.getSqlSessionTemplate().insert(context_space+".add_heartbeat_monitor", domain);
	}

	@Override
	public List<HeartbeatMonitorDomain> listNoCompletedHeartbeat() {
		return this.getSqlSessionTemplate().selectList(context_space + ".list_nocompleted_data");
	}

	@Override
	public void closeCompletedTask(String id) {
		this.getSqlSessionTemplate().update(context_space +".close_no_completed_by_id",id);
	}

	@Override
	public void closeNormalTask() {
		this.getSqlSessionTemplate().update(context_space +".close_normal_task");
	}

	@Override
	public void clearOldData() {
		this.getSqlSessionTemplate().delete(context_space +".del_heartbeat_by_old_date");
	}

}
