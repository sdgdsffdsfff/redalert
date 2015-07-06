/**   
* @Title: BaseLineReportFormatDataBO.java 
* @Package com.tyyd.ywpt.report.biz.quota.dto.baseline 
* @Description:  
* @author wangyu   
* @date 2015-3-24 上午11:12:17 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.quota.dto.baseline;

/**
 * @author wangyu
 *
 */
public class BaseLineReportFormatDataBO {

	
	/**
	 * 长度为3
	 * 索引 0 : X轴，日期时间戳
	 * 索引 1 : 数值，下轨
	 * 索引 2 : 数值，上轨
	 */
	private Object[] arrays;

	/**
	 * @return the arrays
	 */
	public Object[] getArrays() {
		return arrays;
	}

	/**
	 * @param arrays the arrays to set
	 */
	public void setArrays(Object[] arrays) {
		this.arrays = arrays;
	}
	
	
	
	
	
}
