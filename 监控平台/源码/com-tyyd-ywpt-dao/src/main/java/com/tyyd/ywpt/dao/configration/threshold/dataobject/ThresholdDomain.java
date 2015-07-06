/**   
 * @Title: ThresholdDomain.java 
 * @Package com.tyyd.ywpt.dao.configration.threshold.dataobject 
 * @Description:  
 * @author wangyu   
 * @date 2014-6-16 下午1:48:16 
 * @CopyRight 天翼阅读
 * @version V1.0   
 */
package com.tyyd.ywpt.dao.configration.threshold.dataobject;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author wangyu
 * 
 */
public class ThresholdDomain {

	private String id;

	/**
	 * 主机ID/数据库ID
	 */
	private String monitorId;

	/**
	 * 指标ID
	 */
	private String quotaId;

	/**
	 * 配置类型 ,见字典（sys_type）
	 */
	private Integer configType;

	/**
	 * 指标值
	 */
	private Float quotaValue;

	/**
	 * 指标单位
	 */
	private String quotaMetric;

	/**
	 * 上次的设置值
	 */
	private Float lastQuotaValue;


	/**
	 * 创建时间
	 */
	private Date gmtCreated;

	/**
	 * 修改时间
	 */
	private Date gmtModifed;
	
	
	/**
	 * 预警指标的阈值
	 */
	private Float warnQuotaValue;
	
	/**
	 * 致命指标的阈值
	 */
	private Float criticalQuotaValue;
	
	/**
	 * 上次的预警设置的阈值
	 */
	private Float lastWarnQuotaValue;
	
	/**
	 * 上次的致命设置的阈值
	 */
	private Float lastCriticalQuotaValue;

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
	 * @return the quotaId
	 */
	public String getQuotaId() {
		return quotaId;
	}

	/**
	 * @param quotaId the quotaId to set
	 */
	public void setQuotaId(String quotaId) {
		this.quotaId = quotaId;
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
	 * @return the quotaValue
	 */
	public Float getQuotaValue() {
		return quotaValue;
	}

	/**
	 * @param quotaValue the quotaValue to set
	 */
	public void setQuotaValue(Float quotaValue) {
		this.quotaValue = quotaValue;
	}

	/**
	 * @return the quotaMetric
	 */
	public String getQuotaMetric() {
		return quotaMetric;
	}

	/**
	 * @param quotaMetric the quotaMetric to set
	 */
	public void setQuotaMetric(String quotaMetric) {
		this.quotaMetric = quotaMetric;
	}

	/**
	 * @return the lastQuotaValue
	 */
	public Float getLastQuotaValue() {
		return lastQuotaValue;
	}

	/**
	 * @param lastQuotaValue the lastQuotaValue to set
	 */
	public void setLastQuotaValue(Float lastQuotaValue) {
		this.lastQuotaValue = lastQuotaValue;
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
	 * @return the lastWarnQuotaValue
	 */
	public Float getLastWarnQuotaValue() {
		return lastWarnQuotaValue;
	}

	/**
	 * @param lastWarnQuotaValue the lastWarnQuotaValue to set
	 */
	public void setLastWarnQuotaValue(Float lastWarnQuotaValue) {
		this.lastWarnQuotaValue = lastWarnQuotaValue;
	}

	/**
	 * @return the lastCriticalQuotaValue
	 */
	public Float getLastCriticalQuotaValue() {
		return lastCriticalQuotaValue;
	}

	/**
	 * @param lastCriticalQuotaValue the lastCriticalQuotaValue to set
	 */
	public void setLastCriticalQuotaValue(Float lastCriticalQuotaValue) {
		this.lastCriticalQuotaValue = lastCriticalQuotaValue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	
}
