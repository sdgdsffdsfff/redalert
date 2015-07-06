/**   
* @Title: DbTableSpaceHisDAOImpl.java 
* @Package com.tyyd.ywpt.dao.core.collect.datafile.impl 
* @Description:  
* @author wangyu   
* @date 2014-8-7 下午5:01:22 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.datafile.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.core.collect.datafile.DbTableSpaceHisDAO;
import com.tyyd.ywpt.dao.core.collect.datafile.dataobject.DbTableSpaceHisDomain;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class DbTableSpaceHisDAOImpl extends TyydBaseDAO implements DbTableSpaceHisDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.core.collect.datafile.dataobject.DbTableSpaceHisDomain";
	
	
	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.datafile.DbTableSpaceHisDAO#listUnCompletedTask()
	 */
	@Override
	public List<DbTableSpaceHisDomain> listUnCompletedTask() {
		// TODO Auto-generated method stub
		return this.getSqlSessionTemplate().selectList(context_space+".list_uncompleted_task");
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.datafile.DbTableSpaceHisDAO#closeNormalUnCompletedTask()
	 */
	@Override
	public void closeNormalUnCompletedTask() {
		this.getSqlSessionTemplate().update(context_space +".close_normal_uncompleted_task");

	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.datafile.DbTableSpaceHisDAO#closeUnCompletedTaskById(java.lang.String)
	 */
	@Override
	public void closeUnCompletedTaskById(String id) {
		// TODO Auto-generated method stub
		this.getSqlSessionTemplate().update(context_space +".close_uncompleted_task_by_id",id);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.datafile.DbTableSpaceHisDAO#closeNoMonitorTask()
	 */
	@Override
	public void closeNoMonitorTask() {
		// TODO Auto-generated method stub
		this.getSqlSessionTemplate().update(context_space +".close_no_monitor_task");
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.datafile.DbTableSpaceHisDAO#closeExpiredData()
	 */
	@Override
	public void closeExpiredData() {
		this.getSqlSessionTemplate().update(context_space +".close_expired_tbspace_data");
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.datafile.DbTableSpaceHisDAO#getTbsMonitorUnCompletedData()
	 */
	@Override
	public List<DbTableSpaceHisDomain> getTbsMonitorUnCompletedData() {
		return this.getSqlSessionTemplate().selectList(context_space +".list_tbs_monitor_uncompleted_limit_id");
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.datafile.DbTableSpaceHisDAO#getTbsMonitorUnCompletedObj(java.lang.String)
	 */
	@Override
	public DbTableSpaceHisDomain getTbsMonitorUnCompletedObj(String id) {
		return this.getSqlSessionTemplate().selectOne(context_space +".get_tbs_monitor_uncompleted_obj", id);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.datafile.DbTableSpaceHisDAO#getMaxSnapGmtDate(java.lang.String)
	 */
	@Override
	public String getMaxSnapGmtDate(String dbId) {
		// TODO Auto-generated method stub
		return this.getSqlSessionTemplate().selectOne(context_space +".get_max_snap_gmt_date", dbId);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.datafile.DbTableSpaceHisDAO#getMaxSnapId(java.lang.String, java.lang.String)
	 */
	@Override
	public Long getMaxSnapId(String dbId, String gmtCreated) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("gmtCreated", gmtCreated);
		parameter.put("dbId", dbId);
		return this.getSqlSessionTemplate().selectOne(context_space+".get_max_snap_id",parameter);
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.dao.core.collect.datafile.DbTableSpaceHisDAO#listLastTbsMonitorHis(java.lang.String, java.lang.Long)
	 */
	@Override
	public List<DbTableSpaceHisDomain> listLastTbsMonitorHis(String dbId,
			Long snapId) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("snapId", snapId.toString());
		parameter.put("dbId", dbId);
		return this.getSqlSessionTemplate().selectList(context_space+".list_last_tbs_monitor",parameter);
	}

}
