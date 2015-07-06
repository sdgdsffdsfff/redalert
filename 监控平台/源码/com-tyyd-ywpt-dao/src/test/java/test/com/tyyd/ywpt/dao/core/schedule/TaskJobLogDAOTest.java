package test.com.tyyd.ywpt.dao.core.schedule;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import test.com.tyyd.ywpt.dao.BaseDAOTest;

import com.tyyd.ywpt.dao.base.dataobject.PageQuery;
import com.tyyd.ywpt.dao.core.schedule.tasklog.TaskJobLogDAO;
import com.tyyd.ywpt.dao.core.schedule.tasklog.dataobject.TaskJobLogDomain;

public class TaskJobLogDAOTest extends BaseDAOTest {

	@Resource
	private TaskJobLogDAO taskJobLogDAO;
	
	
	@Test
	public void addTaskJobLog() {
		for(int i=0;i<10;i++){
			TaskJobLogDomain domain = new TaskJobLogDomain();
			domain.setBeanId("bean_"+i);
			domain.setMonitorId("M1234"+i);
			domain.setJobId("5");
			domain.setServerId("srv01");
			domain.setSysType(1);
			taskJobLogDAO.addTaskJobLog(domain);
		}
		
	}

	@Test
	public void listTaskJobLog() {
		PageQuery<List<TaskJobLogDomain>> pageQuery = new PageQuery<List<TaskJobLogDomain>>();
		pageQuery.setCurrentPage(1);
		pageQuery.setPageSize(10);
		pageQuery = taskJobLogDAO.listTaskJobLog(pageQuery,"M12341", 1, "bean_1", "5");
		for(TaskJobLogDomain domain : pageQuery.getRecords()){
			System.out.println(domain);
		}
	}

}
