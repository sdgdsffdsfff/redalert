/**   
* @Title: JavaJobDefDomain.java 
* @Package com.tyyd.ywpt.dao.configration.job.dataobject 
* @Description:  
* @author wangyu   
* @date 2014-11-18 下午5:42:44 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.configration.job.dataobject;

/**
 * @author wangyu
 *
 */
public class JavaJobDefDomain {

	private Long id;
	
	private String name;
	
	private String packClass;
	
	private String remark;
	
	private String sysType;
	
	private String beanPrefix;

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
	 * @return the packClass
	 */
	public String getPackClass() {
		return packClass;
	}

	/**
	 * @param packClass the packClass to set
	 */
	public void setPackClass(String packClass) {
		this.packClass = packClass;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the sysType
	 */
	public String getSysType() {
		return sysType;
	}

	/**
	 * @param sysType the sysType to set
	 */
	public void setSysType(String sysType) {
		this.sysType = sysType;
	}

	/**
	 * @return the beanPrefix
	 */
	public String getBeanPrefix() {
		return beanPrefix;
	}

	/**
	 * @param beanPrefix the beanPrefix to set
	 */
	public void setBeanPrefix(String beanPrefix) {
		this.beanPrefix = beanPrefix;
	}
	
	
	
	
	
}
