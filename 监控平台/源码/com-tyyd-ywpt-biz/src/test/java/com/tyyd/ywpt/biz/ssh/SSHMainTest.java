/**   
* @Title: SSHMainTest.java 
* @Package com.tyyd.ywpt.biz.ssh 
* @Description:  
* @author wangyu   
* @date 2014-6-30 上午10:20:57 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.ssh;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.tyyd.ywpt.biz.ssh.dataobject.SSHResultBO;
import com.tyyd.ywpt.biz.ssh.impl.AbstractLinuxLocalInvokeManager;

/**
 * @author wangyu
 *
 */


public class SSHMainTest extends AbstractLinuxLocalInvokeManager{

	@Test
	public void testLocalShellInvoke(){
		String shell1 = "python /home/mysql/admin/bin/newbin/get_alert_info.py --dbid=\"60\" --alertkey=\"ORA-|error|ERROR|fail\"";
		String shell2 = "python /home/mysql/admin/bin/newbin/get_alert_info.py --dbid=\"61\" --alertkey=\"ORA-|error|ERROR|fail\"";
		String shell3 = "python /home/mysql/admin/bin/newbin/get_alert_info.py --dbid=\"62\" --alertkey=\"ORA-|error|ERROR|fail\"";
		String shell4 = "python /home/mysql/admin/bin/newbin/get_alert_info.py --dbid=\"63\" --alertkey=\"ORA-|error|ERROR|fail\"";
		
		SSHResultBO result1 = this.localCmdExec(shell1);
		SSHResultBO result2 = this.localCmdExec(shell2);
		SSHResultBO result3 = this.localCmdExec(shell3);
		SSHResultBO result4 = this.localCmdExec(shell4);
		
		System.out.println(result1.getResult());
		System.out.println(result2.getResult());
		System.out.println(result3.getResult());
		System.out.println(result4.getResult());
		
	}
	
}
