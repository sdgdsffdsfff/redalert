/**   
* @Title: MySQLAwrReviewEnum.java 
* @Package com.tyyd.ywpt.report.biz.stat.mysql.dto 
* @Description:  
* @author wangyu   
* @date 2015-5-20 下午3:18:25 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.stat.mysql.dto;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * @author wangyu
 *
 */
public enum MySQLAwrReviewEnum {

	execCount("execCount","总执行次数","exec_count"),
	queryTimeSum("queryTimeSum","SQL执行消耗总时间 ","query_time_sum"),
	lockTimeSum("lockTimeSum","锁等待总时间","lock_time_sum"),
	rowsSentSum("rowsSentSum","传输数据行数","rows_sent_sum"),
	rowsExaminedSum("rowsExaminedSum","扫描数据行数","rows_examined_sum"),
	rowsAffectedSum("rowsAffectedSum","改变数据行数","rows_affected_sum"),
	fullScanCnt("fullScanCnt","全表扫描次数","full_scan_cnt"),
	fullJoinCnt("fullJoinCnt","全表连接次数","full_join_cnt"),
	tmpTableCnt("tmpTableCnt","使用临时表数量","tmp_table_cnt"),
	tmpTableOnDiskCnt("tmpTableOnDiskCnt","使用硬盘上临时表数量","tmp_table_on_disk_cnt"),
	fileSortCnt("fileSortCnt","使用文件排序次数","filesort_cnt"),
	filesortOnDistCnt("filesortOnDistCnt","硬盘上文件排序次数","filesort_on_disk_cnt");
	
	
	private String filed;
	private String comment;
	private String dbCloumn;
	
	
	/**
	 * @return the dbCloumn
	 */
	public String getDbCloumn() {
		return dbCloumn;
	}
	/**
	 * @param dbCloumn the dbCloumn to set
	 */
	public void setDbCloumn(String dbCloumn) {
		this.dbCloumn = dbCloumn;
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
	 * @param filed
	 * @param comment
	 */
	private MySQLAwrReviewEnum(String filed, String comment,String dbCloumn) {
		this.filed = filed;
		this.comment = comment;
		this.dbCloumn = dbCloumn;
	}
	
	/**
	 * 获取所有的key
	 * @return
	 */
	public static Map<String,String> keys(){
		MySQLAwrReviewEnum[] enums = MySQLAwrReviewEnum.class.getEnumConstants();
		Map<String,String> result = new HashMap<String,String>();
		for(MySQLAwrReviewEnum awr : enums){
			result.put(awr.getFiled(), awr.getComment());
		}
		return result;
	}
	
	
	public static String getColumn(String enumKey){
		if(StringUtils.isBlank(enumKey)){
			return StringUtils.EMPTY;
		}
		
		MySQLAwrReviewEnum[] enums = MySQLAwrReviewEnum.class.getEnumConstants();
		for(MySQLAwrReviewEnum oracle : enums){
			if(enumKey.equals(oracle.getFiled())){
				return oracle.getDbCloumn();
			}
		}
		return StringUtils.EMPTY;
	}
}
