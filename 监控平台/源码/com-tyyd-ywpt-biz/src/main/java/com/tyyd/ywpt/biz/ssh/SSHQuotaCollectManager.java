/**   
* @Title: SSHQuotaCollectManager.java 
* @Package com.tyyd.ywpt.biz.ssh 
* @Description:  
* @author wangyu   
* @date 2014-6-25 下午2:20:09 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.ssh;

import java.util.List;
import java.util.Map;

/**
 * @author wangyu
 *
 */
public interface SSHQuotaCollectManager {

	
	/**
	 * 获取CPU的信息
	 */
	public Map<String,Object> getCpuQuota();
	
	/**
	 * 获取内存的信息
	 * @return
	 */
	public Map<String,Object> getMemoryQuota();
	
	
	/**
	 * 磁盘指标
	 * @return
	 */
	public List<Map<String,Object>> getDiskQuota();
	
	
	/**
	 * 网络指标
	 * @return
	 */
	public Map<String,Object> getNetWorkQuota();
	
	
	/**
	 * 心跳
	 * @return
	 */
	public boolean heartBeatMonitor();
}
