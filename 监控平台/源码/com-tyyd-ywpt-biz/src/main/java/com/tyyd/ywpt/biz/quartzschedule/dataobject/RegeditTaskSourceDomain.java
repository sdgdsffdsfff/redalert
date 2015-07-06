/**   
* @Title: RegeditTaskSourceDomain.java 
* @Package com.tyyd.ywpt.biz.quartzschedule.dataobject 
* @Description:  
* @author wangyu   
* @date 2014-11-17 上午11:09:22 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.quartzschedule.dataobject;

/**
 * @author wangyu
 *
 */
public class RegeditTaskSourceDomain {

	private String monitorId;
	
	private Integer configType;
	
	private Integer jobType;
	
	private Integer deamonId;
	
	private Object monitorObj;
	
	private Integer beanType;
	
	
	private Object beanObj;

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
	 * @return the jobType
	 */
	public Integer getJobType() {
		return jobType;
	}

	/**
	 * @param jobType the jobType to set
	 */
	public void setJobType(Integer jobType) {
		this.jobType = jobType;
	}

	/**
	 * @return the deamonId
	 */
	public Integer getDeamonId() {
		return deamonId;
	}

	/**
	 * @param deamonId the deamonId to set
	 */
	public void setDeamonId(Integer deamonId) {
		this.deamonId = deamonId;
	}

	/**
	 * @return the monitorObj
	 */
	public Object getMonitorObj() {
		return monitorObj;
	}

	/**
	 * @param monitorObj the monitorObj to set
	 */
	public void setMonitorObj(Object monitorObj) {
		this.monitorObj = monitorObj;
	}

	/**
	 * @return the beanObj
	 */
	public Object getBeanObj() {
		return beanObj;
	}

	/**
	 * @param beanObj the beanObj to set
	 */
	public void setBeanObj(Object beanObj) {
		this.beanObj = beanObj;
	}

	/**
	 * @return the beanType
	 */
	public Integer getBeanType() {
		return beanType;
	}

	/**
	 * @param beanType the beanType to set
	 */
	public void setBeanType(Integer beanType) {
		this.beanType = beanType;
	}

	
	
	
}
