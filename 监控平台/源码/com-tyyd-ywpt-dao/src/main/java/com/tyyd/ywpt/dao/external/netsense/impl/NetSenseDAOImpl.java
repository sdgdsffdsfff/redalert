/**   
* @Title: NetSenseDAOImpl.java 
* @Package com.tyyd.ywpt.dao.external.netsense.impl 
* @Description:  
* @author wangyu   
* @date 2015-1-22 下午5:26:25 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.external.netsense.impl;

import java.util.HashMap;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.external.netsense.NetSenseDAO;
import com.tyyd.ywpt.dao.external.netsense.dataobject.NetSenseDataDomain;

/**
 * @author wangyu
 *
 */
public class NetSenseDAOImpl extends TyydBaseDAO implements NetSenseDAO {

	private final static String context = "com.tyyd.ywpt.dao.external.netsense.dataobject.NetSenseDataDomain";
	
	@Override
	public Long getLastId() {
		return this.getSqlSessionTemplate().selectOne(context+".get_lastid");
	}

	@Override
	public void addLastId(Long id) {
		this.getSqlSessionTemplate().insert(context+".add_lastid", id);
	}

	@Override
	public void updateLastId(Long nowId, Long oldId) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("nowId", nowId);
		parameter.put("oldId", oldId);
		this.getSqlSessionTemplate().update(context+".update_lastid",parameter);
	}

	@Override
	public void addNetSenseData(NetSenseDataDomain domain) {
		this.getSqlSessionTemplate().insert(context+".add_net_sense_data", domain);
	}

}
