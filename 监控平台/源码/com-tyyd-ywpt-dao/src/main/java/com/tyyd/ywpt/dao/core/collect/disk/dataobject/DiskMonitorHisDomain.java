/**   
* @Title: DiskMonitorHisDomain.java 
* @Package com.tyyd.ywpt.dao.core.collect.disk.dataobject 
* @Description:  
* @author wangyu   
* @date 2014-6-23 下午4:19:42 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.disk.dataobject;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author wangyu
 *
 */
public class DiskMonitorHisDomain {

	private String id;

    /**
     * 主机ID
     */
    private String hostId;

    /**
     * 磁盘名
     */
    private String diskName;

    /**
     * 已用,单位是M
     */
    private Float used;

    /**
     * 剩余,单位是M
     */
    private Float remain;

    /**
     * 利用率
     */
    private Float usedPercent;

    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 修改时间
     */
    private Date gmtModifed;
    
    
    /**
     * 别名
     */
    private String nickName;
    
    /**
     * IP
     */
    private String ipAddr;
    
    /**
     * 预警单位
     */
    private String thresholdUnit;
    
    /**
     * 预警值
     */
    private Float thresholdValue;
    
    /**
     * 标准预警值
     */
    private Long waringQuotaValue;
    
    
    /**
     * 致命指标值
     */
    private Long criticalQuotaValue;
    
    /**
     * 阈值算法类型
     */
    private Integer thresholdType;
    
    /**
     * 0:无预警，1：warn，2：critical
     */
    private Integer warnLevel;
    
	/**
	 * @return the waringQuotaValue
	 */
	public Long getWaringQuotaValue() {
		return waringQuotaValue;
	}

	/**
	 * @param waringQuotaValue the waringQuotaValue to set
	 */
	public void setWaringQuotaValue(Long waringQuotaValue) {
		this.waringQuotaValue = waringQuotaValue;
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
	 * @return the diskName
	 */
	public String getDiskName() {
		return diskName;
	}

	/**
	 * @param diskName the diskName to set
	 */
	public void setDiskName(String diskName) {
		this.diskName = diskName;
	}

	/**
	 * @return the used
	 */
	public Float getUsed() {
		return used;
	}

	/**
	 * @param used the used to set
	 */
	public void setUsed(Float used) {
		this.used = used;
	}

	/**
	 * @return the remain
	 */
	public Float getRemain() {
		return remain;
	}

	/**
	 * @param remain the remain to set
	 */
	public void setRemain(Float remain) {
		this.remain = remain;
	}

	/**
	 * @return the usedPercent
	 */
	public Float getUsedPercent() {
		return usedPercent;
	}

	/**
	 * @param usedPercent the usedPercent to set
	 */
	public void setUsedPercent(Float usedPercent) {
		this.usedPercent = usedPercent;
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

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
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
	 * @return the thresholdUnit
	 */
	public String getThresholdUnit() {
		return thresholdUnit;
	}

	/**
	 * @param thresholdUnit the thresholdUnit to set
	 */
	public void setThresholdUnit(String thresholdUnit) {
		this.thresholdUnit = thresholdUnit;
	}

	/**
	 * @return the thresholdValue
	 */
	public Float getThresholdValue() {
		return thresholdValue;
	}

	/**
	 * @param thresholdValue the thresholdValue to set
	 */
	public void setThresholdValue(Float thresholdValue) {
		this.thresholdValue = thresholdValue;
	}

	/**
	 * @return the criticalQuotaValue
	 */
	public Long getCriticalQuotaValue() {
		return criticalQuotaValue;
	}

	/**
	 * @param criticalQuotaValue the criticalQuotaValue to set
	 */
	public void setCriticalQuotaValue(Long criticalQuotaValue) {
		this.criticalQuotaValue = criticalQuotaValue;
	}

	/**
	 * @return the thresholdType
	 */
	public Integer getThresholdType() {
		return thresholdType;
	}

	/**
	 * @param thresholdType the thresholdType to set
	 */
	public void setThresholdType(Integer thresholdType) {
		this.thresholdType = thresholdType;
	}

	/**
	 * @return the warnLevel
	 */
	public Integer getWarnLevel() {
		return warnLevel;
	}

	/**
	 * @param warnLevel the warnLevel to set
	 */
	public void setWarnLevel(Integer warnLevel) {
		this.warnLevel = warnLevel;
	}
	
	
}
