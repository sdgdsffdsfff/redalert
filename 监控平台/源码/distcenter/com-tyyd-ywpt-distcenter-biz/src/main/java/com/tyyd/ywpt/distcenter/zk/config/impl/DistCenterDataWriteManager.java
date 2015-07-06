/**   
* @Title: DistCenterDataWriteManager.java 
* @Package com.tyyd.ywpt.distcenter.zk.config.impl 
* @Description:  
* @author wangyu   
* @date 2015-1-8 下午3:24:30 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.distcenter.zk.config.impl;

import java.util.List;

import com.tyyd.ywpt.distcenter.zk.config.AbstractZooKeeperWritePropertyAdapterManager;
import com.tyyd.ywpt.distcenter.zk.data.dataobject.StandardZookeeperDataTypeBO;

/**
 * @author wangyu
 *
 */
public class DistCenterDataWriteManager extends
		AbstractZooKeeperWritePropertyAdapterManager {

	private final static String CONFIGURATION_ROOT_PATH = "/tyyd/configuration";
	
	
	@Override
	protected List<StandardZookeeperDataTypeBO> loadDistCenterConfig() {
		return getDistCenterConfigAdapterManager().listDistConfigAdapter();
	}


	@Override
	protected void initRootPath() {
		setPath(CONFIGURATION_ROOT_PATH);
	}


}
