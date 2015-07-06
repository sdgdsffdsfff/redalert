/**   
* @Title: TogetherStatTypeEnum.java 
* @Package com.tyyd.ywpt.biz.dict 
* @Description:  
* @author wangyu   
* @date 2014-9-23 上午10:57:28 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.dict;

import org.apache.commons.lang.StringUtils;

/**
 * @author wangyu
 *
 */
public enum TogetherStatTypeEnum {

	HIS(0,"历史迁移"),
	HOUR(1,"4小时任务"),
	DAY(2,"日任务"),
	WEEK(3,"周任务"),
	MONTH(4,"月任务");
	
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
	private TogetherStatTypeEnum(Integer val, String text) {
		this.val = val;
		this.text = text;
	}
	
	public static String getEnum(Integer val){
		TogetherStatTypeEnum[] enums = TogetherStatTypeEnum.class.getEnumConstants();
		for(TogetherStatTypeEnum statType : enums){
			if(statType.getVal().intValue() == val.intValue()){
				return statType.getText();
			}
		}
		return StringUtils.EMPTY;
	}
}
