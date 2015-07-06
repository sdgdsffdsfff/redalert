/**   
* @Title: DynamicDataSourceInterface.java 
* @Package com.tyyd.ywpt.tools.ds 
* @Description:  
* @author wangyu   
* @date 2014-7-2 下午4:56:53 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.tools.ds;

import com.tyyd.ywpt.tools.ds.dataobject.DynamicDataSourceDomain;

/**
 * @author wangyu
 *
 */
public interface DynamicDataSourceInterface {

	public void regAnNewDataSource(String beanId,DynamicDataSourceDomain domain);
}
