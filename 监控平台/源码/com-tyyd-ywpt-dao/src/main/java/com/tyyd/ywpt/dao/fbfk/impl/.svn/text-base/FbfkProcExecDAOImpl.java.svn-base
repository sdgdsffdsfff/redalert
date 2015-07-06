/**   
* @Title: FbfkProcExecDAOImpl.java 
* @Package com.tyyd.ywpt.dao.fbfk.impl 
* @Description:  
* @author wangyu   
* @date 2015-4-15 下午4:58:34 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.fbfk.impl;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.fbfk.FbfkProcExecDAO;

/**
 * @author wangyu
 *
 */
public class FbfkProcExecDAOImpl extends TyydBaseDAO implements FbfkProcExecDAO {

	private static final String context = "com.tyyd.ywpt.dao.fbfk.FbfkProcExecDAO";
	
	@Override
	public void execute(String sql) {
		this.getSqlSessionTemplate().update(context+".exec_sql",sql);
	}

}
