/**   
* @Title: BaseLineHandlerManager.java 
* @Package com.tyyd.ywpt.report.biz.quota.baseline 
* @Description:  
* @author wangyu   
* @date 2015-3-31 下午4:15:21 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.quota.baseline;

import java.util.List;

import com.tyyd.ywpt.dao.baseline.dataobject.BaseLineRecordDomain;
import com.tyyd.ywpt.report.biz.quota.dto.baseline.BaseLineReportFormatDataBO;

/**
 * @author wangyu
 *
 */
public interface BaseLineHandlerManager {

	/**
	 * 处理基线数据
	 * @param currentYmd
	 * @param startHm
	 * @param endHm
	 * @param baseLineList
	 * @param maxValResult
	 * @param avgValResult
	 */
	public void handler(String currentYmd, String startHm, 
			String endHm, List<BaseLineRecordDomain> baseLineList,
			List<BaseLineReportFormatDataBO> maxValResult,
			List<BaseLineReportFormatDataBO> avgValResult); 
}
