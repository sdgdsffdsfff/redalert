/**   
* @Title: MonitorInit.java 
* @Package com.tyyd.ywpt.biz.init 
* @Description:  
* @author wangyu   
* @date 2014-7-15 下午4:01:44 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.init;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tyyd.ywpt.biz.BaseBizTest;

/**
 * @author wangyu
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class MonitorInit extends BaseBizTest{

	
	@Test
	public void testInit(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, 1);
		Date targetDate = c.getTime();
		Date now = new Date();
		while(true){
			if(now.getTime() > targetDate.getTime()){
				break;
			}
		}
	}
}
