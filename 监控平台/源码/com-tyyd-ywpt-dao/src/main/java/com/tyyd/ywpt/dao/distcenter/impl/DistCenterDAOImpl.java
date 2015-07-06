/**   
* @Title: DistCenterDAOImpl.java 
* @Package com.tyyd.ywpt.dao.distcenter.impl 
* @Description:  
* @author wangyu   
* @date 2015-1-7 下午2:37:26 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.distcenter.impl;

import java.util.List;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.distcenter.DistCenterDAO;
import com.tyyd.ywpt.dao.distcenter.dataobject.DistCenterDomain;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class DistCenterDAOImpl extends TyydBaseDAO implements DistCenterDAO {

	
	private final static String context = "com.tyyd.ywpt.dao.distcenter.dataobject.DistCenterDomain";
	
	@Override
	public List<DistCenterDomain> listAllConfig() {
		return this.getSqlSessionTemplate().selectList(context+".list_all_dist_center_config");
	}

	
	
	
}
