/**   
* @Title: QuotaMonitorConfigDAO.java 
* @Package com.tyyd.ywpt.dao.configration.schedule 
* @Description:  
* @author wangyu   
* @date 2014-6-24 上午9:40:37 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.configration.schedule;

import java.util.List;

import com.tyyd.ywpt.dao.configration.schedule.dataobject.QuotaScheduleconfigDomain;

/**
 * @author wangyu
 *
 */
public interface QuotaMonitorConfigDAO {

	/**
	 * 添加一个执行计划
	 * @param domain
	 */
	public void addQuotaScheduleconfig(QuotaScheduleconfigDomain domain);
	
	/**
	 * 删除指标的任务
	 * @param id
	 */
	public void deleteQuotaScheduleconfig(String id);
	
	
	
	/**
	 * 根据监控对象获取所有的指标
	 * @param monitorId
	 * @return
	 */
	public List<QuotaScheduleconfigDomain> listQuotaScheduleconfigByMonitorId(String monitorId,Integer configType);
	
}
