/**   
* @Title: SpringContextHolder.java 
* @Package com.tyyd.ywpt.tools.bean 
* @Description:  
* @author wangyu   
* @date 2014-7-2 上午11:03:58 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.tools.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/**
 * @author wangyu
 *
 */
public class SpringContextHolder implements ApplicationContextAware{

private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		SpringContextHolder.applicationContext =context;  
	}

	/**
	 * @return the context
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {  
		if(!applicationContext.containsBean(name)){
			return null;
		}
        return (T) applicationContext.getBean(name);  
    } 
	
	
	@SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> clazz) {
        return (T) applicationContext.getBeansOfType(clazz);
    }
	
}
