/**   
* @Title: AlertRecordDAO.java 
* @Package com.tyyd.ywpt.dao.alert.record 
* @Description:  
* @author wangyu   
* @date 2014-6-19 下午12:04:18 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.alert.record;

import java.util.List;

import com.tyyd.ywpt.dao.alert.record.dataobject.AlertRecordDomain;
import com.tyyd.ywpt.dao.base.dataobject.PageQuery;

/**
 * @author wangyu
 *
 */
public interface AlertRecordDAO {

	/**
	 * 新增告警记录
	 * @param domain
	 */
	public void addAlertRecord(AlertRecordDomain domain);
	
	/**
	 * 更新告警通知的任务
	 * @param id
	 */
	public void updateAlertRecordTaskStatus(Long id);
	
	
	/**
	 * 查询某一天的告警日志
	 * 分系统查询（db，主机）
	 * @param pageQuery
	 * @param queryDate
	 * @return
	 */
	public PageQuery<List<AlertRecordDomain>> listAlertRecord(PageQuery<List<AlertRecordDomain>> pageQuery,
				String startQueryDate,String endQueryDate,String hostId,String dbId,Integer sysType,Integer isCompleted);
	
	
	/**
	 * 查询告警记录
	 * @param id
	 * @return
	 */
	public AlertRecordDomain getAlertRecordById(String id);
	
	
	/**
	 * 返回有限制条数的未完成任务
	 * * @return
	 */
	public List<AlertRecordDomain> getUnCompleteTaskLimit(Integer limitSize);
	
	
	
	/**
	 * 主机类的预警
	 * @param hostId
	 * @param limitSize
	 * @return
	 */
	public List<AlertRecordDomain> listAlertRecordByHost(String hostId,Integer limitSize,String startDate,String endDate);
	

	/**
	 * 数据库类的预警
	 * @param dbId
	 * @param dbType
	 * @param limitSize
	 * @return
	 */
	public List<AlertRecordDomain> listAlertRecordByDb(String dbId,Integer dbType,Integer limitSize,String startDate,String endDate);
	
	
	/**
	 * 预警总览
	 * @param startDate
	 * @param limitSize
	 * @return
	 */
	public List<AlertRecordDomain> listAlertRecordByAllLooking(String startDate,Integer limitSize);
	
	
	/**
	 * 获取限制数已内的创建日期
	 * @param limitSize
	 * @return
	 */
	public String getLimitGmtCreatedDate(Integer limitSize);
	
}
