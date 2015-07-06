package test.com.tyyd.ywpt.dao.server;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import test.com.tyyd.ywpt.dao.BaseDAOTest;

import com.tyyd.ywpt.dao.server.ServerRegDAO;
import com.tyyd.ywpt.dao.server.dataobject.ServerRegDomain;

public class ServerRegDAOTest extends BaseDAOTest  {

	@Resource
	private ServerRegDAO serverRegDAO;
	
	@Test
	public void addServerReg() {
		for(int i=0;i<5;i++){
			ServerRegDomain domain = new ServerRegDomain();
			domain.setId("001"+i);
			domain.setIp("localhost1"+i);
			domain.setPort(8080);
			domain.setServerName("采集1");
			serverRegDAO.addServerReg(domain);
		}
	}

	@Test
	public void updateServerRegInfo() {
		ServerRegDomain domain = new ServerRegDomain();
		domain.setId("001");
		domain.setIp("localhost");
		domain.setPort(9090);
		domain.setServerName("采集");
		serverRegDAO.updateServerRegInfo(domain);
	}

	@Test
	public void deleteServerReg() {
		serverRegDAO.deleteServerReg("001");
	}

	@Test
	public void listServerReg() {
		List<ServerRegDomain> list = serverRegDAO.listServerReg();
		for(ServerRegDomain domain : list){
			System.out.println(domain.toString());
		}
	}

	@Test
	public void getServerRegById() {
		ServerRegDomain domain = serverRegDAO.getServerRegById("001");
		System.out.println(domain.toString());
	}

}
