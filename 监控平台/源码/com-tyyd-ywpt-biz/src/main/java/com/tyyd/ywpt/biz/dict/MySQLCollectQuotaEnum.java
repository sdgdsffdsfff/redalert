/**   
* @Title: MySQLCollectQuotaEnum.java 
* @Package com.tyyd.ywpt.biz.dict 
* @Description:  
* @author wangyu   
* @date 2014-7-10 上午10:34:16 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.dict;

import org.apache.commons.lang.StringUtils;

/**
 * @author wangyu
 *
 */
public enum MySQLCollectQuotaEnum {

	SESSION(1,"session"),
	QUERIES(2,"queries"),
	SELECT(3,"select"),
	SORT(4,"sort"),
	INNODB_ROW(5,"innodb_row"),
	INNODB_BUFFER(6,"innodb_buffer"),
	EXEC_TRANS(7,"exec_trans"),
	NETWORK(8,"network"),
	INNODB_IO(9,"innodb_io"),
	PROCESSLIST(10,"processlist");
	

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
	private MySQLCollectQuotaEnum(Integer val, String text) {
		this.val = val;
		this.text = text;
	}
 
	public static String getEnum(Integer val){
		MySQLCollectQuotaEnum[] enums = MySQLCollectQuotaEnum.class.getEnumConstants();
		for(MySQLCollectQuotaEnum oracle : enums){
			if(oracle.getVal().intValue() == val.intValue()){
				return oracle.getText();
			}
		}
		return StringUtils.EMPTY;
	}
	
}
