/**   
* @Title: AlaramContent.java 
* @Package com.tyyd.ywpt.schedule.alarm.dataobject 
* @Description:  
* @author wangyu   
* @date 2014-8-13 上午10:10:05 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.alarm.dataobject;

/**
 * @author wangyu
 *
 */
public class AlaramContent {

	/**
	 * 邮件标题
	 */
	private String mailTitle;
	
	/**
	 * 邮件内容
	 */
	private String mailContent;
	
	
	/**
	 * 监控归类的内容
	 */
	private String monitorAlertContent;

	/**
	 * @return the mailTitle
	 */
	public String getMailTitle() {
		return mailTitle;
	}

	/**
	 * @param mailTitle the mailTitle to set
	 */
	public void setMailTitle(String mailTitle) {
		this.mailTitle = mailTitle;
	}

	/**
	 * @return the mailContent
	 */
	public String getMailContent() {
		return mailContent;
	}

	/**
	 * @param mailContent the mailContent to set
	 */
	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	/**
	 * @return the monitorAlertContent
	 */
	public String getMonitorAlertContent() {
		return monitorAlertContent;
	}

	/**
	 * @param monitorAlertContent the monitorAlertContent to set
	 */
	public void setMonitorAlertContent(String monitorAlertContent) {
		this.monitorAlertContent = monitorAlertContent;
	}

	
	
}
