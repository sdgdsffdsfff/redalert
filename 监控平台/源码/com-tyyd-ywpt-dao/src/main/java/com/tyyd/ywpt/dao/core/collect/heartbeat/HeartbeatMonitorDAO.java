/**   
* @Title: HeartbeatMonitorDAO.java 
* @Package com.tyyd.ywpt.dao.core.collect.heartbeat 
* @Description:  
* @author wangyu   
* @date 2014-6-23 下午5:10:00 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.heartbeat;

import java.util.List;

import com.tyyd.ywpt.dao.core.collect.heartbeat.dataobject.HeartbeatMonitorDomain;

/**
 * @author wangyu
 *
 */
public interface HeartbeatMonitorDAO {

	/**
	 * 添加一条心跳监控日志
	 * @param domain
	 */
	public void addHeartbeatMonitor(HeartbeatMonitorDomain domain);
	
	
	/**
	 * 返回没处理的数据,限制100条
	 * @param limitCounts
	 * @return
	 */
	public List<HeartbeatMonitorDomain> listNoCompletedHeartbeat();
	
	
	/**
	 * 关闭已处理任务
	 * @param id
	 */
	public void closeCompletedTask(String id);
	
	/**
	 * 关闭正常任务
	 */
	public void closeNormalTask();
	
	
	/**
	 * 清理老数据
	 */
	public void clearOldData();
}
