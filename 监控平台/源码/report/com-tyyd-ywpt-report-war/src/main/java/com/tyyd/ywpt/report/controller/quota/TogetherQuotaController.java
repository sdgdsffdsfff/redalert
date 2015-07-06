/**   
* @Title: TogetherQuotaController.java 
* @Package com.tyyd.ywpt.report.controller.quota 
* @Description:  
* @author wangyu   
* @date 2014-9-28 上午10:41:29 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.controller.quota;

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

import com.tyyd.ywpt.report.biz.quota.GeneralQuotaReportDataManager;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleGeneralReportDataBO;

/**
 * @author wangyu
 *
 */

@Controller
@RequestMapping("/report/quota/together")
public class TogetherQuotaController {

	public static final Logger LOGGER = Logger.getLogger(TogetherQuotaController.class);  
	
	@Resource
	private GeneralQuotaReportDataManager generalQuotaReportDataManager;
	
	
	/**
	 * 多指标取数接口
	 * @param monitorId
	 * @param configType
	 * @param statType
	 * @param quotaIds
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/multi/{monitorId}/{configType}/{statType}/{quotaIds}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO multipleQuota(
			@PathVariable("monitorId") String monitorId,
			@PathVariable("configType") Integer configType,
			@PathVariable("statType") Integer statType,
			@PathVariable("quotaIds") String quotaIds,
			HttpServletRequest request, HttpServletResponse response) {
		
		boolean vaildFlag = checkParam(monitorId,configType,statType,quotaIds);
		
		if(!vaildFlag){
			return null;
		}
		
		MultipleGeneralReportDataBO multipleGeneralReportDataBO = generalQuotaReportDataManager
				.listMultipleQuotaGeneralReportDataTogether(configType,
						statType, monitorId, quotaIds);
		
		return multipleGeneralReportDataBO;
	}


	/**
	 * 复合指标
	 * @param monitorId
	 * @param configType
	 * @param statType
	 * @param quotaIds
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/complex/{monitorId}/{configType}/{statType}/{quotaIds}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO complexQuota(
			@PathVariable("monitorId") String monitorId,
			@PathVariable("configType") Integer configType,
			@PathVariable("statType") Integer statType,
			@PathVariable("quotaIds") String quotaIds,
			@PathVariable("quotaName") String quotaName,
			HttpServletRequest request, HttpServletResponse response) {
		
		boolean vaildFlag = checkParam(monitorId,configType,statType,quotaIds);
		
		if(!vaildFlag){
			return null;
		}
		
		MultipleGeneralReportDataBO multipleGeneralReportDataBO = generalQuotaReportDataManager
				.listComplexQuotaGeneralReportDataTogether(quotaName,
						configType, statType, monitorId, quotaIds);
				
		
		return multipleGeneralReportDataBO;
	}
	
	
	
	/**
	 * @param monitorId
	 * @param configType
	 * @param statType
	 * @param quotaIds
	 * @return
	 */
	private boolean checkParam(String monitorId, Integer configType,
			Integer statType, String quotaIds) {
		
		if(StringUtils.isBlank(monitorId) 
				|| StringUtils.isBlank(quotaIds) 
				|| configType == null
				|| statType == null){
			return false;
		}
		
		return true;
	}
	
}
