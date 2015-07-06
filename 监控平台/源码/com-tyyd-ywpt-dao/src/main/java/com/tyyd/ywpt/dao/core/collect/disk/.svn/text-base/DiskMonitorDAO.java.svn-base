/**   
* @Title: DiskMonitorDAO.java 
* @Package com.tyyd.ywpt.dao.core.collect.disk 
* @Description:  
* @author wangyu   
* @date 2014-6-23 下午4:21:41 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.disk;

import java.util.List;

import com.tyyd.ywpt.dao.core.collect.disk.dataobject.DiskMonitorDomain;

/**
 * @author wangyu
 *
 */
public interface DiskMonitorDAO {

	/**
	 * 添加磁盘
	 * @param domain
	 */
	public void addDiskMonitor(DiskMonitorDomain domain);
	
	/**
	 * 删除磁盘
	 * @param monitorId
	 */
	public void deleteDiskMonitor(String monitorId);
	
	/**
	 * 磁盘列表
	 * @param monitorId
	 * @return
	 */
	public List<DiskMonitorDomain> listDiskMonitor(String monitorId);
	
	
	/**
	 * 磁盘总的统计信息 
	 * @param hostId
	 * @return
	 */
	public DiskMonitorDomain totalDiskInfo(String hostId);
	
	
	/**
	 * 更新磁盘信息
	 * @param hostId
	 * @param diskName
	 */
	public void updateDiskInfo(String hostId,String diskName,Float used,Float remain,Float usedPercent);
	
	
	/**
	 * 获取数据
	 * @param hostId
	 * @param diskName
	 * @return
	 */
	public DiskMonitorDomain getDiskMonitorById(String hostId,String diskName);
}
