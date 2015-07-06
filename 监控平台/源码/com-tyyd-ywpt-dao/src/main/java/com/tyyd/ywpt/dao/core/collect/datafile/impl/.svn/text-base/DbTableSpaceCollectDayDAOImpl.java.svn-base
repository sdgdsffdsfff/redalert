/**   
* @Title: DbTableSpaceCollectDayDAOImpl.java 
* @Package com.tyyd.ywpt.dao.core.collect.datafile.impl 
* @Description:  
* @author wangyu   
* @date 2015-6-1 下午1:08:43 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.datafile.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.core.collect.datafile.DbTableSpaceCollectDayDAO;
import com.tyyd.ywpt.dao.core.collect.datafile.dataobject.DbTableSpaceCollectDayDomain;

/**
 * @author wangyu
 *
 */
public class DbTableSpaceCollectDayDAOImpl extends TyydBaseDAO implements
		DbTableSpaceCollectDayDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.core.collect.datafile.dataobject.DbTableSpaceCollectDayDomain";
	
	
	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.datafile.DbTableSpaceCollectDayDAO#collectTbsInfo(java.lang.String, java.lang.String, java.lang.String, java.lang.Float)
	 */
	@Override
	public void collectTbsInfo(String dbId, String collectDate, String snapId,
			Float lastDayUsed) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("dbId", dbId);
		parameter.put("collectDate", collectDate);
		parameter.put("snapId", snapId);
		parameter.put("lastDayUsed", lastDayUsed);
		
		//新增数据 
		this.getSqlSessionTemplate().insert(context_space+".add_tbs_monitor_collect_day", parameter);

	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.datafile.DbTableSpaceCollectDayDAO#getMaxSnapId(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String getMaxSnapId(String dbId, String startDate, String endDate) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("dbId", dbId);
		parameter.put("startDate", startDate);
		parameter.put("endDate", endDate);

		return this.getSqlSessionTemplate().selectOne(context_space+".get_max_snap_id", parameter);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.datafile.DbTableSpaceCollectDayDAO#delCurrentTbsData(java.lang.String, java.lang.String)
	 */
	@Override
	public void delCurrentTbsData(String dbId, String collectDate) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("dbId", dbId);
		parameter.put("collectDate", collectDate);
		
		this.getSqlSessionTemplate().delete(context_space+".del_tbs_monitor_collect_day", parameter);

	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.datafile.DbTableSpaceCollectDayDAO#getMaxTbsCollectDate(java.lang.String)
	 */
	@Override
	public String getMaxTbsCollectDate(String dbId) {
		return this.getSqlSessionTemplate().selectOne(context_space+".get_max_tbs_date", dbId);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.datafile.DbTableSpaceCollectDayDAO#getMinTbsHisCollectDate(java.lang.String, java.lang.String)
	 */
	@Override
	public String getMinTbsHisCollectDate(String dbId, String strDate) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("dbId", dbId);
		parameter.put("queryDate", strDate);
		return this.getSqlSessionTemplate().selectOne(context_space+".get_tbs_his_mindate", parameter);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.datafile.DbTableSpaceCollectDayDAO#getLastDayTbsInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public List<DbTableSpaceCollectDayDomain> getLastDayTbsInfo(String dbId,
			String collectDate) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("dbId", dbId);
		parameter.put("collectDate", collectDate);
		
		return this.getSqlSessionTemplate().selectList(context_space+".get_lastday_tbsinfo", parameter);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.datafile.DbTableSpaceCollectDayDAO#updateTbsInfo(java.lang.String, java.lang.String, java.lang.String, java.lang.Float)
	 */
	@Override
	public void updateTbsInfo(String dbId, String collectDate, String tbsName,
			Float lastDayUsed) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("dbId", dbId);
		parameter.put("collectDate", collectDate);
		parameter.put("tbsName", tbsName);
		parameter.put("lastDayUsed", lastDayUsed);
		
		this.getSqlSessionTemplate().update(context_space+".update_tbsinfo", parameter);

	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.datafile.DbTableSpaceCollectDayDAO#sumDiskTotal(java.lang.String)
	 */
	@Override
	public List<DbTableSpaceCollectDayDomain> sumTbsTotal(String dbId) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("dbId", dbId);
		return this.getSqlSessionTemplate().selectList(context_space+".total_tbs_report", parameter);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.datafile.DbTableSpaceCollectDayDAO#sumDiskDelta(java.lang.String)
	 */
	@Override
	public List<DbTableSpaceCollectDayDomain> sumTbsDelta(String dbId) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("dbId", dbId);
		return this.getSqlSessionTemplate().selectList(context_space+".delta_tbs_Report", parameter);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.datafile.DbTableSpaceCollectDayDAO#sumDiskDeltaByDiskName(java.lang.String, java.lang.String)
	 */
	@Override
	public List<DbTableSpaceCollectDayDomain> sumTbsDeltaByTbsName(
			String dbId,String tbsName) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("dbId", dbId);
		parameter.put("tbsName", tbsName);
		return this.getSqlSessionTemplate().selectList(context_space+".delta_tbs_report_by_tbs", parameter);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.datafile.DbTableSpaceCollectDayDAO#sumDiskTotalByDisk(java.lang.String, java.lang.String)
	 */
	@Override
	public List<DbTableSpaceCollectDayDomain> sumTbsTotalByTbs(String dbId,
			String tbsName) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("dbId", dbId);
		parameter.put("tbsName", tbsName);
		return this.getSqlSessionTemplate().selectList(context_space+".total_tbs_report_by_tbs", parameter);
	}

}
