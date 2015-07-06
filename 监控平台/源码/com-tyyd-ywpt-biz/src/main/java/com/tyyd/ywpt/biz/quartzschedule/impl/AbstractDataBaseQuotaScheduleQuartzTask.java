/**   
* @Title: AbstractDataBaseQuotaScheduleQuartzTask.java 
* @Package com.tyyd.ywpt.biz.quartzschedule.impl 
* @Description:  
* @author wangyu   
* @date 2014-7-3 上午10:01:00 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.quartzschedule.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import com.tyyd.ywpt.biz.bean.dataobject.DynamicBeanBO;
import com.tyyd.ywpt.biz.quartzschedule.dataobject.QuartzScheduleJobBO;
import com.tyyd.ywpt.tools.bean.SpringContextHolder;
import com.tyyd.ywpt.tools.ds.dataobject.DynamicDataSourceDomain;
import com.tyyd.ywpt.tools.ds.impl.DynamicDataSourceContextHolder;

/**
 * @author wangyu
 *
 */
public abstract class AbstractDataBaseQuotaScheduleQuartzTask extends AbstractQuotaScheduleQuartzTask{
	
	public static final Logger LOGGER = Logger.getLogger(AbstractDataBaseQuotaScheduleQuartzTask.class);  
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		//get定时任务的BeanId
		String dataSourceBeanId = getScheduleTaskBeanId(context);
		
		if(StringUtils.isEmpty(dataSourceBeanId)){
			//切换失败
			LOGGER.error("切换数据源失败，原因:未找到对应的数据源ID("+dataSourceBeanId+")");
		}
		try{
			SpringContextHolder.getBean(dataSourceBeanId);
		}catch(NoSuchBeanDefinitionException ex){
			LOGGER.error("切换数据源失败，原因:未找到对应的数据源ID("+dataSourceBeanId+")");
		}
		
		//切换数据源
		DynamicDataSourceContextHolder.setDataSource(dataSourceBeanId);
		
		LOGGER.info("当前数据源是:"+dataSourceBeanId+","+getDataSourceInfo(dataSourceBeanId));
		
		//继续执行
		super.execute(context);
		
		//退出重置
		afterResetDS();
	}
	
	
	/**
	 * 
	 * @param context
	 * @return
	 */
	private String getScheduleTaskBeanId(JobExecutionContext context){
		QuartzScheduleJobBO scheduleJob = (QuartzScheduleJobBO)context.getMergedJobDataMap().get("taskData");
		if(scheduleJob != null){
			return scheduleJob.getBeanId();
		}
		return StringUtils.EMPTY;
	}
	

	public abstract void doTask(QuartzScheduleJobBO scheduleJob);

	
	@SuppressWarnings("rawtypes")
	protected String getDataSourceInfo(String dataSourceBeanId){
		StringBuffer sb = new StringBuffer();
		DynamicBeanBO dynamicBean = getCustomerBeanListFactory().getBeanProperty(dataSourceBeanId);
		if(dynamicBean == null){
			LOGGER.error(dataSourceBeanId +" is not exists in CustomerBeanListFactory");
			throw new NullPointerException(dataSourceBeanId +" is not exists in CustomerBeanListFactory");
		}
		Object dynamicDs = dynamicBean.getPropertyBean();
		DynamicDataSourceDomain domain = null;
		if(dynamicDs instanceof DynamicDataSourceDomain){
			domain = (DynamicDataSourceDomain)dynamicDs;
		}
		
		if(domain != null){
			sb.append("DataSource[ip:");
			sb.append(domain.getIpAddr());
			sb.append(",port:");
			sb.append(domain.getPort());
			sb.append(",username:");
			sb.append(domain.getUserName());
			sb.append(",database:");
			sb.append(domain.getDbType());
			sb.append("]");
		}
		return sb.toString();
	}
	
}
