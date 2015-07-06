/**   
* @Title: HostQuotaDayHisDomain.java 
* @Package com.tyyd.ywpt.dao.core.collect.his.host.dataobject 
* @Description:  
* @author wangyu   
* @date 2014-6-16 下午2:00:38 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.his.host.dataobject;

import java.util.Date;

/**
 * @author wangyu
 *
 */
public class HostQuotaDayHisDomain {

	private String hostQuotaHisId;

    /**
     * 每日采集ID
     */
    private String quotaCollectId;

    /**
     * 主机ID
     */
    private String hostId;

    /**
     * 指标ID
     */
    private String quotaId;

    /**
     * 指标名
     */
    private String quotaName;

    /**
     * 采集值
     */
    private Float quotaValue;

    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 修改时间
     */
    private Date gmtModifed;
    
    /**
     * 上一次的采集值
     */
    private Float lastQuotaValue;
    
    

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
	 * @return the hostQuotaHisId
	 */
	public String getHostQuotaHisId() {
		return hostQuotaHisId;
	}

	/**
	 * @param hostQuotaHisId the hostQuotaHisId to set
	 */
	public void setHostQuotaHisId(String hostQuotaHisId) {
		this.hostQuotaHisId = hostQuotaHisId;
	}

	/**
	 * @return the quotaCollectId
	 */
	public String getQuotaCollectId() {
		return quotaCollectId;
	}

	/**
	 * @param quotaCollectId the quotaCollectId to set
	 */
	public void setQuotaCollectId(String quotaCollectId) {
		this.quotaCollectId = quotaCollectId;
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
