/**   
* @Title: MySQLQuotaController.java 
* @Package com.tyyd.ywpt.report.controller.quota 
* @Description:  
* @author wangyu   
* @date 2014-9-15 上午10:36:14 
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
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleDataBO;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleGeneralReportDataBO;

/**
 * @author wangyu
 *
 */
@Controller
@RequestMapping("/report/quota/mysql")
public class MySQLQuotaController {

	public static final Logger LOGGER = Logger.getLogger(MySQLQuotaController.class);  
	
	private static final String QUOTA_REPORT_VIEWS = "/report/quota/mysql/mysql_quota";
	
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
	 * TPS指标
	 * @param dbId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/tps/{dbId}/{startDate}/{endDate}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO tps(
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
		GeneralMonitorBO generalMonitorBO = generalQuotaReportDataManager.getMonitorInfo(dbId, SysTypeEnum.MySQL.getVal());
		
		//只选择Com_insert 60,Com_update 62,Com_delete 63,Com_replace 64
		String quotaClientChecked = "60_62_63_64";
		MultipleGeneralReportDataBO dto = generalQuotaReportDataManager.listComplexQuotaGeneralReportData("tps",SysTypeEnum.MySQL.getVal(), dbId, quotaClientChecked,startDate,endDate);
		dto.setReportName(String.format("%s[%s],TPS指标", generalMonitorBO.getMonitorName(),generalMonitorBO.getIpAddr()));
		
		MultipleDataBO[] array = dto.getSeriesData();
		for(MultipleDataBO data : array){
			data.setType("spline");
		}
		
		return dto;
	}
	
	
	/**
	 * QPS指标
	 * @param dbId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/qps/{dbId}/{startDate}/{endDate}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO ops(
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
		GeneralMonitorBO generalMonitorBO = generalQuotaReportDataManager.getMonitorInfo(dbId, SysTypeEnum.MySQL.getVal());
				
		//只选择Com_select 61
		MultipleGeneralReportDataBO dto = generalQuotaReportDataManager.listMultipleQuotaGeneralReportData(SysTypeEnum.MySQL.getVal(), dbId, "61",startDate,endDate);
		dto.setReportName(String.format("%s[%s],QPS", generalMonitorBO.getMonitorName(),generalMonitorBO.getIpAddr()));
		
		MultipleDataBO[] array = dto.getSeriesData();
		for(MultipleDataBO data : array){
			if(data.getName().equalsIgnoreCase("Com_select")){
				data.setType("spline");
			}
		}
		
		return dto;
	}
	
	
	/**
	 * MySQL命中率指标
	 * @param dbId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/hits/{dbId}/{startDate}/{endDate}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO hits(
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
		GeneralMonitorBO generalMonitorBO = generalQuotaReportDataManager.getMonitorInfo(dbId, SysTypeEnum.MySQL.getVal());
				
		//INNODB_BUFFER_POOL_READ_REQUESTS 44,INNODB_BUFFER_POOL_READS 45 公式:44/(44+45)
		MultipleGeneralReportDataBO dto = generalQuotaReportDataManager.calMySQLHits(dbId,startDate,endDate);
		dto.setReportName(String.format("%s[%s],命中率", generalMonitorBO.getMonitorName(),generalMonitorBO.getIpAddr()));
		
		MultipleDataBO[] array = dto.getSeriesData();
		for(MultipleDataBO data : array){
			data.setType("spline");
		}
		
		return dto;
	}
	
	
	/**
	 * IOPS
	 * @param dbId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/iops/{dbId}/{startDate}/{endDate}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO iops(
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
		GeneralMonitorBO generalMonitorBO = generalQuotaReportDataManager.getMonitorInfo(dbId, SysTypeEnum.MySQL.getVal());
		
		//只选择INNODB_DATA_READS 46,Innodb_data_writes 47
		String quotaClientChecked = "46_47";
		MultipleGeneralReportDataBO dto = generalQuotaReportDataManager.listComplexQuotaGeneralReportData("iops",SysTypeEnum.MySQL.getVal(), dbId, quotaClientChecked,startDate,endDate);
		dto.setReportName(String.format("%s[%s],iops指标", generalMonitorBO.getMonitorName(),generalMonitorBO.getIpAddr()));
		
		MultipleDataBO[] array = dto.getSeriesData();
		for(MultipleDataBO data : array){
			data.setType("spline");
		}
		
		return dto;
	} 
	
	
	/**
	 * 逻辑读指标
	 * @param dbId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/lread/{dbId}/{startDate}/{endDate}", method = RequestMethod.GET)
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
		GeneralMonitorBO generalMonitorBO = generalQuotaReportDataManager.getMonitorInfo(dbId, SysTypeEnum.MySQL.getVal());
				
		//只选择 INNODB_BUFFER_POOL_READ_REQUESTS (InnoDB已经完成的逻辑读请求数) 44
		MultipleGeneralReportDataBO dto = generalQuotaReportDataManager.listMultipleQuotaGeneralReportData(SysTypeEnum.MySQL.getVal(), dbId, "44",startDate,endDate);
		dto.setReportName(String.format("%s[%s],逻辑读", generalMonitorBO.getMonitorName(),generalMonitorBO.getIpAddr()));
		return dto;
	}
	
	
	/**
	 * 连接数指标
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
		GeneralMonitorBO generalMonitorBO = generalQuotaReportDataManager.getMonitorInfo(dbId, SysTypeEnum.MySQL.getVal());
				
		//只选择 THREADS_CONNECTED 18
		MultipleGeneralReportDataBO dto = generalQuotaReportDataManager.listMultipleQuotaGeneralReportData(SysTypeEnum.MySQL.getVal(), dbId, "18",startDate,endDate);
		dto.setReportName(String.format("%s[%s],连接数", generalMonitorBO.getMonitorName(),generalMonitorBO.getIpAddr()));
		return dto;
	}
	
	
	/**
	 * MySQL流量指标
	 * @param dbId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/sendandrecv/{dbId}/{startDate}/{endDate}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO sendAndRecv(
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
		GeneralMonitorBO generalMonitorBO = generalQuotaReportDataManager.getMonitorInfo(dbId, SysTypeEnum.MySQL.getVal());
		
		//只选择 BYTES_RECEIVED 43, BYTES_SENT 42
		MultipleGeneralReportDataBO dto = generalQuotaReportDataManager.listMultipleQuotaGeneralReportData(SysTypeEnum.MySQL.getVal(), dbId, "42_43",startDate,endDate);
		dto.setReportName(String.format("%s[%s],MySQL流量指标", generalMonitorBO.getMonitorName(),generalMonitorBO.getIpAddr()));
		return dto;
	}
	
	
	/**
	 * 从库相对主库的延时
	 * @param dbId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/secondsbehindmaster/{dbId}/{startDate}/{endDate}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO secondsBehindMaster(
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
		GeneralMonitorBO generalMonitorBO = generalQuotaReportDataManager.getMonitorInfo(dbId, SysTypeEnum.MySQL.getVal());
				
		//只选择 THREADS_CONNECTED 18
		MultipleGeneralReportDataBO dto = generalQuotaReportDataManager.listMultipleQuotaGeneralReportData(SysTypeEnum.MySQL.getVal(), dbId, "77777780",startDate,endDate);
		dto.setReportName(String.format("%s[%s],从库相对主库的延时(秒)", generalMonitorBO.getMonitorName(),generalMonitorBO.getIpAddr()));
		return dto;
	}
	
	
	/**
	 * mysql 进程对应的cpu使用率(%)
	 * @param dbId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/mysqldcpu/{dbId}/{startDate}/{endDate}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO mysqldCpu(
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
		GeneralMonitorBO generalMonitorBO = generalQuotaReportDataManager.getMonitorInfo(dbId, SysTypeEnum.MySQL.getVal());
				
		//只选择 THREADS_CONNECTED 18
		MultipleGeneralReportDataBO dto = generalQuotaReportDataManager.listMultipleQuotaGeneralReportData(SysTypeEnum.MySQL.getVal(), dbId, "77777779",startDate,endDate);
		dto.setReportName(String.format("%s[%s],MySQL进程对应的cpu使用率", generalMonitorBO.getMonitorName(),generalMonitorBO.getIpAddr()));
		return dto;
	}
}
