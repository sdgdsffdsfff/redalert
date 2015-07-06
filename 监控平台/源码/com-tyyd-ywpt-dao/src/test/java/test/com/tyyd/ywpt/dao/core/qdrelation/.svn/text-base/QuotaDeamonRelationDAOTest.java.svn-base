package test.com.tyyd.ywpt.dao.core.qdrelation;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import test.com.tyyd.ywpt.dao.BaseDAOTest;

import com.tyyd.ywpt.dao.base.dataobject.PageQuery;
import com.tyyd.ywpt.dao.core.schedule.qdrelation.QuotaDaemonRelationDAO;
import com.tyyd.ywpt.dao.core.schedule.qdrelation.dataobject.QuotaDaemonRelationDomain;

public class QuotaDeamonRelationDAOTest extends BaseDAOTest  {

	@Resource
	private QuotaDaemonRelationDAO quotaDaemonRelationDAO;
	
	
	@Test
	public void addQuotaDaemonRelation() {
		for(int i=0;i<100;i++){
			QuotaDaemonRelationDomain domain = new QuotaDaemonRelationDomain();
			domain.setDaemonType(1+i);
			domain.setQuotaId("001"+i);
			quotaDaemonRelationDAO.addQuotaDaemonRelation(domain);
		}
		
	}

	@Test
	public void deleteQuotaDaemonRelation() {
		QuotaDaemonRelationDomain domain = new QuotaDaemonRelationDomain();
		domain.setDaemonType(1);
		domain.setQuotaId("0010");
		quotaDaemonRelationDAO.deleteQuotaDaemonRelation(domain);
	}

	@Test
	public void listQuotaDaemonRelation() {
		PageQuery<List<QuotaDaemonRelationDomain>> pageQuery = new PageQuery<List<QuotaDaemonRelationDomain>>();
		pageQuery.setCurrentPage(2);
		pageQuery.setPageSize(10);
		pageQuery = quotaDaemonRelationDAO.listQuotaDaemonRelation(pageQuery);
		for(QuotaDaemonRelationDomain domain : pageQuery.getRecords()){
			System.out.println(domain);
		}
	}

}
