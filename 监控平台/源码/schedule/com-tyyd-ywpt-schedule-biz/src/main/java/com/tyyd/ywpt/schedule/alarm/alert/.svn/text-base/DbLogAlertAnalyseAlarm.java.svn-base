/**   
* @Title: DbLogAlertAnalyseAlarm.java 
* @Package com.tyyd.ywpt.schedule.alarm.alert 
* @Description:  
* @author wangyu   
* @date 2014-8-5 下午4:04:10 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.alarm.alert;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tyyd.ywpt.biz.dict.AlertRecordMonitorTypeEnum;
import com.tyyd.ywpt.biz.dict.AlertRecordNoticeLevelEnum;
import com.tyyd.ywpt.dao.alert.logalert.DbLogAlertDAO;
import com.tyyd.ywpt.dao.alert.logalert.dataobject.AlertTypeEnum;
import com.tyyd.ywpt.dao.alert.logalert.dataobject.DbLogAlertDomain;
import com.tyyd.ywpt.dao.alert.record.dataobject.AlertRecordDomain;
import com.tyyd.ywpt.dao.alert.record.dataobject.MoitorAlertRecordDomain;
import com.tyyd.ywpt.schedule.alarm.AbstractAlarmAnalyseBase;
import com.tyyd.ywpt.schedule.alarm.dataobject.AlaramContent;
import com.tyyd.ywpt.schedule.alarm.dataobject.AlarmNotifyTypeEnum;
import com.tyyd.ywpt.schedule.alarm.util.AlarmKeyReplaceUtils;
import com.tyyd.ywpt.tools.encrypt.MD5Utils;

/**
 * @author wangyu
 *
 */
public class DbLogAlertAnalyseAlarm extends AbstractAlarmAnalyseBase<DbLogAlertDomain> {

	public static final Logger LOGGER = Logger.getLogger(DbLogAlertAnalyseAlarm.class);
	
	private DbLogAlertDAO dbLogAlertDAO;
	
	private AlaramContent dbLogAlertAlarmContent;
	

	@Override
	public void init() {
		setWorker("日志告警");
	}

	@Override
	public List<DbLogAlertDomain> listUnCompetedTask() {
		List<DbLogAlertDomain> dataList = dbLogAlertDAO.listNoCompletedMsg();
		return dataList;
	}

	@Override
	public void closeUnCompletedTask(List<DbLogAlertDomain> dataList) {
		if(CollectionUtils.isNotEmpty(dataList)){
			for(DbLogAlertDomain domain : dataList){
				dbLogAlertDAO.updateDbLogAlertCompleted(domain.getId());
			}
		}
	}

	@Override
	public List<AlertRecordDomain> analyData(List<DbLogAlertDomain> dataList) {
		List<AlertRecordDomain> result = new ArrayList<AlertRecordDomain>();
		if(CollectionUtils.isNotEmpty(dataList)){
			for(DbLogAlertDomain domain : dataList){
				
				//如果是MYSQL部分11，10，12的告警，先过滤
				if(domain.getAlertType().intValue() == AlertTypeEnum.MYSQL_LONG_SQL.getAlertType().intValue()
						|| domain.getAlertType().intValue() == AlertTypeEnum.MYSQL_RESULT_DATA_SQL.getAlertType().intValue()
						|| domain.getAlertType().intValue() == AlertTypeEnum.MYSQL_EXAMINED_SQL.getAlertType().intValue()){
					
					//关闭任务
					dbLogAlertDAO.updateDbLogAlertCompleted(domain.getId());
					continue;
				}
				
				
				
				AlertRecordDomain alertRecord = new AlertRecordDomain();
				
				alertRecord.setMonitorId(domain.getDbId());
				alertRecord.setSysType(domain.getConfigType());
				
				if(domain.getIsPhoneSent() == null || domain.getIsPhoneSent().intValue()==0){
					alertRecord.setNotifyType(AlarmNotifyTypeEnum.mixed.getNotifyType());
				}else{
					alertRecord.setNotifyType(AlarmNotifyTypeEnum.email.getNotifyType());
				}
				
				//设置标题和内容
				AlaramContent alarm = getAlaramContent(domain,dbLogAlertAlarmContent);
				alertRecord.setContent(alarm.getMailContent());
				alertRecord.setTitle(alarm.getMailTitle());
				alertRecord.setGmtCreated(domain.getGmtCreated());
				
				alertRecord.setMonitorType(AlertRecordMonitorTypeEnum.alert.getVal());
				alertRecord.setNoticeLevel(AlertRecordNoticeLevelEnum.warn.getVal());
				result.add(alertRecord);
			}
		}
		return result;
	}

