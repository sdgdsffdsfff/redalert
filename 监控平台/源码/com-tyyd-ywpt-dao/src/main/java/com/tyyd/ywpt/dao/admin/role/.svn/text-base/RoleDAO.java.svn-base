/**   
* @Title: RoleDAO.java 
* @Package com.tyyd.ywpt.dao.admin.role 
* @Description:  
* @author wangyu   
* @date 2014-6-18 下午4:23:08 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.admin.role;

import java.util.List;

import com.tyyd.ywpt.dao.admin.role.dataobject.RoleDomain;

/**
 * @author wangyu
 *
 */
public interface RoleDAO {

	/**
	 * 角色列表
	 * @return
	 */
	public List<RoleDomain> listRoles();
	
	/**
	 * 获取角色信息
	 * @param roleId
	 * @return
	 */
	public RoleDomain getRoleById(String roleId);
	
	/**
	 * 添加角色
	 * @param roleDomain
	 */
	public void addRole(RoleDomain roleDomain);
	
	/**
	 * 删除角色
	 * @param roleId
	 */
	public void delRoleById(String roleId);
	
	/**
	 * 更新角色
	 * @param roleDomain
	 */
	public void updateRole(RoleDomain roleDomain);
}
