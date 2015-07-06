/**   
* @Title: QuotaDaemonRelationDomain.java 
* @Package com.tyyd.ywpt.dao.core.schedule.qdrelation.dataobject 
* @Description:  
* @author wangyu   
* @date 2014-6-16 下午3:01:49 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.schedule.qdrelation.dataobject;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author wangyu
 *
 */
public class QuotaDaemonRelationDomain {


    /**
     * 指标ID
     */
    private String quotaId;

    /**
     * 任务类型 见字典 daemon_type
     */
    private Integer daemonType;

    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 修改时间
     */
    private Date gmtModifed;


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
