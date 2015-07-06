/**   
* @Title: MinitorAlertRecordDisplayManager.java 
* @Package com.tyyd.ywpt.report.biz 
* @Description:  
* @author wangyu   
* @date 2014-11-4 上午9:10:50 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.alert;

import java.util.List;

import com.tyyd.ywpt.dao.base.dataobject.PageQuery;
import com.tyyd.ywpt.report.biz.alert.dto.MonitorAlertDisplayDTO;

/**
 * @author wangyu
 *
 */
public interface MinitorAlertRecordDisplayManager {

	/**
	 * 预警归类总览(在一个期限内)
	 * @param pageQuery
	 * @param queryDay
	 * @return
	 */
	public PageQuery<List<MonitorAlertDisplayDTO>> listAllAlertComplex(Integer currentPage,String queryDay);
	
	
	/**
	 * 主机预警归类
	 * @param pageQuery
	 * @param hostId
	 * @return
	 */
	public PageQuery<List<MonitorAlertDisplayDTO>> listHostAlertComplex(Integer currentPage,String hostId,String startDate,String endDate);
	
	
	/**
	 * 数据库预警归类
	 * @param pageQuery
	 * @param dbId
	 * @param dbType
	 * @return
	 */
	public PageQuery<List<MonitorAlertDisplayDTO>> listDbAlertComplex(Integer currentPage,String dbId,Integer dbType,String startDate,String endDate);
}
