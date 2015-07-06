/**   
* @Title: TextMailSenderTest.java 
* @Package com.schedule.tools.mail 
* @Description:  
* @author wangyu   
* @date 2014-8-8 上午11:42:10 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.schedule.tools.mail;

import javax.annotation.Resource;

import org.junit.Test;

import com.schedule.tools.BaseToolsTest;
import com.tyyd.ywpt.tools.email.EmailSendService;
import com.tyyd.ywpt.tools.email.dataobject.MailRecvMessage;

/**
 * @author wangyu
 *
 */
public class TextMailSenderTest extends BaseToolsTest{

	@Resource
	private EmailSendService emailSendService;
	
	@Test
	public void textSend(){
		
		for(int i=0;i<1000;i++){
			MailRecvMessage message = new MailRecvMessage();
			message.setTitle("测试"+i);
			message.setContent("测试"+i+",内容"+i);
			message.setRecv(new String[]{"newshake@163.com","13355718137@189.cn","zf_zhouf@189.cn","napelss@gmail.com"});
			emailSendService.sendMail(message);
		}
	}
}
