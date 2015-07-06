/**   
* @Title: MySQLSshImpl.java 
* @Package com.tyyd.ywpt.biz.ssh.invoke.mysql.impl 
* @Description:  
* @author wangyu   
* @date 2014-7-7 下午4:22:22 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.ssh.invoke.mysql.impl;

import org.apache.log4j.Logger;

import com.tyyd.ywpt.biz.ssh.impl.AbstractSSHConnectManager;
import com.tyyd.ywpt.biz.ssh.invoke.mysql.MySQLSsh;

/**
 * @author wangyu
 *
 */
public class MySQLSshImpl extends AbstractSSHConnectManager implements MySQLSsh {

	public static final Logger LOGGER = Logger.getLogger(MySQLSshImpl.class);  
	
	@Override
	public void sshCollectMySQLStat(String dataBaseId) {
		localCmdExec("python /home/mysql/admin/bin/newbin/get_mysql_info.py --dbid=\""+dataBaseId+"\" --host_type=\"mysqlstat\" ");
	}

	@Override
	public void sshCollectMySQLSlowSQL(String ipAddr,String dataBaseId) {
		localCmdExec("python /home/mysql/admin/bin/newbin/get_slowlog.py --host_ip=\""+ipAddr+"\" --dbid=\""+dataBaseId+"\"");
	}

	@Override
	public void sshCollectMySQLAlert(String dataBaseId, String monitorPath,
			String keyWord) {
		localCmdExec("python /home/mysql/admin/bin/newbin/get_alert_info.py --dbid=\""+dataBaseId+"\" --alertfile=\""+monitorPath+"\" --alertkey=\""+keyWord+"\"");
	}

	@Override
	public void sshCollectMySQLTableSpace(String dbId) {
		localCmdExec("python /home/mysql/admin/bin/newbin/get_tablespace.py --dbid=\""+dbId+"\"");
	}

	@Override
	public void sshCollectMySQLSlaveStat(String dataBaseId) {
		localCmdExec("python /home/mysql/admin/bin/newbin/get_mysql_info.py --dbid=\""+dataBaseId+"\" --host_type=\"slavestat\" ");
	}

	@Override
	public void sshCollectMySQLLongSQL(String dataBaseId) {
		localCmdExec("python /home/mysql/admin/bin/newbin/get_mysql_info.py --dbid=\""+dataBaseId+"\" --host_type=\"longsql\" ");
	}

	@Override
	public void sshCollectMySQLSQLResult(String dataBaseId) {
		localCmdExec("python /home/mysql/admin/bin/newbin/get_mysql_info.py --dbid=\""+dataBaseId+"\" --host_type=\"sqlresult\" ");
	}

	@Override
	public void sshRefreshMasterSlaveRelation(String dataBaseId) {
		localCmdExec("python /home/mysql/admin/bin/newbin/get_mysql_replication.py  --dbid=\""+dataBaseId+"\" ");
	}

}
