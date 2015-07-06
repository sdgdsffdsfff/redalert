/**   
* @Title: MonitorAlertRecordDAO.java 
* @Package com.tyyd.ywpt.dao.alert.record 
* @Description:  
* @author wangyu   
* @date 2014-10-16 上午10:32:17 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.alert.record;

import java.util.List;

import com.tyyd.ywpt.dao.alert.record.dataobject.MoitorAlertRecordDomain;
import com.tyyd.ywpt.dao.base.dataobject.PageQuery;

/**
 * @author wangyu
 *
 */
public interface MonitorAlertRecordDAO {

	/**
	 * 添加告警归类
	 * @param domain
	 */
	public void addMonitorAlertRecord(MoitorAlertRecordDomain domain);
	
	/**
	 * 删除被修复的告警
	 * @param id
	 */
	public void deleteMonitorAlertRecord(Long id);
	
	
	/**
	 * 某个监控机的所有告警归类列表
	 * @param monitorId
	 * @param configType
	 * @return
	 */
	public List<MoitorAlertRecordDomain> listMonitorAlertRecord(String monitorId,Integer configType,Integer noticeLevel);
	
	
	/**
	 * 判断是否已存在
	 * @param domain
	 * @return
	 */
	public boolean isExistMonitorAlertRecord(MoitorAlertRecordDomain domain);
	
	
	/**
	 * 根据监控类型做的总览信息
	 * @param monitorId
	 * @param configType
	 * @return
	 */
	public List<MoitorAlertRecordDomain> listMonitorPandectInfo(Integer configType);
	
	
	/**
	 * 主机问题
	 * @param pageQuery
	 * @param hostId
	 * @return
	 */
	public PageQuery<List<MoitorAlertRecordDomain>> listHostPageQuery(PageQuery<List<MoitorAlertRecordDomain>> pageQuery,String hostId,String startDate,String endDate);
	
	
	/**
	 * 数据库问题
	 * @param pageQuery
	 * @param dbId
	 * @param dbType
	 * @return
	 */
	public PageQuery<List<MoitorAlertRecordDomain>> listDbPageQuery(PageQuery<List<MoitorAlertRecordDomain>> pageQuery,String dbId,Integer dbType,String startDate,String endDate);
	
	/**
	 * 所有问题
	 * @param pageQuery
	 * @param dbId
	 * @param dbType
	 * @return
	 */
	public PageQuery<List<MoitorAlertRecordDomain>> listAllPageQuery(PageQuery<List<MoitorAlertRecordDomain>> pageQuery,String queryDay);
	
	
	/**
	 * 更新修改时间
	 * @param domain
	 */
	public void updateLastTime(MoitorAlertRecordDomain domain);
}
