/**   
* @Title: OracleHisSgaStatManager.java 
* @Package com.tyyd.ywpt.report.biz.stat.oracle.sga 
* @Description:  
* @author wangyu   
* @date 2014-12-15 下午4:30:36 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.stat.oracle.sga;

import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleGeneralReportDataBO;

/**
 * @author wangyu
 *
 */
public interface OracleHisSgaStatManager {

	
	/**
	 * SGA指标报表数据
	 * @param beginTime
	 * @param endTime
	 * @param databaseId
	 * @param instanceId
	 * @param quotaName
	 * @return
	 */
	public MultipleGeneralReportDataBO listSgaStatReportData(
			String beginTime, String endTime, Long databaseId,
			Long instanceId, String[] quotaNames);
}
