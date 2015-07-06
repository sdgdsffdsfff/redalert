/**   
* @Title: DbDatafilesDAOTest.java 
* @Package test.com.tyyd.ywpt.dao.core.collect.datafiles 
* @Description:  
* @author wangyu   
* @date 2014-6-23 下午2:24:28 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package test.com.tyyd.ywpt.dao.core.collect.datafiles;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import test.com.tyyd.ywpt.dao.BaseDAOTest;

import com.tyyd.ywpt.dao.core.collect.datafile.DbDatafilesDAO;
import com.tyyd.ywpt.dao.core.collect.datafile.dataobject.DbDatafilesDomain;

/**
 * @author wangyu
 *
 */
public class DbDatafilesDAOTest extends BaseDAOTest {

	@Resource
	private DbDatafilesDAO dbDatafilesDAO;
	
	@Test
	public void addDatafile() {
		for (int i = 0; i < 10; i++) {
			DbDatafilesDomain domain = new DbDatafilesDomain();
			domain.setId("0011"+i);
			domain.setDbId("001");
			domain.setMaxTbs(4000f);
			domain.setTbsName("tbs_1");
			domain.setUsedTbs(200f);
			domain.setUsePercent(15f);
			dbDatafilesDAO.addDatafile(domain);
		}
	}
	
	@Test
	public void listDbDatafiles(){
		List<DbDatafilesDomain> list = dbDatafilesDAO.listDbDatafiles("2014-06-12 12:12:12", "2014-06-28 12:12:12", "tbs_1", "001");
		for(DbDatafilesDomain domain : list){
			System.out.println(domain.toString());
		}
		
		
	}
	
}
