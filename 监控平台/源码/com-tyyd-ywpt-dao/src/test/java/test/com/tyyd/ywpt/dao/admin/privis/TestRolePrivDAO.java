/**   
* @Title: TestRolePrivDAO.java 
* @Package test.com.tyyd.ywpt.dao.admin.privis 
* @Description:  
* @author wangyu   
* @date 2014-6-18 上午11:24:08 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package test.com.tyyd.ywpt.dao.admin.privis;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tyyd.ywpt.dao.admin.privis.RolePrivDAO;
import com.tyyd.ywpt.dao.admin.privis.dataobject.RolePrivDomain;

/**
 * @author wangyu
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/spring/application-dao.xml"})
public class TestRolePrivDAO extends AbstractJUnit4SpringContextTests {

	@Resource
	private RolePrivDAO rolePrivDAO;
	
	@Test
	public void queryPrivByRoleIdTest(){
		List<RolePrivDomain> result = rolePrivDAO.queryPrivByRoleId("00001");
		Assert.assertEquals(1, result.size());
	}
	
	@Test
	public void addPrivsRoleTest(){
		RolePrivDomain domain = new RolePrivDomain();
		domain.setRoleId("00001");
		domain.setResouceId("00002");
		rolePrivDAO.addPrivsRole(domain);
	}
	
	@Test
	public void delPrivsRoleTest(){
		rolePrivDAO.delPrivsRole("00001");
	}
	
}
