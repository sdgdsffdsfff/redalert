/**   
* @Title: GeneralQuotaBO.java 
* @Package com.tyyd.ywpt.report.biz.quota.dto 
* @Description:  
* @author wangyu   
* @date 2014-9-3 下午5:37:38 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.quota.dto;

/**
 * @author wangyu
 *
 */
public class GeneralQuotaBO {

	private String quotaId;
	
	private String quotaName;
	
	private String metric;

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
	 * @return the quotaName
	 */
	public String getQuotaName() {
		return quotaName;
	}

	/**
	 * @param quotaName the quotaName to set
	 */
	public void setQuotaName(String quotaName) {
		this.quotaName = quotaName;
	}

	/**
	 * @return the metric
	 */
	public String getMetric() {
		return metric;
	}

	/**
	 * @param metric the metric to set
	 */
	public void setMetric(String metric) {
		this.metric = metric;
	}
	
	
	
}
