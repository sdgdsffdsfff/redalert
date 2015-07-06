/**   
* @Title: HostQuotaTogetherDAO.java 
* @Package com.tyyd.ywpt.dao.core.collect.together.host 
* @Description:  
* @author wangyu   
* @date 2014-9-22 下午1:41:43 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.together.host;

/**
 * @author wangyu
 *
 */
public interface HostQuotaTogetherDAO {

	 /**
	  * 主机4小时采集统计
	 * @param startDate
	 * @param endDate
	 */
	public void hostQuotaHoursTogether(String startDate,String endDate);
	
	
	/**
	 * 主机1天采集统计
	 * @param startDate
	 * @param endDate
	 */
	public void hostQuotaDayTogether(String startDate,String endDate);
	
	
	/**
	 * 主机一周采集统计
	 * @param startDate
	 * @param endDate
	 */
	public void hostQuotaWeekTogether(String startDate,String endDate);
	
	/**
	 * 主机一个月采集统计
	 * @param startDate
	 * @param endDate
	 */
	public void hostQuotaMonthTogether(String startDate,String endDate);
	
	
	
	/**
	 * 清除4小时单位的数据
	 * @param fromDate
	 */
	public void clearHostQuotaHoursTogether(String fromDate);
	
	
	/**
	 * 清除1天单位的数据
	 * @param fromDate
	 */
	public void clearHostQuotaDayTogether(String fromDate);
	
	
	/**
	 * 清除1周单位的数据
	 * @param fromDate
	 */
	public void clearHostQuotaWeekTogether(String fromDate);
	
	
	/**
	 * 清除1月单位的数据
	 * @param fromDate
	 */
	public void clearHostQuotaMonthTogether(String fromDate);
	
}
