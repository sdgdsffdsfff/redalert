/**   
* @Title: SysTypeEnum.java 
* @Package com.tyyd.ywpt.biz.dict 
* @Description:  
* @author wangyu   
* @date 2014-7-9 上午9:09:31 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.dict;

import org.apache.commons.lang.StringUtils;

/**
 * @author wangyu
 *
 */
public enum SysTypeEnum {
	
	server(1,"主机"),
	Oracle(2,"Oracle"),
	MySQL(3,"MySQL"),
	KEEPALIVED(4,"keepAlived");

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
	private SysTypeEnum(Integer val, String text) {
		this.val = val;
		this.text = text;
	}
	
	public static String getEnum(Integer val){
		SysTypeEnum[] enums = SysTypeEnum.class.getEnumConstants();
		for(SysTypeEnum sysType : enums){
			if(sysType.getVal().intValue() == val.intValue()){
				return sysType.getText();
			}
		}
		return StringUtils.EMPTY;
	}
}
