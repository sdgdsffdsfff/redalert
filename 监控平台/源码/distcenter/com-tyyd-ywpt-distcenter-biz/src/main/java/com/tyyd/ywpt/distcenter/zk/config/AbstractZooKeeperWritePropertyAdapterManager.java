/**   
* @Title: ZooKeeperWritePropertyManager.java 
* @Package com.tyyd.ywpt.distcenter.zk.config 
* @Description:  
* @author wangyu   
* @date 2015-1-7 下午4:04:29 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.distcenter.zk.config;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.log4j.Logger;
import org.apache.zookeeper.data.Stat;

import com.tyyd.ywpt.distcenter.zk.data.DistCenterConfigAdapterManager;
import com.tyyd.ywpt.distcenter.zk.data.dataobject.StandardZookeeperDataTypeBO;
import com.tyyd.ywpt.tools.bean.SpringContextHolder;

/**
 * @author wangyu
 *
 */
public abstract class AbstractZooKeeperWritePropertyAdapterManager {

	public static final Logger LOGGER = Logger.getLogger(AbstractZooKeeperWritePropertyAdapterManager.class);  
	
	private String path;
	
	/**
	 * 把数据写入ZK
	 * @return
	 */
	public boolean writeMapKeyToZKDistCener() {
		Boolean result = Boolean.FALSE;
		
		//初始化本次任务的root path
		initRootPath();
		
		//加载数据源
		List<StandardZookeeperDataTypeBO> dataMap = loadDistCenterConfig();
		
		//初始化root path
		createRootKeyPath(path,"");
		
		//数据源数据不为空
		if(CollectionUtils.isNotEmpty(dataMap)){
			for(StandardZookeeperDataTypeBO bo : dataMap){
				String key = bo.getKey();
				Object value = bo.getValue();
				
				String column = bo.getColumn();
				
				String znode = null;
				if(StringUtils.isNotBlank(column)){
					znode = new StringBuffer(getPath()).append("/").append(column).append("/").append(key).toString();
				}else{
					znode = new StringBuffer(getPath()).append("/").append(key).toString();
				}
				
				//判断是否存在
				boolean isExists = isExistsRootPath(znode);
				
				if(isExists){
					//设置znode
					updateZnode(znode,value.toString());
				}else{
					//创建一个znode
					createRootKeyPath(znode,value.toString());
				}
			}
		}
		
		return result;
	}

	
	


	/**
	 * @param znode
	 * @param string
	 */
	private void updateZnode(String znode, String value) {
		try {
			getCuratorFramework().setData().forPath(znode, value.getBytes());
		} catch (Exception e) {
			LOGGER.error("更新znode内容失败", e);
		}
	}

	/**
	 * 创建根路径
	 * @param path2
	 */
	private void createRootKeyPath(String path,String value) {
		try {
			getCuratorFramework().create().creatingParentsIfNeeded().forPath(path, value.getBytes());
		} catch (Exception e) {
			LOGGER.error("更新znode内容失败", e);
		}
	}

	/**
	 * 判断是否存在路径
	 * @param path2
	 * @return
	 */
	private boolean isExistsRootPath(String path){
		boolean isExists = Boolean.FALSE;
		try {
			Stat stat = getCuratorFramework().checkExists().forPath(path);
			if(null != stat){
				isExists = Boolean.TRUE;
			}
		} catch (Exception e) {
			isExists = Boolean.TRUE;
			
			//纪录日志
			LOGGER.error("zk判断路径是否存在失败", e);
		}
		
		return isExists;
	}

	/**
	 * 加载数据
	 * @return
	 */
	protected abstract List<StandardZookeeperDataTypeBO> loadDistCenterConfig();

	
	/**
	 * 初始化root path
	 */
	protected abstract void initRootPath() ;
	
	/**
	 * @return the path
	 */
	private String getPath() {
		return path;
	}
	
	
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * 获取CuratorFrameWork
	 * @return
	 */
	protected CuratorFramework getCuratorFramework() {
		return SpringContextHolder.getBean("zookeeperFactoryBean");
	}
	
	
	protected DistCenterConfigAdapterManager getDistCenterConfigAdapterManager() {
		return SpringContextHolder.getBean("distCenterConfigAdapterManager");
	}
}
