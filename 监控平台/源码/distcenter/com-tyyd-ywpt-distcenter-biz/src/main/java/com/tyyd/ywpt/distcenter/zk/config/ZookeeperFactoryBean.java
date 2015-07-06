/**   
* @Title: ZookeeperFactoryBean.java 
* @Package com.tyyd.ywpt.distcenter.zk.config 
* @Description:  
* @author wangyu   
* @date 2015-1-4 下午6:15:14 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.distcenter.zk.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.UnhandledErrorListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.tyyd.ywpt.distcenter.zk.config.impl.NodeEventListener;

/**
 * @author wangyu
 *
 */
public class ZookeeperFactoryBean implements FactoryBean<CuratorFramework> , InitializingBean,DisposableBean{

	public static final Logger LOGGER = Logger.getLogger(ZookeeperFactoryBean.class);
	
	/**
	 * Zookeeper Client
	 */
	private CuratorFramework zkClient;
	
	
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
	public void destroy() throws Exception {
		zkClient.close();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(baseSleepTimeMs,maxRetries);
		
		//创建一个连接池
		zkClient = CuratorFrameworkFactory.builder()
						.connectString(zkConnectString)
						.retryPolicy(retryPolicy)
						.connectionTimeoutMs(connectionTimeoutMs)
						.sessionTimeoutMs(sessionTimeoutMs)
						.build();

		//添加监听
		//zkClient.getCuratorListenable().addListener(new NodeEventListener());
		
		
		//错误处理
		zkClient.getUnhandledErrorListenable().addListener(new UnhandledErrorListener() {
			
			@Override
			public void unhandledError(String message, Throwable e) {
				LOGGER.info("CuratorFramework unhandledError: "+ message);
			}
		});
		
		
		//启动连接服务(客户端)
		zkClient.start();
		
	}

	@Override
	public CuratorFramework getObject() throws Exception {
		return zkClient;
	}

	@Override
	public Class<?> getObjectType() {
		return CuratorFramework.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
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

	
	
	
}
