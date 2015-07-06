/**   
* @Title: OraclePerformanceStatDAO.java 
* @Package com.tyyd.ywpt.dao.stat.oracle 
* @Description:  
* @author wangyu   
* @date 2015-5-12 上午9:54:31 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.stat.oracle;

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
public interface OraclePerformanceStatDAO {

	
	/**
	 * 快照对应排名前20的SQL 
	 * @param snapId
	 * @param dataBaseId
	 * @param instanceId
	 * @return
	 */
	public List<OracleSnapTopSQLDomain> listTopSQL(Long snapId,Long dataBaseId,Long instanceId,String sortColumn,String sortType);
	
	
	/**
	 * SQL内容
	 * @param sqlId
	 * @param dataBaseId
	 * @param instanceId
	 * @return
	 */
	public String getSqlTextById(String sqlId,Long dataBaseId,Long instanceId); 
	
	
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
	 * @param waitClass
	 * @return
	 */
	public List<OracleSnapWaitEventDomain> totalWaitEvent(Long snapId,Long dataBaseId,Long instanceId,Long lastSnapId);
	
	/**
	 * 事件类型
	 * @param snapId
	 * @param dataBaseId
	 * @param instanceId
	 * @param eventName
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
	
	
	/**
	 * 定位snap
	 * @param dbId
	 * @param instanceId
	 * @param quotaTime
	 * @return
	 */
	public OracleSnapShotDomain getOracleSnapShotDomain(Long dbId,Long instanceId,String quotaTime);
	
	/** 
	 * 上一个快照
	 * @param dbId
	 * @param instanceId
	 * @param snapId
	 * @return
	 */
	public OracleSnapShotDomain lastOracleSnapShotDomain(Long dbId,Long instanceId,Long snapId);
}
