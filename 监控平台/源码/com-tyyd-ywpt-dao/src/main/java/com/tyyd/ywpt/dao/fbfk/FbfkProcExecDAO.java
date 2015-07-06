/**   
* @Title: FbfkProcExecDAO.java 
* @Package com.tyyd.ywpt.dao.fbfk 
* @Description:  
* @author wangyu   
* @date 2015-4-15 下午4:41:30 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.fbfk;

/**
 * @author wangyu
 *
 */
public interface FbfkProcExecDAO {

	/**
	 * 执行SQL
	 * @param sql
	 */
	public void execute(String sql);
}
