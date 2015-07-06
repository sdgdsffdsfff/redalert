package test.com.tyyd.ywpt.dao.configration.schedule;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import test.com.tyyd.ywpt.dao.BaseDAOTest;

import com.tyyd.ywpt.dao.configration.schedule.DeamonScheduleconfigDAO;
import com.tyyd.ywpt.dao.configration.schedule.dataobject.DeamonScheduleconfigDomain;

public class DeamonScheduleconfigDAOTest extends BaseDAOTest {

	@Resource
	private DeamonScheduleconfigDAO deamonScheduleconfigDAO;
	
	@Test
	public void addDeamonScheduleconfig() {
		for(int i=0;i<10;i++){
			DeamonScheduleconfigDomain domain = new DeamonScheduleconfigDomain();
			domain.setId("002"+i);
			domain.setConfigType(1);
			domain.setMonitorId("001H");
//			domain.setDeamonType(1+i);
//			domain.setDeamonName("Mysql"+i);
			deamonScheduleconfigDAO.addDeamonScheduleconfig(domain);
		}
	}

	@Test
	public void deleteDeamonScheduleconfig() {
		deamonScheduleconfigDAO.deleteDeamonScheduleconfig("0010");
	}

	@Test
	public void getScheduleconfigById() {
		DeamonScheduleconfigDomain domain = deamonScheduleconfigDAO.getScheduleconfigById("0010");
		System.out.println(domain.toString());
	}

	@Test
	public void updateDeamonScheduleconfig() {
		DeamonScheduleconfigDomain domain = new DeamonScheduleconfigDomain();
		domain.setId("0011");
		domain.setConfigType(1);
		domain.setExtCol("ext");
		domain.setMonitorId("001H");
		domain.setPollTimes(5);
		domain.setPollUnit(1);
		domain.setQuartzConf("0 0 0 1/s");
//		domain.setDeamonType(1);
//		domain.setDeamonName("Mysql");
		deamonScheduleconfigDAO.updateDeamonScheduleconfig(domain);
		
	}

	@Test
	public void listDeamonScheduleconfigByMonitorId() {
		List<DeamonScheduleconfigDomain> list = deamonScheduleconfigDAO.listDeamonScheduleconfigByMonitorId("001H",1);
		for(DeamonScheduleconfigDomain domain : list){
			System.out.println(domain.toString());
		}
	}

}
