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
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.tyyd.ywpt.biz.dict.AlertRecordMonitorTypeEnum;
import com.tyyd.ywpt.biz.dict.AlertRecordNoticeLevelEnum;
import com.tyyd.ywpt.biz.dict.SysTypeEnum;
import com.tyyd.ywpt.dao.alert.record.dataobject.AlertRecordDomain;
import com.tyyd.ywpt.dao.core.collect.even.mysql.MysqlQuotaCollectDayDAO;
import com.tyyd.ywpt.dao.core.collect.even.mysql.dataobject.MysqlQuotaCollectDayDomain;
import com.tyyd.ywpt.schedule.alarm.AbstractMemoryAlarmAnalyseBase;
import com.tyyd.ywpt.schedule.alarm.dataobject.AlaramContent;
import com.tyyd.ywpt.schedule.alarm.dataobject.AlarmNotifyTypeEnum;
import com.tyyd.ywpt.schedule.alarm.quota.phone.QuotaPhoneSentManager;
import com.tyyd.ywpt.schedule.alarm.util.AlarmKeyReplaceUtils;

/**
 * @author wangyu
 *
 */
public class MySQLQuotaAnalyseAlarm extends AbstractMemoryAlarmAnalyseBase<MysqlQuotaCollectDayDomain>{

	private MysqlQuotaCollectDayDAO mysqlQuotaCollectDayDAO;
	
	private AlaramContent mysqlQuotaAlarmContent;
	
	private QuotaPhoneSentManager quotaPhoneSentManager;
	
	@Override
	public void init() {
		setWorker("[MySQL指标预警]");
	}


	@Override
	public List<MysqlQuotaCollectDayDomain> listUnCompetedTask() {
		return mysqlQuotaCollectDayDAO.getMySQLMonitorUnCompletedData();
	}


	@Override
	public void closeUnCompletedTask(List<MysqlQuotaCollectDayDomain> dataList) {
		if(CollectionUtils.isEmpty(dataList)){
			return ;
		}
		for(MysqlQuotaCollectDayDomain domain : dataList){
			mysqlQuotaCollectDayDAO.closeUnCompletedTask(domain.getId());
		}
	}


	@Override
	public List<AlertRecordDomain> analyData(
			List<MysqlQuotaCollectDayDomain> taskList) {
		List<AlertRecordDomain> result = new ArrayList<AlertRecordDomain>();
		if(CollectionUtils.isEmpty(taskList)){
			return result;
		}
		for(MysqlQuotaCollectDayDomain domain : taskList){
			
			//获取对象
			MysqlQuotaCollectDayDomain obj = this.getMysqlQuotaCollectDayDomain(domain.getId());
			
			//没配置预警阈值的数据，直接丢掉
			if(null == obj){
				LOGGER.info("未配置MySQL指标预警阈值,mysql_quota_id="+domain.getId());
				continue;
			}
			
			//判断预警的类型
			int legelData = this.illegalData(obj);
			
			//合法数据不需要预警
			if(NumberUtils.INTEGER_ZERO.intValue() == legelData) {
				continue; 
			}
			
			LOGGER.info("MySQL指标预警 ,mysql_quota_id="+obj.getId());
			
			
			//设置domain的预警等级 
			obj.setWarnLevel(legelData);
			AlertRecordDomain alertRecord = this.convertAlertRecordDomain(obj, legelData);
			
			
			result.add(alertRecord);
		}
		return result;
	}
	
	
	private AlertRecordDomain convertAlertRecordDomain(MysqlQuotaCollectDayDomain domain,int legelFlag){
		
		AlertRecordDomain alertRecord = new AlertRecordDomain();
		
		alertRecord.setMonitorId(domain.getDbId());
		alertRecord.setQuotaId(domain.getQuotaId());
		alertRecord.setQuotaName(domain.getQuotaName());
		alertRecord.setQuotaValue(domain.getQuotaValue());
		alertRecord.setSysType(SysTypeEnum.MySQL.getVal());
		alertRecord.setNotifyType(AlarmNotifyTypeEnum.email.getNotifyType());
		
		boolean isPhoneSent = quotaPhoneSentManager.requirePhoneSent(domain.getQuotaId());
		if(isPhoneSent){
			alertRecord.setNotifyType(AlarmNotifyTypeEnum.mixed.getNotifyType());
		}
		
		//设置标题和内容
		AlaramContent alarm = getAlaramContent(domain,mysqlQuotaAlarmContent);
		alertRecord.setContent(alarm.getMailContent());
		alertRecord.setTitle(alarm.getMailTitle());
		alertRecord.setGmtCreated(domain.getGmtCreated());
		
		alertRecord.setMonitorType(AlertRecordMonitorTypeEnum.quota.getVal());
		alertRecord.setNoticeLevel(domain.getWarnLevel());
		
		return alertRecord;
		
	}
	
