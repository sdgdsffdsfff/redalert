/**   
 * @Title: QuotaController.java 
 * @Package com.tyyd.ywpt.report.controller.quota 
 * @Description:  
 * @author wangyu   
 * @date 2014-9-3 上午9:29:22 
 * @CopyRight 天翼阅读
 * @version V1.0   
 */
package com.tyyd.ywpt.report.controller.quota;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tyyd.ywpt.report.biz.quota.GeneralQuotaReportDataManager;
import com.tyyd.ywpt.report.biz.quota.dto.GeneralMonitorBO;
import com.tyyd.ywpt.report.biz.quota.dto.GeneralQuotaBO;
import com.tyyd.ywpt.report.biz.quota.dto.GeneralQuotaReportListBO;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleGeneralReportDataBO;
import com.tyyd.ywpt.report.controller.quota.dto.GeneralMonitorDTO;
import com.tyyd.ywpt.report.controller.quota.dto.SimpleQuotaReportDataDTO;

/**
 * @author wangyu
 * 
 */
@Controller
public class GeneralQuotaController {

	public static final Logger LOGGER = Logger.getLogger(GeneralQuotaController.class);  
	
	
	private static final String QUOTA_REPORT_VIEWS = "/report/quota/general/list";
	private static final String MULTI_QUOTA_REPORT_VIEWS = "/report/quota/general/multi_list";
	private static final String CHOOSE_QUOTA_REPORT_VIEWS = "/report/quota/general/choose_list";
	
	
	@Resource
	private GeneralQuotaReportDataManager generalQuotaReportDataManager;
	
	

	/**
	 * 单指标
	 * @return
	 */
	@RequestMapping(value="/report/views/general/quota")
	public String quotaReport(){
		return QUOTA_REPORT_VIEWS;
	}
	
	
	/**
	 * 多指标,分大类别，只有某大类下指标比较少，适用，
	 * 支持排除法
	 * @return
	 */
	@RequestMapping(value="/report/views/general/multi/quota")
	public String multiQuotaReport(){
		return MULTI_QUOTA_REPORT_VIEWS;
	}
	
	
	/**
	 * 选择多个指标出报表，做多三个指标
	 * @return
	 */
	@RequestMapping(value="/report/views/general/choose/quota")
	public String chooseQuotaReport(){
		return CHOOSE_QUOTA_REPORT_VIEWS;
	}
	
