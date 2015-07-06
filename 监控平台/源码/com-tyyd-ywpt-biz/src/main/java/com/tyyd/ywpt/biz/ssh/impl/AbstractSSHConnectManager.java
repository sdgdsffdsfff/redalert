/**   
 * @Title: SSHConnectManager.java 
 * @Package com.tyyd.ywpt.biz.ssh 
 * @Description:  
 * @author wangyu   
 * @date 2014-6-25 下午1:59:47 
 * @CopyRight 天翼阅读
 * @version V1.0   
 */
package com.tyyd.ywpt.biz.ssh.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.tyyd.ywpt.biz.ssh.dataobject.SSHCmdEnum;
import com.tyyd.ywpt.biz.ssh.dataobject.SSHResultBO;

/**
 * @author wangyu
 * 
 */
public abstract class AbstractSSHConnectManager {

	public static final Logger LOGGER = Logger.getLogger(AbstractSSHConnectManager.class);  
	private static final Integer SSH_CONNECT_TIMEOUT = 15 * 1000;
	private static final String FIRST_CONNECT_CONFIRM_KEY = "StrictHostKeyChecking";
	private static final String FIRST_CONNECT_CONFIRM_PARAMETER = "no";
	private static final Integer MAX_RECONNECT_TIMES = 5;
	private static final long SLEEP_SECOND = 1000l;

	/**
	 * ip地址
	 */
	private String connectIpAddr;	
	
	/**
	 * 连接端口,默认22端口
	 */
	private Integer connectPort = 22;
	
	/**
	 * 用户名
	 */
	private String connectUserName;
	
	/**
	 * 密码
	 */
	private String connectPassword;
	

	
	
	// private String sftpHost = "localhost";
	// private int sftpPort = 22;
	// private String sftpUserName = "petric";
	// private String sftpPassword = "123";
	// private int timeout = 30000;

	
	/**
	 * 一次性创建session
	 * @return
	 */
	private Session createSessionOnce(){
		Session sshSession = null;
		JSch jSch = new JSch();
		try{
			//为了解决Connection closed by foreign host的问题，每次连接后，都关闭
			//连接的时候，重新创建连接，开销会比较大
			// http://www.pooy.net/connection-closed-foreign-host.html
			//创建一个连接session
			if (sshSession == null || !sshSession.isConnected()) {
				sshSession = jSch.getSession(connectUserName, connectIpAddr, connectPort);
				sshSession.setPassword(connectPassword);
				Properties config = new Properties();
				//设置第一次登陆的时候提示，可选值：(ask | yes | no)
				config.put(FIRST_CONNECT_CONFIRM_KEY, FIRST_CONNECT_CONFIRM_PARAMETER);
				sshSession.setConfig(config);
				//设置登陆超时时间 
				sshSession.setTimeout(SSH_CONNECT_TIMEOUT);
				sshSession.connect();
			}
		} catch (Exception e) {
			if (sshSession != null) {
				sshSession.disconnect();
				sshSession = null;
			}
		}
		return sshSession;
	}
	
	/**
	 * 创建一个Session
	 * @return
	 */
	private Session createSession(){
		Session sshSession = null;
		//一个线程，最多创建三次连接
		
		Integer start = ReConnectThreadLocal.getReconnTimes();
		
		try{
			
			sshSession = createSessionOnce();
			if(sshSession == null || !sshSession.isConnected()){
				throw new NullPointerException("创建Session失败，触发重连机制,第1次失败");
			}
			LOGGER.info(getConnectInfo()+"第1次创建连接成功");
			return sshSession;
		} catch (Exception e) {
			if (sshSession != null) {
				sshSession.disconnect();
				sshSession = null;
			}
			
			LOGGER.error(getConnectInfo()+"发起第"+(start+1)+"次重连,首次创建session异常为：",e);
			//重连
			while(start < MAX_RECONNECT_TIMES){
				try {
					Thread.sleep(SLEEP_SECOND);
				} catch (InterruptedException ie) {
					LOGGER.error(getConnectInfo()+"主线程等待1秒，发生异常：",ie);
				}
				LOGGER.error(getConnectInfo()+"发起第"+(start+1)+"次重连");
				ReConnectThreadLocal.setReconnTimes();
				start = ReConnectThreadLocal.getReconnTimes();
				try{
					sshSession = createSessionOnce();
					if(sshSession == null || !sshSession.isConnected()){
						throw new NullPointerException("创建Session失败，触发重连机制,第"+(start+1)+"次失败");
					}
					return sshSession;
				}catch(Exception e1){
					LOGGER.error(getConnectInfo()+"发起第"+(start+1)+"次重连,异常为:",e1);
				}
			}
			
			
			if(sshSession == null || !sshSession.isConnected()){
				LOGGER.error(getConnectInfo()+"三次重连失败，放弃本次任务",e);
				ReConnectThreadLocal.clear();
			}
			
		}
		return sshSession;
	}
	
	
	/**
	 * 获取连接
	 * 
	 * @return
	 */
//	private void getConnection() {
//		
//		Session sshSession = null;
//		Channel sshChannel = null;
//		
//		try {
//			//为了解决Connection closed by foreign host的问题，每次连接后，都关闭
//			//连接的时候，重新创建连接，开销会比较大
//			// http://www.pooy.net/connection-closed-foreign-host.html
//			sshSession = createSession();
//			
//			//获取通道，也用重连机制
//			sshChannel = sshSession.openChannel("exec");
//			
//			//设置线程安全
//			SSHThreadLocal.setSession(sshSession, sshChannel);
//			
//			LOGGER.info(getConnectInfo()+"打开新建的通道");
//		} catch (Exception e) {
//			if (sshSession != null) {
//				sshSession.disconnect();
//				sshSession = null;
//			}
//			sshChannel = null;
//			LOGGER.error(getConnectInfo()+"创建失败",e);
//			
//		}
//		//return sshChannel == null ? null : (ChannelExec) sshChannel;
//	}

