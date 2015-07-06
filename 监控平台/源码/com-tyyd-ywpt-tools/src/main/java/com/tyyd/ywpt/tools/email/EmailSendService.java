/**   
* @Title: EmailSendService.java 
* @Package com.tyyd.ywpt.tools.email 
* @Description:  
* @author wangyu   
* @date 2014-8-8 上午10:27:38 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.tools.email;

import com.tyyd.ywpt.tools.email.dataobject.MailRecvMessage;

/**
 * @author wangyu
 *
 */
public interface EmailSendService {

	/**
	 * 邮件发送
	 * @param message
	 */
	public void sendMail(MailRecvMessage message);
}
