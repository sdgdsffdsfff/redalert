/**   
* @Title: DistCenterConfigAdapterManager.java 
* @Package com.tyyd.ywpt.distcenter.zk.data 
* @Description:  
* @author wangyu   
* @date 2015-1-7 下午3:13:59 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.distcenter.zk.data;

import java.util.List;

import com.tyyd.ywpt.distcenter.zk.data.dataobject.StandardZookeeperDataTypeBO;

/**
 * @author wangyu
 *
 */
public interface DistCenterConfigAdapterManager {

	public List<StandardZookeeperDataTypeBO> listDistConfigAdapter();
	
}
