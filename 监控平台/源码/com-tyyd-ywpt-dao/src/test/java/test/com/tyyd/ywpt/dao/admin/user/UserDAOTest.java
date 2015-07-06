/**   
* @Title: UserDAOTest.java 
* @Package test.com.tyyd.ywpt.dao.admin.user 
* @Description:  
* @author wangyu   
* @date 2014-6-18 下午5:57:53 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package test.com.tyyd.ywpt.dao.admin.user;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.tyyd.ywpt.dao.admin.user.UserDAO;
import com.tyyd.ywpt.dao.admin.user.dataobject.UserDomain;
import com.tyyd.ywpt.dao.base.dataobject.PageQuery;

import test.com.tyyd.ywpt.dao.BaseDAOTest;

/**
 * @author wangyu
 *
 */
public class UserDAOTest extends BaseDAOTest {

	@Resource
	private UserDAO userDAO;
	
	@Test
	public void addUserTest(){
		for(int i=0;i<100;i++){
			UserDomain userDomain = new UserDomain();
			userDomain.setEmail("newshake@163.com"+i);
			userDomain.setLoginName("newshake"+i);
			userDomain.setMobileMail("15382328086@189.cn"+i);
			userDomain.setPasswd("123456"+i);
			userDomain.setUserName("王宇"+i);
			userDomain.setId("001"+i);
			userDAO.addUser(userDomain);
		}
	}
	
	@Test
	public void delUserTest(){
		userDAO.delUser("001");
	}
	
	@Test
	public void getUserByIdTest(){
		UserDomain userDomain = userDAO.getUserById("001");
		System.out.println(userDomain.getUserName());
	}
	
	@Test
	public void updateUserTest(){
		UserDomain userDomain = new UserDomain();
		userDomain.setEmail("newshake@163.com");
		userDomain.setLoginName("newshake");
		userDomain.setMobileMail("15382328086@189.cn");
		userDomain.setPasswd("123456");
		userDomain.setUserName("王宇");
		userDomain.setId("001");
		userDAO.updateUser(userDomain);
	}
	
	@Test
	public void listUserPagesTest(){
		PageQuery<List<UserDomain>> pageQuery = new PageQuery<List<UserDomain>>();
		pageQuery.setCurrentPage(3);
		pageQuery.setPageSize(10);
		pageQuery = userDAO.listUserPages(pageQuery, "");
		for(UserDomain domain : pageQuery.getRecords()){
			System.out.println(domain.getId()+":"+domain.getUserName());
		}
	}
	
	@Test
	public void updatePasswdTest(){
		UserDomain userDomain = new UserDomain();
		userDomain.setPasswd("123456789");
		userDomain.setId("001");
		userDAO.updatePasswd(userDomain);
	}
	
	
}
