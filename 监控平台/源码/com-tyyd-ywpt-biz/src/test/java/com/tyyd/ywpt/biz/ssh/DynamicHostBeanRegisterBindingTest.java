/**   
* @Title: DynamicHostBeanRegisterBindingTest.java 
* @Package com.tyyd.ywpt.biz.ssh 
* @Description:  
* @author wangyu   
* @date 2014-6-27 上午10:04:16 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.ssh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.tyyd.ywpt.biz.BaseBizTest;
import com.tyyd.ywpt.biz.bean.DynamicHostBeanRegisterBinding;
import com.tyyd.ywpt.biz.quartzschedule.ScheduleJob;
import com.tyyd.ywpt.biz.quartzschedule.dataobject.QuartzScheduleJobBO;
import com.tyyd.ywpt.biz.quartzschedule.impl.server.CPUQuotaScheduleJob;
import com.tyyd.ywpt.biz.quartzschedule.impl.server.MemoryQuotaScheduleJob;
import com.tyyd.ywpt.biz.ssh.impl.SSHQuotaCollectManagerImpl;
import com.tyyd.ywpt.dao.configration.host.dataobject.HostConfigDomain;
import com.tyyd.ywpt.tools.bean.SpringContextHolder;

/**
 * @author wangyu
 *
 */
public class DynamicHostBeanRegisterBindingTest extends BaseBizTest {

	@Resource
	private DynamicHostBeanRegisterBinding dynamicHostBeanRegisterBinding;
	
	@Resource
	private ScheduleJob scheduleJob;
	
	@Test
	public void testDynamicRegBean(){
		
		System.out.println("注册");
		//注册linux Bean
		dynamicHostBeanRegisterBinding
				.regSimpleBean("host_1_SSHQuotaCollectManager_beanId",
						SSHQuotaCollectManagerImpl.class, getLinuxExam(),
						getLinuxMap());
		
		//注册AIX Bean
		dynamicHostBeanRegisterBinding
				.regSimpleBean("host_2_SSHQuotaCollectManager_beanId",
						SSHQuotaCollectManagerImpl.class, getAixExam(),
						getAixMap());
		
		System.out.println("Get Bean");
		//调用测试
		SSHQuotaCollectManager linuxBean = SpringContextHolder.getBean("host_1_SSHQuotaCollectManager_beanId");
		SSHQuotaCollectManager aixBean = SpringContextHolder.getBean("host_2_SSHQuotaCollectManager_beanId");
		
		System.out.println("执行调用");
		Map<String,Object> linuxResult = linuxBean.getCpuQuota();
		Map<String,Object> aixResult = aixBean.getCpuQuota();
		
		System.out.println("输出");
		System.out.println(linuxResult.toString());
		System.out.println(aixResult.toString());
		
		
		//删除一个bean
		System.out.println("删除Bean");
		dynamicHostBeanRegisterBinding.removeBean("host_2_SSHQuotaCollectManager_beanId");
		
		//再次调用
		System.out.println("删除后调用");
		aixBean = SpringContextHolder.getBean("host_2_SSHQuotaCollectManager_beanId");
		if(aixBean == null){
			System.out.println("aixBean is Null");
		}else{
			aixResult = aixBean.getCpuQuota();
			System.out.println(aixResult.toString());
		}
		
		
	}
	
	private HostConfigDomain getLinuxExam(){
		HostConfigDomain domain = new HostConfigDomain();
		domain.setHostId("1");
		domain.setIpAddr("192.168.10.49");
		domain.setHostName("syjz-db-master.189read.com");
		domain.setIsHostCollect("Y");
		domain.setUserAccount("monitor");
		domain.setUserPasswd("monitor");
		return domain;
	}
	
	
	private Map<String,Object> getLinuxMap(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("connectIpAddr", "192.168.10.49");
		map.put("connectUserName", "monitor");
		map.put("connectPassword", "monitor");
		map.put("connectPort", 22);
		return map;
	}
	
