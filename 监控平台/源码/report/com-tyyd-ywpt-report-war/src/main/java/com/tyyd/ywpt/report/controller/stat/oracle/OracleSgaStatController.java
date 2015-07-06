/**   
* @Title: OracleSgaStatController.java 
* @Package com.tyyd.ywpt.report.controller.stat.oracle 
* @Description:  
* @author wangyu   
* @date 2014-12-16 下午2:29:28 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.controller.stat.oracle;

import java.util.Date;

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
import com.tyyd.ywpt.dao.configration.db.DbConfigDAO;
import com.tyyd.ywpt.dao.configration.db.dataobject.DbConfigDomain;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleGeneralReportDataBO;
import com.tyyd.ywpt.report.biz.stat.oracle.sga.OracleHisSgaStatManager;

/**
 * @author wangyu
 *
 */

@Controller
@RequestMapping("/report/stat/oracle/sga")
public class OracleSgaStatController {

	
	private static final String QUOTA_REPORT_VIEWS = "/report/stat/oracle/oracle_sga_stat";
	
	@Resource
	private OracleHisSgaStatManager oracleHisSgaStatManager;
	
	@Resource
	private DbConfigDAO dbConfigDAO;
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView mysqlQuota(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView model = new ModelAndView();
		
		String beginTime = DateUtils.addDate(new Date(), -3);
		String endTime = DateUtils.DateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
		
		String now = DateUtils.getCurrentDateTime();
		if(DateUtils.compareDate(endTime, now) > 0){
			endTime = now;
		}
		
		
		model.addObject("beginTime", beginTime);
		model.addObject("endTime", endTime);
		
		model.setViewName(QUOTA_REPORT_VIEWS);
		return model;
		
	}
	
	/**
	 * free memory
	 * @param dbId
	 * @param beginTime
	 * @param endTime
	 * @param sqlId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/freememory/{dbId}/{beginTime}/{endTime}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO freeMemory(
			@PathVariable("dbId") String dbId,
			@PathVariable("beginTime") String beginTime,
			@PathVariable("endTime") String endTime,
			HttpServletRequest request, HttpServletResponse response) {
		
		DbConfigDomain dbConfigDomain = dbConfigDAO.getDataBaseConfig(dbId);
		
		MultipleGeneralReportDataBO dto = oracleHisSgaStatManager
				.listSgaStatReportData(beginTime, endTime,
						dbConfigDomain.getOraDataBaseId(),
						dbConfigDomain.getOraInstanceId(),
						new String[] { "free memory" });
		
		dto.setReportName(String.format("%s[%s],Free Memory", dbConfigDomain.getDbName(),dbConfigDomain.getIpAddr()));
		
		return dto;
	}
	
	
	/**
	 * gcs
	 * @param dbId
	 * @param beginTime
	 * @param endTime
	 * @param sqlId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/gcs/{dbId}/{beginTime}/{endTime}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO gcs(
			@PathVariable("dbId") String dbId,
			@PathVariable("beginTime") String beginTime,
			@PathVariable("endTime") String endTime,
			HttpServletRequest request, HttpServletResponse response) {
		
		DbConfigDomain dbConfigDomain = dbConfigDAO.getDataBaseConfig(dbId);
		
		MultipleGeneralReportDataBO dto = oracleHisSgaStatManager
				.listSgaStatReportData(beginTime, endTime,
						dbConfigDomain.getOraDataBaseId(),
						dbConfigDomain.getOraInstanceId(),
						new String[] { "gcs resources","gcs shadows","ges enqueues","ges resource" });
		
		dto.setReportName(String.format("%s[%s],Gcs", dbConfigDomain.getDbName(),dbConfigDomain.getIpAddr()));
		
		return dto;
	}
	
	
	/**
	 * SQL AREA
	 * @param dbId
	 * @param beginTime
	 * @param endTime
	 * @param sqlId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/sqlarea/{dbId}/{beginTime}/{endTime}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO sqlArea(
			@PathVariable("dbId") String dbId,
			@PathVariable("beginTime") String beginTime,
			@PathVariable("endTime") String endTime,
			HttpServletRequest request, HttpServletResponse response) {
		
		DbConfigDomain dbConfigDomain = dbConfigDAO.getDataBaseConfig(dbId);
		
		MultipleGeneralReportDataBO dto = oracleHisSgaStatManager
				.listSgaStatReportData(beginTime, endTime,
						dbConfigDomain.getOraDataBaseId(),
						dbConfigDomain.getOraInstanceId(),
						new String[] { "sql area" });
		
		dto.setReportName(String.format("%s[%s],sql area", dbConfigDomain.getDbName(),dbConfigDomain.getIpAddr()));
		
		return dto;
	}
	
}
