/**   
* @Title: DbRelationDAOTest.java 
* @Package test.com.tyyd.ywpt.dao.configration.relation 
* @Description:  
* @author wangyu   
* @date 2014-6-21 下午7:52:09 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package test.com.tyyd.ywpt.dao.configration.relation;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import test.com.tyyd.ywpt.dao.BaseDAOTest;

import com.tyyd.ywpt.dao.configration.relation.DbRelationDAO;
import com.tyyd.ywpt.dao.configration.relation.dataobject.DbRelationDomain;

/**
 * @author wangyu
 *
 */
public class DbRelationDAOTest extends BaseDAOTest {

	@Resource
	private DbRelationDAO dbRelationDAO;
	
	
	@Test
	public void addDataBaseRelation() {
		for(int i=5;i<10;i++){
			DbRelationDomain domain = new DbRelationDomain();
			domain.setBatchId("B_112311");
			domain.setDbId("001DB0");
			domain.setHostId("001H0");
			domain.setId("002"+i);
			domain.setIsMaster("0");
			domain.setIsReadonly("0");
			domain.setRelationType(1);
			dbRelationDAO.addDataBaseRelation(domain);
		}
	}

	@Test
	public void listDbRelation() {
		List<DbRelationDomain> list = dbRelationDAO.listDbRelation("B_1123");
		for(DbRelationDomain domain : list){
			System.out.println(domain.toString());
		}
	}

	@Test
	public void deleteDataBaseRelation() {
		dbRelationDAO.deleteDataBaseRelation("B_1123");
	}

}
