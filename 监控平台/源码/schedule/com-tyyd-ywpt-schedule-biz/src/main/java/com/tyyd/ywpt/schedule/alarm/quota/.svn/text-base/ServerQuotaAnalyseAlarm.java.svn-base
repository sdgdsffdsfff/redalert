/**   
* @Title: QuotaAnalyseAlarm.java 
* @Package com.tyyd.ywpt.schedule.alarm.quota 
* @Description:  
* @author wangyu   
* @date 2014-8-6 上午9:05:37 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.alarm.quota;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.tyyd.ywpt.biz.dict.AlertRecordMonitorTypeEnum;
import com.tyyd.ywpt.biz.dict.AlertRecordNoticeLevelEnum;
import com.tyyd.ywpt.biz.dict.SysTypeEnum;
import com.tyyd.ywpt.dao.alert.record.dataobject.AlertRecordDomain;
import com.tyyd.ywpt.dao.core.collect.even.host.HostQuotaCollectDayDAO;
import com.tyyd.ywpt.dao.core.collect.even.host.dataobject.HostQuotaCollectDayDomain;
import com.tyyd.ywpt.schedule.alarm.AbstractMemoryAlarmAnalyseBase;
import com.tyyd.ywpt.schedule.alarm.dataobject.AlaramContent;
import com.tyyd.ywpt.schedule.alarm.dataobject.AlarmNotifyTypeEnum;
import com.tyyd.ywpt.schedule.alarm.quota.phone.QuotaPhoneSentManager;
import com.tyyd.ywpt.schedule.alarm.util.AlarmKeyReplaceUtils;

/**
 * @author wangyu
 *
 */
public class ServerQuotaAnalyseAlarm extends AbstractMemoryAlarmAnalyseBase<HostQuotaCollectDayDomain>{

	private HostQuotaCollectDayDAO hostQuotaCollectDayDAO;
	
	private AlaramContent hostQuotaAlarmContent;
	
	private QuotaPhoneSentManager quotaPhoneSentManager;

	@Override
	public void init() {
		setWorker("[主机指标预警]");
	}

	@Override
	public List<HostQuotaCollectDayDomain> listUnCompetedTask() {
		return hostQuotaCollectDayDAO.getHostMonitorUnCompletedData();
	}

	@Override
	public void closeUnCompletedTask(List<HostQuotaCollectDayDomain> dataList) {
		if(CollectionUtils.isNotEmpty(dataList)){
			for(HostQuotaCollectDayDomain domain : dataList){
				hostQuotaCollectDayDAO.closeUnCompletedQuotaCollectDataById(domain.getId());
			}
		}
	}

	@Override
	public List<AlertRecordDomain> analyData(List<HostQuotaCollectDayDomain> dataList) {
		List<AlertRecordDomain> result = new ArrayList<AlertRecordDomain>();
		if(CollectionUtils.isNotEmpty(dataList)){
			for(HostQuotaCollectDayDomain domain : dataList){
				
				//获取对象
				HostQuotaCollectDayDomain obj = this.getHostQuotaCollectDayDomain(domain.getId());
				
				//没配置预警阈值的数据，直接丢掉
				if(null == obj){
					LOGGER.info("未配置主机预警阈值,host_quota_id="+domain.getId());
					continue;
				}
				
				//判断预警的类型
				int legelData = this.illegalData(obj);
				
				//合法数据不需要预警
				if(NumberUtils.INTEGER_ZERO.intValue() == legelData) {
					continue; 
				}
				
				LOGGER.info("主机预警 ,host_quota_id="+obj.getId());
				
				//设置domain的预警等级 
				obj.setWarnLevel(legelData);
				AlertRecordDomain alertRecord = this.convertAlertRecordDomain(obj, legelData);
				
				result.add(alertRecord);
			}
		}
		
		return result;
	}

	
	private HostQuotaCollectDayDomain getHostQuotaCollectDayDomain(String id){
		HostQuotaCollectDayDomain obj = hostQuotaCollectDayDAO.getHostMonitorUnCompletedObj(id);
		return obj;
	}
	
