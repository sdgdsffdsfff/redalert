/**   
* @Title: DiskMonitorCollectDayDAo.java 
* @Package com.tyyd.ywpt.dao.core.collect.disk 
* @Description:  
* @author wangyu   
* @date 2015-5-28 上午10:24:36 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.disk;

import java.util.List;

import com.tyyd.ywpt.dao.core.collect.disk.dataobject.DiskMonitorCollectDayDomain;


/**
 * @author wangyu
 *
 */
public interface DiskMonitorCollectDayDAO {

	/**
	 * 收集磁盘数据
	 * @param hostId
	 * @param collectDate
	 */
	public void collectDiskInfo(String hostId,String collectDate,String snapId, Float lastDayUsed);
	
	
	/**
	 * 获取当前时间段内的最大快照采集时间
	 * @param hostId
	 * @param collectDate
	 * @return
	 */
	public String getMaxSnapId(String hostId,String startDate,String endDate);
	
	/**
	 * 删除当前快照时间
	 * @param hostId
	 * @param collectDate
	 */
	public void delCurrentDiskData(String hostId,String collectDate);
	
	
	/**
	 * 获取最大的采集时间
	 * @param hostId
	 * @return
	 */
	public String getMaxDiskCollectDate(String hostId);
	
	
	/**
	 * 从his中拿最小的时间
	 * @param hostId
	 * @return
	 */
	public String getMinDiskHisCollectDate(String hostId,String strDate);
	/**
	 * 昨日的数据
	 * @param hostId
	 * @param collectDate
	 * @return
	 */
	public List<DiskMonitorCollectDayDomain> getLastDayDiskInfo(String hostId,String collectDate);
	
	
	/**
	 * 更新
	 * @param hostId
	 * @param collectDate
	 * @param diskName
	 * @param lastDayUsed
	 */
	public void updateDiskInfo(String hostId,String collectDate,String diskName,Float lastDayUsed);
	
	
	/**
	 * 磁盘总量
	 * @param hostId
	 * @return
	 */
	public List<DiskMonitorCollectDayDomain> sumDiskTotal(String hostId);
	
	/**
	 * 磁盘增量
	 * @param hostId
	 * @return
	 */
	public List<DiskMonitorCollectDayDomain> sumDiskDelta(String hostId);
	
	
	/**
	 * 某块盘的增量
	 * @param hostId
	 * @param diskName
	 * @return
	 */
	public List<DiskMonitorCollectDayDomain> sumDiskDeltaByDiskName(String hostId,String diskName);
	
	
	/**
	 * 某块盘的总量
	 * @param hostId
	 * @param diskName
	 * @return
	 */
	public List<DiskMonitorCollectDayDomain> sumDiskTotalByDisk(String hostId,String diskName);
}
