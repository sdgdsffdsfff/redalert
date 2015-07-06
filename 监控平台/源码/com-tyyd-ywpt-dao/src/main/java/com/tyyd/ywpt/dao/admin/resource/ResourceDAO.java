/**   
* @Title: ResourceDAO.java 
* @Package com.tyyd.ywpt.dao.admin.resource 
* @Description:  
* @author wangyu   
* @date 2014-6-18 下午1:00:49 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.admin.resource;

import java.util.List;

import com.tyyd.ywpt.dao.admin.resource.dataobject.ResouceDomain;
import com.tyyd.ywpt.dao.base.dataobject.PageQuery;

/**
 * @author wangyu
 *
 */
public interface ResourceDAO {

	/**
	 * 查询所有资源
	 * @return
	 */
	public List<ResouceDomain> listResource(String parentId);
	
	/**
	 * 资源分页
	 * @param pageQuery
	 * @return
	 */
	public PageQuery<List<ResouceDomain>> resourcePageQuery(PageQuery<List<ResouceDomain>> pageQuery,String resourceName);
	
	
	/**
	 * 根据资源ID查详细内容
	 * @param id
	 * @return
	 */
	public ResouceDomain getResouceById(String id);
	
	/**
	 * 添加资源
	 * @param resource
	 */
	public void addResource(ResouceDomain resource);
	
	
	/**
	 * 更新资源
	 * @param resource
	 */
	public void modifyResource(ResouceDomain resource);
	
	
	/**
	 * 删除资源
	 * @param resourceId
	 */
	public void delResource(String resourceId);
}
