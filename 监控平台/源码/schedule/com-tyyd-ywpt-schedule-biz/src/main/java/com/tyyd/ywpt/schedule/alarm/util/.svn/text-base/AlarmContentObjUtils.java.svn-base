/**   
* @Title: AlarmContentObjUtils.java 
* @Package com.tyyd.ywpt.schedule.alarm.util 
* @Description:  
* @author wangyu   
* @date 2014-8-13 上午10:12:12 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.alarm.util;

import com.tyyd.ywpt.schedule.alarm.dataobject.AlaramContent;

/**
 * @author wangyu
 *
 */
public class AlarmContentObjUtils {

	private AlarmKeyReplaceUtils utils;
	
	/**
	 * 邮件
	 */
	private AlaramContent alaramContent;
	
	public AlaramContent getAlaramContent(){
		AlaramContent alarmContent = new AlaramContent();
		String mailTitle = utils.formatMsgText(alaramContent.getMailTitle());
		String mailContent = utils.formatMsgText(alaramContent.getMailContent());
		String monitorContent = utils.formatMsgText(alaramContent.getMonitorAlertContent());
		alarmContent.setMailTitle(mailTitle);
		alarmContent.setMailContent(mailContent);
		alarmContent.setMonitorAlertContent(monitorContent);
		return alarmContent;
	}
	
	/**
	 * @return the utils
	 */
	public AlarmKeyReplaceUtils getUtils() {
		return utils;
	}


	/**
	 * @param utils the utils to set
	 */
	public void setUtils(AlarmKeyReplaceUtils utils) {
		this.utils = utils;
	}

	/**
	 * @param alaramContent the alaramContent to set
	 */
	public void setAlaramContent(AlaramContent alaramContent) {
		this.alaramContent = alaramContent;
	}
	
	
}
