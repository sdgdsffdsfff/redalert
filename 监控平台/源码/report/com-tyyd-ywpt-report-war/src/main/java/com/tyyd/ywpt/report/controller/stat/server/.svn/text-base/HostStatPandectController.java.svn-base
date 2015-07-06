/**   
* @Title: HostStatPandectController.java 
* @Package com.tyyd.ywpt.report.controller.stat.server 
* @Description:  
* @author wangyu   
* @date 2015-2-2 上午11:30:42 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.controller.stat.server;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tyyd.ywpt.dao.stat.server.domain.HostStatPandectDomain;
import com.tyyd.ywpt.report.biz.stat.server.HostStatPandectManager;

/**
 * @author wangyu
 *
 */
@Controller
@RequestMapping("/report/stat/server/pandect")
public class HostStatPandectController {

	
	private static final String STAT_PANDECT_VIEWS = "/report/stat/server/sever_stat_pandect";
	
	
	@Resource
	private HostStatPandectManager hostStatPandectManager;
	
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView query(
			HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView model = new ModelAndView();
		
		List<HostStatPandectDomain> list = hostStatPandectManager.listHostStatPandect();
		
		model.addObject("dataList", list);
		
		model.setViewName(STAT_PANDECT_VIEWS);
		return model;
	}
	
}
