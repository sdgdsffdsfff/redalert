/**   
* @Title: HeartBeatAnalyseAlarm.java 
* @Package com.tyyd.ywpt.schedule.alarm.heartbeat 
* @Description:  
* @author wangyu   
* @date 2014-8-5 上午11:36:35 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.alarm.heartbeat;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tyyd.ywpt.biz.dict.AlertRecordMonitorTypeEnum;
import com.tyyd.ywpt.biz.dict.AlertRecordNoticeLevelEnum;
import com.tyyd.ywpt.biz.dict.SysTypeEnum;
import com.tyyd.ywpt.dao.alert.record.dataobject.AlertRecordDomain;
import com.tyyd.ywpt.dao.alert.record.dataobject.MoitorAlertRecordDomain;
import com.tyyd.ywpt.dao.core.collect.heartbeat.HeartbeatMonitorDAO;
import com.tyyd.ywpt.dao.core.collect.heartbeat.dataobject.HeartbeatMonitorDomain;
import com.tyyd.ywpt.schedule.alarm.AbstractAlarmAnalyseBase;
import com.tyyd.ywpt.schedule.alarm.dataobject.AlaramContent;
import com.tyyd.ywpt.schedule.alarm.dataobject.AlarmNotifyTypeEnum;
import com.tyyd.ywpt.schedule.alarm.util.AlarmContentObjUtils;
import com.tyyd.ywpt.schedule.alarm.util.AlarmKeyReplaceUtils;

/**
 * @author wangyu
 *
 */
public class HeartBeatAnalyseAlarm extends AbstractAlarmAnalyseBase<HeartbeatMonitorDomain> {

	public static final Logger LOGGER = Logger.getLogger(AbstractAlarmAnalyseBase.class);
	
	private HeartbeatMonitorDAO heartbeatMonitorDAO;
	
	private AlaramContent hostHeartBeatAlarmContent;
	
	private AlaramContent dbHeartBeatAlarmContent;
	
	@Override
	public List<AlertRecordDomain> analyData(List<HeartbeatMonitorDomain> dataList) {
		List<AlertRecordDomain> result = new ArrayList<AlertRecordDomain>();
		if(CollectionUtils.isNotEmpty(dataList)){
			for(HeartbeatMonitorDomain domain : dataList){
				if(domain.getConfigType() != null){
					AlertRecordDomain alertRecord = new AlertRecordDomain();
					alertRecord.setSysType(domain.getConfigType());
					if(domain.getConfigType().intValue() == SysTypeEnum.server.getVal().intValue()){
						//主机层
						alertRecord.setMonitorId(domain.getHostId());
					}else if(domain.getConfigType().intValue() == SysTypeEnum.Oracle.getVal().intValue()){
						//Oracle
						alertRecord.setMonitorId(domain.getDbId());
					}else if(domain.getConfigType().intValue() == SysTypeEnum.MySQL.getVal().intValue()){
						//MySQL 
						alertRecord.setMonitorId(domain.getDbId());
					}
					
					//设置标题和内容
					AlaramContent alarm = getAlaramContent(domain);
					alertRecord.setGmtCreated(domain.getGmtCreated());
					alertRecord.setContent(alarm.getMailContent());
					alertRecord.setTitle(alarm.getMailTitle());
					alertRecord.setNotifyType(AlarmNotifyTypeEnum.mixed.getNotifyType());
					
					alertRecord.setMonitorType(AlertRecordMonitorTypeEnum.heart_beat.getVal());
					alertRecord.setNoticeLevel(AlertRecordNoticeLevelEnum.critical.getVal());
					result.add(alertRecord);
				}
			}
			
		}
		
		return result;
	}

	
	
