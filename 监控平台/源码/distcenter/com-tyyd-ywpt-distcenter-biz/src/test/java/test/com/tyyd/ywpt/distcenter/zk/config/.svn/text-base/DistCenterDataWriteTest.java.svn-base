/**   
* @Title: DistCenterDataWriteTest.java 
* @Package test.com.tyyd.ywpt.distcenter.zk.config 
* @Description:  
* @author wangyu   
* @date 2015-1-8 下午3:47:35 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package test.com.tyyd.ywpt.distcenter.zk.config;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tyyd.ywpt.distcenter.zk.config.impl.DistCenterDataWriteManager;

/**
 * @author wangyu
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/spring/dist-center-application-group.xml"})
public class DistCenterDataWriteTest {

	@Resource
	private DistCenterDataWriteManager distCenterDataWriteManager;
	
	@Test
	public void testWrite(){
		distCenterDataWriteManager.writeMapKeyToZKDistCener();
	}
	
	
}
