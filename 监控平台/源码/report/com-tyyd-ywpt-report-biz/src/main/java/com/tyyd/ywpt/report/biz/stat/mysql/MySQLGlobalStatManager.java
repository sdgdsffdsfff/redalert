/**   
* @Title: MySQLGlobalStatManager.java 
* @Package com.tyyd.ywpt.report.biz.stat.mysql 
* @Description:  
* @author wangyu   
* @date 2014-12-11 下午2:56:52 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.stat.mysql;

import java.util.List;

import com.tyyd.ywpt.report.biz.stat.mysql.dto.MySQLGlobalStatDTO;

/**
 * @author wangyu
 *
 */
public interface MySQLGlobalStatManager {

	public List<MySQLGlobalStatDTO> listMySQLGlobalStat();
}
