/**   
* @Title: UserDAO.java 
* @Package com.tyyd.ywpt.dao.admin.user 
* @Description:  
* @author wangyu   
* @date 2014-6-18 下午5:09:49 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.admin.user;

import java.util.List;

import com.tyyd.ywpt.dao.admin.user.dataobject.UserDomain;
import com.tyyd.ywpt.dao.base.dataobject.PageQuery;

/**
 * @author wangyu
 *
 */
public interface UserDAO {

	/**
	 * 添加用户
	 * @param userDomain
	 */
	public void addUser(UserDomain userDomain);
	
	/**
	 * 删除用户
	 * @param userId
	 */
	public void delUser(String userId);
	
	/**
	 * 获取用户信息
	 * @param userId
	 * @return
	 */
	public UserDomain getUserById(String userId);
	
	/**
	 * 更新用户
	 * @param userDomain
	 */
	public void updateUser(UserDomain userDomain);
	
	/**
	 * 用户列表
	 * @param pageQuery
	 * @param userName
	 * @return
	 */
	public PageQuery<List<UserDomain>> listUserPages(PageQuery<List<UserDomain>> pageQuery,String userName);
	
	/**
	 * 更新用户密码
	 * @param userDomain
	 */
	public void updatePasswd(UserDomain userDomain);
}
