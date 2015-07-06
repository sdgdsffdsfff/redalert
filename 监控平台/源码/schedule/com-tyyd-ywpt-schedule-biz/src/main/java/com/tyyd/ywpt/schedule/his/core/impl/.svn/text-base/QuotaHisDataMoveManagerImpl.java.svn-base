/**   
* @Title: QuotaHisDataMoveManagerImpl.java 
* @Package com.tyyd.ywpt.schedule.his.core.impl 
* @Description:  
* @author wangyu   
* @date 2014-9-1 下午2:25:39 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.his.core.impl;

import javax.annotation.Resource;

import com.tyyd.ywpt.biz.util.DateUtils;
import com.tyyd.ywpt.dao.core.collect.heartbeat.HeartbeatMonitorDAO;
import com.tyyd.ywpt.dao.core.collect.his.host.HostQuotaHisDAO;
import com.tyyd.ywpt.dao.core.collect.his.mysql.MySQLQuotaDayHisDAO;
import com.tyyd.ywpt.dao.core.collect.his.oracle.OracleQuotaDayHisDAO;
import com.tyyd.ywpt.dao.core.schedule.tasklog.TaskJobLogDAO;
import com.tyyd.ywpt.schedule.his.core.QuotaHisDataMoveManager;

/**
 * @author wangyu
 *
 */
public class QuotaHisDataMoveManagerImpl implements QuotaHisDataMoveManager {

	@Resource
	private HostQuotaHisDAO hostQuotaHisDAO;
	
	@Resource
	private MySQLQuotaDayHisDAO mySQLQuotaDayHisDAO;
	
	@Resource
	private OracleQuotaDayHisDAO oracleQuotaDayHisDAO;
	
	@Resource
	private HeartbeatMonitorDAO heartbeatMonitorDAO;
	
	@Resource
	private TaskJobLogDAO taskJobLogDAO;
	
	@Override
	public void moveHostHisData() {
		String date = DateUtils.getBeforeTwoDay();
		hostQuotaHisDAO.moveHisData(date);
	}

	@Override
	public void moveMySQLHisData() {
		String date = DateUtils.getBeforeTwoDay();
		mySQLQuotaDayHisDAO.moveHisData(date);
	}

	@Override
	public void moveOracleHisData() {
		String date = DateUtils.getBeforeTwoDay();
		oracleQuotaDayHisDAO.moveHisData(date);
	}

	@Override
	public void clearHeartBeat() {
		heartbeatMonitorDAO.clearOldData();
	}

	@Override
	public void clearTaskLog() {
		taskJobLogDAO.clearOldData();
	}

}
