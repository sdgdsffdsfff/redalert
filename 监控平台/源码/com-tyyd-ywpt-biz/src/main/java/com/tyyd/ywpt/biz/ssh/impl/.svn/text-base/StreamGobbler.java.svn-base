/**   
 * @Title: StreamGobbler.java 
 * @Package com.tyyd.ywpt.biz.ssh.impl 
 * @Description:  
 * @author wangyu   
 * @date 2014-7-30 上午10:14:58 
 * @CopyRight 天翼阅读
 * @version V1.0   
 */
package com.tyyd.ywpt.biz.ssh.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

/**
 * @author wangyu
 * 
 */
public class StreamGobbler extends Thread{

	public static final Logger LOGGER = Logger.getLogger(StreamGobbler.class);
	private InputStream is;
	private String type;
	private StringBuffer stringBuffer = null;
	private String shellCmd;
	
	/**
	 * @return the stringBuffer
	 */
	public StringBuffer getStringBuffer() {
		return stringBuffer;
	}

	/**
	 * @param stringBuffer the stringBuffer to set
	 */
	public void setStringBuffer(StringBuffer stringBuffer) {
		this.stringBuffer = stringBuffer;
	}


	/**
	 * @param is
	 * @param type
	 * @param charset
	 */
	StreamGobbler(InputStream is, String type ,String shellCmd) {
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

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		doWork();
	}

}