	/**
	 * 不满足规则的数据，即需要发预警的数据
	 * @return
	 */
	private Integer illegalData(MysqlQuotaCollectDayDomain domain){
		Integer result = 0;
		if(domain == null || domain.getThresholdType() == null){
			return result;
		}
		
		//阈值
		Float warnThresholdValue = NumberUtils.toFloat(domain.getWaringQuotaValue().toString());
		Float criticalThresholdValue =  NumberUtils.toFloat(domain.getCriticalQuotaValue().toString());
		Float quotaValue = domain.getQuotaValue();
		Integer thresholdType = domain.getThresholdType();
		
		
		return this.calculateQuotaWarnLevel(warnThresholdValue,criticalThresholdValue,quotaValue,thresholdType);
		
	}

	private MysqlQuotaCollectDayDomain getMysqlQuotaCollectDayDomain(String id){
		MysqlQuotaCollectDayDomain obj = mysqlQuotaCollectDayDAO.getMySQLMonitorUnCompletedObj(id);
		return obj;
	}
	
	


	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.schedule.alarm.AbstractAlarmAnalyseBase#initAlarmKeyReplaceUtils(java.lang.Object)
	 */
	@Override
	protected AlarmKeyReplaceUtils initAlarmKeyReplaceUtils(
			MysqlQuotaCollectDayDomain domain) {
		
		Integer port = null;
		if(StringUtils.isNotBlank(domain.getPort())){
			port = Integer.valueOf(domain.getPort());
		}
		
		Long warnValue = domain.getWaringQuotaValue();
		if(domain.getWarnLevel().intValue() == AlertRecordNoticeLevelEnum.critical.getVal().intValue()){
			warnValue = domain.getCriticalQuotaValue();
		}
		
		AlarmKeyReplaceUtils obj = new AlarmKeyReplaceUtils(domain.getIpAddr(),
				domain.getNickName(), domain.getNickName(), port,
				domain.getQuotaId(), domain.getQuotaName(),
				domain.getQuotaValue(), domain.getQuotaMetric(),
				domain.getGmtCreated(),warnValue);
		
		return obj;
	}


	/**
	 * @return the mysqlQuotaCollectDayDAO
	 */
	public MysqlQuotaCollectDayDAO getMysqlQuotaCollectDayDAO() {
		return mysqlQuotaCollectDayDAO;
	}


	/**
	 * @param mysqlQuotaCollectDayDAO the mysqlQuotaCollectDayDAO to set
	 */
	public void setMysqlQuotaCollectDayDAO(
			MysqlQuotaCollectDayDAO mysqlQuotaCollectDayDAO) {
		this.mysqlQuotaCollectDayDAO = mysqlQuotaCollectDayDAO;
	}


	/**
	 * @return the mysqlQuotaAlarmContent
	 */
	public AlaramContent getMysqlQuotaAlarmContent() {
		return mysqlQuotaAlarmContent;
	}


	/**
	 * @param mysqlQuotaAlarmContent the mysqlQuotaAlarmContent to set
	 */
	public void setMysqlQuotaAlarmContent(AlaramContent mysqlQuotaAlarmContent) {
		this.mysqlQuotaAlarmContent = mysqlQuotaAlarmContent;
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

}
