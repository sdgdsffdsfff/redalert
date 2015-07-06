/**   
* @Title: DbTableSubInstanceTitleDAO.java 
* @Package com.tyyd.ywpt.dao.fbfk 
* @Description:  
* @author wangyu   
* @date 2015-4-15 上午10:04:56 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.fbfk;

import java.util.List;

import com.tyyd.ywpt.dao.fbfk.dataobject.DbTableSubInstanceTitleDomain;

/**
 * 实例信息摘要定义
 * 实例级别的账号密码设置
 * @author wangyu
 *
 */
public interface DbTableSubInstanceTitleDAO {

	/**
	 * 获取实例对应的账号密码等信息
	 * @param dbTableSubClassifyId
	 * @return
	 */
	public List<DbTableSubInstanceTitleDomain> listDbTableSubInstanceTitleById(Integer dbTableSubClassifyId);

	
	/**
	 * 获取详细的信息
	 * @param dbTableSubClassifyId
	 * @param dbId
	 * @return
	 */
	public DbTableSubInstanceTitleDomain getDbTableSubInstanceTitleByDbId(Integer dbTableSubClassifyId,String dbId);
}
