/**   
* @Title: OracleDbaHisSQLStatDomain.java 
* @Package com.tyyd.ywpt.dao.stat.oracle 
* @Description:  
* @author wangyu   
* @date 2014-12-3 上午10:48:20 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.stat.oracle.dataobject;

/**
 * @author wangyu
 *
 */
public class OracleDbaHisSQLStatDomain {

	private Float quotaValue;
	
	private String unixTimeStamp;
	
	/**
	 * snap id
	 */
	private Long snapId;

	
	/**
	 * databaseId
	 */
	private Long dbId;
	
	
	/**
	 * 实例ID
	 */
	private Long instanceId;
	
	
	/**
	 * sqlId
	 */
	private String sqlId;
	
	
	/**
	 * plan_hash_value
	 */
	private String planHashValue;

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
	 * @return the unixTimeStamp
	 */
	public String getUnixTimeStamp() {
		return unixTimeStamp;
	}

	/**
	 * @param unixTimeStamp the unixTimeStamp to set
	 */
	public void setUnixTimeStamp(String unixTimeStamp) {
		this.unixTimeStamp = unixTimeStamp;
	}

	/**
	 * @return the snapId
	 */
	public Long getSnapId() {
		return snapId;
	}

	/**
	 * @param snapId the snapId to set
	 */
	public void setSnapId(Long snapId) {
		this.snapId = snapId;
	}

	/**
	 * @return the dbId
	 */
	public Long getDbId() {
		return dbId;
	}

	/**
	 * @param dbId the dbId to set
	 */
	public void setDbId(Long dbId) {
		this.dbId = dbId;
	}

	/**
	 * @return the instanceId
	 */
	public Long getInstanceId() {
		return instanceId;
	}

	/**
	 * @param instanceId the instanceId to set
	 */
	public void setInstanceId(Long instanceId) {
		this.instanceId = instanceId;
	}

	/**
	 * @return the sqlId
	 */
	public String getSqlId() {
		return sqlId;
	}

	/**
	 * @param sqlId the sqlId to set
	 */
	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}

	/**
	 * @return the planHashValue
	 */
	public String getPlanHashValue() {
		return planHashValue;
	}

	/**
	 * @param planHashValue the planHashValue to set
	 */
	public void setPlanHashValue(String planHashValue) {
		this.planHashValue = planHashValue;
	}
	
	
	
	
}
