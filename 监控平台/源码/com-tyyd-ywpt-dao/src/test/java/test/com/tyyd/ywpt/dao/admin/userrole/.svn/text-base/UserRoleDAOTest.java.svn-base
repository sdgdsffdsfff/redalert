/**   
* @Title: UserRoleDAOTest.java 
* @Package test.com.tyyd.ywpt.dao.admin.userrole 
* @Description:  
* @author wangyu   
* @date 2014-6-18 下午7:12:03 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package test.com.tyyd.ywpt.dao.admin.userrole;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import test.com.tyyd.ywpt.dao.BaseDAOTest;

import com.tyyd.ywpt.dao.admin.userrole.UserRoleDAO;
import com.tyyd.ywpt.dao.admin.userrole.dataobject.UserRoleDomain;

/**
 * @author wangyu
 *
 */
public class UserRoleDAOTest extends BaseDAOTest {

	@Resource
	private UserRoleDAO userRoleDAO;
	
	@Test
	public void addUserRoleTest(){
		for(int i=0;i<10;i++){
			UserRoleDomain userRoleDomain = new UserRoleDomain();
			userRoleDomain.setUserId("002U");
			userRoleDomain.setRoleId("002R"+i);
			userRoleDAO.addUserRole(userRoleDomain);
		}
	}
	
	@Test
	public void delUserRoleByUserIdTest(){
		userRoleDAO.delUserRoleByUserId("001U");
	}
	
	@Test
	public void listUserRoleTest(){
		List<UserRoleDomain> list = userRoleDAO.listUserRole("001U");
		for(UserRoleDomain userRole : list){
			System.out.println(userRole.getUserId()+","+userRole.getRoleId());
		}
	}
}