	@Override
	protected AlarmKeyReplaceUtils initAlarmKeyReplaceUtils(
			HostQuotaCollectDayDomain domain) {
		
		Long warnValue = domain.getWaringQuotaValue();
		if(domain.getWarnLevel().intValue() == AlertRecordNoticeLevelEnum.critical.getVal().intValue()){
			warnValue = domain.getCriticalQuotaValue();
		}
		
		AlarmKeyReplaceUtils obj = new AlarmKeyReplaceUtils(domain.getIpAddr(),
				domain.getNickName(), domain.getNickName(), domain.getPort(),
				domain.getQuotaId(), domain.getQuotaName(),
				domain.getQuotaValue(), domain.getQuotaMetric(),
				domain.getGmtCreated(),warnValue);
		
		return obj;
	}

	/**
	 * @return the hostQuotaCollectDayDAO
	 */
	public HostQuotaCollectDayDAO getHostQuotaCollectDayDAO() {
		return hostQuotaCollectDayDAO;
	}

	/**
	 * @param hostQuotaCollectDayDAO the hostQuotaCollectDayDAO to set
	 */
	public void setHostQuotaCollectDayDAO(
			HostQuotaCollectDayDAO hostQuotaCollectDayDAO) {
		this.hostQuotaCollectDayDAO = hostQuotaCollectDayDAO;
	}

	/**
	 * @return the hostQuotaAlarmContent
	 */
	public AlaramContent getHostQuotaAlarmContent() {
		return hostQuotaAlarmContent;
	}

	/**
	 * @param hostQuotaAlarmContent the hostQuotaAlarmContent to set
	 */
	public void setHostQuotaAlarmContent(AlaramContent hostQuotaAlarmContent) {
		this.hostQuotaAlarmContent = hostQuotaAlarmContent;
	}

	/**
	 * @return the quotaPhoneSentManager
	 */
	public QuotaPhoneSentManager getQuotaPhoneSentManager() {
		return quotaPhoneSentManager;
	}

	/**
	 * @param quotaPhoneSentManager the quotaPhoneSentManager to set
	 */
	public void setQuotaPhoneSentManager(QuotaPhoneSentManager quotaPhoneSentManager) {
		this.quotaPhoneSentManager = quotaPhoneSentManager;
	}

	/**
	 * 不满足规则的数据，即需要发预警的数据
	 * @return
	 */
	private Integer illegalData(HostQuotaCollectDayDomain domain){
		Integer result = 0;
		if(domain == null || domain.getThresholdType() == null){
			return result;
		}
		
		//阈值
		Float warnThresholdValue = NumberUtils.toFloat(domain.getWaringQuotaValue().toString());
		Float criticalThresholdValue =  NumberUtils.toFloat(domain.getCriticalQuotaValue().toString());
		Float quotaValue = domain.getQuotaValue();
		Integer thresholdType = domain.getThresholdType();
		
		
		return calculateQuotaWarnLevel(warnThresholdValue,criticalThresholdValue,quotaValue,thresholdType);
		
	}
	
	/**
	 * 转化对象
	 * @param domain
	 * @param legelFlag
	 * @return
	 */
	private AlertRecordDomain convertAlertRecordDomain(HostQuotaCollectDayDomain domain,int legelFlag){
		AlertRecordDomain alertRecord = new AlertRecordDomain();
		
		alertRecord.setMonitorId(domain.getHostId());
		alertRecord.setQuotaId(domain.getQuotaId());
		alertRecord.setQuotaName(domain.getQuotaName());
		alertRecord.setQuotaValue(domain.getQuotaValue());
		alertRecord.setSysType(SysTypeEnum.server.getVal());
		alertRecord.setNotifyType(AlarmNotifyTypeEnum.email.getNotifyType());
		
		boolean isPhoneSent = quotaPhoneSentManager.requirePhoneSent(domain.getQuotaId());
		if(isPhoneSent){
			alertRecord.setNotifyType(AlarmNotifyTypeEnum.mixed.getNotifyType());
		}
		
		//设置标题和内容
		AlaramContent alarm = getAlaramContent(domain,hostQuotaAlarmContent);
		alertRecord.setContent(alarm.getMailContent());
		alertRecord.setTitle(alarm.getMailTitle());
		alertRecord.setGmtCreated(domain.getGmtCreated());
		
		alertRecord.setMonitorType(AlertRecordMonitorTypeEnum.quota.getVal());
		alertRecord.setNoticeLevel(domain.getWarnLevel());
		
		return alertRecord;
	}
}
