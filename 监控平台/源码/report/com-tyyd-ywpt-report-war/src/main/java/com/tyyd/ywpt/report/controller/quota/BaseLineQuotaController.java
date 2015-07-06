/**   
 * @Title: BaseLineQuotaController.java 
 * @Package com.tyyd.ywpt.report.controller.quota 
 * @Description:  
 * @author wangyu   
 * @date 2015-3-19 下午4:56:21 
 * @CopyRight 天翼阅读
 * @version V1.0   
 */
package com.tyyd.ywpt.report.controller.quota;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tyyd.ywpt.biz.util.DateUtils;
import com.tyyd.ywpt.dao.core.quota.QuotaModelDAO;
import com.tyyd.ywpt.dao.core.quota.dataobject.QuotaModelDomain;
import com.tyyd.ywpt.report.biz.quota.GeneralQuotaReportDataManager;
import com.tyyd.ywpt.report.biz.quota.dto.GeneralMonitorBO;
import com.tyyd.ywpt.report.biz.quota.dto.baseline.BaseLineReportDTO;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleGeneralReportDataBO;
import com.tyyd.ywpt.report.biz.quota.dto.multi.ReportOrgFormatDataBO;

/**
 * @author wangyu
 * 
 */

@Controller
@RequestMapping("/report/quota/baseline")
public class BaseLineQuotaController {

	public static final Logger LOGGER = Logger.getLogger(BaseLineQuotaController.class);

	private static final String QUOTA_REPORT_VIEWS = "/report/quota/baseline/baseline_quota";
	
	
	@Resource
	private GeneralQuotaReportDataManager generalQuotaReportDataManager;
	
	@Resource
	private QuotaModelDAO  quotaModelDAO;
	
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView model = new ModelAndView();
		String beginTime = DateUtils.addHour(new Date(), -2);
		String endTime = DateUtils.getCurrentDateTime();
		model.addObject("beginTime", beginTime);
		model.addObject("endTime", endTime);
		model.setViewName(QUOTA_REPORT_VIEWS);
		return model;
	}
	
	
	/**
	 * 基线指标
	 * @param dbId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/single/{monitorId}/{configType}/{quotaId}/{startDate}/{endDate}", method = RequestMethod.GET)
	public @ResponseBody BaseLineReportDTO singleQuota(
			@PathVariable("monitorId") String monitorId,
			@PathVariable("configType") Integer configType,
			@PathVariable("quotaId") String quotaId,
			@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate,
			HttpServletRequest request, HttpServletResponse response) {

		// 参数检查
		if (StringUtils.isBlank(monitorId) 
				|| null == configType) {
			LOGGER.error("参数检查错误");
			return null;
		}
		
		//主机信息
		GeneralMonitorBO generalMonitorBO = generalQuotaReportDataManager.getMonitorInfo(monitorId, configType);
				
		String quotaName = "";
		QuotaModelDomain quotaModelDomain = quotaModelDAO.getQuotaModelById(quotaId);
		if(quotaModelDomain != null){
			quotaName = quotaModelDomain.getQuotaName();
		}
		
		
		MultipleGeneralReportDataBO multipleGeneralReportDataBO = generalQuotaReportDataManager.listMultipleQuotaGeneralReportData(configType, monitorId, quotaId,startDate,endDate);
		
		//获取最大，最小的时间戳
		List<ReportOrgFormatDataBO> seriesList = multipleGeneralReportDataBO.getSeriesData()[0].getData();
		
		long startTimeStamp = seriesList.get(0).getX();
		long endTimeStamp = seriesList.get(seriesList.size()-1).getX();
				
		//返回基线
		BaseLineReportDTO baseLineReportDTO = generalQuotaReportDataManager.listBaseLineData(monitorId, configType, quotaId, endTimeStamp, startTimeStamp);
		
		baseLineReportDTO.setReportName(String.format("%s[%s],%s", generalMonitorBO.getMonitorName(),generalMonitorBO.getIpAddr(),quotaName));
		baseLineReportDTO.setQuotaData(multipleGeneralReportDataBO.getSeriesData()[0]);
		
		return baseLineReportDTO;
	}
	
	

}
