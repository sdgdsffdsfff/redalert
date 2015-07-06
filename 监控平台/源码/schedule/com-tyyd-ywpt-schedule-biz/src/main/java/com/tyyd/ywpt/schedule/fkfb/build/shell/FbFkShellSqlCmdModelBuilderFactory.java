/**   
* @Title: FbFkJdbcSqlCmdModelBuilderFactory.java 
* @Package com.tyyd.ywpt.schedule.fkfb.build.jdbc 
* @Description:  
* @author wangyu   
* @date 2015-4-17 上午11:15:56 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.fkfb.build.shell;


/**
 * @author wangyu
 *
 */
public class FbFkShellSqlCmdModelBuilderFactory {

	/**
	 * perl /home/mysql/admin/bin/percona-toolkit-2.1.2/bin/pt-online-schema-change 
	 * --user=root --host=192.168.10.49 --password=tyyd!QAZxsw2 --port=3306 --nodrop-old-table 
	 * --alter=" ADD client_version VARCHAR(60)" D=sms,t=orderqueue --execute
	 */
	
	
	
	private String account;
	private String passwd;
	private Integer port;
	private String ipAddr;
	
	
	private String schemaPrefix;
	private Integer schemaCurrentIndex;
	private String tablePrefix;
	private Integer tableCurrentIndex;
	private String ddlScript;
	
	
	

	
	/**
	 * @param account
	 * @param passwd
	 * @param port
	 * @param ipAddr
	 * @param schemaPrefix
	 * @param schemaCurrentIndex
	 * @param tablePrefix
	 * @param tableCurrentIndex
	 * @param ddlScript
	 */
	public FbFkShellSqlCmdModelBuilderFactory(String account, String passwd,
			Integer port, String ipAddr, String schemaPrefix,
			Integer schemaCurrentIndex, String tablePrefix,
			Integer tableCurrentIndex, String ddlScript) {
		
		this.account = account;
		this.passwd = passwd;
		this.port = port;
		this.ipAddr = ipAddr;
		this.schemaPrefix = schemaPrefix;
		this.schemaCurrentIndex = schemaCurrentIndex;
		this.tablePrefix = tablePrefix;
		this.tableCurrentIndex = tableCurrentIndex;
		this.ddlScript = ddlScript;
	}


	public String buildModel(){
		
		StringBuffer sqlCmd = new StringBuffer();
		
		//account
		sqlCmd.append(this.user());
		
		//host
		sqlCmd.append(this.host());
		
		//passwd
		sqlCmd.append(this.passwd());
		
		//port
		sqlCmd.append(this.port());
		
		//charset
		sqlCmd.append(this.charSet());
		
		//ddlScript
		sqlCmd.append(this.ddlScript());
		
		//database
		sqlCmd.append(this.schema());
		
		//table
		sqlCmd.append(this.table());
		
		return sqlCmd.toString();
		
	}
	
	
	/**
	 * @return
	 */
	private Object charSet() {
		StringBuffer sqlCmd = new StringBuffer();
		sqlCmd.append("--charset=utf8");
		sqlCmd.append(" ");
		return sqlCmd.toString();
	}


	/**
	 * user
	 * @return
	 */
	private String user(){
		StringBuffer sqlCmd = new StringBuffer();
		
		sqlCmd.append("perl /home/mysql/admin/bin/percona-toolkit-2.1.2/bin/pt-online-schema-change ");
		sqlCmd.append("--user=");
		sqlCmd.append(this.account);
		sqlCmd.append(" ");
		return sqlCmd.toString();
	}
	
	/**
	 * passwd
	 * @return
	 */
	private String passwd(){
		StringBuffer sqlCmd = new StringBuffer();
		
		sqlCmd.append("--password=");
		sqlCmd.append(this.passwd);
		sqlCmd.append(" ");
		return sqlCmd.toString();
	}
	
	
	/**
	 * host
	 * @return
	 */
	private String host(){
		StringBuffer sqlCmd = new StringBuffer();
		
		sqlCmd.append("--host=");
		sqlCmd.append(this.ipAddr);
		sqlCmd.append(" ");
		return sqlCmd.toString();
	}
	
	
	/**
	 * port
	 * @return
	 */
	private String port(){
		StringBuffer sqlCmd = new StringBuffer();
		
		sqlCmd.append("--port=");
		sqlCmd.append(this.port);
		sqlCmd.append(" ");
		return sqlCmd.toString();
	}
	
	/**
	 * 库名
	 * @return
	 */
	private String schema(){
		StringBuffer sqlCmd = new StringBuffer();
		
		sqlCmd.append("D=");
		sqlCmd.append(this.schemaPrefix);
		sqlCmd.append("_");
		sqlCmd.append(this.schemaCurrentIndex);
		sqlCmd.append(",");
		sqlCmd.append(" ");
		return sqlCmd.toString();
	}
	
	
	private String table(){
		StringBuffer sqlCmd = new StringBuffer();
		
		sqlCmd.append("t=");
		sqlCmd.append(this.tablePrefix);
		sqlCmd.append("_");
		sqlCmd.append(this.tableCurrentIndex);
		sqlCmd.append(" --execute --max-load=Threads_running=150 --critical-load=Threads_running=300");
		sqlCmd.append(" ");
		return sqlCmd.toString();
	}
	
	
	/**
	 * ddlScript
	 * @return
	 */
	private String ddlScript(){
		StringBuffer sqlCmd = new StringBuffer();
		
		sqlCmd.append("--alter=\"");
		sqlCmd.append(this.ddlScript);
		sqlCmd.append("\"");
		return sqlCmd.toString();
	}
	
}
