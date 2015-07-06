/**   
* @Title: MultipleDataDTO.java 
* @Package com.tyyd.ywpt.report.controller.quota.dto.multi 
* @Description:  
* @author wangyu   
* @date 2014-9-5 上午10:52:24 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.controller.quota.dto.multi;

import java.util.List;

/**
 * @author wangyu
 *
 */
public class MultipleDataDTO {

	/**
	 * series名
	 */
	private String name;
	
	/**
	 * 数据
	 */
	private List<Object[]> data;

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
	public List<Object[]> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<Object[]> data) {
		this.data = data;
	}

	
	
}
