/**   
* @Title: ThresholdDAO.java 
* @Package com.tyyd.ywpt.dao.configration.threshold 
* @Description:  
* @author wangyu   
* @date 2014-6-22 下午1:10:47 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.configration.threshold;

import java.util.List;

import com.tyyd.ywpt.dao.configration.threshold.dataobject.ThresholdDomain;

/**
 * @author wangyu
 *
 */
public interface ThresholdDAO {

	/**
	 * 添加预警
	 * @param domain
	 */
	public void addThreshold(ThresholdDomain domain);
	
	/**
	 * 更新预警
	 * @param domain
	 */
	public void updateThresholdInfo(ThresholdDomain domain);
	
	/**
	 * 删除预警
	 * @param id
	 */
	public void deleteThreshold(String id);
	
	/**
	 * 获取预警
	 * @param id
	 * @return
	 */
	public ThresholdDomain getThresholdById(String id);
	
	/**
	 * 某监控的预警列表
	 * @param monitorId
	 * @param configType
	 * @return
	 */
	public List<ThresholdDomain> listThreshold(String monitorId,Integer configType);
}
