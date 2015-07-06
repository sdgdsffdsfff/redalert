/**   
* @Title: MinitorAlertRecordDisplayManagerImpl.java 
* @Package com.tyyd.ywpt.report.biz.alert.impl 
* @Description:  
* @author wangyu   
* @date 2014-11-4 上午9:23:18 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.alert.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.tyyd.ywpt.biz.util.DateUtils;
import com.tyyd.ywpt.dao.alert.record.MonitorAlertRecordDAO;
import com.tyyd.ywpt.dao.alert.record.dataobject.MoitorAlertRecordDomain;
import com.tyyd.ywpt.dao.base.dataobject.PageQuery;
import com.tyyd.ywpt.report.biz.alert.MinitorAlertRecordDisplayManager;
import com.tyyd.ywpt.report.biz.alert.dto.MonitorAlertDisplayDTO;

/**
 * @author wangyu
 *
 */
public class MinitorAlertRecordDisplayManagerImpl implements
		MinitorAlertRecordDisplayManager {

	private final static Integer PAGE_SIZE = 20; 
	private final static String YYYY_MM_DD = "yyyy-MM-dd";
	
	@Resource
	private MonitorAlertRecordDAO monitorAlertRecordDAO;
	
	@Override
	public PageQuery<List<MonitorAlertDisplayDTO>> listAllAlertComplex(
			Integer currentPage, String queryDay) {
		
		PageQuery<List<MoitorAlertRecordDomain>> pageQuery = new PageQuery<List<MoitorAlertRecordDomain>>();
		pageQuery.setCurrentPage(currentPage);
		pageQuery.setPageSize(PAGE_SIZE);
		
		if(StringUtils.isBlank(queryDay)){
			Date queryDate = DateUtils.getDelayDay(-3);
			queryDay = DateUtils.DateToString(queryDate, YYYY_MM_DD);
		}
		
		pageQuery = monitorAlertRecordDAO.listAllPageQuery(pageQuery, queryDay);
		
		PageQuery<List<MonitorAlertDisplayDTO>> result = convertPageQuery(pageQuery);
		return result;
	}

	/**
	 * @param pageQuery
	 * @return
	 */
	private PageQuery<List<MonitorAlertDisplayDTO>> convertPageQuery(
			PageQuery<List<MoitorAlertRecordDomain>> pageQuery) {
		PageQuery<List<MonitorAlertDisplayDTO>> result = new PageQuery<List<MonitorAlertDisplayDTO>>();
		if(pageQuery == null){
			result.setTotalRows(0);
			return result;
		}
		List<MonitorAlertDisplayDTO> dataList = new ArrayList<MonitorAlertDisplayDTO>();
		for(MoitorAlertRecordDomain domain : pageQuery.getRecords()){
			MonitorAlertDisplayDTO dto = new MonitorAlertDisplayDTO();
			try {
				BeanUtils.copyProperties(dto, domain);
				String gmtCreated = DateUtils.DateToString(domain.getGmtCreated(), "yyyy-MM-dd HH:mm:ss");
				dto.setGmtCreatedStr(gmtCreated);
				String gmtModifed = DateUtils.DateToString(domain.getGmtModifed(), "yyyy-MM-dd HH:mm:ss");
				dto.setGmtModifedStr(gmtModifed);
				String summary = StringUtils.substring(domain.getContent(), 0, 50);
				dto.setSummary(summary);
				
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			dataList.add(dto);
		}
		
		result.setRecords(dataList);
		result.setCurrentPage(pageQuery.getCurrentPage());
		result.setPageSize(pageQuery.getPageSize());
		result.setTotalRows(pageQuery.getTotalRows());
		
		
		return result;
	}

	@Override
	public PageQuery<List<MonitorAlertDisplayDTO>> listHostAlertComplex(
			Integer currentPage, String hostId,String startDate,String endDate) {
		
		PageQuery<List<MoitorAlertRecordDomain>> pageQuery = new PageQuery<List<MoitorAlertRecordDomain>>();
		pageQuery.setCurrentPage(currentPage);
		pageQuery.setPageSize(PAGE_SIZE);
		
		pageQuery = monitorAlertRecordDAO.listHostPageQuery(pageQuery, hostId,startDate,endDate);
		
		PageQuery<List<MonitorAlertDisplayDTO>> result = convertPageQuery(pageQuery);
		return result;
	}

	@Override
	public PageQuery<List<MonitorAlertDisplayDTO>> listDbAlertComplex(
			Integer currentPage, String dbId,
			Integer dbType,String startDate,String endDate) {
		
		PageQuery<List<MoitorAlertRecordDomain>> pageQuery = new PageQuery<List<MoitorAlertRecordDomain>>();
		pageQuery.setCurrentPage(currentPage);
		pageQuery.setPageSize(PAGE_SIZE);
		
		pageQuery = monitorAlertRecordDAO.listDbPageQuery(pageQuery, dbId, dbType,startDate,endDate);
		
		PageQuery<List<MonitorAlertDisplayDTO>> result = convertPageQuery(pageQuery);
		return result;
		
	}

}
