/**   
* @Title: KeepAlivedHeartBeatInvokeScheduleJob.java 
* @Package com.tyyd.ywpt.biz.quartzschedule.impl.invoke.keepalived 
* @Description:  
* @author wangyu   
* @date 2014-11-20 下午4:49:36 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.quartzschedule.impl.invoke.keepalived;

import com.tyyd.ywpt.biz.bean.dataobject.DynamicBeanBO;
import com.tyyd.ywpt.biz.quartzschedule.dataobject.QuartzScheduleJobBO;
import com.tyyd.ywpt.biz.quartzschedule.impl.AbstractQuotaScheduleQuartzTask;
import com.tyyd.ywpt.dao.configration.hostdiralert.dataobject.HostDirAlertConfigDomain;

/**
 * @author wangyu
 *
 */
public class KeepAlivedAlertLogInvokeScheduleJob extends
		AbstractQuotaScheduleQuartzTask {

	
	@SuppressWarnings("rawtypes")
	@Override
	public void doTask(QuartzScheduleJobBO scheduleJob) {
		DynamicBeanBO beanDomain = getCustomerBeanListFactory().getBeanProperty(scheduleJob.getBeanId());
		Object obj = beanDomain.getPropertyBean();
		HostDirAlertConfigDomain dbDomain = null;
		if(obj instanceof HostDirAlertConfigDomain){
			dbDomain = (HostDirAlertConfigDomain)obj;
		}
		getKeepAlivedSsh().checkKeepAlivedLog(dbDomain.getMonitorId(), dbDomain.getHostDir(), dbDomain.getKeyword());
	}

}
