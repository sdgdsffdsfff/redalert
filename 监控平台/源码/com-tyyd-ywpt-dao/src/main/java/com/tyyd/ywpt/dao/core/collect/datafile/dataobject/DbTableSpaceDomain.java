/**   
* @Title: DbTableSpaceDomain.java 
* @Package com.tyyd.ywpt.dao.core.collect.datafile.dataobject 
* @Description:  
* @author wangyu   
* @date 2014-6-23 下午2:54:15 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.datafile.dataobject;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author wangyu
 *
 */
public class DbTableSpaceDomain {

	private String id;

    /**
     * 数据库ID
     */
    private String dbId;

    /**
     * 表空间名
     */
    private String tbsName;

    /**
     * 已用,M为单位
     */
    private Float usedTbs;

    /**
     * 表空间上限,M为单位
     */
    private Float maxTbs;

    /**
     * 表空间利用率
     */
    private Float usePercent;

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
    
    
    boolean isNew;
    
    
    
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
	 * @return the tbsName
	 */
	public String getTbsName() {
		return tbsName;
	}

	/**
	 * @param tbsName the tbsName to set
	 */
	public void setTbsName(String tbsName) {
		this.tbsName = tbsName;
	}

	/**
	 * @return the usedTbs
	 */
	public Float getUsedTbs() {
		return usedTbs;
	}

	/**
	 * @param usedTbs the usedTbs to set
	 */
	public void setUsedTbs(Float usedTbs) {
		this.usedTbs = usedTbs;
	}

	/**
	 * @return the maxTbs
	 */
	public Float getMaxTbs() {
		return maxTbs;
	}

	/**
	 * @param maxTbs the maxTbs to set
	 */
	public void setMaxTbs(Float maxTbs) {
		this.maxTbs = maxTbs;
	}

	/**
	 * @return the usePercent
	 */
	public Float getUsePercent() {
		return usePercent;
	}

	/**
	 * @param usePercent the usePercent to set
	 */
	public void setUsePercent(Float usePercent) {
		this.usePercent = usePercent;
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
