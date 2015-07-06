/**   
* @Title: SSHCmdUtils.java 
* @Package com.tyyd.ywpt.biz.ssh.dataobject 
* @Description:  
* @author wangyu   
* @date 2014-6-25 下午3:07:54 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.ssh.dataobject;

/**
 * @author wangyu
 *
 */
public class SSHCmdEnum {

	// 查询操作系统是linux还是AIX
	public final static String OS_VERSION_CMD = "uname -a |awk '{print $1}'";

	//CPU规定返回长度为5项
	public final static int CPU_INFO_RESULT_LENGTH = 5;
	
	//获取内存信息,AIX版
	public final static String AIX_MEMORY_CMD = "free -m |grep 'Mem'|awk '{print $2,$3,$4,$6}'";
	
	//AIX
	public final static String AIX = "AIX";
	
	//Linux
	public final static String Linux = "Linux";
	
	//换行
	public static final String CTLF = "\n";
	
	//空格分割
	public static final String SPACE = " ";
}
