/**   
* @Title: MultipleGeneralMonitorDTO.java 
* @Package com.tyyd.ywpt.report.controller.quota.dto 
* @Description:  
* @author wangyu   
* @date 2014-9-5 上午10:48:46 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.controller.quota.dto.multi;

/**
 * @author wangyu
 *
 */
public class MultipleGeneralReportDataDTO {

	/**
	 * 多条线
	 */
	private MultipleDataDTO[] seriesData;
	
	/**
	 * 报表名称
	 */
	private String reportName;

	/**
	 * @return the seriesData
	 */
	public MultipleDataDTO[] getSeriesData() {
		return seriesData;
	}

	/**
	 * @param seriesData the seriesData to set
	 */
	public void setSeriesData(MultipleDataDTO[] seriesData) {
		this.seriesData = seriesData;
	}

	/**
	 * @return the reportName
	 */
	public String getReportName() {
		return reportName;
	}

	/**
	 * @param reportName the reportName to set
	 */
	public void setReportName(String reportName) {
		this.reportName = reportName;
	} 
	
	
	
}
