/**   
* @Title: DbConfigDAOTest.java 
* @Package test.com.tyyd.ywpt.dao.configration.db 
* @Description:  
* @author wangyu   
* @date 2014-6-20 下午4:28:23 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package test.com.tyyd.ywpt.dao.configration.db;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import test.com.tyyd.ywpt.dao.BaseDAOTest;

import com.tyyd.ywpt.dao.base.dataobject.PageQuery;
import com.tyyd.ywpt.dao.configration.db.DbConfigDAO;
import com.tyyd.ywpt.dao.configration.db.dataobject.DbConfigDomain;

/**
 * @author wangyu
 *
 */
public class DbConfigDAOTest extends BaseDAOTest   {

	@Resource
	private DbConfigDAO dbConfigDAO;
	
	@Test
	public void addDataBaseConfig() {
		int i=1000;
			DbConfigDomain domain = new DbConfigDomain();
			domain.setDbId("001DB"+i);
			domain.setHostId("001H0");
			domain.setDbName("db_name"+i);
			domain.setDbPasswd("pass");
			domain.setDbType(1);
			domain.setDbUserName("system"+i);
			domain.setNickName("dbNick"+i);
			domain.setPort("1521"+i);
			domain.setServerId("srv");
			domain.setSid("ora");
			domain.setGlobalScheduleConf("0 0 0 0 0 0 *");
			dbConfigDAO.addDataBaseConfig(domain);
	}

	@Test
	public void delDataBaseConfig() {
		dbConfigDAO.delDataBaseConfig("001DB");
	}

	@Test
	public void getDataBaseConfig() {
		DbConfigDomain domain = dbConfigDAO.getDataBaseConfig("001DB1000");
		System.out.println(domain.toString());
	}

	@Test
	public void listDataBaseConfig() {
		PageQuery<List<DbConfigDomain>> pageQuery = new PageQuery<List<DbConfigDomain>>();
		pageQuery.setCurrentPage(2);
		pageQuery.setPageSize(5);
		pageQuery = dbConfigDAO.listDataBaseConfig(pageQuery, "localhost");
		for(DbConfigDomain domain : pageQuery.getRecords()){
			System.out.println(domain.toString());
		}
	}

	
	@Test
	public void updateDataBaseConfig() {
		DbConfigDomain domain = new DbConfigDomain();
		domain.setDbId("001DB26");
		domain.setHostId("001H0");
		domain.setDbName("db_name");
		domain.setDbPasswd("pass");
		domain.setDbType(1);
		domain.setDbUserName("system");
		domain.setNickName("dbNick");
		domain.setPort("1521");
		domain.setServerId("srv");
		domain.setSid("ora");
		
		dbConfigDAO.updateDataBaseConfig(domain);
	}
	
	@Test
	public void updateDataBaseMonitorStatus(){
		DbConfigDomain domain = new DbConfigDomain();
		domain.setLastHeartBeatStatus(2);
		domain.setDbId("11");
		dbConfigDAO.updateDataBaseMonitorStatus(domain);
	}
}
