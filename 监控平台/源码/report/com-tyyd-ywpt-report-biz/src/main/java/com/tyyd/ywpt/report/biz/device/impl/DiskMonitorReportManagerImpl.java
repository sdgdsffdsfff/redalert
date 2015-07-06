/**   
* @Title: DiskMonitorReportManagerImpl.java 
* @Package com.tyyd.ywpt.report.biz.device.impl 
* @Description:  
* @author wangyu   
* @date 2015-6-1 下午3:40:04 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.device.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.tyyd.ywpt.biz.util.MathUtils;
import com.tyyd.ywpt.dao.configration.host.HostConfigDAO;
import com.tyyd.ywpt.dao.configration.host.dataobject.HostConfigDomain;
import com.tyyd.ywpt.dao.core.collect.disk.DiskMonitorCollectDayDAO;
import com.tyyd.ywpt.dao.core.collect.disk.DiskMonitorDAO;
import com.tyyd.ywpt.dao.core.collect.disk.dataobject.DiskMonitorCollectDayDomain;
import com.tyyd.ywpt.dao.core.collect.disk.dataobject.DiskMonitorDomain;
import com.tyyd.ywpt.report.biz.device.DiskMonitorReportManager;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleDataBO;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleGeneralReportDataBO;
import com.tyyd.ywpt.report.biz.quota.dto.multi.ReportOrgFormatDataBO;
import com.tyyd.ywpt.report.biz.quota.dto.pie.PieDrillDownDataFormatBO;
import com.tyyd.ywpt.report.biz.quota.dto.pie.PieJsonDataFormatBO;
import com.tyyd.ywpt.report.biz.quota.dto.pie.PieReportOrgFormatDataBO;

/**
 * @author wangyu
 *
 */
public class DiskMonitorReportManagerImpl implements DiskMonitorReportManager{

	public static final int TABLE_COLUMN_SIZE = 3;
	
	@Resource
	private DiskMonitorDAO diskMonitorDAO;
	
	@Resource
	private DiskMonitorCollectDayDAO diskMonitorCollectDayDAO;

	@Resource
	private HostConfigDAO hostConfigDAO;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DiskMonitorDomain[]> listDiskMonitor(String hostId) {
		if(StringUtils.isBlank(hostId)){
			return Collections.EMPTY_LIST;
		}
		
		List<DiskMonitorDomain> list = diskMonitorDAO.listDiskMonitor(hostId);
		
		List<DiskMonitorDomain[]> result = this.changeListToTable(list);
		return result;
		
	}


	
	/**
	 * 表list转化成table展示
	 * @param list
	 * @return
	 */
	private List<DiskMonitorDomain[]> changeListToTable(List<DiskMonitorDomain> list){
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		
		List<DiskMonitorDomain[]> result = new ArrayList<DiskMonitorDomain[]>();
		//列表总计
		int total = list.size();
		
		//行数
		int listSize = total/TABLE_COLUMN_SIZE;
		if(total%TABLE_COLUMN_SIZE > 0){
			listSize = listSize + 1;
		}
	
		int index = 0;
		for(int i=0;i<listSize;i++){
			DiskMonitorDomain[] array = new DiskMonitorDomain[TABLE_COLUMN_SIZE];
			
			for(int j=0;j<TABLE_COLUMN_SIZE;j++){
				if(index < total - 1){
					
					DiskMonitorDomain obj = list.get(index);
					obj.setUsed(MathUtils.round(obj.getUsed()/1024, 2));
					obj.setRemain(MathUtils.round(obj.getRemain()/1024, 2));
					obj.setUsedPercent(MathUtils.round(obj.getUsedPercent(), 2));
					
					array[j] = obj;
				}else{
					array[j] = new DiskMonitorDomain();
				}
				
				
				index++;
			}
			
			result.add(array);
			
		}
		
		return result;
	}
	
	
	@Override
	public MultipleGeneralReportDataBO totalDiskReport(String hostId) {
		if(StringUtils.isBlank(hostId)){
			return null;
		}
		
		MultipleGeneralReportDataBO result = new MultipleGeneralReportDataBO();
		result.setReportName("磁盘总量走势");
		
		
		List<DiskMonitorCollectDayDomain> list = diskMonitorCollectDayDAO.sumDiskTotal(hostId);
		
		MultipleDataBO bo = this.convertListToReportData(list,"磁盘总量");
		if(bo == null){
			return null;
		}
		
		
		MultipleDataBO[] seriesData = new MultipleDataBO[]{bo};
		result.setSeriesData(seriesData);
		
		return result;
	}


