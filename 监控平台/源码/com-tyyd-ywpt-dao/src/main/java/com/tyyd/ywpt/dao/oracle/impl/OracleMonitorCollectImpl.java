/**   
* @Title: OracleMonitorCollectImpl.java 
* @Package com.tyyd.ywpt.dao.oracle.impl 
* @Description:  
* @author wangyu   
* @date 2014-7-7 上午9:30:11 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.oracle.impl;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.oracle.OracleMonitorCollect;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class OracleMonitorCollectImpl extends TyydBaseDAO implements
		OracleMonitorCollect {

	private static final String SPACE = "com.tyyd.ywpt.dao.oracle.dataobject.OracleMonitorCollect";
	
	@Override
	public String getSysdate() {
		return this.getSqlSessionTemplate().selectOne(SPACE + ".get_current_date");
	}

}
