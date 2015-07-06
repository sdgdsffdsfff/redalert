/**   
* @Title: TbsMonitorReportManagerImpl.java 
* @Package com.tyyd.ywpt.report.biz.device.impl 
* @Description:  
* @author wangyu   
* @date 2015-6-3 下午1:21:07 
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
import com.tyyd.ywpt.dao.configration.db.DbConfigDAO;
import com.tyyd.ywpt.dao.configration.db.dataobject.DbConfigDomain;
import com.tyyd.ywpt.dao.core.collect.datafile.DbTableSpaceCollectDayDAO;
import com.tyyd.ywpt.dao.core.collect.datafile.DbTableSpaceDAO;
import com.tyyd.ywpt.dao.core.collect.datafile.dataobject.DbTableSpaceCollectDayDomain;
import com.tyyd.ywpt.dao.core.collect.datafile.dataobject.DbTableSpaceDomain;
import com.tyyd.ywpt.report.biz.device.TbsMonitorReportManager;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleDataBO;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleGeneralReportDataBO;
import com.tyyd.ywpt.report.biz.quota.dto.multi.ReportOrgFormatDataBO;

/**
 * @author wangyu
 *
 */
public class TbsMonitorReportManagerImpl implements TbsMonitorReportManager {

	public static final int TABLE_COLUMN_SIZE = 3;
	
	@Resource
	private DbConfigDAO dbConfigDAO;
	
	@Resource
	private DbTableSpaceCollectDayDAO dbTableSpaceCollectDayDAO;
	
	@Resource
	private DbTableSpaceDAO dbTableSpaceDAO;

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.report.biz.device.TbsMonitorReportManager#getDataBaseInfo(java.lang.String)
	 */
	@Override
	public DbConfigDomain getDataBaseInfo(String dbId) {
		return dbConfigDAO.getDataBaseConfig(dbId);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.report.biz.device.TbsMonitorReportManager#listTbsMonitor(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DbTableSpaceDomain[]> listTbsMonitor(String dbId) {
		if(StringUtils.isBlank(dbId)){
			return Collections.EMPTY_LIST;
		}
		
		List<DbTableSpaceDomain> list = dbTableSpaceDAO.listDbTableSpace(dbId);
		
		List<DbTableSpaceDomain[]> result = this.changeListToTable(list);
		return result;
	}
	
	

