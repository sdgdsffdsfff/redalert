/**   
* @Title: AlertMonitorComplexDisplayController.java 
* @Package com.tyyd.ywpt.report.controller.alert 
* @Description:  
* @author wangyu   
* @date 2014-11-4 上午11:16:09 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.controller.alert;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tyyd.ywpt.biz.dict.SysTypeEnum;
import com.tyyd.ywpt.dao.base.dataobject.PageQuery;
import com.tyyd.ywpt.report.biz.alert.MinitorAlertRecordDisplayManager;
import com.tyyd.ywpt.report.biz.alert.dto.MonitorAlertDisplayDTO;
import com.tyyd.ywpt.report.controller.alert.dto.MonitorAlertComplexParamDTO;

/**
 * @author wangyu
 *
 */
@Controller
@RequestMapping("/report/alert/complex")
public class AlertMonitorComplexDisplayController {

	private static final String ALERT_REPORT_BASE_VIEW = "/report/alert_complex/alert_base";
	private static final String ALERT_REPORT_SERVER_VIEW = "/report/alert_complex/server/server_alert";
	private static final String ALERT_REPORT_ORACLE_VIEW = "/report/alert_complex/oracle/oracle_alert";
	private static final String ALERT_REPORT_MYSQL_VIEW = "/report/alert_complex/mysql/mysql_alert";
	
	@Resource
	private MinitorAlertRecordDisplayManager minitorAlertRecordDisplayManager;
	
	
	/**
	 * 预警总览
	 * @return
	 */
	@RequestMapping(value = "/looking/all", method = RequestMethod.GET)
	public ModelAndView alertRecordPage(
			HttpServletRequest request, 
			HttpServletResponse response,
			@ModelAttribute("pageComplexSearch") MonitorAlertComplexParamDTO dto){
		ModelAndView model = new ModelAndView();
		
		PageQuery<List<MonitorAlertDisplayDTO>> pageQuery = minitorAlertRecordDisplayManager.listAllAlertComplex(dto.getCurrentPage(), null);
		
		model.addObject("pageObject", pageQuery);
		
		model.addObject("formDto", dto);
		model.addObject("title", "总览");
		model.setViewName(ALERT_REPORT_BASE_VIEW);
		return model;
	}
	
	/**
	 * 主机预警
	 * @return
	 */
	@RequestMapping(value = "/looking/server", method = RequestMethod.GET)
	public ModelAndView hostAlertRecordPage(
			HttpServletRequest request, 
			HttpServletResponse response,
			@ModelAttribute("pageComplexSearch") MonitorAlertComplexParamDTO dto){
		ModelAndView model = new ModelAndView();
		
		if(dto.getHostId()==null || dto.getHostId().equals("-1") || dto.getHostId().equals("选择")){
			dto.setHostId("");
		}
		PageQuery<List<MonitorAlertDisplayDTO>> pageQuery = minitorAlertRecordDisplayManager.listHostAlertComplex(dto.getCurrentPage(), dto.getHostId(),dto.getStartDate(),dto.getEndDate());
		
		model.addObject("pageObject", pageQuery);
		
		model.addObject("formDto", dto);
		model.addObject("title", "主机预警归类");
		model.setViewName(ALERT_REPORT_SERVER_VIEW);
		return model;
	}
	
	
	/**
	 * ORACLE预警
	 * @return
	 */
	@RequestMapping(value = "/looking/oracle", method = RequestMethod.GET)
	public ModelAndView oracleAlertRecordPage(
			HttpServletRequest request, 
			HttpServletResponse response,
			@ModelAttribute("pageComplexSearch") MonitorAlertComplexParamDTO dto){
		ModelAndView model = new ModelAndView();
		
		if(dto.getDbId()==null || dto.getDbId().equals("-1") || dto.getDbId().equals("选择")){
			dto.setDbId("");
		}
		PageQuery<List<MonitorAlertDisplayDTO>> pageQuery = 
				minitorAlertRecordDisplayManager.listDbAlertComplex(dto.getCurrentPage(), dto.getDbId(), SysTypeEnum.Oracle.getVal(), dto.getStartDate(), dto.getEndDate());
		
		model.addObject("pageObject", pageQuery);
		
		model.addObject("formDto", dto);
		model.addObject("title", "Oracle预警归类");
		model.setViewName(ALERT_REPORT_ORACLE_VIEW);
		return model;
	}
	
	
	/**
	 * MySQL预警
	 * @return
	 */
	@RequestMapping(value = "/looking/mysql", method = RequestMethod.GET)
	public ModelAndView mysqlAlertRecordPage(
			HttpServletRequest request, 
			HttpServletResponse response,
			@ModelAttribute("pageComplexSearch") MonitorAlertComplexParamDTO dto){
		ModelAndView model = new ModelAndView();
		
		if(dto.getDbId()==null || dto.getDbId().equals("-1") || dto.getDbId().equals("选择")){
			dto.setDbId("");
		}
		PageQuery<List<MonitorAlertDisplayDTO>> pageQuery = 
				minitorAlertRecordDisplayManager.listDbAlertComplex(dto.getCurrentPage(), dto.getDbId(), SysTypeEnum.MySQL.getVal(), dto.getStartDate(), dto.getEndDate());
		
		model.addObject("pageObject", pageQuery);
		
		model.addObject("formDto", dto);
		model.addObject("title", "MySQL预警归类");
		model.setViewName(ALERT_REPORT_MYSQL_VIEW);
		return model;
	}
}
