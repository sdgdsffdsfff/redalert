package test.com.tyyd.ywpt.dao.configration.schedule;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import test.com.tyyd.ywpt.dao.BaseDAOTest;

import com.tyyd.ywpt.dao.configration.schedule.QuotaMonitorConfigDAO;
import com.tyyd.ywpt.dao.configration.schedule.dataobject.QuotaScheduleconfigDomain;

public class QuotaScheduleconfigDAOTest extends BaseDAOTest {

	@Resource
	private QuotaMonitorConfigDAO quotaMonitorConfigDAO;
	
	@Test
	public void addQuotaScheduleconfig() {
		for(int i=0;i<10;i++){
			QuotaScheduleconfigDomain domain = new QuotaScheduleconfigDomain();
			domain.setId("001"+i);
			domain.setConfigType(1);
			domain.setMonitorId("001H");
			domain.setQuotaId("001"+i);
			domain.setQuotaName("Mysql datafiles "+i);
			quotaMonitorConfigDAO.addQuotaScheduleconfig(domain);
		}
	}

	@Test
	public void deleteQuotaScheduleconfig() {
		quotaMonitorConfigDAO.deleteQuotaScheduleconfig("0011");
	}


	@Test
	public void listQuotaScheduleconfigByMonitorId() {
		List<QuotaScheduleconfigDomain> list = quotaMonitorConfigDAO.listQuotaScheduleconfigByMonitorId("001H",1);
		for(QuotaScheduleconfigDomain domain : list){
			System.out.println(domain.toString());
		}
	}

}
