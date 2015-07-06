/**   
* @Title: NotifyExtractContentManagerImpl.java 
* @Package com.tyyd.ywpt.schedule.notify.impl 
* @Description:  
* @author wangyu   
* @date 2014-8-13 下午4:01:25 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.notify.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tyyd.ywpt.dao.alert.record.AlertNotifyRecordDAO;
import com.tyyd.ywpt.dao.alert.record.AlertRecordDAO;
import com.tyyd.ywpt.dao.alert.record.dataobject.AlertNotifyRecordDomain;
import com.tyyd.ywpt.dao.alert.record.dataobject.AlertRecordDomain;
import com.tyyd.ywpt.schedule.alarm.dataobject.AlarmNotifyTypeEnum;
import com.tyyd.ywpt.schedule.notify.AlarmUserInfoManager;
import com.tyyd.ywpt.schedule.notify.NotifyExtractContentManager;
import com.tyyd.ywpt.schedule.notify.dataobject.AlarmUserDTO;
import com.tyyd.ywpt.tools.email.EmailSendService;
import com.tyyd.ywpt.tools.email.dataobject.MailRecvMessage;
import com.tyyd.ywpt.tools.phone.SmsMessageUtils;

/**
 * @author wangyu
 */
public class NotifyExtractContentManagerImpl implements
		NotifyExtractContentManager {

	public static final Logger LOGGER = Logger.getLogger(NotifyExtractContentManagerImpl.class);
	
	private final static Integer limit_size = 10;
	
	@Resource
	private AlertNotifyRecordDAO alertNotifyRecordDAO;
	
	@Resource
	private AlertRecordDAO alertRecordDAO;
	
	@Resource
	private AlarmUserInfoManager alarmUserInfoManager;
	
	@Resource
	private SmsMessageUtils smsMessageUtils;
	
	@Resource
	private EmailSendService emailSendService;
	
	public void sendMessage() {
		LOGGER.info("[预警发送] 任务开始...");
		List<AlertRecordDomain> list = alertRecordDAO.getUnCompleteTaskLimit(limit_size);
		if(CollectionUtils.isNotEmpty(list)){
			LOGGER.info(String.format("[预警发送]  本次任务数%d条...", list.size()));
			for(AlertRecordDomain domain : list){
				
				AlarmUserDTO  dto = alarmUserInfoManager.getUserInfoByMonitor(domain.getMonitorId(), domain.getSysType());
				
				if(dto == null
						|| ArrayUtils.isEmpty(dto.getMails())
						|| ArrayUtils.isEmpty(dto.getMobileMails())){
					String error = String.format("未配置接收人的信息,MonitorId=%s,ConfigType=%d", domain.getMonitorId(),domain.getSysType());
					LOGGER.error(error);
					
					//同时关闭任务
					alertRecordDAO.updateAlertRecordTaskStatus(domain.getId());
					
					continue;
				}
				
				//发送mail
				MailRecvMessage mailMessage = new MailRecvMessage();
				mailMessage.setRecv(dto.getMails());
				mailMessage.setTitle(domain.getTitle());
				mailMessage.setContent(domain.getContent());
				
				if(domain.getNotifyType().intValue() == AlarmNotifyTypeEnum.mixed.getNotifyType().intValue()){
					// CC 
					mailMessage.setCc(dto.getMobileMails());
				}
				
				emailSendService.sendMail(mailMessage);
				
				//发短信
				//boolean isSendPhone = false;
				if(domain.getNotifyType().intValue() == AlarmNotifyTypeEnum.mixed.getNotifyType().intValue()){
					if(ArrayUtils.isNotEmpty(dto.getMobiles())){
						int len = dto.getMobiles().length;
						String[] org = dto.getMobiles();
						StringBuffer sb = new StringBuffer();
						for(int j=0;j<len;j++){
							sb.append(org[j]);sb.append(",");
						}
						String[] phones = StringUtils.split(StringUtils.removeEnd(sb.toString(), ","), ",");
						smsMessageUtils.sendMessage(phones, domain.getContent());
					}
				}
				
				
				
				
				//关闭任务
				alertRecordDAO.updateAlertRecordTaskStatus(domain.getId());
				
				//记录发送记录,以后改成手机，目前还是用139邮箱
				saveMessagelog(domain,dto.getMobileMails(),dto.getMails());
			}
		}
		
		LOGGER.info("[预警发送] 任务结束...");
	}

	/**
	 * 写发送日志
	 */
	private void saveMessagelog(AlertRecordDomain domain,String[] mobiles,String[] mails) {
		AlertNotifyRecordDomain notifyRecord = new AlertNotifyRecordDomain();
		notifyRecord.setAlertRecordId(domain.getId().toString());
		if(domain.getNotifyType().intValue() == AlarmNotifyTypeEnum.mixed.getNotifyType().intValue()){
			notifyRecord.setMobileMails(convertListToString(mobiles));
		}
		notifyRecord.setReceviceMails(convertListToString(mails));
		notifyRecord.setNoticeType(domain.getNotifyType());
		alertNotifyRecordDAO.addAlertNotifyRecord(notifyRecord);
	}

	
	private String convertListToString(String[] input){
		StringBuffer result = new StringBuffer();
		for(int i=0;i<input.length;i++){
			result.append(input[i]);
			result.append(",");
		}
		String tmp = result.toString();
		return StringUtils.removeEnd(tmp, ",");
	}
	
	
	public static void main(String[] args) {
		String error = String.format("未配置接收人的信息,MonitorId=%s,ConfigType=%d", "M123",1);
		System.out.println(error);
	}

}
