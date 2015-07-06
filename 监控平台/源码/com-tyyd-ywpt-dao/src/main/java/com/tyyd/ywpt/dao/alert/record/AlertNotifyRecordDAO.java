/**   
* @Title: AlertNotifyRecordDAO.java 
* @Package com.tyyd.ywpt.dao.alert.record 
* @Description:  
* @author wangyu   
* @date 2014-6-19 下午12:17:06 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.alert.record;

import java.util.List;

import com.tyyd.ywpt.dao.alert.record.dataobject.AlertNotifyRecordDomain;

/**
 * @author wangyu
 *
 */
public interface AlertNotifyRecordDAO {

	/**
	 * 添加告警通知人记录
	 * @param domain
	 */
	public void addAlertNotifyRecord(AlertNotifyRecordDomain domain);
	
	/**
	 * 更新任务状态
	 * @param id
	 */
	public void updateAlertNotifyRecordById(String id);
	
	/**
	 * 查询接收报警的人
	 * @param alertId
	 * @return
	 */
	public List<AlertNotifyRecordDomain> queryAlertNotifyRecordByAlertId(String alertId);
	
}
