/**   
* @Title: IndexPageController.java 
* @Package com.tyyd.ywpt.report.controller 
* @Description:  
* @author wangyu   
* @date 2014-11-13 上午9:37:06 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author wangyu
 *
 */
@Controller
@RequestMapping("/report/index")
public class IndexPageController {

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/report/index/index");
		return mv;
	}
}
