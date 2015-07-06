/**   
* @Title: OracleQuotaController.java 
* @Package com.tyyd.ywpt.report.controller.quota 
* @Description:  
* @author wangyu   
* @date 2014-9-16 下午2:20:45 
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
import org.springframework.web.servlet.ModelAndView;

import com.tyyd.ywpt.biz.dict.SysTypeEnum;
import com.tyyd.ywpt.biz.util.DateUtils;
import com.tyyd.ywpt.report.biz.quota.GeneralQuotaReportDataManager;
import com.tyyd.ywpt.report.biz.quota.dto.GeneralMonitorBO;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleGeneralReportDataBO;

/**
 * @author wangyu
 *
 */
@Controller
@RequestMapping("/report/quota/oracle")
public class OracleQuotaController {

public static final Logger LOGGER = Logger.getLogger(MySQLQuotaController.class);  
	
	private static final String QUOTA_REPORT_VIEWS = "/report/quota/oracle/oracle_quota";
	
	@Resource
	private GeneralQuotaReportDataManager generalQuotaReportDataManager;
	
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView mysqlQuota(){
		ModelAndView model = new ModelAndView();
		String beginTime = DateUtils.DateToString(DateUtils.getDelayDay(-1), "yyyy-MM-dd") +" 00:00:01";
		String endTime = DateUtils.getCurrentDate() + " 23:59:59";
		model.addObject("beginTime", beginTime);
		model.addObject("endTime", endTime);
		model.setViewName(QUOTA_REPORT_VIEWS);
		return model;
	}
	
	/**
	 * 每秒的事务数
	 * @param dbId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/trans/{dbId}/{startDate}/{endDate}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO trans(
			@PathVariable("dbId") String dbId,
			@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate,
			HttpServletRequest request, HttpServletResponse response) {

		// 参数检查
		if (StringUtils.isBlank(dbId)) {
			LOGGER.error("参数检查错误");
			return null;
		}
		
		//主机信息
		GeneralMonitorBO generalMonitorBO = generalQuotaReportDataManager.getMonitorInfo(dbId, SysTypeEnum.Oracle.getVal());
				
		//只选择user commits 582481098,user rollbacks 3671147913 
		//MultipleGeneralReportDataBO dto = generalQuotaReportDataManager.listComplexQuotaGeneralReportData("事务数",SysTypeEnum.Oracle.getVal(), dbId, "582481098_3671147913");
		/*
		 *--每秒回滚数量(复合)
			2022_每秒提交数量 
			2024_每秒回滚数量
		 */
		
		MultipleGeneralReportDataBO dto = generalQuotaReportDataManager.listComplexQuotaGeneralReportData("事务数",SysTypeEnum.Oracle.getVal(), dbId, "2022_2024",startDate,endDate);
		
		dto.setReportName(String.format("%s[%s],每秒事务数", generalMonitorBO.getMonitorName(),generalMonitorBO.getIpAddr()));
		
