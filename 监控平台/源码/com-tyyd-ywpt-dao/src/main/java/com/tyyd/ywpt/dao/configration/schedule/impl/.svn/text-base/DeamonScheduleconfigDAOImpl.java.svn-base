/**   
* @Title: DeamonScheduleconfigDAOImpl.java 
* @Package com.tyyd.ywpt.dao.configration.schedule.impl 
* @Description:  
* @author wangyu   
* @date 2014-6-24 上午10:30:24 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.configration.schedule.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.configration.schedule.DeamonScheduleconfigDAO;
import com.tyyd.ywpt.dao.configration.schedule.dataobject.DeamonScheduleconfigDomain;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class DeamonScheduleconfigDAOImpl extends TyydBaseDAO implements
		DeamonScheduleconfigDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.configration.schedule.dataobject.DeamonScheduleconfigDomain";
	
	@Override
	public void addDeamonScheduleconfig(DeamonScheduleconfigDomain domain) {
		this.getSqlSessionTemplate().insert(context_space + ".add_daemon_schedule_config", domain);
	}

	@Override
	public void deleteDeamonScheduleconfig(String id) {
		this.getSqlSessionTemplate().delete(context_space + ".delete_daemon_schedule_config", id);
	}

	@Override
	public DeamonScheduleconfigDomain getScheduleconfigById(String id) {
		return this.getSqlSessionTemplate().selectOne(context_space +".get_schedule_config_by_id", id);
	}

	@Override
	public void updateDeamonScheduleconfig(DeamonScheduleconfigDomain domain) {
		this.getSqlSessionTemplate().update(context_space + ".update_daemon_schedule_config", domain);
	}

	@Override
	public List<DeamonScheduleconfigDomain> listDeamonScheduleconfigByMonitorId(
			String monitorId, Integer configType) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("configType", configType);
		parameterMap.put("monitorId", monitorId);
		return this.getSqlSessionTemplate().selectList(
				context_space + ".list_deamon_schedule_config_by_monitor_id",
				parameterMap);
	}

	@Override
	public List<DeamonScheduleconfigDomain> listCloseDeamonScheduleconfig() {
		return this.getSqlSessionTemplate().selectList(
				context_space + ".list_close_deamon_schedule_config");
	}

	@Override
	public List<DeamonScheduleconfigDomain> listServerDeamonScheduleconfig(String hostId) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("hostId", hostId);
		return this.getSqlSessionTemplate().selectList(
				context_space + ".list_host_jobs",parameterMap);
	}

	@Override
	public List<DeamonScheduleconfigDomain> listDbTypeDeamonScheduleconfig(
			String monitorId, Integer configType) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("configType", configType);
		parameterMap.put("dbId", monitorId);
		return this.getSqlSessionTemplate().selectList(
				context_space + ".list_db_jobs",parameterMap);
	}

	@Override
	public void openDeamonScheduleconfig(String id) {
		this.getSqlSessionTemplate().delete(context_space + ".open_daemon_schedule_config", id);
	}

	@Override
	public DeamonScheduleconfigDomain getHangScheduleconfigById(String id) {
		return this.getSqlSessionTemplate().selectOne(context_space +".get_hang_schedule_config_by_id", id);
	}

	@Override
	public List<DeamonScheduleconfigDomain> listHangScheduleTasks() {
		return this.getSqlSessionTemplate().selectList(context_space+".list_hang_schedule");
	}

}
