/**   
* @Title: SingleDataSourceBeanRegUtils.java 
* @Package com.tyyd.ywpt.tools.ds 
* @Description:  
* @author wangyu   
* @date 2014-7-2 下午4:16:04 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.tools.ds.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;

import com.tyyd.ywpt.tools.bean.SpringContextHolder;
import com.tyyd.ywpt.tools.ds.dataobject.DynamicDataSourceDomain;

/**
 * @author wangyu
 *
 */
public class SingleDataSourceBeanRegUtils {
	
	private static final String datasource_bean_class = "org.apache.commons.dbcp.BasicDataSource";  
	private static final String oracle_driver = "oracle.jdbc.driver.OracleDriver";
	private static final String mysql_driver = "com.mysql.jdbc.Driver";
	
	private int maxActive;
	
	private int maxIdle;
	
	private long maxWait;
	
	private boolean defaultAutoCommit;
	
	private static Map<Object,Object> targetDataSources = new HashMap<Object,Object>();
	
	private DesignatedDynamicDataSource designatedDynamicDataSource;
	
	
	
	public void regDataSource(String beanId,DynamicDataSourceDomain domain){
		
		ConfigurableApplicationContext applicationContext = (ConfigurableApplicationContext) SpringContextHolder.getApplicationContext();  
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory(); 
		
        //  创建bean  
        BeanDefinitionBuilder bdb = BeanDefinitionBuilder.rootBeanDefinition(datasource_bean_class);  
        bdb.getBeanDefinition().setAttribute("id", beanId);  
        bdb.getBeanDefinition().setDestroyMethodName("close");
        if(domain.getDbType().equalsIgnoreCase(DynamicDataSourceDomain.dbEnum.Oracle.getDatabase())){
        	//如果是oracle
        	bdb.addPropertyValue("driverClassName", oracle_driver);  
        	bdb.addPropertyValue("validationQuery", "select 1 from dual"); 
        }else if(domain.getDbType().equalsIgnoreCase(DynamicDataSourceDomain.dbEnum.MySQL.getDatabase())){
        	//如果是MySQL
        	bdb.addPropertyValue("driverClassName", mysql_driver); 
        	bdb.addPropertyValue("validationQuery", "select 1"); 
        }
        bdb.addPropertyValue("url", domain.toCreateConnectUrl());  
        bdb.addPropertyValue("username", domain.getUserName());  
        bdb.addPropertyValue("password", domain.getPasswd());  
        bdb.addPropertyValue("maxActive", maxActive);  
        bdb.addPropertyValue("maxIdle", maxIdle);  
        bdb.addPropertyValue("maxWait", maxWait);  
        bdb.addPropertyValue("defaultAutoCommit", defaultAutoCommit); 
        
        //配置重连机制
        bdb.addPropertyValue("testWhileIdle", false); 
        bdb.addPropertyValue("testOnBorrow", true); 
        bdb.addPropertyValue("testOnReturn", false); 
        
        bdb.addPropertyValue("timeBetweenEvictionRunsMillis", 30000); 
        bdb.addPropertyValue("numTestsPerEvictionRun", 10); 
        bdb.addPropertyValue("minEvictableIdleTimeMillis", 1800000); 
        
        
        
        //  注册bean  
        beanFactory.registerBeanDefinition(beanId, bdb.getBeanDefinition());  
        
        //  放入map中，注意一定是刚才创建bean对象  
        targetDataSources.put(beanId, SpringContextHolder.getBean(beanId));  
        
        //  将创建的map对象set到 targetDataSources；  
        designatedDynamicDataSource.setTargetDataSources(targetDataSources);
		
	}

	public void startDataSource(){
		 //  必须执行此操作，才会重新初始化AbstractRoutingDataSource 中的 resolvedDataSources，也只有这样，动态切换才会起效  
		designatedDynamicDataSource.afterPropertiesSet();
	}
	
	
	/**
	 * @return the maxActive
	 */
	public int getMaxActive() {
		return maxActive;
	}

	/**
	 * @param maxActive the maxActive to set
	 */
	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	/**
	 * @return the maxIdle
	 */
	public int getMaxIdle() {
		return maxIdle;
	}

	/**
	 * @param maxIdle the maxIdle to set
	 */
	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	/**
	 * @return the maxWait
	 */
	public long getMaxWait() {
		return maxWait;
	}

	/**
	 * @param maxWait the maxWait to set
	 */
	public void setMaxWait(long maxWait) {
		this.maxWait = maxWait;
	}

	/**
	 * @return the defaultAutoCommit
	 */
	public boolean isDefaultAutoCommit() {
		return defaultAutoCommit;
	}

	/**
	 * @param defaultAutoCommit the defaultAutoCommit to set
	 */
	public void setDefaultAutoCommit(boolean defaultAutoCommit) {
		this.defaultAutoCommit = defaultAutoCommit;
	}

	/**
	 * @return the designatedDynamicDataSource
	 */
	public DesignatedDynamicDataSource getDesignatedDynamicDataSource() {
		return designatedDynamicDataSource;
	}

	/**
	 * @param designatedDynamicDataSource the designatedDynamicDataSource to set
	 */
	public void setDesignatedDynamicDataSource(
			DesignatedDynamicDataSource designatedDynamicDataSource) {
		this.designatedDynamicDataSource = designatedDynamicDataSource;
	}
	
	
	
}
