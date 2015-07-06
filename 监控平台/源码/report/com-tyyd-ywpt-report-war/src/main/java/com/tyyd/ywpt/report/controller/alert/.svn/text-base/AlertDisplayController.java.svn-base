/**   
* @Title: AlertDisplayController.java 
* @Package com.tyyd.ywpt.report.controller.alert 
* @Description:  
* @author wangyu   
* @date 2014-10-27 上午10:55:43 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.controller.alert;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tyyd.ywpt.biz.dict.SysTypeEnum;
import com.tyyd.ywpt.report.biz.alert.AlertDisplayManager;
import com.tyyd.ywpt.report.biz.alert.dto.AlertDisplayDTO;
import com.tyyd.ywpt.report.controller.alert.dto.HostAlertDispalyParamDTO;

/**
 * @author wangyu
 *
 */
@Controller
@RequestMapping("/report/alert")
public class AlertDisplayController {

	private static final String ALERT_REPORT_BASE_VIEW = "/report/alert/alert_base";
	private static final String ALERT_REPORT_SERVER_VIEW = "/report/alert/server/server_alert";
	private static final String ALERT_REPORT_ORACLE_VIEW = "/report/alert/oracle/oracle_alert";
	private static final String ALERT_REPORT_MYSQL_VIEW = "/report/alert/mysql/mysql_alert";
	
	@Resource
	private AlertDisplayManager alertDisplayManager;
	
	/**
	 * 总览
	 * @return
	 */
	@RequestMapping(value = "/looking/all", method = RequestMethod.GET)
	public ModelAndView alertIndexPage(
			HttpServletRequest request, 
			HttpServletResponse response){
		ModelAndView model = new ModelAndView();
		Integer pageSize = 0;
		String limitSize = request.getParameter("page_limit_select");
		if(StringUtils.isBlank(limitSize)){
			pageSize = 15;
		}else{
			pageSize = Integer.valueOf(limitSize);
		}
		List<AlertDisplayDTO> list = alertDisplayManager.listLookingAllAlert(pageSize);
		model.addObject("alertDisplayList", list);
		model.addObject("pageSize", pageSize);
		model.addObject("title", "总览");
		model.setViewName(ALERT_REPORT_BASE_VIEW);
		return model;
	}
	
	/**
	 * 主机预警一览
	 * @return
	 */
	@RequestMapping(value = "/looking/server", method = RequestMethod.GET)
	public ModelAndView serverAlertRecordPage(
			HttpServletRequest request, 
			HttpServletResponse response,
			@ModelAttribute("hostSearch") HostAlertDispalyParamDTO dto){
		ModelAndView model = new ModelAndView();
		
//		String startDate = request.getParameter("startDate");
//		String endDate = request.getParameter("endDate");
//		String hostId = request.getParameter("hostId");
//		String limitSize = request.getParameter("limitSize");
		
		
		Integer pageSize = 0;
		if(dto.getLimitSize() == null || dto.getLimitSize()==0){
			pageSize = 15;
		}else{
			pageSize = dto.getLimitSize();
		}
		
		List<AlertDisplayDTO> list = alertDisplayManager.listHostAlert(dto.getHostId(), pageSize, dto.getStartDate(), dto.getEndDate());
		model.addObject("alertDisplayList", list);
		
		model.addObject("formDto", dto);
		model.addObject("title", "主机");
		model.setViewName(ALERT_REPORT_SERVER_VIEW);
		return model;
	}
	
	/**
	 * ORACLE预警一览
	 * @return
	 */
	@RequestMapping(value = "/looking/oracle", method = RequestMethod.GET)
	public ModelAndView oracleAlertRecordPage(
			HttpServletRequest request, 
			HttpServletResponse response,
			@ModelAttribute("oracleSearch") HostAlertDispalyParamDTO dto){
		
		ModelAndView model = new ModelAndView();
		Integer pageSize = 0;
		if(dto.getLimitSize() == null || dto.getLimitSize()==0){
			pageSize = 15;
		}else{
			pageSize = dto.getLimitSize();
		}
		
		List<AlertDisplayDTO> list = alertDisplayManager.listDbAlert(dto.getHostId(), SysTypeEnum.Oracle.getVal() ,pageSize, dto.getStartDate(), dto.getEndDate());
		model.addObject("alertDisplayList", list);
		
		model.addObject("formDto", dto);
		model.addObject("title", "Oracle");
		model.setViewName(ALERT_REPORT_ORACLE_VIEW);
		
		return model;
	}
	
	/**
	 * MySQL预警一览
	 * @return
	 */
	@RequestMapping(value = "/looking/mysql", method = RequestMethod.GET)
	public ModelAndView mysqlAlertRecordPage(
			HttpServletRequest request, 
			HttpServletResponse response,
			@ModelAttribute("mysqlSearch") HostAlertDispalyParamDTO dto){
		
		ModelAndView model = new ModelAndView();
		Integer pageSize = 0;
		if(dto.getLimitSize() == null || dto.getLimitSize()==0){
			pageSize = 15;
		}else{
			pageSize = dto.getLimitSize();
		}
		
		List<AlertDisplayDTO> list = alertDisplayManager.listDbAlert(dto.getHostId(), SysTypeEnum.MySQL.getVal() ,pageSize, dto.getStartDate(), dto.getEndDate());
		model.addObject("alertDisplayList", list);
		
		model.addObject("formDto", dto);
		model.addObject("title", "MySQL");
		model.setViewName(ALERT_REPORT_MYSQL_VIEW);
		
		return model;
	}
	
	
	
}
