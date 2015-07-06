package test.com.tyyd.ywpt.dao.core.collect.disk;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import test.com.tyyd.ywpt.dao.BaseDAOTest;

import com.tyyd.ywpt.dao.core.collect.disk.DiskMonitorDAO;
import com.tyyd.ywpt.dao.core.collect.disk.dataobject.DiskMonitorDomain;

public class DiskMonitorDAOTest extends BaseDAOTest {

	@Resource
	private DiskMonitorDAO diskMonitorDAO;
	
	@Test
	public void addDiskMonitor(){
		for(int i=0;i<10;i++){
			DiskMonitorDomain domain = new DiskMonitorDomain();
			domain.setDiskName("/root"+i);
			domain.setHostId("002");
			domain.setRemain(100f);
			domain.setUsed(100f);
			domain.setUsedPercent(50f);
			diskMonitorDAO.addDiskMonitor(domain);
		}
	}
	
	@Test
	public void deleteDiskMonitor(){
		diskMonitorDAO.deleteDiskMonitor("001");
	}
	
	@Test
	public void listDiskMonitor(){
		List<DiskMonitorDomain> list = diskMonitorDAO.listDiskMonitor("001");
		for(DiskMonitorDomain domain : list){
			System.out.println(domain.toString());
		}
	}
}
