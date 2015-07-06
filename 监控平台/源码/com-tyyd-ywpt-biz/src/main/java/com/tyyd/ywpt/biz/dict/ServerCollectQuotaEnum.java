/**   
* @Title: ServerCollectEnum.java 
* @Package com.tyyd.ywpt.biz.dict 
* @Description:  
* @author wangyu   
* @date 2014-7-10 上午10:30:17 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.dict;

import org.apache.commons.lang.StringUtils;

/**
 * @author wangyu
 *
 */
public enum ServerCollectQuotaEnum {

	CPU(1,"cpu"),
	MEMORY(2,"memory"),
	NETWORK(3,"network"),
	DISK(4,"disk");

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
	private ServerCollectQuotaEnum(Integer val, String text) {
		this.val = val;
		this.text = text;
	}
 
	public static String getEnum(Integer val){
		ServerCollectQuotaEnum[] enums = ServerCollectQuotaEnum.class.getEnumConstants();
		for(ServerCollectQuotaEnum oracle : enums){
			if(oracle.getVal().intValue() == val.intValue()){
				return oracle.getText();
			}
		}
		return StringUtils.EMPTY;
	}
	
	
}
