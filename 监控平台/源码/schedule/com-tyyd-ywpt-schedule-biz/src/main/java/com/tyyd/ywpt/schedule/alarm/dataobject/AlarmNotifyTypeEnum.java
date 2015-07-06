/**   
* @Title: AlarmNotifyTypeEnum.java 
* @Package com.tyyd.ywpt.schedule.alarm.dataobject 
* @Description:  
* @author wangyu   
* @date 2014-8-13 下午1:52:34 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.alarm.dataobject;

/**
 * @author wangyu
 *
 */
public enum AlarmNotifyTypeEnum {

	email(0,"邮件"),
	mixed(1,"邮件 + 短信");
	
	private String content;
	private Integer notifyType;
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the notifyType
	 */
	public Integer getNotifyType() {
		return notifyType;
	}
	/**
	 * @param notifyType the notifyType to set
	 */
	public void setNotifyType(Integer notifyType) {
		this.notifyType = notifyType;
	}
	/**
	 * @param notifyType
	 * @param content
	 */
	private AlarmNotifyTypeEnum(Integer notifyType, String content) {
		this.notifyType = notifyType;
		this.content = content;
	}
	
	
}
