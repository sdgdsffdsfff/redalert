/**   
* @Title: MonitorIpPrivsDAOImpl.java 
* @Package com.tyyd.ywpt.dao.configration.privsip.impl 
* @Description:  
* @author wangyu   
* @date 2014-11-14 下午5:00:30 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.configration.privsip.impl;

import java.util.HashMap;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.configration.privsip.MonitorIpPrivsDAO;

/**
 * @author wangyu
 *
 */
public class MonitorIpPrivsDAOImpl extends TyydBaseDAO implements
		MonitorIpPrivsDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.configration.privsip.dataobject.MonitorIpPrivsDomain";
	
	
	@Override
	public boolean isExistsIp(String mdkey) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mdkey", mdkey);
		Integer counts = this.getSqlSessionTemplate().selectOne(context_space+".is_exists_privs_ip",map);
		if(counts>0){
			return true;
		}
		return false;
	}

}
