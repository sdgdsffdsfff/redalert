/**   
 * @Title: EmailSendServiceImpl.java 
 * @Package com.tyyd.ywpt.tools.email.impl 
 * @Description:  
 * @author wangyu   
 * @date 2014-8-8 上午10:28:42 
 * @CopyRight 天翼阅读
 * @version V1.0   
 */
package com.tyyd.ywpt.tools.email.impl;


import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.tyyd.ywpt.tools.email.EmailSendService;
import com.tyyd.ywpt.tools.email.dataobject.MailHeaderCheckResult;
import com.tyyd.ywpt.tools.email.dataobject.MailRecvMessage;

/**
 * @author wangyu
 * 文本邮件，可用来做告警
 */
public class TextEmailSendServiceImpl implements EmailSendService {

	public static final Logger LOGGER = Logger.getLogger(TextEmailSendServiceImpl.class);  
	
	private JavaMailSender mailSender;
	
	private String from;
	
	@Override
	public void sendMail(MailRecvMessage message) {
		MailHeaderCheckResult result = checkMailHeader(message);
		if (!result.isSuccess()) {
			throw new MailSendException(result.getErrorContent());
		}
		SimpleMailMessage mail = new SimpleMailMessage();
		try {
			mail.setFrom(from);
			mail.setTo(message.getRecv());// 接受者
			mail.setCc(message.getCc()); //抄送
			mail.setSubject(message.getTitle());// 主题
			mail.setText(message.getContent());// 邮件内容
			mailSender.send(mail);
		} catch (Exception e) {
			LOGGER.error("邮件发送失败", e);
		}
	}

	private MailHeaderCheckResult checkMailHeader(MailRecvMessage message) {
		MailHeaderCheckResult result = new MailHeaderCheckResult();
		result.setSuccess(false);
		if (message == null) {
			result.setErrorContent("检查邮件发送失败,传入MESSAGE是空对象");
			return result;
		}

		if (StringUtils.isBlank(message.getTitle())) {
			result.setErrorContent("邮件标题必填");
			return result;
		}

		if (StringUtils.isBlank(message.getContent())) {
			result.setErrorContent("邮件内容必填");
			return result;
		}

		if (message.getRecv() == null || message.getRecv().length == 0) {
			result.setErrorContent("接收人必填");
			return result;
		}

		result.setSuccess(true);
		return result;
	}

	/**
	 * @return the mailSender
	 */
	public JavaMailSender getMailSender() {
		return mailSender;
	}

	/**
	 * @param mailSender the mailSender to set
	 */
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	
	
	

}
