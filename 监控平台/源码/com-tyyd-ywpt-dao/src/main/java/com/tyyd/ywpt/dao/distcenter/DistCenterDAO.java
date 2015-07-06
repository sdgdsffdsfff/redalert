/**   
* @Title: DistCenterDAO.java 
* @Package com.tyyd.ywpt.dao.distcenter 
* @Description:  
* @author wangyu   
* @date 2015-1-7 下午2:30:08 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.distcenter;

import java.util.List;

import com.tyyd.ywpt.dao.distcenter.dataobject.DistCenterDomain;

/**
 * @author wangyu
 *
 */
public interface DistCenterDAO {

	
	/**
	 * 
	 * @return
	 */
	public List<DistCenterDomain> listAllConfig();
}
