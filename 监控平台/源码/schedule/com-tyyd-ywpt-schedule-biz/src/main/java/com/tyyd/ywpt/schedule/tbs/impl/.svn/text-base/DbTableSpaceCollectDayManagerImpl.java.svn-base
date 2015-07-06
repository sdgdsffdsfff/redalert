/**   
* @Title: DbTableSpaceCollectDayManagerImpl.java 
* @Package com.tyyd.ywpt.schedule.tbs.impl 
* @Description:  
* @author wangyu   
* @date 2015-6-1 下午1:29:27 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.tbs.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tyyd.ywpt.biz.dict.SysTypeEnum;
import com.tyyd.ywpt.biz.util.DateUtils;
import com.tyyd.ywpt.dao.configration.db.DbConfigDAO;
import com.tyyd.ywpt.dao.configration.db.dataobject.DbConfigDomain;
import com.tyyd.ywpt.dao.core.collect.datafile.DbTableSpaceCollectDayDAO;
import com.tyyd.ywpt.dao.core.collect.datafile.dataobject.DbTableSpaceCollectDayDomain;
import com.tyyd.ywpt.schedule.tbs.DbTableSpaceCollectDayManager;

/**
 * @author wangyu
 *
 */
public class DbTableSpaceCollectDayManagerImpl implements DbTableSpaceCollectDayManager{

	private static final Logger LOGGER = Logger.getLogger(DbTableSpaceCollectDayManagerImpl.class);
	
	
	@Resource
	private DbTableSpaceCollectDayDAO dbTableSpaceCollectDayDAO;
	
	@Resource
	private DbConfigDAO dbConfigDAO;

	
	
	/**
	 * 生成数据
	 * @param hostId
	 * @param collectDate
	 */
	private void buildTbsDay(String dbId, String collectDate) {
		
		LOGGER.info(String.format("开始生成日数据 {hostId:%s,collectDate:%s}", dbId,collectDate));
		
		long start = System.currentTimeMillis();
		//获取该时间最后一次的snapId
		String nextDay = DateUtils.addDateByFormat(DateUtils.StringToDate(collectDate, "yyyyMMdd"), 1, "yyyyMMdd");
		
		String snapId = dbTableSpaceCollectDayDAO.getMaxSnapId(dbId, collectDate, nextDay);
		if(StringUtils.isBlank(snapId)){
			LOGGER.error(String.format("开始生成日表空间数据 {dbId:%s,collectDate:%s}有错，原因是未获取snapId", dbId,collectDate));
			return;
		}
		
		//先删除可能存在的数据
		dbTableSpaceCollectDayDAO.delCurrentTbsData(dbId, collectDate);
		
		//添加数据
		dbTableSpaceCollectDayDAO.collectTbsInfo(dbId, collectDate, snapId, 0f);
		
		//获取昨日的统计数据
		Date current = DateUtils.StringToDate(collectDate, "yyyyMMdd");
		String lastDay = DateUtils.addDateByFormat(current, -1,"yyyyMMdd");
		List<DbTableSpaceCollectDayDomain> lastDayDiskList = dbTableSpaceCollectDayDAO.getLastDayTbsInfo(dbId, lastDay); 
		
		//更新上一次的数据
		if(CollectionUtils.isNotEmpty(lastDayDiskList)){
			for(DbTableSpaceCollectDayDomain obj : lastDayDiskList){
				if(obj != null && StringUtils.isNotBlank(obj.getTbsName())){
					Float lastDayUsed = (obj.getUsedTbs() == null ) ? 0f : obj.getUsedTbs();
					
					//更新
					dbTableSpaceCollectDayDAO.updateTbsInfo(dbId, collectDate, obj.getTbsName(), lastDayUsed);
				}
			}
		}
		
		long end = System.currentTimeMillis();
		LOGGER.info(String.format("开始生成日表空间数据 {dbId:%s,collectDate:%s}完成，总计花费%s毫秒", dbId,collectDate,(end-start)));
	}


	@Override
	public void doTask() {
		LOGGER.info(String.format("开始调度表空间生成"));
		long start = System.currentTimeMillis();
		
		List<DbConfigDomain> oracleList = dbConfigDAO.listNormalDbConfig(SysTypeEnum.Oracle.getVal());
		List<DbConfigDomain> mysqlList = dbConfigDAO.listNormalDbConfig(SysTypeEnum.MySQL.getVal());
		
		List<DbConfigDomain> dbList = new ArrayList<DbConfigDomain>();
		dbList.addAll(oracleList);
		dbList.addAll(mysqlList);
		
		
		
		if(CollectionUtils.isNotEmpty(dbList)){
			for(DbConfigDomain domain : dbList){
				
				//生成数据
				this.doDataBaseTask(domain.getDbId());
				
			}
		}
		
		long end = System.currentTimeMillis();
		LOGGER.info(String.format("调度表空间生成完成，总计花费%s毫秒",(end-start)));
	}


	/**
	 * @param hostId
	 * @param startDate
	 */
	private void produceTbsDayInfo(String dbId, String startDate) {
		Date lastDay = DateUtils.getDelayDay(-1);
		Date sDate = DateUtils.StringToDate(startDate, "yyyyMMdd");
		
		int bwt = this.calBeetweenDays(sDate,lastDay);
		
		for(int i=1;i<bwt;i++){
			String tmpDate = DateUtils.addDateByFormat(sDate, i, "yyyyMMdd");
			if(StringUtils.isNotBlank(tmpDate)){
				this.buildTbsDay(dbId, tmpDate);
			}
		}
	}

	
	/**
	 * 计算天数差值
	 * @param startDate
	 * @return
	 */
	private int calBeetweenDays (Date sDate,Date lastDay){
		
		int bwt = DateUtils.daysBetween(sDate , lastDay);
		
		if(bwt <= 0){
			return -1;
		}
		
		return bwt;
	}
	

	/**
	 * @param hostId
	 * @return
	 */
	private String getStartDate(String dbId) {

		if(StringUtils.isBlank(dbId)){
			return StringUtils.EMPTY;
		}
		
		//从统计表中拿最后一次的时间
		String startDate = dbTableSpaceCollectDayDAO.getMaxTbsCollectDate(dbId);
		if(StringUtils.isBlank(startDate)){
			Date lastMonth = DateUtils.getDelayDay(-30);
			String strDate = DateUtils.DateToString(lastMonth, "yyyy-MM-dd");
			startDate = dbTableSpaceCollectDayDAO.getMinTbsHisCollectDate(dbId, strDate);
		}
		
		if(StringUtils.isBlank(startDate)){
			return StringUtils.EMPTY;
		}
		
		return startDate;
	}


	@Override
	public void doDataBaseTask(String dbId) {
		
		//获取最后一次时间
		String startDate = this.getStartDate(dbId);
		
		if(StringUtils.isNotBlank(startDate)){
			//统计数据
			this.produceTbsDayInfo(dbId, startDate);
		}
		
	}

}
