package test.com.tyyd.ywpt.dao.core.collect.datafiles;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import test.com.tyyd.ywpt.dao.BaseDAOTest;

import com.tyyd.ywpt.dao.core.collect.datafile.DbTableSpaceDAO;
import com.tyyd.ywpt.dao.core.collect.datafile.dataobject.DbTableSpaceDomain;

public class DbTableSpaceDAOTest extends BaseDAOTest  {

	@Resource
	private DbTableSpaceDAO dbTableSpaceDAO;
	
	@Test
	public void addDbTableSpace() {
		for (int i = 0; i < 10; i++) {
			DbTableSpaceDomain domain = new DbTableSpaceDomain();
			domain.setDbId("002");
			domain.setMaxTbs(4000f);
			domain.setTbsName("tbs_1"+i);
			domain.setUsedTbs(200f);
			domain.setUsePercent(15f);
			dbTableSpaceDAO.addDbTableSpace(domain);
		}
		
	}

	@Test
	public void listDbTableSpace() {
		List<DbTableSpaceDomain> list = dbTableSpaceDAO.listDbTableSpace("001");
		for(DbTableSpaceDomain domain : list){
			System.out.println(domain.toString());
		}
	}

	@Test
	public void deleteDbTableSpace() {
		dbTableSpaceDAO.deleteDbTableSpace("001");
	}

}
