/**   
* @Title: KeepAlivedSshImpl.java 
* @Package com.tyyd.ywpt.biz.ssh.invoke.keepalived 
* @Description:  
* @author wangyu   
* @date 2014-11-20 下午3:10:05 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.ssh.invoke.keepalived.impl;

import com.tyyd.ywpt.biz.ssh.impl.AbstractSSHConnectManager;
import com.tyyd.ywpt.biz.ssh.invoke.keepalived.KeepAlivedSsh;

/**
 * @author wangyu
 *
 */
public class KeepAlivedSshImpl extends AbstractSSHConnectManager implements KeepAlivedSsh {

	@Override
	public void checkKeepAlivedStatus(String dbId) {
		localCmdExec("python /home/mysql/admin/bin/newbin/get_keepalived_status.py --dbid=\""+dbId+"\"");
	}

	@Override
	public void checkKeepAlivedLog(String dbId, String logPath, String keyWord) {
		localCmdExec("python /home/mysql/admin/bin/newbin/get_keepalived_log.py --dbid=\""+dbId+"\" --alertfile=\""+logPath+"\" --alertkey=\""+keyWord+"\"");
	}

}
