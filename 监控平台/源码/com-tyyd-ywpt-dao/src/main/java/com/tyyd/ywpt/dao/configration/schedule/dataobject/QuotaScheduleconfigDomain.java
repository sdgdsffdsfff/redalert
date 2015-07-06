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
public class QuotaScheduleconfigDomain {

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
     * 指标ID
     */
    private String quotaId;
    
    /**
     * 指标名
     */
    private String quotaName;

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
	 * @return the quotaName
	 */
	public String getQuotaName() {
		return quotaName;
	}

	/**
	 * @param quotaName the quotaName to set
	 */
	public void setQuotaName(String quotaName) {
		this.quotaName = quotaName;
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
