/**   
* @Title: OracleSQLStatController.java 
* @Package com.tyyd.ywpt.report.controller.stat.oracle 
* @Description:  
* @author wangyu   
* @date 2014-12-3 下午4:27:15 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.controller.stat.oracle;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tyyd.ywpt.biz.dict.SQLStatTypeEnum;
import com.tyyd.ywpt.biz.util.DateUtils;
import com.tyyd.ywpt.dao.configration.db.DbConfigDAO;
import com.tyyd.ywpt.dao.configration.db.dataobject.DbConfigDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.OracleDbaSqlStatListDomain;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleGeneralReportDataBO;
import com.tyyd.ywpt.report.biz.stat.oracle.performace.OraclePerformanceStatManager;
import com.tyyd.ywpt.report.biz.stat.oracle.sqlstat.OracleHisSQLStatManager;

/**
 * @author wangyu
 *
 */
@Controller
@RequestMapping("/report/stat/oracle/sqlstat")
public class OracleSQLStatController {

	private static final String QUOTA_REPORT_VIEWS = "/report/stat/oracle/oracle_sqlstat";
	private static final String QUOTA_REPORT_LIST_VIEWS = "/report/stat/oracle/oracle_sqlstat_list";
	
	
	@Resource
	private OracleHisSQLStatManager oracleHisSQLStatManager;
	
	@Resource
	private OraclePerformanceStatManager oraclePerformanceStatManager;
	
