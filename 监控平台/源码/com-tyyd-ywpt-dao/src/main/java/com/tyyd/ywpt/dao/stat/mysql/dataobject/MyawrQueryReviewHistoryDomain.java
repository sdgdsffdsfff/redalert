/**   
* @Title: MyawrQueryReviewHistoryDomain.java 
* @Package com.tyyd.ywpt.dao.stat.mysql.dataobject 
* @Description:  
* @author wangyu   
* @date 2015-5-19 上午10:00:16 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.stat.mysql.dataobject;

import java.util.Date;

/**
 * @author wangyu
 *
 */
public class MyawrQueryReviewHistoryDomain {

	
	/**
	 * 数据库ID
	 * 同表b_db_config的db_id列
	 */
	private String dbMax;
	
	
	/**
	 * SQL的MD5
	 */
	private String checkSum;
	
	
	/**
	 * 最早执行
	 */
	private Date tsMin;
	
	
	/**
	 * 最晚执行
	 */
	private Date tsMax;
	
	
	/**
	 * SQL样板
	 */
	private String sample;
	
	
	/**
	 * 总执行次数
	 */
	private Float execCount;
	
	
	/**
	 * SQL执行消耗总时间 
	 */
	private Float queryTimeSum;
	
	
	/**
	 * 锁等待总时间
	 */
	private Float lockTimeSum;
	
	
	/**
	 * 传输数据行数
	 */
	private Float rowsSentSum;
	
	
	/**
	 * 扫描数据行数
	 */
	private Float rowsExaminedSum;
	
	
	/**
	 * 改变数据行数
	 */
	private Float rowsAffectedSum;
	
	
	/**
	 * 全表扫描次数
	 */
	private Float fullScanCnt;
	
	
	/**
	 * 全表连接次数
	 */
	private Float fullJoinCnt;
	
	
	/**
	 * 使用临时表数量
	 */
	private Float tmpTableCnt;
	
	
	/**
	 * 使用硬盘上临时表数量
	 */
	private Float tmpTableOnDiskCnt;
	
	
	/**
	 * 使用文件排序次数
	 */
	private Float fileSortCnt;
	
	
	/**
	 * 硬盘上文件排序次数
	 */
	private Float filesortOnDistCnt;
	
	private String gmtCreated;
	
	
	private String minDate;
	
	private String maxDate;
	
	

	/**
	 * @return the minDate
	 */
	public String getMinDate() {
		return minDate;
	}


	/**
	 * @param minDate the minDate to set
	 */
	public void setMinDate(String minDate) {
		this.minDate = minDate;
	}


	/**
	 * @return the maxDate
	 */
	public String getMaxDate() {
		return maxDate;
	}


