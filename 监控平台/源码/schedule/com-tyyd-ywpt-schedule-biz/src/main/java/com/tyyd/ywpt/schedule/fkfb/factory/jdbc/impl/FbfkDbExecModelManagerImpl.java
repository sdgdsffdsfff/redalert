/**   
* @Title: FbfkDbExecModelManagerImpl.java 
* @Package com.tyyd.ywpt.schedule.fkfb.factory.jdbc.impl 
* @Description:  
* @author wangyu   
* @date 2015-4-16 下午2:13:53 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.fkfb.factory.jdbc.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.tyyd.ywpt.dao.fbfk.FbfkProcExecDAO;
import com.tyyd.ywpt.schedule.fkfb.factory.dataobject.FbfkExecResultBO;
import com.tyyd.ywpt.schedule.fkfb.factory.jdbc.FbfkDbExecModelManager;
import com.tyyd.ywpt.tools.ds.impl.DynamicDataSourceContextHolder;

/**
 * @author wangyu
 *
 */
public class FbfkDbExecModelManagerImpl implements FbfkDbExecModelManager {

	public static final Logger LOGGER = Logger.getLogger(FbfkDbExecModelManagerImpl.class);
	
	@Resource
	private FbfkProcExecDAO fbfkProcExecDAO;
	
	
	@Override
	public FbfkExecResultBO execDbCommand(Long taskId,String dbId,String sqlCmd) {
		LOGGER.info("执行cmd : "+sqlCmd);

		//设置当前DS
		String mysqlDs = String.format("mysql_%s", dbId);
		
		//切换到当前DS
		DynamicDataSourceContextHolder.setDataSource(mysqlDs);
		
		FbfkExecResultBO result = this.execTask(taskId, dbId, sqlCmd);
		
		//切换默认DS
		DynamicDataSourceContextHolder.setDataSource("dataSource");
		
		return result;
	}


	/**
	 * @param sqlCmd
	 * @return
	 */
	private FbfkExecResultBO execTask(Long taskId,String dbId,String sqlCmd) {
		FbfkExecResultBO result = new FbfkExecResultBO();
		boolean execFlag = Boolean.FALSE;
		StringBuffer log = new StringBuffer();
		
		try{
			fbfkProcExecDAO.execute(sqlCmd);
			execFlag = Boolean.TRUE;
			log.append("执行成功");
			LOGGER.error("SQL 执行 ["+sqlCmd+"]成功");
			
		}catch(Exception e){
			String error = e.getMessage();
			log.append("执行失败 , 失败原因:"); 
			log.append(error);
			LOGGER.error("SQL 执行异常 ["+sqlCmd+"]", e);
		}
		
		result.setResult(log.toString());
		result.setSuccess(execFlag);
		
		return result;
	}

}
