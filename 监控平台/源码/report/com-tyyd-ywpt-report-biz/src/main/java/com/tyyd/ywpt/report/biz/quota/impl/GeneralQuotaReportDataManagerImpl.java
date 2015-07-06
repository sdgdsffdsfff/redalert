/**   
* @Title: GeneralQuotaReportDataManagerImpl.java 
* @Package com.tyyd.ywpt.report.biz.quota.impl 
* @Description:  
* @author wangyu   
* @date 2014-9-2 下午2:39:13 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.quota.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tyyd.ywpt.biz.dict.MySQLCollectQuotaEnum;
import com.tyyd.ywpt.biz.dict.OracleCollectQuotaEnum;
import com.tyyd.ywpt.biz.dict.ServerCollectQuotaEnum;
import com.tyyd.ywpt.biz.dict.SysTypeEnum;
import com.tyyd.ywpt.biz.dict.TogetherStatTypeEnum;
import com.tyyd.ywpt.biz.util.DateUtils;
import com.tyyd.ywpt.dao.baseline.BaseLineRecordDAO;
import com.tyyd.ywpt.dao.baseline.dataobject.BaseLineRecordDomain;
import com.tyyd.ywpt.dao.configration.db.DbConfigDAO;
import com.tyyd.ywpt.dao.configration.db.dataobject.DbConfigDomain;
import com.tyyd.ywpt.dao.configration.host.HostConfigDAO;
import com.tyyd.ywpt.dao.configration.host.dataobject.HostConfigDomain;
import com.tyyd.ywpt.dao.core.collect.even.host.HostQuotaCollectDayDAO;
import com.tyyd.ywpt.dao.core.collect.even.host.dataobject.HostQuotaCollectDayDomain;
import com.tyyd.ywpt.dao.core.collect.even.mysql.MysqlQuotaCollectDayDAO;
import com.tyyd.ywpt.dao.core.collect.even.mysql.dataobject.MysqlQuotaCollectDayDomain;
import com.tyyd.ywpt.dao.core.collect.even.oracle.OracleQuotaCollectDayDAO;
import com.tyyd.ywpt.dao.core.collect.even.oracle.dataobject.OracleQuotaCollectDayDomain;
import com.tyyd.ywpt.dao.core.collect.together.general.GeneralQuotaTogetherDAO;
import com.tyyd.ywpt.dao.core.collect.together.general.dataobject.GeneralQuotaTogetherDomain;
import com.tyyd.ywpt.dao.core.quota.QuotaModelDAO;
import com.tyyd.ywpt.dao.core.quota.dataobject.QuotaModelDomain;
import com.tyyd.ywpt.report.biz.quota.GeneralQuotaReportDataManager;
import com.tyyd.ywpt.report.biz.quota.baseline.BaseLineHandlerManager;
import com.tyyd.ywpt.report.biz.quota.dto.GeneralMonitorBO;
import com.tyyd.ywpt.report.biz.quota.dto.GeneralQuotaBO;
import com.tyyd.ywpt.report.biz.quota.dto.GeneralQuotaReportListBO;
import com.tyyd.ywpt.report.biz.quota.dto.baseline.BaseLineReportDTO;
import com.tyyd.ywpt.report.biz.quota.dto.baseline.BaseLineReportDataBO;
import com.tyyd.ywpt.report.biz.quota.dto.baseline.BaseLineReportFormatDataBO;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleDataBO;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleGeneralReportDataBO;
import com.tyyd.ywpt.report.biz.quota.dto.multi.ReportOrgFormatDataBO;

/**
 * @author wangyu
 *
 */
