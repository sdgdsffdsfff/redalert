/**   
* @Title: RoleDAOImpl.java 
* @Package com.tyyd.ywpt.dao.admin.role.impl 
* @Description:  
* @author wangyu   
* @date 2014-6-18 下午4:47:37 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.admin.role.impl;

import java.util.List;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.admin.role.RoleDAO;
import com.tyyd.ywpt.dao.admin.role.dataobject.RoleDomain;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class RoleDAOImpl extends TyydBaseDAO implements RoleDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.admin.role.dataobject.RoleDomain";

	@Override
	public List<RoleDomain> listRoles() {
		return this.getSqlSessionTemplate().selectList(context_space+".list_roles");
	}


	@Override
	public RoleDomain getRoleById(String roleId) {
		return this.getSqlSessionTemplate().selectOne(context_space+".get_role_by_id", roleId);
	}


	@Override
	public void addRole(RoleDomain roleDomain) {
		this.getSqlSessionTemplate().insert(context_space+".add_role", roleDomain);
	}


	@Override
	public void delRoleById(String roleId) {
		this.getSqlSessionTemplate().delete(context_space+".del_role_by_id", roleId);
	}


	@Override
	public void updateRole(RoleDomain roleDomain) {
		this.getSqlSessionTemplate().update(context_space+".update_role", roleDomain);
	}

}
