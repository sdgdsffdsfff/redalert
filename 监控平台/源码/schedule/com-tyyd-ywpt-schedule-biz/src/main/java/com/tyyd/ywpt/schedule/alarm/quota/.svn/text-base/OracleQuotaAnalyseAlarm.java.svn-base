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
import com.tyyd.ywpt.dao.core.collect.even.oracle.OracleQuotaCollectDayDAO;
import com.tyyd.ywpt.dao.core.collect.even.oracle.dataobject.OracleQuotaCollectDayDomain;
import com.tyyd.ywpt.schedule.alarm.AbstractMemoryAlarmAnalyseBase;
import com.tyyd.ywpt.schedule.alarm.dataobject.AlaramContent;
import com.tyyd.ywpt.schedule.alarm.dataobject.AlarmNotifyTypeEnum;
import com.tyyd.ywpt.schedule.alarm.quota.phone.QuotaPhoneSentManager;
import com.tyyd.ywpt.schedule.alarm.util.AlarmKeyReplaceUtils;

/**
 * @author wangyu
 *
 */
public class OracleQuotaAnalyseAlarm extends AbstractMemoryAlarmAnalyseBase<OracleQuotaCollectDayDomain>{

	private OracleQuotaCollectDayDAO oracleQuotaCollectDayDAO;
	
	private AlaramContent oracleQuotaAlarmContent;
	
	private QuotaPhoneSentManager quotaPhoneSentManager;
	
	@Override
	public void init() {
		setWorker("Oracle指标预警");
	}

	@Override
	public List<OracleQuotaCollectDayDomain> listUnCompetedTask() {
		return oracleQuotaCollectDayDAO.getOracleMonitorUnCompletedData();
	}

	@Override
	public void closeUnCompletedTask(List<OracleQuotaCollectDayDomain> dataList) {
		if(CollectionUtils.isEmpty(dataList)){
			return ;
		}
		for(OracleQuotaCollectDayDomain domain : dataList){
			oracleQuotaCollectDayDAO.closeUnCompletedOracleQuota(domain.getId());
		}
	}

	@Override
	public List<AlertRecordDomain> analyData(
			List<OracleQuotaCollectDayDomain> taskList) {
		List<AlertRecordDomain> result = new ArrayList<AlertRecordDomain>();
		if(CollectionUtils.isEmpty(taskList)){
			return result;
		}
		
		for(OracleQuotaCollectDayDomain domain : taskList){
			
			
			//获取对象
			OracleQuotaCollectDayDomain obj = this.getOracleQuotaCollectDayDomain(domain.getId());
			
			//没配置预警阈值的数据，直接丢掉
			if(null == obj){
				LOGGER.info("未配置ORACLE指标预警阈值,oracle_quota_id="+domain.getId());
				continue;
			}
			
			//判断预警的类型
			int legelData = this.illegalData(obj);
			
			//合法数据不需要预警
			if(NumberUtils.INTEGER_ZERO.intValue() == legelData) {
				continue; 
			}
			
			LOGGER.info("ORACLE指标预警 ,oracle_quota_id="+obj.getId());
			
			//设置domain的预警等级 
			obj.setWarnLevel(legelData);
			AlertRecordDomain alertRecord = this.convertAlertRecordDomain(obj, legelData);
			
			result.add(alertRecord);
		}
		
		return result;
	}
	
	
	private AlertRecordDomain convertAlertRecordDomain(OracleQuotaCollectDayDomain domain,int legelFlag){
		AlertRecordDomain alertRecord = new AlertRecordDomain();
		
		alertRecord.setMonitorId(domain.getDbId());
		alertRecord.setQuotaId(domain.getQuotaId());
		alertRecord.setQuotaName(domain.getQuotaName());
		alertRecord.setQuotaValue(domain.getQuotaValue());
		alertRecord.setSysType(SysTypeEnum.Oracle.getVal());
		alertRecord.setNotifyType(AlarmNotifyTypeEnum.email.getNotifyType());
		
		boolean isPhoneSent = quotaPhoneSentManager.requirePhoneSent(domain.getQuotaId());
		if(isPhoneSent){
			alertRecord.setNotifyType(AlarmNotifyTypeEnum.mixed.getNotifyType());
		}
		
		//设置标题和内容
		AlaramContent alarm = getAlaramContent(domain,oracleQuotaAlarmContent);
		alertRecord.setContent(alarm.getMailContent());
		alertRecord.setTitle(alarm.getMailTitle());
		alertRecord.setGmtCreated(domain.getGmtCreated());
		
		alertRecord.setMonitorType(AlertRecordMonitorTypeEnum.quota.getVal());
		alertRecord.setNoticeLevel(domain.getWarnLevel());
		
		return alertRecord;
		
	}
	
	private OracleQuotaCollectDayDomain getOracleQuotaCollectDayDomain(String id){
		OracleQuotaCollectDayDomain obj = oracleQuotaCollectDayDAO.getOracleMonitorUnCompletedObj(id);
		return obj;
	}
	
	

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.schedule.alarm.AbstractAlarmAnalyseBase#initAlarmKeyReplaceUtils(java.lang.Object)
	 */
	@Override
	protected AlarmKeyReplaceUtils initAlarmKeyReplaceUtils(
			OracleQuotaCollectDayDomain domain) {
		
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
	 * @return the oracleQuotaCollectDayDAO
	 */
	public OracleQuotaCollectDayDAO getOracleQuotaCollectDayDAO() {
		return oracleQuotaCollectDayDAO;
	}

	/**
	 * @param oracleQuotaCollectDayDAO the oracleQuotaCollectDayDAO to set
	 */
	public void setOracleQuotaCollectDayDAO(
			OracleQuotaCollectDayDAO oracleQuotaCollectDayDAO) {
		this.oracleQuotaCollectDayDAO = oracleQuotaCollectDayDAO;
	}

	/**
	 * @return the oracleQuotaAlarmContent
	 */
	public AlaramContent getOracleQuotaAlarmContent() {
		return oracleQuotaAlarmContent;
	}

	/**
	 * @param oracleQuotaAlarmContent the oracleQuotaAlarmContent to set
	 */
	public void setOracleQuotaAlarmContent(AlaramContent oracleQuotaAlarmContent) {
		this.oracleQuotaAlarmContent = oracleQuotaAlarmContent;
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
	private Integer illegalData(OracleQuotaCollectDayDomain domain){
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
}
