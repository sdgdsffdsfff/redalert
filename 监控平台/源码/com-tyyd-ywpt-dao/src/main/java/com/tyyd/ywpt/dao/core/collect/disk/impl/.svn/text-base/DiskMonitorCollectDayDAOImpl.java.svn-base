/**   
* @Title: DiskMonitorCollectDayDAOImpl.java 
* @Package com.tyyd.ywpt.dao.core.collect.disk.impl 
* @Description:  
* @author wangyu   
* @date 2015-5-28 上午10:55:25 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.disk.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.core.collect.disk.DiskMonitorCollectDayDAO;
import com.tyyd.ywpt.dao.core.collect.disk.dataobject.DiskMonitorCollectDayDomain;

/**
 * @author wangyu
 *
 */
public class DiskMonitorCollectDayDAOImpl extends TyydBaseDAO implements DiskMonitorCollectDayDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.core.collect.disk.dataobject.DiskMonitorCollectDayDomain";
	
	
	@Override
	public void collectDiskInfo(String hostId, String collectDate,String snapId,Float lastDayUsed) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("hostId", hostId);
		parameter.put("collectDate", collectDate);
		parameter.put("snapId", snapId);
		parameter.put("lastDayUsed", lastDayUsed);
		
		//新增数据 
		this.getSqlSessionTemplate().insert(context_space+".add_disk_monitor_collect_day", parameter);
	}

	@Override
	public String getMaxDiskCollectDate(String hostId) {
		return this.getSqlSessionTemplate().selectOne(context_space+".get_max_disk_date", hostId);
	}

	@Override
	public String getMaxSnapId(String hostId, String startDate,String endDate) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("hostId", hostId);
		parameter.put("startDate", startDate);
		parameter.put("endDate", endDate);

		return this.getSqlSessionTemplate().selectOne(context_space+".get_max_snap_id", parameter);
	}

	@Override
	public void delCurrentDiskData(String hostId, String collectDate) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("hostId", hostId);
		parameter.put("collectDate", collectDate);
		
		this.getSqlSessionTemplate().delete(context_space+".del_disk_monitor_collect_day", parameter);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.disk.DiskMonitorCollectDayDAO#getLastDayDiskInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public List<DiskMonitorCollectDayDomain> getLastDayDiskInfo(String hostId,
			String collectDate) {

		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("hostId", hostId);
		parameter.put("collectDate", collectDate);
		
		return this.getSqlSessionTemplate().selectList(context_space+".get_lastday_diskinfo", parameter);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.disk.DiskMonitorCollectDayDAO#updateDiskInfo(java.lang.String, java.lang.String, java.lang.String, java.lang.Float)
	 */
	@Override
	public void updateDiskInfo(String hostId, String collectDate,
			String diskName, Float lastDayUsed) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("hostId", hostId);
		parameter.put("collectDate", collectDate);
		parameter.put("diskName", diskName);
		parameter.put("lastDayUsed", lastDayUsed);
		
		this.getSqlSessionTemplate().update(context_space+".update_diskinfo", parameter);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.disk.DiskMonitorCollectDayDAO#getMinDiskHisCollectDate(java.lang.String)
	 */
	@Override
	public String getMinDiskHisCollectDate(String hostId,String strDate) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("hostId", hostId);
		parameter.put("queryDate", strDate);
		return this.getSqlSessionTemplate().selectOne(context_space+".get_disk_his_mindate", parameter);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.disk.DiskMonitorCollectDayDAO#sumDiskTotal(java.lang.String)
	 */
	@Override
	public List<DiskMonitorCollectDayDomain> sumDiskTotal(String hostId) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("hostId", hostId);
		return this.getSqlSessionTemplate().selectList(context_space+".total_disk_report", parameter);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.disk.DiskMonitorCollectDayDAO#sumDiskDelta(java.lang.String)
	 */
	@Override
	public List<DiskMonitorCollectDayDomain> sumDiskDelta(String hostId) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("hostId", hostId);
		return this.getSqlSessionTemplate().selectList(context_space+".delta_Disk_Report", parameter);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.disk.DiskMonitorCollectDayDAO#sumDiskDeltaByDiskName(java.lang.String, java.lang.String)
	 */
	@Override
	public List<DiskMonitorCollectDayDomain> sumDiskDeltaByDiskName(
			String hostId, String diskName) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("hostId", hostId);
		parameter.put("diskName", diskName);
		return this.getSqlSessionTemplate().selectList(context_space+".delta_disk_report_by_disk", parameter);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.disk.DiskMonitorCollectDayDAO#sumDiskTotalByDisk(java.lang.String, java.lang.String)
	 */
	@Override
	public List<DiskMonitorCollectDayDomain> sumDiskTotalByDisk(String hostId,
			String diskName) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("hostId", hostId);
		parameter.put("diskName", diskName);
		return this.getSqlSessionTemplate().selectList(context_space+".total_disk_report_by_disk", parameter);
	}

}
