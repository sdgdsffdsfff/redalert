/**   
* @Title: DBAHisTablesDAO.java 
* @Package com.tyyd.ywpt.dao.dba.his 
* @Description:  
* @author wangyu   
* @date 2015-2-4 上午10:59:28 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.dba.his;

import java.util.List;

import com.tyyd.ywpt.dao.dba.his.dataobject.DBAStatSnapDomain;

/**
 * @author wangyu
 *
 */
public interface DBAHisTablesDAO {

	/**
	 * 
	 * @param srcTableName
	 * @param destTableName
	 * @param dbId
	 * @param snapId
	 * @param instanceId
	 */
	public void moveDBAHisTable(String srcTableName,String destTableName,Long dbId,Long snapId,Long instanceId);
	
	
	/**
	 * 限1000条
	 * @return
	 */
	public List<DBAStatSnapDomain> listHisSnap();
}
