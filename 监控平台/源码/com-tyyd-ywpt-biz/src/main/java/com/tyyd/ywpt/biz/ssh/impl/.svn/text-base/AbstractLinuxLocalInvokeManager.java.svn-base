/**   
* @Title: AbstractLinuxLocalInvokeManager.java 
* @Package com.tyyd.ywpt.biz.ssh.impl 
* @Description:  
* @author wangyu   
* @date 2015-2-6 下午1:37:07 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.ssh.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tyyd.ywpt.biz.ssh.dataobject.SSHResultBO;

/**
 * linux 本地调用
 * @author wangyu
 *
 */
public abstract class AbstractLinuxLocalInvokeManager {

	public static final Logger LOGGER = Logger.getLogger(AbstractLinuxLocalInvokeManager.class);
	
	
	public SSHResultBO localCmdExec(String shellCmd){
		SSHResultBO result = new SSHResultBO();
		result.setSucess(false);
		if(StringUtils.isBlank(shellCmd)){
			LOGGER.error("JAVA调用本地命令异常，CMD为空");
			return null;
		}
		
		LOGGER.info("执行本地调用，cmd="+shellCmd);
		
		String[] cmdarray = new String[]{"/bin/sh", "-c", shellCmd};
		try {
			Process process = Runtime.getRuntime().exec(cmdarray);
			
			//定义一个错误处理的线程
			Callable<String> errorCallable = new LocalCallBack(process.getErrorStream(), "Error", shellCmd);    
			
            //定义一个信息流的处理线程
			Callable<String> outputCallable = new LocalCallBack(process.getInputStream(), "Output", shellCmd);
            
			//创建线程池,两个线程
			ExecutorService pool = Executors.newFixedThreadPool(2);
			
			//提交任务
			Future<String> errorFutrue = pool.submit(errorCallable);
			Future<String> outputFutrue = pool.submit(outputCallable);
			
			int exitVal = process.waitFor();  
			
			result.setSucess(true);
			if(exitVal != 0){
				result.setSucess(false);
				LOGGER.error("执行本地调用出错，错误码是:"+exitVal+",CMD="+shellCmd);
			}
			
			//处理返回的信息
			try {
				result.setResult(outputFutrue.get() + errorFutrue.get());
				
				pool.shutdown();
			} catch (ExecutionException e) {
				LOGGER.error("获取本地调用的返回信息出错", e);
			}
			return result;
		} catch (IOException e) {
			LOGGER.error(shellCmd +" 调用本地命令异常",e);
		} catch (InterruptedException e) {
			LOGGER.error(shellCmd +" Process.waitFor异常",e);
		} 
		
		return result;
	}
	
	

class LocalCallBack implements Callable<String>{
	
	private InputStream is;
	private String type;
	private String shellCmd;
	
	/**
	 * @param is
	 * @param type
	 * @param charset
	 */
	LocalCallBack(InputStream is, String type ,String shellCmd) {
		super();
		this.is = is;
		this.type = type;
		this.shellCmd = shellCmd;
	}

	public void doWork(){
		BufferedReader br = null;
		try {
			InputStreamReader isr = new InputStreamReader(is,"utf-8");
			br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				CallBackThreadResult.setCallBackResult(line);
				if (type.equals("Error"))
					LOGGER.error("cmd="+shellCmd+","+line);
				else{
					//如果是正常的信息量，debug输出
					LOGGER.debug("cmd="+shellCmd+","+line);
				}
			}
		} catch (IOException ioe) {
			LOGGER.error("读取流异常",ioe);
		}finally{
			if(br != null){
                try  
                {  
                	br.close();  
                }catch (Exception e){  
                	LOGGER.error("关闭流异常",e);
                }  
            }
		}
		
	}
	
	
	@Override
	public String call() throws Exception {
		//先清理一次缓冲
		CallBackThreadResult.clear();
		
		//执行shell命令
		this.doWork();
		
		//获取返回值
		String result = CallBackThreadResult.getCallBackResult();
		
		//清理缓冲,返回
		CallBackThreadResult.clear();
		
		return result;
	}
	
}
	
}

class CallBackThreadResult {
	
	private static final ThreadLocal<String> callBackResult = new ThreadLocal<String>();

	/**
	 * @return the callBackResult
	 */
	public static String getCallBackResult() {
		String result = callBackResult.get();
		if(StringUtils.isBlank(result)){
			result = StringUtils.EMPTY;
		}
		return result;
	}

	
	public static void setCallBackResult(String result) {
		String oldResult = callBackResult.get();
		if(StringUtils.isBlank(oldResult)){
			oldResult = StringUtils.EMPTY;
		}
		if(StringUtils.isBlank(result)){
			result = StringUtils.EMPTY;
		}
		
		callBackResult.set(oldResult+result);
	}
	
	public static void clear(){
		callBackResult.remove();
	}
	
}
