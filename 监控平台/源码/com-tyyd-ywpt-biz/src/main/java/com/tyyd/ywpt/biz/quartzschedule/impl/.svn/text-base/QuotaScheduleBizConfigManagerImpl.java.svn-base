/**   
* @Title: QuotaScheduleBizConfigManagerImpl.java 
* @Package com.tyyd.ywpt.biz.quartzschedule.impl 
* @Description:  
* @author wangyu   
* @date 2014-7-11 上午10:10:32 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.quartzschedule.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.quartz.Job;

import com.tyyd.ywpt.biz.dict.SysTypeEnum;
import com.tyyd.ywpt.biz.quartzschedule.QuotaScheduleBizConfigManager;
import com.tyyd.ywpt.biz.quartzschedule.dataobject.QuartzScheduleJobBO;
import com.tyyd.ywpt.dao.configration.db.DbConfigDAO;
import com.tyyd.ywpt.dao.configration.db.dataobject.DbConfigDomain;
import com.tyyd.ywpt.dao.configration.host.HostConfigDAO;
import com.tyyd.ywpt.dao.configration.host.dataobject.HostConfigDomain;
import com.tyyd.ywpt.dao.configration.schedule.DeamonScheduleconfigDAO;
import com.tyyd.ywpt.dao.configration.schedule.dataobject.DeamonScheduleconfigDomain;

/**
 * @author wangyu
 *
 */
public class QuotaScheduleBizConfigManagerImpl implements QuotaScheduleBizConfigManager{

	public static final Logger LOGGER = Logger.getLogger(QuotaScheduleBizConfigManagerImpl.class);  
	
	@Resource
	private DbConfigDAO dbConfigDAO;
	
	@Resource
	private HostConfigDAO hostConfigDAO;
	
	@Resource
	private DeamonScheduleconfigDAO deamonScheduleconfigDAO;
	
	/**
	 * 主机的调度任务
	 */
	private List<QuartzScheduleJobBO> getHostSchedule(String serverId){
		List<QuartzScheduleJobBO> jobs = new ArrayList<QuartzScheduleJobBO>();
		List<HostConfigDomain> hostList = hostConfigDAO.listHostConfig(serverId);
		for(HostConfigDomain domain : hostList){
			List<DeamonScheduleconfigDomain> list = deamonScheduleconfigDAO
					.listDeamonScheduleconfigByMonitorId(domain.getHostId(),
							SysTypeEnum.server.getVal());
			
			if(list != null && list.size()>0){
				for(DeamonScheduleconfigDomain scheduleDomain : list){
					QuartzScheduleJobBO bo = convertConfigToScheduleJob(scheduleDomain);
					if(bo == null) 
						continue;
					jobs.add(bo);
				}
			}
		}
		return jobs;
	}

	
	
	/**
	 * Oracle的调度任务
	 * @param serverId
	 * @return
	 */
	private List<QuartzScheduleJobBO> getOracleSchedule(String serverId){
		List<QuartzScheduleJobBO> jobs = new ArrayList<QuartzScheduleJobBO>();
		List<DbConfigDomain> dbList = dbConfigDAO.listDbConfigByServerId(serverId, SysTypeEnum.Oracle.getVal().toString());
		for(DbConfigDomain domain : dbList){
			List<DeamonScheduleconfigDomain> list = deamonScheduleconfigDAO
					.listDeamonScheduleconfigByMonitorId(domain.getDbId(),
							SysTypeEnum.Oracle.getVal());
			
			if(list != null && list.size()>0){
				for(DeamonScheduleconfigDomain scheduleDomain : list){
					QuartzScheduleJobBO bo = convertConfigToScheduleJob(scheduleDomain);
					if(bo == null) 
						continue;
					jobs.add(bo);
				}
			}
		}
		return jobs;
	}
	
	
	
