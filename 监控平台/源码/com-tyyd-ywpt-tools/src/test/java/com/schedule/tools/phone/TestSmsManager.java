/**   
* @Title: TestSmsManager.java 
* @Package com.schedule.tools.phone 
* @Description:  
* @author wangyu   
* @date 2014-12-26 上午11:01:22 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.schedule.tools.phone;

import javax.annotation.Resource;

import org.junit.Test;

import com.schedule.tools.BaseToolsTest;
import com.tyyd.ywpt.tools.email.dataobject.MailRecvMessage;
import com.tyyd.ywpt.tools.phone.SmsMessageUtils;

/**
 * @author wangyu
 *
 */
public class TestSmsManager extends BaseToolsTest{

	
	@Resource
	private SmsMessageUtils smsMessageUtils;
	
	@Test
	public void textSend(){
		
		smsMessageUtils.sendMessage(new String[]{"15382328086"}, "测试");
	}
}
