/**   
* @Title: PieReportOrgFormatDataBO.java 
* @Package com.tyyd.ywpt.report.biz.quota.dto.pie 
* @Description:  
* @author wangyu   
* @date 2015-6-4 下午5:09:13 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.quota.dto.pie;

import java.util.List;

/**
 * @author wangyu
 *
 */
public class PieReportOrgFormatDataBO {

	/**
	 * data
	 */
	private List<PieJsonDataFormatBO> dataList;
	
	/**
	 * 大类
	 */
	private String[] categories;

	/**
	 * @return the dataList
	 */
	public List<PieJsonDataFormatBO> getDataList() {
		return dataList;
	}

	/**
	 * @param dataList the dataList to set
	 */
	public void setDataList(List<PieJsonDataFormatBO> dataList) {
		this.dataList = dataList;
	}

	/**
	 * @return the categories
	 */
	public String[] getCategories() {
		return categories;
	}

	/**
	 * @param categories the categories to set
	 */
	public void setCategories(String[] categories) {
		this.categories = categories;
	}

	
}
