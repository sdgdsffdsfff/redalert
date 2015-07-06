/**   
* @Title: UserRoleDAO.java 
* @Package com.tyyd.ywpt.dao.admin.userrole 
* @Description:  
* @author wangyu   
* @date 2014-6-18 下午6:49:43 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.admin.userrole;

import java.util.List;

import com.tyyd.ywpt.dao.admin.userrole.dataobject.UserRoleDomain;

/**
 * @author wangyu
 *
 */
public interface UserRoleDAO {

	
	/**
	 * 添加用户角色
	 * @param userRoleDomain
	 */
	public void addUserRole(UserRoleDomain userRoleDomain);
	
	/**
	 * 删除用户角色
	 * @param UserId
	 */
	public void delUserRoleByUserId(String UserId);
	
	/**
	 * 获取用户对应的角色
	 * @param userId
	 * @return
	 */
	public List<UserRoleDomain> listUserRole(String userId);
}
