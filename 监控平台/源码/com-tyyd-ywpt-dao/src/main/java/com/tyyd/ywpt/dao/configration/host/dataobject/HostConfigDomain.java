/**   
 * @Title: HostDomain.java 
 * @Package com.tyyd.ywpt.dao.configration.host.dataobject 
 * @Description:  
 * @author wangyu   
 * @date 2014-6-16 上午11:46:47 
 * @CopyRight 天翼阅读
 * @version V1.0   
 */
package com.tyyd.ywpt.dao.configration.host.dataobject;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author wangyu
 * 
 */
public class HostConfigDomain {

	/**
	 * 主机ID
	 */
	private String hostId;

	
	/**
	 * 主机名
	 */
	private String hostName;
	
	/**
	 * 所属IDC机房
	 */
	private Integer idcId;
	
	/**
	 * IP地址
	 */
	private String ipAddr;

	/**
	 * 是否主机采集 (Y是，N否)
	 */
	private String isHostCollect;

	/**
	 * 系统账号
	 */
	private String userAccount;

	/**
	 * 系统密码 DES/AES加密
	 */
	private String userPasswd;

	/**
	 * 内核
	 */
	private String osInfo;

	/**
	 * cpu信息
	 */
	private String cpuInfo;

	/**
	 * 内存信息
	 */
	private String memoryInfo;

	/**
	 * 硬盘信息
	 */
	private String disckInfo;

	/**
	 * 状态 0:正常,1:删除
	 */
	private String status;

	/**
	 * 心跳监测配置 ,quartz的定义模式
	 */
	private String globalScheduleConf;

	/**
	 * 采集的服务器,指定由哪个采集服务器来采集数据，达到分压的效果
	 */
	private String serverId;
	
	
	/**
	 * 上一次心跳监测的时间
	 */
	private Date lastHeartBeatDate;
	
	/**
	 * 上一次心跳监测的状态
	 */
	private Integer lastHeartBeatStatus;

	/**
	 * 创建时间
	 */
	private Date gmtCreated;

	/**
	 * 修改时间
	 */
	private Date gmtModifed;
	
	/**
	 * 别名
	 */
	private String nickName;

	
	
	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * @return the hostId
	 */
	public String getHostId() {
		return hostId;
	}

	/**
	 * @param hostId
	 *            the hostId to set
	 */
	public void setHostId(String hostId) {
		this.hostId = hostId;
	}

	/**
	 * @return the ipAddr
	 */
	public String getIpAddr() {
		return ipAddr;
	}

	/**
	 * @param ipAddr
	 *            the ipAddr to set
	 */
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	/**
	 * @return the isHostCollect
	 */
	public String getIsHostCollect() {
		return isHostCollect;
	}

	/**
	 * @param isHostCollect
	 *            the isHostCollect to set
	 */
	public void setIsHostCollect(String isHostCollect) {
		this.isHostCollect = isHostCollect;
	}

	/**
	 * @return the userAccount
	 */
	public String getUserAccount() {
		return userAccount;
	}

	/**
	 * @param userAccount
	 *            the userAccount to set
	 */
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	/**
	 * @return the userPasswd
	 */
	public String getUserPasswd() {
		return userPasswd;
	}

	/**
	 * @param userPasswd
	 *            the userPasswd to set
	 */
	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}

	/**
	 * @return the osInfo
	 */
	public String getOsInfo() {
		return osInfo;
	}

	/**
	 * @param osInfo
	 *            the osInfo to set
	 */
	public void setOsInfo(String osInfo) {
		this.osInfo = osInfo;
	}

	/**
	 * @return the cpuInfo
	 */
	public String getCpuInfo() {
		return cpuInfo;
	}

	/**
	 * @param cpuInfo
	 *            the cpuInfo to set
	 */
	public void setCpuInfo(String cpuInfo) {
		this.cpuInfo = cpuInfo;
	}

	/**
	 * @return the memoryInfo
	 */
	public String getMemoryInfo() {
		return memoryInfo;
	}

	/**
	 * @param memoryInfo
	 *            the memoryInfo to set
	 */
	public void setMemoryInfo(String memoryInfo) {
		this.memoryInfo = memoryInfo;
	}

	/**
	 * @return the disckInfo
	 */
	public String getDisckInfo() {
		return disckInfo;
	}

	/**
	 * @param disckInfo
	 *            the disckInfo to set
	 */
	public void setDisckInfo(String disckInfo) {
		this.disckInfo = disckInfo;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the globalScheduleConf
	 */
	public String getGlobalScheduleConf() {
		return globalScheduleConf;
	}

	/**
	 * @param globalScheduleConf
	 *            the globalScheduleConf to set
	 */
	public void setGlobalScheduleConf(String globalScheduleConf) {
		this.globalScheduleConf = globalScheduleConf;
	}

	/**
	 * @return the serverId
	 */
	public String getServerId() {
		return serverId;
	}

	/**
	 * @param serverId
	 *            the serverId to set
	 */
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	/**
	 * @return the gmtCreated
	 */
	public Date getGmtCreated() {
		return gmtCreated;
	}

	/**
	 * @param gmtCreated
	 *            the gmtCreated to set
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
	 * @param gmtModifed
	 *            the gmtModifed to set
	 */
	public void setGmtModifed(Date gmtModifed) {
		this.gmtModifed = gmtModifed;
	}

	/**
	 * @return the idcId
	 */
	public Integer getIdcId() {
		return idcId;
	}

	/**
	 * @param idcId the idcId to set
	 */
	public void setIdcId(Integer idcId) {
		this.idcId = idcId;
	}

	/**
	 * @return the hostName
	 */
	public String getHostName() {
		return hostName;
	}

	/**
	 * @param hostName the hostName to set
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	/**
	 * @return the lastHeartBeatDate
	 */
	public Date getLastHeartBeatDate() {
		return lastHeartBeatDate;
	}

	/**
	 * @param lastHeartBeatDate the lastHeartBeatDate to set
	 */
	public void setLastHeartBeatDate(Date lastHeartBeatDate) {
		this.lastHeartBeatDate = lastHeartBeatDate;
	}

	/**
	 * @return the lastHeartBeatStatus
	 */
	public Integer getLastHeartBeatStatus() {
		return lastHeartBeatStatus;
	}

	/**
	 * @param lastHeartBeatStatus the lastHeartBeatStatus to set
	 */
	public void setLastHeartBeatStatus(Integer lastHeartBeatStatus) {
		this.lastHeartBeatStatus = lastHeartBeatStatus;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