	private HostConfigDomain getAixExam(){
		HostConfigDomain domain = new HostConfigDomain();
		domain.setHostId("2");
		domain.setIpAddr("192.168.10.17");
		domain.setHostName("SJYD_SJK_1");
		domain.setIsHostCollect("Y");
		domain.setUserAccount("monitor");
		domain.setUserPasswd("monitor");
		return domain;
	}
	
	
	private Map<String,Object> getAixMap(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("connectIpAddr", "192.168.10.17");
		map.put("connectUserName", "monitor");
		map.put("connectPassword", "monitor");
		map.put("connectPort", 22);
		return map;
	}
	
	
	@Test
	public void testSchedule(){
		System.out.println("注册");
		//注册linux Bean
		dynamicHostBeanRegisterBinding
				.regSimpleBean("host_1_SSHQuotaCollectManager_beanId",
						SSHQuotaCollectManagerImpl.class, getLinuxExam(),
						getLinuxMap());
		
		//注册AIX Bean
		dynamicHostBeanRegisterBinding
				.regSimpleBean("host_2_SSHQuotaCollectManager_beanId",
						SSHQuotaCollectManagerImpl.class, getAixExam(),
						getAixMap());
		
		//注册调度任务
		QuartzScheduleJobBO cpu49Job = new QuartzScheduleJobBO("job-01", "host_1_SSHQuotaCollectManager_beanId", "cpu",
				CPUQuotaScheduleJob.class, "host_1_SSHQuotaCollectManager_beanId_cpu", "server cpu collection",
				"1", "0 0/1 * * * ?", "192.168.10.49 cpu采集");
		
		QuartzScheduleJobBO memory49Job = new QuartzScheduleJobBO("job-02", "host_1_SSHQuotaCollectManager_beanId", "memory",
				MemoryQuotaScheduleJob.class, "host_1_SSHQuotaCollectManager_beanId_memory", "server memory collection",
				"1", "0 0/1 * * * ?", "192.168.10.49 memory采集");
		
		QuartzScheduleJobBO cpu17job = new QuartzScheduleJobBO("job-03", "host_2_SSHQuotaCollectManager_beanId", "cpu",
				CPUQuotaScheduleJob.class, "host_2_SSHQuotaCollectManager_beanId_cpu", "server cpu collection",
				"1", "0 0/1 * * * ?", "192.168.10.17 cpu采集");
		
		//添加调度
		scheduleJob.addTask(cpu49Job);
		scheduleJob.addTask(memory49Job);
		scheduleJob.addTask(cpu17job);
		
		try {
			Thread.sleep(40000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("已经注册的任务列表================");
		//查看任务量
		List<QuartzScheduleJobBO> taskList = scheduleJob.scheduleTask();
		for(QuartzScheduleJobBO bo : taskList){
			System.out.println(bo);
			//System.out.println("jobId="+bo.getJobId() +",beanId="+bo.getBeanId()+",taskType="+bo.getTaskType()+",jobName="+bo.getJobName());
		}
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		//查看正在运行的任务
		System.out.println("正在运行的任务列表================");
		List<QuartzScheduleJobBO> runningList = scheduleJob.runningTask();
		for(QuartzScheduleJobBO bo : runningList){
			System.out.println(bo);
			//System.out.println("jobId="+bo.getJobId() +",beanId="+bo.getBeanId()+",taskType="+bo.getTaskType()+",jobName="+bo.getJobName());
		}
		
		//删除任务
		System.out.println("删除49CPU定时调度================");
		scheduleJob.delTask(cpu49Job.getJobName(),cpu49Job.getJobGroup());
		
		while(true){
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//查看任务
			System.out.println("已经注册的任务列表================");
			List<QuartzScheduleJobBO> newTaskList = scheduleJob.scheduleTask();
			for(QuartzScheduleJobBO bo : newTaskList){
				System.out.println(bo);
				//System.out.println("jobId="+bo.getJobId() +",beanId="+bo.getBeanId()+",taskType="+bo.getTaskType()+",jobName="+bo.getJobName());
			}
		}
		
	}
	
}
