/**   
 * @Title: ServerQuotaController.java 
 * @Package com.tyyd.ywpt.report.controller.quota 
 * @Description:  
 * @author wangyu   
 * @date 2014-9-10 下午4:16:29 
 * @CopyRight 天翼阅读
 * @version V1.0   
 */
package com.tyyd.ywpt.report.controller.quota;

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

import com.tyyd.ywpt.biz.dict.SysTypeEnum;
import com.tyyd.ywpt.biz.util.DateUtils;
import com.tyyd.ywpt.report.biz.quota.GeneralQuotaReportDataManager;
import com.tyyd.ywpt.report.biz.quota.dto.GeneralMonitorBO;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleDataBO;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleGeneralReportDataBO;
import com.tyyd.ywpt.report.biz.quota.dto.multi.ReportOrgFormatDataBO;

/**
 * @author wangyu
 * 
 */
@Controller
@RequestMapping("/report/quota/server")
public class ServerQuotaController {

	public static final Logger LOGGER = Logger.getLogger(ServerQuotaController.class);  
	
	private static final String QUOTA_REPORT_VIEWS = "/report/quota/server/sever_quota";
	
	
	@Resource
	private GeneralQuotaReportDataManager generalQuotaReportDataManager;
	
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView serverQuota(){
		ModelAndView model = new ModelAndView();
		String beginTime = DateUtils.getCurrentDate() +" 00:00:01";
		String endTime = DateUtils.getCurrentDate() + " 23:59:59";
		model.addObject("beginTime", beginTime);
		model.addObject("endTime", endTime);
		model.setViewName(QUOTA_REPORT_VIEWS);
		return model;
	}
	
	@RequestMapping(value = "/data/cpu/{monitorId}/{startDate}/{endDate}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO cpu(
			@PathVariable("monitorId") String monitorId,
			@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate,
			HttpServletRequest request, HttpServletResponse response) {

		// 参数检查
		if (StringUtils.isBlank(monitorId)) {
			LOGGER.error("参数检查错误");
			return null;
		}
		
		//主机信息
		GeneralMonitorBO generalMonitorBO = generalQuotaReportDataManager.getMonitorInfo(monitorId, SysTypeEnum.server.getVal());
				
		//只选择cpu_user 1,cpu_iowait 4
		MultipleGeneralReportDataBO dto = generalQuotaReportDataManager.listMultipleQuotaGeneralReportData(SysTypeEnum.server.getVal(), monitorId, "1_4",startDate,endDate);
		dto.setReportName(String.format("%s[%s],CPU使用情况", generalMonitorBO.getMonitorName(),generalMonitorBO.getIpAddr()));
		
		MultipleDataBO[] array = dto.getSeriesData();
		for(MultipleDataBO data : array){
			if(data.getName().equalsIgnoreCase("cpu_user")){
				//data.setyAxis(0);
				data.setType("areaspline");
			}else if(data.getName().equalsIgnoreCase("cpu_iowait")){
				//data.setyAxis(1);
				data.setType("spline");
			}
		}
		
		return dto;
	}
	
	
	@RequestMapping(value = "/data/load/{monitorId}/{startDate}/{endDate}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO load(
			@PathVariable("monitorId") String monitorId,
			@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate,
			HttpServletRequest request, HttpServletResponse response) {

		// 参数检查
		if (StringUtils.isBlank(monitorId)) {
			LOGGER.error("参数检查错误");
			return null;
		}
		
		//主机信息
		GeneralMonitorBO generalMonitorBO = generalQuotaReportDataManager.getMonitorInfo(monitorId, SysTypeEnum.server.getVal());
				
		//load5 5
		MultipleGeneralReportDataBO dto = generalQuotaReportDataManager.listMultipleQuotaGeneralReportData(SysTypeEnum.server.getVal(), monitorId, "5",startDate,endDate);
		dto.setReportName(String.format("%s[%s],load情况", generalMonitorBO.getMonitorName(),generalMonitorBO.getIpAddr()));
		
		return dto;
	}
	
	
	
	@RequestMapping(value = "/data/network/{monitorId}/{startDate}/{endDate}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO network(
			@PathVariable("monitorId") String monitorId,
			@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate,
			HttpServletRequest request, HttpServletResponse response) {

		// 参数检查
		if (StringUtils.isBlank(monitorId)) {
			LOGGER.error("参数检查错误");
			return null;
		}
		
		//主机信息
		GeneralMonitorBO generalMonitorBO = generalQuotaReportDataManager.getMonitorInfo(monitorId, SysTypeEnum.server.getVal());
				
		//只选择net_receive 16,net_send 17
		MultipleGeneralReportDataBO dto = generalQuotaReportDataManager.listMultipleQuotaGeneralReportData(SysTypeEnum.server.getVal(), monitorId, "16_17",startDate,endDate);
		dto.setReportName(String.format("%s[%s],流量指标", generalMonitorBO.getMonitorName(),generalMonitorBO.getIpAddr()));
		
		MultipleDataBO[] array = dto.getSeriesData();
		for(MultipleDataBO data : array){
			if(data.getName().equalsIgnoreCase("net_receive")){
				data.setType("areaspline");
			}else if(data.getName().equalsIgnoreCase("net_send")){
				data.setType("spline");
			}
		}
		
		return dto;
	}
	
	
	@RequestMapping(value = "/data/memory/{monitorId}/{startDate}/{endDate}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO memory(
			@PathVariable("monitorId") String monitorId,
			@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate,
			HttpServletRequest request, HttpServletResponse response) {

		// 参数检查
		if (StringUtils.isBlank(monitorId)) {
			LOGGER.error("参数检查错误");
			return null;
		}
		
		//主机信息
		GeneralMonitorBO generalMonitorBO = generalQuotaReportDataManager.getMonitorInfo(monitorId, SysTypeEnum.server.getVal());
				
		//只选择mem used per  100,swap used per 101
		MultipleGeneralReportDataBO dto = generalQuotaReportDataManager.listMultipleQuotaGeneralReportData(SysTypeEnum.server.getVal(), monitorId, "100_101",startDate,endDate);
		dto.setReportName(String.format("%s[%s],内存指标", generalMonitorBO.getMonitorName(),generalMonitorBO.getIpAddr()));
		
		MultipleDataBO[] array = dto.getSeriesData();
		for(MultipleDataBO data : array){
			if(data.getName().equalsIgnoreCase("mem used per")){
				data.setType("areaspline");
			}else if(data.getName().equalsIgnoreCase("swap used per")){
				data.setType("spline");
			}
			
			List<ReportOrgFormatDataBO> list = data.getData();
			for(ReportOrgFormatDataBO obj : list){
				Float f = Float.valueOf(obj.getY()) * 100;
				obj.setY(f);
			}
		}
		
		return dto;
	}
}
