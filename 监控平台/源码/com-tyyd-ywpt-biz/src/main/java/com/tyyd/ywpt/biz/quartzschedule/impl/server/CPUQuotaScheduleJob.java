/**   
* @Title: CPUQuotaScheduleJob.java 
* @Package com.tyyd.ywpt.biz.quartzschedule.impl.server 
* @Description:  
* @author wangyu   
* @date 2014-7-1 上午9:51:50 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.quartzschedule.impl.server;

import java.util.Map;

import org.quartz.DisallowConcurrentExecution;

import com.tyyd.ywpt.biz.quartzschedule.dataobject.QuartzScheduleJobBO;
import com.tyyd.ywpt.biz.quartzschedule.impl.AbstractQuotaScheduleQuartzTask;
import com.tyyd.ywpt.biz.ssh.SSHQuotaCollectManager;
import com.tyyd.ywpt.biz.ssh.impl.SSHQuotaCollectManagerImpl;
import com.tyyd.ywpt.tools.bean.SpringContextHolder;

/**
 * @author wangyu
 * CPU指标的定时任务
 *
 */

@DisallowConcurrentExecution
public class CPUQuotaScheduleJob extends AbstractQuotaScheduleQuartzTask {

	/**
	 * 做任务
	 * @param scheduleJob
	 */
	public void doTask(QuartzScheduleJobBO scheduleJob){
		Object beanObj = SpringContextHolder.getBean(scheduleJob.getBeanId());
		if(beanObj instanceof SSHQuotaCollectManagerImpl 
				|| beanObj instanceof  SSHQuotaCollectManager){
			//如果是主机类的指标
			SSHQuotaCollectManager targetBean = (SSHQuotaCollectManager)beanObj;
			Map<String,Object> resultMap = targetBean.getCpuQuota();
			
			//打印测试
			System.out.println(resultMap.toString());
			try {
				Thread.sleep(100000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
