/**   
* @Title: SshOracleStatInvokeScheduleJob.java 
* @Package com.tyyd.ywpt.biz.quartzschedule.impl.invoke.oracle 
* @Description:  
* @author wangyu   
* @date 2014-7-7 下午4:45:09 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.quartzschedule.impl.invoke.oracle;

import org.quartz.DisallowConcurrentExecution;

import com.tyyd.ywpt.biz.bean.dataobject.DynamicBeanBO;
import com.tyyd.ywpt.biz.quartzschedule.dataobject.QuartzScheduleJobBO;
import com.tyyd.ywpt.biz.quartzschedule.impl.AbstractQuotaScheduleQuartzTask;
import com.tyyd.ywpt.dao.configration.hostdiralert.dataobject.HostDirAlertConfigDomain;

/**
 * @author wangyu
 *
 */
@DisallowConcurrentExecution
public class SshOracleAlertInvokeScheduleJob extends AbstractQuotaScheduleQuartzTask{

	@SuppressWarnings("rawtypes")
	@Override
	public void doTask(QuartzScheduleJobBO scheduleJob) {
		DynamicBeanBO beanDomain = getCustomerBeanListFactory().getBeanProperty(scheduleJob.getBeanId());
		Object obj = beanDomain.getPropertyBean();
		HostDirAlertConfigDomain dbDomain = null;
		if(obj instanceof HostDirAlertConfigDomain){
			dbDomain = (HostDirAlertConfigDomain)obj;
		}
		getOracleSsh().sshCollectOracleAlert(dbDomain.getMonitorId(),dbDomain.getHostDir(),dbDomain.getKeyword());
	}
}
