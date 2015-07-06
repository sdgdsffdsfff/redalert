/**   
* @Title: UserDAOImpl.java 
* @Package com.tyyd.ywpt.dao.admin.user.impl 
* @Description:  
* @author wangyu   
* @date 2014-6-18 下午5:18:01 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.admin.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.admin.user.UserDAO;
import com.tyyd.ywpt.dao.admin.user.dataobject.UserDomain;
import com.tyyd.ywpt.dao.base.dataobject.PageQuery;

/**
 * @author wangyu
 *
 */
public class UserDAOImpl extends TyydBaseDAO<List<UserDomain>> implements UserDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.admin.user.dataobject.UserDomain";
	
	
	@Override
	public void addUser(UserDomain userDomain) {
		this.getSqlSessionTemplate().insert(context_space +".add_user", userDomain);
	}

	@Override
	public void delUser(String userId) {
		this.getSqlSessionTemplate().delete(context_space +".del_user", userId);
	}

	@Override
	public UserDomain getUserById(String userId) {
		return this.getSqlSessionTemplate().selectOne(context_space +".get_user_by_id", userId);
	}

	@Override
	public void updateUser(UserDomain userDomain) {
		this.getSqlSessionTemplate().update(context_space +".update_user", userDomain);
	}

	@Override
	public PageQuery<List<UserDomain>> listUserPages(
			PageQuery<List<UserDomain>> pageQuery, String userName) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", userName);
		return this.selectPages(context_space+".list_user_pages", pageQuery, map);
	}

	@Override
	public void updatePasswd(UserDomain userDomain) {
		this.getSqlSessionTemplate().update(context_space +".update_user_passwd", userDomain);
	}

}
