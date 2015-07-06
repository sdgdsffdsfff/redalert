/**   
 * @Title: ZookeeperClientBeanTest.java 
 * @Package test.com.tyyd.ywpt.distcenter.zk.config 
 * @Description:  
 * @author wangyu   
 * @date 2015-1-4 下午8:39:30 
 * @CopyRight 天翼阅读
 * @version V1.0   
 */
package test.com.tyyd.ywpt.distcenter.zk.config;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.nodes.PersistentEphemeralNode;
import org.apache.curator.framework.recipes.nodes.PersistentEphemeralNode.Mode;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tyyd.ywpt.distcenter.zk.data.dataobject.StandardZookeeperDataTypeBO;
import com.tyyd.ywpt.tools.bean.SpringContextHolder;

/**
 * @author wangyu
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/dist-center-application-group.xml" })
public class ZookeeperClientBeanTest {

	@Resource
	private StandardZookeeperDataTypeBO standardZookeeperDataTypeBO;

	@Test
	public void testConnect() {
		CuratorFramework client = SpringContextHolder
				.getBean("zookeeperFactoryBean");
		try {
//			client.create().withMode(CreateMode.EPHEMERAL)
//					.forPath("/resouces/node-001","192.168.1.29".getBytes());
//			
			PersistentEphemeralNode node = new PersistentEphemeralNode(client,Mode.EPHEMERAL,"/resouces/node-001","192.168.1.29".getBytes());
			node.start();
			
			node.waitForInitialCreate(3, TimeUnit.SECONDS);
			
			String actualPath = node.getActualPath();
			System.out.println("node " + actualPath + " value: " + new String(client.getData().forPath(actualPath)));
			
			
			TimeUnit.MINUTES.sleep(2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