	@Resource
	private DbConfigDAO dbConfigDAO;
	
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView mysqlQuota(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView model = new ModelAndView();
		String dbId = request.getParameter("dbId");
		String sqlID = request.getParameter("sqlid");
		String sampleTime = request.getParameter("sampletime");
		
		if(StringUtils.isNotBlank(dbId) 
				&& StringUtils.isNotBlank(sqlID) 
				&& StringUtils.isNotBlank(sampleTime)){
			model.addObject("sqlId", sqlID);
			model.addObject("dbId", dbId);
			
			String beginTime = DateUtils.addDate(DateUtils.StringToDate(sampleTime, "yyyy-MM-dd HH:mm:ss"), -1);
			String endTime = DateUtils.addDate(DateUtils.StringToDate(sampleTime, "yyyy-MM-dd HH:mm:ss"), 1);
			
			String now = DateUtils.getCurrentDateTime();
			if(DateUtils.compareDate(endTime, now) > 0){
				endTime = now;
			}
			
			
			DbConfigDomain dbConfigDomain = dbConfigDAO.getDataBaseConfig(dbId);
			Long dataBaseId = dbConfigDomain.getOraDataBaseId();
			Long instanceId = dbConfigDomain.getOraInstanceId();
			
			if(dataBaseId != null && instanceId != null){
				String sqlText = oraclePerformanceStatManager.getSqlText(sqlID, dataBaseId, instanceId);
				model.addObject("sqlText", sqlText);
			}
			
			
			model.addObject("beginTime", beginTime);
			model.addObject("endTime", endTime);
		}
		
		
		
		
		model.setViewName(QUOTA_REPORT_VIEWS);
		return model;
		
	}
	
	
	/**
	 * 物理读
	 * @param dbId
	 * @param beginTime
	 * @param endTime
	 * @param sqlId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/diskread/{dbId}/{beginTime}/{endTime}/{sqlId}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO diskRead(
			@PathVariable("dbId") String dbId,
			@PathVariable("beginTime") String beginTime,
			@PathVariable("endTime") String endTime,
			@PathVariable("sqlId") String sqlId,
			HttpServletRequest request, HttpServletResponse response) {
		
		DbConfigDomain dbConfigDomain = dbConfigDAO.getDataBaseConfig(dbId);
		
		MultipleGeneralReportDataBO dto = oracleHisSQLStatManager.listSQLStatReportData(beginTime, endTime,
				new String[]{SQLStatTypeEnum.disk_reads_total.getKey() , 
								SQLStatTypeEnum.disk_reads_seconds.getKey()},
				dbConfigDomain.getOraDataBaseId(),
				dbConfigDomain.getOraInstanceId(), sqlId);
		
		dto.setReportName(String.format("%s[%s],物理读", dbConfigDomain.getDbName(),dbConfigDomain.getIpAddr()));
		
		return dto;
	}
	
	
	
	/**
	 * 逻辑读
	 * @param dbId
	 * @param beginTime
	 * @param endTime
	 * @param sqlId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/bufferget/{dbId}/{beginTime}/{endTime}/{sqlId}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO bufferGet(
			@PathVariable("dbId") String dbId,
			@PathVariable("beginTime") String beginTime,
			@PathVariable("endTime") String endTime,
			@PathVariable("sqlId") String sqlId,
			HttpServletRequest request, HttpServletResponse response) {
		
		DbConfigDomain dbConfigDomain = dbConfigDAO.getDataBaseConfig(dbId);
		
		MultipleGeneralReportDataBO dto = oracleHisSQLStatManager.listSQLStatReportData(beginTime, endTime,
				new String[]{SQLStatTypeEnum.buffer_gets_total.getKey() , 
								SQLStatTypeEnum.buffer_gets_seconds.getKey()},
				dbConfigDomain.getOraDataBaseId(),
				dbConfigDomain.getOraInstanceId(), sqlId);
		
		dto.setReportName(String.format("%s[%s],逻辑读", dbConfigDomain.getDbName(),dbConfigDomain.getIpAddr()));
		
		return dto;
	}
	
	/**
	 * 内存指标
	 * @param dbId
	 * @param beginTime
	 * @param endTime
	 * @param sqlId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/memory/{dbId}/{beginTime}/{endTime}/{sqlId}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO memory(
			@PathVariable("dbId") String dbId,
			@PathVariable("beginTime") String beginTime,
			@PathVariable("endTime") String endTime,
			@PathVariable("sqlId") String sqlId,
			HttpServletRequest request, HttpServletResponse response) {
		
		DbConfigDomain dbConfigDomain = dbConfigDAO.getDataBaseConfig(dbId);
		
		MultipleGeneralReportDataBO dto = oracleHisSQLStatManager.listSQLStatReportData(beginTime, endTime,
				new String[]{SQLStatTypeEnum.memory.getKey()},
				dbConfigDomain.getOraDataBaseId(),
				dbConfigDomain.getOraInstanceId(), sqlId);
		
		dto.setReportName(String.format("%s[%s],内存使用", dbConfigDomain.getDbName(),dbConfigDomain.getIpAddr()));
		
		return dto;
	}
	
	
	
	/**
	 * 执行次数
	 * @param dbId
	 * @param beginTime
	 * @param endTime
	 * @param sqlId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/executions/{dbId}/{beginTime}/{endTime}/{sqlId}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO executions(
			@PathVariable("dbId") String dbId,
			@PathVariable("beginTime") String beginTime,
			@PathVariable("endTime") String endTime,
			@PathVariable("sqlId") String sqlId,
			HttpServletRequest request, HttpServletResponse response) {
		
		DbConfigDomain dbConfigDomain = dbConfigDAO.getDataBaseConfig(dbId);
		
		MultipleGeneralReportDataBO dto = oracleHisSQLStatManager.listSQLStatReportData(beginTime, endTime,
				new String[]{SQLStatTypeEnum.executes.getKey()},
				dbConfigDomain.getOraDataBaseId(),
				dbConfigDomain.getOraInstanceId(), sqlId);
		
		dto.setReportName(String.format("%s[%s],执行数次", dbConfigDomain.getDbName(),dbConfigDomain.getIpAddr()));
		
		return dto;
	}
	
	
	/**
	 * SQL解析
	 * @param dbId
	 * @param beginTime
	 * @param endTime
	 * @param sqlId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/parse/{dbId}/{beginTime}/{endTime}/{sqlId}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO parse(
			@PathVariable("dbId") String dbId,
			@PathVariable("beginTime") String beginTime,
			@PathVariable("endTime") String endTime,
			@PathVariable("sqlId") String sqlId,
			HttpServletRequest request, HttpServletResponse response) {
		
		DbConfigDomain dbConfigDomain = dbConfigDAO.getDataBaseConfig(dbId);
		
		MultipleGeneralReportDataBO dto = oracleHisSQLStatManager.listSQLStatReportData(beginTime, endTime,
				new String[]{SQLStatTypeEnum.parse_total.getKey() , 
								SQLStatTypeEnum.parse_seconds.getKey()},
				dbConfigDomain.getOraDataBaseId(),
				dbConfigDomain.getOraInstanceId(), sqlId);
		
		dto.setReportName(String.format("%s[%s],SQL解析", dbConfigDomain.getDbName(),dbConfigDomain.getIpAddr()));
		
		return dto;
	}
	
	
	/**
	 * 执行时间
	 * @param dbId
	 * @param beginTime
	 * @param endTime
	 * @param sqlId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/elapsed/{dbId}/{beginTime}/{endTime}/{sqlId}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO elapsed(
			@PathVariable("dbId") String dbId,
			@PathVariable("beginTime") String beginTime,
			@PathVariable("endTime") String endTime,
			@PathVariable("sqlId") String sqlId,
			HttpServletRequest request, HttpServletResponse response) {
		
		DbConfigDomain dbConfigDomain = dbConfigDAO.getDataBaseConfig(dbId);
		
		MultipleGeneralReportDataBO dto = oracleHisSQLStatManager.listSQLStatReportData(beginTime, endTime,
				new String[]{SQLStatTypeEnum.elapsed_time_total.getKey() , 
								SQLStatTypeEnum.elapsed_time_seconds.getKey()},
				dbConfigDomain.getOraDataBaseId(),
				dbConfigDomain.getOraInstanceId(), sqlId);
		
		dto.setReportName(String.format("%s[%s],执行时间", dbConfigDomain.getDbName(),dbConfigDomain.getIpAddr()));
		
		return dto;
	}
	
	
	@RequestMapping(value = "/sqlstatlistview", method = RequestMethod.GET)
	public ModelAndView sqlStatListView(HttpServletRequest request, HttpServletResponse response){
		ModelAndView model = new ModelAndView();
		String dbId = request.getParameter("oracle_monitors");
		String sqlId = request.getParameter("sqlId");
		String timeParam = request.getParameter("times");
		
		if(StringUtils.isBlank(timeParam)){
			timeParam = "0";
		}
		
		if(StringUtils.isBlank(dbId)){
			dbId = StringUtils.EMPTY;
		}
		
		if(StringUtils.isBlank(sqlId)){
			sqlId = StringUtils.EMPTY;
		}
		
		Integer times =Integer.valueOf(timeParam);
		
		
		
		List<OracleDbaSqlStatListDomain> list = oracleHisSQLStatManager.listOracleSqlStatBySqlId(sqlId, dbId, times);
		
		model.addObject("dbId", dbId);
		model.addObject("sqlId", sqlId);
		model.addObject("times", times);
		model.addObject("list", list);
		
		model.setViewName(QUOTA_REPORT_LIST_VIEWS);
		return model;
		
	}
	
	
	
}
