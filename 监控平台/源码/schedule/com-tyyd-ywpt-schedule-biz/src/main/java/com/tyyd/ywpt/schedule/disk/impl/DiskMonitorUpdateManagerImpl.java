/**   
* @Title: DiskMonitorUpdateManagerImpl.java 
* @Package com.tyyd.ywpt.schedule.disk.impl 
* @Description:  
* @author wangyu   
* @date 2015-6-3 下午5:31:22 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.disk.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tyyd.ywpt.dao.configration.host.HostConfigDAO;
import com.tyyd.ywpt.dao.configration.host.dataobject.HostConfigDomain;
import com.tyyd.ywpt.dao.core.collect.disk.DiskMonitorDAO;
import com.tyyd.ywpt.dao.core.collect.disk.DiskMonitorHisDAO;
import com.tyyd.ywpt.dao.core.collect.disk.dataobject.DiskMonitorDomain;
import com.tyyd.ywpt.dao.core.collect.disk.dataobject.DiskMonitorHisDomain;
import com.tyyd.ywpt.schedule.disk.DiskMonitorUpdateManager;

/**
 * @author wangyu
 *
 */
public class DiskMonitorUpdateManagerImpl implements DiskMonitorUpdateManager {
	
	private static final Logger LOGGER = Logger.getLogger(DiskMonitorUpdateManagerImpl.class);
	
	@Resource
	private HostConfigDAO hostConfigDAO;
	
	@Resource
	private DiskMonitorDAO diskMonitorDAO;
	
	@Resource
	private DiskMonitorHisDAO diskMonitorHisDAO;
	

	@Override
	public void doTask() {
		List<HostConfigDomain> hostList = hostConfigDAO.listNormalHostConfig();
		if(CollectionUtils.isNotEmpty(hostList)){
			for(HostConfigDomain domain : hostList){
				this.doSingleTask(domain.getHostId());
			}
		}
		
	}


	/**
	 * @param hostId
	 */
	@Override
	public void doSingleTask(String hostId) {
		LOGGER.info(String.format("开始更新磁盘数据 {hostId:%s}", hostId));
		
		long start = System.currentTimeMillis();
		
		//获取最大的snap
		Long snapId = this.getMaxSnap(hostId);
		if(snapId == null){
			return;
		}
		
		//获取最后一次采集的信息
		List<DiskMonitorHisDomain> lastDiskHisList = diskMonitorHisDAO.listLastDiskMonitorHis(hostId, snapId);
		if(CollectionUtils.isEmpty(lastDiskHisList)){
			return;
		}
		
		//当前的磁盘信息
		List<DiskMonitorDomain> list = diskMonitorDAO.listDiskMonitor(hostId);

		//获取最新的信息
		list = this.reNewDiskMonitorInfo(list,lastDiskHisList);
		
		//保存
		saveNewDiskList(list);
		
		LOGGER.info(String.format("更新磁盘数据结束 {hostId:%s,used:%s}", hostId,(System.currentTimeMillis()-start)));
		
	}


	/**
	 * @param list
	 */
	private void saveNewDiskList(List<DiskMonitorDomain> list) {
		if(CollectionUtils.isNotEmpty(list)){
			for(DiskMonitorDomain domain : list){
				if(domain.isNew()){
					diskMonitorDAO.addDiskMonitor(domain);
				}else{
					diskMonitorDAO.updateDiskInfo(domain.getHostId(),
							domain.getDiskName(), domain.getUsed(),
							domain.getRemain(), domain.getUsedPercent());
				}
			}
		}
	}


	/**
	 * @param list
	 * @param lastDiskHisList
	 * @return
	 */
	private List<DiskMonitorDomain> reNewDiskMonitorInfo(
			List<DiskMonitorDomain> list,
			List<DiskMonitorHisDomain> lastDiskHisList) {
		
		List<DiskMonitorDomain> newList = new ArrayList<DiskMonitorDomain>();
		if(CollectionUtils.isEmpty(list)){
			//全部新增
			for(DiskMonitorHisDomain his : lastDiskHisList){
				if(StringUtils.isBlank(his.getHostId()) || StringUtils.isBlank(his.getDiskName())){
					continue;
				}
				
				DiskMonitorDomain obj = this.copyBean(his,true);
				newList.add(obj);
			}
			return newList;
		}
		
		for(DiskMonitorHisDomain his : lastDiskHisList){
			if(StringUtils.isBlank(his.getHostId()) || StringUtils.isBlank(his.getDiskName())){
				continue;
			}
			
			//判断是否新增
			boolean isNew = this.isNewDisk(his.getHostId(), his.getDiskName());
			DiskMonitorDomain obj = this.copyBean(his, isNew);
			newList.add(obj);
		}
		return newList;
		
	}


	/**
	 * 是否存在
	 * @param hostId
	 * @param diskName
	 * @return
	 */
	private boolean isNewDisk(String hostId, String diskName) {

		DiskMonitorDomain domain = diskMonitorDAO.getDiskMonitorById(hostId, diskName);
		if(domain == null){
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}


	/**
	 * @param his
	 * @return
	 */
	private DiskMonitorDomain copyBean(DiskMonitorHisDomain his,boolean isNew) {
		DiskMonitorDomain domain = new DiskMonitorDomain();
		if(his != null){
			domain.setHostId(his.getHostId());
			domain.setDiskName(his.getDiskName());
			domain.setUsed(his.getUsed());
			domain.setRemain(his.getRemain());
			domain.setUsedPercent(his.getUsedPercent());

			domain.setNew(isNew);
			if(isNew){
				domain.setThresholdType(1); //默认百分比预警
				domain.setCriticalQuotaValue(98f); //默认98%
				domain.setWarnQuotaValue(95f); //默认95%
			}
			return domain;
		}
		
		return null;
	}


	/**
	 * @param hostId
	 * @return
	 */
	private Long getMaxSnap(String hostId) {
		String maxGmtCreated = diskMonitorHisDAO.getMaxSnapGmtDate(hostId);
		if(StringUtils.isBlank(maxGmtCreated)){
			return null;
		}
		
		Long snapId = diskMonitorHisDAO.getMaxSnapId(hostId, maxGmtCreated);
		return snapId;
	}

	
	
	
}
