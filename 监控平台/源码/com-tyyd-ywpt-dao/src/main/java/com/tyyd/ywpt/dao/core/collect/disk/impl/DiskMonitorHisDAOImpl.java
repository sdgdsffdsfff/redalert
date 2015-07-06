/**   
* @Title: DiskMonitorHisDAOImpl.java 
* @Package com.tyyd.ywpt.dao.core.collect.disk.impl 
* @Description:  
* @author wangyu   
* @date 2014-6-23 下午4:28:10 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.disk.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.core.collect.disk.DiskMonitorHisDAO;
import com.tyyd.ywpt.dao.core.collect.disk.dataobject.DiskMonitorHisDomain;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class DiskMonitorHisDAOImpl extends TyydBaseDAO implements
		DiskMonitorHisDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.core.collect.disk.dataobject.DiskMonitorHisDomain";
	
	@Override
	public void addDiskMonitorHis(DiskMonitorHisDomain domain) {
		this.getSqlSessionTemplate().insert(context_space +".add_disk_monitor_his", domain);
	}

	@Override
	public List<DiskMonitorHisDomain> listDiskMonitorHis(String startDate,
			String endDate, String hostId, String diskName) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("startQueryDate", startDate);
		parameter.put("endQueryDate", endDate);
		parameter.put("diskName", diskName);
		parameter.put("hostId", hostId);
		return this.getSqlSessionTemplate().selectList(context_space+".list_disk_monitor_his",parameter);
	}

	@Override
	public List<DiskMonitorHisDomain> listUnCompletedTask() {
		return this.getSqlSessionTemplate().selectList(context_space+".list_uncompleted_task");
	}

	@Override
	public void closeNormalUnCompletedTask() {
		this.getSqlSessionTemplate().update(context_space +".close_normal_uncompleted_task");
	}

	@Override
	public void closeUnCompletedTaskById(String id) {
		this.getSqlSessionTemplate().update(context_space +".close_uncompleted_task_by_id",id);
	}

	@Override
	public void closeNoMonitorTask() {
		this.getSqlSessionTemplate().update(context_space +".close_no_monitor_task");
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.disk.DiskMonitorHisDAO#closeExpiredData()
	 */
	@Override
	public void closeExpiredData() {
		this.getSqlSessionTemplate().update(context_space +".close_expired_disk_data");
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.disk.DiskMonitorHisDAO#getDiskMonitorUnCompletedData()
	 */
	@Override
	public List<DiskMonitorHisDomain> getDiskMonitorUnCompletedData() {
		return this.getSqlSessionTemplate().selectList(context_space +".list_disk_monitor_uncompleted_limit_id");
	}

	@Override
	public DiskMonitorHisDomain getDiskMonitorUnCompletedObj(String id) {
		return this.getSqlSessionTemplate().selectOne(context_space +".get_disk_monitor_uncompleted_obj", id);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.disk.DiskMonitorHisDAO#getMaxSnapGmtDate(java.lang.String)
	 */
	@Override
	public String getMaxSnapGmtDate(String hostId) {
		
		return this.getSqlSessionTemplate().selectOne(context_space +".get_max_snap_gmt_date", hostId);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.disk.DiskMonitorHisDAO#getMaxSnapId(java.lang.String, java.lang.String)
	 */
	@Override
	public Long getMaxSnapId(String hostId, String gmtCreated) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("gmtCreated", gmtCreated);
		parameter.put("hostId", hostId);
		return this.getSqlSessionTemplate().selectOne(context_space+".get_max_snap_id",parameter);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.disk.DiskMonitorHisDAO#listDiskMonitorHis(java.lang.String, java.lang.Long)
	 */
	@Override
	public List<DiskMonitorHisDomain> listLastDiskMonitorHis(String hostId,
			Long snapId) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("snapId", snapId.toString());
		parameter.put("hostId", hostId);
		return this.getSqlSessionTemplate().selectList(context_space+".list_last_disk_monitor",parameter);
	}

}
