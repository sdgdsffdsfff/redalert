/**   
* @Title: OracleQuotaTogetherDAO.java 
* @Package com.tyyd.ywpt.dao.core.collect.together.oracle 
* @Description:  
* @author wangyu   
* @date 2014-9-23 上午9:56:20 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.together.oracle;

/**
 * @author wangyu
 *
 */
public interface OracleQuotaTogetherDAO {

	/**
	  * oracle4小时采集统计
	 * @param startDate
	 * @param endDate
	 */
	public void oracleQuotaHoursTogether(String startDate,String endDate);
	
	
	/**
	 * oracle1天采集统计
	 * @param startDate
	 * @param endDate
	 */
	public void oracleQuotaDayTogether(String startDate,String endDate);
	
	
	/**
	 * oracle一周采集统计
	 * @param startDate
	 * @param endDate
	 */
	public void oracleQuotaWeekTogether(String startDate,String endDate);
	
	/**
	 * oracle一个月采集统计
	 * @param startDate
	 * @param endDate
	 */
	public void oracleQuotaMonthTogether(String startDate,String endDate);
	
	
	
	
	/**
	 * 清理4小时为单位的数据
	 * @param fromDate
	 */
	public void clearOracleQuotaHoursTogether(String fromDate);
	
	
	/**
	 * 清理1天为单位的数据
	 * @param fromDate
	 */
	public void clearOracleQuotaDayTogether(String fromDate);
	
	
	/**
	 * 清理1周为单位的数据
	 * @param fromDate
	 */
	public void clearOracleQuotaWeekTogether(String fromDate);
	
	
	/**
	 * 清理1月为单位的数据
	 * @param fromDate
	 */
	public void clearOracleQuotaMonthTogether(String fromDate);
}
