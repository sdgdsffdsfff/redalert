/**   
* @Title: MySQLQuotaTogetherDAO.java 
* @Package com.tyyd.ywpt.dao.core.collect.together.mysql 
* @Description:  
* @author wangyu   
* @date 2014-9-23 上午9:44:32 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.together.mysql;

/**
 * @author wangyu
 *
 */
public interface MySQLQuotaTogetherDAO {

	/**
	  * MySQL4小时采集统计
	 * @param startDate
	 * @param endDate
	 */
	public void mysqlQuotaHoursTogether(String startDate,String endDate);
	
	
	/**
	 * MySQL1天采集统计
	 * @param startDate
	 * @param endDate
	 */
	public void mysqlQuotaDayTogether(String startDate,String endDate);
	
	
	/**
	 * MySQL一周采集统计
	 * @param startDate
	 * @param endDate
	 */
	public void mysqlQuotaWeekTogether(String startDate,String endDate);
	
	/**
	 * MySQL一个月采集统计
	 * @param startDate
	 * @param endDate
	 */
	public void mysqlQuotaMonthTogether(String startDate,String endDate);
	
	
	
	/**
	 * 清理4小时单位的垃圾数据
	 * @param fromDate
	 */
	public void clearMySQLQuotaHoursTogether(String fromDate);
	
	
	/**
	 * 清理1天单位的垃圾数据
	 * @param fromDate
	 */
	public void clearMySQLQuotaDayTogether(String fromDate);
	
	
	/**
	 * 清理1周单位的垃圾数据
	 * @param fromDate
	 */
	public void clearMySQLQuotaWeekTogether(String fromDate);
	
	/**
	 * 清理1月单位的垃圾数据
	 * @param fromDate
	 */
	public void clearMySQLQuotaMonthTogether(String fromDate);
}
