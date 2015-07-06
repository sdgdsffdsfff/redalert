/**   
* @Title: AlarmUserInfoManager.java 
* @Package com.tyyd.ywpt.schedule.notify 
* @Description:  
* @author wangyu   
* @date 2014-8-13 下午5:47:14 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.notify;

import com.tyyd.ywpt.schedule.notify.dataobject.AlarmUserDTO;


/**
 * @author wangyu
 *
 */
public interface AlarmUserInfoManager {

	/**
	 * 重刷
	 */
	public void refreshAlarmUserMap();
	
	
	/**
	 * 根据monitorId查询用户信息
	 * @param monitorId
	 * @param configType
	 * @return
	 */
	public AlarmUserDTO getUserInfoByMonitor(String monitorId,Integer configType);
	
}