	/**
	 * MySQL的调度任务
	 * @param serverId
	 * @return
	 */
	private List<QuartzScheduleJobBO> getMySQLSchedule(String serverId){
		List<QuartzScheduleJobBO> jobs = new ArrayList<QuartzScheduleJobBO>();
		List<DbConfigDomain> dbList = dbConfigDAO.listDbConfigByServerId(serverId, SysTypeEnum.MySQL.getVal().toString());
		for(DbConfigDomain domain : dbList){
			List<DeamonScheduleconfigDomain> list = deamonScheduleconfigDAO
					.listDeamonScheduleconfigByMonitorId(domain.getDbId(),
							SysTypeEnum.MySQL.getVal());
			
			if(list != null && list.size()>0){
				for(DeamonScheduleconfigDomain scheduleDomain : list){
					QuartzScheduleJobBO bo = convertConfigToScheduleJob(scheduleDomain);
					if(bo == null) 
						continue;
					jobs.add(bo);
				}
			}
		}
		return jobs;
	}
	
	
	/**
	 * MySQL的调度任务
	 * @param serverId
	 * @return
	 */
	private List<QuartzScheduleJobBO> getKeepAlivedSchedule(String serverId){
		List<QuartzScheduleJobBO> jobs = new ArrayList<QuartzScheduleJobBO>();
		List<DbConfigDomain> dbList = dbConfigDAO.listDbConfigByServerId(serverId, SysTypeEnum.KEEPALIVED.getVal().toString());
		for(DbConfigDomain domain : dbList){
			List<DeamonScheduleconfigDomain> list = deamonScheduleconfigDAO
					.listDeamonScheduleconfigByMonitorId(domain.getDbId(),
							SysTypeEnum.KEEPALIVED.getVal());
			
			if(list != null && list.size()>0){
				for(DeamonScheduleconfigDomain scheduleDomain : list){
					QuartzScheduleJobBO bo = convertConfigToScheduleJob(scheduleDomain);
					if(bo == null) 
						continue;
					jobs.add(bo);
				}
			}
		}
		return jobs;
	}

	
	@SuppressWarnings("unchecked")
	private QuartzScheduleJobBO convertConfigToScheduleJob(DeamonScheduleconfigDomain scheduleDomain){
		QuartzScheduleJobBO bo = new QuartzScheduleJobBO();
		bo.setJobId(scheduleDomain.getId());
		bo.setBeanId(scheduleDomain.getBeanId());
		bo.setCronExpression(scheduleDomain.getQuartzConf());
		bo.setDomainId(scheduleDomain.getMonitorId());
		bo.setJobGroup(scheduleDomain.getJobGroup());
		bo.setJobName(scheduleDomain.getJobName());
		bo.setTaskType(scheduleDomain.getConfigType().toString());
		String className = scheduleDomain.getScheduleClass();
		
		try {
			Class<? extends Job> cl = ((Class<? extends Job>) Class.forName(className));
			bo.setJobClass(cl);
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
			//记录日志
			LOGGER.error("类没找到", e);
			return null;
		}
		return bo;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<QuartzScheduleJobBO> listQuartzConfig(String serverId,
			Integer sysType) {
		
		if(sysType.intValue() == SysTypeEnum.server.getVal().intValue()){
			return getHostSchedule(serverId);
		}else if(sysType.intValue() == SysTypeEnum.Oracle.getVal().intValue()){
			return getOracleSchedule(serverId);
		}else if(sysType.intValue() == SysTypeEnum.MySQL.getVal().intValue()){
			return getMySQLSchedule(serverId);
		}else if(sysType.intValue() == SysTypeEnum.KEEPALIVED.getVal().intValue()){
			return getKeepAlivedSchedule(serverId);
		}
		return Collections.EMPTY_LIST;
	}



	@Override
	public List<QuartzScheduleJobBO> listQuartzConfig(Integer sysType) {

		if(sysType.intValue() == SysTypeEnum.server.getVal().intValue()){
			return getAllHostSchedule();
		}else if(sysType.intValue() == SysTypeEnum.Oracle.getVal().intValue()){
			return getAllDbSchedule(SysTypeEnum.Oracle.getVal());
		}else if(sysType.intValue() == SysTypeEnum.MySQL.getVal().intValue()){
			return getAllDbSchedule(SysTypeEnum.MySQL.getVal());
		}else if(sysType.intValue() == SysTypeEnum.KEEPALIVED.getVal().intValue()){
			return getAllDbSchedule(SysTypeEnum.KEEPALIVED.getVal());
		}
		return Collections.EMPTY_LIST;
	}


	
	private List<QuartzScheduleJobBO> getAllHostSchedule(){
		List<QuartzScheduleJobBO> jobs = new ArrayList<QuartzScheduleJobBO>();
		List<HostConfigDomain> hostList = hostConfigDAO.listNormalHostConfig();
		for(HostConfigDomain domain : hostList){
			List<DeamonScheduleconfigDomain> list = deamonScheduleconfigDAO
					.listServerDeamonScheduleconfig(domain.getHostId());		
			
			if(list != null && list.size()>0){
				for(DeamonScheduleconfigDomain scheduleDomain : list){
					QuartzScheduleJobBO bo = convertConfigToScheduleJob(scheduleDomain);
					if(bo == null) 
						continue;
					
					bo.setJobStatus(String.valueOf(scheduleDomain.getStatus()));
					bo.setServerId(domain.getServerId());
					bo.setIpAddr(domain.getIpAddr());
					
					jobs.add(bo);
				}
			}
		}
		return jobs;
	}
	
	private List<QuartzScheduleJobBO> getAllDbSchedule(Integer dbType){
		List<QuartzScheduleJobBO> jobs = new ArrayList<QuartzScheduleJobBO>();
		List<DbConfigDomain> dbList = dbConfigDAO.listNormalDbConfig(dbType);
		for(DbConfigDomain domain : dbList){
			List<DeamonScheduleconfigDomain> list = deamonScheduleconfigDAO
					.listDbTypeDeamonScheduleconfig(domain.getDbId(),
							dbType);
			
			if(list != null && list.size()>0){
				for(DeamonScheduleconfigDomain scheduleDomain : list){
					QuartzScheduleJobBO bo = convertConfigToScheduleJob(scheduleDomain);
					
					if(bo == null) 
						continue;
					
					bo.setJobStatus(String.valueOf(scheduleDomain.getStatus()));
					bo.setServerId(domain.getServerId());
					bo.setPort(domain.getPort());
					bo.setIpAddr(domain.getIpAddr());
					
					jobs.add(bo);
				}
			}
		}
		return jobs;
	}
	
}
