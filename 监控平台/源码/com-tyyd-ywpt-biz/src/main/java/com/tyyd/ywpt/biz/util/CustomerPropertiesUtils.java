/**   
 * @Title: CustomerPropertiesUtils.java 
 * @Package com.tyyd.ywpt.biz.util 
 * @Description:  
 * @author wangyu   
 * @date 2014-7-24 下午2:07:25 
 * @CopyRight 天翼阅读
 * @version V1.0   
 */
package com.tyyd.ywpt.biz.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * @author wangyu
 * 
 */
public class CustomerPropertiesUtils implements InitializingBean{

	public static final Logger LOGGER = Logger.getLogger(CustomerPropertiesUtils.class);  
	
	private Properties props;
	
	private String location;
	
	public String getProperty(String name) {
		if(props == null){
			return StringUtils.EMPTY;
		}
		return props.getProperty(name);
	}

	
	/**
	 * @return the props
	 */
	public Properties getProps() {
		return props;
	}



	/**
	 * @param props the props to set
	 */
	public void setProps(Properties props) {
		this.props = props;
	}

	

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}


	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}


	@Override
	public void afterPropertiesSet() throws Exception {
		if(StringUtils.isBlank(location)){
			LOGGER.error("location is empty...");
			return ;
		}
		try{
			Resource resource = new ClassPathResource(location);
			Properties p = PropertiesLoaderUtils.loadProperties(resource);
			setProps(p);
		} catch (IOException e) {
			LOGGER.error("initing Properties :"+location+" is exception",e);
		}
		
		LOGGER.info("initing Properties :"+location+" is success");
	}
}
