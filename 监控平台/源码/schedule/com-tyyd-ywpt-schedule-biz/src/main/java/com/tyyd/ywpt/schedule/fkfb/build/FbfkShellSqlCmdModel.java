/**   
* @Title: FbfkShellSqlCmdModel.java 
* @Package com.tyyd.ywpt.schedule.fkfb.build 
* @Description:  
* @author wangyu   
* @date 2015-4-17 下午3:10:16 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.fkfb.build;

import com.tyyd.ywpt.schedule.fkfb.build.shell.FbFkShellSqlCmdModelBuilderFactory;

/**
 * Shell脚本的创建类
 * @author wangyu
 *
 */
public class FbfkShellSqlCmdModel {

	
	public static FbfkShellSqlCmdBuilder build(){
		return new FbfkShellSqlCmdBuilder();
	}
	
	public static class FbfkShellSqlCmdBuilder{
		
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
		 * 设置account
		 */
		public FbfkShellSqlCmdBuilder account(String account) {
			this.account = account;
            return this;
		}
		
		/**
		 * 设置passwd
		 */
		public FbfkShellSqlCmdBuilder passwd(String passwd) {
			this.passwd = passwd;
            return this;
		}
		
		/**
		 * 设置ipAddr
		 */
		public FbfkShellSqlCmdBuilder ipAddr(String ipAddr) {
			this.ipAddr = ipAddr;
            return this;
		}
		
		/**
		 * 设置port
		 */
		public FbfkShellSqlCmdBuilder port(Integer port) {
			this.port = port;
            return this;
		}


		/**
		 * 设置schemaPrefix
		 */
		public FbfkShellSqlCmdBuilder schemaPrefix(String schemaPrefix) {
			this.schemaPrefix = schemaPrefix;
			return this;
		}


		/**
		 * 设置schemaCurrentIndex
		 */
		public FbfkShellSqlCmdBuilder schemaCurrentIndex(Integer schemaCurrentIndex) {
			this.schemaCurrentIndex = schemaCurrentIndex;
			return this;
		}


		/**
		 * 设置tablePrefix
		 */
		public FbfkShellSqlCmdBuilder tablePrefix(String tablePrefix) {
			this.tablePrefix = tablePrefix;
			return this;
		}


		/**
		 * 设置tableCurrentIndex
		 */
		public FbfkShellSqlCmdBuilder tableCurrentIndex(Integer tableCurrentIndex) {
			this.tableCurrentIndex = tableCurrentIndex;
			return this;
		}
		
		/**
		 * 设置ddlScript
		 */
		public FbfkShellSqlCmdBuilder ddlScript(String ddlScript) {
			this.ddlScript = ddlScript;
			return this;
		}
		

		public String buildSqlCmdModel(){
			return new FbFkShellSqlCmdModelBuilderFactory(account, passwd,
					port, ipAddr, schemaPrefix, schemaCurrentIndex,
					tablePrefix, tableCurrentIndex, ddlScript).buildModel();
		}
		
	}
	
}
