/**   
* @Title: TaskJobLogDAOImpl.java 
* @Package com.tyyd.ywpt.dao.core.schedule.tasklog.impl 
* @Description:  
* @author wangyu   
* @date 2014-6-25 上午8:43:50 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.schedule.tasklog.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.base.dataobject.PageQuery;
import com.tyyd.ywpt.dao.core.schedule.tasklog.TaskJobLogDAO;
import com.tyyd.ywpt.dao.core.schedule.tasklog.dataobject.TaskJobLogDomain;

/**
 * @author wangyu
 *
 */
public class TaskJobLogDAOImpl extends TyydBaseDAO<List<TaskJobLogDomain>> implements TaskJobLogDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.core.schedule.tasklog.dataobject.TaskJobLogDomain";
	
	@Override
	public void addTaskJobLog(TaskJobLogDomain domain) {
		this.getSqlSessionTemplate().insert(context_space +".add_task_job_log", domain);
	}

	@Override
	public PageQuery<List<TaskJobLogDomain>> listTaskJobLog(
			PageQuery<List<TaskJobLogDomain>> pageQuery,String monitorId,Integer sysType,String beanId,String jobId) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("monitorId", monitorId);
		parameter.put("sysType", sysType);
		parameter.put("beanId", beanId);
		parameter.put("jobId", jobId);
		return this.selectPages(context_space +".list_task_job_log", pageQuery, parameter);
	}

	@Override
	public List<TaskJobLogDomain> listTaskJobByDaemon(String serverId) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("serverId", serverId);
		return this.getSqlSessionTemplate().selectList(context_space + ".list_task_log_by_daemon", parameter);
	}

	@Override
	public void clearOldData() {
		this.getSqlSessionTemplate().delete(context_space+".del_tasklog_by_old_date");
	}

	@Override
	public List<TaskJobLogDomain> listTaskJobByDaemon() {
		return this.getSqlSessionTemplate().selectList(context_space + ".list_all_task_log_by_daemon");
	}

}
