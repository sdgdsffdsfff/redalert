/**   
* @Title: DynamicBeanRegisterBindingImpl.java 
* @Package com.tyyd.ywpt.biz.bean.impl 
* @Description:  
* @author wangyu   
* @date 2014-6-26 下午1:47:45 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.bean.impl;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;

import com.tyyd.ywpt.biz.bean.DynamicHostBeanRegisterBinding;
import com.tyyd.ywpt.biz.init.AbstractCustomerBeanIniting;
import com.tyyd.ywpt.biz.ssh.impl.AbstractSSHConnectManager;
import com.tyyd.ywpt.tools.bean.SpringContextHolder;

/**
 * @author wangyu
 *
 */
public class DynamicBeanRegisterBindingImpl extends AbstractCustomerBeanIniting implements DynamicHostBeanRegisterBinding {


	public static final Logger LOGGER = Logger.getLogger(DynamicBeanRegisterBindingImpl.class);  
	
	private final static String PROTOTYPE_BEAN_REG_TYPE = "prototype";
	private final static String SINGLETON_BEAN_REG_TYPE = "singleton";
	
	
	
	@Override
	public boolean removeBean(String beanId) {
		boolean isSucess = false;
		
		if(StringUtils.isBlank(beanId)){
			//记录错误日志
			return isSucess;
		}
		
		//从Spring容器中删除
		if(!removeApplicationContextBean(beanId)){
			return isSucess;
		}
		
		//从自定义bean中删除注册bean
		return removeBeanDefinition(beanId);
	}
	
	
	/**
	 * 从Spring中删除Bean
	 * @param beanId
	 * @return
	 */
	private boolean removeApplicationContextBean(String beanId){
		boolean isSucess = false;
		ConfigurableApplicationContext applicationContext = (ConfigurableApplicationContext) SpringContextHolder.getApplicationContext();  
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory(); 
		try{
			//从application中删除
			if(beanFactory.containsBean(beanId)){
				beanFactory.removeBeanDefinition(beanId);
			}
		}catch(NoSuchBeanDefinitionException ex){
			return isSucess; 
		}
		return true;
	}
	

	@Override
	public boolean regSimpleBean(String beanId, Class<?> registerBeanClass,
			Object propertyBean, Map<String, Object> propertyMap) {
		boolean isSucess = false;
		
		//参数检查
		if(!checkBeanParameter(beanId, registerBeanClass, propertyBean, propertyMap)){
			LOGGER.error("注册Bean失败，参数异常["+propertyMap.toString()+"]");
			return isSucess;
		}
		
		//Spring bean注册
		if(!applicationBeanDefinitionBuild(beanId, registerBeanClass, propertyBean, propertyMap)){
			LOGGER.error("注册Bean失败，beanId="+beanId);
			return isSucess;
		}
		
		 //注册到自定义Bean管理中心
        return regeditCustomerBeanFactory(beanId,propertyBean);
	}

	
	private boolean checkBeanParameter(String beanId, Class<?> registerBeanClass,
			Object propertyBean, Map<String, Object> propertyMap){
		boolean isSucess = false;
		
		//beanId 为空
		if(StringUtils.isBlank(beanId)){
			return isSucess;
		}
		
		//定义的bean
		if(registerBeanClass == null){
			return isSucess;
		}
		
		return true;
	}
	
	private boolean applicationBeanDefinitionBuild(String beanId, Class<?> registerBeanClass,
			Object propertyBean, Map<String, Object> propertyMap){
		
		boolean isSucess = false;
		ConfigurableApplicationContext applicationContext = (ConfigurableApplicationContext) SpringContextHolder.getApplicationContext();  
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory(); 
		
		//如果bean不存在
		 if (!beanFactory.containsBean(beanId)) {
			 //定义一个Bean
	         BeanDefinitionBuilder bdb = BeanDefinitionBuilder.genericBeanDefinition(registerBeanClass);  
	         
	         //配置参数
	         if(propertyMap != null){
	        	 Iterator<String> iter = propertyMap.keySet().iterator();
 	        	 while(iter.hasNext()){
 	        		String key = iter.next();
 	        		Object value = propertyMap.get(key);
 	        		bdb.addPropertyValue(key, value);
 	        	 }
	         }
	         
	         //设置为prototype,每次都要重新初始化
	         bdb.setScope(PROTOTYPE_BEAN_REG_TYPE);
	         
	         // parent.isAssignableFrom(child)
	         if(AbstractSSHConnectManager.class.isAssignableFrom(registerBeanClass)){
//	        	 bdb.setInitMethodName("init");
//	        	 bdb.setLazyInit(false);
//	        	 bdb.setScope(SINGLETON_BEAN_REG_TYPE);
	         }
	         
	         
	         //注册到applicationContext中
	         try{
	        	 beanFactory.registerBeanDefinition(beanId, bdb.getBeanDefinition());  
	         }catch(BeanDefinitionStoreException ex){
	        	 LOGGER.error("动态注册Bean异常",ex);
	        	 return isSucess;
	         }
	     } 
		 
		 return true;
	}
	
	/**
	 * 注册到自定义Bean管理中心
	 * @param propertyBean
	 */
	private boolean regeditCustomerBeanFactory(String beanId,Object propertyBean){
		return getCustomerBeanListFactory().beanDefinitionBuild(beanId, propertyBean);
	}

	
	
	/**
	 * 从自定义Bean管理中心删除
	 * @param beanId
	 */
	private boolean removeBeanDefinition(String beanId){
		return getCustomerBeanListFactory().removeBeanDefinition(beanId);
	}

}
