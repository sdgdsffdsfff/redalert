/**   
* @Title: FbfkDbScriptExecModelManagerImpl.java 
* @Package com.tyyd.ywpt.schedule.fkfb.factory.script.impl 
* @Description:  
* @author wangyu   
* @date 2015-4-16 下午4:35:09 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.fkfb.factory.script.impl;

import org.apache.log4j.Logger;

import com.tyyd.ywpt.biz.ssh.dataobject.SSHResultBO;
import com.tyyd.ywpt.biz.ssh.impl.AbstractLinuxLocalInvokeManager;
import com.tyyd.ywpt.schedule.fkfb.factory.dataobject.FbfkExecResultBO;
import com.tyyd.ywpt.schedule.fkfb.factory.jdbc.impl.FbfkDbExecModelManagerImpl;
import com.tyyd.ywpt.schedule.fkfb.factory.script.FbfkDbScriptExecModelManager;

/**
 * @author wangyu
 *
 */
public class FbfkDbScriptExecModelManagerImpl extends AbstractLinuxLocalInvokeManager implements
		FbfkDbScriptExecModelManager {

	public static final Logger LOGGER = Logger.getLogger(FbfkDbExecModelManagerImpl.class);
	
	
	@Override
	public FbfkExecResultBO execScript(Long taskId, String dbId, String shellCmd) {
		LOGGER.info("执行 shellCmd:"+shellCmd);
		
		SSHResultBO sshResultBO = this.localCmdExec(shellCmd);
		
		FbfkExecResultBO result = new FbfkExecResultBO();
		result.setSuccess(sshResultBO.isSucess());
		result.setResult(sshResultBO.getResult());
		
		return result;
	}

}
