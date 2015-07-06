/**   
* @Title: AbstractGeneralQuotaReportManager.java 
* @Package com.tyyd.ywpt.report.biz.quota.impl 
* @Description:  
* @author wangyu   
* @date 2014-12-16 上午10:24:47 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.quota.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.tyyd.ywpt.report.biz.quota.dto.GeneralQuotaReportListBO;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleDataBO;
import com.tyyd.ywpt.report.biz.quota.dto.multi.ReportOrgFormatDataBO;

/**
 * @author wangyu
 *
 */
public abstract class AbstractGeneralQuotaReportManager<T> {

	
	
	/**
	 * 转化成数组类型，供MultipleDataBO对象使用，是MultipleDataBO对象中的一个要素
	 * @param quotaReportList
	 * @return
	 */
	protected List<ReportOrgFormatDataBO> convertGeneralQuotaReportData(List<GeneralQuotaReportListBO> quotaReportList){
		if(CollectionUtils.isEmpty(quotaReportList)){
			return null;
		}
		
		List<ReportOrgFormatDataBO> result = new ArrayList<ReportOrgFormatDataBO>();
		for(GeneralQuotaReportListBO report : quotaReportList){
			ReportOrgFormatDataBO reportOrgFormatDataBO = new ReportOrgFormatDataBO();
			reportOrgFormatDataBO.setX(Long.valueOf(report.getGmtCreatedDate()) * 1000);
			reportOrgFormatDataBO.setY(report.getQuotaValue());
			//TODO 未来增加ID列
			reportOrgFormatDataBO.setId(report.getId());
			
			result.add(reportOrgFormatDataBO);
		}
		
		return result;
	}
	
	
	
	/**
	 * 
	 * @param arrays
	 * @return
	 */
	protected MultipleDataBO[] convertMultipleData(List<MultipleDataBO> arrays) {
		if(CollectionUtils.isNotEmpty(arrays)){
			MultipleDataBO[] result = new MultipleDataBO[arrays.size()];
			for(int i=0;i<result.length;i++){
				result[i] = arrays.get(i);
			}
			return result;
		}
		
		return new MultipleDataBO[0];
	}
	
	
	
	/**
	 * 元数据转化
	 * @param list
	 * @return
	 */
	protected List<GeneralQuotaReportListBO> convertReportDbData(List<T> list) {
		List<GeneralQuotaReportListBO> dataList = new ArrayList<GeneralQuotaReportListBO>();
		if(CollectionUtils.isNotEmpty(list)){
			for(T domain : list){
				GeneralQuotaReportListBO dto = copyPropertiesFromDomain(domain);
				dataList.add(dto);
			}
		}
		
		return dataList;
	}
	
	/**
	 * 每种元数据的转化方式不同，丢给上层应用来解决
	 * @param domain
	 * @return
	 */
	protected abstract GeneralQuotaReportListBO copyPropertiesFromDomain(T domain);


	
}
