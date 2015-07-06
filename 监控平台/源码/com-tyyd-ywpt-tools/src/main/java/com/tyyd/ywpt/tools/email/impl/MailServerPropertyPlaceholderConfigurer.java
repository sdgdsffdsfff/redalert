/**   
* @Title: MailServerPropertyPlaceholderConfigurer.java 
* @Package com.tyyd.ywpt.tools.email.impl 
* @Description:  
* @author wangyu   
* @date 2014-11-27 下午3:37:33 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.tools.email.impl;

import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * @author wangyu
 *
 */
public class MailServerPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer{

	@Override
	protected String resolvePlaceholder(String placeholder, Properties props) {

		
		
		return super.resolvePlaceholder(placeholder, props);
	}

	
	
	
}
