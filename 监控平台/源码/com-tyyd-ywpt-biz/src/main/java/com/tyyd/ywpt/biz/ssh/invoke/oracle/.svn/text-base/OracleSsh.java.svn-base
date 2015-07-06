/**   
* @Title: OracleSsh.java 
* @Package com.tyyd.ywpt.biz.ssh.invoke.oracle 
* @Description:  
* @author wangyu   
* @date 2014-7-7 下午4:27:33 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.ssh.invoke.oracle;

/**
 * @author wangyu
 *
 */
public interface OracleSsh {

	/**
	 * Oracle AWR info
	 * @param dataBaseId
	 */
	public void sshCollectOracleAWRInfo(String dataBaseId);
	
	/**
	 * Oracle SQL info
	 * @param dataBaseId
	 */
	public void sshCollectOracleSQLInfo(String dataBaseId);
	
	
	/**
	 * Oracle Stat info
	 * @param dataBaseId
	 */
	public void sshCollectOracleStatInfo(String dataBaseId);
	
	
	
	/**
	 * oracle 告警日志
	 * @param dataBaseId
	 * @param monitorPath
	 * @param keyWord
	 */
	public void sshCollectOracleAlert(String dataBaseId,String monitorPath,String keyWord);
	
	
	/**
	 * 检查表空间
	 * @param dbId
	 */
	public void sshCollectOracleTableSpace(String dbId);
	
	
	/**
	 * 长session的监控
	 * @param dbId
	 */
	public void sshCheckLongSQL(String dbId);
	
	/**
	 * 事务
	 * @param dbId
	 */
	public void sshCheckTrans(String dbId);
	
	/**
	 * 索引高度
	 * @param dbId
	 */
	public void sshCheckIndexLevel(String dbId);
	
}
