/**   
 * @Title: HttpRequestConfigUtils.java 
 * @Package com.tyyd.ywpt.tools.http.impl 
 * @Description:  
 * @author wangyu   
 * @date 2014-8-8 下午5:33:31 
 * @CopyRight 天翼阅读
 * @version V1.0   
 */
package com.tyyd.ywpt.tools.http.impl;

import java.util.Arrays;

import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author wangyu
 * 
 */
public class HttpRequestConfigUtils implements InitializingBean {

	/**
	 * 连接超时时间，单位毫秒
	 */
	private int connectionTimeOut;

	/**
	 * 传输超时,单位毫秒
	 */
	private int socketTimeOut;

	/**
	 * 请求的配置
	 */
	private RequestConfig requestConfig;

	/**
	 * 设置连接超时
	 * 
	 * @return
	 */
	public RequestConfig getRequestConfig() {
		if (requestConfig != null) {
			return requestConfig;
		}
		return initRequestConfig();
	}

	/**
	 * 初始化Http请求的超时时间
	 * @return
	 */
	private RequestConfig initRequestConfig() {
		// 设置全局请求参数
		RequestConfig defaultRequestConfig = RequestConfig
				.custom()
				.setCookieSpec(CookieSpecs.BEST_MATCH)
				.setExpectContinueEnabled(true)
				.setStaleConnectionCheckEnabled(true)
				.setTargetPreferredAuthSchemes(
						Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
				.setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
				.build();

		requestConfig = RequestConfig.copy(defaultRequestConfig)
				.setConnectTimeout(connectionTimeOut)
				.setSocketTimeout(socketTimeOut).build();
		
		return requestConfig;
	}

	/**
	 * @return the connectionTimeOut
	 */
	public int getConnectionTimeOut() {
		return connectionTimeOut;
	}

	/**
	 * @param connectionTimeOut
	 *            the connectionTimeOut to set
	 */
	public void setConnectionTimeOut(int connectionTimeOut) {
		this.connectionTimeOut = connectionTimeOut;
	}

	/**
	 * @return the socketTimeOut
	 */
	public int getSocketTimeOut() {
		return socketTimeOut;
	}

	/**
	 * @param socketTimeOut
	 *            the socketTimeOut to set
	 */
	public void setSocketTimeOut(int socketTimeOut) {
		this.socketTimeOut = socketTimeOut;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		initRequestConfig();
	}

}