	/**
	 * @return the connectIpAddr
	 */
	public String getConnectIpAddr() {
		return connectIpAddr;
	}

	/**
	 * @param connectIpAddr the connectIpAddr to set
	 */
	public void setConnectIpAddr(String connectIpAddr) {
		this.connectIpAddr = connectIpAddr;
	}

	/**
	 * @return the connectPort
	 */
	public Integer getConnectPort() {
		return connectPort;
	}

	/**
	 * @param connectPort the connectPort to set
	 */
	public void setConnectPort(Integer connectPort) {
		this.connectPort = connectPort;
	}

	/**
	 * @return the connectUserName
	 */
	public String getConnectUserName() {
		return connectUserName;
	}

	/**
	 * @param connectUserName the connectUserName to set
	 */
	public void setConnectUserName(String connectUserName) {
		this.connectUserName = connectUserName;
	}

	/**
	 * @return the connectPassword
	 */
	public String getConnectPassword() {
		return connectPassword;
	}

	/**
	 * @param connectPassword the connectPassword to set
	 */
	public void setConnectPassword(String connectPassword) {
		this.connectPassword = connectPassword;
	}

	/**
	 * 关闭连接
	 */
	private void closeChannel() {
		try {
			//从线程中拿到session和channel
			Session session = SSHThreadLocal.getSession();
			Channel ch = SSHThreadLocal.getChannel();
			if (ch != null ) {
				ch.disconnect();
				ch = null;
				LOGGER.info(getConnectInfo()+"关闭ssh通道");
			}
			if (session != null ) {
				session.disconnect();
				session = null;
				LOGGER.info(getConnectInfo()+"关闭ssh Session");
			}
			
			//销毁线程
			SSHThreadLocal.remove();
			
		} catch (Exception e) {
			LOGGER.error(getConnectInfo()+"关闭异常", e);
		}
	}

	
	
	private ChannelExec getChannel(String command){
		Session session = SSHThreadLocal.getSession();
		Channel ch = SSHThreadLocal.getChannel();
		try{
			
			if(ch == null){
				//重新打开一个通道
				if(session == null || !session.isConnected()){
					session = createSession();
					LOGGER.info("重新创建一个连接");
					if(session == null){
						throw new NullPointerException("session 重建创建，但是获取还是为null,放弃本次任务 ,CMD = "+command);
					}
				}
				ch = session.openChannel("exec");
			}
			
			//重新设置session和channel的线程
			SSHThreadLocal.setSession(session, ch);
			
		}catch(Exception e){
			LOGGER.error("获取通道失败,放弃本次任务", e);
		}
		return (ChannelExec) ch;
	}
	
