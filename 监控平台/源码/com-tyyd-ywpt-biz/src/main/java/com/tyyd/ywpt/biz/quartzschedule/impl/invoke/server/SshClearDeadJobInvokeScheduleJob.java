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

import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.tyyd.ywpt.biz.ssh.invoke.ServerSsh;
import com.tyyd.ywpt.tools.bean.SpringContextHolder;

/**
 * @author wangyu
 *
 */

@DisallowConcurrentExecution
public class SshClearDeadJobInvokeScheduleJob implements Job{

	public static final Logger LOGGER = Logger.getLogger(SshClearDeadJobInvokeScheduleJob.class);  
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		LOGGER.info("清理僵死进程开始");
		ServerSsh serverSsh = SpringContextHolder.getBean("serverSsh"); 
		serverSsh.sshClearDeadJob();
		LOGGER.info("清理僵死进程结束");
	}

	
	
}
