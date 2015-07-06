/**   
* @Title: MultipleDataDTO.java 
* @Package com.tyyd.ywpt.report.controller.quota.dto.multi 
* @Description:  
* @author wangyu   
* @date 2014-9-5 上午10:52:24 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.quota.dto.multi;

import java.util.List;

/**
 * @author wangyu
 *
 */
public class MultipleDataBO {

	/**
	 * series名
	 */
	private String name;
	
	
	/**
	 * yAxis的顺序,从1开始
	 */
	private int yAxis;
	
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 数据
	 */
	private List<ReportOrgFormatDataBO> data;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the data
	 */
	public List<ReportOrgFormatDataBO> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<ReportOrgFormatDataBO> data) {
		this.data = data;
	}

	/**
	 * @return the yAxis
	 */
	public int getyAxis() {
		return yAxis;
	}

	/**
	 * @param yAxis the yAxis to set
	 */
	public void setyAxis(int yAxis) {
		this.yAxis = yAxis;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	
}
