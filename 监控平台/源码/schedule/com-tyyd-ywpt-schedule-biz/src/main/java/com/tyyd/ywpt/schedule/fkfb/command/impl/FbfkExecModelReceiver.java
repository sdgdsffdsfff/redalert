/**   
 * @Title: FbfkExecModelReceiver.java 
 * @Package com.tyyd.ywpt.schedule.fkfb.command.impl 
 * @Description:  
 * @author wangyu   
 * @date 2015-4-17 下午5:54:29 
 * @CopyRight 天翼阅读
 * @version V1.0   
 */
package com.tyyd.ywpt.schedule.fkfb.command.impl;

import javax.annotation.Resource;

import com.tyyd.ywpt.schedule.fkfb.build.FbFkJdbcSqlCmdModel;
import com.tyyd.ywpt.schedule.fkfb.build.FbfkShellSqlCmdModel;
import com.tyyd.ywpt.schedule.fkfb.dataobject.FbFkExeInputBO;
import com.tyyd.ywpt.schedule.fkfb.factory.FbfkExecModelFactory;
import com.tyyd.ywpt.schedule.fkfb.factory.dataobject.FbfkExecResultBO;

/**
 * @author wangyu
 * 
 */
public class FbfkExecModelReceiver {

	@Resource
	private FbfkExecModelFactory fbfkExecModelFactory;
	
	
	/**
	 * 创建表
	 * 
	 * @param execBO
	 * @return
	 */
	public FbfkExecResultBO createTableCmd(FbFkExeInputBO execBO) {
		
		//生成sqlCmd
		String sqlCmd = FbFkJdbcSqlCmdModel.build()
				.ddlType(execBO.getDdlType())
				.schemaPrefix(execBO.getSchemaPrefix())
				.schemaCurrentIndex(execBO.getSchemaCurrentIndex())
				.tablePrefix(execBO.getTablePrefix())
				.tableCurrentIndex(execBO.getTableCurrentIndex())
				.ddlScript(execBO.getDdlScript()).buildSqlCmdModel();
		
		
		//执行
		FbfkExecResultBO result = fbfkExecModelFactory.exec(execBO.getTaskId(),
				execBO.getDbId(), sqlCmd, execBO.getExecType());
		
		return result;
	}

	
	
	/**
	 * 创建索引
	 * 
	 * @param execBO
	 * @return
	 */
	public FbfkExecResultBO createIndexCmd(FbFkExeInputBO execBO) {
		
		//生成sqlCmd
		String sqlCmd = FbFkJdbcSqlCmdModel.build()
				.ddlType(execBO.getDdlType())
				.schemaPrefix(execBO.getSchemaPrefix())
				.schemaCurrentIndex(execBO.getSchemaCurrentIndex())
				.tablePrefix(execBO.getTablePrefix())
				.tableCurrentIndex(execBO.getTableCurrentIndex())
				.ddlScript(execBO.getDdlScript()).buildSqlCmdModel();
		
		
		//执行
		FbfkExecResultBO result = fbfkExecModelFactory.exec(execBO.getTaskId(),
				execBO.getDbId(), sqlCmd, execBO.getExecType());
		
		return result;
	}
	
	
	/**
	 * 改变结构
	 * 
	 * @param execBO
	 * @return
	 */
	public FbfkExecResultBO alertFiledCmd(FbFkExeInputBO execBO) {
		
		//生成sqlCmd
		String sqlCmd = FbfkShellSqlCmdModel.build()
				.account(execBO.getAccount())
				.ipAddr(execBO.getIpAddr())
				.passwd(execBO.getPasswd())
				.port(execBO.getPort())
				.schemaPrefix(execBO.getSchemaPrefix())
				.schemaCurrentIndex(execBO.getSchemaCurrentIndex())
				.tablePrefix(execBO.getTablePrefix())
				.tableCurrentIndex(execBO.getTableCurrentIndex())
				.ddlScript(execBO.getDdlScript()).buildSqlCmdModel();
		
		
		//执行
		FbfkExecResultBO result = fbfkExecModelFactory.exec(execBO.getTaskId(),
				execBO.getDbId(), sqlCmd, execBO.getExecType());
		
		return result;
	}
	
	
	/**
	 * 添加字段
	 * @param execBO
	 * @return
	 */
	public FbfkExecResultBO addFiledsCmd(FbFkExeInputBO execBO) {
		
		//生成sqlCmd
		String sqlCmd = FbFkJdbcSqlCmdModel.build()
				.ddlType(execBO.getDdlType())
				.schemaPrefix(execBO.getSchemaPrefix())
				.schemaCurrentIndex(execBO.getSchemaCurrentIndex())
				.tablePrefix(execBO.getTablePrefix())
				.tableCurrentIndex(execBO.getTableCurrentIndex())
				.ddlScript(execBO.getDdlScript()).buildSqlCmdModel();
		
		
		//执行
		FbfkExecResultBO result = fbfkExecModelFactory.exec(execBO.getTaskId(),
				execBO.getDbId(), sqlCmd, execBO.getExecType());
		
		return result;
	}
	
	/**
	 * 修改注释
	 * @param execBO
	 * @return
	 */
	public FbfkExecResultBO modifyCommentsCmd(FbFkExeInputBO execBO) {
		
		//生成sqlCmd
		String sqlCmd = FbFkJdbcSqlCmdModel.build()
				.ddlType(execBO.getDdlType())
				.schemaPrefix(execBO.getSchemaPrefix())
				.schemaCurrentIndex(execBO.getSchemaCurrentIndex())
				.tablePrefix(execBO.getTablePrefix())
				.tableCurrentIndex(execBO.getTableCurrentIndex())
				.ddlScript(execBO.getDdlScript()).buildSqlCmdModel();
		
		
		//执行
		FbfkExecResultBO result = fbfkExecModelFactory.exec(execBO.getTaskId(),
				execBO.getDbId(), sqlCmd, execBO.getExecType());
		
		return result;
	}
}
