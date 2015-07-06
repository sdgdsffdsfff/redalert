package test.com.tyyd.ywpt.dao.alert;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import test.com.tyyd.ywpt.dao.BaseDAOTest;

import com.tyyd.ywpt.dao.alert.record.AlertNotifyRecordDAO;
import com.tyyd.ywpt.dao.alert.record.dataobject.AlertNotifyRecordDomain;

public class AlertNotifyRecordDAOTest extends BaseDAOTest {

	@Resource
	private AlertNotifyRecordDAO alertNotifyRecordDAO;
	
	@Test
	public void addAlertNotifyRecord() {
		for(int i=0;i<10;i++){
			AlertNotifyRecordDomain domain = new AlertNotifyRecordDomain();
			domain.setAlertRecordId("001a");
			domain.setUserId("001u"+i);
			domain.setId("001"+i);
			domain.setMobileMails("xx@163.com"+i);
			domain.setReceviceMails("xx@189.cn"+i);
			alertNotifyRecordDAO.addAlertNotifyRecord(domain);
		}
	}

	@Test
	public void updateAlertNotifyRecordById() {
		alertNotifyRecordDAO.updateAlertNotifyRecordById("001");
	}

	@Test
	public void queryAlertNotifyRecordByAlertId() {
		List<AlertNotifyRecordDomain> list = alertNotifyRecordDAO.queryAlertNotifyRecordByAlertId("001a");
		for(AlertNotifyRecordDomain domain : list){
			System.out.println(domain.getUserId()+","+domain.getMobileMails()+","+domain.getIsComplete());
		}
	}

}
