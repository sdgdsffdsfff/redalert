/**   
 * @Title: DiskMonitorHisDAOTest.java 
 * @Package test.com.tyyd.ywpt.dao.core.collect.disk 
 * @Description:  
 * @author wangyu   
 * @date 2014-6-23 下午4:49:46 
 * @CopyRight 天翼阅读
 * @version V1.0   
 */
package test.com.tyyd.ywpt.dao.core.collect.disk;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import test.com.tyyd.ywpt.dao.BaseDAOTest;

import com.tyyd.ywpt.dao.core.collect.disk.DiskMonitorHisDAO;
import com.tyyd.ywpt.dao.core.collect.disk.dataobject.DiskMonitorHisDomain;

/**
 * @author wangyu
 * 
 */
public class DiskMonitorHisDAOTest extends BaseDAOTest {

	@Resource
	private DiskMonitorHisDAO diskMonitorHisDAO;

	
	@Test
	public void addDiskMonitorHis() {
		for(int i=0;i<10;i++){
			DiskMonitorHisDomain domain = new DiskMonitorHisDomain();
			domain.setId("0011"+i);
			domain.setDiskName("/proc");
			domain.setHostId("001");
			domain.setRemain(100f);
			domain.setUsed(100f);
			domain.setUsedPercent(50f);
			diskMonitorHisDAO.addDiskMonitorHis(domain);
		}
	}

	
	@Test
	public void listDiskMonitorHis() {
		List<DiskMonitorHisDomain> list = diskMonitorHisDAO.listDiskMonitorHis("2014-06-12 12:12:12", "2014-06-28 12:12:12", "001", "/root");
		for(DiskMonitorHisDomain domain : list){
			System.out.println(domain.toString());
		}
	}
	
	
	
}
