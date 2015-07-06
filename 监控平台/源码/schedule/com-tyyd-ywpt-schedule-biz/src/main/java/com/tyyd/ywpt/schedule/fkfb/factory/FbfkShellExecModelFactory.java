/**   
* @Title: FbfkShellExecModelFactory.java 
* @Package com.tyyd.ywpt.schedule.fkfb.factory 
* @Description:  
* @author wangyu   
* @date 2015-4-16 下午4:49:31 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.fkfb.factory;

import com.tyyd.ywpt.schedule.fkfb.factory.script.FbfkDbScriptExecModelManager;
import com.tyyd.ywpt.tools.bean.SpringContextHolder;

/**
 * @author wangyu
 *
 */
public class FbfkShellExecModelFactory {

	public static FbfkDbScriptExecModelManager createShellModel(){
		return SpringContextHolder.getBean("fbfkDbScriptExecModelManager");
	}
	
}
