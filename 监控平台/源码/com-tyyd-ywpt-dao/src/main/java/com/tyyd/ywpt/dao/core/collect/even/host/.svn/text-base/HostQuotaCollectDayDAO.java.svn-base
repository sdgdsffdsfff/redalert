/**   
* @Title: HostQuotaCollectDayDAO.java 
* @Package com.tyyd.ywpt.dao.core.collect.even.host 
* @Description:  
* @author wangyu   
* @date 2014-6-25 上午9:38:19 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.even.host;

import java.util.List;

import com.tyyd.ywpt.dao.core.collect.even.host.dataobject.HostQuotaCollectDayDomain;

/**
 * @author wangyu
 *
 */
public interface HostQuotaCollectDayDAO {

	/**
	 * 添加一个指标值
	 * @param domain
	 */
	public void addHostQuotaCollectDay(HostQuotaCollectDayDomain domain);
	
	
	/**
	 * 迁移数据(预留)
	 * @param startCreatedDate
	 * @param endCreatedDate
	 */
	public void moveHostQuotaCollectDay(String startCreatedDate,String endCreatedDate);
	
	
	/**
	 * 一个时间段内的指标值
	 * @param startCreatedDate
	 * @param endCreatedDate
	 * @param hostId
	 * @param quotaId
	 * @return
	 */
	public List<HostQuotaCollectDayDomain> listHostQuotaCollectDay(String startCreatedDate,String endCreatedDate,String hostId,String quotaId);
	
	
	/**
	 * 获取上一个指标采集的值
	 * @param hostId
	 * @param quotaId
	 * @return
	 */
	public HostQuotaCollectDayDomain getLastHostQuotaCollect(String hostId,String quotaId);
	
	
	/**
	 * 未处理的指标列表
	 * @return
	 */
	public List<HostQuotaCollectDayDomain> listUnCompletedQuotaCollectData();
	
	
	/**
	 * @param id
	 */
	public void closeUnCompletedQuotaCollectDataById(String id);
	
	
	/**
	 * 关闭正常的任务
	 */
	public void closeNormalUnCompletedQuotaCollectData();
	
	
	/**
	 * 报表展示的数据
	 * @param quotaId
	 * @param pageSize
	 * @return
	 */
	public List<HostQuotaCollectDayDomain> listReportData(String hostId,String quotaId,Integer pageSize,String startDate,String endDate);
	
	
	/**
	 * 关闭未配置预警阈值的任务
	 */
	public void closeNoThresholdTasks();
	
	
	/**
	 * 返回1000行，未处理的数据
	 */
	public List<HostQuotaCollectDayDomain> getHostMonitorUnCompletedData();
	
	
	/**
	 * 查询未处理的磁盘数据
	 * @param id
	 * @return
	 */
	public HostQuotaCollectDayDomain getHostMonitorUnCompletedObj(String id);
	
}
