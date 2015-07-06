/**   
* @Title: MySQLDbStatExtendsDAO.java 
* @Package com.tyyd.ywpt.dao.configration.db 
* @Description:  
* @author wangyu   
* @date 2014-12-10 下午1:49:58 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.configration.db;

import com.tyyd.ywpt.dao.configration.db.dataobject.MySQLDbStatExtendsDomain;

/**
 * @author wangyu
 *
 */
public interface MySQLDbStatExtendsDAO {

	
	/**
	 * 添加扩展信息
	 * @param domain
	 */
	public void addMySQLDbStatExtends(MySQLDbStatExtendsDomain domain);
	
	
	/**
	 * 修改扩展信息
	 * @param domain
	 */
	public void updateMySQLDbStatExtends(MySQLDbStatExtendsDomain domain);
	
	
	/**
	 * 获取信息
	 * @param dbId
	 * @return
	 */
	public MySQLDbStatExtendsDomain getMySQLDbStatExtendsDomain(String dbId);
	
}
