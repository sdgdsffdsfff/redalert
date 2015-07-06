/**   
* @Title: MySQLAwrReviewManagerImpl.java 
* @Package com.tyyd.ywpt.report.biz.stat.mysql.impl 
* @Description:  
* @author wangyu   
* @date 2015-5-20 上午10:00:51 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.stat.mysql.impl;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

import com.tyyd.ywpt.biz.util.DateUtils;
import com.tyyd.ywpt.dao.core.collect.even.mysql.MysqlQuotaCollectDayDAO;
import com.tyyd.ywpt.dao.core.collect.even.mysql.dataobject.MysqlQuotaCollectDayDomain;
import com.tyyd.ywpt.dao.stat.mysql.MyAwrReviewDAO;
import com.tyyd.ywpt.dao.stat.mysql.dataobject.MyawrQueryReviewDomain;
import com.tyyd.ywpt.dao.stat.mysql.dataobject.MyawrQueryReviewHistoryDomain;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleDataBO;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleGeneralReportDataBO;
import com.tyyd.ywpt.report.biz.quota.dto.multi.ReportOrgFormatDataBO;
import com.tyyd.ywpt.report.biz.quota.impl.GeneralQuotaReportDataManagerImpl;
import com.tyyd.ywpt.report.biz.stat.mysql.MySQLAwrReviewManager;
import com.tyyd.ywpt.report.biz.stat.mysql.dto.MySQLAwrReviewEnum;

/**
 * @author wangyu
 *
 */
public class MySQLAwrReviewManagerImpl implements MySQLAwrReviewManager {

	public static final Logger LOGGER = Logger.getLogger(GeneralQuotaReportDataManagerImpl.class);  
	
	
	@Resource
	private MyAwrReviewDAO myAwrReviewDAO;
	
	@Resource
	private MysqlQuotaCollectDayDAO mysqlQuotaCollectDayDAO;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MyawrQueryReviewHistoryDomain> listMyAwrQueryReviewHistory(
			String mysqlCollectDayId,String sortColumn,String sortType) {

		//判空
		if(StringUtils.isBlank(mysqlCollectDayId)){
			return Collections.EMPTY_LIST;
		}
		
		//获取对应的上下文
		MysqlQuotaCollectDayDomain mysqlQuotaCollectDayDomain = mysqlQuotaCollectDayDAO.getMysqlMonitorDataById(mysqlCollectDayId);
		if(mysqlQuotaCollectDayDomain == null || mysqlQuotaCollectDayDomain.getGmtCreated() == null){
			return Collections.EMPTY_LIST;
		}
		
		//格式化日期
		Date gmtCreated = mysqlQuotaCollectDayDomain.getGmtCreated();
		String start = DateUtils.addMonute(gmtCreated, -10);
		String end = DateUtils.addMonute(gmtCreated, 10);
		
		String dbId = mysqlQuotaCollectDayDomain.getDbId();
		
		sortColumn = getSortColumn(sortColumn);
		sortType = getSortType(sortType);
	
		List<MyawrQueryReviewHistoryDomain> list = myAwrReviewDAO.listMyAwrQueryReviewHistory(dbId, start, end ,sortColumn,sortType);
		
		return list;
	}

	/**
	 * @param sortType
	 * @return
	 */
	private String getSortType(String sortType) {
		String result = "asc";
		
		//sortType
		if(StringUtils.isBlank(sortType)){
			sortType = StringUtils.EMPTY;
		}
		
		if(sortType.equals("0")){
			result = "asc";
		}
		
		if(sortType.equals("1")){
			result = "desc";
		}
		
		return result;
	}

