/**   
* @Title: MyAwrReviewDAOImpl.java 
* @Package com.tyyd.ywpt.dao.stat.mysql.impl 
* @Description:  
* @author wangyu   
* @date 2015-5-19 下午3:49:41 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.stat.mysql.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.stat.mysql.MyAwrReviewDAO;
import com.tyyd.ywpt.dao.stat.mysql.dataobject.MyawrQueryReviewDomain;
import com.tyyd.ywpt.dao.stat.mysql.dataobject.MyawrQueryReviewHistoryDomain;

/**
 * @author wangyu
 *
 */
public class MyAwrReviewDAOImpl extends TyydBaseDAO implements
		MyAwrReviewDAO {

	
	private final static String context_space = "com.tyyd.ywpt.dao.stat.mysql.dataobject.MyawrQueryReviewHistoryDomain";
	
	
	@Override
	public List<MyawrQueryReviewHistoryDomain> listMyAwrQueryReviewHistory(
			String dbId, String startTime, String endTime,String sortColumn,String sortType) {
		
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("dbId", dbId);
		parameter.put("startTime", startTime);
		parameter.put("endTime", endTime);
		parameter.put("sortColumn", sortColumn);
		parameter.put("sortType", sortType);
		
		return this.getSqlSessionTemplate().selectList(context_space+".list_mysql_awr_stat", parameter);
	}

	@Override
	public List<MyawrQueryReviewHistoryDomain> loadMyAwrQueryReviewHistoryByCheckSum(
			String dbId, String startTime, String endTime, String checkSum) {
		
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("dbId", dbId);
		parameter.put("startTime", startTime);
		parameter.put("endTime", endTime);
		parameter.put("checkSum", checkSum);
		
		return this.getSqlSessionTemplate().selectList(context_space+".load_mysql_awr_stat_by_checksum",parameter);
	}

	@Override
	public MyawrQueryReviewDomain getMyawrQueryReview(String checkSum) {
		return this.getSqlSessionTemplate().selectOne(context_space+".load_mysql_sql_awr_stat_by_checksum",checkSum);
	}

	@Override
	public boolean isExistsMyAwrQueryReviewHistory(String dbId,
			String startTime, String endTime) {
		
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("dbId", dbId);
		parameter.put("startTime", startTime);
		parameter.put("endTime", endTime);
		
		
		Long counts = this.getSqlSessionTemplate().selectOne(context_space+".is_exists_my_awr_query_review_history", parameter);
		if(counts == null || counts == 0l){
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

}
