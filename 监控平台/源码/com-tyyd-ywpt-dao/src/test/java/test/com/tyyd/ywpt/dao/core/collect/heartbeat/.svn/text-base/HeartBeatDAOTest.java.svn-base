/**   
* @Title: HeartBeatDAOTest.java 
* @Package test.com.tyyd.ywpt.dao.core.collect.heartbeat 
* @Description:  
* @author wangyu   
* @date 2014-6-23 下午5:49:58 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package test.com.tyyd.ywpt.dao.core.collect.heartbeat;

import javax.annotation.Resource;

import org.junit.Test;

import test.com.tyyd.ywpt.dao.BaseDAOTest;

import com.tyyd.ywpt.dao.core.collect.heartbeat.HeartbeatMonitorDAO;
import com.tyyd.ywpt.dao.core.collect.heartbeat.dataobject.HeartbeatMonitorDomain;

/**
 * @author wangyu
 *
 */
public class HeartBeatDAOTest extends BaseDAOTest {

	
	@Resource
	private HeartbeatMonitorDAO heartbeatMonitorDAO;
	
	@Test
	public void addHeartbeatMonitor(){
		HeartbeatMonitorDomain domain = new HeartbeatMonitorDomain();
		domain.setDbId("001db");
		domain.setHostId("001H");
		domain.setId("001");
		domain.setMonitorStatus(1);
		heartbeatMonitorDAO.addHeartbeatMonitor(domain);
	}
}
