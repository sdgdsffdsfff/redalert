/**   
* @Title: OracleCollectQuotaEnum.java 
* @Package com.tyyd.ywpt.biz.dict 
* @Description:  
* @author wangyu   
* @date 2014-7-10 上午10:44:46 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.dict;

import org.apache.commons.lang.StringUtils;

/**
 * @author wangyu
 *
 */
public enum OracleCollectQuotaEnum {

	EXEC_TRANS(1,"exec_trans"),
	IO(2,"IO"),
	LATENCY(3,"latency"),
	NETWORK(4,"network"),
	MEMORY_PROCESS(5,"memory_process");
	

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
	private OracleCollectQuotaEnum(Integer val, String text) {
		this.val = val;
		this.text = text;
	}
 

	public static String getEnum(Integer val){
		OracleCollectQuotaEnum[] enums = OracleCollectQuotaEnum.class.getEnumConstants();
		for(OracleCollectQuotaEnum oracle : enums){
			if(oracle.getVal().intValue() == val.intValue()){
				return oracle.getText();
			}
		}
		return StringUtils.EMPTY;
	}
	
}
