/**   
* @Title: TbsAnalyseAlarm.java 
* @Package com.tyyd.ywpt.schedule.alarm.tablespace 
* @Description:  
* @author wangyu   
* @date 2014-8-7 下午4:28:57 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.alarm.tablespace;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.tyyd.ywpt.biz.dict.AlertRecordMonitorTypeEnum;
import com.tyyd.ywpt.biz.dict.AlertRecordNoticeLevelEnum;
import com.tyyd.ywpt.biz.dict.DiskThresholdTypeEnum;
import com.tyyd.ywpt.biz.dict.SysTypeEnum;
import com.tyyd.ywpt.dao.alert.record.dataobject.AlertRecordDomain;
import com.tyyd.ywpt.dao.core.collect.datafile.DbTableSpaceHisDAO;
import com.tyyd.ywpt.dao.core.collect.datafile.dataobject.DbTableSpaceHisDomain;
import com.tyyd.ywpt.schedule.alarm.AbstractMemoryAlarmAnalyseBase;
import com.tyyd.ywpt.schedule.alarm.dataobject.AlaramContent;
import com.tyyd.ywpt.schedule.alarm.dataobject.AlarmNotifyTypeEnum;
import com.tyyd.ywpt.schedule.alarm.util.AlarmContentObjUtils;
import com.tyyd.ywpt.schedule.alarm.util.AlarmKeyReplaceUtils;

/**
 * @author wangyu
 *
 */
public class TbsAnalyseAlarm extends AbstractMemoryAlarmAnalyseBase<DbTableSpaceHisDomain> {

	private DbTableSpaceHisDAO dbTableSpaceHisDAO;
	
	private AlaramContent mysqlTableSpaceAlarmContent;
	
	private AlaramContent oracleTableSpaceAlarmContent;
	
	

	@Override
	public List<DbTableSpaceHisDomain> listUnCompetedTask() {
		return dbTableSpaceHisDAO.getTbsMonitorUnCompletedData();
	}

	@Override
	public void closeUnCompletedTask(List<DbTableSpaceHisDomain> dataList) {
		if(CollectionUtils.isEmpty(dataList)){
			return;
		}
		
		for(DbTableSpaceHisDomain domain : dataList){
			if(domain == null || StringUtils.isBlank(domain.getId())){
				continue;
			}
			dbTableSpaceHisDAO.closeUnCompletedTaskById(domain.getId());
		}
	}

	@Override
	public List<AlertRecordDomain> analyData(
			List<DbTableSpaceHisDomain> taskList) {
		List<AlertRecordDomain> result = new ArrayList<AlertRecordDomain>();
		if(CollectionUtils.isEmpty(taskList)){
			return result;
		}
		
		for(DbTableSpaceHisDomain domain : taskList){
			
			if(domain == null || StringUtils.isBlank(domain.getId())){
				continue;
			}
			
			//获取对象
			DbTableSpaceHisDomain obj = this.getDbTableSpaceHisDomain(domain.getId());
			
			//没配置预警阈值的数据，直接丢掉
			if(null == obj){
				LOGGER.info("未配置表空间预警阈值,tbs_his_id="+domain.getId());
				continue;
			}
			
			//判断预警的类型
			int legelData = this.illegalData(obj);
			
			//合法数据不需要预警
			if(NumberUtils.INTEGER_ZERO.intValue() == legelData) {
				continue; 
			}
			
			LOGGER.info("表空间预警 ,tbs_his_id="+domain.getId());
			
			//设置domain的预警等级 
			obj.setWarnLevel(legelData);
			obj.setMaxTbs(obj.getMaxTbs() / 1024);
			AlertRecordDomain alertRecord = this.convertAlertRecordDomain(obj, legelData);
			
			result.add(alertRecord);
		}
		
		return result;
	}

	
	private DbTableSpaceHisDomain getDbTableSpaceHisDomain(String id){
		DbTableSpaceHisDomain obj = null;
		try{
			obj = dbTableSpaceHisDAO.getTbsMonitorUnCompletedObj(id);
		}catch(Exception e){
			LOGGER.error("his_tbs_id="+id, e);
		}
		return obj;
	}
	
	
	
	@Override
	public void init() {
		setWorker("[表空间预警]");
	}
	
	protected AlaramContent getAlaramContent(DbTableSpaceHisDomain domain){
		AlarmKeyReplaceUtils utils = initAlarmKeyReplaceUtils(domain);
		AlarmContentObjUtils contentObjUtils = new AlarmContentObjUtils();
		contentObjUtils.setUtils(utils);
		if(domain.getDbType().intValue() == SysTypeEnum.Oracle.getVal().intValue()){
			contentObjUtils.setAlaramContent(oracleTableSpaceAlarmContent);
		}else{
			contentObjUtils.setAlaramContent(mysqlTableSpaceAlarmContent);
		}
		
		return contentObjUtils.getAlaramContent();
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.schedule.alarm.AbstractAlarmAnalyseBase#initAlarmKeyReplaceUtils(java.lang.Object)
	 */
	@Override
	protected AlarmKeyReplaceUtils initAlarmKeyReplaceUtils(
			DbTableSpaceHisDomain domain) {
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
				domain.getThresholdValue(), domain.getThresholdUnit(),domain.getTbsName(),
				domain.getGmtCreated(),warnValue,domain.getMaxTbs());
		
		return obj;
	}

