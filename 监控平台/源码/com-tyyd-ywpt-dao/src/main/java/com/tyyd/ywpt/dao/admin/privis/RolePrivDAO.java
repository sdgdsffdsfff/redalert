/**   
* @Title: RolePrivDAO.java 
* @Package com.tyyd.ywpt.dao.admin.privis 
* @Description:  
* @author wangyu   
* @date 2014-6-18 上午9:17:52 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.admin.privis;

import java.util.List;

import com.tyyd.ywpt.dao.admin.privis.dataobject.RolePrivDomain;

/**
 * @author wangyu
 *
 */
public interface RolePrivDAO {

	/**
	 * 根据角色ID查询权限
	 * @param roleId
	 * @return
	 */
	public List<RolePrivDomain> queryPrivByRoleId(String roleId);
	
	
	/**
	 * 添加角色与权限的关系
	 * @param rolePriv
	 */
	public void addPrivsRole(RolePrivDomain rolePriv);
	
	/**
	 * 删除角色的权限
	 * @param roleId
	 */
	public void delPrivsRole(String roleId);
}
