/**   
* @Title: OraclePerformanceStatManager.java 
* @Package com.tyyd.ywpt.report.biz.stat.oracle.performace 
* @Description:  
* @author wangyu   
* @date 2015-5-13 上午11:13:30 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.stat.oracle.performace;

import java.util.List;

import com.tyyd.ywpt.dao.stat.oracle.dataobject.OracleSnapShotDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.snap.OracleSnapActiceSessionDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.snap.OracleSnapEventClassDetailDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.snap.OracleSnapEventSQLDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.snap.OracleSnapTopSQLDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.snap.OracleSnapWaitEventDomain;

/**
 * @author wangyu
 *
 */
public interface OraclePerformanceStatManager {

	
	/**
	 * 获取快照信息
	 * @param oracleQuotaCollectId
	 * @return
	 */
	public OracleSnapShotDomain getSnap(String oracleQuotaCollectId);
	
	/**
	 * 获取top20 的SQL
	 * @return
	 */
	public List<OracleSnapTopSQLDomain> getTopSQL(Long snapId,Long dataBaseId,Long instanceId,String sortColumn,String sortType);
	
	/**
	 * 快照对应的active session
	 * @param snapId
	 * @param dataBaseId
	 * @param instanceId
	 * @return
	 */
	public List<OracleSnapActiceSessionDomain> listActiceSession(Long snapId,Long dataBaseId,Long instanceId);
	
	
	/**
	 * 总等待事件
	 * @param snapId
	 * @param dataBaseId
	 * @param instanceId
	 * @return
	 */
	public List<OracleSnapWaitEventDomain> totalWaitEvent(Long snapId,Long dataBaseId,Long instanceId,Long lastSnapId);
	
	
	/**
	 * 事件类型详细
	 * @param snapId
	 * @param dataBaseId
	 * @param instanceId
	 * @param waitClass
	 * @return
	 */
	public List<OracleSnapEventClassDetailDomain> loadEventByClass(Long snapId,Long dataBaseId,Long instanceId,String waitClass,Long lastSnapId);
	
	
	/**
	 * 事件对应的SQL
	 * @param snapId
	 * @param dataBaseId
	 * @param instanceId
	 * @param eventName
	 * @return
	 */
	public List<OracleSnapEventSQLDomain> loadEventSql(Long snapId,Long dataBaseId,Long instanceId,String eventName);
	
	
	public String getSqlText(String sqlId,Long dataBaseId,Long instanceId);
	
	
}
