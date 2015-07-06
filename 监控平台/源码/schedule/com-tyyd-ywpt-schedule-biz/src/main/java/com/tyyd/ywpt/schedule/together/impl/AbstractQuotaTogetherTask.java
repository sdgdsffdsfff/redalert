/**   
* @Title: AbstractQuotaTogetherTask.java 
* @Package com.tyyd.ywpt.schedule.together.impl 
* @Description:  
* @author wangyu   
* @date 2014-9-23 上午11:29:04 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.together.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tyyd.ywpt.biz.dict.SysTypeEnum;
import com.tyyd.ywpt.biz.dict.TogetherStatTypeEnum;
import com.tyyd.ywpt.biz.util.DateUtils;
import com.tyyd.ywpt.dao.core.collect.together.log.StatisticsRecordLogDAO;
import com.tyyd.ywpt.dao.core.collect.together.log.dataobject.StatisticsRecordLogDomain;
import com.tyyd.ywpt.schedule.together.MonitorQuotaTogetherManager;
import com.tyyd.ywpt.schedule.together.dataobject.QueryDateTogetherDomain;
import com.tyyd.ywpt.tools.bean.SpringContextHolder;

/**
 * @author wangyu
 *
 */
public abstract class AbstractQuotaTogetherTask {

	
	public static final Logger LOGGER = Logger.getLogger(AbstractQuotaTogetherTask.class);
	
	/**
	 * 默认最早的开始时间
	 */
	protected static final String DEFAULT_START_DATE = "2014-09-15 00:00:01";
	protected static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	
	protected void doTask(Integer sysType,Integer statType){
		LOGGER.info(String.format("汇总开始,系统类型:%s,统计类型:%s",SysTypeEnum.getEnum(sysType),TogetherStatTypeEnum.getEnum(statType)));
		//计算时间
		QueryDateTogetherDomain queryDomain = calQueryDateTogether(sysType,statType);
		
		//检查日期
		boolean canDoTask = checkQuotaData(queryDomain,sysType,statType);
		
		if(canDoTask){
			//统计
			getMonitorQuotaTogetherManager().quotaTogether(queryDomain.getStateDate(), queryDomain.getEndDate(), sysType, statType);
			
			//记录日志 
			checkInTogetherLog(queryDomain,sysType,statType);
		}else{
			String info = String.format("本次Together汇总退出,参数[开始时间 : %s,结束时间 : %s,系统类型: %s,统计类型: %s]",
					queryDomain.getStateDate(),
					queryDomain.getEndDate(),
					SysTypeEnum.getEnum(sysType),
					TogetherStatTypeEnum.getEnum(statType));
			LOGGER.info(info);
		}
	
		LOGGER.info(String.format("汇总结束,系统类型:%s,统计类型:%s",SysTypeEnum.getEnum(sysType),TogetherStatTypeEnum.getEnum(statType)));
	}
	
	


	/**
	 * @param queryDomain
	 * @param sysType
	 * @param statType
	 */
	private void checkInTogetherLog(QueryDateTogetherDomain queryDomain,
			Integer sysType, Integer statType) {

		StatisticsRecordLogDomain domain = getStatisticsRecordLogDAO().getStatisticsRecord(sysType, statType);
		if(domain == null){
			//添加一条记录
			StatisticsRecordLogDomain newDomain = new StatisticsRecordLogDomain();
			newDomain.setStatType(statType);
			newDomain.setSysType(sysType);
			newDomain.setWorkTimeStr(queryDomain.getEndDate());
			getStatisticsRecordLogDAO().addStatisticsRecordLog(newDomain);
		}else{
			getStatisticsRecordLogDAO().updateStatisticsRecordLog(queryDomain.getEndDate(), sysType, statType);
		}
	}




