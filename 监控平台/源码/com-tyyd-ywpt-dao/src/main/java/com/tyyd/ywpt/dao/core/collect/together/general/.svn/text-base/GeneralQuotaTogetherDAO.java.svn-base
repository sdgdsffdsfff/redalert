/**   
* @Title: GeneralQuotaTogetherDAO.java 
* @Package com.tyyd.ywpt.dao.core.collect.together.general 
* @Description:  
* @author wangyu   
* @date 2014-9-25 上午11:27:36 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.together.general;

import java.util.List;

import com.tyyd.ywpt.dao.core.collect.together.general.dataobject.GeneralQuotaTogetherDomain;

/**
 * @author wangyu
 *
 */
public interface GeneralQuotaTogetherDAO {

	
	/**
	 * 报表展示的数据
	 * @param quotaId
	 * @param pageSize
	 * @return
	 */
	public List<GeneralQuotaTogetherDomain> listReportData(String hostId,String dbId,String quotaId,Integer pageSize,String tabName);
	
	
	/**
	 * 复合指标报表展示的数据
	 * @param quotaId
	 * @param pageSize
	 * @return
	 */
	public List<GeneralQuotaTogetherDomain> listComplexQuotaReportData(String hostId,String dbId,String[] quotaId,Integer pageSize,String tabName);
	
	
	
}
