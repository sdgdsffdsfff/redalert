/**   
* @Title: DataSourceDefManagerImpl.java 
* @Package com.tyyd.ywpt.biz.ds.impl 
* @Description:  
* @author wangyu   
* @date 2014-7-9 下午5:13:29 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.ds.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.tyyd.ywpt.biz.bean.dataobject.CustomerBeanPrefixConstast;
import com.tyyd.ywpt.biz.dict.SysTypeEnum;
import com.tyyd.ywpt.biz.ds.DataSourceBizDefManager;
import com.tyyd.ywpt.dao.configration.db.DbConfigDAO;
import com.tyyd.ywpt.dao.configration.db.dataobject.DbConfigDomain;
import com.tyyd.ywpt.tools.ds.dataobject.DynamicDataSourceDomain;
import com.tyyd.ywpt.tools.ds.dataobject.DynamicDataSourceDomain.dbEnum;

/**
 * @author wangyu
 *
 */
public class DataSourceBizDefManagerImpl implements DataSourceBizDefManager {
	
	@Resource
	private DbConfigDAO dbConfigDAO;

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, DynamicDataSourceDomain> getMySQLDsDef(String serverId) {
		List<DbConfigDomain> list = dbConfigDAO.listDbConfigByServerId(serverId, SysTypeEnum.MySQL.getVal().toString());
		if(list == null){
			return Collections.EMPTY_MAP;
		}
		Map<String, DynamicDataSourceDomain> result = new HashMap<String, DynamicDataSourceDomain>();
		for(DbConfigDomain domain : list){
			String beanId = CustomerBeanPrefixConstast.MYSQL_DS_PREFIX + domain.getDbId();
			
			DynamicDataSourceDomain dynamicDataSourceDomain = new DynamicDataSourceDomain();
			dynamicDataSourceDomain.setPort(domain.getPort());
			dynamicDataSourceDomain.setIpAddr(domain.getIpAddr());
			dynamicDataSourceDomain.setUserName(domain.getDbUserName());
			dynamicDataSourceDomain.setPasswd(domain.getDbPasswd());
			dynamicDataSourceDomain.setInstanceId(domain.getDbName());
			dynamicDataSourceDomain.setDbType(dbEnum.MySQL.getDatabase());
			
			dynamicDataSourceDomain.setHostId(domain.getHostId());
			dynamicDataSourceDomain.setDbId(domain.getDbId());
			
			result.put(beanId, dynamicDataSourceDomain);
		}
		
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, DynamicDataSourceDomain> getOracleDsDef(String serverId) {
		List<DbConfigDomain> list = dbConfigDAO.listDbConfigByServerId(serverId, SysTypeEnum.Oracle.getVal().toString());
		if(list == null){
			return Collections.EMPTY_MAP;
		}
		Map<String, DynamicDataSourceDomain> result = new HashMap<String, DynamicDataSourceDomain>();
		for(DbConfigDomain domain : list){
			String beanId = CustomerBeanPrefixConstast.ORACLE_DS_PREFIX + domain.getDbId();
			
			DynamicDataSourceDomain dynamicDataSourceDomain = new DynamicDataSourceDomain();
			dynamicDataSourceDomain.setPort(domain.getPort());
			dynamicDataSourceDomain.setIpAddr(domain.getIpAddr());
			dynamicDataSourceDomain.setUserName(domain.getDbUserName());
			dynamicDataSourceDomain.setPasswd(domain.getDbPasswd());
			dynamicDataSourceDomain.setInstanceId(domain.getSid());
			dynamicDataSourceDomain.setDbType(dbEnum.Oracle.getDatabase());
			
			dynamicDataSourceDomain.setHostId(domain.getHostId());
			dynamicDataSourceDomain.setDbId(domain.getDbId());
			
			result.put(beanId, dynamicDataSourceDomain);
		}
		
		return result;
	}

}
