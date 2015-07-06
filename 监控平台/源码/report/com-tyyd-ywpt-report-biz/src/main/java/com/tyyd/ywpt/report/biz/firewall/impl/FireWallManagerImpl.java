/**   
* @Title: FireWallManagerImpl.java 
* @Package com.tyyd.ywpt.report.biz.firewall.impl 
* @Description:  
* @author wangyu   
* @date 2015-4-20 下午5:17:07 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.firewall.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.tyyd.ywpt.dao.firewall.FireWallDAO;
import com.tyyd.ywpt.dao.firewall.dataobject.FireWallDomain;
import com.tyyd.ywpt.report.biz.firewall.FireWallManager;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleDataBO;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleGeneralReportDataBO;
import com.tyyd.ywpt.report.biz.quota.dto.multi.ReportOrgFormatDataBO;

/**
 * @author wangyu
 *
 */
public class FireWallManagerImpl implements FireWallManager {

	@Resource
	private FireWallDAO fireWallDAO;
	
	@Override
	public MultipleGeneralReportDataBO listFireWall(String startDate,
			String endDate, String flowCountKey,String embryonicKey) {
		
		
		//半
		List<FireWallDomain> flowCountList = fireWallDAO.listFireWallByKey(startDate, endDate, flowCountKey);
		List<FireWallDomain> embryonicList = fireWallDAO.listFireWallByKey(startDate, endDate, embryonicKey);
		
		MultipleGeneralReportDataBO multipleGeneralReportDataBO = new MultipleGeneralReportDataBO();
		MultipleDataBO flowCountDataBO = new MultipleDataBO();
		MultipleDataBO embryonicDataBO = new MultipleDataBO();
		MultipleDataBO[] seriesData = new MultipleDataBO[]{flowCountDataBO,embryonicDataBO};
		
		flowCountDataBO.setName("flow_count");
		embryonicDataBO.setName("embryonic");
		
		flowCountDataBO.setData(this.convertGeneralQuotaReportData(flowCountList));
		embryonicDataBO.setData(this.convertGeneralQuotaReportData(embryonicList));
		
		flowCountDataBO.setType("spline");
		embryonicDataBO.setType("spline");
		
		multipleGeneralReportDataBO.setSeriesData(seriesData);
		multipleGeneralReportDataBO.setReportName("多指标报表");
		
		return multipleGeneralReportDataBO;
	}


	
	/**
	 * 转化
	 * @param fireWallList
	 * @return
	 */
	private List<ReportOrgFormatDataBO> convertGeneralQuotaReportData(List<FireWallDomain> fireWallList){
		if(CollectionUtils.isEmpty(fireWallList)){
			return null;
		}
		
		List<ReportOrgFormatDataBO> result = new ArrayList<ReportOrgFormatDataBO>();
		for(FireWallDomain report : fireWallList){
			ReportOrgFormatDataBO reportOrgFormatDataBO = new ReportOrgFormatDataBO();
			reportOrgFormatDataBO.setX(Long.valueOf(report.getGmtCreatedStamp()) * 1000);
			
			String fireWallValue = report.getFireWallValue().replace("\r", "");
			if(!NumberUtils.isNumber(fireWallValue)){
				continue;
			}
			
			reportOrgFormatDataBO.setY(NumberUtils.toFloat(fireWallValue));
			//TODO 未来增加ID列
			//reportOrgFormatDataBO.setId(String.valueOf(report.getId()));
			
			result.add(reportOrgFormatDataBO);
		}
		
		return result;
	}


}
