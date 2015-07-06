/**   
* @Title: HisDbaTest.java 
* @Package com.shedule.biz.his 
* @Description:  
* @author wangyu   
* @date 2015-2-4 下午3:01:54 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.shedule.biz.his;

import javax.annotation.Resource;

import org.junit.Test;

import com.shedule.biz.BaseScheduleBizTest;
import com.tyyd.ywpt.schedule.his.dba.DBAHisTablesScheduleManager;

/**
 * @author wangyu
 *
 */
public class HisDbaTest extends BaseScheduleBizTest{

	@Resource
	private DBAHisTablesScheduleManager dBAHisTablesScheduleManager;
	
	
	@Test
	public void testDoTask(){
		dBAHisTablesScheduleManager.doClearHisDataTask();
	}
	
	
}
