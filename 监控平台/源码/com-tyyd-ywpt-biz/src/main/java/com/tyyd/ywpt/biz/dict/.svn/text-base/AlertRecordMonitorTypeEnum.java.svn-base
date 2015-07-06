/**   
* @Title: AlertRecordMonitorTypeEnum.java 
* @Package com.tyyd.ywpt.biz.dict 
* @Description:  
* @author wangyu   
* @date 2014-10-15 下午5:25:51 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.dict;

import org.apache.commons.lang.StringUtils;

/**
 * @author wangyu
 *
 */
public enum AlertRecordMonitorTypeEnum {
	
	heart_beat(1,"心跳检查"),
	quota(2,"指标"),
	disk(3,"磁盘"),
	tbs(4,"表空间"),
	alert(5,"告警日志");
	
	private Integer val;
	
	private String text;

	/**
	 * @return the val
	 */
	public Integer getVal() {
		return val;
	}

	/**
	 * @param val the val to set
	 */
	public void setVal(Integer val) {
		this.val = val;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @param val
	 * @param text
	 */
	private AlertRecordMonitorTypeEnum(Integer val, String text) {
		this.val = val;
		this.text = text;
	}
	
	public static String getEnum(Integer val){
		AlertRecordMonitorTypeEnum[] enums = AlertRecordMonitorTypeEnum.class.getEnumConstants();
		for(AlertRecordMonitorTypeEnum statType : enums){
			if(statType.getVal().intValue() == val.intValue()){
				return statType.getText();
			}
		}
		return StringUtils.EMPTY;
	}
}
