/**   
* @Title: DbTableSubLogDAO.java 
* @Package com.tyyd.ywpt.dao.fbfk 
* @Description:  
* @author wangyu   
* @date 2015-4-15 上午10:06:08 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.fbfk;

import java.util.List;

import com.tyyd.ywpt.dao.fbfk.dataobject.DbTableSubLogDomain;

/**
 * 执行日志
 * @author wangyu
 *
 */
public interface DbTableSubLogDAO {

	/**
	 * 纪录日志
	 * @param log
	 */
	public void addDbTableSubLog(DbTableSubLogDomain log);

	
	
	/**
	 * 加载对应的任务运行纪录
	 * @param taskId
	 * @return
	 */
	public List<DbTableSubLogDomain> listDbTableSubLog(Long taskId);
	
	
}
