/**   
* @Title: TaskJobLogDAO.java 
* @Package com.tyyd.ywpt.dao.core.schedule.tasklog 
* @Description:  
* @author wangyu   
* @date 2014-6-24 下午6:01:45 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.schedule.tasklog;

import java.util.List;

import com.tyyd.ywpt.dao.base.dataobject.PageQuery;
import com.tyyd.ywpt.dao.core.schedule.tasklog.dataobject.TaskJobLogDomain;

/**
 * @author wangyu
 *
 */
public interface TaskJobLogDAO {

	/**
	 * 添加一个运行的任务
	 * @param domain
	 */
	public void addTaskJobLog(TaskJobLogDomain domain);
	
	
	
	/**
	 * 
	 * @param pageQuery
	 * @return
	 */
	public PageQuery<List<TaskJobLogDomain>> listTaskJobLog(PageQuery<List<TaskJobLogDomain>> pageQuery,String monitorId,Integer sysType,String beanId,String jobId);
	
	
	/**
	 * 找出最近一次任务运行的时间
	 * @param serverId
	 * @return
	 */
	public List<TaskJobLogDomain> listTaskJobByDaemon(String serverId);
	
	/**
	 * 找出最近一次任务运行的时间
	 * @return
	 */
	public List<TaskJobLogDomain> listTaskJobByDaemon();
	
	/**
	 * 清理老数据
	 */
	public void clearOldData();
}
