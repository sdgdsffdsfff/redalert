/**   
* @Title: OracleEventController.java 
* @Package com.tyyd.ywpt.report.controller.stat.oracle 
* @Description:  
* @author wangyu   
* @date 2014-12-4 下午2:22:34 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.controller.stat.oracle;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tyyd.ywpt.biz.util.DateUtils;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.OracleDbaHisActiveSessEventDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.OracleDbaHisActiveSessSampleDomain;
import com.tyyd.ywpt.report.biz.stat.oracle.sqlstat.OracleHisSQLStatManager;

/**
 * @author wangyu
 *
 */
@Controller
@RequestMapping("/report/stat/oracle/event")
public class OracleEventController {

	
	private static final String SAMPLE_TIME_VIEWS = "/report/stat/oracle/event/sample_time";
	private static final String EVENT_LIST_VIEWS = "/report/stat/oracle/event/event_list";
	
	@Resource
	private OracleHisSQLStatManager oracleHisSQLStatManager;
	
	
	
	@RequestMapping(value = "/sampletime", method = RequestMethod.GET)
	public ModelAndView sampleTime(
			HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView model = new ModelAndView();
		
		String dbId = request.getParameter("oracle_monitors");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String min = request.getParameter("minCounts");
		Integer minCounts = 0;
		
		if(NumberUtils.isDigits(min)){
			minCounts = Integer.valueOf(min);
		}
		
		if(minCounts == null || minCounts <= 0){
			minCounts = 100;
		}
		
		if(StringUtils.isBlank(beginTime)){
			beginTime = DateUtils.getBeforeTwoDay() + " 00:00:01";
		}
		
		if(StringUtils.isBlank(endTime)){
			endTime = DateUtils.getCurrentDate() + " 23:59:59";
		}
		
		List<OracleDbaHisActiveSessSampleDomain> list = oracleHisSQLStatManager.listSampleTimes(beginTime, endTime, dbId, minCounts);
		
		model.addObject("sampleTimeList", list);
		model.addObject("minCounts", minCounts);
		model.addObject("beginTime", beginTime);
		model.addObject("endTime", endTime);
		model.addObject("dbId", dbId);
		
		
		model.setViewName(SAMPLE_TIME_VIEWS);
		return model;
	}
	
	
	@RequestMapping(value = "/eventlist/{dbId}/{sampleTime}", method = RequestMethod.GET)
	public ModelAndView eventList(
			@PathVariable("dbId") String dbId,
			@PathVariable("sampleTime") String sampleTime,
			HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView model = new ModelAndView();
		
		List<OracleDbaHisActiveSessEventDomain> list = oracleHisSQLStatManager.listEventsBySampleTime(sampleTime, dbId);
		
		model.addObject("eventList", list);
		model.addObject("sampleTime", sampleTime);
		model.addObject("dbId", dbId);
		model.setViewName(EVENT_LIST_VIEWS);
		return model;
	}
	
}
