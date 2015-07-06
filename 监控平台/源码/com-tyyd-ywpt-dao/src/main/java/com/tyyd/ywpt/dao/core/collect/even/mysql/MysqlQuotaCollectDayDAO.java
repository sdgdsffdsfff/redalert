/**   
* @Title: MysqlQuotaCollectDayDAO.java 
* @Package com.tyyd.ywpt.dao.core.collect.even.mysql 
* @Description:  
* @author wangyu   
* @date 2014-6-25 上午10:51:34 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.even.mysql;

import java.util.List;

import com.tyyd.ywpt.dao.core.collect.even.mysql.dataobject.MysqlQuotaCollectDayDomain;

/**
 * @author wangyu
 *
 */
public interface MysqlQuotaCollectDayDAO {

	
	/**
	 * 添加MySQL指标的采集数据
	 * @param domain
	 */
	public void addMysqlQuotaCollectDay(MysqlQuotaCollectDayDomain domain);
	
	/**
	 * 迁移历史数据，预留接口
	 * @param startDate
	 * @param endDate
	 */
	public void moveMysqlQuotaCollectDay(String startDate,String endDate);
	
	
	/**
	 * 指标列表
	 * @param startDate
	 * @param endDate
	 * @param hostId
	 * @param dbId
	 * @param quotaId
	 * @return
	 */
	public List<MysqlQuotaCollectDayDomain> listMysqlQuotaCollectDay(String startDate,String endDate,String hostId,String dbId,String quotaId);
	
	
	/**
	 * 上一次采集值
	 * @param hostId
	 * @param dbId
	 * @param quotaId
	 * @return
	 */
	public MysqlQuotaCollectDayDomain getLastMysqlQuotaCollectDay(String hostId,String dbId,String quotaId);
	
	
	/**
	 * 收集未处理的任务
	 * @return
	 */
	public List<MysqlQuotaCollectDayDomain> listUnCompletedMySQLQuotaCollectData();
	
	/**
	 * 关闭已经处理的任务
	 * @param id
	 */
	public void closeUnCompletedTask(String id);
	
	
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
	public List<MysqlQuotaCollectDayDomain> listReportData(String dbId,String quotaId,Integer pageSize,String startDate,String endDate);
	
	
	/**
	 * 复合指标报表展示的数据
	 * @param quotaId
	 * @param pageSize
	 * @return
	 */
	public List<MysqlQuotaCollectDayDomain> listComplexQuotaReportData(String dbId,String[] quotaId,Integer pageSize,String startDate,String endDate);
	
	
	/**
	 * MySQL命中率的特殊算法
	 * @param dbId
	 * @param pageSize
	 * @return
	 */
	public List<MysqlQuotaCollectDayDomain> listHitsQuotaData(String dbId,Integer pageSize,String startDate,String endDate);

	/**
	 * 关闭未配置预警阈值的任务
	 */
	public void closeNoThresholdTasks();
	
	
	/**
	 * 获取不需要计算的指标值
	 * @param dbId
	 * @param quotaId
	 */
	public Float getNoCalQuotaValue(String dbId,String quotaId);
	
	
	/**
	 * 获取需要计算的指标值
	 * @param dbId
	 * @param quotaId
	 */
	public Float getNeedCalQuotaValue(String dbId,String quotaId);
	
	
	/**
	 * 返回1000行，未处理的数据
	 */
	public List<MysqlQuotaCollectDayDomain> getMySQLMonitorUnCompletedData();
	
	
	/**
	 * 查询未处理的磁盘数据
	 * @param id
	 * @return
	 */
	public MysqlQuotaCollectDayDomain getMySQLMonitorUnCompletedObj(String id);
	
	/**
	 * 根据ID获取数据
	 * @param id
	 * @return
	 */
	public MysqlQuotaCollectDayDomain getMysqlMonitorDataById(String id);
	
}
