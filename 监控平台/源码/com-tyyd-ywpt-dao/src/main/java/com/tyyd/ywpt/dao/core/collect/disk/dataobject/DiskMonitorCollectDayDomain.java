/**   
* @Title: DiskMonitorCollectDayDomain.java 
* @Package com.tyyd.ywpt.dao.core.collect.disk.dataobject 
* @Description:  
* @author wangyu   
* @date 2015-5-28 上午10:26:05 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.disk.dataobject;

import java.util.Date;

/**
 * @author wangyu
 *
 */
public class DiskMonitorCollectDayDomain {

	private Long id;

    /**
     * 主机ID
     */
    private String hostId;

    /**
     * 磁盘名
     */
    private String diskName;

    /**
     * 已用,单位是M
     */
    private Float used;
    
    
    private Float lastDayUsed;

    /**
     * 剩余,单位是M
     */
    private Float remain;

    /**
     * 利用率
     */
    private Float usedPercent;

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
	 * @return the diskName
	 */
	public String getDiskName() {
		return diskName;
	}

	/**
	 * @param diskName the diskName to set
	 */
	public void setDiskName(String diskName) {
		this.diskName = diskName;
	}

	/**
	 * @return the used
	 */
	public Float getUsed() {
		return used;
	}

	/**
	 * @param used the used to set
	 */
	public void setUsed(Float used) {
		this.used = used;
	}

	/**
	 * @return the remain
	 */
	public Float getRemain() {
		return remain;
	}

	/**
	 * @param remain the remain to set
	 */
	public void setRemain(Float remain) {
		this.remain = remain;
	}

	/**
	 * @return the usedPercent
	 */
	public Float getUsedPercent() {
		return usedPercent;
	}

	/**
	 * @param usedPercent the usedPercent to set
	 */
	public void setUsedPercent(Float usedPercent) {
		this.usedPercent = usedPercent;
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

  
}
