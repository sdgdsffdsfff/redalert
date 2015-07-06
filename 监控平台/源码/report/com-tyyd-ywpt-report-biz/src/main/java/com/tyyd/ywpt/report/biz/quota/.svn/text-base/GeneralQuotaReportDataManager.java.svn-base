/**   
 * @Title: GeneralQuotaReportDataManager.java 
 * @Package com.tyyd.ywpt.report.biz.quota 
 * @Description:  
 * @author wangyu   
 * @date 2014-9-2 下午2:32:30 
 * @CopyRight 天翼阅读
 * @version V1.0   
 */
package com.tyyd.ywpt.report.biz.quota;

import java.util.List;

import com.tyyd.ywpt.report.biz.quota.dto.GeneralMonitorBO;
import com.tyyd.ywpt.report.biz.quota.dto.GeneralQuotaBO;
import com.tyyd.ywpt.report.biz.quota.dto.GeneralQuotaReportListBO;
import com.tyyd.ywpt.report.biz.quota.dto.baseline.BaseLineReportDTO;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleGeneralReportDataBO;

/**
 * @author wangyu
 * 
 */
public interface GeneralQuotaReportDataManager {

	public static final Integer MAX_QUOTA_SIZE = 3;
	
	/**
	 * 通用型的指标报表数据格式
	 * @param monitorId
	 * @param sysType
	 * @param quotaId
	 * @param gmtCreated
	 * @param pageSize
	 * @return
	 */
	public List<GeneralQuotaReportListBO> listGeneralQuotaReportData(
			String monitorId, Integer sysType, String quotaId,
			String gmtCreated, Integer pageSize,String startDate,String endDate);
	
	
	
	/**
	 * 通用型的指标报表数据格式
	 * @param monitorId
	 * @param sysType
	 * @param quotaId
	 * @return
	 */
	public List<GeneralQuotaReportListBO> listGeneralQuotaReportData(
			String monitorId, Integer sysType, String quotaId,String startDate,String endDate);
	
	
	
	/**
	 * 获取指标信息
	 * @param quotaId
	 * @return
	 */
	public GeneralQuotaBO getQuotaInfo(String quotaId);
	
	
	/**
	 * 获取监控机器信息
	 * @param monitorId
	 * @param configType
	 * @return
	 */
	public GeneralMonitorBO getMonitorInfo(String monitorId,Integer configType);
	
	
	/**
	 * 正常的主机列表
	 * @return
	 */
	public List<GeneralMonitorBO> listNormalMonitors(Integer configType);
	
	
	 
	
	/**
	 * 某个主机的多个监控指标数据
	 * @param configType
	 * @param quotaCategory
	 * @param monitorId
	 * @return
	 */
	public MultipleGeneralReportDataBO listMultipleGeneralReportData(Integer configType,Integer quotaCategory,String monitorId,String quotaClientChecked,String startDate,String endDate);
	
	
	/**
	 * 多个指标比较
	 * @param configType
	 * @param monitorId
	 * @param quotaClientChecked
	 * @return
	 */
	public MultipleGeneralReportDataBO listMultipleQuotaGeneralReportData(Integer configType,String monitorId,String quotaClientChecked,String startDate,String endDate);
	
	
	/**
	 * 多指标需复合计算
	 * @param configType
	 * @param monitorId
	 * @param quotaClientChecked
	 * @return
	 */
	public MultipleGeneralReportDataBO listComplexQuotaGeneralReportData(String quotaName,Integer configType,String monitorId,String quotaClientChecked,String startDate,String endDate);
	
	
	/**
	 * 计算MySQL的hits
	 * @param dbId
	 * @return
	 */
	public MultipleGeneralReportDataBO calMySQLHits(String dbId,String startDate,String endDate);
	
	
	/**
	 * 多指标（together类型）
	 * @param configType
	 * @param monitorId
	 * @param quotaClientChecked
	 * @return
	 */
	public MultipleGeneralReportDataBO listMultipleQuotaGeneralReportDataTogether(Integer configType,Integer statType,String monitorId,String quotaClientChecked);
	

	/**
	 * 多指标需复合计算(together类型)
	 * @param configType
	 * @param monitorId
	 * @param quotaClientChecked
	 * @return
	 */
	public MultipleGeneralReportDataBO listComplexQuotaGeneralReportDataTogether(String quotaName,Integer configType,Integer statType,String monitorId,String quotaClientChecked);
	
	
	
	/**
	 * 获取基线的三种数据
	 * @param monitorId
	 * @param configType
	 * @param quotaId
	 * @param maxTime	指标展示最大的时间 , 以timestampe格式
	 * @param minTime	指标展示最小的时间 , 以timestampe格式
	 * @return
	 */
	public BaseLineReportDTO listBaseLineData(String monitorId,Integer configType,String quotaId,Long maxTimeStamp,Long minTimeStamp);
	
}
