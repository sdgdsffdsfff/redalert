/**   
 * @Title: HttpServiceImpl.java 
 * @Package com.tyyd.ywpt.tools.http.impl 
 * @Description:  
 * @author wangyu   
 * @date 2014-8-8 下午4:38:49 
 * @CopyRight 天翼阅读
 * @version V1.0   
 */
package com.tyyd.ywpt.tools.http.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.tyyd.ywpt.tools.bean.SpringContextHolder;
import com.tyyd.ywpt.tools.http.HttpService;

/**
 * @author wangyu
 * 
 */
public class HttpServiceImpl implements HttpService {

	public static final Logger LOGGER = Logger.getLogger(HttpServiceImpl.class);
	
	private static final String encoding = "UTF-8";

	@Resource
	private HttpConnectionManager httpConnectionManager;
	
	
	@Resource
	private HttpRequestConfigUtils httpRequestConfigUtils;

	@Override
	public String getRequest(String url) {
		String result = "";
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse resp = null;

		try {
			
			HttpGet httpGet = new HttpGet(url);
			httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63");
			httpGet.setHeader("User-Agent", "monitor");
			httpGet.setHeader("Connection", "Keep-Alive");
			
			httpGet.setConfig(getRequestConfig());

			// 请求返回值句柄
			//httpClient = HttpClients.createDefault();
			httpClient = getHttpClient();
			resp = httpClient.execute(httpGet);

			if (HttpStatus.SC_OK == resp.getStatusLine().getStatusCode()) {
				// 获取返回对象
				HttpEntity entity = resp.getEntity();
				if (entity != null) {
					// 获取返回对象的内容流
					// 读取内容流进行处理
					result = EntityUtils.toString(entity, encoding);
					EntityUtils.consume(resp.getEntity());
					return result;
				}
			}

		} catch (ClientProtocolException ce) {
			LOGGER.error("HttpGet请求失败", ce);
		} catch (IOException ioe) {
			LOGGER.error("HttpGet请求失败", ioe);
		} finally {
			try {
//				if (null != httpClient) {
//					httpClient.close();
//				}
				if (null != resp) {
					resp.close();
				}
			} catch (IOException e) {
				LOGGER.error("HttpGet请求关闭异常", e);
			}
		}
		
		return result;
	}

	@Override
	public String postRequest(String url, Map<String,String> params) {
		String result = "";
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse resp = null;

		try {
			// 设置协议头相关信息
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(getRequestConfig());
			
			//设置请求的参数
			if(!CollectionUtils.isEmpty(params)){
				List<NameValuePair> formparams = new ArrayList<NameValuePair>();
				Iterator<String> keys = params.keySet().iterator();
				while(keys.hasNext()){
					String key = keys.next();
					String value = params.get(key);
					formparams.add(new BasicNameValuePair(key, value));
				}
				LOGGER.info("请求URL:"+url+"请求参数:"+formparams.toString());
				UrlEncodedFormEntity requestEntity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);  
				httpPost.setEntity(requestEntity);
			}
			
			

			// 请求返回值句柄
			httpClient = HttpClients.createDefault();
			resp = httpClient.execute(httpPost);

			if (HttpStatus.SC_OK == resp.getStatusLine().getStatusCode()) {
				// 获取返回对象
				HttpEntity entity = resp.getEntity();
				if (entity != null) {
					// 获取返回对象的内容流
					// 读取内容流进行处理
					result = EntityUtils.toString(entity, encoding);
					EntityUtils.consume(resp.getEntity());
					LOGGER.info("返回:"+result);
					return result;
				}
			}

		} catch (ClientProtocolException ce) {
			ce.printStackTrace();
			LOGGER.error("HttpGet请求失败", ce);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			LOGGER.error("HttpGet请求失败", ioe);
		} finally {
			try {
				if (null != httpClient) {
					httpClient.close();
				}
				if (null != resp) {
					resp.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				LOGGER.error("HttpGet请求关闭异常", e);
			}
		}
		
		return result;
	}

	private CloseableHttpClient getHttpClient() {
		return httpConnectionManager.getHttpClient();
	}

	private RequestConfig getRequestConfig() {
		return httpRequestConfigUtils.getRequestConfig();
	}
}
