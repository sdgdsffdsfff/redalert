/**   
* @Title: FireWallDomain.java 
* @Package com.tyyd.ywpt.dao.firewall.dataobject 
* @Description:  
* @author wangyu   
* @date 2015-4-20 下午3:38:31 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.firewall.dataobject;

import java.util.Date;

/**
 * @author wangyu
 *
 */
public class FireWallDomain {

	private Long id;
	
	private Date gmtCreated;
	
	private String gmtCreatedStamp;
	
	private String fireWallValue;

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
	 * @return the gmtCreatedStamp
	 */
	public String getGmtCreatedStamp() {
		return gmtCreatedStamp;
	}

	/**
	 * @param gmtCreatedStamp the gmtCreatedStamp to set
	 */
	public void setGmtCreatedStamp(String gmtCreatedStamp) {
		this.gmtCreatedStamp = gmtCreatedStamp;
	}

	/**
	 * @return the fireWallValue
	 */
	public String getFireWallValue() {
		return fireWallValue;
	}

	/**
	 * @param fireWallValue the fireWallValue to set
	 */
	public void setFireWallValue(String fireWallValue) {
		this.fireWallValue = fireWallValue;
	}
	
	
	
}
