/**   
* @Title: HostQuotaCollectDayDAOTest.java 
* @Package test.com.tyyd.ywpt.dao.core.collect.even 
* @Description:  
* @author wangyu   
* @date 2014-6-25 上午10:29:21 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package test.com.tyyd.ywpt.dao.core.collect.even;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import test.com.tyyd.ywpt.dao.BaseDAOTest;

import com.tyyd.ywpt.dao.core.collect.even.host.HostQuotaCollectDayDAO;
import com.tyyd.ywpt.dao.core.collect.even.host.dataobject.HostQuotaCollectDayDomain;

/**
 * @author wangyu
 *
 */
public class HostQuotaCollectDayDAOTest extends BaseDAOTest {

	@Resource
	private HostQuotaCollectDayDAO hostQuotaCollectDayDAO;
	
	@Test
	public void addHostQuotaCollectDay() {
		for(int i=0;i<100;i++){
			HostQuotaCollectDayDomain domain = new HostQuotaCollectDayDomain();
			domain.setHostId("001H");
			domain.setId("004"+i);
			domain.setQuotaId("Quota"+i);
			domain.setQuotaName("quname"+i);
			domain.setQuotaValue(1f);
			domain.setLastQuotaValue(2f);
			hostQuotaCollectDayDAO.addHostQuotaCollectDay(domain);
		}
	}

	@Test
	public void moveHostQuotaCollectDay() {

	}

	@Test
	public void listHostQuotaCollectDay() {
		List<HostQuotaCollectDayDomain> list = hostQuotaCollectDayDAO.listHostQuotaCollectDay("2014-06-25 09:45:48", "2014-06-25 21:45:48", "001H", "Quota0");
		for(HostQuotaCollectDayDomain domain : list){
			System.out.println(domain);
		}
		
	}

	@Test
	public void getLastHostQuotaCollect() {
		HostQuotaCollectDayDomain domain = hostQuotaCollectDayDAO.getLastHostQuotaCollect("001H", "Quota0");
		System.out.println(domain);
	}

}
