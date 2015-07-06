/**   
* @Title: HostQuotaHisDAO.java 
* @Package com.tyyd.ywpt.dao.core.collect.his.host 
* @Description:  
* @author wangyu   
* @date 2014-9-1 上午9:47:14 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.his.host;

/**
 * @author wangyu
 *
 */
public interface HostQuotaHisDAO {

	/**
	 * 迁移历史数据，比gmtCreatedDate时间还早的数据
	 * @param gmtCreatedDate 格式为YYYY-MM-DD
	 */
	public void moveHisData(String gmtCreatedDate);
}
