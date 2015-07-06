/**   
* @Title: FbfkExecCmdInvoker.java 
* @Package com.tyyd.ywpt.schedule.fkfb.command 
* @Description:  
* @author wangyu   
* @date 2015-4-17 下午6:16:03 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.fkfb.command;

import javax.annotation.Resource;

import com.tyyd.ywpt.schedule.fkfb.dataobject.FbFkExeInputBO;
import com.tyyd.ywpt.schedule.fkfb.factory.dataobject.FbfkExecResultBO;

/**
 * @author wangyu
 *
 */
public class FbfkExecCmdInvoker {

	private FbFkExeInputBO execBO;
	
	@Resource
	private FbfkExecCommand command;

	/**
	 * @return the command
	 */
	public FbfkExecCommand getCommand() {
		return command;
	}

	/**
	 * @param command the command to set
	 */
	public void setCommand(FbfkExecCommand command) {
		this.command = command;
	}
	
	/**
	 * 执行
	 * @return
	 */
	public FbfkExecResultBO execCmd(){
		FbfkExecResultBO result = command.execute(execBO);
		return result;
	}
	
	/**
	 * @param execBO
	 */
	private FbfkExecCmdInvoker(FbFkExeInputBO execBO) {
		this.execBO = execBO;
	}
	
}
