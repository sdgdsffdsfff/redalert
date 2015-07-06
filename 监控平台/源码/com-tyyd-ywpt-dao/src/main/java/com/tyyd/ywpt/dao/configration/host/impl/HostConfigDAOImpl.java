/**   
* @Title: HostDAOImpl.java 
* @Package com.tyyd.ywpt.dao.configration.host.impl 
* @Description:  
* @author wangyu   
* @date 2014-6-20 下午2:07:20 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.configration.host.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.base.dataobject.PageQuery;
import com.tyyd.ywpt.dao.configration.host.HostConfigDAO;
import com.tyyd.ywpt.dao.configration.host.dataobject.HostConfigDomain;

/**
 * @author wangyu
 *
 */
public class HostConfigDAOImpl extends TyydBaseDAO<List<HostConfigDomain>> implements HostConfigDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.configration.host.dataobject.HostConfigDomain";
	
	@Override
	public void addHost(HostConfigDomain domain) {
		this.getSqlSessionTemplate().insert(context_space+".add_host", domain);
	}

	@Override
	public void delHost(String hostId) {
		this.getSqlSessionTemplate().update(context_space +".del_host", hostId);
	}

	@Override
	public HostConfigDomain getHostById(String hostId) {
		return this.getSqlSessionTemplate().selectOne(context_space +".get_host_by_id", hostId);
	}

	@Override
	public PageQuery<List<HostConfigDomain>> listHost(
			PageQuery<List<HostConfigDomain>> pageQuery, String ipAddr, String idc,
			String hostName) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("ipAddr", ipAddr);
		parameterMap.put("idc", idc);
		parameterMap.put("hostName", hostName);
		return this.selectPages(context_space +".list_host", pageQuery, parameterMap);
	}

	@Override
	public void updateHostInfo(HostConfigDomain domain) {
		this.getSqlSessionTemplate().update(context_space +".update_host_info", domain);
	}


	@Override
	public void updateHostMonitorStatus(HostConfigDomain domain) {
		this.getSqlSessionTemplate().update(context_space +".update_host_monitor", domain);
	}


	@Override
	public List<HostConfigDomain> listHostConfig(String serverId) {
		return this.getSqlSessionTemplate().selectList(context_space +".list_all_host_by_serverid",serverId);
	}

	@Override
	public List<HostConfigDomain> listNormalHostConfig() {
		return this.getSqlSessionTemplate().selectList(context_space +".list_normal_host");
	}

}