	/**
	 * 单指标的数据源
	 * @param monitorId
	 * @param sysType
	 * @param quotaId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/report/data/general/quota/{monitorId}/{sysType}/{quotaId}",method = RequestMethod.GET)
	public @ResponseBody SimpleQuotaReportDataDTO getQuotaReportData(
			@PathVariable("monitorId") String monitorId,
			@PathVariable("sysType") Integer sysType, 
			@PathVariable("quotaId") String quotaId,
			HttpServletRequest request,
			HttpServletResponse response) {
		
		SimpleQuotaReportDataDTO dto = new SimpleQuotaReportDataDTO();
		//参数检查
		if (!gennralQuotaReportParamValid(monitorId, sysType, quotaId)) {
			LOGGER.error("参数检查错误");
			return dto;
		}
		
		//取数据
		List<GeneralQuotaReportListBO> result = generalQuotaReportDataManager.listGeneralQuotaReportData(monitorId, sysType, quotaId,null,null);
		
		//主机信息
		GeneralMonitorBO generalMonitorBO = generalQuotaReportDataManager.getMonitorInfo(monitorId, sysType);
		
		//指标信息
		GeneralQuotaBO generalQuotaBO = generalQuotaReportDataManager.getQuotaInfo(quotaId);
		

		
		//转化
		dto = convertBizDataToReportData(result,generalMonitorBO,generalQuotaBO);
		
		return dto;
	}

	
	
	/**
	 * 大类的指标
	 * @param monitorId
	 * @param sysType
	 * @param quotaId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/report/data/general/multi/quota/{monitorId}/{sysType}/{category}/{quotaClientChecked}",method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO getCpuQuotaReportData(
			@PathVariable("monitorId") String monitorId,
			@PathVariable("sysType") Integer sysType, 
			@PathVariable("category") Integer category,
			@PathVariable("quotaClientChecked") String quotaClientChecked,
			HttpServletRequest request,
			HttpServletResponse response) {
		
		//参数检查
		if (StringUtils.isBlank(monitorId) || sysType == null || category == null) {
			LOGGER.error("参数检查错误");
			return null;
		}
		
		MultipleGeneralReportDataBO dto = generalQuotaReportDataManager.listMultipleGeneralReportData(sysType, category, monitorId,quotaClientChecked,null,null);
		
		return dto;
	} 
	
	
	
	/**
	 * 选择指标的数据
	 * @param monitorId
	 * @param sysType
	 * @param category
	 * @param quotaClientChecked
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/report/data/general/choose/quota/{monitorId}/{sysType}/{quotaClientChecked}",method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO getCpuQuotaReportData(
			@PathVariable("monitorId") String monitorId,
			@PathVariable("sysType") Integer sysType, 
			@PathVariable("quotaClientChecked") String quotaClientChecked,
			HttpServletRequest request,
			HttpServletResponse response) {
		
		//参数检查
		if (StringUtils.isBlank(monitorId) || sysType == null ) {
			LOGGER.error("参数检查错误");
			return null;
		}
		
		MultipleGeneralReportDataBO dto = generalQuotaReportDataManager.listMultipleQuotaGeneralReportData(sysType, monitorId, quotaClientChecked,null,null);
		
		return dto;
	} 
	

	@RequestMapping(value="/report/data/general/monitor/{configType}",method = RequestMethod.GET)
	public @ResponseBody List<GeneralMonitorDTO> listMonitorInfo(
			@PathVariable("configType") Integer configType, 
			HttpServletRequest request,
			HttpServletResponse response){
		
		List<GeneralMonitorDTO> result = new ArrayList<GeneralMonitorDTO>();
		if(configType == null){
			return result;
		}
		
		List<GeneralMonitorBO> list = generalQuotaReportDataManager.listNormalMonitors(configType);
		
		for(GeneralMonitorBO bo : list){
			GeneralMonitorDTO dto = new GeneralMonitorDTO();
			dto.setConfigType(configType);
			dto.setIpAddr(bo.getIpAddr());
			dto.setMonitorId(bo.getMonitorId());
			dto.setMonitorName(bo.getMonitorName());
			
			result.add(dto);
		}
		
		return result;
	}
	
	

	/**
	 * @param monitorId
	 * @param sysType
	 * @param quotaId
	 * @return
	 */
	private boolean gennralQuotaReportParamValid(String monitorId,
			Integer sysType, String quotaId) {
		if(StringUtils.isBlank(monitorId) || StringUtils.isBlank(quotaId) || sysType==null){
			return false;
		}
		return true;
	}

	
	/**
	 * 转化
	 * @param result
	 * @return
	 */
	private SimpleQuotaReportDataDTO convertBizDataToReportData(List<GeneralQuotaReportListBO> result,
			GeneralMonitorBO generalMonitorBO, GeneralQuotaBO generalQuotaBO){
		if(CollectionUtils.isNotEmpty(result) 
				&& generalMonitorBO!=null 
				&& generalQuotaBO!=null){
			
			SimpleQuotaReportDataDTO dto = new SimpleQuotaReportDataDTO();
			int length = result.size();
			//数据
			List<Object[]> seriesData = new ArrayList<Object[]>();
			//转化
			for(int i=0;i<length;i++){
				Object[] xseriesDataDTO = new Object[2];
				GeneralQuotaReportListBO quotaDataBO = result.get(i);
				xseriesDataDTO[0] = Long.valueOf(quotaDataBO.getGmtCreatedDate()) * 1000;
				xseriesDataDTO[1] = quotaDataBO.getQuotaValue();
				
				seriesData.add(xseriesDataDTO);
			}
			
			dto.setSeriesData(seriesData);
			dto.setMetric(generalQuotaBO.getMetric());
			
			//报表名称
			StringBuffer reportName = new StringBuffer();
			reportName.append(generalMonitorBO.getMonitorName());
			reportName.append("[");
			reportName.append(generalMonitorBO.getIpAddr());
			reportName.append("]");
			dto.setReportName(reportName.toString());
			
			//曲线名
			dto.setSeriesName(generalQuotaBO.getQuotaName());
			
			//指标名
			dto.setQuotaName(generalQuotaBO.getQuotaName());
			
			return dto;
			
		}
		
		return new SimpleQuotaReportDataDTO();
	}
	
}
