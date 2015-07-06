/**   
* @Title: MySQLStatExtendsEnum.java 
* @Package com.tyyd.ywpt.biz.dict 
* @Description:  
* @author wangyu   
* @date 2014-12-10 下午3:01:18 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.dict;

/**
 * @author wangyu
 *
 */
public enum MySQLStatExtendsEnum {
	
	THREADS_CONNECTED("18","己建立连接数"),
	THREADS_RUNNING("19","active连接数"),
	COM_SELECT("61","COM_SELECT"),
	COM_INSERT("60","Com_insert"),
	COM_UPDATE("62","Com_update"),
	COM_DELETE("63","Com_delete"),
	COM_REPLACE("64","Com_replace"),
	BYTES_RECEIVED("43","接受流量/秒"),
	BYTES_SENT("42","发送流量/秒");
	
	

	private String quotaId;
	
	private String quotaRemark;

	/**
	 * @return the quotaId
	 */
	public String getQuotaId() {
		return quotaId;
	}

	/**
	 * @param quotaId the quotaId to set
	 */
	public void setQuotaId(String quotaId) {
		this.quotaId = quotaId;
	}

	/**
	 * @return the quotaRemark
	 */
	public String getQuotaRemark() {
		return quotaRemark;
	}

	/**
	 * @param quotaRemark the quotaRemark to set
	 */
	public void setQuotaRemark(String quotaRemark) {
		this.quotaRemark = quotaRemark;
	}

	/**
	 * @param quotaId
	 * @param quotaRemark
	 */
	private MySQLStatExtendsEnum(String quotaId, String quotaRemark) {
		this.quotaId = quotaId;
		this.quotaRemark = quotaRemark;
	}
	
	
	
}
