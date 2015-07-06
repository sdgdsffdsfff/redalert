/**   
* @Title: MemoryQuotaScheduleJob.java 
* @Package com.tyyd.ywpt.biz.quartzschedule.impl.server 
* @Description:  
* @author wangyu   
* @date 2014-7-1 下午1:26:42 
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
 *
 */
@DisallowConcurrentExecution
public class MemoryQuotaScheduleJob extends AbstractQuotaScheduleQuartzTask {
	
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
			Map<String,Object> resultMap = targetBean.getMemoryQuota();
			
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
