/**   
* @Title: DbLogAlertDAOImpl.java 
* @Package com.tyyd.ywpt.dao.alert.logalert.impl 
* @Description:  
* @author wangyu   
* @date 2014-8-5 下午4:26:57 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.alert.logalert.impl;

import java.util.List;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.alert.logalert.DbLogAlertDAO;
import com.tyyd.ywpt.dao.alert.logalert.dataobject.DbLogAlertDomain;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class DbLogAlertDAOImpl extends TyydBaseDAO implements DbLogAlertDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.alert.logalert.dataobject.DbLogAlertDomain";
	
	@Override
	public List<DbLogAlertDomain> listNoCompletedMsg() {
		return this.getSqlSessionTemplate().selectList(context_space+".list_no_completed_alert");
	}

	@Override
	public void updateDbLogAlertCompleted(String id) {
		this.getSqlSessionTemplate().update(context_space +".update_no_completed_alert_by_id",id);
	}

}
