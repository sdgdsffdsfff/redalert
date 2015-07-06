/**   
* @Title: OracleQuotaCollectDayDAO.java 
* @Package com.tyyd.ywpt.dao.core.collect.even.oracle 
* @Description:  
* @author wangyu   
* @date 2014-6-25 上午11:53:16 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.even.oracle;

import java.util.List;

import com.tyyd.ywpt.dao.core.collect.even.oracle.dataobject.OracleQuotaCollectDayDomain;

/**
 * @author wangyu
 *
 */
public interface OracleQuotaCollectDayDAO {

	
	/**
	 * 添加一个Oracle指标的采集数据
	 * @param domain
	 */
	public void addOracleQuotaCollectDay(OracleQuotaCollectDayDomain domain);
	
	/**
	 * 迁移历史数据，预留接口
	 * @param startDate
	 * @param endDate
	 */
	public void moveOracleQuotaCollectDay(String startDate,String endDate);
	
	
	/**
	 * 指标列表
	 * @param startDate
	 * @param endDate
	 * @param hostId
	 * @param dbId
	 * @param quotaId
	 * @return
	 */
	public List<OracleQuotaCollectDayDomain> listOracleQuotaCollectDay(String startDate,String endDate,String hostId,String dbId,String quotaId);
	
	
	/**
	 * 上一次采集值
	 * @param hostId
	 * @param dbId
	 * @param quotaId
	 * @return
	 */
	public OracleQuotaCollectDayDomain getLastOracleQuotaCollectDay(String hostId,String dbId,String quotaId);
	
	/**
	 * 查询未关闭的任务
	 * @return
	 */
	public List<OracleQuotaCollectDayDomain> listUnCompletedOracleQuotaCollectDay();
	
	
	/**
	 * 关闭任务
	 * @param id
	 */
	public void closeUnCompletedOracleQuota(String id);
	
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
	public List<OracleQuotaCollectDayDomain> listReportData(String dbId,String quotaId,Integer pageSize,String startDate,String endDate);
	
	
	/**
	 * 复合指标报表展示的数据
	 * @param quotaId
	 * @param pageSize
	 * @return
	 */
	public List<OracleQuotaCollectDayDomain> listComplexQuotaReportData(String dbId,String[] quotaId,Integer pageSize,String startDate,String endDate);

	/**
	 * 关闭未配置预警阈值的任务
	 */
	public void closeNoThresholdTasks();
	
	
	/**
	 * 返回1000行，未处理的数据
	 */
	public List<OracleQuotaCollectDayDomain> getOracleMonitorUnCompletedData();
	
	
	/**
	 * 查询未处理的磁盘数据
	 * @param id
	 * @return
	 */
	public OracleQuotaCollectDayDomain getOracleMonitorUnCompletedObj(String id);
	
	/**
	 * 根据ID获取数据
	 * @param id
	 * @return
	 */
	public OracleQuotaCollectDayDomain getOracleMonitorDataById(String id);
}
