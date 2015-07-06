/**   
* @Title: HostDirAlertConfigDAOImpl.java 
* @Package com.tyyd.ywpt.dao.configration.hostdiralert.impl 
* @Description:  
* @author wangyu   
* @date 2014-6-20 下午5:42:09 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.configration.hostdiralert.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.base.dataobject.PageQuery;
import com.tyyd.ywpt.dao.configration.hostdiralert.HostDirAlertConfigDAO;
import com.tyyd.ywpt.dao.configration.hostdiralert.dataobject.HostDirAlertConfigDomain;

/**
 * @author wangyu
 *
 */
public class HostDirAlertConfigDAOImpl extends TyydBaseDAO<List<HostDirAlertConfigDomain>> implements
		HostDirAlertConfigDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.configration.hostdiralert.dataobject.HostDirAlertConfigDomain";
	
	
	@Override
	public void addHostDirAlertConfig(HostDirAlertConfigDomain domain) {
		this.getSqlSessionTemplate().insert(context_space +".add_host_dir_alert_config", domain);
	}

	@Override
	public void delHostDirAlertConfig(String id) {
		this.getSqlSessionTemplate().delete(context_space +".del_host_dir_alert_config", id);
	}

	@Override
	public HostDirAlertConfigDomain getHostDirAlertConfig(String id) {
		return this.getSqlSessionTemplate().selectOne(context_space +".get_host_dir_alert_config", id);
	}

	@Override
	public void updateHostDirAlertConfig(HostDirAlertConfigDomain domain) {
		this.getSqlSessionTemplate().update(context_space +".update_host_dir_alert_config", domain);
	}

	@Override
	public PageQuery<List<HostDirAlertConfigDomain>> listHostDirAlertConfig(
			PageQuery<List<HostDirAlertConfigDomain>> pageQuery, String hostDir,String hostId) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("hostDir", hostDir);
		parameterMap.put("hostId", hostId);
		return this.selectPages(context_space +".list_host_dir_alert_config", pageQuery, parameterMap);
	}


	
	@Override
	public List<HostDirAlertConfigDomain> listHostAlertConfig(Integer configType,
			Integer logType, String serverId) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("configType", configType);
		parameterMap.put("logType", logType);
		parameterMap.put("serverId", serverId);
		return this.getSqlSessionTemplate().selectList(context_space +".list_host_alert_config",parameterMap);
	}

	
	@Override
	public List<HostDirAlertConfigDomain> listDbAlertConfig(Integer configType,
			Integer logType, String serverId) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("configType", configType);
		parameterMap.put("logType", logType);
		parameterMap.put("serverId", serverId);
		return this.getSqlSessionTemplate().selectList(context_space +".list_db_alert_config",parameterMap);
	}
}