public class GeneralQuotaReportDataManagerImpl implements
		GeneralQuotaReportDataManager {

	private static final Integer PAGE_SIZE = 500;
	public static final Logger LOGGER = Logger.getLogger(GeneralQuotaReportDataManagerImpl.class);  
	
	
	@Resource
	private HostQuotaCollectDayDAO hostQuotaCollectDayDAO;
	
	@Resource
	private OracleQuotaCollectDayDAO oracleQuotaCollectDayDAO;
	
	@Resource
	private MysqlQuotaCollectDayDAO mysqlQuotaCollectDayDAO;
	
	@Resource
	private QuotaModelDAO quotaModelDAO;
	
	@Resource
	private HostConfigDAO hostConfigDAO;
	
	@Resource
	private DbConfigDAO dbConfigDAO;
	
	@Resource
	private GeneralQuotaTogetherDAO generalQuotaTogetherDAO;
	
	@Resource
	private BaseLineRecordDAO baseLineRecordDAO;
	
	@Resource
	private BaseLineHandlerManager baseLineHandlerManager;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<GeneralQuotaReportListBO> listGeneralQuotaReportData(
			String monitorId, Integer sysType, String quotaId,
			String gmtCreated, Integer pageSize,String startDate,String endDate) {
		
		if(pageSize == null || pageSize < 0){
			pageSize = PAGE_SIZE;
		}
		
		if(sysType == null){
			LOGGER.error("类型参数不能为空");
			return Collections.EMPTY_LIST;
		}
		
		if(SysTypeEnum.server.getVal().intValue() != sysType.intValue() 
				&& SysTypeEnum.Oracle.getVal().intValue() != sysType.intValue() 
				&& SysTypeEnum.MySQL.getVal().intValue() != sysType.intValue() ){
			LOGGER.error(String.format("类型参数不符合预期值,当前值是[%s]",sysType));
			return Collections.EMPTY_LIST;
		}
		
		
		List<GeneralQuotaReportListBO> result = new ArrayList<GeneralQuotaReportListBO>();
		
		//主机
		if(SysTypeEnum.server.getVal().intValue() == sysType.intValue()){
			result = getHostReportData(monitorId, quotaId, 1000 ,startDate,endDate);
			
		}else if(SysTypeEnum.Oracle.getVal().intValue() == sysType.intValue()){
			result = getOracleReportData(monitorId, quotaId, pageSize,startDate,endDate);
		
		}else if(SysTypeEnum.MySQL.getVal().intValue() == sysType.intValue()){
			result = getMySQLReportData(monitorId, quotaId, pageSize,startDate,endDate);
		
		}
		
		return result;
	}

	/**
	 * 
	 * MySQL数据
	 * @param monitorId
	 * @param quotaId
	 * @param pageSize
	 * @return
	 */
	private List<GeneralQuotaReportListBO> getMySQLReportData(
			String monitorId, String quotaId, Integer pageSize,String startDate,String endDate) {

		List<GeneralQuotaReportListBO> dataList = new ArrayList<GeneralQuotaReportListBO>();
		
		List<MysqlQuotaCollectDayDomain> list = mysqlQuotaCollectDayDAO.listReportData(monitorId, quotaId, pageSize,startDate,endDate);
		if(CollectionUtils.isNotEmpty(list)){
			for(MysqlQuotaCollectDayDomain domain : list){
				GeneralQuotaReportListBO dto = new GeneralQuotaReportListBO();
				dto.setQuotaValue(domain.getQuotaValue());
				dto.setGmtCreatedDate(domain.getGmtCreatedTimeStamp());
				dto.setId(domain.getId());
				dataList.add(dto);
			}
		}
		
		return dataList;
	}

	/**
	 * 
	 * Oracle数据
	 * @param monitorId
	 * @param quotaId
	 * @param pageSize
	 * @return
	 */
	private List<GeneralQuotaReportListBO> getOracleReportData(
			String monitorId, String quotaId, Integer pageSize,String startDate,String endDate) {
		List<GeneralQuotaReportListBO> dataList = new ArrayList<GeneralQuotaReportListBO>();
		
		List<OracleQuotaCollectDayDomain> list = oracleQuotaCollectDayDAO.listReportData(monitorId, quotaId, pageSize,startDate,endDate);
		if(CollectionUtils.isNotEmpty(list)){
			for(OracleQuotaCollectDayDomain domain : list){
				GeneralQuotaReportListBO dto = new GeneralQuotaReportListBO();
				dto.setQuotaValue(domain.getQuotaValue());
				dto.setGmtCreatedDate(domain.getGmtCreatedTimeStamp());
				dto.setId(domain.getId());
				dataList.add(dto);
			}
		}
		
		return dataList;
		
	}

	/**
	 * 
	 * 主机数据
	 * @param monitorId
	 * @param quotaId
	 * @param pageSize
	 * @return
	 */
	private List<GeneralQuotaReportListBO> getHostReportData(String monitorId,
			String quotaId, Integer pageSize,String startDate,String endDate) {
		List<GeneralQuotaReportListBO> dataList = new ArrayList<GeneralQuotaReportListBO>();
		
		List<HostQuotaCollectDayDomain> list = hostQuotaCollectDayDAO.listReportData(monitorId, quotaId, pageSize,startDate,endDate);
		if(CollectionUtils.isNotEmpty(list)){
			for(HostQuotaCollectDayDomain domain : list){
				GeneralQuotaReportListBO dto = new GeneralQuotaReportListBO();
				dto.setQuotaValue(domain.getQuotaValue());
				dto.setGmtCreatedDate(domain.getGmtCreatedTimeStamp());
				dto.setId(domain.getId());
				dataList.add(dto);
			}
		}
		
		return dataList;
	}

	@Override
	public List<GeneralQuotaReportListBO> listGeneralQuotaReportData(
			String monitorId, Integer sysType, String quotaId,String startDate,String endDate) {
		return listGeneralQuotaReportData(monitorId,sysType,quotaId,null,null,startDate,endDate);
	}

	@Override
	public GeneralQuotaBO getQuotaInfo(String quotaId) {
		if(StringUtils.isBlank(quotaId)){
			return null;
		}
		
		QuotaModelDomain domain = quotaModelDAO.getQuotaModelById(quotaId);
		if(domain == null){
			return null;
		}
		
		GeneralQuotaBO quotaBO = new GeneralQuotaBO();
		quotaBO.setQuotaId(domain.getId());
		quotaBO.setQuotaName(domain.getQuotaName());
		quotaBO.setMetric(domain.getQuotaMetric());
		return quotaBO;
	}

	@Override
	public GeneralMonitorBO getMonitorInfo(String monitorId, Integer configType) {
		
		if(StringUtils.isBlank(monitorId) || configType == null){
			return null;
		}
		
		if(SysTypeEnum.server.getVal().intValue() == configType.intValue()){
			HostConfigDomain domain = hostConfigDAO.getHostById(monitorId);
			
			GeneralMonitorBO monitorBO = new GeneralMonitorBO();
			monitorBO.setConfigType(configType);
			monitorBO.setIpAddr(domain.getIpAddr());
			monitorBO.setMonitorId(monitorId);
			monitorBO.setMonitorName(domain.getNickName());
			return monitorBO;
			
		}else if(SysTypeEnum.Oracle.getVal().intValue() == configType.intValue() 
				|| SysTypeEnum.MySQL.getVal().intValue() == configType.intValue()){
			DbConfigDomain domain = dbConfigDAO.getDataBaseConfig(monitorId);
		
			GeneralMonitorBO monitorBO = new GeneralMonitorBO();
			monitorBO.setConfigType(configType);
			monitorBO.setIpAddr(domain.getIpAddr());
			monitorBO.setMonitorId(monitorId);
			monitorBO.setMonitorName(domain.getNickName());
			return monitorBO;
		}
		
		return null;
	}

	@Override
	public List<GeneralMonitorBO> listNormalMonitors(Integer configType) {
		List<GeneralMonitorBO> result = new ArrayList<GeneralMonitorBO>();
		if(configType == null){
			return result;
		}
		
		if(configType.intValue() == SysTypeEnum.server.getVal().intValue()){
			List<HostConfigDomain> list = hostConfigDAO.listNormalHostConfig();
			result = new ArrayList<GeneralMonitorBO>();
			for(HostConfigDomain domain : list){
				GeneralMonitorBO monitorBO = new GeneralMonitorBO();
				monitorBO.setConfigType(SysTypeEnum.server.getVal());
				monitorBO.setIpAddr(domain.getIpAddr());
				monitorBO.setMonitorId(domain.getHostId());
				monitorBO.setMonitorName(domain.getNickName());
				
				result.add(monitorBO);
			}
		}else if(configType.intValue() == SysTypeEnum.Oracle.getVal().intValue() 
				|| configType.intValue() == SysTypeEnum.MySQL.getVal().intValue()){
			List<DbConfigDomain> list = dbConfigDAO.listNormalDbConfig(configType);
			result = new ArrayList<GeneralMonitorBO>();
			for(DbConfigDomain domain : list){
				GeneralMonitorBO monitorBO = new GeneralMonitorBO();
				monitorBO.setConfigType(configType);
				monitorBO.setIpAddr(domain.getIpAddr()+":"+domain.getPort());
				monitorBO.setMonitorId(domain.getDbId());
				monitorBO.setMonitorName(domain.getNickName());
				
				result.add(monitorBO);
			}
		}
		return result;
	}

	@Override
	public MultipleGeneralReportDataBO listMultipleGeneralReportData(
			Integer configType, Integer quotaCategory, String monitorId,String quotaClientChecked,String startDate,String endDate) {
		
		MultipleGeneralReportDataBO multipleGeneralReportDataBO = new MultipleGeneralReportDataBO();
		//获取指标大类
		List<QuotaModelDomain> quotaList = quotaModelDAO.listCategoryQuotas(configType, quotaCategory);
		
		//去除非客户端要求的指标
		quotaList = moveQuotaData(quotaClientChecked,quotaList);
		
		if(CollectionUtils.isEmpty(quotaList)){
			return null;
		}
		
		MultipleDataBO[] seriesData = new MultipleDataBO[quotaList.size()];
		for(int i=0;i<quotaList.size();i++){
			QuotaModelDomain domain = quotaList.get(i);
			MultipleDataBO multipleDataBO = new MultipleDataBO();
			multipleDataBO.setName(domain.getQuotaName());
			
			List<GeneralQuotaReportListBO> result = listGeneralQuotaReportData(monitorId,configType,domain.getId(),startDate,endDate);
			multipleDataBO.setData(convertGeneralQuotaReportData(result));
			
			seriesData[i] = multipleDataBO;
			
		}
		
		multipleGeneralReportDataBO.setSeriesData(seriesData);
		
		String quotaCategoryName = "类指标";
		if(SysTypeEnum.server.getVal().intValue() == configType.intValue()){
			quotaCategoryName = ServerCollectQuotaEnum.getEnum(quotaCategory) + quotaCategoryName;
		}else if(SysTypeEnum.Oracle.getVal().intValue() == configType.intValue()){
			quotaCategoryName = OracleCollectQuotaEnum.getEnum(quotaCategory) + quotaCategoryName;
		}else if(SysTypeEnum.MySQL.getVal().intValue() == configType.intValue()){
			quotaCategoryName = MySQLCollectQuotaEnum.getEnum(quotaCategory) + quotaCategoryName;
		}
		
		multipleGeneralReportDataBO.setReportName(quotaCategoryName);
		
		return multipleGeneralReportDataBO;
	}

	
	/**
	 * @param quotaClientChecked
	 * @param quotaList
	 * @return
	 */
	private List<QuotaModelDomain> moveQuotaData(String quotaClientChecked,
			List<QuotaModelDomain> quotaList) {
		List<QuotaModelDomain> result = new ArrayList<QuotaModelDomain>();
		
		//客户端未配置，则返回所有
		if(StringUtils.isBlank(quotaClientChecked)){
			return quotaList;
		}
		
		String[] clientQuotaArray = StringUtils.split(quotaClientChecked, "_", 0);

		for(QuotaModelDomain domain : quotaList){
			String quotaId = domain.getId();
			if(ArrayUtils.contains(clientQuotaArray, quotaId)){
				result.add(domain);
			}
		}
		
		return result;
	}

	/**
	 * 指标通用型转化数据格式
	 * @param quotaReportList
	 * @return
	 */
	private List<ReportOrgFormatDataBO> convertGeneralQuotaReportData(List<GeneralQuotaReportListBO> quotaReportList){
		if(CollectionUtils.isEmpty(quotaReportList)){
			return null;
		}
		
		List<ReportOrgFormatDataBO> result = new ArrayList<ReportOrgFormatDataBO>();
		for(GeneralQuotaReportListBO report : quotaReportList){
			ReportOrgFormatDataBO reportOrgFormatDataBO = new ReportOrgFormatDataBO();
			reportOrgFormatDataBO.setX(Long.valueOf(report.getGmtCreatedDate()) * 1000);
			reportOrgFormatDataBO.setY(report.getQuotaValue());
			//TODO 未来增加ID列
			reportOrgFormatDataBO.setId(report.getId());
			
			result.add(reportOrgFormatDataBO);
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.report.biz.quota.GeneralQuotaReportDataManager#listMultipleQuotaGeneralReportData(java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public MultipleGeneralReportDataBO listMultipleQuotaGeneralReportData(
			Integer configType, String monitorId, String quotaClientChecked,String startDate,String endDate) {
		
		
		MultipleGeneralReportDataBO multipleGeneralReportDataBO = new MultipleGeneralReportDataBO();
		
		String[] clientQuotaArray = StringUtils.split(quotaClientChecked, "_", 0);
		if(clientQuotaArray.length > GeneralQuotaReportDataManager.MAX_QUOTA_SIZE){
			return null;
		}
		
		List<QuotaModelDomain> quotaList = new ArrayList<QuotaModelDomain>();
		for(String quotaId : clientQuotaArray){
			QuotaModelDomain domain = quotaModelDAO.getQuotaModelById(quotaId);
			if(domain!=null){
				quotaList.add(domain);
			}
		}
		
		if(CollectionUtils.isEmpty(quotaList)){
			return null;
		}
		
		MultipleDataBO[] seriesData = new MultipleDataBO[quotaList.size()];
		for(int i=0;i<quotaList.size();i++){
			QuotaModelDomain domain = quotaList.get(i);
			MultipleDataBO multipleDataBO = new MultipleDataBO();
			multipleDataBO.setName(domain.getQuotaName());
			
			List<GeneralQuotaReportListBO> result = listGeneralQuotaReportData(monitorId,configType,domain.getId(),startDate,endDate);
			multipleDataBO.setData(convertGeneralQuotaReportData(result));
			//multipleDataBO.setyAxis(i+1);
			seriesData[i] = multipleDataBO;
			
		}
		
		multipleGeneralReportDataBO.setSeriesData(seriesData);
		multipleGeneralReportDataBO.setReportName("多指标报表");
		
		return multipleGeneralReportDataBO;
		
	}

	@Override
	public MultipleGeneralReportDataBO listComplexQuotaGeneralReportData(String quotaName,
			Integer configType, String monitorId, String quotaClientChecked,String startDate,String endDate) {
		
		MultipleGeneralReportDataBO multipleGeneralReportDataBO = new MultipleGeneralReportDataBO();
		
		String[] clientQuotaArray = StringUtils.split(quotaClientChecked, "_", 0);
		if(clientQuotaArray == null){
			return null;
		}
		
		List<GeneralQuotaReportListBO> result = listComplexQuotaData(monitorId,configType,clientQuotaArray,null,null,startDate,endDate);
		MultipleDataBO multipleDataBO = new MultipleDataBO();
		multipleDataBO.setName(quotaName);
		multipleDataBO.setData(convertGeneralQuotaReportData(result));
		
		multipleGeneralReportDataBO.setSeriesData(new MultipleDataBO[]{multipleDataBO});
		multipleGeneralReportDataBO.setReportName(quotaName);
		
		return multipleGeneralReportDataBO;
		
	}

	/**
	 * @param monitorId
	 * @param configType
	 * @param clientQuotaArray
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<GeneralQuotaReportListBO> listComplexQuotaData(
			String monitorId, Integer configType, String[] clientQuotaArray, Integer pageSize,String gmtCreated,String startDate,String endDate) {

		if(pageSize == null || pageSize < 0){
			pageSize = PAGE_SIZE;
		}
		
		if(configType == null){
			LOGGER.error("类型参数不能为空");
			return Collections.EMPTY_LIST;
		}
		
		if(SysTypeEnum.server.getVal().intValue() != configType.intValue() 
				&& SysTypeEnum.Oracle.getVal().intValue() != configType.intValue() 
				&& SysTypeEnum.MySQL.getVal().intValue() != configType.intValue() ){
			LOGGER.error(String.format("类型参数不符合预期值,当前值是[%s]",configType));
			return Collections.EMPTY_LIST;
		}
		
		
		List<GeneralQuotaReportListBO> result = new ArrayList<GeneralQuotaReportListBO>();
		
		//MySQL
		if(SysTypeEnum.MySQL.getVal().intValue() == configType.intValue()){
			result = listMySQLComplexQuotaReportData(monitorId, clientQuotaArray, pageSize,startDate,endDate);
		}else if(SysTypeEnum.Oracle.getVal().intValue() == configType.intValue()){
			result = listOracleComplexQuotaReportData(monitorId, clientQuotaArray, pageSize,startDate,endDate);
		}
		
		return result;
	}

	/**
	 * 复合MYSQL指标
	 * @param monitorId
	 * @param clientQuotaArray
	 * @param pageSize
	 * @return
	 */
	private List<GeneralQuotaReportListBO> listMySQLComplexQuotaReportData(
			String monitorId, String[] clientQuotaArray, Integer pageSize,String startDate,String endDate) {
		
		List<MysqlQuotaCollectDayDomain> list = mysqlQuotaCollectDayDAO.listComplexQuotaReportData(monitorId, clientQuotaArray, pageSize,startDate,endDate);
		
		return convertMySQLQuotaToGeneralQuotaData(list);
	}
	
	
	private List<GeneralQuotaReportListBO> listOracleComplexQuotaReportData(
			String monitorId, String[] clientQuotaArray, Integer pageSize,String startDate,String endDate) {
		
		List<OracleQuotaCollectDayDomain> list = oracleQuotaCollectDayDAO.listComplexQuotaReportData(monitorId, clientQuotaArray, pageSize,startDate,endDate);
		
		return convertOracleQuotaToGeneralQuotaData(list);
	}


	@Override
	public MultipleGeneralReportDataBO calMySQLHits(String dbId,String startDate,String endDate) {
		
		List<MysqlQuotaCollectDayDomain> list = mysqlQuotaCollectDayDAO.listHitsQuotaData(dbId, PAGE_SIZE,startDate,endDate);
		
		List<GeneralQuotaReportListBO> dataList = convertMySQLQuotaToGeneralQuotaData(list);
		
		MultipleDataBO multipleDataBO = new MultipleDataBO();
		multipleDataBO.setName("命中率");
		multipleDataBO.setData(convertGeneralQuotaReportData(dataList));
		
		MultipleGeneralReportDataBO multipleGeneralReportDataBO = new MultipleGeneralReportDataBO();
		multipleGeneralReportDataBO.setSeriesData(new MultipleDataBO[]{multipleDataBO});
		multipleGeneralReportDataBO.setReportName("命中率指标");
		
		return multipleGeneralReportDataBO;
	}

	/**
	 * @param list
	 */
	private List<GeneralQuotaReportListBO> convertMySQLQuotaToGeneralQuotaData(
			List<MysqlQuotaCollectDayDomain> list) {
		
		List<GeneralQuotaReportListBO> dataList = new ArrayList<GeneralQuotaReportListBO>();
		
		if(CollectionUtils.isNotEmpty(list)){
			for(MysqlQuotaCollectDayDomain domain : list){
				GeneralQuotaReportListBO dto = new GeneralQuotaReportListBO();
				dto.setQuotaValue(domain.getQuotaValue());
				dto.setGmtCreatedDate(domain.getGmtCreatedTimeStamp());
				dto.setId(domain.getId());
				dataList.add(dto);
			}
		}
		return dataList;
	}
	
	
	private List<GeneralQuotaReportListBO> convertOracleQuotaToGeneralQuotaData(
			List<OracleQuotaCollectDayDomain> list) {
		
		List<GeneralQuotaReportListBO> dataList = new ArrayList<GeneralQuotaReportListBO>();
		
		if(CollectionUtils.isNotEmpty(list)){
			for(OracleQuotaCollectDayDomain domain : list){
				GeneralQuotaReportListBO dto = new GeneralQuotaReportListBO();
				dto.setQuotaValue(domain.getQuotaValue());
				dto.setGmtCreatedDate(domain.getGmtCreatedTimeStamp());
				dto.setId(domain.getId());
				dataList.add(dto);
			}
		}
		return dataList;
	}

	@Override
	public MultipleGeneralReportDataBO listMultipleQuotaGeneralReportDataTogether(
			Integer configType, Integer statType, String monitorId,
			String quotaClientChecked) {
		
		//计算出表名
		String tabName = calTableName(configType, statType);
		
		MultipleGeneralReportDataBO multipleGeneralReportDataBO = new MultipleGeneralReportDataBO();
		
		String[] clientQuotaArray = StringUtils.split(quotaClientChecked, "_", 0);
		if(clientQuotaArray.length > GeneralQuotaReportDataManager.MAX_QUOTA_SIZE){
			return null;
		}
		
		List<QuotaModelDomain> quotaList = new ArrayList<QuotaModelDomain>();
		for(String quotaId : clientQuotaArray){
			QuotaModelDomain domain = quotaModelDAO.getQuotaModelById(quotaId);
			if(domain!=null){
				quotaList.add(domain);
			}
		}
		
		if(CollectionUtils.isEmpty(quotaList)){
			return null;
		}
		
		MultipleDataBO[] seriesData = new MultipleDataBO[quotaList.size()];
		for(int i=0;i<quotaList.size();i++){
			QuotaModelDomain domain = quotaList.get(i);
			MultipleDataBO multipleDataBO = new MultipleDataBO();
			multipleDataBO.setName(domain.getQuotaName());
			
			List<GeneralQuotaReportListBO> result = listTogetherGeneralQuotaReportData(monitorId,configType,domain.getId(),tabName);
			multipleDataBO.setData(convertGeneralQuotaReportData(result));
			//multipleDataBO.setyAxis(i+1);
			seriesData[i] = multipleDataBO;
			
		}
		
		multipleGeneralReportDataBO.setSeriesData(seriesData);
		multipleGeneralReportDataBO.setReportName("多指标报表");
		
		return multipleGeneralReportDataBO;
		
	}

	/**
	 * 计算表名
	 * @param configType
	 * @param statType
	 * @return
	 */
	private String calTableName(Integer configType, Integer statType) {
		
		if(configType == null || statType == null){
			return "";
		}
		
		String tabName = "";
		
		if(configType.intValue() == SysTypeEnum.server.getVal().intValue()){
			if(statType.intValue() == TogetherStatTypeEnum.HOUR.getVal().intValue()){
				tabName = "b_host_quota_together_hours";
				
			}else if(statType.intValue() == TogetherStatTypeEnum.DAY.getVal().intValue()){
				tabName = "b_host_quota_together_day";
				
			}else if(statType.intValue() == TogetherStatTypeEnum.WEEK.getVal().intValue()){
				tabName = "b_host_quota_together_week";
				
			}else if(statType.intValue() == TogetherStatTypeEnum.MONTH.getVal().intValue()){
				tabName = "b_host_quota_together_month";
				
			}
		}else if(configType.intValue() == SysTypeEnum.MySQL.getVal().intValue()){
			if(statType.intValue() == TogetherStatTypeEnum.HOUR.getVal().intValue()){
				tabName = "b_mysql_quota_together_hours";
				
			}else if(statType.intValue() == TogetherStatTypeEnum.DAY.getVal().intValue()){
				tabName = "b_mysql_quota_together_day";
				
			}else if(statType.intValue() == TogetherStatTypeEnum.WEEK.getVal().intValue()){
				tabName = "b_mysql_quota_together_week";
				
			}else if(statType.intValue() == TogetherStatTypeEnum.MONTH.getVal().intValue()){
				tabName = "b_mysql_quota_together_month";
				
			}
		}else if(configType.intValue() == SysTypeEnum.Oracle.getVal().intValue()){
			if(statType.intValue() == TogetherStatTypeEnum.HOUR.getVal().intValue()){
				tabName = "b_oracle_quota_together_hours";
				
			}else if(statType.intValue() == TogetherStatTypeEnum.DAY.getVal().intValue()){
				tabName = "b_oracle_quota_together_day";
				
			}else if(statType.intValue() == TogetherStatTypeEnum.WEEK.getVal().intValue()){
				tabName = "b_oracle_quota_together_week";
				
			}else if(statType.intValue() == TogetherStatTypeEnum.MONTH.getVal().intValue()){
				tabName = "b_oracle_quota_together_month";
				
			}
		}
		
		
		return tabName;
	}

	@Override
	public MultipleGeneralReportDataBO listComplexQuotaGeneralReportDataTogether(
			String quotaName, Integer configType, Integer statType,
			String monitorId, String quotaClientChecked) {
		
		String tabName = calTableName(configType, statType);
		
		MultipleGeneralReportDataBO multipleGeneralReportDataBO = new MultipleGeneralReportDataBO();
		
		String[] clientQuotaArray = StringUtils.split(quotaClientChecked, "_", 0);
		if(clientQuotaArray == null){
			return null;
		}
		
		List<GeneralQuotaReportListBO> result = listTogetherComplexQuotaData(monitorId,configType,clientQuotaArray,tabName);
		MultipleDataBO multipleDataBO = new MultipleDataBO();
		multipleDataBO.setName(quotaName);
		multipleDataBO.setData(convertGeneralQuotaReportData(result));
		
		multipleGeneralReportDataBO.setSeriesData(new MultipleDataBO[]{multipleDataBO});
		multipleGeneralReportDataBO.setReportName(quotaName);
		
		return multipleGeneralReportDataBO;
		
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	private List<GeneralQuotaReportListBO> listTogetherGeneralQuotaReportData(
			String monitorId, Integer sysType, String quotaId, String tabName) {
		
		if(sysType == null){
			LOGGER.error("类型参数不能为空");
			return Collections.EMPTY_LIST;
		}
		
		if(SysTypeEnum.server.getVal().intValue() != sysType.intValue() 
				&& SysTypeEnum.Oracle.getVal().intValue() != sysType.intValue() 
				&& SysTypeEnum.MySQL.getVal().intValue() != sysType.intValue() ){
			LOGGER.error(String.format("类型参数不符合预期值,当前值是[%s]",sysType));
			return Collections.EMPTY_LIST;
		}
		
		
		
		
		String hostId = "";
		String dbId = "";
		if(sysType.intValue() == SysTypeEnum.server.getVal().intValue()){
			hostId = monitorId;
			dbId = "";
		}else if(sysType.intValue() == SysTypeEnum.Oracle.getVal().intValue() 
				|| sysType.intValue() == SysTypeEnum.MySQL.getVal().intValue()){
			hostId = "";
			dbId = monitorId;
		}
		List<GeneralQuotaTogetherDomain> list = generalQuotaTogetherDAO.listReportData(hostId, dbId, quotaId, PAGE_SIZE, tabName);
		
		List<GeneralQuotaReportListBO> dataList = new ArrayList<GeneralQuotaReportListBO>();
		
		if(CollectionUtils.isNotEmpty(list)){
			for(GeneralQuotaTogetherDomain domain : list){
				GeneralQuotaReportListBO dto = new GeneralQuotaReportListBO();
				dto.setQuotaValue(domain.getQuotaValue());
				dto.setGmtCreatedDate(domain.getGmtCreated());
				
				dataList.add(dto);
			}
		}
		
		return dataList;
	}
	
	
	@SuppressWarnings("unchecked")
	private List<GeneralQuotaReportListBO> listTogetherComplexQuotaData(
			String monitorId, Integer configType, String[] clientQuotaArray, String tabName) {

		
		if(configType == null){
			LOGGER.error("类型参数不能为空");
			return Collections.EMPTY_LIST;
		}
		
		if(SysTypeEnum.server.getVal().intValue() != configType.intValue() 
				&& SysTypeEnum.Oracle.getVal().intValue() != configType.intValue() 
				&& SysTypeEnum.MySQL.getVal().intValue() != configType.intValue() ){
			LOGGER.error(String.format("类型参数不符合预期值,当前值是[%s]",configType));
			return Collections.EMPTY_LIST;
		}
		
		
		List<GeneralQuotaReportListBO> result = new ArrayList<GeneralQuotaReportListBO>();
		

		String hostId = "";
		String dbId = "";
		if(configType.intValue() == SysTypeEnum.server.getVal().intValue()){
			hostId = monitorId;
			dbId = "";
		}else if(configType.intValue() == SysTypeEnum.Oracle.getVal().intValue() 
				|| configType.intValue() == SysTypeEnum.MySQL.getVal().intValue()){
			hostId = "";
			dbId = monitorId;
		}
		List<GeneralQuotaTogetherDomain> list = generalQuotaTogetherDAO.listComplexQuotaReportData(hostId, dbId, clientQuotaArray, PAGE_SIZE, tabName);
		
		if(CollectionUtils.isNotEmpty(list)){
			for(GeneralQuotaTogetherDomain domain : list){
				GeneralQuotaReportListBO dto = new GeneralQuotaReportListBO();
				dto.setQuotaValue(domain.getQuotaValue());
				dto.setGmtCreatedDate(domain.getGmtCreated());
				result.add(dto);
			}
		}
		
		return result;
	}

	@Override
	public BaseLineReportDTO listBaseLineData(String monitorId,
			Integer configType, String quotaId, Long maxTimeStamp,
			Long minTimeStamp) {

		//上下轨
		BaseLineReportDataBO areaRange = new BaseLineReportDataBO(); 
		areaRange.setName("最大最小值");
		areaRange.setzIndex(0);
		areaRange.setType("arearange");
		
		//中轨
		BaseLineReportDataBO avgRange = new BaseLineReportDataBO();
		avgRange.setName("平均值");
		areaRange.setzIndex(1);
		
		List<BaseLineReportFormatDataBO> maxTotal = new ArrayList<BaseLineReportFormatDataBO>();
		List<BaseLineReportFormatDataBO> avgTotal = new ArrayList<BaseLineReportFormatDataBO>();
		
		
		List<BaseLineRecordDomain> list = baseLineRecordDAO.listBaseLineRecord(monitorId, configType, quotaId);
		
		String startDate = DateUtils.convertTimeStampToDateString(minTimeStamp);
		String endDate = DateUtils.convertTimeStampToDateString(maxTimeStamp); 
		
		//开始hh:mm
		String startYmd = StringUtils.split(startDate)[0];
		String startHm = StringUtils.split(startDate)[1];
		
		//结束hh:mm
		String endYmd = StringUtils.split(endDate)[0];
		String endHm = StringUtils.split(endDate)[1];
		
		
		int days = DateUtils.daysBetweenByStrFormat(startYmd,endYmd);
		
		if(days == 0){
			//日内
			baseLineHandlerManager.handler(startYmd, startHm, endHm, list, maxTotal, avgTotal);
			
		}else if(days == 1){
			//隔日
			
			//补前
			baseLineHandlerManager.handler(startYmd, startHm, StringUtils.EMPTY, list, maxTotal, avgTotal);
			
			//补后
			baseLineHandlerManager.handler(endHm, StringUtils.EMPTY, endHm, list, maxTotal, avgTotal);
			
		}else{
			
			//补前
			baseLineHandlerManager.handler(startYmd, startHm, StringUtils.EMPTY, list, maxTotal, avgTotal);
			
			String newStartYmd = this.addDate(startYmd);
			
			for(int j=0;j<days-1;j++){
				//补中
				baseLineHandlerManager.handler(newStartYmd, StringUtils.EMPTY, StringUtils.EMPTY, list, maxTotal, avgTotal);
				
				newStartYmd = this.addDate(newStartYmd);
			}
			
			//补后
			baseLineHandlerManager.handler(endHm, StringUtils.EMPTY, endHm, list, maxTotal, avgTotal);
		}
		
		
		
		//转化二位数组
		convertListToBaseLineArray(maxTotal,areaRange,3);
		convertListToBaseLineArray(avgTotal,avgRange,2);
		
		
		BaseLineReportDTO result = new BaseLineReportDTO();
		result.setAreaRange(areaRange);
		result.setAvgRange(avgRange);
		
		
		return  result;
	}

	
	
	
	/**
	 * 加1天
	 * @param startYmd
	 * @return
	 */
	private String addDate(String startYmd){
		Date startD = DateUtils.StringToDate(startYmd, "yyyy-MM-dd");
		String newStartYmd = DateUtils.addDateByFormat(startD, 1, "yyyy-MM-dd");
		return newStartYmd;
	}

	/**
	 * @param maxTotal
	 * @param avgRange
	 */
	private void convertListToBaseLineArray(
			List<BaseLineReportFormatDataBO> maxTotal,
			BaseLineReportDataBO avgRange,int col) {

		if(CollectionUtils.isEmpty(maxTotal)){
			return;
		}
		
		int row = maxTotal.size();
		
		Object[][] newArray= new Object[row][col];
		
		int index = 0;
		for(BaseLineReportFormatDataBO bo : maxTotal){
			newArray[index] = bo.getArrays();
			index++;
		}
		
		avgRange.setData(newArray);
	}


}
