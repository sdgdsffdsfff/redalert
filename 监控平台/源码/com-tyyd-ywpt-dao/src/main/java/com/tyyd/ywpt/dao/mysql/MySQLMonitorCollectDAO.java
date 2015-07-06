/**   
* @Title: MySQLMonitorCollectDAO.java 
* @Package com.tyyd.ywpt.dao.mysql 
* @Description:  
* @author wangyu   
* @date 2014-7-3 上午9:29:51 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.mysql;

import java.util.List;

import com.tyyd.ywpt.dao.mysql.dataobject.MySQLStatDomain;

/**
 * @author wangyu
 *
 */
public interface MySQLMonitorCollectDAO {

	/**
	 * 获取MySQL某个DB的变量值
	 * @param dbId
	 * @return
	 */
	public List<MySQLStatDomain> listMySQLStats(String[] variables);
	
	
	/**
	 * 获取当前日期
	 * @return
	 */
	public String getCurrentDate();
}
