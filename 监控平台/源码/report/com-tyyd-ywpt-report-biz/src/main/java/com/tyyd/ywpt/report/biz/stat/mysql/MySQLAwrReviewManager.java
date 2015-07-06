/**   
* @Title: MySQLAwrReviewManager.java 
* @Package com.tyyd.ywpt.report.biz.stat.mysql 
* @Description:  
* @author wangyu   
* @date 2015-5-20 上午9:49:47 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.stat.mysql;

import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.core.collect.even.mysql.dataobject.MysqlQuotaCollectDayDomain;
import com.tyyd.ywpt.dao.stat.mysql.dataobject.MyawrQueryReviewDomain;
import com.tyyd.ywpt.dao.stat.mysql.dataobject.MyawrQueryReviewHistoryDomain;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleGeneralReportDataBO;

/**
 * @author wangyu
 *
 */
public interface MySQLAwrReviewManager {

	
	/**
	 * 获取最近20条慢sql
	 * @param mysqlCollectDayId
	 * @return
	 */
	public List<MyawrQueryReviewHistoryDomain> listMyAwrQueryReviewHistory(String mysqlCollectDayId,String sortColumn,String sortType);
	
	/**
	 * 检查mysql awr是否采集
	 * @param mysqlCollectDayId
	 * @return
	 */
	public boolean checkMySQLAwrExists(String mysqlCollectDayId);
	
	
	/**
	 * 图表
	 * @param dbId
	 * @param snapTime
	 * @param checkSum
	 * @return
	 */
	public Map<String,MultipleGeneralReportDataBO> listMySQLAwrReview(String dbId,String start,String end,String checkSum);
	
	
	/**
	 * 获取checkSum的情况
	 * @param checkSum
	 * @return
	 */
	public MyawrQueryReviewDomain getMyawrQueryReview(String checkSum);
	
	
	/**
	 * 查询mysql采集数据
	 * @param id
	 * @return
	 */
	public MysqlQuotaCollectDayDomain loadMysqlQuotaCollectDay(String id);
	
}
