/**   
 * @Title: AlertNotifyConfigDomain.java 
 * @Package com.tyyd.ywpt.dao.alert.recevie.dataobject 
 * @Description:  
 * @author wangyu   
 * @date 2014-6-16 上午11:40:21 
 * @CopyRight 天翼阅读
 * @version V1.0   
 */
package com.tyyd.ywpt.dao.alert.recevie.dataobject;

import java.util.Date;

/**
 * @author wangyu
 * 
 */
public class AlertNotifyConfigDomain {

	/**
	 * 数据库ID/主机ID
	 */
	private String monitorId;

	/**
	 * 预警类型 见字典（sys_type）
	 */
	private Integer configType;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 状态 0:正常,1:删除
	 */
	private String status;

	/**
	 * 创建时间
	 */
	private Date gmtCreated;

	/**
	 * 修改时间
	 */
	private Date gmtModifed;

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
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
	
	
}
