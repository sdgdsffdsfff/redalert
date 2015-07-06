/**   
* @Title: TaskDTO.java 
* @Package com.tyyd.ywpt.tasklist.dto 
* @Description:  
* @author wangyu   
* @date 2014-11-6 下午2:48:09 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.monitor.controller.tasklist.dto;

import org.quartz.Job;

/**
 * @author wangyu
 *
 */
public class TaskDTO {

	/**
     * 任务id
     */
    private String jobId;
    
    
    /**
     * Spring中定义的bean
     */
    private String beanId;
    
    /**
     * 任务类型, server ,oracle ,mysql
     */
    private String taskType;
    
    
    
    /**
     * job的对象
     */
    private Class<? extends Job> jobClass;
 
    /**
     * 任务名称 taskType
     */
    private String jobName;
 
    /**
     * 任务分组 beanId
     */
    private String jobGroup;
 
    /**
     *  None：Trigger已经完成，且不会在执行，或者找不到该触发器，或者Trigger已经被删除
		NORMAL:正常状态
		PAUSED：暂停状态
		COMPLETE：触发器完成，但是任务可能还正在执行中
		BLOCKED：线程阻塞状态
		ERROR：出现错误
     */
    private String jobStatus;
 
    /**
     *  <!-- s m h d m w(?) y(?) -->
     *  0 0/1 * * * ?  	每分钟一次
     *  0 0/5 * * * ?  	每5钟一次
     *  0 0/10 * * * ?  每10钟一次
     * 任务运行时间表达式
     */
    private String cronExpression;
 
    /**
     * 任务描述 
     */
    private String desc;
    
    
    /**
     * 对应的表ID
     * domainId + taskType能找到对应的记录
     */
    private String domainId;


    /**
     * 最后一次跑的时间
     */
    private String lastRunDate;
    
    
    private String serverId;
    
    private String ipAddr;
    
    private String port;
    
	/**
	 * @return the jobId
	 */
	public String getJobId() {
		return jobId;
	}


	/**
	 * @param jobId the jobId to set
	 */
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}


	/**
	 * @return the beanId
	 */
	public String getBeanId() {
		return beanId;
	}


	/**
	 * @param beanId the beanId to set
	 */
	public void setBeanId(String beanId) {
		this.beanId = beanId;
	}


	/**
	 * @return the taskType
	 */
	public String getTaskType() {
		return taskType;
	}


	/**
	 * @param taskType the taskType to set
	 */
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}


	/**
	 * @return the jobClass
	 */
	public Class<? extends Job> getJobClass() {
		return jobClass;
	}


	/**
	 * @param jobClass the jobClass to set
	 */
	public void setJobClass(Class<? extends Job> jobClass) {
		this.jobClass = jobClass;
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
	 * @return the jobGroup
	 */
	public String getJobGroup() {
		return jobGroup;
	}


	/**
	 * @param jobGroup the jobGroup to set
	 */
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}


	/**
	 * @return the jobStatus
	 */
	public String getJobStatus() {
		return jobStatus;
	}


	/**
	 * @param jobStatus the jobStatus to set
	 */
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}


	/**
	 * @return the cronExpression
	 */
	public String getCronExpression() {
		return cronExpression;
	}


	/**
	 * @param cronExpression the cronExpression to set
	 */
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}


	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}


	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}


	/**
	 * @return the domainId
	 */
	public String getDomainId() {
		return domainId;
	}


	/**
	 * @param domainId the domainId to set
	 */
	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}


	/**
	 * @return the lastRunDate
	 */
	public String getLastRunDate() {
		return lastRunDate;
	}


	/**
	 * @param lastRunDate the lastRunDate to set
	 */
	public void setLastRunDate(String lastRunDate) {
		this.lastRunDate = lastRunDate;
	}


	/**
	 * @return the serverId
	 */
	public String getServerId() {
		return serverId;
	}


	/**
	 * @param serverId the serverId to set
	 */
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}


	/**
	 * @return the ipAddr
	 */
	public String getIpAddr() {
		return ipAddr;
	}


	/**
	 * @param ipAddr the ipAddr to set
	 */
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}


	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}


	/**
	 * @param port the port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}
    
    
    
}
