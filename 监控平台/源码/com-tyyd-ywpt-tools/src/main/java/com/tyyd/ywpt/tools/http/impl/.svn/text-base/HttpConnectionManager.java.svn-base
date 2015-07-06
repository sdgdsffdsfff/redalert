/**   
 * @Title: HttpConnectionManager.java 
 * @Package com.tyyd.ywpt.tools.http.impl 
 * @Description:  
 * @author wangyu   
 * @date 2014-8-8 下午4:47:41 
 * @CopyRight 天翼阅读
 * @version V1.0   
 */
package com.tyyd.ywpt.tools.http.impl;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author wangyu
 * http://blog.csdn.net/lwcfoxhunter2009/article/details/23627293       
 * http://www.cnblogs.com/chenying99/p/3735282.html 
 */
public class HttpConnectionManager implements InitializingBean {
	
	/**
	 * 最大并发数
	 */
	private int maxConcurrency;

	/**
	 * 最大路由数
	 */
	private int maxRoute;
	
	
	/**
	 * 连接池
	 */
	private PoolingHttpClientConnectionManager connManager;


	public CloseableHttpClient getHttpClient() {
		if(connManager == null){
			initHttpClientPooling();
		}

		// 初始化httpClient
		CloseableHttpClient httpClient = HttpClients.custom()
						.setConnectionManager(connManager)
						.build();
		return httpClient;
	}
	
	
	/**
	 * 初始化线程池
	 * @return
	 */
	private void initHttpClientPooling(){
		if(connManager == null){
			connManager = new PoolingHttpClientConnectionManager();
			
			// 设置连接池最大并发连接
			connManager.setMaxTotal(maxConcurrency);

			// 设置单个路由最大连接，覆盖默认值2
			connManager.setDefaultMaxPerRoute(maxRoute);
			
		}
	}

	/**
	 * @return the maxConcurrency
	 */
	public int getMaxConcurrency() {
		return maxConcurrency;
	}

	/**
	 * @param maxConcurrency the maxConcurrency to set
	 */
	public void setMaxConcurrency(int maxConcurrency) {
		this.maxConcurrency = maxConcurrency;
	}

	/**
	 * @return the maxRoute
	 */
	public int getMaxRoute() {
		return maxRoute;
	}

	/**
	 * @param maxRoute the maxRoute to set
	 */
	public void setMaxRoute(int maxRoute) {
		this.maxRoute = maxRoute;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		initHttpClientPooling();
	}
}
