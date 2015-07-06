/**   
* @Title: OracleHisSgaStatManagerImpl.java 
* @Package com.tyyd.ywpt.report.biz.stat.oracle.sga.impl 
* @Description:  
* @author wangyu   
* @date 2014-12-15 下午4:44:06 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.stat.oracle.sga.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;

import com.tyyd.ywpt.dao.stat.oracle.OracleHisSgaStatDAO;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.OracleDBAHisSgaStatDomain;
import com.tyyd.ywpt.report.biz.quota.dto.GeneralQuotaReportListBO;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleDataBO;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleGeneralReportDataBO;
import com.tyyd.ywpt.report.biz.quota.impl.AbstractGeneralQuotaReportManager;
import com.tyyd.ywpt.report.biz.stat.oracle.sga.OracleHisSgaStatManager;

/**
 * @author wangyu
 *
 */
public class OracleHisSgaStatManagerImpl extends AbstractGeneralQuotaReportManager<OracleDBAHisSgaStatDomain> implements OracleHisSgaStatManager {

	
	@Resource
	private OracleHisSgaStatDAO oracleHisSgaStatDAO;
	
	@Override
	public MultipleGeneralReportDataBO listSgaStatReportData(String beginTime,
			String endTime, Long databaseId, Long instanceId, String[] quotaNames) {

		if(instanceId == null || databaseId == null || ArrayUtils.isEmpty(quotaNames)){
			return null;
		}
		
		MultipleGeneralReportDataBO multipleGeneralReportDataBO = new MultipleGeneralReportDataBO();
		List<MultipleDataBO> arrays = new ArrayList<MultipleDataBO>();
		
		for(String quota : quotaNames){
			MultipleDataBO multipleDataBO = new MultipleDataBO();
			multipleDataBO.setName(quota);
			
			List<OracleDBAHisSgaStatDomain> list = oracleHisSgaStatDAO.listSga(beginTime, endTime, databaseId, instanceId, quota);
			List<GeneralQuotaReportListBO> result = convertReportDbData(list);
			multipleDataBO.setData(convertGeneralQuotaReportData(result));
			arrays.add(multipleDataBO);
		}
		
		MultipleDataBO[] seriesData = convertMultipleData(arrays);
		
		multipleGeneralReportDataBO.setSeriesData(seriesData);
		multipleGeneralReportDataBO.setReportName("多指标报表");
		
		return multipleGeneralReportDataBO;
		
	}

	@Override
	protected GeneralQuotaReportListBO copyPropertiesFromDomain(OracleDBAHisSgaStatDomain domain) {
		
		GeneralQuotaReportListBO dto = new GeneralQuotaReportListBO();
		dto.setQuotaValue(domain.getCapture());
		dto.setGmtCreatedDate(domain.getSnapTime());
		StringBuffer id = new StringBuffer();
		id.append(domain.getDbId());
		id.append("_");
		id.append(domain.getInstanceId());
		id.append("_");
		id.append(domain.getSnapId());
		id.append("_");
		id.append(domain.getPool());
		id.append("_");
		id.append(domain.getName());
		
		return dto;
	}

}