	/**
	 * @param maxDate the maxDate to set
	 */
	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}


	/**
	 * @return the gmtCreated
	 */
	public String getGmtCreated() {
		return gmtCreated;
	}


	/**
	 * @param gmtCreated the gmtCreated to set
	 */
	public void setGmtCreated(String gmtCreated) {
		this.gmtCreated = gmtCreated;
	}


	/**
	 * @return the dbMax
	 */
	public String getDbMax() {
		return dbMax;
	}


	/**
	 * @param dbMax the dbMax to set
	 */
	public void setDbMax(String dbMax) {
		this.dbMax = dbMax;
	}


	/**
	 * @return the checkSum
	 */
	public String getCheckSum() {
		return checkSum;
	}


	/**
	 * @param checkSum the checkSum to set
	 */
	public void setCheckSum(String checkSum) {
		this.checkSum = checkSum;
	}


	/**
	 * @return the sample
	 */
	public String getSample() {
		return sample;
	}


	/**
	 * @param sample the sample to set
	 */
	public void setSample(String sample) {
		this.sample = sample;
	}


	/**
	 * @return the execCount
	 */
	public Float getExecCount() {
		return execCount;
	}


	/**
	 * @param execCount the execCount to set
	 */
	public void setExecCount(Float execCount) {
		this.execCount = execCount;
	}


	/**
	 * @return the queryTimeSum
	 */
	public Float getQueryTimeSum() {
		return queryTimeSum;
	}


	/**
	 * @param queryTimeSum the queryTimeSum to set
	 */
	public void setQueryTimeSum(Float queryTimeSum) {
		this.queryTimeSum = queryTimeSum;
	}


	/**
	 * @return the lockTimeSum
	 */
	public Float getLockTimeSum() {
		return lockTimeSum;
	}


	/**
	 * @param lockTimeSum the lockTimeSum to set
	 */
	public void setLockTimeSum(Float lockTimeSum) {
		this.lockTimeSum = lockTimeSum;
	}


	/**
	 * @return the rowsSentSum
	 */
	public Float getRowsSentSum() {
		return rowsSentSum;
	}


	/**
	 * @param rowsSentSum the rowsSentSum to set
	 */
	public void setRowsSentSum(Float rowsSentSum) {
		this.rowsSentSum = rowsSentSum;
	}


	/**
	 * @return the rowsExaminedSum
	 */
	public Float getRowsExaminedSum() {
		return rowsExaminedSum;
	}


	/**
	 * @param rowsExaminedSum the rowsExaminedSum to set
	 */
	public void setRowsExaminedSum(Float rowsExaminedSum) {
		this.rowsExaminedSum = rowsExaminedSum;
	}


	/**
	 * @return the rowsAffectedSum
	 */
	public Float getRowsAffectedSum() {
		return rowsAffectedSum;
	}


	/**
	 * @param rowsAffectedSum the rowsAffectedSum to set
	 */
	public void setRowsAffectedSum(Float rowsAffectedSum) {
		this.rowsAffectedSum = rowsAffectedSum;
	}


	/**
	 * @return the fullScanCnt
	 */
	public Float getFullScanCnt() {
		return fullScanCnt;
	}


	/**
	 * @param fullScanCnt the fullScanCnt to set
	 */
	public void setFullScanCnt(Float fullScanCnt) {
		this.fullScanCnt = fullScanCnt;
	}


	/**
	 * @return the fullJoinCnt
	 */
	public Float getFullJoinCnt() {
		return fullJoinCnt;
	}


	/**
	 * @param fullJoinCnt the fullJoinCnt to set
	 */
	public void setFullJoinCnt(Float fullJoinCnt) {
		this.fullJoinCnt = fullJoinCnt;
	}


	/**
	 * @return the tmpTableCnt
	 */
	public Float getTmpTableCnt() {
		return tmpTableCnt;
	}


	/**
	 * @param tmpTableCnt the tmpTableCnt to set
	 */
	public void setTmpTableCnt(Float tmpTableCnt) {
		this.tmpTableCnt = tmpTableCnt;
	}


	/**
	 * @return the tmpTableOnDiskCnt
	 */
	public Float getTmpTableOnDiskCnt() {
		return tmpTableOnDiskCnt;
	}


	/**
	 * @param tmpTableOnDiskCnt the tmpTableOnDiskCnt to set
	 */
	public void setTmpTableOnDiskCnt(Float tmpTableOnDiskCnt) {
		this.tmpTableOnDiskCnt = tmpTableOnDiskCnt;
	}


	/**
	 * @return the fileSortCnt
	 */
	public Float getFileSortCnt() {
		return fileSortCnt;
	}


	/**
	 * @param fileSortCnt the fileSortCnt to set
	 */
	public void setFileSortCnt(Float fileSortCnt) {
		this.fileSortCnt = fileSortCnt;
	}


	/**
	 * @return the filesortOnDistCnt
	 */
	public Float getFilesortOnDistCnt() {
		return filesortOnDistCnt;
	}


	/**
	 * @param filesortOnDistCnt the filesortOnDistCnt to set
	 */
	public void setFilesortOnDistCnt(Float filesortOnDistCnt) {
		this.filesortOnDistCnt = filesortOnDistCnt;
	}


	/**
	 * @return the tsMin
	 */
	public Date getTsMin() {
		return tsMin;
	}


	/**
	 * @param tsMin the tsMin to set
	 */
	public void setTsMin(Date tsMin) {
		this.tsMin = tsMin;
	}


	/**
	 * @return the tsMax
	 */
	public Date getTsMax() {
		return tsMax;
	}


	/**
	 * @param tsMax the tsMax to set
	 */
	public void setTsMax(Date tsMax) {
		this.tsMax = tsMax;
	} 
	
	
	
}
