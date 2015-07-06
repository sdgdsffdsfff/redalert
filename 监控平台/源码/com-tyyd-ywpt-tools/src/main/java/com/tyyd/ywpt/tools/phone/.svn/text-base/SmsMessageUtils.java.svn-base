/**   
* @Title: SmsMessageUtils.java 
* @Package com.tyyd.ywpt.tools.phone 
* @Description:  
* @author wangyu   
* @date 2014-8-11 上午9:33:45 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.tools.phone;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tyyd.ywpt.tools.encrypt.MD5Utils;
import com.tyyd.ywpt.tools.http.HttpService;

/**
 * @author wangyu
 *
 */
public class SmsMessageUtils {

	public static final Logger LOGGER = Logger.getLogger(SmsMessageUtils.class);
	
	private HttpService httpService;
	
	private String phoneUrl; 

	private void sendMessage(String phoneNumber,String content){
		final String token = MD5Utils.getMD5Format("yutian" + getCurrentDate());
//		String tmp = "";
//		try {
//			tmp = new String(content.getBytes("GBK"), "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			LOGGER.error("转码出错", e);
//		}
//		
		Map<String,String> postParams = new HashMap<String,String>();
		postParams.put("telephone", phoneNumber);
		postParams.put("content", content);
		postParams.put("token", token);
		postParams.put("smsType", "3");
		postParams.put("orgType", "");
		postParams.put("appName", "ywpt");
		postParams.put("chanael", "");

		String result = httpService.postRequest(phoneUrl, postParams);
		LOGGER.info(result);
	}
	
	public void sendMessage(String[] phoneNumber,String content){
		if(phoneNumber == null || phoneNumber.length == 0 || StringUtils.isBlank(content)){
			return;
		}
		for(String phone : phoneNumber){
			if(StringUtils.isNotBlank(phone)){
				sendMessage(phone,content);
			}
		}
		
	}
	
	
	private static SimpleDateFormat getDateFormat(String pattern)
			throws RuntimeException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		dateFormat.applyPattern(pattern);
		return dateFormat;
	}
	
	private static String DateToString(Date date, String pattern) {  
        String dateString = null;  
        if (date != null) {  
            try {  
                dateString = getDateFormat(pattern).format(date);  
            } catch (Exception e) {  
            }  
        }  
        return dateString;  
    }  
  
    
    private static String getCurrentDate(){
    	return DateToString(new Date(),"yyyy-MM-dd");
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

	/**
	 * @return the phoneUrl
	 */
	public String getPhoneUrl() {
		return phoneUrl;
	}

	/**
	 * @param phoneUrl the phoneUrl to set
	 */
	public void setPhoneUrl(String phoneUrl) {
		this.phoneUrl = phoneUrl;
	}
    
    
}
