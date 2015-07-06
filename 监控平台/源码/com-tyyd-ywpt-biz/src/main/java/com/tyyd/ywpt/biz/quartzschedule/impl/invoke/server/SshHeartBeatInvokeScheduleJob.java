/**   
* @Title: SshHeartBeatInvokeScheduleJob.java 
* @Package com.tyyd.ywpt.biz.quartzschedule.impl.invoke.server 
* @Description:  
* @author wangyu   
* @date 2014-7-15 上午10:04:47 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.quartzschedule.impl.invoke.server;

import org.apache.commons.lang.StringUtils;
import org.quartz.DisallowConcurrentExecution;

import com.tyyd.ywpt.biz.bean.dataobject.DynamicBeanBO;
import com.tyyd.ywpt.biz.dict.SysTypeEnum;
import com.tyyd.ywpt.biz.quartzschedule.dataobject.QuartzScheduleJobBO;
import com.tyyd.ywpt.biz.quartzschedule.impl.AbstractQuotaScheduleQuartzTask;
import com.tyyd.ywpt.biz.ssh.invoke.ServerSsh;
import com.tyyd.ywpt.biz.ssh.invoke.ServerSshImpl;
import com.tyyd.ywpt.dao.configration.host.dataobject.HostConfigDomain;
import com.tyyd.ywpt.dao.core.collect.heartbeat.dataobject.HeartbeatMonitorDomain;
import com.tyyd.ywpt.tools.bean.SpringContextHolder;

/**
 * @author wangyu
 *
 */

@DisallowConcurrentExecution
public class SshHeartBeatInvokeScheduleJob extends AbstractQuotaScheduleQuartzTask{

	@SuppressWarnings("rawtypes")
	@Override
	public void doTask(QuartzScheduleJobBO scheduleJob) {
		Object beanObj = SpringContextHolder.getBean(scheduleJob.getBeanId());
		if(beanObj instanceof ServerSshImpl 
				|| beanObj instanceof  ServerSsh){
			//如果是主机类的指标
			ServerSsh targetBean = (ServerSsh)beanObj;
			
			boolean flag = Boolean.TRUE;
			try{
				flag = targetBean.heartBeatMonitor();
			}catch(Exception e){
				LOGGER.error("心跳监测异常",e);
				flag = Boolean.FALSE;
			}
			Integer isActive = 0;
			if(!flag){
				isActive = 1;
			}
			
			DynamicBeanBO beanDomain = getCustomerBeanListFactory().getBeanProperty(scheduleJob.getBeanId());
			Object obj = beanDomain.getPropertyBean();
			HostConfigDomain hostConfigDomain = null;
			if(obj instanceof HostConfigDomain){
				hostConfigDomain = (HostConfigDomain)obj;
			}
			
			
			//更新hostConfig
			HostConfigDomain dbConfigDomain = new HostConfigDomain();
			dbConfigDomain.setHostId(hostConfigDomain.getHostId());
			dbConfigDomain.setLastHeartBeatStatus(isActive);
			getHostConfigDAO().updateHostMonitorStatus(dbConfigDomain);
			
			//写入日志
			getHeartbeatMonitorDAO().addHeartbeatMonitor(
					new HeartbeatMonitorDomain(hostConfigDomain.getHostId(), StringUtils.EMPTY, isActive, SysTypeEnum.server.getVal()));
			
			
			
			
			
		}
	}

}
