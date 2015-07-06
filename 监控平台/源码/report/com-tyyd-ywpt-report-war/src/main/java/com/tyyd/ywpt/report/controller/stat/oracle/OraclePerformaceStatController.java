/**   
* @Title: OraclePerformaceStatController.java 
* @Package com.tyyd.ywpt.report.controller.stat.oracle 
* @Description:  
* @author wangyu   
* @date 2015-5-14 上午11:37:31 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.controller.stat.oracle;

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

import com.tyyd.ywpt.dao.stat.oracle.dataobject.OracleSnapShotDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.snap.OracleSnapActiceSessionDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.snap.OracleSnapEventClassDetailDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.snap.OracleSnapEventSQLDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.snap.OracleSnapTopSQLDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.snap.OracleSnapWaitEventDomain;
import com.tyyd.ywpt.report.biz.stat.oracle.performace.OraclePerformanceStatManager;

/**
 * @author wangyu
 *
 */

@Controller
@RequestMapping("/report/stat/oracle/performance")
public class OraclePerformaceStatController {

	private static final String VIEWS = "/report/stat/oracle/performance/startPage";
	private static final String ERROR_VIEWS = "/report/stat/oracle/performance/error";
	
	
	
	@Resource
	private OraclePerformanceStatManager oraclePerformanceStatManager;
	
	/**
	 * 展示页
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView startPage(
			@PathVariable("id") String id,
			HttpServletRequest request, 
			HttpServletResponse response){
		
		ModelAndView model = new ModelAndView();
		
		OracleSnapShotDomain domain = oraclePerformanceStatManager.getSnap(id);
		
		model.setViewName(VIEWS);
		model.addObject("domain", domain);
		
		if(domain == null){
			model.addObject("errorMsg", "请检查oraDatabase和instanceId是否配置、及AWR数据是否采集");
			model.setViewName(ERROR_VIEWS);
		}
		
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
	@RequestMapping(value = "/data/topsql/{snapId}/{dataBaseId}/{instanceId}", method = RequestMethod.GET)
	public @ResponseBody List<OracleSnapTopSQLDomain>  getTopSQL(
			@PathVariable("snapId") Long snapId,
			@PathVariable("dataBaseId") Long dataBaseId,
			@PathVariable("instanceId") Long instanceId,
			HttpServletRequest request, 
			HttpServletResponse response) {
		
		String sortColumn = request.getParameter("columnId");
		String sortType = request.getParameter("sortType");
		
		
		List<OracleSnapTopSQLDomain> list = oraclePerformanceStatManager.getTopSQL(snapId, dataBaseId, instanceId,sortColumn,sortType);
		
		return list;
	}
	
	
	/**
	 * active session
	 * @param snapId
	 * @param dataBaseId
	 * @param instanceId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/activesession/{snapId}/{dataBaseId}/{instanceId}", method = RequestMethod.GET)
	public @ResponseBody List<OracleSnapActiceSessionDomain>  activeSession(
			@PathVariable("snapId") Long snapId,
			@PathVariable("dataBaseId") Long dataBaseId,
			@PathVariable("instanceId") Long instanceId,
			HttpServletRequest request, 
			HttpServletResponse response) {
		
		List<OracleSnapActiceSessionDomain> list = oraclePerformanceStatManager.listActiceSession(snapId, dataBaseId, instanceId);
		
		return list;
	}
	
	
	/**
	 * Total Wait Event
	 * @param snapId
	 * @param dataBaseId
	 * @param instanceId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/totalwaitevent/{snapId}/{dataBaseId}/{instanceId}/{lastSnapId}", method = RequestMethod.GET)
	public @ResponseBody List<OracleSnapWaitEventDomain>  totalWaitEvent(
			@PathVariable("snapId") Long snapId,
			@PathVariable("dataBaseId") Long dataBaseId,
			@PathVariable("instanceId") Long instanceId,
			@PathVariable("lastSnapId") Long lastSnapId,
			HttpServletRequest request, 
			HttpServletResponse response) {
		
		List<OracleSnapWaitEventDomain> list = oraclePerformanceStatManager.totalWaitEvent(snapId, dataBaseId, instanceId,lastSnapId);
		
		return list;
	}
	
	
	/**
	 * load Event By Class
	 * @param snapId
	 * @param dataBaseId
	 * @param instanceId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/loadeventbyclass/{snapId}/{dataBaseId}/{instanceId}/{lastSnapId}", method = RequestMethod.GET)
	public @ResponseBody List<OracleSnapEventClassDetailDomain>  loadEventByClass(
			@PathVariable("snapId") Long snapId,
			@PathVariable("dataBaseId") Long dataBaseId,
			@PathVariable("instanceId") Long instanceId,
			@PathVariable("lastSnapId") Long lastSnapId,
			HttpServletRequest request, 
			HttpServletResponse response) {
		
		String waitClass = request.getParameter("waitClass");
		List<OracleSnapEventClassDetailDomain> list = oraclePerformanceStatManager.loadEventByClass(snapId, dataBaseId, instanceId,waitClass,lastSnapId);
		
		return list;
	}
	
	/**
	 * load Event Sql
	 * @param snapId
	 * @param dataBaseId
	 * @param instanceId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/loadeventsql/{snapId}/{dataBaseId}/{instanceId}/{lastSnapId}", method = RequestMethod.GET)
	public @ResponseBody List<OracleSnapEventSQLDomain>  loadEventSql(
			@PathVariable("snapId") Long snapId,
			@PathVariable("dataBaseId") Long dataBaseId,
			@PathVariable("instanceId") Long instanceId,
			HttpServletRequest request, 
			HttpServletResponse response) {
		
		String eventName = request.getParameter("eventName"); 
		List<OracleSnapEventSQLDomain> list = oraclePerformanceStatManager.loadEventSql(snapId, dataBaseId, instanceId,eventName);
		
		return list;
	}
	
}
