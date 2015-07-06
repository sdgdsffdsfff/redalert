/**   
* @Title: CommonHttpUtils.java 
* @Package com.tyyd.ywpt.tools.http 
* @Description:  
* @author wangyu   
* @date 2015-1-23 下午3:22:17 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.tools.http;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author wangyu
 *
 */
public class CommonHttpUtils {

	public static final Logger LOGGER = Logger.getLogger(CommonHttpUtils.class);
	
	private HttpService httpService;
	
	public String getRequest(String url){
		String result = "";
		if(StringUtils.isNotBlank(url)){
			result = httpService.getRequest(url);
		}
		return result;
	}
	

	/**
	 * @return the httpService
	 */
	public HttpService getHttpService() {
		return httpService;
	}

	/**
	 * @param httpService the httpService to set
	 */
	public void setHttpService(HttpService httpService) {
		this.httpService = httpService;
	}

	
	
}
