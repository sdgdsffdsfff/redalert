/**   
* @Title: MysqlQuotaCollectDayDAOTest.java 
* @Package test.com.tyyd.ywpt.dao.core.collect.even 
* @Description:  
* @author wangyu   
* @date 2014-6-25 上午11:34:27 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package test.com.tyyd.ywpt.dao.core.collect.even;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import test.com.tyyd.ywpt.dao.BaseDAOTest;

import com.tyyd.ywpt.dao.core.collect.even.mysql.MysqlQuotaCollectDayDAO;
import com.tyyd.ywpt.dao.core.collect.even.mysql.dataobject.MysqlQuotaCollectDayDomain;

/**
 * @author wangyu
 *
 */
public class MysqlQuotaCollectDayDAOTest extends BaseDAOTest  {

	@Resource
	private MysqlQuotaCollectDayDAO mysqlQuotaCollectDayDAO;
	
	@Test
	public void addMysqlQuotaCollectDay() {
		for(int i=0;i<100;i++){
			MysqlQuotaCollectDayDomain domain = new MysqlQuotaCollectDayDomain();
			domain.setHostId("001H");
			domain.setId("003"+i);
			domain.setQuotaId("Quota"+i);
			domain.setQuotaName("quname"+i);
			domain.setQuotaValue(1f);
			domain.setLastQuotaValue(2f);
			domain.setDbId("001DB");
			mysqlQuotaCollectDayDAO.addMysqlQuotaCollectDay(domain);
		}
	}
	
	

	@Test
	public void moveMysqlQuotaCollectDay() {
		
	}

	@Test
	public void listMysqlQuotaCollectDay() {
		List<MysqlQuotaCollectDayDomain> list = mysqlQuotaCollectDayDAO.listMysqlQuotaCollectDay("2014-06-25 09:45:48", "2014-06-25 21:45:48", "001H","001DB", "Quota0");
		for(MysqlQuotaCollectDayDomain domain : list){
			System.out.println(domain);
		}
	}

	@Test
	public void getLastMysqlQuotaCollectDay() {
		MysqlQuotaCollectDayDomain domain = mysqlQuotaCollectDayDAO.getLastMysqlQuotaCollectDay("001H","001DB", "Quota0");
		System.out.println(domain);
	}

}
