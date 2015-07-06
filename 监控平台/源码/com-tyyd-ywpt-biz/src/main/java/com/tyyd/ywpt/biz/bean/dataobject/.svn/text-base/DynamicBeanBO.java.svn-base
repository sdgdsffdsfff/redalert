/**   
* @Title: DynamicBeanBO.java 
* @Package com.tyyd.ywpt.biz.bean.dataobject 
* @Description:  
* @author wangyu   
* @date 2014-6-26 上午11:20:50 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.bean.dataobject;

import java.util.Date;

/**
 * @author wangyu
 *
 */
public class DynamicBeanBO<T> {

	/**
	 * beanId
	 * 规则:  
	 * 	主机 : "host_"+ hostId
	 * 	Oracle : "ora_" + db_id
	 *  MySQL  : "mysql_" +db_id
	 * 			
	 */
	private String beanId;
	
	/**
	 * 前缀
	 */
	private String prefix;
	
	/**
	 * 哪个对象被注册
	 */
	private T propertyBean;
	
	
	/**
	 * bean的创建时间
	 */
	private Date beanCreatedDate;
	

	/**
	 * @return the beanId
	 */
	public String getBeanId() {
		return beanId;
	}

	/**
	 * @param beanId the beanId to set
	 */
	public void setBeanId(String beanId) {
		this.beanId = beanId;
	}


	/**
	 * @return the beanCreatedDate
	 */
	public Date getBeanCreatedDate() {
		return beanCreatedDate;
	}


	/**
	 * @param beanCreatedDate the beanCreatedDate to set
	 */
	public void setBeanCreatedDate(Date beanCreatedDate) {
		this.beanCreatedDate = beanCreatedDate;
	}

	/**
	 * @return the propertyBean
	 */
	public T getPropertyBean() {
		return propertyBean;
	}

	/**
	 * @param propertyBean the propertyBean to set
	 */
	public void setPropertyBean(T propertyBean) {
		this.propertyBean = propertyBean;
	}

	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @param prefix the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	
}