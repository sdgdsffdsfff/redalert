/**   
* @Title: OracleSshImpl.java 
* @Package com.tyyd.ywpt.biz.ssh.invoke.oracle.impl 
* @Description:  
* @author wangyu   
* @date 2014-7-7 下午4:28:04 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.ssh.invoke.oracle.impl;

import org.apache.log4j.Logger;

import com.tyyd.ywpt.biz.ssh.impl.AbstractSSHConnectManager;
import com.tyyd.ywpt.biz.ssh.invoke.oracle.OracleSsh;

/**
 * @author wangyu
 *
 */
public class OracleSshImpl extends AbstractSSHConnectManager implements OracleSsh{

	public static final Logger LOGGER = Logger.getLogger(OracleSshImpl.class);  
	
	@Override
	public void sshCollectOracleAWRInfo(String dataBaseId) {
		localCmdExec("python /home/mysql/admin/bin/newbin/get_ora_info.py --dbid=\""+dataBaseId+"\" --host_type=\"awr\"");
	}

	
	@Override
	public void sshCollectOracleSQLInfo(String dataBaseId) {
		localCmdExec("python /home/mysql/admin/bin/newbin/get_ora_info.py --dbid=\""+dataBaseId+"\" --host_type=\"sqltext\"");
	}

	
	@Override
	public void sshCollectOracleStatInfo(String dataBaseId) {
		localCmdExec("python /home/mysql/admin/bin/newbin/get_ora_info.py --dbid=\""+dataBaseId+"\" --host_type=\"stat\"");
	}


	@Override
	public void sshCollectOracleAlert(String dataBaseId, String monitorPath,
			String keyWord) {
		localCmdExec("python /home/mysql/admin/bin/newbin/get_alert_info.py --dbid=\""+dataBaseId+"\" --alertfile=\""+monitorPath+"\" --alertkey=\""+keyWord+"\"");
	}


	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.biz.ssh.invoke.oracle.OracleSsh#sshTableSpace(java.lang.String)
	 */
	@Override
	public void sshCollectOracleTableSpace(String dbId) {
		localCmdExec("python /home/mysql/admin/bin/newbin/get_tablespace.py --dbid=\""+dbId+"\"");
	}


	@Override
	public void sshCheckLongSQL(String dbId) {
		localCmdExec("python /home/mysql/admin/bin/newbin/get_ora_info.py --dbid=\""+dbId+"\" --host_type=\"longsql\"");
	}


	@Override
	public void sshCheckTrans(String dbId) {
		localCmdExec("python /home/mysql/admin/bin/newbin/get_ora_info.py --dbid=\""+dbId+"\" --host_type=\"trans\"");
	}

	@Override
	public void sshCheckIndexLevel(String dbId) {
		localCmdExec("python /home/mysql/admin/bin/newbin/get_ora_info.py --dbid=\""+dbId+"\" --host_type=\"level\"");
	}
}
