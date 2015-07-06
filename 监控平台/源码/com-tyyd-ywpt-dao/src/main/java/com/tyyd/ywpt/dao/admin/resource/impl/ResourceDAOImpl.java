/**   
 * @Title: ResourceDAOImpl.java 
 * @Package com.tyyd.ywpt.dao.admin.resource.impl 
 * @Description:  
 * @author wangyu   
 * @date 2014-6-18 下午2:05:45 
 * @CopyRight 天翼阅读
 * @version V1.0   
 */
package com.tyyd.ywpt.dao.admin.resource.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.admin.resource.ResourceDAO;
import com.tyyd.ywpt.dao.admin.resource.dataobject.ResouceDomain;
import com.tyyd.ywpt.dao.base.dataobject.PageQuery;

/**
 * @author wangyu
 * 
 */
public class ResourceDAOImpl extends TyydBaseDAO<List<ResouceDomain>> implements
		ResourceDAO {

	private static final String context_space = "com.tyyd.ywpt.dao.admin.resource.dataobject.ResouceDomain";


	@Override
	public void addResource(ResouceDomain resource) {
		this.getSqlSessionTemplate().insert(context_space + ".add_resource", resource);

	}

	@Override
	public void modifyResource(ResouceDomain resource) {
		this.getSqlSessionTemplate().update(context_space + ".modify_resource", resource);
	}

	@Override
	public void delResource(String resourceId) {
		this.getSqlSessionTemplate().delete(context_space + ".del_resource", resourceId);

	}

	@Override
	public List<ResouceDomain> listResource(String parentId) {
		return getSqlSessionTemplate().selectList(context_space + ".list_resource_by_parentId",parentId);
	}

	
	@Override
	public PageQuery<List<ResouceDomain>> resourcePageQuery(
			PageQuery<List<ResouceDomain>> pageQuery,String resourceName) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("resouceName", resourceName);
		return this.selectPages(context_space +".page_resource", pageQuery, parameterMap);
	}

	
	@Override
	public ResouceDomain getResouceById(String id) {
		return this.getSqlSessionTemplate().selectOne(context_space +".query_resource_by_id", id);
	}

}
