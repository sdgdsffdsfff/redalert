/**   
* @Title: DiskMonitorDAOImpl.java 
* @Package com.tyyd.ywpt.dao.core.collect.disk.impl 
* @Description:  
* @author wangyu   
* @date 2014-6-23 下午4:25:40 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.disk.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.core.collect.disk.DiskMonitorDAO;
import com.tyyd.ywpt.dao.core.collect.disk.dataobject.DiskMonitorDomain;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class DiskMonitorDAOImpl extends TyydBaseDAO implements DiskMonitorDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.core.collect.disk.dataobject.DiskMonitorDomain";
	
	@Override
	public void addDiskMonitor(DiskMonitorDomain domain) {
		this.getSqlSessionTemplate().insert(context_space +".add_disk_monitor", domain);
	}

	@Override
	public void deleteDiskMonitor(String monitorId) {
		this.getSqlSessionTemplate().delete(context_space+".delete_disk_monitor",monitorId);
	}

	@Override
	public List<DiskMonitorDomain> listDiskMonitor(String monitorId) {
		return this.getSqlSessionTemplate().selectList(context_space+".list_disk_monitor",monitorId);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.disk.DiskMonitorDAO#totalDiskInfo(java.lang.String)
	 */
	@Override
	public DiskMonitorDomain totalDiskInfo(String hostId) {
		// TODO Auto-generated method stub
		return this.getSqlSessionTemplate().selectOne(context_space+".total_disk_info",hostId);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.disk.DiskMonitorDAO#updateDiskInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public void updateDiskInfo(String hostId, String diskName,Float used,Float remain,Float usedPercent) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("hostId", hostId);
		parameter.put("diskName", diskName);
		parameter.put("used", used);
		parameter.put("remain", remain);
		parameter.put("usedPercent", usedPercent);
		this.getSqlSessionTemplate().update(context_space+".update_disk_info",parameter);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.disk.DiskMonitorDAO#getDiskMonitorById(java.lang.String, java.lang.String)
	 */
	@Override
	public DiskMonitorDomain getDiskMonitorById(String hostId, String diskName) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("hostId", hostId);
		parameter.put("diskName", diskName);
		
		return this.getSqlSessionTemplate().selectOne(context_space+".get_disk_monitor_by_id",parameter);
	}

}
