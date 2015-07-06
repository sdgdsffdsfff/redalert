/**   
* @Title: AlertNotifyConfigDAOImpl.java 
* @Package com.tyyd.ywpt.dao.alert.recevie.impl 
* @Description:  
* @author wangyu   
* @date 2014-6-19 上午11:02:11 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.alert.recevie.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.admin.user.dataobject.UserDomain;
import com.tyyd.ywpt.dao.alert.recevie.AlertNotifyConfigDAO;
import com.tyyd.ywpt.dao.alert.recevie.dataobject.AlertNotifyConfigDomain;
import com.tyyd.ywpt.dao.base.dataobject.PageQuery;

/**
 * @author wangyu
 *
 */
public class AlertNotifyConfigDAOImpl extends TyydBaseDAO<List<AlertNotifyConfigDomain>> implements
		AlertNotifyConfigDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.alert.recevie.dataobject.AlertNotifyConfigDomain";

	@Override
	public void addNofiyConfig(AlertNotifyConfigDomain configDomain) {
		this.getSqlSessionTemplate().insert(context_space+".add_nofiy_config", configDomain);
	}

	@Override
	public PageQuery<List<AlertNotifyConfigDomain>> listAlertNotifyConfig(
			PageQuery<List<AlertNotifyConfigDomain>> pageQuery, String monitorId) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("monitorId", monitorId);
		return this.selectPages(context_space+".list_alert_notify_config", pageQuery, parameterMap);
	}

	@Override
	public void delNotifyConfig(String monitorId,Integer configType,String userId) {
		AlertNotifyConfigDomain configDomain = new AlertNotifyConfigDomain();
		configDomain.setMonitorId(monitorId);
		configDomain.setConfigType(configType);
		configDomain.setUserId(userId);
		this.getSqlSessionTemplate().update(context_space+".del_notify_config", configDomain);
	}


	@Override
	public AlertNotifyConfigDomain getAlertNotifyConfig(String monitorId,Integer configType,String userId) {
		AlertNotifyConfigDomain configDomain = new AlertNotifyConfigDomain();
		configDomain.setMonitorId(monitorId);
		configDomain.setConfigType(configType);
		configDomain.setUserId(userId);
		return this.getSqlSessionTemplate().selectOne(context_space+".get_alert_notify_config", configDomain);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.alert.recevie.AlertNotifyConfigDAO#listUserInfoByMonitorConfig(java.lang.String, java.lang.Integer)
	 */
	@Override
	public List<UserDomain> listUserInfoByMonitorConfig(
			String monitorId, Integer configType) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("monitorId", monitorId);
		parameterMap.put("configType", configType);
		return this.getSqlSessionTemplate().selectList(context_space + ".query_userinfo_by_monitorinfo", parameterMap);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.alert.recevie.AlertNotifyConfigDAO#listAlertNotifyMonitorPageQuery(com.tyyd.ywpt.dao.base.dataobject.PageQuery)
	 */
	@Override
	public PageQuery<List<AlertNotifyConfigDomain>> listAlertNotifyMonitorPageQuery(
			PageQuery<List<AlertNotifyConfigDomain>> pageQuery) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		return this.selectPages(context_space+".list_alert_notify_monitor_pagequery", pageQuery, parameterMap);
	}

}
