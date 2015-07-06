/**   
* @Title: StatisticsRecordLogDAOImpl.java 
* @Package com.tyyd.ywpt.dao.core.collect.together.log.impl 
* @Description:  
* @author wangyu   
* @date 2014-9-22 上午9:56:14 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.together.log.impl;

import java.util.HashMap;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.core.collect.together.log.StatisticsRecordLogDAO;
import com.tyyd.ywpt.dao.core.collect.together.log.dataobject.StatisticsRecordLogDomain;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class StatisticsRecordLogDAOImpl extends TyydBaseDAO implements StatisticsRecordLogDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.core.collect.together.log.dataobject.StatisticsRecordLogDomain";
	
	
	@Override
	public void addStatisticsRecordLog(StatisticsRecordLogDomain log) {
		this.getSqlSessionTemplate().insert(context_space + ".add_statistics_record_log", log);
	}


	@Override
	public void updateStatisticsRecordLog(String workTime, Integer sysType,
			Integer statType) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("workTime", workTime);
		parameterMap.put("sysType", sysType);
		parameterMap.put("statType", statType);
				
		this.getSqlSessionTemplate().update(context_space +".modify_statistics_record_by_type", parameterMap);
	}


	@Override
	public StatisticsRecordLogDomain getStatisticsRecord(Integer sysType, Integer statType) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("sysType", sysType);
		parameterMap.put("statType", statType);

		return this.getSqlSessionTemplate().selectOne(context_space +".get_statistics_record_by_type", parameterMap);
	}

}
