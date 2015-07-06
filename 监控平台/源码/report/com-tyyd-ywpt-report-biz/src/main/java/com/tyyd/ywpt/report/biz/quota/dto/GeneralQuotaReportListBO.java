/**   
* @Title: GeneralQuotaReportDataDTO.java 
* @Package com.tyyd.ywpt.report.biz.quota.dto 
* @Description:  
* @author wangyu   
* @date 2014-9-2 下午2:33:22 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.quota.dto;

import java.io.Serializable;

/**
 * @author wangyu
 *
 */
public class GeneralQuotaReportListBO implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6758632612069292493L;

	private Float quotaValue;
	
	private String gmtCreatedDate;
	
	
	private String id;

	/**
	 * @return the quotaValue
	 */
	public Float getQuotaValue() {
		return quotaValue;
	}

	/**
	 * @param quotaValue the quotaValue to set
	 */
	public void setQuotaValue(Float quotaValue) {
		this.quotaValue = quotaValue;
	}

	/**
	 * @return the gmtCreatedDate
	 */
	public String getGmtCreatedDate() {
		return gmtCreatedDate;
	}

	/**
	 * @param gmtCreatedDate the gmtCreatedDate to set
	 */
	public void setGmtCreatedDate(String gmtCreatedDate) {
		this.gmtCreatedDate = gmtCreatedDate;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
}
