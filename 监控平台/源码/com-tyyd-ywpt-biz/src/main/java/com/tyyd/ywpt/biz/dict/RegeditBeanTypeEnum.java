/**   
* @Title: RegeditBeanTypeEnum.java 
* @Package com.tyyd.ywpt.biz.dict 
* @Description:  
* @author wangyu   
* @date 2014-11-17 上午9:59:00 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.dict;

import org.apache.commons.lang.StringUtils;

/**
 * @author wangyu
 *
 */
public enum RegeditBeanTypeEnum {

	server(1,"主机","host_"),
	Oracle(2,"Oracle","oracle_"),
	MySQL(3,"MySQL","mysql_"),
	alert_oracle(4,"oracle告警日志","alert_ora_"),
	alert_mysql(5,"mysql告警日志","alert_mysql_"),
	datasource_oracle(6,"oracle 数据源","datasource_oracle_"),
	datasource_mysql(7,"mysql 数据源","datasource_mysql_"),
	keepalived(8,"keepalived主机","keepalived_"),
	alert_keepalived(9,"alert keepalived日志告警","alert_keepalived_");
	

	private Integer val;
	
	private String text;
	
	private String prefix;

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
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @param prefix the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
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
	private RegeditBeanTypeEnum(Integer val, String text,String prefix) {
		this.val = val;
		this.text = text;
		this.prefix = prefix;
	}
	
	
	public static String getEnum(Integer val){
		RegeditBeanTypeEnum[] enums = RegeditBeanTypeEnum.class.getEnumConstants();
		for(RegeditBeanTypeEnum sysType : enums){
			if(sysType.getVal().intValue() == val.intValue()){
				return sysType.getPrefix();
			}
		}
		return StringUtils.EMPTY;
	}
	
}
