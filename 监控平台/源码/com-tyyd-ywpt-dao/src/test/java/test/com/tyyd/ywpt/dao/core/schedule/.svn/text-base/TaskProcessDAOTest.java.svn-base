package test.com.tyyd.ywpt.dao.core.schedule;

import javax.annotation.Resource;

import org.junit.Test;

import test.com.tyyd.ywpt.dao.BaseDAOTest;

import com.tyyd.ywpt.dao.core.schedule.process.TaskProcessDAO;
import com.tyyd.ywpt.dao.core.schedule.process.dataobject.TaskProcessDomain;

public class TaskProcessDAOTest extends BaseDAOTest {

	@Resource
	private TaskProcessDAO taskProcessDAO;
	
	@Test
	public void addTaskProcess() {
		for(int i=0;i<100;i++){
			TaskProcessDomain domain = new TaskProcessDomain();
			domain.setDaemonType(1+i);
			domain.setDbId("001DB");
			domain.setHostId("001H");
			domain.setId("001"+i);
			domain.setIsComplete("0");
			domain.setIsHeartbeat(1);
			domain.setProcessId("P_xxxx"+i);
			domain.setServerId("srv01");
			domain.setSysType(1);
			taskProcessDAO.addTaskProcess(domain);
		}
		
		
		
	}

	@Test
	public void delTaskProcess() {
		taskProcessDAO.delTaskProcess("001H", "001DB", 1, "P_xxxx0", 1, 0);
	}

	@Test
	public void getTaskProcess() {
		
		TaskProcessDomain domain = taskProcessDAO.getTaskProcess("001H", "001DB", 1, "P_xxxx0", 1, 0);
		System.out.println(domain.toString());
	}

}
