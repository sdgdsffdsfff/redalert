/**   
* @Title: FbfkJdbcExecModelFactory.java 
* @Package com.tyyd.ywpt.schedule.fkfb.factory 
* @Description:  
* @author wangyu   
* @date 2015-4-16 下午4:45:57 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.fkfb.factory;

import com.tyyd.ywpt.schedule.fkfb.factory.jdbc.FbfkDbExecModelManager;
import com.tyyd.ywpt.tools.bean.SpringContextHolder;

/**
 * @author wangyu
 *
 */
public class FbfkJdbcExecModelFactory {

	public static FbfkDbExecModelManager createJdbcExecModel(){
		return SpringContextHolder.getBean("fbfkDbExecModelManager");
	}
	
}
