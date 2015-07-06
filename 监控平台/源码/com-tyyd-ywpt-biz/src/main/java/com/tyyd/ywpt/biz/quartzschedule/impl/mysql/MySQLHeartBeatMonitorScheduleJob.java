/**   
* @Title: MySQLHeartBeatMonitorScheduleJob.java 
* @Package com.tyyd.ywpt.biz.quartzschedule.impl.mysql 
* @Description:  
* @author wangyu   
* @date 2014-7-7 下午5:43:10 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.quartzschedule.impl.mysql;

import org.apache.commons.lang.StringUtils;
import org.quartz.DisallowConcurrentExecution;

import com.tyyd.ywpt.biz.bean.dataobject.DynamicBeanBO;
import com.tyyd.ywpt.biz.dict.SysTypeEnum;
import com.tyyd.ywpt.biz.quartzschedule.dataobject.QuartzScheduleJobBO;
import com.tyyd.ywpt.biz.quartzschedule.impl.AbstractDataBaseQuotaScheduleQuartzTask;
import com.tyyd.ywpt.dao.configration.db.dataobject.DbConfigDomain;
import com.tyyd.ywpt.dao.core.collect.heartbeat.dataobject.HeartbeatMonitorDomain;
import com.tyyd.ywpt.dao.mysql.MySQLMonitorCollectDAO;
import com.tyyd.ywpt.dao.mysql.impl.MySQLMonitorCollectDAOImpl;
import com.tyyd.ywpt.tools.bean.SpringContextHolder;
import com.tyyd.ywpt.tools.ds.dataobject.DynamicDataSourceDomain;

/**
 * @author wangyu
 *
 */
@DisallowConcurrentExecution
public class MySQLHeartBeatMonitorScheduleJob extends AbstractDataBaseQuotaScheduleQuartzTask{

	@SuppressWarnings("rawtypes")
	@Override
	public void doTask(QuartzScheduleJobBO scheduleJob) {
		//先切换了数据源，直接可以调用DAO
		Object beanObj = SpringContextHolder.getBean("mySQLMonitorCollectDAO");
		if(beanObj instanceof MySQLMonitorCollectDAO 
				|| beanObj instanceof MySQLMonitorCollectDAOImpl){
			MySQLMonitorCollectDAO targetBean = (MySQLMonitorCollectDAO)beanObj;
			
//			if(scheduleJob.getBeanId().equals("datasource_mysql_11")){
//				System.out.println("in");
//			}
			
			String currentDate = null;
			try{
				currentDate = targetBean.getCurrentDate();
			}catch(Exception e){
				LOGGER.error("心跳监测异常",e);
				currentDate = "";
			}
			Integer isActive = 0;
			if(StringUtils.isBlank(currentDate)){
				isActive = 1;
			}
			
			DynamicBeanBO beanDomain = getCustomerBeanListFactory().getBeanProperty(scheduleJob.getBeanId());
			Object obj = beanDomain.getPropertyBean();
			DynamicDataSourceDomain dynamicDataSourceDomain = null;
			if(obj instanceof DynamicDataSourceDomain){
				dynamicDataSourceDomain = (DynamicDataSourceDomain)obj;
			}
			
			//切换数据源
			afterResetDS();
			
			//更新dbconfig
			DbConfigDomain dbConfigDomain = new DbConfigDomain();
			dbConfigDomain.setDbId(dynamicDataSourceDomain.getDbId());
			dbConfigDomain.setLastHeartBeatStatus(isActive);
			getDbConfigDAO().updateDataBaseMonitorStatus(dbConfigDomain);
			
			//写入日志
			getHeartbeatMonitorDAO().addHeartbeatMonitor(
					new HeartbeatMonitorDomain(dynamicDataSourceDomain.getHostId(), dynamicDataSourceDomain
							.getDbId(), isActive, SysTypeEnum.MySQL.getVal()));
			
			
			
		}
		
		
	}
}