	@Override
	public List<MoitorAlertRecordDomain> reduceAlertRecord(
			List<HeartbeatMonitorDomain> dataList) {
		
		List<MoitorAlertRecordDomain> result = new ArrayList<MoitorAlertRecordDomain>();
		if(CollectionUtils.isNotEmpty(dataList)){
			Vector<String> keys = new Vector<String>();
			for(HeartbeatMonitorDomain domain : dataList){
				MoitorAlertRecordDomain alertRecord = new MoitorAlertRecordDomain();
				if(domain.getConfigType().intValue() == SysTypeEnum.server.getVal().intValue()){
					alertRecord.setMonitorId(domain.getHostId());
				}else if(domain.getConfigType().intValue() == SysTypeEnum.Oracle.getVal().intValue() 
						|| domain.getConfigType().intValue() == SysTypeEnum.MySQL.getVal().intValue()){
					alertRecord.setMonitorId(domain.getDbId());
				}
				
				alertRecord.setSysType(domain.getConfigType());
				
				
				//设置内容
				AlaramContent alarm = getAlaramContent(domain);
				alertRecord.setContent(alarm.getMonitorAlertContent());
				alertRecord.setGmtCreated(domain.getGmtCreated());
				
				alertRecord.setMonitorType(AlertRecordMonitorTypeEnum.heart_beat.getVal());
				alertRecord.setNoticeLevel(AlertRecordNoticeLevelEnum.critical.getVal());
				
				String key = domain.getDbId()+":"+domain.getConfigType();
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
	
	

	protected AlaramContent getAlaramContent(HeartbeatMonitorDomain domain){
		AlarmKeyReplaceUtils utils = initAlarmKeyReplaceUtils(domain);
		AlarmContentObjUtils contentObjUtils = new AlarmContentObjUtils();
		contentObjUtils.setUtils(utils);
		if(domain.getConfigType().intValue() == SysTypeEnum.server.getVal().intValue()){
			contentObjUtils.setAlaramContent(hostHeartBeatAlarmContent);
		}else{
			contentObjUtils.setAlaramContent(dbHeartBeatAlarmContent);
		}
		
		return contentObjUtils.getAlaramContent();
	}
	
	
	/**
	 * @param domain
	 */
	protected AlarmKeyReplaceUtils initAlarmKeyReplaceUtils(HeartbeatMonitorDomain domain) {
		Integer port = null;
		if(StringUtils.isNotBlank(domain.getPort())){
			port = Integer.valueOf(domain.getPort());
		}
		
		AlarmKeyReplaceUtils obj = new AlarmKeyReplaceUtils(domain.getIpAddr(),
				domain.getNickName(), domain.getDbName(), port,
				domain.getGmtCreated());
		
		return obj;
	}


	/**
	 * 关闭已经处理的任务
	 * @param dataList
	 */
	@Override
	public void closeUnCompletedTask(List<HeartbeatMonitorDomain> dataList) {
		for(HeartbeatMonitorDomain domain : dataList){
			heartbeatMonitorDAO.closeCompletedTask(domain.getId());
		}
	}

	@Override
	public void init() {
		setWorker("[心跳监测]");
	}

	@Override
	public List<HeartbeatMonitorDomain> listUnCompetedTask() {
		return heartbeatMonitorDAO.listNoCompletedHeartbeat();
	}

	@Override
	public void closeNormalUnCompletedTask() {
		heartbeatMonitorDAO.closeNormalTask();
	}


	/**
	 * @return the heartbeatMonitorDAO
	 */
	public HeartbeatMonitorDAO getHeartbeatMonitorDAO() {
		return heartbeatMonitorDAO;
	}


	/**
	 * @param heartbeatMonitorDAO the heartbeatMonitorDAO to set
	 */
	public void setHeartbeatMonitorDAO(HeartbeatMonitorDAO heartbeatMonitorDAO) {
		this.heartbeatMonitorDAO = heartbeatMonitorDAO;
	}


	/**
	 * @return the hostHeartBeatAlarmContent
	 */
	public AlaramContent getHostHeartBeatAlarmContent() {
		return hostHeartBeatAlarmContent;
	}


	/**
	 * @param hostHeartBeatAlarmContent the hostHeartBeatAlarmContent to set
	 */
	public void setHostHeartBeatAlarmContent(AlaramContent hostHeartBeatAlarmContent) {
		this.hostHeartBeatAlarmContent = hostHeartBeatAlarmContent;
	}


	/**
	 * @return the dbHeartBeatAlarmContent
	 */
	public AlaramContent getDbHeartBeatAlarmContent() {
		return dbHeartBeatAlarmContent;
	}


	/**
	 * @param dbHeartBeatAlarmContent the dbHeartBeatAlarmContent to set
	 */
	public void setDbHeartBeatAlarmContent(AlaramContent dbHeartBeatAlarmContent) {
		this.dbHeartBeatAlarmContent = dbHeartBeatAlarmContent;
	}

	
}
