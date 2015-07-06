/**   
* @Title: BaseLineMonitorTaskImpl.java 
* @Package com.tyyd.ywpt.schedule.baseline.task.impl 
* @Description:  
* @author wangyu   
* @date 2015-3-11 下午4:05:47 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.baseline.task.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.tyyd.ywpt.biz.dict.SysTypeEnum;
import com.tyyd.ywpt.dao.baseline.BaseLineConfigDAO;
import com.tyyd.ywpt.dao.baseline.dataobject.BaseLineConfigDomain;
import com.tyyd.ywpt.dao.configration.db.DbConfigDAO;
import com.tyyd.ywpt.dao.configration.db.dataobject.DbConfigDomain;
import com.tyyd.ywpt.dao.configration.host.HostConfigDAO;
import com.tyyd.ywpt.dao.configration.host.dataobject.HostConfigDomain;
import com.tyyd.ywpt.schedule.baseline.facade.BaseLineMonitorCalMetaDataFacade;
import com.tyyd.ywpt.schedule.baseline.task.BaseLineMonitorTask;

/**
 * @author wangyu
 *
 */
public class BaseLineMonitorTaskImpl implements BaseLineMonitorTask{

	@Resource
	private BaseLineMonitorCalMetaDataFacade baseLineMonitorCalMetaDataFacade;
	
	@Resource
	private DbConfigDAO dbConfigDAO;
	
	@Resource
	private HostConfigDAO hostConfigDAO;
	
	@Resource
	private BaseLineConfigDAO baseLineConfigDAO;
	
	//最大10个线程
	private final static int max_nThreads = 10;
	
	@Override
	public void runMonitorTask() {
		
		//设置一个固定长度的线程池
		ExecutorService service = Executors.newFixedThreadPool(max_nThreads);
		
		//获取对应的Db
		List<DbConfigDomain> oracleDbConfigs = dbConfigDAO.listNormalDbConfig(SysTypeEnum.Oracle.getVal());
		List<DbConfigDomain> mysqlDbConfigs = dbConfigDAO.listNormalDbConfig(SysTypeEnum.MySQL.getVal());
		
		List<DbConfigDomain> arrayList = new ArrayList<DbConfigDomain>();
		arrayList.addAll(oracleDbConfigs);
		arrayList.addAll(mysqlDbConfigs);
		
		List<HostConfigDomain> serverConfigs = hostConfigDAO.listNormalHostConfig();
		//主机
		for(HostConfigDomain domain : serverConfigs){
			List<BaseLineConfigDomain> baseLineConfigs = baseLineConfigDAO.listBaseLineConfig(domain.getHostId(), SysTypeEnum.server.getVal());
			this.submitTask(service, baseLineConfigs);
		}
		
		
		//数据库
		for(DbConfigDomain dbConfig : arrayList){
			List<BaseLineConfigDomain> baseLineConfigs = baseLineConfigDAO.listBaseLineConfig(dbConfig.getDbId(), dbConfig.getDbType());
			this.submitTask(service, baseLineConfigs);
		}
	}

	
	
	/**
	 * 提交作业
	 * @param baseLineConfigs
	 */
	private void submitTask(ExecutorService service,List<BaseLineConfigDomain> baseLineConfigs){
		if(CollectionUtils.isNotEmpty(baseLineConfigs)){
			for(BaseLineConfigDomain baseLineConfigDomain : baseLineConfigs){
				BaseLineMonitorTaskRunnable task = new BaseLineMonitorTaskRunnable(baseLineConfigDomain);
				service.submit(task);
			}
		}
	}
	
	class BaseLineMonitorTaskRunnable implements Runnable{

		@Override
		public void run() {
			baseLineMonitorCalMetaDataFacade.doCalMetaData(
					this.baseLineConfigDomain.getMonitorId(),
					this.baseLineConfigDomain.getConfigType(),
					this.baseLineConfigDomain.getQuotaId());
		}
		
		private BaseLineConfigDomain baseLineConfigDomain;

		/**
		 * @param dbConfig
		 */
		private BaseLineMonitorTaskRunnable(BaseLineConfigDomain _baseLineConfigDomain) {
			this.baseLineConfigDomain = _baseLineConfigDomain;
		}
	}
	
}
