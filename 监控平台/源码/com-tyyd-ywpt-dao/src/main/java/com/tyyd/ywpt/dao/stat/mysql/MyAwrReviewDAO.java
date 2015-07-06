/**   
* @Title: MyAwrReviewDAo.java 
* @Package com.tyyd.ywpt.dao.stat.mysql 
* @Description:  
* @author wangyu   
* @date 2015-5-19 下午2:14:08 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.stat.mysql;

import java.util.List;

import com.tyyd.ywpt.dao.stat.mysql.dataobject.MyawrQueryReviewDomain;
import com.tyyd.ywpt.dao.stat.mysql.dataobject.MyawrQueryReviewHistoryDomain;

/**
 * @author wangyu
 *
 */
public interface MyAwrReviewDAO {

	/**
	 * 获取最近20条慢sql
	 * @param dbId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<MyawrQueryReviewHistoryDomain> listMyAwrQueryReviewHistory(String dbId,String startTime,String endTime,String sortColumn,String sortType);
	
	
	/**
	 * 判断是否存在
	 * @param dbId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public boolean isExistsMyAwrQueryReviewHistory(String dbId,String startTime,String endTime); 
	
	/**
	 * 获取SQL的性能分析
	 * @param dbId
	 * @param startTime
	 * @param endTime
	 * @param checkSum
	 * @return
	 */
	public List<MyawrQueryReviewHistoryDomain> loadMyAwrQueryReviewHistoryByCheckSum(String dbId,String startTime,String endTime,String checkSum);

	
	/**
	 * 获取checkSum的情况
	 * @param checkSum
	 * @return
	 */
	public MyawrQueryReviewDomain getMyawrQueryReview(String checkSum);

}
