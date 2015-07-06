/**   
* @Title: PhoneTest.java 
* @Package com.schedule.tools.phone 
* @Description:  
* @author wangyu   
* @date 2014-8-11 下午2:24:30 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.shedule.biz.phone;

import javax.annotation.Resource;

import org.junit.Test;

import com.shedule.biz.BaseScheduleBizTest;
import com.tyyd.ywpt.tools.phone.SmsMessageUtils;

/**
 * @author wangyu
 *
 */
public class PhoneTest extends BaseScheduleBizTest{

	@Resource
	private SmsMessageUtils smsMessageUtils;
	
	@Test
	public void testSend(){
		smsMessageUtils.sendMessage(new String[]{"","15382328086"}, "指标ID:1,指标名:cpu_user超过了预警阈值60%,当前值为 61.0% 小机_18 [192.168.10.18] 2015-01-04 03:04:51");
	}

}
