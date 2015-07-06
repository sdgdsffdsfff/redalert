package test.com.tyyd.ywpt.dao.configration.threshold;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import test.com.tyyd.ywpt.dao.BaseDAOTest;

import com.tyyd.ywpt.dao.configration.threshold.ThresholdDAO;
import com.tyyd.ywpt.dao.configration.threshold.dataobject.ThresholdDomain;

public class ThresholdDAOTest extends BaseDAOTest  {

	@Resource
	private ThresholdDAO thresholdDAO;
	
	@Test
	public void addThreshold() {
		for(int i=10;i<15;i++){
			ThresholdDomain domain = new ThresholdDomain();
			domain.setConfigType(2);
			domain.setId("001T"+i);
			domain.setLastQuotaValue(0.01f);
			domain.setMonitorId("001MF");
			domain.setQuotaId("001Q"+i);
			domain.setQuotaMetric("metric"+i);
			domain.setQuotaValue(1.0f);
			thresholdDAO.addThreshold(domain);
		}
	}

	@Test
	public void updateThresholdInfo() {
		ThresholdDomain domain = new ThresholdDomain();
		domain.setConfigType(1);
		domain.setId("001T1");
		domain.setLastQuotaValue(0.01f);
		domain.setMonitorId("001M");
		domain.setQuotaId("001Q");
		domain.setQuotaMetric("metric");
		domain.setQuotaValue(1.0f);
		thresholdDAO.updateThresholdInfo(domain);
	}

	@Test
	public void deleteThreshold() {
		thresholdDAO.deleteThreshold("001T1");
	}

	@Test
	public void getThresholdById() {
		ThresholdDomain domain = thresholdDAO.getThresholdById("001T0");
		System.out.println(domain.toString());
	}

	@Test
	public void listThreshold() {
		List<ThresholdDomain> list = thresholdDAO.listThreshold("001M", 1);
		for(ThresholdDomain domain : list){
			System.out.println(domain.toString());
		}
	}

}
