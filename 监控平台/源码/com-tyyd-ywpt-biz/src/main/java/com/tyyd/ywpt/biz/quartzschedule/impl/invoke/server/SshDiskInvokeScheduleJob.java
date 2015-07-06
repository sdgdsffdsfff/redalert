/**   
* @Title: SshCpuInvokeScheduleJob.java 
* @Package com.tyyd.ywpt.biz.quartzschedule.impl.invoke.server 
* @Description:  
* @author wangyu   
* @date 2014-7-7 下午1:36:11 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.quartzschedule.impl.invoke.server;

import org.quartz.DisallowConcurrentExecution;

import com.tyyd.ywpt.biz.bean.dataobject.DynamicBeanBO;
import com.tyyd.ywpt.biz.quartzschedule.dataobject.QuartzScheduleJobBO;
import com.tyyd.ywpt.biz.quartzschedule.impl.AbstractQuotaScheduleQuartzTask;
import com.tyyd.ywpt.dao.configration.host.dataobject.HostConfigDomain;

/**
 * @author wangyu
 *
 */

@DisallowConcurrentExecution
public class SshDiskInvokeScheduleJob extends AbstractQuotaScheduleQuartzTask {

	
	@SuppressWarnings("rawtypes")
	@Override
	public void doTask(QuartzScheduleJobBO scheduleJob) {
		DynamicBeanBO beanDomain = getCustomerBeanListFactory().getBeanProperty(scheduleJob.getBeanId());
		Object obj = beanDomain.getPropertyBean();
		HostConfigDomain hostDomain = null;
		if(obj instanceof HostConfigDomain){
			hostDomain = (HostConfigDomain)obj;
		}
		getServerSsh().sshCollectServerDiskInfo(hostDomain.getIpAddr());
		
	}

	
	
}
