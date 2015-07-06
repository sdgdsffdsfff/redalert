/**   
* @Title: DynamicDataSourceAutoWiredImpl.java 
* @Package com.tyyd.ywpt.biz.ds.impl 
* @Description:  
* @author wangyu   
* @date 2014-7-2 上午10:10:18 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.tools.ds.impl;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author wangyu
 *
 */
public class DesignatedDynamicDataSource extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		return DynamicDataSourceContextHolder.getDataSource();
	}

}
