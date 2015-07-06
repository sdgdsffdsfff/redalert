/**   
* @Title: AbstractClearDirtyQuotaTogetherManager.java 
* @Package com.tyyd.ywpt.schedule.together.impl 
* @Description:  
* @author wangyu   
* @date 2014-9-24 下午2:31:03 
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
import com.tyyd.ywpt.schedule.together.MonitorQuotaTogetherManager;
import com.tyyd.ywpt.tools.bean.SpringContextHolder;

/**
 * @author wangyu
 *
 */
public abstract class AbstractClearDirtyQuotaTogetherManager {

	
	public static final Logger LOGGER = Logger.getLogger(AbstractClearDirtyQuotaTogetherManager.class);
	
	
	protected void doTask(Integer sysType,Integer statType){
		LOGGER.info(String.format("清理脏数据,系统类型:%s,统计类型:%s",SysTypeEnum.getEnum(sysType),TogetherStatTypeEnum.getEnum(statType)));
		//计算时间
		String deleteFromDate = calDelFromDate(sysType,statType);
		
		//判断是否应该清理
		boolean canDoTask = confirmDelTask(deleteFromDate,sysType,statType);
		
		//清理脏数据
		if(canDoTask){
			getMonitorQuotaTogetherManager().clearQuotaTogether(deleteFromDate, sysType, statType);
		}else{
			String info = String.format("本次清理退出,参数[时间 : %s,系统类型: %s,统计类型: %s]",
					deleteFromDate,
					SysTypeEnum.getEnum(sysType),
					TogetherStatTypeEnum.getEnum(statType));
			LOGGER.info(info);
		}
		
		LOGGER.info(String.format("清理结束,时间:%s,系统类型:%s,统计类型:%s",deleteFromDate,SysTypeEnum.getEnum(sysType),TogetherStatTypeEnum.getEnum(statType)));
	}
	
	/**
	 * 确认是否需要执行
	 * @param deleteFromDate
	 * @param sysType
	 * @param statType
	 * @return
	 */
	private boolean confirmDelTask(String deleteFromDate, Integer sysType,
			Integer statType) {
		
		if(StringUtils.isBlank(deleteFromDate)){
			return false;
		}
		
		return true;
	}


	/**
	 * 计算截至时间
	 * @param sysType
	 * @param statType
	 * @return
	 */
	private String calDelFromDate(Integer sysType, Integer statType) {
		String deleteFromDate = null;
		Date nowDate = new Date();
		if(TogetherStatTypeEnum.HOUR.getVal().intValue() == statType.intValue()){
			//4小时为单位，只保留18条为单位的数量，即72小时，3天前可以清理
			deleteFromDate = DateUtils.addDate(nowDate, -3);
			
		}else if(TogetherStatTypeEnum.DAY.getVal().intValue() == statType.intValue()){
			//1天为单位，保留一个月的数据，30日,即一个月前可以清理
			deleteFromDate = DateUtils.addDate(nowDate, -30);
			
		}else if(TogetherStatTypeEnum.WEEK.getVal().intValue() == statType.intValue()){
			//周为单位，保留6个月的数据,即6个月前可以清理
			deleteFromDate = DateUtils.addMonth(nowDate, -6);
			
		}else if(TogetherStatTypeEnum.MONTH.getVal().intValue() == statType.intValue()){
			//保留两年数据，即24个月前的数据，可以清理
			deleteFromDate = DateUtils.addMonth(nowDate, -24);
			
		}
		
		
		return deleteFromDate;
	}



	private MonitorQuotaTogetherManager getMonitorQuotaTogetherManager(){
		return SpringContextHolder.getBean("monitorQuotaTogetherManager");
	}
	
	
	
	/**
	 * 清理脏数据
	 * @param sysType
	 * @param statType
	 */
	public abstract void clearQuotaDirtyData();
}
