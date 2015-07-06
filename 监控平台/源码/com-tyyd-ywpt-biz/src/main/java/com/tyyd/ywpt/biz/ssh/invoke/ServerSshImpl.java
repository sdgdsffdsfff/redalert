/**   
* @Title: ServerSshImpl.java 
* @Package com.tyyd.ywpt.biz.ssh.invoke 
* @Description:  
* @author wangyu   
* @date 2014-7-7 上午11:35:36 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.ssh.invoke;

import org.apache.log4j.Logger;

import com.tyyd.ywpt.biz.ssh.impl.AbstractSSHConnectManager;

/**
 * @author wangyu
 * 如果本系统是win系统，那么还是采用ssh的调用方式，否则采用本地调用
 */
public class ServerSshImpl extends AbstractSSHConnectManager implements ServerSsh {

	public static final Logger LOGGER = Logger.getLogger(ServerSshImpl.class);  
	
	@Override
	public void sshCollectServerCpuInfo(String ipAddr) {
//		if(!isLinux()){
//			LOGGER.info("采用ssh的执行方式");
//			longConnectionWithCmd("python /home/mysql/admin/bin/newbin/get_host_info.py "+ipAddr+" cpu");
//		}else{
//			LOGGER.info("采用Rumtime的执行方式");
//			localCmdExec("python /home/mysql/admin/bin/newbin/get_host_info.py "+ipAddr+" cpu");
//		}
		localCmdExec("python /home/mysql/admin/bin/newbin/get_host_info.py --host_ip=\""+ipAddr+"\" --host_type=\"cpu\"");
	}

	@Override
	public void sshCollectServerMemoryInfo(String ipAddr) {
		localCmdExec("python /home/mysql/admin/bin/newbin/get_host_info.py --host_ip=\""+ipAddr+"\" --host_type=\"mem\"");
	}

	
	@Override
	public void sshCollectServerNetWorkInfo(String ipAddr) {
		localCmdExec("python /home/mysql/admin/bin/newbin/get_host_info.py --host_ip=\""+ipAddr+"\" --host_type=\"net\"");
	}

	
	@Override
	public void sshCollectServerDiskInfo(String ipAddr) {
		localCmdExec("python /home/mysql/admin/bin/newbin/get_host_info.py --host_ip=\""+ipAddr+"\" --host_type=\"disk\"");
	}

	@Override
	public void sshClearDeadJob() {
		String cmd = " ps -eo pid,cmd,lstart,etime|grep python|grep -v grep|awk '{print $1,$NF}'|awk -F - '{print $1}'|awk -F : '{print $1}'|awk '{if ($2 >=1) print $1}'|xargs kill -9";
		longConnectionWithCmd(cmd);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.biz.ssh.invoke.ServerSsh#sshCollectSsdInfo(java.lang.String)
	 */
	@Override
	public void sshCollectSsdInfo(String ipAddr) {
		localCmdExec("python /home/mysql/admin/bin/newbin/get_host_info.py --host_ip=\""+ipAddr+"\" --host_type=\"ssd\"");
	}


}
