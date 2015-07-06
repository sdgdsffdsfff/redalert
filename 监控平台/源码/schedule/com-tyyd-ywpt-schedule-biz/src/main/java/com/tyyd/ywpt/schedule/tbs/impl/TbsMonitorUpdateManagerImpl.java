/**   
* @Title: TbsMonitorUpdateManagerImpl.java 
* @Package com.tyyd.ywpt.schedule.tbs.impl 
* @Description:  
* @author wangyu   
* @date 2015-6-4 下午2:30:52 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.tbs.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tyyd.ywpt.biz.dict.SysTypeEnum;
import com.tyyd.ywpt.dao.configration.db.DbConfigDAO;
import com.tyyd.ywpt.dao.configration.db.dataobject.DbConfigDomain;
import com.tyyd.ywpt.dao.core.collect.datafile.DbTableSpaceDAO;
import com.tyyd.ywpt.dao.core.collect.datafile.DbTableSpaceHisDAO;
import com.tyyd.ywpt.dao.core.collect.datafile.dataobject.DbTableSpaceDomain;
import com.tyyd.ywpt.dao.core.collect.datafile.dataobject.DbTableSpaceHisDomain;
import com.tyyd.ywpt.schedule.tbs.TbsMonitorUpdateManager;

/**
 * @author wangyu
 *
 */
public class TbsMonitorUpdateManagerImpl implements TbsMonitorUpdateManager {

	private static final Logger LOGGER = Logger.getLogger(TbsMonitorUpdateManagerImpl.class);
	
	@Resource
	private DbConfigDAO dbConfigDAO;
	
	@Resource
	private DbTableSpaceDAO dbTableSpaceDAO;
	
	@Resource
	private DbTableSpaceHisDAO dbTableSpaceHisDAO;
	
	
	@Override
	public void doTask() {
		List<DbConfigDomain> oracleList = dbConfigDAO.listNormalDbConfig(SysTypeEnum.Oracle.getVal());
		List<DbConfigDomain> mysqlList = dbConfigDAO.listNormalDbConfig(SysTypeEnum.MySQL.getVal());
		List<DbConfigDomain> dbList = new ArrayList<DbConfigDomain>();
		dbList.addAll(oracleList);
		dbList.addAll(mysqlList);
		
		if(CollectionUtils.isNotEmpty(dbList)){
			for(DbConfigDomain domain : dbList){
				this.doSingleTask(domain.getDbId());
			}
		}
		
	}

	@Override
	public void doSingleTask(String dbId) {
		LOGGER.info(String.format("开始更新表空间数据 {dbId:%s}", dbId));
		
		long start = System.currentTimeMillis();
		
		//获取最大的snap
		Long snapId = this.getMaxSnap(dbId);
		if(snapId == null){
			return;
		}
		
		//获取最后一次采集的信息
		List<DbTableSpaceHisDomain> lastTbsHisList = dbTableSpaceHisDAO.listLastTbsMonitorHis(dbId, snapId);
		if(CollectionUtils.isEmpty(lastTbsHisList)){
			return;
		}
		
		//当前的表空间信息
		List<DbTableSpaceDomain> list = dbTableSpaceDAO.listDbTableSpace(dbId);

		//获取最新的信息
		list = this.reNewTbsMonitorInfo(list,lastTbsHisList);
		
		//保存
		saveNewTbsList(list);
		
		LOGGER.info(String.format("更新表空间数据结束 {dbId:%s,used:%s}", dbId,(System.currentTimeMillis()-start)));
	}

	

	/**
	 * @param list
	 */
	private void saveNewTbsList(List<DbTableSpaceDomain> list) {
		if(CollectionUtils.isNotEmpty(list)){
			for(DbTableSpaceDomain domain : list){
				if(domain.isNew()){
					dbTableSpaceDAO.addDbTableSpace(domain);
				}else{
					dbTableSpaceDAO.updateDbTableSpaceInfo(domain.getDbId(),
							domain.getTbsName(), domain.getUsedTbs(),
							domain.getMaxTbs(), domain.getUsePercent());
				}
			}
		}
	}


	/**
	 * @param list
	 * @param lastDiskHisList
	 * @return
	 */
	private List<DbTableSpaceDomain> reNewTbsMonitorInfo(
			List<DbTableSpaceDomain> list,
			List<DbTableSpaceHisDomain> lastTbsHisList) {
		
		List<DbTableSpaceDomain> newList = new ArrayList<DbTableSpaceDomain>();
		if(CollectionUtils.isEmpty(list)){
			//全部新增
			for(DbTableSpaceHisDomain his : lastTbsHisList){
				if(StringUtils.isBlank(his.getDbId()) || StringUtils.isBlank(his.getTbsName())){
					continue;
				}
				
				DbTableSpaceDomain obj = this.copyBean(his,true);
				newList.add(obj);
			}
			return newList;
		}
		
		for(DbTableSpaceHisDomain his : lastTbsHisList){
			if(StringUtils.isBlank(his.getDbId()) || StringUtils.isBlank(his.getTbsName())){
				continue;
			}
			
			//判断是否新增
			boolean isNew = this.isNewDisk(his.getDbId(), his.getTbsName());
			DbTableSpaceDomain obj = this.copyBean(his, isNew);
			newList.add(obj);
		}
		return newList;
		
	}


	/**
	 * 是否存在
	 * @param hostId
	 * @param diskName
	 * @return
	 */
	private boolean isNewDisk(String dbId, String tbsName) {

		DbTableSpaceDomain domain = dbTableSpaceDAO.getDbTableSpaceMonitorById(dbId, tbsName);
		if(domain == null){
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}


	/**
	 * @param his
	 * @return
	 */
	private DbTableSpaceDomain copyBean(DbTableSpaceHisDomain his,boolean isNew) {
		DbTableSpaceDomain domain = new DbTableSpaceDomain();
		if(his != null){
			domain.setDbId(his.getDbId());
			domain.setTbsName(his.getTbsName());
			domain.setUsedTbs(his.getUsedTbs());
			domain.setMaxTbs(his.getMaxTbs());
			domain.setUsePercent(his.getUsePercent());

			domain.setNew(isNew);
			if(isNew){
				domain.setThresholdType(1); //默认百分比预警
				domain.setCriticalQuotaValue(98f); //默认98%
				domain.setWarnQuotaValue(95f); //默认95%
			}
			return domain;
		}
		
		return null;
	}


	/**
	 * @param hostId
	 * @return
	 */
	private Long getMaxSnap(String dbId) {
		String maxGmtCreated = dbTableSpaceHisDAO.getMaxSnapGmtDate(dbId);
		if(StringUtils.isBlank(maxGmtCreated)){
			return null;
		}
		
		Long snapId = dbTableSpaceHisDAO.getMaxSnapId(dbId, maxGmtCreated);
		return snapId;
	}

	
	
}
