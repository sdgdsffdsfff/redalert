/**   
* @Title: BaseLineConfigDAOImpl.java 
* @Package com.tyyd.ywpt.dao.baseline.impl 
* @Description:  
* @author wangyu   
* @date 2015-3-10 上午11:10:02 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.baseline.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.baseline.BaseLineConfigDAO;
import com.tyyd.ywpt.dao.baseline.dataobject.BaseLineConfigDomain;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class BaseLineConfigDAOImpl extends TyydBaseDAO implements
		BaseLineConfigDAO {

	
	private final static String context = "com.tyyd.ywpt.dao.baseline.dataobject.BaseLineConfigDomain";
	
	@Override
	public List<BaseLineConfigDomain> listBaseLineConfig(String monitorId,
			Integer configType) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("monitorId", monitorId);
		parameter.put("configType", configType);
		
		return this.getSqlSessionTemplate().selectList(context+".list_base_line_config", parameter);
	}

	@Override
	public boolean isExistsBaseLineConfig(String monitorId, Integer configType,
			String quotaId) {
		
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("monitorId", monitorId);
		parameter.put("configType", configType);
		parameter.put("quotaId", quotaId);
		
		Integer counts = this.getSqlSessionTemplate().selectOne(context+".is_exists_base_line_config",parameter);
		
		if(counts > 0){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
