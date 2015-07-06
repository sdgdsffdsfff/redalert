/**   
* @Title: HostDAOTest.java 
* @Package test.com.tyyd.ywpt.dao.configration.host 
* @Description:  
* @author wangyu   
* @date 2014-6-20 下午2:14:30 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package test.com.tyyd.ywpt.dao.configration.host;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.tyyd.ywpt.dao.base.dataobject.PageQuery;
import com.tyyd.ywpt.dao.configration.host.HostConfigDAO;
import com.tyyd.ywpt.dao.configration.host.dataobject.HostConfigDomain;

import test.com.tyyd.ywpt.dao.BaseDAOTest;

/**
 * @author wangyu
 *
 */

public class HostConfigDAOTest extends BaseDAOTest{

	@Resource
	private HostConfigDAO hostConfigDAO;
	
	@Test
	public void addHost() {
		for(int i=0;i<10;i++){
			HostConfigDomain domain = new HostConfigDomain();
			domain.setHostId("001HFh"+i);
			domain.setIdcId(3);
			domain.setIpAddr("172"+i);
			domain.setIsHostCollect("1");
			domain.setServerId("srv01"+i);
			domain.setHostName("172BAE"+i);
			domain.setUserAccount("ROOT");
			domain.setUserPasswd("ROOT123");
			domain.setGlobalScheduleConf("0 0 0 0 0 1/w");
			hostConfigDAO.addHost(domain);
		}
	}

	@Test
	public void delHost() {
		hostConfigDAO.delHost("001H");
	}

	@Test
	public void getHostById() {
		HostConfigDomain domain = hostConfigDAO.getHostById("001H0");
		System.out.println(domain);
	}

	@Test
	public void listHost() {
		PageQuery<List<HostConfigDomain>> pageQuery = new PageQuery<List<HostConfigDomain>>();
		pageQuery.setCurrentPage(2);
		pageQuery.setPageSize(5);
		pageQuery = hostConfigDAO.listHost(pageQuery, "lo", "1", "172");
		for(HostConfigDomain domain : pageQuery.getRecords()){
			System.out.println(domain.getHostId()+","+domain.getHostName()+domain.getIpAddr()+","+domain.getServerId());
		}
	}

	@Test
	public void updateHostInfo() {
		HostConfigDomain domain = new HostConfigDomain();
		domain.setHostId("001H");
		domain.setIdcId(3);
		domain.setIpAddr("172");
		domain.setIsHostCollect("2");
		domain.setServerId("sr-C");
		domain.setHostName("SRC");
		domain.setUserAccount("ROOT1");
		domain.setUserPasswd("ROOT1234");
		domain.setGlobalScheduleConf("1 0 0 0 0 2/w");
		domain.setCpuInfo("cpu");
		domain.setDisckInfo("disk");
		domain.setMemoryInfo("mem");
		domain.setOsInfo("os");
		hostConfigDAO.updateHostInfo(domain);
	}

	@Test
	public void updateHostMonitorStatus(){
		HostConfigDomain domain = new HostConfigDomain();
		domain.setHostId("001H0");
		domain.setLastHeartBeatStatus(1);
		hostConfigDAO.updateHostMonitorStatus(domain);
	}
}
