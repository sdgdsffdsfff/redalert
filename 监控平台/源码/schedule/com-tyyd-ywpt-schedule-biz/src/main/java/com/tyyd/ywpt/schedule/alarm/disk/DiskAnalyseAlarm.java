/**   
* @Title: DiskAnalyseAlarm.java 
* @Package com.tyyd.ywpt.schedule.alarm.disk 
* @Description:  
* @author wangyu   
* @date 2014-8-7 下午4:06:29 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.alarm.disk;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.tyyd.ywpt.biz.dict.AlertRecordMonitorTypeEnum;
import com.tyyd.ywpt.biz.dict.AlertRecordNoticeLevelEnum;
import com.tyyd.ywpt.biz.dict.DiskThresholdTypeEnum;
import com.tyyd.ywpt.biz.dict.SysTypeEnum;
import com.tyyd.ywpt.dao.alert.record.dataobject.AlertRecordDomain;
import com.tyyd.ywpt.dao.alert.record.dataobject.MoitorAlertRecordDomain;
import com.tyyd.ywpt.dao.core.collect.disk.DiskMonitorHisDAO;
import com.tyyd.ywpt.dao.core.collect.disk.dataobject.DiskMonitorHisDomain;
import com.tyyd.ywpt.schedule.alarm.AbstractMemoryAlarmAnalyseBase;
import com.tyyd.ywpt.schedule.alarm.dataobject.AlaramContent;
import com.tyyd.ywpt.schedule.alarm.dataobject.AlarmNotifyTypeEnum;
import com.tyyd.ywpt.schedule.alarm.util.AlarmKeyReplaceUtils;

/**
 * @author wangyu
 *
 */
public class DiskAnalyseAlarm extends AbstractMemoryAlarmAnalyseBase<DiskMonitorHisDomain>{

	private DiskMonitorHisDAO diskMonitorHisDAO;
	
	private AlaramContent diskAlarmContent;


	@Override
	public List<DiskMonitorHisDomain> listUnCompetedTask() {
		return diskMonitorHisDAO.getDiskMonitorUnCompletedData();
	}

	@Override
	public void closeUnCompletedTask(List<DiskMonitorHisDomain> dataList) {
		if(CollectionUtils.isEmpty(dataList)){
			return ;
		}
		
		for(DiskMonitorHisDomain domain : dataList){
			diskMonitorHisDAO.closeUnCompletedTaskById(domain.getId());
		}
	}

	@Override
	public List<AlertRecordDomain> analyData(List<DiskMonitorHisDomain> dataList) {
		List<AlertRecordDomain> result = new ArrayList<AlertRecordDomain>();
		if(CollectionUtils.isEmpty(dataList)){
			return result;
		}
		for(DiskMonitorHisDomain domain : dataList){
			
			//获取对象
			DiskMonitorHisDomain obj = this.getDiskHisDomain(domain.getId());
			
			//没配置预警阈值的数据，直接丢掉
			if(null == obj){
				LOGGER.info("未配置磁盘预警阈值,disk_his_id="+domain.getId());
				continue;
			}
				
			
			//判断预警的类型
			int legelData = this.illegalData(obj);
			
			//合法数据不需要预警
			if(NumberUtils.INTEGER_ZERO.intValue() == legelData) {
				continue; 
			}
			
			LOGGER.info("磁盘预警 ,disk_his_id="+domain.getId());
			
			//设置domain的预警等级 
			obj.setWarnLevel(legelData);
			AlertRecordDomain alertRecord = this.convertAlertRecordDomain(obj, legelData);
			
			result.add(alertRecord);
		}
		
		return result;
	}

	
	
