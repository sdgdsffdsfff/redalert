/**   
* @Title: DbTableSubLogDomain.java 
* @Package com.tyyd.ywpt.dao.fbfk.dataobject 
* @Description:  
* @author wangyu   
* @date 2015-4-15 上午11:03:43 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.fbfk.dataobject;

import java.util.Date;

/**
 * @author wangyu
 *
 */
public class DbTableSubLogDomain {

	private Long taskId;
	
	
	private Long id;
	
	
	/**
	 * 同instanceid
	 */
	private String dbId;
	
	
	/**
	 * 库名
	 */
	private String dbName;
	

	
	/**
	 * 表名
	 */
	private String tableName;
	
	
	/**
	 * 执行类型 1:脚本 2:sql
	 */
	private Integer execType;
	
	
	/**
	 * 执行的具体脚本
	 */
	private String execScript;
	
	/**
	 * 执行日志
	 */
	private String jobLog;
	
	
	/**
	 * 执行标示 0成功 1失败
	 */
	private Integer jobFlag;
	
	
	/**
	 * 创建时间
	 */
	private Date gmtCreated;
	
	
	/**
	 * 修改时间
	 */
	private Date gmtModifed;


	/**
	 * @return the taskId
	 */
	public Long getTaskId() {
		return taskId;
	}


	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * @return the dbId
	 */
	public String getDbId() {
		return dbId;
	}


	/**
	 * @param dbId the dbId to set
	 */
	public void setDbId(String dbId) {
		this.dbId = dbId;
	}


	/**
	 * @return the dbName
	 */
	public String getDbName() {
		return dbName;
	}


	/**
	 * @param dbName the dbName to set
	 */
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}


	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}


	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}


	/**
	 * @return the jobLog
	 */
	public String getJobLog() {
		return jobLog;
	}


	/**
	 * @param jobLog the jobLog to set
	 */
	public void setJobLog(String jobLog) {
		this.jobLog = jobLog;
	}


	/**
	 * @return the jobFlag
	 */
	public Integer getJobFlag() {
		return jobFlag;
	}


	/**
	 * @param jobFlag the jobFlag to set
	 */
	public void setJobFlag(Integer jobFlag) {
		this.jobFlag = jobFlag;
	}


	/**
	 * @return the gmtCreated
	 */
	public Date getGmtCreated() {
		return gmtCreated;
	}


	/**
	 * @param gmtCreated the gmtCreated to set
	 */
	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}


	/**
	 * @return the gmtModifed
	 */
	public Date getGmtModifed() {
		return gmtModifed;
	}


	/**
	 * @param gmtModifed the gmtModifed to set
	 */
	public void setGmtModifed(Date gmtModifed) {
		this.gmtModifed = gmtModifed;
	}


	/**
	 * @return the execType
	 */
	public Integer getExecType() {
		return execType;
	}


	/**
	 * @param execType the execType to set
	 */
	public void setExecType(Integer execType) {
		this.execType = execType;
	}


	/**
	 * @return the execScript
	 */
	public String getExecScript() {
		return execScript;
	}


	/**
	 * @param execScript the execScript to set
	 */
	public void setExecScript(String execScript) {
		this.execScript = execScript;
	}
	
	
	
	
}
