/**   
* @Title: ConnectDBManagerImpl.java 
* @Package com.tyyd.ywpt.report.biz.monitor.impl 
* @Description:  
* @author wangyu   
* @date 2014-11-24 上午9:42:42 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.monitor.impl;

import javax.annotation.Resource;

import com.tyyd.ywpt.dao.mysql.MySQLMonitorCollectDAO;
import com.tyyd.ywpt.report.biz.monitor.ConnectDBManager;

/**
 * @author wangyu
 *
 */
public class ConnectDBManagerImpl implements ConnectDBManager {

	@Resource
	private MySQLMonitorCollectDAO mySQLMonitorCollectDAO;
	
	@Override
	public void connectDb() {
		mySQLMonitorCollectDAO.getCurrentDate();
	}

}
