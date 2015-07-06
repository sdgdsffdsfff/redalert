/**   
* @Title: HostDirAlertConfigDAOTest.java 
* @Package test.com.tyyd.ywpt.dao.configration.hostdiralert 
* @Description:  
* @author wangyu   
* @date 2014-6-20 下午5:46:33 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package test.com.tyyd.ywpt.dao.configration.hostdiralert;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import test.com.tyyd.ywpt.dao.BaseDAOTest;

import com.tyyd.ywpt.dao.base.dataobject.PageQuery;
import com.tyyd.ywpt.dao.configration.hostdiralert.HostDirAlertConfigDAO;
import com.tyyd.ywpt.dao.configration.hostdiralert.dataobject.HostDirAlertConfigDomain;

/**
 * @author wangyu
 *
 */
public class HostDirAlertConfigDAOTest extends BaseDAOTest {

	@Resource
	private HostDirAlertConfigDAO hostDirAlertConfigDAO;
	
	@Test
	public void addHostDirAlertConfig() {
//		for(int i=0;i<100;i++){
//			HostDirAlertConfigDomain domain = new HostDirAlertConfigDomain();
//			domain.setId("001"+i);
//			domain.setHostId("host01");
//			domain.setHostDir("/root/abbc.log"+i);
//			domain.setKeyword("abc"+i);
//			hostDirAlertConfigDAO.addHostDirAlertConfig(domain);
//		}
	}

	@Test
	public void delHostDirAlertConfig() {
		hostDirAlertConfigDAO.delHostDirAlertConfig("001");
	}

	@Test
	public void getHostDirAlertConfig() {
		HostDirAlertConfigDomain domain = hostDirAlertConfigDAO.getHostDirAlertConfig("0010");
		System.out.println(domain.toString());
	}

	@Test
	public void updateHostDirAlertConfig() {
		HostDirAlertConfigDomain domain = new HostDirAlertConfigDomain();
		domain.setId("0010");
		domain.setHostDir("/root/abbc.log");
		domain.setKeyword("abc");
		hostDirAlertConfigDAO.updateHostDirAlertConfig(domain);
	}

	@Test
	public void listHostDirAlertConfig() {
		PageQuery<List<HostDirAlertConfigDomain>> pageQuery = new PageQuery<List<HostDirAlertConfigDomain>>();
		pageQuery.setCurrentPage(2);
		pageQuery.setPageSize(5);
		pageQuery = hostDirAlertConfigDAO.listHostDirAlertConfig(pageQuery, "1","host01");
		for(HostDirAlertConfigDomain domain : pageQuery.getRecords()){
			System.out.println(domain.toString());
		}
		
	}

	
}
