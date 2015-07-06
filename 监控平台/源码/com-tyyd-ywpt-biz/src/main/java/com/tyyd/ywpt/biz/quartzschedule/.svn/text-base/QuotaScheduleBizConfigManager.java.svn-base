/**   
* @Title: QuotaScheduleBizConfigManager.java 
* @Package com.tyyd.ywpt.biz.quartzschedule 
* @Description:  
* @author wangyu   
* @date 2014-7-11 上午10:05:35 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.quartzschedule;

import java.util.List;

import com.tyyd.ywpt.biz.quartzschedule.dataobject.QuartzScheduleJobBO;

/**
 * @author wangyu
 *
 */
public interface QuotaScheduleBizConfigManager {

	/**
	 * 获取主机、Oracle、MySQL等服务器的定义
	 * 包括心跳
	 * @param serverId
	 * @param sysType
	 * @return
	 */
	public List<QuartzScheduleJobBO>  listQuartzConfig(String serverId,Integer sysType);
	
	
	/**
	 * 获取某种类型的任务列表，包含关闭的任务
	 * @param sysType
	 * @return
	 */
	public List<QuartzScheduleJobBO>  listQuartzConfig(Integer sysType);
}
