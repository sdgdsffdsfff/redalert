/**   
* @Title: MySQLAwrReviewController.java 
* @Package com.tyyd.ywpt.report.controller.stat.mysql 
* @Description:  
* @author wangyu   
* @date 2015-5-21 下午2:31:22 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.controller.stat.mysql;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tyyd.ywpt.biz.util.DateUtils;
import com.tyyd.ywpt.dao.core.collect.even.mysql.dataobject.MysqlQuotaCollectDayDomain;
import com.tyyd.ywpt.dao.stat.mysql.dataobject.MyawrQueryReviewDomain;
import com.tyyd.ywpt.dao.stat.mysql.dataobject.MyawrQueryReviewHistoryDomain;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleGeneralReportDataBO;
import com.tyyd.ywpt.report.biz.stat.mysql.MySQLAwrReviewManager;

/**
 * @author wangyu
 *
 */

@Controller
@RequestMapping("/report/stat/mysql/awr")
public class MySQLAwrReviewController {

	private static final String STAT_VIEWS = "/report/stat/mysql/awr/mysql_awr_stat";
	private static final String STAT_ERROR_VIEWS = "/report/stat/mysql/awr/error_page";
	private static final String SQL_STAT_VIEWS = "/report/stat/mysql/awr/mysql_awr_sql_stat";
	
	
	@Resource
	private MySQLAwrReviewManager mySQLAwrReviewManager;
	
	
	@RequestMapping(value = "/start/{id}", method = RequestMethod.GET)
	public ModelAndView mysqlQuotaView(
			@PathVariable("id") String id,
			HttpServletRequest request, 
			HttpServletResponse response){
		
		ModelAndView model = new ModelAndView();
		model.addObject("id", id);
		
		boolean isChecked = mySQLAwrReviewManager.checkMySQLAwrExists(id);
		if(isChecked){
			model.setViewName(STAT_VIEWS);
			return model;
		}
		
		model.setViewName(STAT_ERROR_VIEWS);
		return model;
		
	} 
	
	
	@RequestMapping(value = "/sql/{id}/{checkSum}", method = RequestMethod.GET)
	public ModelAndView mysqlSQLView(
			@PathVariable("id") String id,
			@PathVariable("checkSum") String checkSum,
			HttpServletRequest request, 
			HttpServletResponse response){
		
		ModelAndView model = new ModelAndView();
		model.addObject("id", id);
		model.addObject("checkSum", checkSum);
		
		MysqlQuotaCollectDayDomain mysqlQuotaCollectDayDomain = mySQLAwrReviewManager.loadMysqlQuotaCollectDay(id);
		if(mysqlQuotaCollectDayDomain == null || mysqlQuotaCollectDayDomain.getGmtCreated() == null){
			model.setViewName(STAT_ERROR_VIEWS);
			return model;
		}
		
		//格式化日期
		Date gmtCreated = mysqlQuotaCollectDayDomain.getGmtCreated();
		String start = DateUtils.addDate(gmtCreated, -3);
		String end = DateUtils.addDate(gmtCreated, 1);
		
		model.addObject("startDate", start);
		model.addObject("endDate", end);
		model.addObject("dbId", mysqlQuotaCollectDayDomain.getDbId());
		
		model.setViewName(SQL_STAT_VIEWS);
		return model;
		
	} 
	
	/**
	 * Top SQL
	 * @param snapId
	 * @param dataBaseId
	 * @param instanceId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/topsql/{id}", method = RequestMethod.GET)
	public @ResponseBody List<MyawrQueryReviewHistoryDomain>  getTopSQL(
			@PathVariable("id") String id,
			HttpServletRequest request, 
			HttpServletResponse response) {
		
		String sortColumn = request.getParameter("columnId");
		String sortType = request.getParameter("sortType");
		
		List<MyawrQueryReviewHistoryDomain> awrReviewList = mySQLAwrReviewManager.listMyAwrQueryReviewHistory(id,sortColumn,sortType);
		
		return awrReviewList;
	}
	
	
	/**
	 * SQL STAT
	 * @param snapId
	 * @param dataBaseId
	 * @param instanceId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/sql/sqlstat/{checkSum}", method = RequestMethod.GET)
	public @ResponseBody MyawrQueryReviewDomain  getSQLStat(
			@PathVariable("checkSum") String checkSum,
			HttpServletRequest request, 
			HttpServletResponse response) {
		
		MyawrQueryReviewDomain myAwrQueryReview = mySQLAwrReviewManager.getMyawrQueryReview(checkSum);
		
		return myAwrQueryReview;
	}
	
	/**
	 * 报表数据
	 * @param snapId
	 * @param dataBaseId
	 * @param instanceId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/sql/rpt/{checkSum}/{start}/{end}/{dbId}", method = RequestMethod.GET)
	public @ResponseBody Map<String,MultipleGeneralReportDataBO>  getSQLRptData(
			@PathVariable("checkSum") String checkSum,
			@PathVariable("start") String start,
			@PathVariable("end") String end,
			@PathVariable("dbId") String dbId,
			HttpServletRequest request, 
			HttpServletResponse response) {
		
		Map<String,MultipleGeneralReportDataBO> myAwrQueryReview = mySQLAwrReviewManager.listMySQLAwrReview(dbId, start, end, checkSum);
		return myAwrQueryReview;
	}
}
