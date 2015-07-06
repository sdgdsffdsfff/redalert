/**   
* @Title: TaskRefreshRegeditManager.java 
* @Package com.tyyd.ywpt.biz.quartzschedule 
* @Description:  
* @author wangyu   
* @date 2014-11-14 下午3:20:38 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.quartzschedule;

import com.tyyd.ywpt.biz.quartzschedule.dataobject.TaskRefreshResponseDTO;

/**
 * @author wangyu
 *
 */
public interface TaskRefreshRegeditManager {

	
	/**
	 * 重新注册任务
	 * @param newJobId
	 * @param oldJobId
	 * @param mdkey
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public TaskRefreshResponseDTO regeditTask(String newJobId,
			String newJobName, String newJobGroup,String cronExpression, String newbeanId,
			String monitorId, String configType, Integer jobType,
			String oldJobId, String oldJobName, String oldJobGroup, String mdkey);
	
	/**
	 * 删除任务（前提是状态正常 ）
	 * @param jobId
	 * @param mdkey
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public TaskRefreshResponseDTO delTask(String jobId,String mdkey);
	
	
	/**
	 * 删除已经被删除的任务
	 * @param jobId
	 * @param mdkey
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public TaskRefreshResponseDTO delHangStatusTask(String jobId,String mdkey);
	
	
	/**
	 * 添加Bean
	 * @param monitorId
	 * @param beanType
	 * @param mdkey
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public TaskRefreshResponseDTO addBean(String monitorId,Integer configType,Integer jobType, String newbeanId,String mdkey);
	
	/**
	 * 删除Bean
	 * @param monitorId
	 * @param beanType
	 * @param mdkey
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public TaskRefreshResponseDTO delBean(String newbeanId,String mdkey);
	
	
	/**
	 * 存在Bean
	 * @param newbeanId
	 * @param mdkey
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public TaskRefreshResponseDTO existsBean(String newbeanId,String mdkey);
	
	
	public String sayHello(String userName);
	
	
	/**
	 * 验证MDKEY
	 * @param mdkey
	 * @return
	 */
	public boolean isPrivsRole(String mdkey);
	
}
