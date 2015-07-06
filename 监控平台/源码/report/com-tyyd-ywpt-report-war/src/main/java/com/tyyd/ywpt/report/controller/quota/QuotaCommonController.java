/**   
* @Title: QuotaCommonController.java 
* @Package com.tyyd.ywpt.report.controller.quota 
* @Description:  
* @author wangyu   
* @date 2015-3-25 下午2:31:55 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.controller.quota;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tyyd.ywpt.biz.dict.BaseLineQuotasEnum;

/**
 * @author wangyu
 *
 */
@Controller
@RequestMapping("/report/common/quota")
public class QuotaCommonController {


	public static final Logger LOGGER = Logger.getLogger(QuotaCommonController.class);  
	
	
	/**
	 * 指标集合
	 * @param dbId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/list/{configType}", method = RequestMethod.GET)
	public @ResponseBody Map<String,String> baselineQuotas(
			@PathVariable("configType") String configType,
			HttpServletRequest request, HttpServletResponse response) {

		// 参数检查
		if (StringUtils.isBlank(configType)) {
			LOGGER.error("参数检查错误");
			return null;
		}
		
		return BaseLineQuotasEnum.getQuotas(configType);
	
	}
	
	
	
	
	
	
	
}
