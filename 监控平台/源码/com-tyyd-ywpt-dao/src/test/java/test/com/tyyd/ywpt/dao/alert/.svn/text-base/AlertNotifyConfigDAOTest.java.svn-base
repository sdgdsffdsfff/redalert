package test.com.tyyd.ywpt.dao.alert;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import test.com.tyyd.ywpt.dao.BaseDAOTest;

import com.tyyd.ywpt.dao.alert.recevie.AlertNotifyConfigDAO;
import com.tyyd.ywpt.dao.alert.recevie.dataobject.AlertNotifyConfigDomain;
import com.tyyd.ywpt.dao.base.dataobject.PageQuery;

public class AlertNotifyConfigDAOTest extends BaseDAOTest  {

	@Resource
	private AlertNotifyConfigDAO alertNotifyConfigDAO;
	
	@Test
	public void addNofiyConfigTest() {
		for(int i=0;i<50;i++){
			AlertNotifyConfigDomain configDomain = new AlertNotifyConfigDomain();
			configDomain.setUserId("001u"+i);
			configDomain.setConfigType(1);
			configDomain.setMonitorId("0001D");
			alertNotifyConfigDAO.addNofiyConfig(configDomain);
		}
	}

	@Test
	public void listAlertNotifyConfig() {
		PageQuery<List<AlertNotifyConfigDomain>> pageQuery = new PageQuery<List<AlertNotifyConfigDomain>>();
		pageQuery.setCurrentPage(3);
		pageQuery.setPageSize(10);
		pageQuery = alertNotifyConfigDAO.listAlertNotifyConfig(pageQuery, "0001M");
		for(AlertNotifyConfigDomain domain : pageQuery.getRecords()){
			System.out.println(domain.getMonitorId()+","+domain.getUserId());
		}
	}

	@Test
	public void delNotifyConfig() {
		alertNotifyConfigDAO.delNotifyConfig("0001M", 1, "001u");
	}

	@Test
	public void getAlertNotifyConfig() {
		AlertNotifyConfigDomain domain = alertNotifyConfigDAO.getAlertNotifyConfig("0001M", 1, "001u");
		System.out.println(domain.getMonitorId()+","+domain.getUserId());
	}

}
