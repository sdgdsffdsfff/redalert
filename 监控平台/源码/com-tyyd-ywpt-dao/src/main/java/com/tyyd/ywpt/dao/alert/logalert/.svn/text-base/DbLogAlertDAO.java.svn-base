/**   
* @Title: DbLogAlertDAO.java 
* @Package com.tyyd.ywpt.dao.alert.logalert 
* @Description:  
* @author wangyu   
* @date 2014-8-5 下午4:09:45 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.alert.logalert;

import java.util.List;

import com.tyyd.ywpt.dao.alert.logalert.dataobject.DbLogAlertDomain;

/**
 * @author wangyu
 *
 */
public interface DbLogAlertDAO {

	
	/**
	 * 查询未处理的告警信息
	 * @return
	 */
	public List<DbLogAlertDomain> listNoCompletedMsg();
	
	
	/**
	 * 关闭处理任务
	 * @param id
	 */
	public void updateDbLogAlertCompleted(String id);
}