	/* 
	 * 
	 * 这个方法弃用，数据从analyData中拿到
	 * 
	 */
	@Override
	@Deprecated
	public List<MoitorAlertRecordDomain> reduceAlertRecord(
			List<DiskMonitorHisDomain> dataList) {
		
		List<MoitorAlertRecordDomain> result = new ArrayList<MoitorAlertRecordDomain>();
		if(CollectionUtils.isNotEmpty(dataList)){
			Vector<String> keys = new Vector<String>();
			for(DiskMonitorHisDomain domain : dataList){
				MoitorAlertRecordDomain alertRecord = new MoitorAlertRecordDomain();
				
				alertRecord.setMonitorId(domain.getHostId());
				alertRecord.setSysType(SysTypeEnum.server.getVal());
				
				
				//设置内容
				AlaramContent alarm = getAlaramContent(domain,diskAlarmContent);
				alertRecord.setContent(alarm.getMonitorAlertContent());
				alertRecord.setGmtCreated(domain.getGmtCreated());
				
				alertRecord.setMonitorType(AlertRecordMonitorTypeEnum.disk.getVal());
				alertRecord.setNoticeLevel(domain.getWarnLevel());
				
				//磁盘名暂时存在指标名中
				alertRecord.setQuotaName(domain.getDiskName());
				
				String key = domain.getHostId()+":"+domain.getDiskName();
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
	
	@Override
	public void init() {
		setWorker("[磁盘预警]");
	}

	@Override
	protected AlarmKeyReplaceUtils initAlarmKeyReplaceUtils(
			DiskMonitorHisDomain domain) {
		
		Long warnValue = domain.getWaringQuotaValue();
		if(domain.getWarnLevel().intValue() == AlertRecordNoticeLevelEnum.critical.getVal().intValue()){
			warnValue = domain.getCriticalQuotaValue();
		}
		
		AlarmKeyReplaceUtils obj = new AlarmKeyReplaceUtils(domain.getIpAddr(),
				domain.getNickName(), 
				domain.getThresholdValue(), domain.getThresholdUnit(),domain.getDiskName(),
				domain.getGmtCreated(),warnValue);
		
		return obj;
	}

	/**
	 * @return the diskMonitorHisDAO
	 */
	public DiskMonitorHisDAO getDiskMonitorHisDAO() {
		return diskMonitorHisDAO;
	}

	/**
	 * @param diskMonitorHisDAO the diskMonitorHisDAO to set
	 */
	public void setDiskMonitorHisDAO(DiskMonitorHisDAO diskMonitorHisDAO) {
		this.diskMonitorHisDAO = diskMonitorHisDAO;
	}

	/**
	 * @return the diskAlarmContent
	 */
	public AlaramContent getDiskAlarmContent() {
		return diskAlarmContent;
	}

	/**
	 * @param diskAlarmContent the diskAlarmContent to set
	 */
	public void setDiskAlarmContent(AlaramContent diskAlarmContent) {
		this.diskAlarmContent = diskAlarmContent;
	}
	
	
	/**
	 * 不满足规则的数据，即需要发预警的数据
	 * @return
	 */
	private Integer illegalData(DiskMonitorHisDomain domain){
		Integer result = 0;
		if(domain == null || domain.getThresholdType() == null){
			return result;
		}
		
		//阈值
		Float warnThresholdValue = NumberUtils.toFloat(domain.getWaringQuotaValue().toString());
		Float criticalThresholdValue =  NumberUtils.toFloat(domain.getCriticalQuotaValue().toString());
		Float diskRemainValue = domain.getRemain();
		Float diskPercentValue = domain.getUsedPercent();
		
		if(domain.getThresholdType().intValue() == DiskThresholdTypeEnum.Percent_Threshold.getType()){
			//百分比的预警方式
			
			//达到告警的阈值
			if(diskPercentValue - warnThresholdValue > 0){
				result = 1 + result;
			}
			
			//达到致命告警的阈值
			if(diskPercentValue - criticalThresholdValue > 0){
				result = 1 + result;
			}
			
		}else if(domain.getThresholdType().intValue() == DiskThresholdTypeEnum.Free_Threshold.getType()){
			//剩余容量的比较方式
			
			//达到告警的阈值
			if(warnThresholdValue - diskRemainValue > 0){
				result = 1 + result;
			}
			
			//达到致命告警的阈值
			if(criticalThresholdValue - diskRemainValue > 0){
				result = 1 + result;
			}
		}
		
		
		return result;
	}
	
	private DiskMonitorHisDomain getDiskHisDomain(String id){
		DiskMonitorHisDomain obj = null;
		try{
			obj = diskMonitorHisDAO.getDiskMonitorUnCompletedObj(id);
		}catch(Exception e){
			LOGGER.error("磁盘信息获取异常，具体历史数据为 : "+id, e);
		}
		
		return obj;
	}
	
	
	
	/**
	 * 转化对象
	 * @param domain
	 * @param legelFlag
	 * @return
	 */
	private AlertRecordDomain convertAlertRecordDomain(DiskMonitorHisDomain domain,int legelFlag){
		AlertRecordDomain alertRecord = new AlertRecordDomain();
		
		alertRecord.setMonitorId(domain.getHostId());
		alertRecord.setSysType(SysTypeEnum.server.getVal());
		
		alertRecord.setNotifyType(AlarmNotifyTypeEnum.mixed.getNotifyType());
		
		//设置标题和内容
		AlaramContent alarm = getAlaramContent(domain,diskAlarmContent);
		alertRecord.setContent(alarm.getMailContent());
		alertRecord.setTitle(alarm.getMailTitle());
		alertRecord.setGmtCreated(domain.getGmtCreated());
		
		alertRecord.setMonitorType(AlertRecordMonitorTypeEnum.disk.getVal());
		
		//alertRecord.setNoticeLevel(domain.getWarnLevel());
		alertRecord.setNoticeLevel(legelFlag);
		
		//磁盘名暂时存在指标名中
		alertRecord.setQuotaName(domain.getDiskName());
		
		return alertRecord;
	}
}
