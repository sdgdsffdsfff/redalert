/**   
* @Title: AlertDisplayManager.java 
* @Package com.tyyd.ywpt.report.biz.alert 
* @Description:  
* @author wangyu   
* @date 2014-10-27 上午10:04:56 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.alert;

import java.util.List;

import com.tyyd.ywpt.report.biz.alert.dto.AlertDisplayDTO;

/**
 * @author wangyu
 *
 */
public interface AlertDisplayManager {

	/**
	 * 预警s总览
	 * @param pageSize
	 * @return
	 */
	public List<AlertDisplayDTO> listLookingAllAlert(Integer pageSize);
	
	/**
	 * 主机预警一览
	 * @param hostId
	 * @param pageSize
	 * @return
	 */
	public List<AlertDisplayDTO> listHostAlert(String hostId,Integer pageSize,String startDate,String endDate);
	
	
	/**
	 * 数据库预约一览
	 * @param dbId
	 * @param dbType 
	 * @param pageSize
	 * @return
	 */
	public List<AlertDisplayDTO> listDbAlert(String dbId,Integer dbType,Integer pageSize,String startDate,String endDate);
}
