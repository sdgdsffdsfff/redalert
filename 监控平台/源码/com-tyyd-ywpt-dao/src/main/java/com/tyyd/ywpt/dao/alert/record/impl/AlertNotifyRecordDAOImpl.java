/**   
* @Title: AlertNotifyRecordDAOImpl.java 
* @Package com.tyyd.ywpt.dao.alert.record.impl 
* @Description:  
* @author wangyu   
* @date 2014-6-19 下午1:07:10 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.alert.record.impl;

import java.util.List;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.alert.record.AlertNotifyRecordDAO;
import com.tyyd.ywpt.dao.alert.record.dataobject.AlertNotifyRecordDomain;

/**
 * @author wangyu
 *
 */
public class AlertNotifyRecordDAOImpl extends TyydBaseDAO<List<AlertNotifyRecordDomain>> implements
		AlertNotifyRecordDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.alert.record.dataobject.AlertNotifyRecordDomain";
	
	@Override
	public void addAlertNotifyRecord(AlertNotifyRecordDomain domain) {
		this.getSqlSessionTemplate().insert(context_space+".add_alert_notify_record", domain);
	}

	
	@Override
	public void updateAlertNotifyRecordById(String id) {
		this.getSqlSessionTemplate().update(context_space+".update_alert_notify_record_by_id", id);
	}

	
	@Override
	public List<AlertNotifyRecordDomain> queryAlertNotifyRecordByAlertId(
			String alertId) {
		return this.getSqlSessionTemplate().selectList(context_space+".query_alert_notify_record_by_alert_id", alertId);
	}

}
