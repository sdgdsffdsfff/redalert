/**   
* @Title: FbfkExecCreateIndexCommandImpl.java 
* @Package com.tyyd.ywpt.schedule.fkfb.command.impl 
* @Description:  
* @author wangyu   
* @date 2015-4-17 下午6:14:44 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.fkfb.command.impl;

import javax.annotation.Resource;

import com.tyyd.ywpt.schedule.fkfb.command.FbfkExecCommand;
import com.tyyd.ywpt.schedule.fkfb.dataobject.FbFkExeInputBO;
import com.tyyd.ywpt.schedule.fkfb.factory.dataobject.FbfkExecResultBO;

/**
 * @author wangyu
 *
 */
public class FbfkExecAlertFiledCommandImpl implements FbfkExecCommand {

	@Resource
	private FbfkExecModelReceiver fbfkExecModelReceiver;
	
	
	@Override
	public FbfkExecResultBO execute(FbFkExeInputBO execBO) {
		return fbfkExecModelReceiver.alertFiledCmd(execBO);
	}
}
