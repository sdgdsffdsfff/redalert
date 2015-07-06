/**   
* @Title: ResourceDAOTest.java 
* @Package test.com.tyyd.ywpt.dao.admin.resource 
* @Description:  
* @author wangyu   
* @date 2014-6-18 下午2:52:10 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package test.com.tyyd.ywpt.dao.admin.resource;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tyyd.ywpt.dao.admin.resource.ResourceDAO;
import com.tyyd.ywpt.dao.admin.resource.dataobject.ResouceDomain;
import com.tyyd.ywpt.dao.base.dataobject.PageQuery;

/**
 * @author wangyu
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/spring/application-dao.xml"})
public class ResourceDAOTest {

	@Resource
	private ResourceDAO resourceDAO;
	
	@Test
	public void listResourceTest(){
		List<ResouceDomain> list = resourceDAO.listResource("-10");
		Assert.assertEquals(2, list.size());
	}
	
	@Test
	public void resourcePageQueryTest(){
		PageQuery<List<ResouceDomain>> pageQuery = new PageQuery<List<ResouceDomain>>();
		pageQuery.setCurrentPage(2);
		pageQuery.setPageSize(10);
		String resourceName = "";
		pageQuery = resourceDAO.resourcePageQuery(pageQuery, resourceName);
		for(ResouceDomain dd : pageQuery.getRecords()){
			System.out.println(dd.getId() +":"+dd.getResouceName());
		}
	}
	
	@Test
	public void addResourceTest(){
		for(int i=3;i<100;i++){
			ResouceDomain resource = new ResouceDomain();
			resource.setId(i+"");
			resource.setParentId("-1");
			resource.setResouceName("测试"+i);
			resource.setStatus("0");
			resource.setUrl("http://../xx"+i);
			resourceDAO.addResource(resource);
		}
	}
	
	@Test
	public void modifyResourceTest(){
		ResouceDomain resource = new ResouceDomain();
		resource.setId("1");
		resource.setParentId("-10");
		resource.setResouceName("测试10");
		resource.setUrl("http://../xx/dd");
		resourceDAO.modifyResource(resource);
	}
	
	@Test
	public void delResourceTest(){
		resourceDAO.delResource("1");
	}
	
	@Test
	public void getResouceByIdTest(){
		ResouceDomain domain = resourceDAO.getResouceById("10");
		System.out.println(domain.getResouceName());
	}
	
}
