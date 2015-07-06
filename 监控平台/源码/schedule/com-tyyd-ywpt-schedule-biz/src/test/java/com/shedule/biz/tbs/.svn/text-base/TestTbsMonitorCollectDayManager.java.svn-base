/**   
* @Title: TestDiskMonitorCollectDayManager.java 
* @Package com.shedule.biz.disk 
* @Description:  
* @author wangyu   
* @date 2015-5-29 下午3:13:25 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.shedule.biz.tbs;

import javax.annotation.Resource;

import org.junit.Test;

import com.shedule.biz.BaseScheduleBizTest;
import com.tyyd.ywpt.schedule.tbs.DbTableSpaceCollectDayManager;
import com.tyyd.ywpt.schedule.tbs.TbsMonitorUpdateManager;

/**
 * @author wangyu
 *
 */
public class TestTbsMonitorCollectDayManager extends BaseScheduleBizTest{

	@Resource
	private DbTableSpaceCollectDayManager dbTableSpaceCollectDayManager;
	
	@Resource
	private TbsMonitorUpdateManager tbsMonitorUpdateManager;
	
	@Test
	public void doSingleTask(){
		tbsMonitorUpdateManager.doSingleTask("11");
	}
	
	
	@Test
	public void doUpdateAllTask(){
		tbsMonitorUpdateManager.doTask();
	}
	
	
	@Test
	public void testdoDataBaseTask(){
		dbTableSpaceCollectDayManager.doDataBaseTask("11");
	}
	
	@Test
	public void testdoTask(){
		dbTableSpaceCollectDayManager.doTask();
	}
}
