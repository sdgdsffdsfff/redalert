/**   
* @Title: NetSenseDAO.java 
* @Package com.tyyd.ywpt.dao.external.netsense 
* @Description:  
* @author wangyu   
* @date 2015-1-22 下午5:22:09 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.external.netsense;

import com.tyyd.ywpt.dao.external.netsense.dataobject.NetSenseDataDomain;

/**
 * @author wangyu
 *
 */
public interface NetSenseDAO {

	/**
	 * 获取lastID
	 * @return
	 */
	public Long getLastId();
	
	
	/**
	 * 新增ID
	 * @param id
	 */
	public void addLastId(Long id);
	
	
	/**
	 * 更新ID
	 * @param nowId
	 * @param oldId
	 */
	public void updateLastId(Long nowId,Long oldId);
	
	
	/**
	 * 新增DATA
	 * @param domain
	 */
	public void addNetSenseData(NetSenseDataDomain domain);
}
