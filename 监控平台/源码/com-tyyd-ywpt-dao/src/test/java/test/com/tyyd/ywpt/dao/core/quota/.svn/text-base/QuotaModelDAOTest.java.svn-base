package test.com.tyyd.ywpt.dao.core.quota;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import test.com.tyyd.ywpt.dao.BaseDAOTest;

import com.tyyd.ywpt.dao.base.dataobject.PageQuery;
import com.tyyd.ywpt.dao.core.quota.QuotaModelDAO;
import com.tyyd.ywpt.dao.core.quota.dataobject.QuotaModelDomain;

public class QuotaModelDAOTest extends BaseDAOTest  {

	@Resource
	private QuotaModelDAO quotaModelDAO;
	
	@Test
	public void addQuotaModel() {
		for(int i=0;i<100;i++){
			QuotaModelDomain domain = new QuotaModelDomain();
			domain.setId("001"+i);
			domain.setQuotaCategory(1);
			domain.setQuotaMetric("M"+i);
			domain.setSysCategory(1);
			domain.setRemark("MySQL tbs"+i);
			domain.setQuotaName("QQ"+i);
			quotaModelDAO.addQuotaModel(domain);
		}
	}

	@Test
	public void deleteQuotaModel() {
		quotaModelDAO.deleteQuotaModel("001");
	}

	@Test
	public void updateQuotaModel() {
		QuotaModelDomain domain = new QuotaModelDomain();
		domain.setId("0010");
		domain.setQuotaCategory(1);
		domain.setQuotaMetric("M");
		domain.setSysCategory(1);
		domain.setRemark("MySQL tbs");
		domain.setQuotaName("QQ");
		quotaModelDAO.updateQuotaModel(domain);
	}

	@Test
	public void getQuotaModelById() {
		QuotaModelDomain domain = quotaModelDAO.getQuotaModelById("001");
		System.out.println(domain.toString());
	}

	@Test
	public void listQuotaModel() {
		PageQuery<List<QuotaModelDomain>> pageQuery = new PageQuery<List<QuotaModelDomain>>();
		pageQuery.setCurrentPage(1);
		pageQuery.setPageSize(10);
		pageQuery = quotaModelDAO.listQuotaModel(pageQuery, 1, 1);
		for(QuotaModelDomain domain : pageQuery.getRecords()){
			System.out.println(domain.toString());
		}
	}

}
