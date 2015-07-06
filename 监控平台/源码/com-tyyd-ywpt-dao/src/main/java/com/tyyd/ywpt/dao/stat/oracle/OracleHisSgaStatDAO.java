/**   
* @Title: OracleHisSgaStatDAO.java 
* @Package com.tyyd.ywpt.dao.stat.oracle 
* @Description:  
* @author wangyu   
* @date 2014-12-15 下午3:27:50 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.stat.oracle;

import java.util.List;

import com.tyyd.ywpt.dao.stat.oracle.dataobject.OracleDBAHisSgaStatDomain;

/**
 * @author wangyu
 *
 */
public interface OracleHisSgaStatDAO {

	/**
	 * 获取某一类的指标数据
	 * @param beginTime
	 * @param endTime
	 * @param dataBaseId
	 * @param instanceNumber
	 * @param quotaName
	 * @return
	 */
	public List<OracleDBAHisSgaStatDomain> listSga(String beginTime,String endTime,Long dataBaseId,Long instanceId,String quotaName);

}
