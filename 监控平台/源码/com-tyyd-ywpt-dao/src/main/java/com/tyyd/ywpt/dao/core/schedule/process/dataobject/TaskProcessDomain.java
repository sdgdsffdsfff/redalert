/**   
* @Title: TaskProcessDomain.java 
* @Package com.tyyd.ywpt.dao.core.schedule.process.dataobject 
* @Description:  
* @author wangyu   
* @date 2014-6-16 下午2:59:35 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.schedule.process.dataobject;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author wangyu
 *
 */
public class TaskProcessDomain {

	private String id;

    /**
     * 系统类型,见数据字典sys_type
     */
    private Integer sysType;

    /**
     * 主机ID
     */
    private String hostId;

    /**
     * 数据库ID
     */
    private String dbId;

    /**
     * 进程ID
     */
    private String processId;

    /**
     * 进程类型,见数据字典 daemon_type
     */
    private Integer daemonType;

    /**
     * 是否心跳
     */
    private Integer isHeartbeat;

    /**
     * 采集服务编码
     */
    private String serverId;

    /**
     * 是否完成 ，0:未完成，1:完成
     */
    private String isComplete;

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
	 * @return the hostId
	 */
	public String getHostId() {
		return hostId;
	}

	/**
	 * @param hostId the hostId to set
	 */
	public void setHostId(String hostId) {
		this.hostId = hostId;
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
	 * @return the processId
	 */
	public String getProcessId() {
		return processId;
	}

	/**
	 * @param processId the processId to set
	 */
	public void setProcessId(String processId) {
		this.processId = processId;
	}

	/**
	 * @return the daemonType
	 */
	public Integer getDaemonType() {
		return daemonType;
	}

	/**
	 * @param daemonType the daemonType to set
	 */
	public void setDaemonType(Integer daemonType) {
		this.daemonType = daemonType;
	}

	/**
	 * @return the isHeartbeat
	 */
	public Integer getIsHeartbeat() {
		return isHeartbeat;
	}

	/**
	 * @param isHeartbeat the isHeartbeat to set
	 */
	public void setIsHeartbeat(Integer isHeartbeat) {
		this.isHeartbeat = isHeartbeat;
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
	 * @return the isComplete
	 */
	public String getIsComplete() {
		return isComplete;
	}

	/**
	 * @param isComplete the isComplete to set
	 */
	public void setIsComplete(String isComplete) {
		this.isComplete = isComplete;
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
    
    
}
