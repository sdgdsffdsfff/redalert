/**   
* @Title: QuotaScheduleconfigDomain.java 
* @Package com.tyyd.ywpt.dao.configration.schedule.dataobject 
* @Description:  
* @author wangyu   
* @date 2014-6-16 下午1:43:30 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.configration.schedule.dataobject;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author wangyu
 *
 */
public class DeamonScheduleconfigDomain {

	/**
	 * 主键
	 */
	private String id;

    /**
     * 数据库ID/主机ID
     */
    private String monitorId;

    /**
     * 系统类型 ,见字典（sys_type）
     */
    private Integer configType;

    
    /**
     * JOB-id
     */
    private Integer jobId;
    
    
    /**
     * 调度名
     */
    private String jobName;

    
    /**
     * 组
     */
    private String jobGroup;
    /**
     * 预警值，单位与指标的单位一致
     */
    private String quartzConf;

    
    /**
     * 轮询单位
     */
    private Integer pollUnit;
    
    /**
     * 轮询间隔,分钟为单位
     */
    private Integer pollTimes;

    /**
     * 特殊指标配置,如不包含的目录，数据文件等
     * 暂时作为扩展字段
     */
    private String extCol;


    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 修改时间
     */
    private Date gmtModifed;


    /**
     * 被调度Bean的Id
     */
    private String beanId;
    

    /**
     * 调度的Class，包全名
     */
    private String scheduleClass;
    
    
    /**
     * 0:正常,1:停用
     */
    private Integer status;
    
    
    private String ipAddr;
    
    private String port;
    
    private String serverId;
    
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
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the monitorId
	 */
	public String getMonitorId() {
		return monitorId;
	}

	/**
	 * @param monitorId the monitorId to set
	 */
	public void setMonitorId(String monitorId) {
		this.monitorId = monitorId;
	}

	/**
	 * @return the configType
	 */
	public Integer getConfigType() {
		return configType;
	}

	/**
	 * @param configType the configType to set
	 */
	public void setConfigType(Integer configType) {
		this.configType = configType;
	}



	/**
	 * @return the quartzConf
	 */
	public String getQuartzConf() {
		return quartzConf;
	}

	/**
	 * @param quartzConf the quartzConf to set
	 */
	public void setQuartzConf(String quartzConf) {
		this.quartzConf = quartzConf;
	}

	/**
	 * @return the pollTimes
	 */
	public Integer getPollTimes() {
		return pollTimes;
	}

	/**
	 * @param pollTimes the pollTimes to set
	 */
	public void setPollTimes(Integer pollTimes) {
		this.pollTimes = pollTimes;
	}

	/**
	 * @return the extCol
	 */
	public String getExtCol() {
		return extCol;
	}

	/**
	 * @param extCol the extCol to set
	 */
	public void setExtCol(String extCol) {
		this.extCol = extCol;
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
	 * @return the pollUnit
	 */
	public Integer getPollUnit() {
		return pollUnit;
	}

	/**
	 * @param pollUnit the pollUnit to set
	 */
	public void setPollUnit(Integer pollUnit) {
		this.pollUnit = pollUnit;
	}

	
	/**
	 * @return the jobId
	 */
	public Integer getJobId() {
		return jobId;
	}

	/**
	 * @param jobId the jobId to set
	 */
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
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
	 * @return the scheduleClass
	 */
	public String getScheduleClass() {
		return scheduleClass;
	}

	/**
	 * @param scheduleClass the scheduleClass to set
	 */
	public void setScheduleClass(String scheduleClass) {
		this.scheduleClass = scheduleClass;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("DeamonScheduleconfigDomain [%s]", ToStringBuilder.reflectionToString(this));
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
    
    
    
}
