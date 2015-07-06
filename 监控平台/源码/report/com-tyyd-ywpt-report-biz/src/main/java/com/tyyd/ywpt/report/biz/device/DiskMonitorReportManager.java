/**   
* @Title: DiskMonitorReportManager.java 
* @Package com.tyyd.ywpt.report.biz.device 
* @Description:  
* @author wangyu   
* @date 2015-6-1 下午3:23:48 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.device;

import java.util.List;

import com.tyyd.ywpt.dao.configration.host.dataobject.HostConfigDomain;
import com.tyyd.ywpt.dao.core.collect.disk.dataobject.DiskMonitorDomain;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleGeneralReportDataBO;
import com.tyyd.ywpt.report.biz.quota.dto.pie.PieReportOrgFormatDataBO;

/**
 * @author wangyu
 *
 */
public interface DiskMonitorReportManager {

	
	/**
	 * 获取主机信息
	 * @param hostId
	 * @return
	 */
	public HostConfigDomain getHostInfo(String hostId);
	
	/**
	 * 获取磁盘最新的列表
	 * @param hostId
	 * @return
	 */
	public List<DiskMonitorDomain[]> listDiskMonitor(String hostId);
	
	/**
	 * 磁盘统计信息
	 * @param hostId
	 * @return
	 */
	public DiskMonitorDomain totalDiskInfo(String hostId);
	
	/**
	 * 磁盘总量
	 * @param hostId
	 * @return
	 */
	public MultipleGeneralReportDataBO totalDiskReport(String hostId);
	
	
	/**
	 * 磁盘增量
	 * @param hostId
	 * @return
	 */
	public MultipleGeneralReportDataBO deltaDiskReport(String hostId);
	
	/**
	 * 某块盘的增量
	 * @param hostId
	 * @return
	 */
	public MultipleGeneralReportDataBO deltaDiskReportByDisk(String hostId,String diskName);
	
	
	/**
	 * 某块盘的总量
	 * @param hostId
	 * @param diskName
	 * @return
	 */
	public MultipleGeneralReportDataBO totalDiskReportByDisk(String hostId,String diskName);
	
	
	
	/**
	 * 最新的饼图
	 * @param hostId
	 * @return
	 */
	public PieReportOrgFormatDataBO theLastDiskInfo(String hostId);
}
