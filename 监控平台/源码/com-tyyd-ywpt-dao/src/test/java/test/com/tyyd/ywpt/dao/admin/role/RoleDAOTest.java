/**   
* @Title: RoleDAOTest.java 
* @Package test.com.tyyd.ywpt.dao.admin.role 
* @Description:  
* @author wangyu   
* @date 2014-6-18 下午4:57:19 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package test.com.tyyd.ywpt.dao.admin.role;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import test.com.tyyd.ywpt.dao.BaseDAOTest;

import com.tyyd.ywpt.dao.admin.role.RoleDAO;
import com.tyyd.ywpt.dao.admin.role.dataobject.RoleDomain;


/**
 * @author wangyu
 *
 */

public class RoleDAOTest extends BaseDAOTest{

	@Resource
	private RoleDAO roleDAO;
	
	@Test
	public void listRolesTest(){
		List<RoleDomain> list = roleDAO.listRoles();
		System.out.println(list.size());
	}
	
	@Test
	public void getRoleByIdTest(){
		RoleDomain domain = roleDAO.getRoleById("001");
		System.out.println(domain.getRoleName());
	}
	
	@Test
	public void addRoleTest(){
		RoleDomain roleDomain = new RoleDomain();
		roleDomain.setId("001");
		roleDomain.setRoleName("ces");
		roleDomain.setStatus("0");
		roleDAO.addRole(roleDomain);
	}
	
	@Test
	public void delRoleByIdTest(){
		roleDAO.delRoleById("001");
	}
	
	@Test
	public void updateRoleTest(){
		RoleDomain roleDomain = new RoleDomain();
		roleDomain.setId("001");
		roleDomain.setRoleName("ces22");
		roleDAO.updateRole(roleDomain);
	}
	
	
}
