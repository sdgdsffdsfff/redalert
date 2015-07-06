/**   
* @Title: TbsMonitorReportManager.java 
* @Package com.tyyd.ywpt.report.biz.device 
* @Description:  
* @author wangyu   
* @date 2015-6-3 下午1:19:32 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.device;

import java.util.List;

import com.tyyd.ywpt.dao.configration.db.dataobject.DbConfigDomain;
import com.tyyd.ywpt.dao.core.collect.datafile.dataobject.DbTableSpaceDomain;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleGeneralReportDataBO;

/**
 * @author wangyu
 *
 */
public interface TbsMonitorReportManager {

	
	/**
	 * 获取数据库信息
	 * @param hostId
	 * @return
	 */
	public DbConfigDomain getDataBaseInfo(String dbId);
	
	/**
	 * 获取表空间最新的列表
	 * @param hostId
	 * @return
	 */
	public List<DbTableSpaceDomain[]> listTbsMonitor(String dbId);
	
	/**
	 * 表空间统计信息
	 * @param hostId
	 * @return
	 */
	public DbTableSpaceDomain totalTbsInfo(String dbId);
	
	/**
	 * 表空间总量
	 * @param hostId
	 * @return
	 */
	public MultipleGeneralReportDataBO totalTbsReport(String dbId);
	
	
	/**
	 * 表空间增量
	 * @param hostId
	 * @return
	 */
	public MultipleGeneralReportDataBO deltaTbsReport(String dbId);
	
	/**
	 * 某个表空间的增量
	 * @param hostId
	 * @return
	 */
	public MultipleGeneralReportDataBO deltaTbsReportByTbs(String dbId,String tbsName);
	
	
	/**
	 * 某个表空间的总量
	 * @param hostId
	 * @param diskName
	 * @return
	 */
	public MultipleGeneralReportDataBO totalTbsReportByTbs(String dbId,String tbsName);
	
}