	/**
	 * 执行服务器端命令
	 */
	public SSHResultBO longConnectionWithCmd(String command) {
		SSHResultBO result = new SSHResultBO();
		ChannelExec channelExec = getChannel(command);
		if (channelExec == null) {
			LOGGER.error(getConnectInfo()+"获取连接失败");
			result.setSucess(false);
			result.setResult("获取连接失败");
			return result;
		}
		LOGGER.info(getConnectInfo()+"执行ssh命令,cmd="+command);
		InputStream in = null;
		try {
			
			channelExec.setInputStream(null);
			channelExec.setErrStream(System.err);
			channelExec.setCommand(command);
			
			in = channelExec.getInputStream();
			
			channelExec.connect();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					in, "utf-8"));
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
				sb.append(SSHCmdEnum.CTLF);
			}
			String newStr = StringUtils.removeEnd(sb.toString(), SSHCmdEnum.CTLF);
			LOGGER.debug(getConnectInfo()+"执行结果:"+newStr);
			
			result.setResult(newStr);
			result.setSucess(true);
			reader.close();
			
		} catch (Exception e) {
			LOGGER.error(getConnectInfo()+"执行"+command+"失败,放弃本次任务,原因:", e);
			result.setResult(e.getMessage());
			result.setSucess(false);
			return result;
		}finally{
			closeChannel();
		}
		return result;
	}

	
	/**
	 * SSH 心跳监测
	 * @return
	 */
	public boolean heartBeatMonitor(){
		boolean isActive = false;
		SSHResultBO result = longConnectionWithCmd("echo '123' ");
		if(result == null || !result.isSucess()){
			return isActive;
		}
		
		if(StringUtils.isNotBlank(result.getResult()) && "123".equals(result.getResult())){
			isActive = true;
			return isActive;
		}
		
		return isActive;
	}
	
	
	private String getConnectInfo(){
		StringBuffer connectInfo = new StringBuffer("");
		connectInfo.append("connect[");
		connectInfo.append("ip:");
		connectInfo.append(connectIpAddr);
		connectInfo.append(",connectPort:");
		connectInfo.append(connectPort);
		connectInfo.append(",username=");
		connectInfo.append(connectUserName);
		connectInfo.append("],body=");
		return connectInfo.toString();
	}

	
	
	/**
	 * 通常非视窗的操作系统，如linux作为PC Server
	 * @return
	 */
	public boolean isLinux(){
		boolean isLinux = true;   
		String osName = System.getProperty("os.name");
		
		if(StringUtils.isBlank(osName)){
			LOGGER.error("获取操作系统失败");
			return false;
		}
		
		//如果找到是windows，那么全部当作linux来处理，因为PC server通常是centos
        if(osName.toUpperCase().indexOf(SystemOs.Windows.getOsName()) != -1){
            isLinux = false;
        }
  
        return isLinux;
	}
	
	
	/**
	 * Java 执行本地的Shell 
	 * 参考 http://blog.csdn.net/zishan007/article/details/19905363
	 * http://search.iteye.com/blog/253257
	 * @param shellCmd
	 * @return
	 */
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
			StreamGobbler errorGobbler = new StreamGobbler(process.getErrorStream(), "Error", shellCmd);    
			
			//错误流不返回信息，直接打log
			errorGobbler.start();
            
            //定义一个信息流的处理线程
			StreamGobbler outputGobbler = new StreamGobbler(process.getInputStream(), "Output", shellCmd);
            
            //信息流
			outputGobbler.start();
			
			int exitVal = process.waitFor();  
			
			if(exitVal != 0){
				LOGGER.error("执行本地调用出错，错误码是:"+exitVal+",CMD="+shellCmd);
			}
			//处理返回的信息
//			String resultStr = future.get();
//			LOGGER.debug("返回内容："+resultStr);
			result.setSucess(true);
			result.setResult("");
			return result;
		} catch (IOException e) {
			LOGGER.error(shellCmd +" 调用本地命令异常",e);
		} catch (InterruptedException e) {
			LOGGER.error(shellCmd +" Process.waitFor异常",e);
		} 
		
		return result;
	}
	
	
	
	enum SystemOs{
		
		Windows(1,"WINDOWS"),
		Linux(2,"LINUX");
		
		private Integer osId;
		private String osName;
		/**
		 * @return the osId
		 */
		public Integer getOsId() {
			return osId;
		}
		/**
		 * @param osId the osId to set
		 */
		public void setOsId(Integer osId) {
			this.osId = osId;
		}
		/**
		 * @return the osName
		 */
		public String getOsName() {
			return osName;
		}
		/**
		 * @param osName the osName to set
		 */
		public void setOsName(String osName) {
			this.osName = osName;
		}
		/**
		 * @param osId
		 * @param osName
		 */
		private SystemOs(Integer osId, String osName) {
			this.osId = osId;
			this.osName = osName;
		}
		
		
		
	}
	
	
	
}

class ReConnectThreadLocal{
	private static final ThreadLocal<Integer> reconn_times = new ThreadLocal<Integer>(){
		@Override
		protected Integer initialValue() {
			return 0;
		}
	}; 
	
	public static void setReconnTimes(){
		Integer cur = reconn_times.get();
		if(cur == null){
			cur = 0;
		}
		reconn_times.set(cur+1);
	}
	
	public static Integer getReconnTimes(){
		Integer cur = reconn_times.get();
		if(cur == null){
			cur = 0;
		}
		return cur;
	}
	
	public static void clear(){
		reconn_times.set(0);
	}
	
}


class SSHThreadLocal {
	private static final ThreadLocal<Session> session_local = new ThreadLocal<Session>(); 
	private static final ThreadLocal<Channel> channel_local = new ThreadLocal<Channel>(); 
	
	/**
	 * 设置Session和通道 
	 * @param value
	 */
	public static void setSession(Session sessionValue,Channel channelValue){
		session_local.set(sessionValue);
		channel_local.set(channelValue);
	}
	
	/**
	 * 获取session
	 * @return
	 */
	public static Session getSession(){
		return session_local.get();
	}
	
	
	/**
	 * 获取通道
	 * @return
	 */
	public static Channel getChannel(){
		return channel_local.get();
	}
	
	
	public static void remove(){
		session_local.remove();
		channel_local.remove();
	}
}
