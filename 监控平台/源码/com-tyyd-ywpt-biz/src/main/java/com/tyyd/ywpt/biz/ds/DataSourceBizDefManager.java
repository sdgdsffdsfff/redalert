/**   
* @Title: DataSourceDefManager.java 
* @Package com.tyyd.ywpt.biz.ds 
* @Description:  
* @author wangyu   
* @date 2014-7-9 下午5:11:51 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.ds;

import java.util.Map;

import com.tyyd.ywpt.tools.ds.dataobject.DynamicDataSourceDomain;

/**
 * @author wangyu
 *
 */
public interface DataSourceBizDefManager {

	/**
	 * 根据服务ID获取MySQL的数据源定义
	 * @param serverId
	 * @return
	 */
	public Map<String,DynamicDataSourceDomain> getMySQLDsDef(String serverId);
	
	
	/**
	 * 根据服务ID获取Oracle的数据源定义
	 * @param serverId
	 * @return
	 */
	public Map<String,DynamicDataSourceDomain> getOracleDsDef(String serverId);
}
