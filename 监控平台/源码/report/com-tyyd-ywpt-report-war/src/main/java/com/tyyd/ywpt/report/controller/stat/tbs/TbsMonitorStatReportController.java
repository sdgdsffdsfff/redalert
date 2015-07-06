/**   
* @Title: TbsMonitorStatReportController.java 
* @Package com.tyyd.ywpt.report.controller.stat.disk 
* @Description:  
* @author wangyu   
* @date 2015-6-2 上午11:33:19 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.controller.stat.tbs;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tyyd.ywpt.biz.dict.SysTypeEnum;
import com.tyyd.ywpt.dao.configration.db.dataobject.DbConfigDomain;
import com.tyyd.ywpt.dao.core.collect.datafile.dataobject.DbTableSpaceDomain;
import com.tyyd.ywpt.report.biz.device.TbsMonitorReportManager;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleGeneralReportDataBO;

/**
 * @author wangyu
 *
 */
@Controller
@RequestMapping("/report/stat/tbs")
public class TbsMonitorStatReportController {

	private static final String ORALCE_VIEWS = "/report/stat/tbs/oracle_tbs_stat";
	private static final String MYSQL_VIEWS = "/report/stat/tbs/mysql_tbs_stat";
	private static final String ERROR_VIEWS = "/report/stat/tbs/tbs_error";
	
	@Resource
	private TbsMonitorReportManager tbsMonitorReportManager;
	
	
	@RequestMapping(value = "/view/{dbId}", method = RequestMethod.GET)
	public ModelAndView views(@PathVariable("dbId") String dbId,
							HttpServletRequest request, 
							HttpServletResponse response){
		
		ModelAndView model = new ModelAndView();
		
		DbConfigDomain dbConfigDomain = tbsMonitorReportManager.getDataBaseInfo(dbId);
		DbTableSpaceDomain dbTableSpaceDomain = tbsMonitorReportManager.totalTbsInfo(dbId);
		if(dbConfigDomain == null || dbTableSpaceDomain == null){
			 model.setViewName(ERROR_VIEWS);
			 return model;
		}
		
		
		model.addObject("dbConfigDomain", dbConfigDomain);
		model.addObject("tbsUsed", dbTableSpaceDomain.getUsedTbs().toString());
		model.addObject("tbsMaxTbs", dbTableSpaceDomain.getMaxTbs().toString());
		model.addObject("tbsUsedPercent", dbTableSpaceDomain.getUsePercent().toString());
		
		model.setViewName(ORALCE_VIEWS);
		
		
		if(dbConfigDomain.getDbType().intValue() == SysTypeEnum.MySQL.getVal().intValue()){
			model.setViewName(MYSQL_VIEWS);
		}
		return model;
	} 

	
	/**
	 * Tbs List
	 * @param dbId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/tbs/info/{dbId}", method = RequestMethod.GET)
	public @ResponseBody List<DbTableSpaceDomain[]>  listTbs(
			@PathVariable("dbId") String dbId,
			HttpServletRequest request, 
			HttpServletResponse response) {
		
		List<DbTableSpaceDomain[]> list = tbsMonitorReportManager.listTbsMonitor(dbId);
		
		return list;
	}
	
	
	/**
	 * total Tbs
	 * @param dbId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/tbs/total/{dbId}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO totalTbs(
			@PathVariable("dbId") String dbId,
			HttpServletRequest request, 
			HttpServletResponse response) {
		
		MultipleGeneralReportDataBO multipleGeneralReportDataBO = tbsMonitorReportManager.totalTbsReport(dbId);
		
		return multipleGeneralReportDataBO;
	}
	
	
	
	/**
	 * Delta Tbs
	 * @param dbId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/tbs/delta/{dbId}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO deltaTbs(
			@PathVariable("dbId") String dbId,
			HttpServletRequest request, 
			HttpServletResponse response) {
		
		MultipleGeneralReportDataBO multipleGeneralReportDataBO = tbsMonitorReportManager.deltaTbsReport(dbId);
		
		return multipleGeneralReportDataBO;
	}
	
	/**
	 * Delta Tbs
	 * @param dbId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/tbs/delta/detail/{dbId}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO deltaTbsReportByTbs(
			@PathVariable("dbId") String dbId,
			HttpServletRequest request, 
			HttpServletResponse response) {
		
		String tbsName = request.getParameter("tbsname");
		MultipleGeneralReportDataBO multipleGeneralReportDataBO = tbsMonitorReportManager.deltaTbsReportByTbs(dbId, tbsName);
		
		return multipleGeneralReportDataBO;
	}
	
	/**
	 * sum Tbs By Tbs
	 * @param dbId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/tbs/total/detail/{dbId}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO sumTbsByName(
			@PathVariable("dbId") String dbId,
			HttpServletRequest request, 
			HttpServletResponse response) {
		
		String tbsName = request.getParameter("tbsname");
		MultipleGeneralReportDataBO multipleGeneralReportDataBO = tbsMonitorReportManager.totalTbsReportByTbs(dbId, tbsName);
		
		return multipleGeneralReportDataBO;
	}
}
