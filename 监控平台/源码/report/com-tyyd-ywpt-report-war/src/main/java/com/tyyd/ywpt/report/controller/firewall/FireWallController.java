/**   
* @Title: FireWallController.java 
* @Package com.tyyd.ywpt.report.controller.firewall 
* @Description:  
* @author wangyu   
* @date 2015-4-21 下午1:19:56 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.controller.firewall;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tyyd.ywpt.biz.util.DateUtils;
import com.tyyd.ywpt.report.biz.firewall.FireWallManager;
import com.tyyd.ywpt.report.biz.firewall.bo.FireWallEnum;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleGeneralReportDataBO;

/**
 * @author wangyu
 *
 */

@Controller
@RequestMapping("/report/firewall")
public class FireWallController {

	public static final Logger LOGGER = Logger.getLogger(FireWallController.class);  
	
	private static final String REPORT_VIEWS = "/report/firewall/fire_wall_view";
	
	@Resource
	private FireWallManager fireWallManager;
	
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView mysqlQuota(){
		ModelAndView model = new ModelAndView();
		String beginTime = DateUtils.getCurrentDate() +" 00:00:01";
		String endTime = DateUtils.getCurrentDateTime();
		model.addObject("beginTime", beginTime);
		model.addObject("endTime", endTime);
		model.setViewName(REPORT_VIEWS);
		return model;
	}
	
	
	/**
	 * 异网WAP
	 * @param dbId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/other/wap/{startDate}/{endDate}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO otherWap(
			@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate,
			HttpServletRequest request, HttpServletResponse response) {

		
		MultipleGeneralReportDataBO dto = fireWallManager.listFireWall(
				startDate, endDate,
				FireWallEnum.other_network_wap.getFlowCountKey(),
				FireWallEnum.other_network_wap.getEmbryonicKey());

		dto.setReportName("异网WAP");
		
		return dto;
	}
	
	
	/**
	 * 有声接口
	 * @param dbId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/voice/iface/{startDate}/{endDate}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO voiceInterface(
			@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate,
			HttpServletRequest request, HttpServletResponse response) {

		
		MultipleGeneralReportDataBO dto = fireWallManager.listFireWall(
				startDate, endDate,
				FireWallEnum.voice_interface.getFlowCountKey(),
				FireWallEnum.voice_interface.getEmbryonicKey());

		dto.setReportName("有声接口");
		
		return dto;
	}
	
	
	/**
	 * 互联星空1
	 * @param dbId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/vent/one/{startDate}/{endDate}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO ventOne(
			@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate,
			HttpServletRequest request, HttpServletResponse response) {

		
		MultipleGeneralReportDataBO dto = fireWallManager.listFireWall(
				startDate, endDate, FireWallEnum.vent_one.getFlowCountKey(),
				FireWallEnum.vent_one.getEmbryonicKey());

		dto.setReportName("互联星空1");
		
		return dto;
	}
	
	
	/**
	 * 互联星空2
	 * @param dbId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/vent/two/{startDate}/{endDate}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO ventTwo(
			@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate,
			HttpServletRequest request, HttpServletResponse response) {

		
		MultipleGeneralReportDataBO dto = fireWallManager.listFireWall(
				startDate, endDate, FireWallEnum.vent_two.getFlowCountKey(),
				FireWallEnum.vent_two.getEmbryonicKey());

		dto.setReportName("互联星空2");
		
		return dto;
	}
	
	
	/**
	 * 防火墙计费
	 * @param dbId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/firewall/pay/{startDate}/{endDate}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO fireWallPay(
			@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate,
			HttpServletRequest request, HttpServletResponse response) {

		
		MultipleGeneralReportDataBO dto = fireWallManager.listFireWall(
				startDate, endDate,
				FireWallEnum.fire_wall_pay.getFlowCountKey(),
				FireWallEnum.fire_wall_pay.getEmbryonicKey());

		dto.setReportName("防火墙计费");
		
		return dto;
	}
	
	/**
	 * 防火墙下发
	 * @param dbId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/firewall/xf/{startDate}/{endDate}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO fireWallXf(
			@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate,
			HttpServletRequest request, HttpServletResponse response) {

		
		MultipleGeneralReportDataBO dto = fireWallManager.listFireWall(
				startDate, endDate,
				FireWallEnum.fire_wall_xf.getFlowCountKey(),
				FireWallEnum.fire_wall_xf.getEmbryonicKey());

		dto.setReportName("防火墙下发");
		
		return dto;
	}
	
	
	/**
	 * 防火墙wap
	 * @param dbId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/firewall/wap/{startDate}/{endDate}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO fireWallWap(
			@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate,
			HttpServletRequest request, HttpServletResponse response) {

		
		MultipleGeneralReportDataBO dto = fireWallManager.listFireWall(
				startDate, endDate,
				FireWallEnum.fire_wall_wap.getFlowCountKey(),
				FireWallEnum.fire_wall_wap.getEmbryonicKey());

		dto.setReportName("防火墙wap");
		
		return dto;
	}
	
	
	/**
	 * 防火墙www
	 * @param dbId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/firewall/www/{startDate}/{endDate}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO fireWallWww(
			@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate,
			HttpServletRequest request, HttpServletResponse response) {

		
		MultipleGeneralReportDataBO dto = fireWallManager.listFireWall(
				startDate, endDate,
				FireWallEnum.fire_wall_www.getFlowCountKey(),
				FireWallEnum.fire_wall_www.getEmbryonicKey());

		dto.setReportName("防火墙www");
		
		return dto;
	}
	
	
	/**
	 * 防火墙异网下发
	 * @param dbId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/firewall/xfothernetwork/{startDate}/{endDate}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO fireWallXfOtherNetWork(
			@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate,
			HttpServletRequest request, HttpServletResponse response) {

		
		MultipleGeneralReportDataBO dto = fireWallManager.listFireWall(
				startDate, endDate,
				FireWallEnum.fire_wall_xf_other_network.getFlowCountKey(),
				FireWallEnum.fire_wall_xf_other_network.getEmbryonicKey());

		dto.setReportName("防火墙异网下发");
		
		return dto;
	}
	
	
	/**
	 * 防火墙有声音频
	 * @param dbId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/firewall/voice/{startDate}/{endDate}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO fireWallVoice(
			@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate,
			HttpServletRequest request, HttpServletResponse response) {

		
		MultipleGeneralReportDataBO dto = fireWallManager.listFireWall(
				startDate, endDate,
				FireWallEnum.fire_wall_voice.getFlowCountKey(),
				FireWallEnum.fire_wall_voice.getEmbryonicKey());

		dto.setReportName("防火墙有声音频");
		
		return dto;
	}
}
