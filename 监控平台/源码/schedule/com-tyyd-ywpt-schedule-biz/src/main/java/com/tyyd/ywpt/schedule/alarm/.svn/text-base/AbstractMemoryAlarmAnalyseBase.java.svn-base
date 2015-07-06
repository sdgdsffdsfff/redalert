/**   
* @Title: AbstractMemoryAlarmAnalyseBase.java 
* @Package com.tyyd.ywpt.schedule.alarm 
* @Description:  
* @author wangyu   
* @date 2015-1-27 下午6:16:08 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.alarm;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.tyyd.ywpt.biz.dict.QuotaThresholdTypeEnum;
import com.tyyd.ywpt.dao.alert.record.dataobject.AlertRecordDomain;
import com.tyyd.ywpt.dao.alert.record.dataobject.MoitorAlertRecordDomain;

/**
 * @author wangyu
 *
 */
public abstract class AbstractMemoryAlarmAnalyseBase<T> extends AbstractAlarmAnalyseBase<T> {

	public void doTask(){
		long start = System.currentTimeMillis();
		LOGGER.info(getWorker() + " 任务开始...");
		
		//获取数据
		LOGGER.info(String.format("%s 获取待处理的任务...", getWorker()));
		List<T> taskList = listUnCompetedTask();
		
		//分析
		LOGGER.info(String.format("%s 开始分析数据...", getWorker()));
		List<AlertRecordDomain> alertDataList = analyData(taskList);
		
		//归并告警
		LOGGER.info(String.format("%s 开始归并告警数据...", getWorker()));
		List<MoitorAlertRecordDomain> monitorAlertList = reduceAlertRecordFromAnanlyData(alertDataList);
		
		//处理关闭数据开始
		LOGGER.info(String.format("%s 关闭任务...", getWorker()));
		closeUnCompletedTask(taskList);
		
		//入库
		LOGGER.info(String.format("%s 开始保存数据...", getWorker()));
		saveDataToDB(alertDataList);
		
		//存入归并数据
		LOGGER.info(String.format("%s 开始保存数据...", getWorker()));
		saveMonitorDataToDB(monitorAlertList);
		
		long end = System.currentTimeMillis();
		long milis = (end - start)/1000;
		LOGGER.info(String.format("%s 任务结束...,共花费[%d]秒时间", getWorker(), milis));
	}
	
	
	/**
	 * 归并类的数据收集（预计在不同类型的预警，传入的值可能还会有变化，先暂时考虑disk和tabspace）
	 * @param taskList
	 * @return
	 */
	public List<MoitorAlertRecordDomain> reduceAlertRecordFromAnanlyData(List<AlertRecordDomain> taskList){
		
		List<MoitorAlertRecordDomain> result = new ArrayList<MoitorAlertRecordDomain>();
		if(CollectionUtils.isNotEmpty(taskList)){
			for(AlertRecordDomain domain : taskList){
				MoitorAlertRecordDomain alertRecord = new MoitorAlertRecordDomain();
				
				alertRecord.setMonitorId(domain.getMonitorId());
				alertRecord.setSysType(domain.getSysType());
				
				
				//设置内容
				alertRecord.setContent(domain.getContent());
				alertRecord.setGmtCreated(domain.getGmtCreated());
				
				alertRecord.setMonitorType(domain.getMonitorType());
				alertRecord.setNoticeLevel(domain.getNoticeLevel());
				
				alertRecord.setQuotaName(domain.getQuotaName());
				//指标类
				alertRecord.setQuotaId(domain.getQuotaId());
				
				
				result.add(alertRecord);
			}
		}
		
		return result;
	}
	
	@Override
	public List<MoitorAlertRecordDomain> reduceAlertRecord(List<T> taskList){
		//旧的方式，合并的数据丢弃，从另外一个地方获取数据，直接覆盖父类
		return null;
	}
	
	/* 
	 * 不需要实现
	 */
	@Override
	public void closeNormalUnCompletedTask() {}
	
	
	/**
	 * 比大小，大的预警
	 * @param warnThresholdValue
	 * @param criticalThresholdValue
	 * @param quotaValue
	 * @return
	 */
	protected Integer calculateBigQuotaWarnLevel(Float warnThresholdValue,Float criticalThresholdValue,Float quotaValue){
		int result = 0;
		
		//达到告警的阈值
		if(quotaValue - warnThresholdValue > 0){
			result = 1 + result;
		}
		
		//达到致命告警的阈值
		if(quotaValue - criticalThresholdValue > 0){
			result = 1 + result;
		}
		
		return result;
	}
	
	
	/**
	 * 比大小，小的预警
	 * @param warnThresholdValue
	 * @param criticalThresholdValue
	 * @param quotaValue
	 * @return
	 */
	protected Integer calculateSmallQuotaWarnLevel(Float warnThresholdValue,Float criticalThresholdValue,Float quotaValue){
		int result = 0;
		
		//达到告警的阈值
		if(quotaValue - warnThresholdValue < 0){
			result = 1 + result;
		}
		
		//达到致命告警的阈值
		if(quotaValue - criticalThresholdValue < 0){
			result = 1 + result;
		}
		
		return result;
	}
	
	protected int calculateQuotaWarnLevel(Float warnThresholdValue,Float criticalThresholdValue,Float quotaValue,Integer thresholdType){
		int result = 0;
		
		if(thresholdType.intValue() == QuotaThresholdTypeEnum.compare_big_warn.getType() 
				|| thresholdType.intValue() == QuotaThresholdTypeEnum.d_value_warn.getType()){
			
			//直接比大小，大的预警
			result = this.calculateBigQuotaWarnLevel(warnThresholdValue,criticalThresholdValue,quotaValue);
			
		}else if(thresholdType.intValue() == QuotaThresholdTypeEnum.compare_small_warn.getType()){
			
			//直接比大小，小的预警
			result = this.calculateSmallQuotaWarnLevel(warnThresholdValue,criticalThresholdValue,quotaValue);
		}
		
		
		return result;		
	}
	
}
