/**   
* @Title: DiskMonitorDomain.java 
* @Package com.tyyd.ywpt.dao.core.collect.disk.dataobject 
* @Description:  
* @author wangyu   
* @date 2014-6-16 下午1:54:39 
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
public class DiskMonitorDomain {

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
     * 阈值类型 1:百分比预警，2：剩余容量预警
     */
    private Integer thresholdType;
    
    /**
     * 致命阈值
     */
    private Float criticalQuotaValue;
    
    /**
     * 预警阈值
     */
    private Float warnQuotaValue;
    
    /**
     * 是否新增，true 是，false 否
     */
    private boolean isNew;
    
    

	/**
	 * @return the isNew
	 */
	public boolean isNew() {
		return isNew;
	}

	/**
	 * @param isNew the isNew to set
	 */
	public void setNew(boolean isNew) {
		this.isNew = isNew;
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
	 * @return the criticalQuotaValue
	 */
	public Float getCriticalQuotaValue() {
		return criticalQuotaValue;
	}

	/**
	 * @param criticalQuotaValue the criticalQuotaValue to set
	 */
	public void setCriticalQuotaValue(Float criticalQuotaValue) {
		this.criticalQuotaValue = criticalQuotaValue;
	}

	/**
	 * @return the warnQuotaValue
	 */
	public Float getWarnQuotaValue() {
		return warnQuotaValue;
	}

	/**
	 * @param warnQuotaValue the warnQuotaValue to set
	 */
	public void setWarnQuotaValue(Float warnQuotaValue) {
		this.warnQuotaValue = warnQuotaValue;
	}
    
    
	
}
