/**   
* @Title: OracleToolsDAOTest.java 
* @Package test.com.tyyd.ywpt.dao.oratools 
* @Description:  
* @author wangyu   
* @date 2015-1-8 上午11:45:23 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package test.com.tyyd.ywpt.dao.oratools;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tyyd.ywpt.dao.oratools.OraToolsDAO;

/**
 * @author wangyu
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/spring/application-oracle-datasource.xml"})
public class OracleToolsDAOTest {

	@Resource
	private OraToolsDAO oraToolsDAO;
	
	@Test
	public void testTools(){
		
		for(int i=0;i<3;i++){
			oraToolsDAO.testPartitions();
		}
	}
}
