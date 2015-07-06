/**   
* @Title: FbFkJdbcSqlCmdModel.java 
* @Package com.tyyd.ywpt.schedule.fkfb.build 
* @Description:  
* @author wangyu   
* @date 2015-4-17 上午10:58:54 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.fkfb.build;

import com.tyyd.ywpt.schedule.fkfb.build.jdbc.FbFkJdbcSqlCmdModelBuilderFactory;

/**
 * Jdbc脚本的创建类
 * @author wangyu
 *
 */
public class FbFkJdbcSqlCmdModel {

	public static FbfkJbdcSqlCmdBuilder build(){
		return new FbfkJbdcSqlCmdBuilder();
	}
	
	public static class FbfkJbdcSqlCmdBuilder{
		
		private Integer ddlType;
		private String schemaPrefix;
		private Integer schemaCurrentIndex;
		private String tablePrefix;
		private Integer tableCurrentIndex;
		private String ddlScript;
		
		
		/**
		 * 设置ddlType
		 */
		public FbfkJbdcSqlCmdBuilder ddlType(Integer ddlType) {
			this.ddlType = ddlType;
            return this;
		}


		/**
		 * 设置schemaPrefix
		 */
		public FbfkJbdcSqlCmdBuilder schemaPrefix(String schemaPrefix) {
			this.schemaPrefix = schemaPrefix;
			return this;
		}


		/**
		 * 设置schemaCurrentIndex
		 */
		public FbfkJbdcSqlCmdBuilder schemaCurrentIndex(Integer schemaCurrentIndex) {
			this.schemaCurrentIndex = schemaCurrentIndex;
			return this;
		}


		/**
		 * 设置tablePrefix
		 */
		public FbfkJbdcSqlCmdBuilder tablePrefix(String tablePrefix) {
			this.tablePrefix = tablePrefix;
			return this;
		}


		/**
		 * 设置tableCurrentIndex
		 */
		public FbfkJbdcSqlCmdBuilder tableCurrentIndex(Integer tableCurrentIndex) {
			this.tableCurrentIndex = tableCurrentIndex;
			return this;
		}
		
		/**
		 * 设置ddlScript
		 */
		public FbfkJbdcSqlCmdBuilder ddlScript(String ddlScript) {
			this.ddlScript = ddlScript;
			return this;
		}
		

		public String buildSqlCmdModel(){
			return new FbFkJdbcSqlCmdModelBuilderFactory(ddlType, schemaPrefix,
					schemaCurrentIndex, tablePrefix, tableCurrentIndex,
					ddlScript).buildModel();
		}
		
	}
	
}
