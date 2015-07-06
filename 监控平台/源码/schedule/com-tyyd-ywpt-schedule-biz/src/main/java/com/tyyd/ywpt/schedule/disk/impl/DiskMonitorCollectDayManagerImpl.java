/**   
* @Title: DiskMonitorCollectDayManagerImpl.java 
* @Package com.tyyd.ywpt.schedule.disk.impl 
* @Description:  
* @author wangyu   
* @date 2015-5-28 下午5:34:50 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.disk.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tyyd.ywpt.biz.util.DateUtils;
import com.tyyd.ywpt.dao.configration.host.HostConfigDAO;
import com.tyyd.ywpt.dao.configration.host.dataobject.HostConfigDomain;
import com.tyyd.ywpt.dao.core.collect.disk.DiskMonitorCollectDayDAO;
import com.tyyd.ywpt.dao.core.collect.disk.dataobject.DiskMonitorCollectDayDomain;
import com.tyyd.ywpt.schedule.disk.DiskMonitorCollectDayManager;

/**
 * @author wangyu
 *
 */
public class DiskMonitorCollectDayManagerImpl implements
		DiskMonitorCollectDayManager {

	private static final Logger LOGGER = Logger.getLogger(DiskMonitorCollectDayManagerImpl.class);
	
	@Resource
	private DiskMonitorCollectDayDAO diskMonitorCollectDayDAO;
	
	@Resource
	private HostConfigDAO hostConfigDAO;
	
	
	/**
	 * 生成数据
	 * @param hostId
	 * @param collectDate
	 */
	private void buildDiskDay(String hostId, String collectDate) {
		
		LOGGER.info(String.format("开始生成日磁盘数据 {hostId:%s,collectDate:%s}", hostId,collectDate));
		
		long start = System.currentTimeMillis();
		//获取该时间最后一次的snapId
		String nextDay = DateUtils.addDateByFormat(DateUtils.StringToDate(collectDate, "yyyyMMdd"), 1, "yyyyMMdd");
		
		String snapId = diskMonitorCollectDayDAO.getMaxSnapId(hostId, collectDate,nextDay);
		if(StringUtils.isBlank(snapId)){
			LOGGER.error(String.format("开始生成日磁盘数据 {hostId:%s,collectDate:%s}有错，原因是未获取snapId", hostId,collectDate));
			return;
		}
		
		//先删除可能存在的数据
		diskMonitorCollectDayDAO.delCurrentDiskData(hostId, collectDate);
		
		//添加数据
		diskMonitorCollectDayDAO.collectDiskInfo(hostId, collectDate, snapId, 0f);
		
		//获取昨日的统计数据
		Date current = DateUtils.StringToDate(collectDate, "yyyyMMdd");
		String lastDay = DateUtils.addDateByFormat(current, -1,"yyyyMMdd");
		List<DiskMonitorCollectDayDomain> lastDayDiskList = diskMonitorCollectDayDAO.getLastDayDiskInfo(hostId, lastDay);
		
		//更新上一次的数据
		if(CollectionUtils.isNotEmpty(lastDayDiskList)){
			for(DiskMonitorCollectDayDomain obj : lastDayDiskList){
				if(obj != null && StringUtils.isNotBlank(obj.getDiskName())){
					Float lastDayUsed = (obj.getUsed() == null ) ? 0f : obj.getUsed();
					
					//更新
					diskMonitorCollectDayDAO.updateDiskInfo(hostId, collectDate, obj.getDiskName(), lastDayUsed);
				}
			}
		}
		
		long end = System.currentTimeMillis();
		LOGGER.info(String.format("开始生成日磁盘数据 {hostId:%s,collectDate:%s}完成，总计花费%s豪秒", hostId,collectDate,(end-start)));
	}


	@Override
	public void doTask() {
		LOGGER.info(String.format("开始调度磁盘生成"));
		long start = System.currentTimeMillis();
		
		List<HostConfigDomain> hostList = hostConfigDAO.listNormalHostConfig();
		
		if(CollectionUtils.isNotEmpty(hostList)){
			for(HostConfigDomain domain : hostList){
				
				//生成数据
				this.doHostTask(domain.getHostId());
				
			}
		}
		
		long end = System.currentTimeMillis();
		LOGGER.info(String.format("开始调度磁盘生成完成，总计花费%s豪秒",(end-start)));
	}


	/**
	 * @param hostId
	 * @param startDate
	 */
	private void produceDiskDayInfo(String hostId, String startDate) {
		Date lastDay = DateUtils.getDelayDay(-1);
		Date sDate = DateUtils.StringToDate(startDate, "yyyyMMdd");
		
		int bwt = this.calBeetweenDays(sDate,lastDay);
		
		for(int i=1;i<bwt;i++){
			String tmpDate = DateUtils.addDateByFormat(sDate, i, "yyyyMMdd");
			if(StringUtils.isNotBlank(tmpDate)){
				this.buildDiskDay(hostId, tmpDate);
			}
		}
	}

	
	/**
	 * 计算天数差值
	 * @param startDate
	 * @return
	 */
	private int calBeetweenDays (Date sDate,Date lastDay){
		
		int bwt = DateUtils.daysBetween(sDate , lastDay);
		
		if(bwt <= 0){
			return -1;
		}
		
		return bwt;
	}
	

	/**
	 * @param hostId
	 * @return
	 */
	private String getStartDate(String hostId) {

		if(StringUtils.isBlank(hostId)){
			return StringUtils.EMPTY;
		}
		
		//从统计表中拿最后一次的时间
		String startDate = diskMonitorCollectDayDAO.getMaxDiskCollectDate(hostId);
		if(StringUtils.isBlank(startDate)){
			Date lastMonth = DateUtils.getDelayDay(-30);
			String strDate = DateUtils.DateToString(lastMonth, "yyyy-MM-dd");
			startDate = diskMonitorCollectDayDAO.getMinDiskHisCollectDate(hostId,strDate);
		}
		
		if(StringUtils.isBlank(startDate)){
			return StringUtils.EMPTY;
		}
		
		return startDate;
	}


	@Override
	public void doHostTask(String hostId) {
		
		//获取最后一次时间
		String startDate = this.getStartDate(hostId);
		
		if(StringUtils.isNotBlank(startDate)){
			//统计数据
			this.produceDiskDayInfo(hostId, startDate);
		}
		
	}

}
