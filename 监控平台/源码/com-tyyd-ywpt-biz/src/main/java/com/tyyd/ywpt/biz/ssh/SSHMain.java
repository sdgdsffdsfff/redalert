/**   
* @Title: SSHMain.java 
* @Package com.tyyd.ywpt.biz.ssh 
* @Description:  
* @author wangyu   
* @date 2014-6-26 下午2:26:33 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.ssh;

import com.tyyd.ywpt.biz.ssh.dataobject.SSHResultBO;
import com.tyyd.ywpt.biz.ssh.impl.AbstractSSHConnectManager;

/**
 * @author wangyu
 *
 */
public class SSHMain extends AbstractSSHConnectManager{

	public static void main(String[] args) {
		SSHMain main = new SSHMain();
		main.setConnectIpAddr("192.168.11.27");
		main.setConnectPort(22);
		main.setConnectUserName("monitor");
		main.setConnectPassword("monitor");
		
		//SSHResultBO bo = main.longConnectionWithCmd("python /home/mysql/admin/bin/newbin/get_mysql_info.py 11");
		SSHResultBO bo = main.longConnectionWithCmd("echo '123'");
		System.out.print(bo.getResult());
		
	}

}