	@Override
	public void closeNormalUnCompletedTask() {
		//告警日志，没有正常任务
		return;
	}

	@Override
	protected AlarmKeyReplaceUtils initAlarmKeyReplaceUtils(
			DbLogAlertDomain domain) {
		Integer port = null;
		if(StringUtils.isNotBlank(domain.getPort())){
			port = Integer.valueOf(domain.getPort());
		}
		
		String alertType = AlertTypeEnum.getEnum(domain.getAlertType());
		if(StringUtils.isBlank(alertType)){
			alertType = AlertTypeEnum.DB_LOG_ALERT.getAlertTypeContent();
		}
		
		AlarmKeyReplaceUtils obj = new AlarmKeyReplaceUtils(domain.getIpAddr(),
				domain.getNickName(), domain.getNickName(), port,domain.getAlertMsg(),
				domain.getGmtCreated(),alertType);
		
		return obj;
	}

	/**
	 * @return the dbLogAlertDAO
	 */
	public DbLogAlertDAO getDbLogAlertDAO() {
		return dbLogAlertDAO;
	}

	/**
	 * @param dbLogAlertDAO the dbLogAlertDAO to set
	 */
	public void setDbLogAlertDAO(DbLogAlertDAO dbLogAlertDAO) {
		this.dbLogAlertDAO = dbLogAlertDAO;
	}

	/**
	 * @return the dbLogAlertAlarmContent
	 */
	public AlaramContent getDbLogAlertAlarmContent() {
		return dbLogAlertAlarmContent;
	}

	/**
	 * @param dbLogAlertAlarmContent the dbLogAlertAlarmContent to set
	 */
	public void setDbLogAlertAlarmContent(AlaramContent dbLogAlertAlarmContent) {
		this.dbLogAlertAlarmContent = dbLogAlertAlarmContent;
	}

	@Override
	public List<MoitorAlertRecordDomain> reduceAlertRecord(
			List<DbLogAlertDomain> dataList) {
		
		List<MoitorAlertRecordDomain> result = new ArrayList<MoitorAlertRecordDomain>();
		if(CollectionUtils.isNotEmpty(dataList)){
			List<String> keys = new Vector<String>();
			for(DbLogAlertDomain domain : dataList){
				MoitorAlertRecordDomain alertRecord = new MoitorAlertRecordDomain();
				
				alertRecord.setMonitorId(domain.getDbId());
				alertRecord.setSysType(domain.getConfigType());
				alertRecord.setSignmd5(MD5Utils.getMD5Format(domain.getAlertType() +":"+ domain.getAlertMsg()));
				
				//设置内容
				AlaramContent alarm = getAlaramContent(domain,dbLogAlertAlarmContent);
				alertRecord.setContent(alarm.getMonitorAlertContent());
				alertRecord.setGmtCreated(domain.getGmtCreated());
				
				alertRecord.setMonitorType(AlertRecordMonitorTypeEnum.alert.getVal());
				alertRecord.setNoticeLevel(AlertRecordNoticeLevelEnum.warn.getVal());
				
				String key = domain.getDbId()+":"+domain.getConfigType()+":"+alertRecord.getSignmd5();
				if(keys.contains(key)){
					continue;
				}else{
					//存入key
					keys.add(key);
				}
				
				result.add(alertRecord);
			}
		}
		return result;
		
	}

}
