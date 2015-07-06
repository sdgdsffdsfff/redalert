/**   
* @Title: ServerSsh.java 
* @Package com.tyyd.ywpt.biz.ssh.invoke 
* @Description:  
* @author wangyu   
* @date 2014-7-7 上午11:21:32 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.ssh.invoke;

/**
 * @author wangyu
 *
 */
public interface ServerSsh {

	/**
	 * 获取主机CPU信息
	 * @param ip
	 */
	public void sshCollectServerCpuInfo(String ipAddr);
	
	/**
	 * 获取主机Memory信息
	 * @param ip
	 */
	public void sshCollectServerMemoryInfo(String ipAddr);
	
	/**
	 * 获取主机NetWork信息
	 * @param ip
	 */
	public void sshCollectServerNetWorkInfo(String ipAddr);
	
	/**
	 * 获取主机Disk信息
	 * @param ip
	 */
	public void sshCollectServerDiskInfo(String ipAddr);
	
	
	/**
	 * SSD 信息
	 * @param ipAddr
	 */
	public void sshCollectSsdInfo(String ipAddr);
	
	
	/**
	 * 心跳
	 * @return
	 */
	public boolean heartBeatMonitor();
	

	/**
	 * 清理僵死的JOB
	 */
	public void sshClearDeadJob();
}
