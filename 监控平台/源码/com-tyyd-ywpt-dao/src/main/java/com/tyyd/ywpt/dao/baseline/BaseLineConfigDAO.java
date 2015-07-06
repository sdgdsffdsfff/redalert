/**   
* @Title: BaseLineConfigDAO.java 
* @Package com.tyyd.ywpt.dao.baseline 
* @Description:  
* @author wangyu   
* @date 2015-3-10 上午10:49:41 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.baseline;

import java.util.List;

import com.tyyd.ywpt.dao.baseline.dataobject.BaseLineConfigDomain;

/**
 * @author wangyu
 *
 */
public interface BaseLineConfigDAO {

	/**
	 * 获取监控机的基线
	 * @param monitorId
	 * @param configType
	 * @return
	 */
	public List<BaseLineConfigDomain> listBaseLineConfig(String monitorId,Integer configType);

	
	/**
	 * 是否存在这个指标的基线
	 * @param monitorId
	 * @param configType
	 * @param quotaId
	 * @return
	 */
	public boolean isExistsBaseLineConfig(String monitorId,Integer configType,String quotaId);
	
}
