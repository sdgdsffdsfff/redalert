/**   
* @Title: TaskJobLogDomain.java 
* @Package com.tyyd.ywpt.dao.core.schedule.tasklog.dataobject 
* @Description:  
* @author wangyu   
* @date 2014-6-16 下午2:55:44 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.schedule.tasklog.dataobject;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author wangyu
 *
 */
public class TaskJobLogDomain {

	private String id;

    /**
     * 系统类型,见数据字典sys_type
     */
    private Integer sysType;

    /**
     * 监控ID
     */
    private String monitorId;


    /**
     * 进程ID
     */
    private String beanId;

    /**
     * 进程类型,见数据字典 daemon_type,就是jobId
     */
    private String jobId;


    /**
     * 采集服务编码
     */
    private String serverId;


    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 修改时间
     */
    private Date gmtModifed;

	

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
	 * @return the sysType
	 */
	public Integer getSysType() {
		return sysType;
	}



	/**
	 * @param sysType the sysType to set
	 */
	public void setSysType(Integer sysType) {
		this.sysType = sysType;
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



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
    
    
}
