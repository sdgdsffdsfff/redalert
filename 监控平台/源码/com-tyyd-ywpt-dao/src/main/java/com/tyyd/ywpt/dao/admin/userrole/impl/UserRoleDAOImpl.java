/**   
* @Title: UserRoleDAOImpl.java 
* @Package com.tyyd.ywpt.dao.admin.userrole.impl 
* @Description:  
* @author wangyu   
* @date 2014-6-18 下午6:51:27 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.admin.userrole.impl;

import java.util.List;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.admin.userrole.UserRoleDAO;
import com.tyyd.ywpt.dao.admin.userrole.dataobject.UserRoleDomain;

/**
 * @author wangyu
 *
 */
public class UserRoleDAOImpl extends TyydBaseDAO<UserRoleDomain> implements
		UserRoleDAO {

	
	private final static String context_space = "com.tyyd.ywpt.dao.admin.userrole.dataobject.UserRoleDomain";
	
	@Override
	public void addUserRole(UserRoleDomain userRoleDomain) {
		this.getSqlSessionTemplate().insert(context_space+".add_user_role", userRoleDomain);
	}

	@Override
	public void delUserRoleByUserId(String UserId) {
		this.getSqlSessionTemplate().delete(context_space+".del_user_role_by_user_id", UserId);
	}


	@Override
	public List<UserRoleDomain> listUserRole(String userId) {
		return this.getSqlSessionTemplate().selectList(context_space+".list_user_role", userId);
	}

}
