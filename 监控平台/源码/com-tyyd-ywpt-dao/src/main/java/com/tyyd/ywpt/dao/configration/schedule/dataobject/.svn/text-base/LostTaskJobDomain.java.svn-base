/**   
* @Title: LostTaskJob.java 
* @Package com.tyyd.ywpt.dao.configration.schedule.dataobject 
* @Description:  
* @author wangyu   
* @date 2014-11-25 上午11:36:15 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.configration.schedule.dataobject;

import java.util.Date;

/**
 * @author wangyu
 *
 */
public class LostTaskJobDomain {

	
	/**
	 * 主键
	 */
	private Long id;
	
	private String deamonId;

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
     * 是否重新注册任务 0:未重新注册，1：已经完成注册,2:重新注册失败
     */
    private Integer regeditTaskStatus   ;
    
    
    /**
     * 是否发送预警,0：未发送，1：已发送
     */
    private Integer warnCompletedStatus  ; 
    
    
    /**
     * 上次运行时间
     */
    private Date lastTime;


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


	/**
	 * @return the regeditTaskStatus
	 */
	public Integer getRegeditTaskStatus() {
		return regeditTaskStatus;
	}


	/**
	 * @param regeditTaskStatus the regeditTaskStatus to set
	 */
	public void setRegeditTaskStatus(Integer regeditTaskStatus) {
		this.regeditTaskStatus = regeditTaskStatus;
	}


	/**
	 * @return the warnCompletedStatus
	 */
	public Integer getWarnCompletedStatus() {
		return warnCompletedStatus;
	}


	/**
	 * @param warnCompletedStatus the warnCompletedStatus to set
	 */
	public void setWarnCompletedStatus(Integer warnCompletedStatus) {
		this.warnCompletedStatus = warnCompletedStatus;
	}


	/**
	 * @return the lastTime
	 */
	public Date getLastTime() {
		return lastTime;
	}


	/**
	 * @param lastTime the lastTime to set
	 */
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}


	/**
	 * @return the deamonId
	 */
	public String getDeamonId() {
		return deamonId;
	}


	/**
	 * @param deamonId the deamonId to set
	 */
	public void setDeamonId(String deamonId) {
		this.deamonId = deamonId;
	}
    
    
    
    
}
