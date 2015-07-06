/**   
* @Title: HostStatPandectDomain.java 
* @Package com.tyyd.ywpt.dao.stat.server 
* @Description:  
* @author wangyu   
* @date 2015-2-2 上午10:34:30 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.stat.server.domain;

/**
 * @author wangyu
 *
 */
public class HostStatPandectDomain {

	
	/**
	 * 主机ID
	 */
	private String hostId;
	
	
	/**
	 * user cpu 利用率
	 */
	private Float cpuUserPercent;
	
	/**
	 * system cpu 利用率
	 */
	private Float cpuSystemPercent;	
	
	
	/**
	 * load 5分钟
	 */
	private Float load;	
	
	/**
	 * 接受到的流量
	 */
	private Float netReceiveCapacity;	
	
	/**
	 * 发送流量
	 */
	private Float netSendCapacity;
	
	/**
	 * 内存使用率
	 */
	private Float memUserPercent;	
	
	
	/**
	 * 主机名
	 */
	private String hostName;
	
	
	/**
	 * 别名
	 */
	private String nickName;
	
	
	/**
	 * IP 地址
	 */
	private String ipAddr;
	
	
	/**
	 * 已用 单位M
	 */
	private String diskUsed;
	
	
	/**
	 * 剩余 单位M
	 */
	private String diskRemain;	
	
	
	/**
	 * 利用率
	 */
	private String diskUsedPercent;


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
	 * @return the cpuUserPercent
	 */
	public Float getCpuUserPercent() {
		return cpuUserPercent;
	}


	/**
	 * @param cpuUserPercent the cpuUserPercent to set
	 */
	public void setCpuUserPercent(Float cpuUserPercent) {
		this.cpuUserPercent = cpuUserPercent;
	}


	/**
	 * @return the cpuSystemPercent
	 */
	public Float getCpuSystemPercent() {
		return cpuSystemPercent;
	}


	/**
	 * @param cpuSystemPercent the cpuSystemPercent to set
	 */
	public void setCpuSystemPercent(Float cpuSystemPercent) {
		this.cpuSystemPercent = cpuSystemPercent;
	}


	/**
	 * @return the load
	 */
	public Float getLoad() {
		return load;
	}


	/**
	 * @param load the load to set
	 */
	public void setLoad(Float load) {
		this.load = load;
	}


	/**
	 * @return the netReceiveCapacity
	 */
	public Float getNetReceiveCapacity() {
		return netReceiveCapacity;
	}


	/**
	 * @param netReceiveCapacity the netReceiveCapacity to set
	 */
	public void setNetReceiveCapacity(Float netReceiveCapacity) {
		this.netReceiveCapacity = netReceiveCapacity;
	}


	/**
	 * @return the netSendCapacity
	 */
	public Float getNetSendCapacity() {
		return netSendCapacity;
	}


	/**
	 * @param netSendCapacity the netSendCapacity to set
	 */
	public void setNetSendCapacity(Float netSendCapacity) {
		this.netSendCapacity = netSendCapacity;
	}


	/**
	 * @return the memUserPercent
	 */
	public Float getMemUserPercent() {
		return memUserPercent;
	}


	/**
	 * @param memUserPercent the memUserPercent to set
	 */
	public void setMemUserPercent(Float memUserPercent) {
		this.memUserPercent = memUserPercent;
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
	 * @return the ipAddr
	 */
	public String getIpAddr() {
		return ipAddr;
	}


	/**
	 * @param ipAddr the ipAddr to set
	 */
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}


	/**
	 * @return the diskUsed
	 */
	public String getDiskUsed() {
		return diskUsed;
	}


	/**
	 * @param diskUsed the diskUsed to set
	 */
	public void setDiskUsed(String diskUsed) {
		this.diskUsed = diskUsed;
	}


	/**
	 * @return the diskRemain
	 */
	public String getDiskRemain() {
		return diskRemain;
	}


	/**
	 * @param diskRemain the diskRemain to set
	 */
	public void setDiskRemain(String diskRemain) {
		this.diskRemain = diskRemain;
	}


	/**
	 * @return the diskUsedPercent
	 */
	public String getDiskUsedPercent() {
		return diskUsedPercent;
	}


	/**
	 * @param diskUsedPercent the diskUsedPercent to set
	 */
	public void setDiskUsedPercent(String diskUsedPercent) {
		this.diskUsedPercent = diskUsedPercent;
	}
	
	
	
	

}
