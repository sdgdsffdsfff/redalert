/**   
* @Title: DbTableSubTaskDomain.java 
* @Package com.tyyd.ywpt.dao.fbfk.dataobject 
* @Description:  
* @author wangyu   
* @date 2015-4-15 上午10:40:21 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.fbfk.dataobject;

import java.util.Date;

/**
 * @author wangyu
 *
 */
public class DbTableSubTaskDomain {

	
	/**
	 * 编码
	 */
	private Long taskId;
	
	private Integer dbTableSubClassifyId;
	
	
	/**
	 * 任务名
	 */
	private String jobName;
	
	
	/**
	 * 任务描述
	 */
	private String jobRemark;
	
	
	/**
	 * contab格式
	 */
	private String jobCronTab;
	
	
	/**
	 * 时间描述 yyyy-mm-dd hh: mm:ss
	 */
	private String jobTime;
	
	
	/**
	 * 状态：0初始，1接受任务，2运行完毕
	 */
	private Integer status;
	
	/**
	 * 任务类型 1:DDL 2:DML
	 */
	private Integer jobType;
	
	/**
	 * 表名
	 */
	private String tableName;
	
	
	/**
	 * 执行脚本
	 */
	private String execScript;
	
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
	 * @return the dbTableSubClassifyId
	 */
	public Integer getDbTableSubClassifyId() {
		return dbTableSubClassifyId;
	}


	/**
	 * @param dbTableSubClassifyId the dbTableSubClassifyId to set
	 */
	public void setDbTableSubClassifyId(Integer dbTableSubClassifyId) {
		this.dbTableSubClassifyId = dbTableSubClassifyId;
	}


	/**
	 * @return the jobName
	 */
	public String getJobName() {
		return jobName;
	}


	/**
	 * @param jobName the jobName to set
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}


	/**
	 * @return the jobRemark
	 */
	public String getJobRemark() {
		return jobRemark;
	}


	/**
	 * @param jobRemark the jobRemark to set
	 */
	public void setJobRemark(String jobRemark) {
		this.jobRemark = jobRemark;
	}


	/**
	 * @return the jobCronTab
	 */
	public String getJobCronTab() {
		return jobCronTab;
	}


	/**
	 * @param jobCronTab the jobCronTab to set
	 */
	public void setJobCronTab(String jobCronTab) {
		this.jobCronTab = jobCronTab;
	}


	/**
	 * @return the jobTime
	 */
	public String getJobTime() {
		return jobTime;
	}


	/**
	 * @param jobTime the jobTime to set
	 */
	public void setJobTime(String jobTime) {
		this.jobTime = jobTime;
	}


	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}


	/**
	 * @return the jobType
	 */
	public Integer getJobType() {
		return jobType;
	}


	/**
	 * @param jobType the jobType to set
	 */
	public void setJobType(Integer jobType) {
		this.jobType = jobType;
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
