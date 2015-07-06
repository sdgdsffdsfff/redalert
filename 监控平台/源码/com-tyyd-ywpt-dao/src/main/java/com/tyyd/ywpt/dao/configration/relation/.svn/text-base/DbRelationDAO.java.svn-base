/**   
* @Title: DbRelationDAO.java 
* @Package com.tyyd.ywpt.dao.configration.relation 
* @Description:  
* @author wangyu   
* @date 2014-6-21 下午7:01:13 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.configration.relation;

import java.util.List;

import com.tyyd.ywpt.dao.configration.relation.dataobject.DbRelationDomain;

/**
 * @author wangyu
 *
 */
public interface DbRelationDAO {

	
	/**
	 * 添加数据库关系，主从，RAC，等关系
	 * @param domain
	 */
	public void addDataBaseRelation(DbRelationDomain domain);
	
	/**
	 * 查询关系列表
	 * @param batchId
	 * @return
	 */
	public List<DbRelationDomain> listDbRelation(String batchId);
	
	/**
	 * 删除数据库关系
	 * @param batchId
	 */
	public void deleteDataBaseRelation(String batchId);
}
