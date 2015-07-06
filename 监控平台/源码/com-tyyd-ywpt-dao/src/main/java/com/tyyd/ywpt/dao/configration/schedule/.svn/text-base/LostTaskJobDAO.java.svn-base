/**   
* @Title: LostTaskJobDAO.java 
* @Package com.tyyd.ywpt.dao.configration.schedule 
* @Description:  
* @author wangyu   
* @date 2014-11-25 上午11:45:42 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.configration.schedule;

import java.util.List;

import com.tyyd.ywpt.dao.configration.schedule.dataobject.LostTaskJobDomain;

/**
 * @author wangyu
 *
 */
public interface LostTaskJobDAO {

	/**
	 * 从任务中采集3倍时间内未完成的job
	 */
	public void collectLostTaskJob();
	
	
	/**
	 * 获取20条未发送邮件提醒
	 */
	public List<LostTaskJobDomain> getUnAlertTask();
	
	
	
	/**
	 * 获取未重新注册包含未成功注册的任务
	 */
	public List<LostTaskJobDomain> getUnRegTask();
	
	
	/**
	 * 通知已完成
	 * @param id
	 */
	public void closeNotify(Long id);
	
	
	/**
	 * 关闭已重新注册的任务
	 * @param id
	 */
	public void closeTaskJob(Long id);
	
	
	/**
	 * 重新注册失败
	 * @param id
	 */
	public void failTaskJob(Long id);
}
