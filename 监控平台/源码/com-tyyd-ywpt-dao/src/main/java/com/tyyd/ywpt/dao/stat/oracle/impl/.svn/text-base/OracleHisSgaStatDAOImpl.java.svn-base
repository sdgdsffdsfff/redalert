/**   
* @Title: OracleHisSgaStatDAOImpl.java 
* @Package com.tyyd.ywpt.dao.stat.oracle.impl 
* @Description:  
* @author wangyu   
* @date 2014-12-15 下午3:31:43 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.stat.oracle.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.stat.oracle.OracleHisSgaStatDAO;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.OracleDBAHisSgaStatDomain;

/**
 * @author wangyu
 *
 */
public class OracleHisSgaStatDAOImpl extends TyydBaseDAO implements OracleHisSgaStatDAO {

	
	private final static String context_space = "com.tyyd.ywpt.dao.stat.oracle.dataobject.OracleDBAHisSgaStatDomain";
	
	@Override
	public List<OracleDBAHisSgaStatDomain> listSga(String beginTime,
			String endTime, Long dataBaseId, Long instanceId,
			String quotaName) {
		
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("beginTime", beginTime);
		parameter.put("endTime", endTime);
		parameter.put("databaseId", dataBaseId);
		parameter.put("instanceId", instanceId);
		parameter.put("quotaName", quotaName);
		
		return this.getSqlSessionTemplate().selectList(context_space+".list_sga_stat", parameter);
	}

}
