/**   
* @Title: OraclePerformanceTopSQLEnum.java 
* @Package com.tyyd.ywpt.report.biz.stat.oracle.performace.impl 
* @Description:  
* @author wangyu   
* @date 2015-5-26 下午3:30:44 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.stat.oracle.performace.impl;

import org.apache.commons.lang.StringUtils;

/**
 * @author wangyu
 *
 */
public enum OraclePerformanceTopSQLEnum {

	fetchesDelta("fetchesDelta","SQL取数据的次数","fetches_delta"),
	sortsDelta("sortsDelta","排序次数","sorts_delta"),
	executionsDelta("executionsDelta","执行次数","executions_delta"),
	parseCallsDelta("parseCallsDelta","解析次数","parse_calls_delta"),
	diskReadsDelta("diskReadsDelta","物理读块数","disk_reads_delta"),
	bufferGetsDelta("bufferGetsDelta","逻辑读块数","buffer_gets_delta"),
	rowsProcessedDelta("rowsProcessedDelta","返回行数","rows_processed_delta"),
	cpuTimeDelta("cpuTimeDelta","CPU时间","cpu_time_delta"),
	elapsedTimeDelta("elapsedTimeDelta","执行时间","elapsed_time_delta"),
	iowaitDelta("iowaitDelta","IO等待时间","iowait_delta"),
	clwaitDelta("clwaitDelta","集群相关等待时间","clwait_delta"),
	apwaitDelta("apwaitDelta","应用程序相关等待时间","apwait_delta"),
	ccwaitDelta("ccwaitDelta","并发相关等待时间","ccwait_delta");
	
	private String filed;
	private String comment;
	private String columnKey;
	/**
	 * @param filed
	 * @param comment
	 * @param columnKey
	 */
	private OraclePerformanceTopSQLEnum(String filed, String comment,
			String columnKey) {
		this.filed = filed;
		this.comment = comment;
		this.columnKey = columnKey;
	}
	/**
	 * @return the filed
	 */
	public String getFiled() {
		return filed;
	}
	/**
	 * @param filed the filed to set
	 */
	public void setFiled(String filed) {
		this.filed = filed;
	}
	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * @return the columnKey
	 */
	public String getColumnKey() {
		return columnKey;
	}
	/**
	 * @param columnKey the columnKey to set
	 */
	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}
	
	
	public static String getColumn(String enumKey){
		if(StringUtils.isBlank(enumKey)){
			return StringUtils.EMPTY;
		}
		
		OraclePerformanceTopSQLEnum[] enums = OraclePerformanceTopSQLEnum.class.getEnumConstants();
		for(OraclePerformanceTopSQLEnum oracle : enums){
			if(enumKey.equals(oracle.getFiled())){
				return oracle.getColumnKey();
			}
		}
		return StringUtils.EMPTY;
	}
}
