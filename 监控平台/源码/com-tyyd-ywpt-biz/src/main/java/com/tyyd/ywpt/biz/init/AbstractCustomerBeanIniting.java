/**   
* @Title: AbstractCustomerBeanIniting.java 
* @Package com.tyyd.ywpt.biz.init 
* @Description:  
* @author wangyu   
* @date 2014-7-16 下午5:30:23 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.init;

import com.tyyd.ywpt.biz.bean.impl.CustomerBeanListFactory;
import com.tyyd.ywpt.tools.bean.SpringContextHolder;

/**
 * @author wangyu
 *
 */
public abstract class AbstractCustomerBeanIniting {

	/**
	 * 获取自定义的Bean
	 * @return
	 */
	public CustomerBeanListFactory getCustomerBeanListFactory(){
		return SpringContextHolder.getBean("customerBeanListFactory");
	} 
	
	
}