	/**
	 * @param list
	 * @return
	 */
	private MultipleDataBO convertListToReportData(
			List<DiskMonitorCollectDayDomain> list,String name) {

		if(CollectionUtils.isNotEmpty(list)){
			MultipleDataBO multipleDataBO = new MultipleDataBO();
			multipleDataBO.setName(name);
			List<ReportOrgFormatDataBO> dataList = new ArrayList<ReportOrgFormatDataBO>();
			for(DiskMonitorCollectDayDomain domain : list){
				ReportOrgFormatDataBO reportOrgFormatDataBO = new ReportOrgFormatDataBO();
				reportOrgFormatDataBO.setX(NumberUtils.toLong(domain.getGmtCreated()+"000"));
				reportOrgFormatDataBO.setY(domain.getUsed());
				dataList.add(reportOrgFormatDataBO);
			}
			
			multipleDataBO.setData(dataList);
			
			return multipleDataBO;
		}
		
		return null;
	}



	@Override
	public MultipleGeneralReportDataBO deltaDiskReport(String hostId) {
		if(StringUtils.isBlank(hostId)){
			return null;
		}
		
		MultipleGeneralReportDataBO result = new MultipleGeneralReportDataBO();
		result.setReportName("磁盘增量走势");
		
		
		List<DiskMonitorCollectDayDomain> list = diskMonitorCollectDayDAO.sumDiskDelta(hostId);
		
		MultipleDataBO bo = this.convertListToReportData(list,"磁盘增量");
		if(bo == null){
			return null;
		}
		
		
		MultipleDataBO[] seriesData = new MultipleDataBO[]{bo};
		result.setSeriesData(seriesData);
		
		return result;
	}

