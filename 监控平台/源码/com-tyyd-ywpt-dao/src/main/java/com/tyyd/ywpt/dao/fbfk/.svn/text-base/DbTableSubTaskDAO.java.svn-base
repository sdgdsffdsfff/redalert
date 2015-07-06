/**   
* @Title: DbTableSubTaskDAO.java 
* @Package com.tyyd.ywpt.dao.fbfk 
* @Description:  
* @author wangyu   
* @date 2015-4-15 上午10:05:47 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.fbfk;

import java.util.List;

import com.tyyd.ywpt.dao.fbfk.dataobject.DbTableSubTaskDomain;

/**
 * 任务列表
 * @author wangyu
 *
 */
public interface DbTableSubTaskDAO {

	/**
	 * 获取多少分钟内的所有任务
	 * @return
	 */
	public List<DbTableSubTaskDomain> listDbTableSubTasks();
	
	
	/**
	 * 关闭任务
	 * @param taskId
	 */
	public void updateDbTableSubTaskByTaskId(Long taskId,Integer status);
}
