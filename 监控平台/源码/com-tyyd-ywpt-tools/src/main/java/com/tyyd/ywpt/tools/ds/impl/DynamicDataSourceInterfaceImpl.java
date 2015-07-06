/**   
* @Title: DynamicDataSourceInterfaceImpl.java 
* @Package com.tyyd.ywpt.tools.ds.impl 
* @Description:  
* @author wangyu   
* @date 2014-7-2 下午4:59:00 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.tools.ds.impl;

import com.tyyd.ywpt.tools.ds.DynamicDataSourceInterface;
import com.tyyd.ywpt.tools.ds.dataobject.DynamicDataSourceDomain;

/**
 * @author wangyu
 *
 */
public class DynamicDataSourceInterfaceImpl implements DynamicDataSourceInterface{

	private SingleDataSourceBeanRegUtils singleDataSourceBeanRegUtils;

	@Override
	public void regAnNewDataSource(String beanId, DynamicDataSourceDomain domain) {
		singleDataSourceBeanRegUtils.regDataSource(beanId, domain);
		singleDataSourceBeanRegUtils.startDataSource();
	}

	/**
	 * @return the singleDataSourceBeanRegUtils
	 */
	public SingleDataSourceBeanRegUtils getSingleDataSourceBeanRegUtils() {
		return singleDataSourceBeanRegUtils;
	}

	/**
	 * @param singleDataSourceBeanRegUtils the singleDataSourceBeanRegUtils to set
	 */
	public void setSingleDataSourceBeanRegUtils(
			SingleDataSourceBeanRegUtils singleDataSourceBeanRegUtils) {
		this.singleDataSourceBeanRegUtils = singleDataSourceBeanRegUtils;
	}
	
}
