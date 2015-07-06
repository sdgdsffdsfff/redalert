/**   
* @Title: SQLStatTypeEnum.java 
* @Package com.tyyd.ywpt.biz.dict 
* @Description:  
* @author wangyu   
* @date 2014-12-3 下午3:36:56 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.dict;

import org.apache.commons.lang.StringUtils;

/**
 * @author wangyu
 *
 */
public enum SQLStatTypeEnum {
	
	executes("executes","执行次数"),
	disk_reads_total("disk_reads_total","物理读总计"),
	disk_reads_seconds("disk_reads_seconds","每次执行的物理读"),
	buffer_gets_total("buffer_gets_total","逻辑读总计"),
	buffer_gets_seconds("buffer_gets_seconds","每次执行逻辑读"),
	memory("memory","内存使用合计"),
	parse_total("parse_total","解析次数合计"),
	parse_seconds("parse_seconds","平均解析次数"),
	elapsed_time_total("elapsed_time_total","执行时间合计"),
	elapsed_time_seconds("elapsed_time_seconds","每次的执行时间");
	
	private String key;
	
	private String text;

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
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
	 * @param key
	 * @param text
	 */
	private SQLStatTypeEnum(String key, String text) {
		this.key = key;
		this.text = text;
	}
	
	
	public static boolean isExists(String key){
		if(StringUtils.isBlank(key)){
			return Boolean.FALSE;
		}
		
		SQLStatTypeEnum[] enums = SQLStatTypeEnum.class.getEnumConstants();
		for(SQLStatTypeEnum sysType : enums){
			if(sysType.getKey().equals(key)){
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
		
	}
	
	
	
	public static String getEnum(String key){
		if(StringUtils.isBlank(key)){
			return StringUtils.EMPTY;
		}
		
		SQLStatTypeEnum[] enums = SQLStatTypeEnum.class.getEnumConstants();
		for(SQLStatTypeEnum sysType : enums){
			if(sysType.getKey().equals(key)){
				return sysType.getText();
			}
		}
		return StringUtils.EMPTY;
	}
}
