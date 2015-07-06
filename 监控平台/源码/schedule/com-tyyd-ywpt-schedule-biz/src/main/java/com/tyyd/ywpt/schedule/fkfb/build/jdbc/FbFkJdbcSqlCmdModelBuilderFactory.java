/**   
* @Title: FbFkJdbcSqlCmdModelBuilderFactory.java 
* @Package com.tyyd.ywpt.schedule.fkfb.build.jdbc 
* @Description:  
* @author wangyu   
* @date 2015-4-17 上午11:15:56 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.fkfb.build.jdbc;


/**
 * @author wangyu
 *
 */
public class FbFkJdbcSqlCmdModelBuilderFactory {

	private Integer ddlType;
	private String schemaPrefix;
	private Integer schemaCurrentIndex;
	private String tablePrefix;
	private Integer tableCurrentIndex;
	private String ddlScript;
	
	/**
	 * @param ddlType
	 * @param schemaPrefix
	 * @param schemaCurrentIndex
	 * @param tablePrefix
	 * @param tableCurrentIndex
	 */
	public FbFkJdbcSqlCmdModelBuilderFactory(Integer ddlType,
			String schemaPrefix, Integer schemaCurrentIndex,
			String tablePrefix, Integer tableCurrentIndex,String ddlScript) {
		
		this.ddlType = ddlType;
		this.schemaPrefix = schemaPrefix;
		this.schemaCurrentIndex = schemaCurrentIndex;
		this.tablePrefix = tablePrefix;
		this.tableCurrentIndex = tableCurrentIndex;
		this.ddlScript = ddlScript;
	}

	
	public String buildModel(){
		
		StringBuffer sqlCmd = new StringBuffer();
		
		//DDL Type 设置
		sqlCmd.append(ddlType());
		
		//database 设置
		sqlCmd.append(schema());
		
		//table 设置
		sqlCmd.append(table());
		
		//script 设置
		sqlCmd.append(this.ddlScript);
		
		return sqlCmd.toString();
		
	}
	
	
	/**
	 * ddl Type 转化
	 * @return
	 */
	private String ddlType(){
		StringBuffer sqlCmd = new StringBuffer();
		
		if(FbfkDdlTypeEnum.CreateTable.getDdlType().intValue() == this.ddlType.intValue()){
			sqlCmd.append("CREATE TABLE ");
			
		}else if(FbfkDdlTypeEnum.AlertComments.getDdlType().intValue() == this.ddlType.intValue() 
				|| FbfkDdlTypeEnum.AddFileds.getDdlType().intValue() == this.ddlType.intValue()
				|| FbfkDdlTypeEnum.CreateIndex.getDdlType().intValue() == this.ddlType.intValue()
				|| FbfkDdlTypeEnum.AlertTableFiled.getDdlType().intValue() == this.ddlType.intValue()){
			sqlCmd.append("ALTER TABLE ");
			
		}
		return sqlCmd.toString();
	}
	
	
	/**
	 * 库名
	 * @return
	 */
	private String schema(){
		StringBuffer sqlCmd = new StringBuffer();
		
		sqlCmd.append("`");
		sqlCmd.append(this.schemaPrefix);
		sqlCmd.append("_");
		sqlCmd.append(this.schemaCurrentIndex);
		sqlCmd.append("`");
		
		return sqlCmd.toString();
	}
	
	
	private String table(){
		StringBuffer sqlCmd = new StringBuffer();
		
		sqlCmd.append("`");
		sqlCmd.append(this.tablePrefix);
		sqlCmd.append("_");
		sqlCmd.append(this.tableCurrentIndex);
		sqlCmd.append("`");
		
		return sqlCmd.toString();
	}
	
}