	/**
	 * 表list转化成table展示
	 * @param list
	 * @return
	 */
	private List<DbTableSpaceDomain[]> changeListToTable(List<DbTableSpaceDomain> list){
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		
		List<DbTableSpaceDomain[]> result = new ArrayList<DbTableSpaceDomain[]>();
		//列表总计
		int total = list.size();
		
		//行数
		int listSize = total/TABLE_COLUMN_SIZE;
		if(total%TABLE_COLUMN_SIZE > 0){
			listSize = listSize + 1;
		}
	
		int index = 0;
		for(int i=0;i<listSize;i++){
			DbTableSpaceDomain[] array = new DbTableSpaceDomain[TABLE_COLUMN_SIZE];
			
			for(int j=0;j<TABLE_COLUMN_SIZE;j++){
				if(index < total - 1){
					
					DbTableSpaceDomain obj = list.get(index);
					obj.setUsedTbs(MathUtils.round(obj.getUsedTbs()/1024, 2));
					obj.setMaxTbs(MathUtils.round(obj.getMaxTbs()/1024, 2));
					obj.setUsePercent(MathUtils.round(obj.getUsePercent(), 2));
					
					array[j] = obj;
				}else{
					array[j] = new DbTableSpaceDomain();
				}
				
				
				index++;
			}
			
			result.add(array);
			
		}
		
		return result;
	}
	

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.report.biz.device.TbsMonitorReportManager#totalTbsInfo(java.lang.String)
	 */
	@Override
	public DbTableSpaceDomain totalTbsInfo(String dbId) {
		if(StringUtils.isBlank(dbId)){
			return null;
		}
		
		DbTableSpaceDomain dbTableSpaceDomain = dbTableSpaceDAO.totalTbsInfo(dbId);
		
		if (dbTableSpaceDomain == null 
				|| dbTableSpaceDomain.getUsedTbs() == null
				|| dbTableSpaceDomain.getMaxTbs() == null) {
			return null;
		}
		
		Float used = dbTableSpaceDomain.getUsedTbs();
		Float max = dbTableSpaceDomain.getMaxTbs();
		Float percent = 100 * used/max;
		
		//格式化 
		used = MathUtils.round(used/1024, 2);
		max = MathUtils.round(max/1024, 2);
		percent = MathUtils.round(percent, 2);
		
		dbTableSpaceDomain.setUsedTbs(used);
		dbTableSpaceDomain.setMaxTbs(max);
		dbTableSpaceDomain.setUsePercent(percent);
		
		return dbTableSpaceDomain;
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.report.biz.device.TbsMonitorReportManager#totalTbsReport(java.lang.String)
	 */
	@Override
	public MultipleGeneralReportDataBO totalTbsReport(String dbId) {
		if(StringUtils.isBlank(dbId)){
			return null;
		}
		
		MultipleGeneralReportDataBO result = new MultipleGeneralReportDataBO();
		result.setReportName("表空间总量走势");
		
		
		List<DbTableSpaceCollectDayDomain> list = dbTableSpaceCollectDayDAO.sumTbsTotal(dbId);
		
		MultipleDataBO bo = this.convertListToReportData(list,"表空间总量");
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
			List<DbTableSpaceCollectDayDomain> list,String name) {

		if(CollectionUtils.isNotEmpty(list)){
			MultipleDataBO multipleDataBO = new MultipleDataBO();
			multipleDataBO.setName(name);
			List<ReportOrgFormatDataBO> dataList = new ArrayList<ReportOrgFormatDataBO>();
			for(DbTableSpaceCollectDayDomain domain : list){
				ReportOrgFormatDataBO reportOrgFormatDataBO = new ReportOrgFormatDataBO();
				reportOrgFormatDataBO.setX(NumberUtils.toLong(domain.getGmtCreated()+"000"));
				reportOrgFormatDataBO.setY(domain.getUsedTbs());
				dataList.add(reportOrgFormatDataBO);
			}
			
			multipleDataBO.setData(dataList);
			
			return multipleDataBO;
		}
		
		return null;
	}

	
	
	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.report.biz.device.TbsMonitorReportManager#deltaTbsReport(java.lang.String)
	 */
	@Override
	public MultipleGeneralReportDataBO deltaTbsReport(String dbId) {
		if(StringUtils.isBlank(dbId)){
			return null;
		}
		
		MultipleGeneralReportDataBO result = new MultipleGeneralReportDataBO();
		result.setReportName("表空间增量走势");
		
		
		List<DbTableSpaceCollectDayDomain> list = dbTableSpaceCollectDayDAO.sumTbsDelta(dbId);
		
		MultipleDataBO bo = this.convertListToReportData(list,"表空间增量");
		if(bo == null){
			return null;
		}
		
		
		MultipleDataBO[] seriesData = new MultipleDataBO[]{bo};
		result.setSeriesData(seriesData);
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.report.biz.device.TbsMonitorReportManager#deltaTbsReportByTbs(java.lang.String, java.lang.String)
	 */
	@Override
	public MultipleGeneralReportDataBO deltaTbsReportByTbs(String dbId,
			String tbsName) {
		if(StringUtils.isBlank(dbId) 
				|| StringUtils.isBlank(tbsName)){
			return null;
		}
		
		MultipleGeneralReportDataBO result = new MultipleGeneralReportDataBO();
		result.setReportName(String.format("表空间[%s]增量走势",tbsName));
		
		List<DbTableSpaceCollectDayDomain> list = dbTableSpaceCollectDayDAO.sumTbsDeltaByTbsName(dbId, tbsName);
		
		MultipleDataBO bo = this.convertListToReportData(list,"表空间增量["+tbsName+"]");
		if(bo == null){
			return null;
		}
		
		MultipleDataBO[] seriesData = new MultipleDataBO[]{bo};
		result.setSeriesData(seriesData);
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.report.biz.device.TbsMonitorReportManager#totalTbsReportByTbs(java.lang.String, java.lang.String)
	 */
	@Override
	public MultipleGeneralReportDataBO totalTbsReportByTbs(String dbId,
			String tbsName) {
		if(StringUtils.isBlank(dbId) || StringUtils.isBlank(tbsName)){
			return null;
		}
		
		MultipleGeneralReportDataBO result = new MultipleGeneralReportDataBO();
		result.setReportName(String.format("表空间[%s]总量走势",tbsName));
		
		List<DbTableSpaceCollectDayDomain> list = dbTableSpaceCollectDayDAO.sumTbsTotalByTbs(dbId, tbsName);
		
		MultipleDataBO bo = this.convertListToReportData(list,"表空间总量["+tbsName+"]");
		if(bo == null){
			return null;
		}
		
		MultipleDataBO[] seriesData = new MultipleDataBO[]{bo};
		result.setSeriesData(seriesData);
		
		return result;
	}
	
	
}
