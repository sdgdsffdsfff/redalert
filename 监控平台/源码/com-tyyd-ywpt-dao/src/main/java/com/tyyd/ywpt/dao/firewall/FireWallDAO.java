/**   
* @Title: FireWallDAO.java 
* @Package com.tyyd.ywpt.dao.firewall 
* @Description:  
* @author wangyu   
* @date 2015-4-20 下午4:34:26 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.firewall;

import java.util.List;

import com.tyyd.ywpt.dao.firewall.dataobject.FireWallDomain;

/**
 * @author wangyu
 *
 */
public interface FireWallDAO {

	/**
	 * 加载数据
	 * @param startDate
	 * @param endDate
	 * @param keyColumn
	 * @return
	 */
	public List<FireWallDomain> listFireWallByKey(String startDate,String endDate,String keyColumn);
	
}
