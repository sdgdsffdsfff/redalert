/**   
* @Title: DynamicHostBeanAutoWire.java 
* @Package com.tyyd.ywpt.biz.bean.impl 
* @Description:  
* @author wangyu   
* @date 2014-7-3 下午3:11:38 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.bean.impl;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class DynamicHostBeanAutoWire implements ApplicationListener {

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if(event instanceof ContextRefreshedEvent){
			loadHost();
		}
	}

	/**
	 * 
	 */
	private void loadHost() {
		
	}
	
	

}
