/**   
* @Title: DiskMonitorHisDAO.java 
* @Package com.tyyd.ywpt.dao.core.collect.disk 
* @Description:  
* @author wangyu   
* @date 2014-6-23 下午4:20:37 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.disk;

import java.util.List;

import com.tyyd.ywpt.dao.core.collect.disk.dataobject.DiskMonitorHisDomain;

/**
 * @author wangyu
 *
 */
public interface DiskMonitorHisDAO {

	/**
	 * 添加一个磁盘
	 * @param domain
	 */
	public void addDiskMonitorHis(DiskMonitorHisDomain domain);
	
	
	/**
	 * 指标出图
	 * @param startDate
	 * @param endDate
	 * @param hostId
	 * @return
	 */
	public List<DiskMonitorHisDomain> listDiskMonitorHis(String startDate,String endDate,String hostId,String diskName);
	
	
	/**
	 * 未处理的任务
	 * @return
	 */
	@Deprecated
	public List<DiskMonitorHisDomain> listUnCompletedTask();
	
	
	/**
	 * 关闭正常任务
	 */
	@Deprecated
	public void closeNormalUnCompletedTask();
	
	
	/**
	 * 关闭不监控的任务
	 */
	@Deprecated
	public void closeNoMonitorTask();
	
	/**
	 * 关闭任务
	 * @param id
	 */
	public void closeUnCompletedTaskById(String id);
	
	/**
	 * 关闭过期任务数据
	 */
	@Deprecated
	public void closeExpiredData();
	
	/**
	 * 返回1000行，未处理的数据
	 */
	public List<DiskMonitorHisDomain> getDiskMonitorUnCompletedData();
	
	
	/**
	 * 查询未处理的磁盘数据
	 * @param id
	 * @return
	 */
	public DiskMonitorHisDomain getDiskMonitorUnCompletedObj(String id);
	
	
	/**
	 * 获取最后一次时间
	 * @param hostId
	 * @return
	 */
	public String getMaxSnapGmtDate(String hostId);
	
	/**
	 * 获取最大的snapId
	 * @param hostId
	 * @param gmtCreated
	 * @return
	 */
	public Long getMaxSnapId(String hostId,String gmtCreated);
	
	/**
	 * 获取磁盘列表
	 * @param hostId
	 * @param snapId
	 * @return
	 */
	public List<DiskMonitorHisDomain> listLastDiskMonitorHis(String hostId,Long snapId);
}
