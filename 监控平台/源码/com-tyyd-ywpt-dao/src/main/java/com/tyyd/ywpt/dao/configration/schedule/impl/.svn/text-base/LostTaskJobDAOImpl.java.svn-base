/**   
* @Title: LostTaskJobDAOImpl.java 
* @Package com.tyyd.ywpt.dao.configration.schedule.impl 
* @Description:  
* @author wangyu   
* @date 2014-11-25 上午11:46:46 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.configration.schedule.impl;

import java.util.List;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.configration.schedule.LostTaskJobDAO;
import com.tyyd.ywpt.dao.configration.schedule.dataobject.LostTaskJobDomain;

/**
 * @author wangyu
 *
 */
public class LostTaskJobDAOImpl extends TyydBaseDAO implements LostTaskJobDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.configration.schedule.dataobject.LostTaskJobDomain";
	
	@Override
	public void collectLostTaskJob() {
		this.getSqlSessionTemplate().insert(context_space+".collect_lost_task_job");
	}

	@Override
	public List<LostTaskJobDomain> getUnAlertTask() {
		return this.getSqlSessionTemplate().selectList(context_space+".get_un_alert_task");
	}

	@Override
	public List<LostTaskJobDomain> getUnRegTask() {
		return this.getSqlSessionTemplate().selectList(context_space+".get_un_reg_task");
	}

	@Override
	public void closeNotify(Long id) {
		this.getSqlSessionTemplate().update(context_space+".close_notify",id);
	}

	@Override
	public void closeTaskJob(Long id) {
		this.getSqlSessionTemplate().update(context_space+".close_task_job",id);
	}

	
	@Override
	public void failTaskJob(Long id) {
		this.getSqlSessionTemplate().update(context_space+".fail_task_job",id);
	}
}
