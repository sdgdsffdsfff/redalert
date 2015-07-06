/**   
* @Title: MySQLSsh.java 
* @Package com.tyyd.ywpt.biz.ssh.invoke.mysql 
* @Description:  
* @author wangyu   
* @date 2014-7-7 下午4:21:34 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.ssh.invoke.mysql;

/**
 * @author wangyu
 *
 */
public interface MySQLSsh {

	/**
	 * 获取MySQL的性能信息
	 * @param dataBaseId
	 */
	public void sshCollectMySQLStat(String dataBaseId);
	
	
	/**
	 * slow sql采集
	 * @param dataBaseId
	 */
	public void sshCollectMySQLSlowSQL(String ipAddr,String dataBaseId);
	
	
	/**
	 * oracle 告警日志
	 * @param dataBaseId
	 * @param monitorPath
	 * @param keyWord
	 */
	public void sshCollectMySQLAlert(String dataBaseId,String monitorPath,String keyWord);
	
	
	/**
	 * MySQL 表空间
	 * @param dbId
	 */
	public void sshCollectMySQLTableSpace(String dbId);
	
	
	/**
	 * SLAVE的状态
	 * @param dataBaseId
	 */
	public void sshCollectMySQLSlaveStat(String dataBaseId);
	
	
	/**
	 * 长时间的SQL
	 * @param dataBaseId
	 */
	public void sshCollectMySQLLongSQL(String dataBaseId);
	
	
	/**
	 * 超过100行的数据SQL
	 * @param dataBaseId
	 */
	public void sshCollectMySQLSQLResult(String dataBaseId);
	
	
	/**
	 * 刷新MYSQL主备关系
	 * @param dataBaseId
	 */
	public void sshRefreshMasterSlaveRelation(String dataBaseId);
}
