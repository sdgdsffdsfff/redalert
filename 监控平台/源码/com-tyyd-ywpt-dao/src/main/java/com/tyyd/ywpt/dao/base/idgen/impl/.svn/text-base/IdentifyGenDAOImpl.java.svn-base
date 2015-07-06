/**   
* @Title: IdentifyGenDAOImpl.java 
* @Package com.tyyd.ywpt.dao.base.idgen.impl 
* @Description:  
* @author wangyu   
* @date 2014-6-19 上午10:22:55 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.base.idgen.impl;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.base.idgen.IdentifyGenDAO;
import com.tyyd.ywpt.dao.base.idgen.dataobject.IdentifyGenDomain;

/**
 * @author wangyu
 *
 */
public class IdentifyGenDAOImpl extends TyydBaseDAO<IdentifyGenDomain> implements
		IdentifyGenDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.base.idgen.dataobject.IdentifyGenDomain";
	
	
	@Override
	public String genId(String tableName) {
		return this.getSqlSessionTemplate().selectOne(context_space+".gen_id", tableName);
	}

}
