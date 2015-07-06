/**   
* @Title: AlertTypeEnum.java 
* @Package com.tyyd.ywpt.dao.alert.logalert.dataobject 
* @Description:  
* @author wangyu   
* @date 2014-8-27 上午10:17:19 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.alert.logalert.dataobject;

import org.apache.commons.lang.StringUtils;

/**
 * @author wangyu
 *
 */
public enum AlertTypeEnum {

	DB_LOG_ALERT(1,"数据库日志告警"),
	MYSQL_STAT(2,"MySQL Stat告警"),
	LONG_TIME_SQL(3,"Oracle Long Time SQL告警"),
	LONG_TIME_TRANS(4,"Oracle Long Time Trans告警"),
	HIGH_LEVEL_INDEX(5,"Oracle High Level Index告警"),
	MYSQL_SLAVE_STOP(6,"mysql slave 告警"),
	ORACLE_MORE_TRANS(7,"oracle more trans告警"),
	ORACLE_TRANS_UNDO(8,"oracle trans undo告警"),
	MYSQL_LONG_SQL(10,"mysql long sql告警"),
	MYSQL_RESULT_DATA_SQL(11,"mysql返回数据过多SQL告警"),
	MYSQL_EXAMINED_SQL(12,"mysql扫描数据过多SQL告警"),
	KEEPALIVED_PROCESS(20,"keepalived进程异常告警"),
	KEEPALIVED_ALERT(21,"keepalived日志告警");
	
	
	private Integer alertType;
	
	private String alertTypeContent;

	/**
	 * @return the alertType
	 */
	public Integer getAlertType() {
		return alertType;
	}

	/**
	 * @param alertType the alertType to set
	 */
	public void setAlertType(Integer alertType) {
		this.alertType = alertType;
	}

	/**
	 * @return the alertTypeContent
	 */
	public String getAlertTypeContent() {
		return alertTypeContent;
	}

	/**
	 * @param alertTypeContent the alertTypeContent to set
	 */
	public void setAlertTypeContent(String alertTypeContent) {
		this.alertTypeContent = alertTypeContent;
	}

	/**
	 * @param alertType
	 * @param alertTypeContent
	 */
	private AlertTypeEnum(Integer alertType, String alertTypeContent) {
		this.alertType = alertType;
		this.alertTypeContent = alertTypeContent;
	}
	
	
	public static String getEnum(Integer val){
		if(val == null)
			return StringUtils.EMPTY;
		
		AlertTypeEnum[] enums = AlertTypeEnum.class.getEnumConstants();
		for(AlertTypeEnum statType : enums){
			if(statType.getAlertType().intValue() == val.intValue()){
				return statType.getAlertTypeContent();
			}
		}
		return StringUtils.EMPTY;
	}
}
