/**   
* @Title: OracleDBAHisSgaStatDomain.java 
* @Package com.tyyd.ywpt.dao.stat.oracle.dataobject 
* @Description:  
* @author wangyu   
* @date 2014-12-15 上午10:42:43 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.stat.oracle.dataobject;

/**
 * @author wangyu
 *
 */
public class OracleDBAHisSgaStatDomain {

	
	/**
	 * 快照时间
	 */
	private String snapTime;
	
	
	/**
	 * 容量
	 */
	private Float capture;
	
	
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
	 * 区域名称
	 */
	private String name;
	
	
	/**
	 * pool名称
	 */
	private String pool;
	
	
	

	/**
	 * @return the snapTime
	 */
	public String getSnapTime() {
		return snapTime;
	}


	/**
	 * @param snapTime the snapTime to set
	 */
	public void setSnapTime(String snapTime) {
		this.snapTime = snapTime;
	}


	/**
	 * @return the capture
	 */
	public Float getCapture() {
		return capture;
	}


	/**
	 * @param capture the capture to set
	 */
	public void setCapture(Float capture) {
		this.capture = capture;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the pool
	 */
	public String getPool() {
		return pool;
	}


	/**
	 * @param pool the pool to set
	 */
	public void setPool(String pool) {
		this.pool = pool;
	}
	 
	
	
	
}
