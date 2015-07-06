/**   
* @Title: DbTableSubClassifyDAO.java 
* @Package com.tyyd.ywpt.dao.fbfk 
* @Description:  
* @author wangyu   
* @date 2015-4-15 上午10:04:17 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.fbfk;

import java.util.List;

import com.tyyd.ywpt.dao.fbfk.dataobject.DbTableSubClassifyDomain;

/**
 * 分库分表归类定义
 * @author wangyu
 *
 */
public interface DbTableSubClassifyDAO {

	/**
	 * 获取当前定义的分库分表归类列表
	 * @return
	 */
	public List<DbTableSubClassifyDomain> listDbTableSubClassify();
	
	
	
	/**
	 * 根据ID获取分库分表的定义
	 * @param dbTableSubClassifyId
	 * @return
	 */
	public DbTableSubClassifyDomain getDbTableSubClassifyById(Integer dbTableSubClassifyId);
	
	
	
	
}
