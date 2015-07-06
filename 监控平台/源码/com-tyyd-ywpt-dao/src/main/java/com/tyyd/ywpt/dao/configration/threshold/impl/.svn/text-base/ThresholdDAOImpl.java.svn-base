/**   
* @Title: ThresholdDAOImpl.java 
* @Package com.tyyd.ywpt.dao.configration.threshold.impl 
* @Description:  
* @author wangyu   
* @date 2014-6-22 下午1:13:51 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.configration.threshold.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.configration.threshold.ThresholdDAO;
import com.tyyd.ywpt.dao.configration.threshold.dataobject.ThresholdDomain;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class ThresholdDAOImpl extends TyydBaseDAO implements ThresholdDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.configration.threshold.dataobject.ThresholdDomain";
	 
	
	@Override
	public void addThreshold(ThresholdDomain domain) {
		this.getSqlSessionTemplate().insert(context_space +".add_threshold", domain);
	}

	@Override
	public void updateThresholdInfo(ThresholdDomain domain) {
		this.getSqlSessionTemplate().update(context_space +".update_threshold_info", domain);
	}

	@Override
	public void deleteThreshold(String id) {
		this.getSqlSessionTemplate().delete(context_space +".delete_threshold", id);
	}

	@Override
	public ThresholdDomain getThresholdById(String id) {
		return this.getSqlSessionTemplate().selectOne(context_space +".get_threshold_by_id", id);
	}

	@Override
	public List<ThresholdDomain> listThreshold(String monitorId,
			Integer configType) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("configType", configType);
		parameterMap.put("monitorId", monitorId);
		return this.getSqlSessionTemplate().selectList(context_space +".list_threshold", parameterMap);
	}

}