	@Override
	public MultipleGeneralReportDataBO deltaDiskReportByDisk(String hostId,
			String diskName) {
		if(StringUtils.isBlank(hostId)){
			return null;
		}
		
		MultipleGeneralReportDataBO result = new MultipleGeneralReportDataBO();
		result.setReportName(String.format("磁盘[%s]增量走势",diskName));
		
		List<DiskMonitorCollectDayDomain> list = diskMonitorCollectDayDAO.sumDiskDeltaByDiskName(hostId, diskName);
		
		MultipleDataBO bo = this.convertListToReportData(list,"磁盘增量["+diskName+"]");
		if(bo == null){
			return null;
		}
		
		MultipleDataBO[] seriesData = new MultipleDataBO[]{bo};
		result.setSeriesData(seriesData);
		
		return result;
	}



	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.report.biz.device.DiskMonitorReportManager#totalDiskInfo(java.lang.String)
	 */
	@Override
	public DiskMonitorDomain totalDiskInfo(String hostId) {
		
		if(StringUtils.isBlank(hostId)){
			return null;
		}
		
		DiskMonitorDomain diskMonitorDomain = diskMonitorDAO.totalDiskInfo(hostId);
		
		if (diskMonitorDomain == null 
				|| diskMonitorDomain.getUsed() == null
				|| diskMonitorDomain.getRemain() == null) {
			return null;
		}
		
		Float used = diskMonitorDomain.getUsed();
		Float remain = diskMonitorDomain.getRemain();
		Float percent = 100 * used/(used+remain);
		
		//格式化 
		used = MathUtils.round(used/1024, 2);
		remain = MathUtils.round(remain/1024, 2);
		percent = MathUtils.round(percent, 2);
		
		diskMonitorDomain.setUsed(used);
		diskMonitorDomain.setRemain(remain);
		diskMonitorDomain.setUsedPercent(percent);
		
		return diskMonitorDomain;
	}



	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.report.biz.device.DiskMonitorReportManager#getHostInfo(java.lang.String)
	 */
	@Override
	public HostConfigDomain getHostInfo(String hostId) {
		return hostConfigDAO.getHostById(hostId);
	}



	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.report.biz.device.DiskMonitorReportManager#totalDiskInfoByDisk(java.lang.String, java.lang.String)
	 */
	@Override
	public MultipleGeneralReportDataBO totalDiskReportByDisk(String hostId, String diskName) {
		
		if(StringUtils.isBlank(hostId)){
			return null;
		}
		
		MultipleGeneralReportDataBO result = new MultipleGeneralReportDataBO();
		result.setReportName(String.format("磁盘[%s]总量走势",diskName));
		
		List<DiskMonitorCollectDayDomain> list = diskMonitorCollectDayDAO.sumDiskTotalByDisk(hostId, diskName);
		
		MultipleDataBO bo = this.convertListToReportData(list,"磁盘总量["+diskName+"]");
		if(bo == null){
			return null;
		}
		
		MultipleDataBO[] seriesData = new MultipleDataBO[]{bo};
		result.setSeriesData(seriesData);
		
		return result;
	}



	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.report.biz.device.DiskMonitorReportManager#theLastDiskInfo(java.lang.String)
	 */
	@Override
	public PieReportOrgFormatDataBO theLastDiskInfo(String hostId) {

		if(StringUtils.isBlank(hostId)){
			return null;
		}
		
		DiskMonitorDomain diskMonitorDomain = diskMonitorDAO.totalDiskInfo(hostId);
		Float used = diskMonitorDomain.getUsed()==null?0f:diskMonitorDomain.getUsed();
		Float remain = diskMonitorDomain.getRemain()==null?0f:diskMonitorDomain.getRemain();
		Float total = used + remain;
		
		
		
		PieReportOrgFormatDataBO result = new PieReportOrgFormatDataBO();
		
		List<DiskMonitorDomain> list = diskMonitorDAO.listDiskMonitor(hostId);
		
		if(CollectionUtils.isNotEmpty(list)){
			//大类
			String[] categories = new String[list.size()];
			
			//内容
			List<PieJsonDataFormatBO> dataList = new ArrayList<PieJsonDataFormatBO>();
			
			
			for(int i=0;i<list.size();i++){
				DiskMonitorDomain domain = list.get(i);
				
						
				String diskName = domain.getDiskName();
				Float singleDiskUsed = domain.getUsed()==null?0f:domain.getUsed();
				singleDiskUsed = MathUtils.round(100 * singleDiskUsed/total,2);
				
				
				PieJsonDataFormatBO pieJsonDataFormatBO = new PieJsonDataFormatBO();
				//设置颜色
				pieJsonDataFormatBO.setColor("colors["+i+"]");
				pieJsonDataFormatBO.setColorIndex(i);
				
				//设置大类数值
				pieJsonDataFormatBO.setY(singleDiskUsed);
				
				//设置小类
				Float usedPercent = MathUtils.round(100 * domain.getUsed() / total,2);
				Float remainPercent = MathUtils.round(100 * domain.getRemain() / total,2);
				
				PieDrillDownDataFormatBO drilldown = new PieDrillDownDataFormatBO();
				drilldown.setName(diskName+" version");
				drilldown.setColor("colors["+i+"]");
				drilldown.setCategories(new String[]{"["+diskName+"]已用","["+diskName+"]剩余"});
				drilldown.setData(new Float[]{usedPercent,remainPercent});
				drilldown.setColorIndex(i);
				
				
				
				pieJsonDataFormatBO.setDrilldown(drilldown);
				
				//设置大类名称
				categories[i] = diskName;
				
				
				dataList.add(pieJsonDataFormatBO); 
			}
			
			
			result.setCategories(categories);
			result.setDataList(dataList);
		}
		
		
		return result;
	}






//	/* (non-Javadoc)
//	 * @see com.tyyd.ywpt.report.biz.device.DiskMonitorReportManager#theLastDiskInfo(java.lang.String)
//	 */
//	@Override
//	public List<Object[]> theLastDiskInfo(String hostId) {
//		
//		if(StringUtils.isBlank(hostId)){
//			return null;
//		}
//		
//		DiskMonitorDomain diskMonitorDomain = diskMonitorDAO.totalDiskInfo(hostId);
//		Float used = diskMonitorDomain.getUsed()==null?0f:diskMonitorDomain.getUsed();
//		Float remain = diskMonitorDomain.getRemain()==null?0f:diskMonitorDomain.getRemain();
//		Float total = used + remain;
//		
//		List<Object[]> dataList = new ArrayList<Object[]>();
//		List<DiskMonitorDomain> list = diskMonitorDAO.listDiskMonitor(hostId);
//		
//		if(CollectionUtils.isNotEmpty(list)){
//			for(DiskMonitorDomain domain : list){
//				Object[] obj = new Object[2];
//				obj[0] = domain.getDiskName();
//				Float singleDiskUsed = domain.getUsed()==null?0f:domain.getUsed();
//				obj[1] = MathUtils.round(100 * singleDiskUsed/total,2);
//				dataList.add(obj);
//			}
//		}
//		
//		return dataList;
//	}
	
}
