/**   
* @Title: DiskQuotaScheduleJob.java 
* @Package com.tyyd.ywpt.biz.quartzschedule.impl.server 
* @Description:  
* @author wangyu   
* @date 2014-7-1 下午1:25:53 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.quartzschedule.impl.server;

import java.util.List;
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
public class DiskQuotaScheduleJob extends AbstractQuotaScheduleQuartzTask {

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
			List<Map<String,Object>> list = targetBean.getDiskQuota();
			
			//打印测试
			for(Map<String,Object> map : list){
				System.out.println(map.toString());
			}
			
		}
		
	}
	
}
