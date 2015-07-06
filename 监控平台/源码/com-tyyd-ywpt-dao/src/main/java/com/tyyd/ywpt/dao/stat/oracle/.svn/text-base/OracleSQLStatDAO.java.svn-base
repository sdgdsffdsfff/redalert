/**   
* @Title: OracleSQLStatDAO.java 
* @Package com.tyyd.ywpt.dao.stat.oracle.dataobject 
* @Description:  
* @author wangyu   
* @date 2014-12-3 下午2:02:19 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.stat.oracle;

import java.util.List;

import com.tyyd.ywpt.dao.stat.oracle.dataobject.OracleDbaHisActiveSessEventDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.OracleDbaHisActiveSessSampleDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.OracleDbaHisSQLStatDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.OracleDbaSqlStatListDomain;

/**
 * @author wangyu
 *
 */
public interface OracleSQLStatDAO {

	
	/**
	 * sqlstat
	 * @param beginTime
	 * @param endTime
	 * @param statType
	 * @param databaseId
	 * @param instanceId
	 * @param sqlId
	 * @return
	 */
	public List<OracleDbaHisSQLStatDomain> listOracleSqlStat(String beginTime,String endTime,String statType,Long databaseId,Long instanceId,String sqlId);
	
	
	/**
	 * 获取sampleTime
	 * @param beginTime
	 * @param endTime
	 * @param databaseId
	 * @param instanceId
	 * @param minCounts 最小数
	 * @return
	 */
	public List<OracleDbaHisActiveSessSampleDomain> listSampleTimes(String beginTime,String endTime,Long databaseId,Long instanceId,Integer minCounts);
	
	
	/**
	 * 事件列表
	 * @param sampleTime
	 * @param databaseId
	 * @param instanceId
	 * @return
	 */
	public List<OracleDbaHisActiveSessEventDomain> listEventsBySampleTime(String sampleTime,Long databaseId,Long instanceId);
	
	
	
	/**
	 * 根据sqlID，查询对应times次内的运行情况
	 * @param sqlId
	 * @param databaseId
	 * @param instanceId
	 * @param times
	 * @return
	 */
	public List<OracleDbaSqlStatListDomain> listOracleSqlStatBySqlId(String sqlId,Long databaseId,Long instanceId,Integer times);
}
