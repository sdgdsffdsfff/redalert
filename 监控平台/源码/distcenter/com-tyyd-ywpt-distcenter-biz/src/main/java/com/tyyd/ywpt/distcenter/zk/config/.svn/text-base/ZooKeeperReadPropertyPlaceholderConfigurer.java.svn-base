/**   
* @Title: ZooKeeperPropertyPlaceholderConfigurer.java 
* @Package com.tyyd.ywpt.distcenter.zk.config 
* @Description:  
* @author wangyu   
* @date 2015-1-4 下午9:31:22 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.distcenter.zk.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.WatchedEvent;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.ResourceUtils;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.util.WebUtils;

import com.tyyd.ywpt.tools.sysproperties.PlaceHolderUtils;

/**
 * @author wangyu
 * http://www.big-mouth.cn/blog/65.html
 */
public class ZooKeeperReadPropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer implements InitializingBean {

	
	private CuratorFramework zkClient;
	
	/**
	 * 本次需查询的根节点
	 */
	public String path;
	
	
	/**
	 * Zookeeper连接字符串
	 */
	private String zkConnectString;
	
	/**
	 * 重试间隔基数，最小单位
	 */
	private int baseSleepTimeMs;
	
	
	/**
	 * 心跳重试的次数
	 */
	private Integer maxRetries;
	
	
	/**
	 * 连接超时时间
	 */
	private Integer connectionTimeoutMs;
	
	
	/**
	 * session超时时间 
	 */
	private Integer sessionTimeoutMs;
	

	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {
		
		
		//加载数据
		Map<String,Object> data = loadCustomerProperties(path);
		
		//加载到内存
		props.putAll(data);
		
		super.processProperties(beanFactoryToProcess, props);
		
		//加watch
//		try {
//			zkClient.checkExists().usingWatcher(new CuratorWatcher(){
//				@Override
//				public void process(WatchedEvent event) throws Exception {
//					String znode = event.getPath();
//					String value = new String(zkClient.getData().forPath(znode),"utf-8");
//					String[] array = StringUtils.split(znode, "/");
//					String nodeName = array[array.length-1];
//					
//					
//				}}).forPath(path);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	/**
	 * 2层的结构
	 * @param path
	 * @return
	 */
	private Map<String, Object> loadCustomerProperties(String configPath) {
		
		getCuratorFramework();
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<String> children = zkClient.getChildren().forPath(configPath);
			
			//取第一层的目录
			for(String columnLevelNode : children){
				
				String znodePath = new StringBuffer(configPath).append("/").append(columnLevelNode).toString();
				
				List<String> lv2Children = zkClient.getChildren().forPath(znodePath);
				
				//获取具体的那层结构 
				for(String node : lv2Children){
					
					String znode = new StringBuffer(znodePath).append("/").append(node).toString(); 
					String value = new String(zkClient.getData().forPath(znode),"utf-8");
					
					result.put(node, value);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 因PropertyPlaceholderConfigurer的特殊性，比lazy_init=false的bean还要早加载，所以没有办法
	 * 只能那个做代码冗余，单独创建一个client
	 * @return the curatorFramework
	 */
	public void getCuratorFramework() {
		
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(baseSleepTimeMs,maxRetries);
		
		//创建一个连接
		zkClient = CuratorFrameworkFactory.builder()
				.connectString(zkConnectString)
				.retryPolicy(retryPolicy)
				.connectionTimeoutMs(connectionTimeoutMs)
				.sessionTimeoutMs(sessionTimeoutMs)
				.build();
		
		zkClient.start();
		
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the zkConnectString
	 */
	public String getZkConnectString() {
		return zkConnectString;
	}

	/**
	 * @param zkConnectString the zkConnectString to set
	 */
	public void setZkConnectString(String zkConnectString) {
		this.zkConnectString = zkConnectString;
	}

	/**
	 * @return the baseSleepTimeMs
	 */
	public int getBaseSleepTimeMs() {
		return baseSleepTimeMs;
	}

	/**
	 * @param baseSleepTimeMs the baseSleepTimeMs to set
	 */
	public void setBaseSleepTimeMs(int baseSleepTimeMs) {
		this.baseSleepTimeMs = baseSleepTimeMs;
	}

	/**
	 * @return the maxRetries
	 */
	public Integer getMaxRetries() {
		return maxRetries;
	}

	/**
	 * @param maxRetries the maxRetries to set
	 */
	public void setMaxRetries(Integer maxRetries) {
		this.maxRetries = maxRetries;
	}

	/**
	 * @return the connectionTimeoutMs
	 */
	public Integer getConnectionTimeoutMs() {
		return connectionTimeoutMs;
	}

	/**
	 * @param connectionTimeoutMs the connectionTimeoutMs to set
	 */
	public void setConnectionTimeoutMs(Integer connectionTimeoutMs) {
		this.connectionTimeoutMs = connectionTimeoutMs;
	}

	/**
	 * @return the sessionTimeoutMs
	 */
	public Integer getSessionTimeoutMs() {
		return sessionTimeoutMs;
	}

	/**
	 * @param sessionTimeoutMs the sessionTimeoutMs to set
	 */
	public void setSessionTimeoutMs(Integer sessionTimeoutMs) {
		this.sessionTimeoutMs = sessionTimeoutMs;
	}

	private Properties getProperties(String location) {

		Properties properties = new Properties();
		InputStream inputStream = null;

		try {
			inputStream = getClass().getResourceAsStream(location);
			properties.load(inputStream);
			PlaceHolderUtils.convertProperties(properties);
			
		} catch (FileNotFoundException ex) {
			throw new IllegalArgumentException("Invalid 'log4jProperties' parameter:" + ex.getMessage());
		} catch (IOException ex) {
			throw new IllegalArgumentException("Invalid 'log4jProperties' parameter:" + ex.getMessage());
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}
		}

		return properties;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		Properties prop = getProperties("/conf/dist_center_biz_config.properties");
		setZkConnectString(prop.getProperty("com.tyyd.dist.center.zk.zkConnectString"));
		setPath(prop.getProperty("com.tyyd.dist.center.zk.config.path"));
		
	}
	
}
