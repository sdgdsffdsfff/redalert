/**   
* @Title: DbTableSpaceCollectDayDomain.java 
* @Package com.tyyd.ywpt.dao.core.collect.datafile.dataobject 
* @Description:  
* @author wangyu   
* @date 2015-6-1 上午11:19:16 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.datafile.dataobject;


/**
 * @author wangyu
 *
 */
public class DbTableSpaceCollectDayDomain {

	
	private Long id;

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
     * 昨日已用,M为单位
     */
    private Float lastDayUsed;
    
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
    private String gmtCreated;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
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
	 * @return the lastDayUsed
	 */
	public Float getLastDayUsed() {
		return lastDayUsed;
	}

	/**
	 * @param lastDayUsed the lastDayUsed to set
	 */
	public void setLastDayUsed(Float lastDayUsed) {
		this.lastDayUsed = lastDayUsed;
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
	public String getGmtCreated() {
		return gmtCreated;
	}

	/**
	 * @param gmtCreated the gmtCreated to set
	 */
	public void setGmtCreated(String gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

    
    
    
}
