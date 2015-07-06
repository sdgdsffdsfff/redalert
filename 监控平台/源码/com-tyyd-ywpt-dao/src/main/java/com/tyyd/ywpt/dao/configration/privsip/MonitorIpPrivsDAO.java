/**   
* @Title: MonitorIpPrivsDAO.java 
* @Package com.tyyd.ywpt.dao.configration.privsip 
* @Description:  
* @author wangyu   
* @date 2014-11-14 下午4:57:13 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.configration.privsip;

/**
 * @author wangyu
 *
 */
public interface MonitorIpPrivsDAO {

	/**
	 * 是否存在授权的IP
	 * @param mdkey MD5 IPADDR
	 * @return
	 */
	public boolean isExistsIp(String mdkey);
}
