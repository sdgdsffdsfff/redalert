/**   
* @Title: MySQLGlobalStatController.java 
* @Package com.tyyd.ywpt.report.controller.stat.mysql 
* @Description:  
* @author wangyu   
* @date 2014-12-11 下午5:14:09 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.controller.stat.mysql;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tyyd.ywpt.report.biz.stat.mysql.MySQLGlobalStatManager;
import com.tyyd.ywpt.report.biz.stat.mysql.dto.MySQLGlobalStatDTO;

/**
 * @author wangyu
 *
 */

@Controller
@RequestMapping("/report/stat/mysql/global")
public class MySQLGlobalStatController {

	private static final String EVENT_LIST_VIEWS = "/report/stat/mysql/mysql_global_stat";
	
	
	@Resource
	private MySQLGlobalStatManager mySQLGlobalStatManager;
	
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView eventList(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView model = new ModelAndView();
		
		List<MySQLGlobalStatDTO> list = mySQLGlobalStatManager.listMySQLGlobalStat();
		
		model.addObject("statList", list);
		model.setViewName(EVENT_LIST_VIEWS);
		return model;
	}
	
	
}