	/**
	 * @return the dbTableSpaceHisDAO
	 */
	public DbTableSpaceHisDAO getDbTableSpaceHisDAO() {
		return dbTableSpaceHisDAO;
	}

	/**
	 * @param dbTableSpaceHisDAO the dbTableSpaceHisDAO to set
	 */
	public void setDbTableSpaceHisDAO(DbTableSpaceHisDAO dbTableSpaceHisDAO) {
		this.dbTableSpaceHisDAO = dbTableSpaceHisDAO;
	}

	/**
	 * @return the mysqlTableSpaceAlarmContent
	 */
	public AlaramContent getMysqlTableSpaceAlarmContent() {
		return mysqlTableSpaceAlarmContent;
	}

	/**
	 * @param mysqlTableSpaceAlarmContent the mysqlTableSpaceAlarmContent to set
	 */
	public void setMysqlTableSpaceAlarmContent(
			AlaramContent mysqlTableSpaceAlarmContent) {
		this.mysqlTableSpaceAlarmContent = mysqlTableSpaceAlarmContent;
	}

	/**
	 * @return the oracleTableSpaceAlarmContent
	 */
	public AlaramContent getOracleTableSpaceAlarmContent() {
		return oracleTableSpaceAlarmContent;
	}

	/**
	 * @param oracleTableSpaceAlarmContent the oracleTableSpaceAlarmContent to set
	 */
	public void setOracleTableSpaceAlarmContent(
			AlaramContent oracleTableSpaceAlarmContent) {
		this.oracleTableSpaceAlarmContent = oracleTableSpaceAlarmContent;
	}

	
	/**
	 * 不满足规则的数据，即需要发预警的数据
	 * @return
	 */
	private Integer illegalData(DbTableSpaceHisDomain domain){
		Integer result = 0;
		if(domain == null || domain.getThresholdType() == null){
			return result;
		}
		
		//阈值
		Float warnThresholdValue = NumberUtils.toFloat(domain.getWaringQuotaValue().toString());
		Float criticalThresholdValue =  NumberUtils.toFloat(domain.getCriticalQuotaValue().toString());
		Float tbsRemainValue = domain.getMaxTbs() - domain.getUsedTbs();
		Float tbsPercentValue = domain.getUsePercent();
		
		if(domain.getThresholdType().intValue() == DiskThresholdTypeEnum.Percent_Threshold.getType()){
			//百分比的预警方式
			
			//达到告警的阈值
			if(tbsPercentValue - warnThresholdValue > 0){
				result = 1 + result;
			}
			
			//达到致命告警的阈值
			if(tbsPercentValue - criticalThresholdValue > 0){
				result = 1 + result;
			}
			
		}else if(domain.getThresholdType().intValue() == DiskThresholdTypeEnum.Free_Threshold.getType()){
			//剩余容量的比较方式
			
			//达到告警的阈值
			if(warnThresholdValue - tbsRemainValue > 0){
				result = 1 + result;
			}
			
			//达到致命告警的阈值
			if(criticalThresholdValue - tbsRemainValue > 0){
				result = 1 + result;
			}
		}
		
		
		return result;
	}
	
	
	
	/**
	 * 转化对象
	 * @param domain
	 * @param legelFlag
	 * @return
	 */
	private AlertRecordDomain convertAlertRecordDomain(DbTableSpaceHisDomain domain,int legelFlag){
		
		AlertRecordDomain alertRecord = new AlertRecordDomain();
		alertRecord.setMonitorId(domain.getDbId());
		alertRecord.setSysType(domain.getDbType());
		
		alertRecord.setNotifyType(AlarmNotifyTypeEnum.mixed.getNotifyType());
		
		//设置标题和内容
		AlaramContent alarm = getAlaramContent(domain);
		alertRecord.setContent(alarm.getMailContent());
		alertRecord.setTitle(alarm.getMailTitle());
		alertRecord.setGmtCreated(domain.getGmtCreated());
		
		alertRecord.setMonitorType(AlertRecordMonitorTypeEnum.tbs.getVal());
		alertRecord.setNoticeLevel(domain.getWarnLevel());
		
		//TBS名暂时存在指标名中
		alertRecord.setQuotaName(domain.getTbsName());
		
		return alertRecord;
	}
}
