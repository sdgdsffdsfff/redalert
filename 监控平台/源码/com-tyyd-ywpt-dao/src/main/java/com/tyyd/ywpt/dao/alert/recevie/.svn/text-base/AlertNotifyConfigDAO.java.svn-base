/**   
* @Title: AlertNotifyConfigDAO.java 
* @Package com.tyyd.ywpt.dao.alert.recevie 
* @Description:  
* @author wangyu   
* @date 2014-6-19 上午10:37:36 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.alert.recevie;

import java.util.List;

import com.tyyd.ywpt.dao.admin.user.dataobject.UserDomain;
import com.tyyd.ywpt.dao.alert.recevie.dataobject.AlertNotifyConfigDomain;
import com.tyyd.ywpt.dao.base.dataobject.PageQuery;

/**
 * @author wangyu
 *
 */
public interface AlertNotifyConfigDAO {

	/**
	 * 告警通知配置
	 * @param configDomain
	 */
	public void addNofiyConfig(AlertNotifyConfigDomain configDomain);
	
	/**
	 * 某个监控的预警人列表
	 * @param pageQuery
	 * @param monitorId
	 * @return
	 */
	public PageQuery<List<AlertNotifyConfigDomain>> listAlertNotifyConfig(PageQuery<List<AlertNotifyConfigDomain>> pageQuery,String monitorId);
	
	/**
	 * 删除通知人
	 * @param notifyConfigId
	 */
	public void delNotifyConfig(String monitorId,Integer configType,String userId);
	
	/**
	 * 获取告警人
	 * @param notifyConfigId
	 * @return
	 */
	public AlertNotifyConfigDomain getAlertNotifyConfig(String monitorId,Integer configType,String userId);
	
	
	/**
	 * 查询监控机下的预警用户
	 * @param monitorId
	 * @param configType
	 * @return
	 */
	public List<UserDomain> listUserInfoByMonitorConfig(String monitorId,Integer configType);
	
	/**
	 * 获取所有的预警主机列表
	 * @param pageQuery
	 * @return
	 */
	public PageQuery<List<AlertNotifyConfigDomain>> listAlertNotifyMonitorPageQuery(PageQuery<List<AlertNotifyConfigDomain>> pageQuery);
}
