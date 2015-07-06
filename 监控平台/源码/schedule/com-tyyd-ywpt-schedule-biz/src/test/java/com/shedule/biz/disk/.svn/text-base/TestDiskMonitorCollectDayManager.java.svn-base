/**   
* @Title: TestDiskMonitorCollectDayManager.java 
* @Package com.shedule.biz.disk 
* @Description:  
* @author wangyu   
* @date 2015-5-29 下午3:13:25 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.shedule.biz.disk;

import javax.annotation.Resource;

import org.junit.Test;

import com.shedule.biz.BaseScheduleBizTest;
import com.tyyd.ywpt.schedule.disk.DiskMonitorCollectDayManager;
import com.tyyd.ywpt.schedule.disk.DiskMonitorUpdateManager;

/**
 * @author wangyu
 *
 */
public class TestDiskMonitorCollectDayManager extends BaseScheduleBizTest{

	@Resource
	private DiskMonitorCollectDayManager diskMonitorCollectDayManager;
	
	
	@Resource
	private DiskMonitorUpdateManager diskMonitorUpdateManager;
	
	@Test
	public void testSingleUpdate(){
		diskMonitorUpdateManager.doSingleTask("10");
	}
	
	@Test
	public void testAllDiskUpdate(){
		diskMonitorUpdateManager.doTask();
	}
	
	@Test
	public void testDoHostTask(){
		diskMonitorCollectDayManager.doHostTask("10");
	}
	
	@Test
	public void testDoTask(){
		diskMonitorCollectDayManager.doTask();
	}
	
	
	
}
