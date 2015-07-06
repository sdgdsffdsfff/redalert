/**   
* @Title: TaskProcessDAO.java 
* @Package com.tyyd.ywpt.dao.core.schedule.process 
* @Description:  
* @author wangyu   
* @date 2014-6-24 上午11:53:37 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.schedule.process;

import com.tyyd.ywpt.dao.core.schedule.process.dataobject.TaskProcessDomain;

/**
 * @author wangyu
 *
 */
public interface TaskProcessDAO {

	/**
	 * 注册任务
	 * @param domain
	 */
	public void addTaskProcess(TaskProcessDomain domain);
	
	/**
	 * 删除进程任务
	 * @param hostId
	 * @param dbId
	 * @param configType
	 * @param processId
	 * @param daemonType
	 * @param isHeartBeat
	 */
	public void delTaskProcess(String hostId,String dbId,Integer sysType,String processId,Integer daemonType,Integer isHeartBeat ); 
	
	/**
	 * 获取进程
	 * @param monitorId
	 * @param configType
	 * @param daemonId
	 * @return
	 */
	public TaskProcessDomain getTaskProcess(String hostId,String dbId,Integer sysType,String processId,Integer daemonType,Integer isHeartBeat); 
}
