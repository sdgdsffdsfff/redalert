/**   
* @Title: FireWallDAOImpl.java 
* @Package com.tyyd.ywpt.dao.firewall.impl 
* @Description:  
* @author wangyu   
* @date 2015-4-20 下午4:41:21 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.firewall.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.firewall.FireWallDAO;
import com.tyyd.ywpt.dao.firewall.dataobject.FireWallDomain;

/**
 * @author wangyu
 *
 */
public class FireWallDAOImpl extends TyydBaseDAO implements FireWallDAO {

	private final static String context = "com.tyyd.ywpt.dao.firewall.dataobject.FireWallDomain";
	
	@Override
	public List<FireWallDomain> listFireWallByKey(String startDate,
			String endDate, String keyColumn) {

		
		Map<String,Object> paramter = new HashMap<String,Object>();
		paramter.put("startDate", startDate);
		paramter.put("endDate", endDate);
		paramter.put("keyColumn", keyColumn);
		
		return this.getSqlSessionTemplate().selectList(context+".list_fire_wall_by_key", paramter);
		
	}

}
