/**   
* @Title: OracleQuotaCollectDayDAOTest.java 
* @Package test.com.tyyd.ywpt.dao.core.collect.even 
* @Description:  
* @author wangyu   
* @date 2014-6-25 下午12:55:53 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package test.com.tyyd.ywpt.dao.core.collect.even;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import test.com.tyyd.ywpt.dao.BaseDAOTest;

import com.tyyd.ywpt.dao.core.collect.even.oracle.OracleQuotaCollectDayDAO;
import com.tyyd.ywpt.dao.core.collect.even.oracle.dataobject.OracleQuotaCollectDayDomain;

/**
 * @author wangyu
 *
 */
public class OracleQuotaCollectDayDAOTest extends BaseDAOTest{

	@Resource
	private OracleQuotaCollectDayDAO oracleQuotaCollectDayDAO;
	
	
	@Test
	public void addOracleQuotaCollectDay() {
		for(int i=0;i<100;i++){
			OracleQuotaCollectDayDomain domain = new OracleQuotaCollectDayDomain();
			domain.setHostId("001H");
			domain.setId("006"+i);
			domain.setQuotaId("Quota"+i);
			domain.setQuotaName("quname"+i);
			domain.setQuotaValue(1f);
			domain.setLastQuotaValue(2f);
			domain.setDbId("001DB");
			oracleQuotaCollectDayDAO.addOracleQuotaCollectDay(domain);
		}
	}
	
	

	@Test
	public void moveOracleQuotaCollectDay() {
		
	}

	@Test
	public void listOracleQuotaCollectDay() {
		List<OracleQuotaCollectDayDomain> list = oracleQuotaCollectDayDAO.listOracleQuotaCollectDay("2014-06-25 09:45:48", "2014-06-25 21:45:48", "001H","001DB", "Quota0");
		for(OracleQuotaCollectDayDomain domain : list){
			System.out.println(domain);
		}
	}

	@Test
	public void getLastOracleQuotaCollectDay() {
		OracleQuotaCollectDayDomain domain = oracleQuotaCollectDayDAO.getLastOracleQuotaCollectDay("001H","001DB", "Quota0");
		System.out.println(domain);
	}
}
