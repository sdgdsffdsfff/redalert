/**   
* @Title: HostAlertDispalyParamDTO.java 
* @Package com.tyyd.ywpt.report.controller.alert.dto 
* @Description:  
* @author wangyu   
* @date 2014-10-30 下午3:06:00 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.controller.alert.dto;

/**
 * @author wangyu
 *
 */
public class HostAlertDispalyParamDTO {

	private String startDate;
	
	private String endDate;
	
	private String hostId;
	
	private Integer limitSize;

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the hostId
	 */
	public String getHostId() {
		return hostId;
	}

	/**
	 * @param hostId the hostId to set
	 */
	public void setHostId(String hostId) {
		this.hostId = hostId;
	}

	/**
	 * @return the limitSize
	 */
	public Integer getLimitSize() {
		return limitSize;
	}

	/**
	 * @param limitSize the limitSize to set
	 */
	public void setLimitSize(Integer limitSize) {
		this.limitSize = limitSize;
	}
	
	
	
}
