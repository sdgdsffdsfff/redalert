/**   
* @Title: AlertRecordDAOTest.java 
* @Package test.com.tyyd.ywpt.dao.alert 
* @Description:  
* @author wangyu   
* @date 2014-6-19 下午6:11:51 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package test.com.tyyd.ywpt.dao.alert;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import test.com.tyyd.ywpt.dao.BaseDAOTest;

import com.tyyd.ywpt.dao.alert.record.AlertRecordDAO;
import com.tyyd.ywpt.dao.alert.record.dataobject.AlertRecordDomain;
import com.tyyd.ywpt.dao.base.dataobject.PageQuery;

/**
 * @author wangyu
 *
 */
public class AlertRecordDAOTest extends BaseDAOTest {

	@Resource
	private AlertRecordDAO alertRecordDAO;

	@Test
	public void addAlertRecord() {
		for(int i=0;i<1;i++){
			AlertRecordDomain domain = new AlertRecordDomain();
			domain.setMonitorId("H003"+i);
			domain.setQuotaId("0001Q"+i);
			domain.setQuotaName("测试"+i);
			domain.setQuotaValue(11.0f+i);
			domain.setSysType(1);
			domain.setTitle("预警"+i);
			domain.setContent("<xml>预警</xml>");
			
			
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, -1);
			domain.setGmtCreated(c.getTime());
			domain.setGmtCreated(null);
			alertRecordDAO.addAlertRecord(domain);
		}
	}

	@Test
	public void updateAlertRecordTaskStatus() {
		alertRecordDAO.updateAlertRecordTaskStatus(1l);
	}

	@Test
	public void listAlertRecord() {
		PageQuery<List<AlertRecordDomain>> pageQuery = new PageQuery<List<AlertRecordDomain>>();
		pageQuery.setCurrentPage(1);
		pageQuery.setPageSize(10);
		String startQueryDate = "2014-06-19 11:45:48";
		String endQueryDate = "2014-06-19 21:45:48";
		String hostId = "H001";
		String dbId = "db001";
		Integer sysType = 1;
		Integer isCompleted = null;
		pageQuery = alertRecordDAO.listAlertRecord(pageQuery, startQueryDate, endQueryDate, hostId, dbId, sysType,isCompleted);
		for(AlertRecordDomain domain : pageQuery.getRecords()){
			System.out.println(domain.getTitle()+","+domain.getContent()+","+domain.getIsComplete());
		}
	}

	@Test
	public void getAlertRecordById() {
		AlertRecordDomain domain = alertRecordDAO.getAlertRecordById("001");
		System.out.println(domain.getTitle()+","+domain.getContent()+","+domain.getIsComplete());
	}

	@Test
	public void getUnCompleteTaskLimit() {
		List<AlertRecordDomain> list = alertRecordDAO.getUnCompleteTaskLimit(10);
		for(AlertRecordDomain domain : list){
			System.out.println(domain.getTitle()+","+domain.getContent()+","+domain.getIsComplete());
		}
	}
	
	
	
}