	/**
	 * @param sortColumn
	 * @return
	 */
	private String getSortColumn(String sortColumn) {
		String result = "";
		if(StringUtils.isBlank(sortColumn)){
			sortColumn = StringUtils.EMPTY;
		}
		
		//获取column
		sortColumn = sortColumn.replace("sql_", "");
		
		result = MySQLAwrReviewEnum.getColumn(sortColumn);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, MultipleGeneralReportDataBO> listMySQLAwrReview(
			String dbId,String start,String end, String checkSum) {

		
		//判空
		if (StringUtils.isBlank(dbId) 
				|| StringUtils.isBlank(start)
				|| StringUtils.isBlank(end) 
				|| StringUtils.isBlank(checkSum)) {
			return MapUtils.EMPTY_MAP;
		}
		
		List<MyawrQueryReviewHistoryDomain> list = myAwrReviewDAO.loadMyAwrQueryReviewHistoryByCheckSum(dbId, start, end, checkSum);
		
		if(CollectionUtils.isEmpty(list)){
			return MapUtils.EMPTY_MAP;
		}
		
		Map<String, MultipleGeneralReportDataBO> result = toReportData(list);
		
		return result;
	}

	/**
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<String, MultipleGeneralReportDataBO> toReportData(
			List<MyawrQueryReviewHistoryDomain> list) {

		if(CollectionUtils.isEmpty(list)){
			return MapUtils.EMPTY_MAP;
		}
		
		Map<String, MultipleGeneralReportDataBO> result = new HashMap<String, MultipleGeneralReportDataBO>();
		Map<String,String> enumMap = MySQLAwrReviewEnum.keys();
		
		Iterator<String> keysIterator = enumMap.keySet().iterator();
		while(keysIterator.hasNext()){
			String key = keysIterator.next();
			String comment = enumMap.get(key);
			
			//转化成报表对象
			MultipleGeneralReportDataBO reportData = this.changeReportData(key,comment,list);
			
			result.put(key, reportData);
		}
		
		return result;
	}

	
	/**
	 * 转化为报表对象
	 * @param keyWord
	 * @param reportName
	 * @param list
	 * @return
	 */
	private MultipleGeneralReportDataBO changeReportData(String keyWord,String reportName,List<MyawrQueryReviewHistoryDomain> list){
		MultipleGeneralReportDataBO result = new MultipleGeneralReportDataBO();
		result.setReportName(reportName+"报表");
		MultipleDataBO multipleDataBO = new MultipleDataBO();
		multipleDataBO.setType("spline");
		multipleDataBO.setName(reportName);
		List<ReportOrgFormatDataBO> datalist = new ArrayList<ReportOrgFormatDataBO>();
		
		for(MyawrQueryReviewHistoryDomain domain : list){
			
			Class<?> clazz = domain.getClass();
			PropertyDescriptor pd = null;
			Object obj = null;
			try {
				pd = new PropertyDescriptor(keyWord,clazz);
				Method method = pd.getReadMethod();
				obj = method.invoke(domain);
			} catch (IntrospectionException e) {
				LOGGER.error("转化对象异常,MySQL AWR 反射出错", e);
				return null;
			} catch (IllegalArgumentException e) {
				LOGGER.error("转化对象异常,MySQL AWR 反射出错", e);
				return null;
			} catch (IllegalAccessException e) {
				LOGGER.error("转化对象异常,MySQL AWR 反射出错", e);
				return null;
			} catch (InvocationTargetException e) {
				LOGGER.error("转化对象异常,MySQL AWR 反射出错", e);
				return null;
			}
			
			ReportOrgFormatDataBO dataBo = new ReportOrgFormatDataBO();
			dataBo.setX(Long.valueOf(domain.getGmtCreated()) * 1000);
			if(obj == null){
				System.out.println("null");
			}
			dataBo.setY(NumberUtils.toFloat(obj.toString()));
			
			datalist.add(dataBo);
		}
		
		multipleDataBO.setData(datalist);
		result.setSeriesData(new MultipleDataBO[]{multipleDataBO});
		return result;
	}
	

	
	@Override
	public MyawrQueryReviewDomain getMyawrQueryReview(String checkSum) {
		return myAwrReviewDAO.getMyawrQueryReview(checkSum);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.report.biz.stat.mysql.MySQLAwrReviewManager#checkMySQLAwrExists(java.lang.String)
	 */
	@Override
	public boolean checkMySQLAwrExists(String mysqlCollectDayId) {
		
		boolean isExists = Boolean.FALSE;

		//判空
		if(StringUtils.isBlank(mysqlCollectDayId)){
			return isExists;
		}
		
		//获取对应的上下文
		MysqlQuotaCollectDayDomain mysqlQuotaCollectDayDomain = mysqlQuotaCollectDayDAO.getMysqlMonitorDataById(mysqlCollectDayId);
		if(mysqlQuotaCollectDayDomain == null || mysqlQuotaCollectDayDomain.getGmtCreated() == null){
			return isExists;
		}
		
		//格式化日期
		Date gmtCreated = mysqlQuotaCollectDayDomain.getGmtCreated();
		String start = DateUtils.addMonute(gmtCreated, -10);
		String end = DateUtils.addMonute(gmtCreated, 10);
		String dbId = mysqlQuotaCollectDayDomain.getDbId();
				
		return myAwrReviewDAO.isExistsMyAwrQueryReviewHistory(dbId, start, end);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.report.biz.stat.mysql.MySQLAwrReviewManager#loadMysqlQuotaCollectDay(java.lang.String)
	 */
	@Override
	public MysqlQuotaCollectDayDomain loadMysqlQuotaCollectDay(String id) {
		MysqlQuotaCollectDayDomain mysqlQuotaCollectDayDomain = mysqlQuotaCollectDayDAO.getMysqlMonitorDataById(id);
		return mysqlQuotaCollectDayDomain;
	}

}
