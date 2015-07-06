/**   
* @Title: QuotaScheduleconfigDAO.java 
* @Package com.tyyd.ywpt.dao.configration.schedule 
* @Description:  
* @author wangyu   
* @date 2014-6-22 上午11:04:24 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.configration.schedule;

import java.util.List;

import com.tyyd.ywpt.dao.configration.schedule.dataobject.DeamonScheduleconfigDomain;

/**
 * @author wangyu
 *
 */
public interface DeamonScheduleconfigDAO {

	/**
	 * 添加一个执行计划
	 * @param domain
	 */
	public void addDeamonScheduleconfig(DeamonScheduleconfigDomain domain);
	
	/**
	 * 删除指标的任务
	 * @param id
	 */
	public void deleteDeamonScheduleconfig(String id);
	
	
	/**
	 * 开启
	 * @param id
	 */
	public void openDeamonScheduleconfig(String id);
	
	/**
	 * 获取一个执行计划的内容
	 * @param id
	 * @return
	 */
	public DeamonScheduleconfigDomain getScheduleconfigById(String id);
	
	/**
	 * 更新任务配置
	 * @param domain
	 */
	public void updateDeamonScheduleconfig(DeamonScheduleconfigDomain domain);
	
	
	/**
	 * 根据监控对象获取所有的指标
	 * @param monitorId
	 * @return
	 */
	public List<DeamonScheduleconfigDomain> listDeamonScheduleconfigByMonitorId(String monitorId,Integer configType);
	
	
	/**
	 * 获取所有关闭的任务
	 * @return
	 */
	public List<DeamonScheduleconfigDomain> listCloseDeamonScheduleconfig();
	
	
	/**
	 * 主机任务
	 * @return
	 */
	public List<DeamonScheduleconfigDomain> listServerDeamonScheduleconfig(String hostId);
	
	
	/**
	 * 其他类型任务
	 * @param dbTypes
	 * @return
	 */
	public List<DeamonScheduleconfigDomain> listDbTypeDeamonScheduleconfig(String monitorId, Integer configType);
	
	
	/**
	 * 状态被停用的任务
	 * @param id
	 * @return
	 */
	public DeamonScheduleconfigDomain getHangScheduleconfigById(String id);
	
	
	/**
	 * 停用的任务列表
	 * @return
	 */
	public List<DeamonScheduleconfigDomain> listHangScheduleTasks();
}