		return dto;
	}
	
	
	
	/**
	 * SQL执行次数/SQL硬解析次数
	 * @param dbId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/sqlexec/{dbId}/{startDate}/{endDate}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO sqlExec(
			@PathVariable("dbId") String dbId,
			@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate,
			HttpServletRequest request, HttpServletResponse response) {

		// 参数检查
		if (StringUtils.isBlank(dbId)) {
			LOGGER.error("参数检查错误");
			return null;
		}
		
		//主机信息
		GeneralMonitorBO generalMonitorBO = generalQuotaReportDataManager.getMonitorInfo(dbId, SysTypeEnum.Oracle.getVal());
				
		//只选择execute count 2453370665,parse count (hard) 143509059
		//MultipleGeneralReportDataBO dto = generalQuotaReportDataManager.listMultipleQuotaGeneralReportData(SysTypeEnum.Oracle.getVal(), dbId, "2453370665_143509059");
		
		/**
		 * -- SQL执行次数/SQL硬解析次数(两条线)
				2046_每秒硬解析次数
				2121_每秒sql执行数
		 */
		MultipleGeneralReportDataBO dto = generalQuotaReportDataManager.listMultipleQuotaGeneralReportData(SysTypeEnum.Oracle.getVal(), dbId, "2046_2121",startDate,endDate);
		dto.setReportName(String.format("%s[%s],每秒SQL执行数/解析次数", generalMonitorBO.getMonitorName(),generalMonitorBO.getIpAddr()));
		
		return dto;
	}
	
	
	
	/**
	 * 逻辑读
	 * @param dbId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/logicread/{dbId}/{startDate}/{endDate}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO logicRead(
			@PathVariable("dbId") String dbId,
			@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate,
			HttpServletRequest request, HttpServletResponse response) {

		// 参数检查
		if (StringUtils.isBlank(dbId)) {
			LOGGER.error("参数检查错误");
			return null;
		}
		
		//主机信息
		GeneralMonitorBO generalMonitorBO = generalQuotaReportDataManager.getMonitorInfo(dbId, SysTypeEnum.Oracle.getVal());
				
		//只选择consistent gets 4162191256
		//MultipleGeneralReportDataBO dto = generalQuotaReportDataManager.listMultipleQuotaGeneralReportData(SysTypeEnum.Oracle.getVal(), dbId, "4162191256");
		/**
		 * 2030_每秒逻辑读
		 */
		MultipleGeneralReportDataBO dto = generalQuotaReportDataManager.listMultipleQuotaGeneralReportData(SysTypeEnum.Oracle.getVal(), dbId, "2030",startDate,endDate);
		
		dto.setReportName(String.format("%s[%s],逻辑读", generalMonitorBO.getMonitorName(),generalMonitorBO.getIpAddr()));
		
		return dto;
	}
	
	
	
	/**
	 * 物理读/物理写
	 * @param dbId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/physicalreadandwrite/{dbId}/{startDate}/{endDate}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO physicalReadAndWrite(
			@PathVariable("dbId") String dbId,
			@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate,
			HttpServletRequest request, HttpServletResponse response) {

		// 参数检查
		if (StringUtils.isBlank(dbId)) {
			LOGGER.error("参数检查错误");
			return null;
		}
		
		//主机信息
		GeneralMonitorBO generalMonitorBO = generalQuotaReportDataManager.getMonitorInfo(dbId, SysTypeEnum.Oracle.getVal());
				
		//只选择physical writes 1190468109,physical reads 2263124246
		//MultipleGeneralReportDataBO dto = generalQuotaReportDataManager.listMultipleQuotaGeneralReportData(SysTypeEnum.Oracle.getVal(), dbId, "2263124246_1190468109");
		/**
		 * --物理读/物理写
			2006_每秒物理写
			2004_每秒物理读
		 */
		MultipleGeneralReportDataBO dto = generalQuotaReportDataManager.listMultipleQuotaGeneralReportData(SysTypeEnum.Oracle.getVal(), dbId, "2006_2004",startDate,endDate);
		dto.setReportName(String.format("%s[%s],物理读/物理写", generalMonitorBO.getMonitorName(),generalMonitorBO.getIpAddr()));
		
		return dto;
	}
	
	
	/**
	 * 连接
	 * @param dbId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/connect/{dbId}/{startDate}/{endDate}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO connect(
			@PathVariable("dbId") String dbId,
			@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate,
			HttpServletRequest request, HttpServletResponse response) {

		// 参数检查
		if (StringUtils.isBlank(dbId)) {
			LOGGER.error("参数检查错误");
			return null;
		}
		
		//主机信息
		GeneralMonitorBO generalMonitorBO = generalQuotaReportDataManager.getMonitorInfo(dbId, SysTypeEnum.Oracle.getVal());
				
		//只选择sesson total 77777778
		MultipleGeneralReportDataBO dto = generalQuotaReportDataManager.listMultipleQuotaGeneralReportData(SysTypeEnum.Oracle.getVal(), dbId, "77777778",startDate,endDate);
		dto.setReportName(String.format("%s[%s],连接数", generalMonitorBO.getMonitorName(),generalMonitorBO.getIpAddr()));
		
		return dto;
	}
	
	
	/**
	 * redo size
	 * @param dbId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/redo/{dbId}/{startDate}/{endDate}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO redo(
			@PathVariable("dbId") String dbId,
			@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate,
			HttpServletRequest request, HttpServletResponse response) {

		// 参数检查
		if (StringUtils.isBlank(dbId)) {
			LOGGER.error("参数检查错误");
			return null;
		}
		
		//主机信息
		GeneralMonitorBO generalMonitorBO = generalQuotaReportDataManager.getMonitorInfo(dbId, SysTypeEnum.Oracle.getVal());
				
		//只选择redo size 1236385760
		//MultipleGeneralReportDataBO dto = generalQuotaReportDataManager.listMultipleQuotaGeneralReportData(SysTypeEnum.Oracle.getVal(), dbId, "1236385760");
		/**
		 * 2016_每秒日志产生量
		 */
		MultipleGeneralReportDataBO dto = generalQuotaReportDataManager.listMultipleQuotaGeneralReportData(SysTypeEnum.Oracle.getVal(), dbId, "2016",startDate,endDate);
		dto.setReportName(String.format("%s[%s],redo值", generalMonitorBO.getMonitorName(),generalMonitorBO.getIpAddr()));
		
		return dto;
	}
	
}