	/**
	 * 
	 * @param queryDomain
	 * @param sysType
	 * @param statType
	 * @return
	 */
	private boolean checkQuotaData(QueryDateTogetherDomain queryDomain,
			Integer sysType, Integer statType) {
		
		boolean isCanDoTask = false;
		if(queryDomain == null 
				|| StringUtils.isBlank(queryDomain.getStateDate()) 
				|| StringUtils.isBlank(queryDomain.getStateDate())){
			return isCanDoTask;
		}
		
		Date endDate = DateUtils.StringToDate(queryDomain.getEndDate(), DATE_FORMAT);
		String nowDate = DateUtils.getCurrentDateTime();
		//检查结束时间是否落在当前日期内
		if(TogetherStatTypeEnum.HOUR.getVal().intValue() == statType.intValue()){
			//是否大于一个小时
			String targetDate = DateUtils.addHour(endDate, 1);
			int delay = DateUtils.compareDate(nowDate, targetDate);
			isCanDoTask = (delay > 0)?true:false;
			
		}else if(TogetherStatTypeEnum.DAY.getVal().intValue() == statType.intValue()){
			//是否大于一个天
			int delay = DateUtils.compareYmdDate(nowDate, queryDomain.getEndDate());
			isCanDoTask = (delay >= 0)?true:false;
			
		}else if(TogetherStatTypeEnum.WEEK.getVal().intValue() == statType.intValue()){
			//是否大于1天
			String targetDate = DateUtils.addDate(endDate, 1);
			int delay = DateUtils.compareYmdDate(nowDate, targetDate);
			isCanDoTask = (delay >= 0)?true:false;
		}else if(TogetherStatTypeEnum.MONTH.getVal().intValue() == statType.intValue()){
			//是否大于1天
			String targetDate = DateUtils.addDate(endDate, 1);
			int delay = DateUtils.compareYmdDate(nowDate, targetDate);
			isCanDoTask = (delay >= 0)?true:false;
		}
		
		
		return isCanDoTask;
	}




	/**
	 * 
	 * @param statType
	 * @return
	 */
	private QueryDateTogetherDomain calQueryDateTogether(Integer sysType,Integer statType) {
		
		StatisticsRecordLogDomain domain = getStatisticsRecordLogDAO().getStatisticsRecord(sysType, statType);
		String startDate = null;
		String endDate = null;
		if(domain == null || domain.getWorkTime() == null){
			startDate = DEFAULT_START_DATE;
		}else{
			Date workTime = domain.getWorkTime();
			startDate = DateUtils.getStandardDate(workTime);
		}
		
		Date workDate = DateUtils.StringToDate(startDate, DATE_FORMAT);
		
		if(TogetherStatTypeEnum.HOUR.getVal().intValue() == statType.intValue()){
			endDate = DateUtils.addHour(workDate, 4);
			
		}else if(TogetherStatTypeEnum.DAY.getVal().intValue() == statType.intValue()){
			endDate = DateUtils.addDate(workDate, 1);
			
		}else if(TogetherStatTypeEnum.WEEK.getVal().intValue() == statType.intValue()){
			endDate = DateUtils.addWeek(workDate, 1);
			
		}else if(TogetherStatTypeEnum.MONTH.getVal().intValue() == statType.intValue()){
			endDate = DateUtils.addMonth(workDate, 1);
			
		}
		
		QueryDateTogetherDomain result = new QueryDateTogetherDomain();
		result.setStateDate(startDate);
		result.setEndDate(endDate);
		
		return result;
	}


	/**
	 * 指标统计
	 */
	public abstract void doQuotaTask();
	
	
	
	
	
	
	/**
	 * 指标统计接口
	 * @return
	 */
	private MonitorQuotaTogetherManager getMonitorQuotaTogetherManager(){
		return SpringContextHolder.getBean("monitorQuotaTogetherManager");
	}
	
	
	/**
	 * 日志记录
	 * @return
	 */
	private StatisticsRecordLogDAO getStatisticsRecordLogDAO(){
		return SpringContextHolder.getBean("statisticsRecordLogDAO");
	}
}



