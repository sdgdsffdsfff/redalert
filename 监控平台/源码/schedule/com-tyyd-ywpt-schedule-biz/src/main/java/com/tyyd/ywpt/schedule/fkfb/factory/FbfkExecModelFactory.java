/**   
* @Title: FbfkExecModelFactory.java 
* @Package com.tyyd.ywpt.schedule.fkfb.factory 
* @Description:  
* @author wangyu   
* @date 2015-4-16 下午4:50:32 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.fkfb.factory;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;

import com.tyyd.ywpt.schedule.fkfb.factory.dataobject.FbfkExecResultBO;
import com.tyyd.ywpt.schedule.fkfb.factory.dataobject.FbfkExecTypeEnum;
import com.tyyd.ywpt.schedule.fkfb.factory.jdbc.FbfkDbExecModelManager;
import com.tyyd.ywpt.schedule.fkfb.factory.script.FbfkDbScriptExecModelManager;

/**
 * @author wangyu
 *
 */
public class FbfkExecModelFactory implements InitializingBean{

	private FbfkDbExecModelManager jdbcExecModelManager;
	
	private FbfkDbScriptExecModelManager shellExecModelManager;
	
	public FbfkExecResultBO exec(Long taskId,String dbId,String sqlCmd,Integer execType){
		
		//检查参数
		FbfkExecResultBO result = this.checkParam(taskId,dbId,sqlCmd,execType);
		
		if(!result.isSuccess()){
			return result;
		}
		
		result.setSuccess(Boolean.FALSE);
		
		if(FbfkExecTypeEnum.shellCmd.getId().intValue() == execType.intValue()){
			this.shellExecModelManager.execScript(taskId, dbId, sqlCmd);
			
		}else if(FbfkExecTypeEnum.sqlCmd.getId().intValue() == execType.intValue()){
			this.jdbcExecModelManager.execDbCommand(taskId, dbId, sqlCmd);
			
		}else{
			result.setResult("请检查 execType 的值");
			return result;
		}
		
		
		result.setSuccess(Boolean.TRUE);
		return result;
	}

	
	
	
	/**
	 * @param taskId
	 * @param dbId
	 * @param sqlCmd
	 * @param execType
	 * @return
	 */
	private FbfkExecResultBO checkParam(Long taskId, String dbId,
			String sqlCmd, Integer execType) {
		FbfkExecResultBO result = new FbfkExecResultBO();
		result.setSuccess(Boolean.FALSE);
		
		if(null == taskId){
			String remark = "taskId 为空，请检查";
			result.setResult(remark);
			return result;
		}
		
		if(StringUtils.isBlank(dbId)){
			String remark = "dbId 为空，请检查";
			result.setResult(remark);
			return result;
		}
		
		if(StringUtils.isBlank(sqlCmd)){
			String remark = "sqlCmd 为空，请检查";
			result.setResult(remark);
			return result;
		}
		
		
		if(null == execType){
			String remark = "execType 为空，请检查";
			result.setResult(remark);
			return result;
		}
		
		result.setSuccess(Boolean.TRUE);
		return result;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
		//jdbc Model
		this.jdbcExecModelManager = FbfkJdbcExecModelFactory.createJdbcExecModel();
		
		//shell Model
		this.shellExecModelManager = FbfkShellExecModelFactory.createShellModel();
		
	}


	/**
	 * jdbc Model
	 * @return
	 */
	public FbfkDbExecModelManager getJdbcExecModelManager() {
		if(null == this.jdbcExecModelManager){
			this.jdbcExecModelManager = FbfkJdbcExecModelFactory.createJdbcExecModel();
		}
		return this.jdbcExecModelManager;
	}


	/**
	 * Shell Model
	 * @return
	 */
	public FbfkDbScriptExecModelManager getShellExecModelManager() {
		if(null == this.shellExecModelManager){
			this.shellExecModelManager = FbfkShellExecModelFactory.createShellModel();
		}
		return this.shellExecModelManager;
	}
	
}
