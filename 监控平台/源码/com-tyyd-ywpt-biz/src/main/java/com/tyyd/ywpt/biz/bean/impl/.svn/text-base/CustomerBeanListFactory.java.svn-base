/**   
* @Title: CustomerBeanListFactory.java 
* @Package com.tyyd.ywpt.biz.bean.dataobject 
* @Description:  
* @author wangyu   
* @date 2014-6-26 下午4:28:13 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.bean.impl;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tyyd.ywpt.biz.bean.dataobject.CustomerBeanPrefixConstast;
import com.tyyd.ywpt.biz.bean.dataobject.DynamicBeanBO;
import com.tyyd.ywpt.biz.dict.DictConstast;
import com.tyyd.ywpt.biz.dict.SysTypeEnum;
import com.tyyd.ywpt.dao.configration.db.dataobject.DbConfigDomain;
import com.tyyd.ywpt.dao.configration.host.dataobject.HostConfigDomain;
import com.tyyd.ywpt.dao.configration.hostdiralert.dataobject.HostDirAlertConfigDomain;
import com.tyyd.ywpt.tools.ds.dataobject.DynamicDataSourceDomain;
import com.tyyd.ywpt.tools.ds.dataobject.DynamicDataSourceDomain.dbEnum;

/**
 * @author wangyu
 * 注册的bean集合，可以查看
 */
public class CustomerBeanListFactory {
	
	public static final Logger LOGGER = Logger.getLogger(CustomerBeanListFactory.class);  
	
	/**
	 * 主机定义
	 */
	private ConcurrentMap<String,DynamicBeanBO<HostConfigDomain>> hostRegisteredMap = new ConcurrentHashMap<String,DynamicBeanBO<HostConfigDomain>>();
	
	
	/**
	 * Oracle定义
	 */
	private ConcurrentMap<String,DynamicBeanBO<DbConfigDomain>> oracleRegisteredMap = new ConcurrentHashMap<String,DynamicBeanBO<DbConfigDomain>>();
	
	
	/**
	 * MySQL定义
	 */
	private ConcurrentMap<String,DynamicBeanBO<DbConfigDomain>> mysqlRegisteredMap = new ConcurrentHashMap<String,DynamicBeanBO<DbConfigDomain>>();

	/**
	 * KeepAlived定义
	 */
	private ConcurrentMap<String,DynamicBeanBO<DbConfigDomain>> keepAlivedRegisteredMap = new ConcurrentHashMap<String,DynamicBeanBO<DbConfigDomain>>();

	
	/**
	 * Oracle告警日志
	 */
	private ConcurrentMap<String,DynamicBeanBO<HostDirAlertConfigDomain>> oraAlertRegisteredMap = new ConcurrentHashMap<String,DynamicBeanBO<HostDirAlertConfigDomain>>();

	
	/**
	 * MySQL 告警日志
	 */
	private ConcurrentMap<String,DynamicBeanBO<HostDirAlertConfigDomain>> mysqlAlertRegisteredMap = new ConcurrentHashMap<String,DynamicBeanBO<HostDirAlertConfigDomain>>();

	/**
	 * KeepAlived 告警日志
	 */
	private ConcurrentMap<String,DynamicBeanBO<HostDirAlertConfigDomain>> keepAlivedAlertRegisteredMap = new ConcurrentHashMap<String,DynamicBeanBO<HostDirAlertConfigDomain>>();

	/**
	 * MySQL 数据源
	 */
	private ConcurrentMap<String,DynamicBeanBO<DynamicDataSourceDomain>> mysqlDataSourceRegisteredMap = new ConcurrentHashMap<String,DynamicBeanBO<DynamicDataSourceDomain>>();

	
	/**
	 * Oracle 数据源
	 */
	private ConcurrentMap<String,DynamicBeanBO<DynamicDataSourceDomain>> oraDataSourceRegisteredMap = new ConcurrentHashMap<String,DynamicBeanBO<DynamicDataSourceDomain>>();

	
	/**
	 * 注册
	 */
	public boolean beanDefinitionBuild(String beanId,Object propertyBean){
		boolean isSucess = false;
		try{
			//主机注册
			if(propertyBean instanceof HostConfigDomain){
				registerHostContext(beanId,propertyBean);
			}else if(propertyBean instanceof DbConfigDomain){
				DbConfigDomain domain = (DbConfigDomain) propertyBean;
				//Oracle ssh Bean注册
				if(domain.getDbType().intValue() == DictConstast.SYS_TYPE_ORACLE.intValue()){
					registerOracleContext(beanId,domain);
				}else if(domain.getDbType().intValue() == DictConstast.SYS_TYPE_MYSQL.intValue()){
					//MySQL ssh bean 注册
					registerMySQLContext(beanId,domain);
				}else if(domain.getDbType().intValue() == SysTypeEnum.KEEPALIVED.getVal().intValue()){
					//KeepAlived ssh bean 注册
					registerKeepAlivedContext(beanId,domain);
				}
	        }else if(propertyBean instanceof HostDirAlertConfigDomain){
	        	HostDirAlertConfigDomain domain = (HostDirAlertConfigDomain) propertyBean;
	        	if(domain.getConfigType().intValue() == DictConstast.SYS_TYPE_ORACLE.intValue()){
	        		registerOraAlertContext(beanId,propertyBean);
	        	}else if(domain.getConfigType().intValue() == DictConstast.SYS_TYPE_MYSQL.intValue()){
	        		registerMySQLAlertContext(beanId,propertyBean);
	        	}else if(domain.getConfigType().intValue() == SysTypeEnum.KEEPALIVED.getVal().intValue()){
	        		registerKeepAlivedAlertContext(beanId,propertyBean);
	        	}
	        }else if(propertyBean instanceof DynamicDataSourceDomain){
	        	DynamicDataSourceDomain domain = (DynamicDataSourceDomain) propertyBean;
	        	if(domain.getDbType().equals(dbEnum.Oracle.getDatabase())){
	        		registerOracleDataSourceContext(beanId,propertyBean);
	        	}else if(domain.getDbType().equals(dbEnum.MySQL.getDatabase())){
	        		registerMySQLDataSourceContext(beanId,propertyBean);
	        	}
	        }
		}catch(Exception e){
			LOGGER.error("注册自定义中心的Bean异常",e);
			return isSucess;
		}
		
		return true;
	}

	
	/**
	 * 注册主机
	 */
	private void registerHostContext(String beanId,Object propertyBean){
		HostConfigDomain domain = (HostConfigDomain) propertyBean;
		DynamicBeanBO<HostConfigDomain> dynamicBeanPropery = new DynamicBeanBO<HostConfigDomain>();
		if(hostRegisteredMap.containsKey(beanId)){
			dynamicBeanPropery = hostRegisteredMap.get(beanId);
		}
		//如果存在则重新注册
		dynamicBeanPropery.setBeanCreatedDate(new Date());
		dynamicBeanPropery.setPrefix(CustomerBeanPrefixConstast.HOST_PREFIX);
		dynamicBeanPropery.setBeanId(beanId);
		dynamicBeanPropery.setPropertyBean(domain);
		
		hostRegisteredMap.put(beanId, dynamicBeanPropery);
	}
	
	
	/**
	 * 注册MySQL
	 * @param beanId
	 * @param propertyBean
	 */
	private void registerMySQLContext(String beanId,DbConfigDomain domain){
		DynamicBeanBO<DbConfigDomain> dynamicBeanPropery = new DynamicBeanBO<DbConfigDomain>();
		if(mysqlRegisteredMap.containsKey(beanId)){
			dynamicBeanPropery = mysqlRegisteredMap.get(beanId);
		}
		dynamicBeanPropery.setPrefix(CustomerBeanPrefixConstast.MYSQL_PREFIX);
		dynamicBeanPropery.setBeanCreatedDate(new Date());
		dynamicBeanPropery.setPropertyBean(domain);
		
		mysqlRegisteredMap.put(beanId, dynamicBeanPropery);
	}
	
	/**
	 * 注册KeepAlived
	 * @param beanId
	 * @param propertyBean
	 */
	private void registerKeepAlivedContext(String beanId,DbConfigDomain domain){
		DynamicBeanBO<DbConfigDomain> dynamicBeanPropery = new DynamicBeanBO<DbConfigDomain>();
		if(keepAlivedRegisteredMap.containsKey(beanId)){
			dynamicBeanPropery = keepAlivedRegisteredMap.get(beanId);
		}
		dynamicBeanPropery.setPrefix(CustomerBeanPrefixConstast.KEEP_ALIVED_PREFIX);
		dynamicBeanPropery.setBeanCreatedDate(new Date());
		dynamicBeanPropery.setPropertyBean(domain);
		
		keepAlivedRegisteredMap.put(beanId, dynamicBeanPropery);
	}
	
	/**
	 * 注册mysql
	 * @param beanId
	 * @param propertyBean
	 */
	private void registerOracleContext(String beanId,DbConfigDomain domain){
		DynamicBeanBO<DbConfigDomain> dynamicBeanPropery = new DynamicBeanBO<DbConfigDomain>();
		if(oracleRegisteredMap.containsKey(beanId)){
			dynamicBeanPropery = oracleRegisteredMap.get(beanId);
		}
		dynamicBeanPropery.setPrefix(CustomerBeanPrefixConstast.ORACLE_PREFIX);
		dynamicBeanPropery.setBeanCreatedDate(new Date());
		dynamicBeanPropery.setPropertyBean(domain);
		
		oracleRegisteredMap.put(beanId, dynamicBeanPropery);
	}

	
	/**
	 * 注册Oracle告警日志
	 */
	private void registerOraAlertContext(String beanId,Object propertyBean){
		HostDirAlertConfigDomain domain = (HostDirAlertConfigDomain) propertyBean;
		DynamicBeanBO<HostDirAlertConfigDomain> dynamicBeanPropery = new DynamicBeanBO<HostDirAlertConfigDomain>();
		if(oraAlertRegisteredMap.containsKey(beanId)){
			dynamicBeanPropery = oraAlertRegisteredMap.get(beanId);
		}
		//如果存在则重新注册
		dynamicBeanPropery.setBeanCreatedDate(new Date());
		dynamicBeanPropery.setPrefix(CustomerBeanPrefixConstast.ORACLE_ALERT_PREFIX);
		dynamicBeanPropery.setBeanId(beanId);
		dynamicBeanPropery.setPropertyBean(domain);
		
		oraAlertRegisteredMap.put(beanId, dynamicBeanPropery);
	}
	
	
	/**
	 * 注册MySQL告警日志
	 */
	private void registerMySQLAlertContext(String beanId,Object propertyBean){
		HostDirAlertConfigDomain domain = (HostDirAlertConfigDomain) propertyBean;
		DynamicBeanBO<HostDirAlertConfigDomain> dynamicBeanPropery = new DynamicBeanBO<HostDirAlertConfigDomain>();
		if(mysqlAlertRegisteredMap.containsKey(beanId)){
			dynamicBeanPropery = mysqlAlertRegisteredMap.get(beanId);
		}
		//如果存在则重新注册
		dynamicBeanPropery.setBeanCreatedDate(new Date());
		dynamicBeanPropery.setPrefix(CustomerBeanPrefixConstast.MYSQL_ALERT_PREFIX);
		dynamicBeanPropery.setBeanId(beanId);
		dynamicBeanPropery.setPropertyBean(domain);
		
		mysqlAlertRegisteredMap.put(beanId, dynamicBeanPropery);
	}
	
	
	/**
	 * 注册KeepAlived告警日志
	 */
	private void registerKeepAlivedAlertContext(String beanId,Object propertyBean){
		HostDirAlertConfigDomain domain = (HostDirAlertConfigDomain) propertyBean;
		DynamicBeanBO<HostDirAlertConfigDomain> dynamicBeanPropery = new DynamicBeanBO<HostDirAlertConfigDomain>();
		if(keepAlivedAlertRegisteredMap.containsKey(beanId)){
			dynamicBeanPropery = keepAlivedAlertRegisteredMap.get(beanId);
		}
		//如果存在则重新注册
		dynamicBeanPropery.setBeanCreatedDate(new Date());
		dynamicBeanPropery.setPrefix(CustomerBeanPrefixConstast.KEEP_ALIVED_ALERT_PREFIX);
		dynamicBeanPropery.setBeanId(beanId);
		dynamicBeanPropery.setPropertyBean(domain);
		
		keepAlivedAlertRegisteredMap.put(beanId, dynamicBeanPropery);
	}
	
	/**
	 * 注册Oracle DataSource bean
	 */
	private void registerOracleDataSourceContext(String beanId,Object propertyBean){
		DynamicDataSourceDomain domain = (DynamicDataSourceDomain) propertyBean;
		DynamicBeanBO<DynamicDataSourceDomain> dynamicBeanPropery = new DynamicBeanBO<DynamicDataSourceDomain>();
		if(oraDataSourceRegisteredMap.containsKey(beanId)){
			dynamicBeanPropery = oraDataSourceRegisteredMap.get(beanId);
		}
		//如果存在则重新注册
		dynamicBeanPropery.setBeanCreatedDate(new Date());
		dynamicBeanPropery.setPrefix(CustomerBeanPrefixConstast.ORACLE_DS_PREFIX);
		dynamicBeanPropery.setBeanId(beanId);
		dynamicBeanPropery.setPropertyBean(domain);
		
		oraDataSourceRegisteredMap.put(beanId, dynamicBeanPropery);
	}
	
	
	/**
	 * 注册MySQL DataSource bean
	 */
	private void registerMySQLDataSourceContext(String beanId,Object propertyBean){
		DynamicDataSourceDomain domain = (DynamicDataSourceDomain) propertyBean;
		DynamicBeanBO<DynamicDataSourceDomain> dynamicBeanPropery = new DynamicBeanBO<DynamicDataSourceDomain>();
		if(mysqlDataSourceRegisteredMap.containsKey(beanId)){
			dynamicBeanPropery = mysqlDataSourceRegisteredMap.get(beanId);
		}
		//如果存在则重新注册
		dynamicBeanPropery.setBeanCreatedDate(new Date());
		dynamicBeanPropery.setPrefix(CustomerBeanPrefixConstast.MYSQL_DS_PREFIX);
		dynamicBeanPropery.setBeanId(beanId);
		dynamicBeanPropery.setPropertyBean(domain);
		
		mysqlDataSourceRegisteredMap.put(beanId, dynamicBeanPropery);
	}
	
	/**
	 * 删除自定义的Bean
	 * @param beanId
	 * @return
	 */
	public boolean removeBeanDefinition(String beanId){
		boolean isSucess = false;
		if(StringUtils.isBlank(beanId)){
			return isSucess;
		}
		
		if(beanId.startsWith(CustomerBeanPrefixConstast.HOST_PREFIX)){
			removeHostRegisteredBean(beanId);
		}else if(beanId.startsWith(CustomerBeanPrefixConstast.MYSQL_PREFIX)){
			removeMySQLRegisteredBean(beanId);
		}else if(beanId.startsWith(CustomerBeanPrefixConstast.ORACLE_PREFIX)){
			removeOracleRegisteredBean(beanId);
		}else if(beanId.startsWith(CustomerBeanPrefixConstast.KEEP_ALIVED_PREFIX)){
			removeKeepAlivedRegisteredBean(beanId);
		}else if(beanId.startsWith(CustomerBeanPrefixConstast.ORACLE_ALERT_PREFIX)){
			removeOracleAlertRegisteredBean(beanId);
		}else if(beanId.startsWith(CustomerBeanPrefixConstast.MYSQL_ALERT_PREFIX)){
			removeMySQLAlertRegisteredBean(beanId);
		}else if(beanId.startsWith(CustomerBeanPrefixConstast.KEEP_ALIVED_ALERT_PREFIX)){
			removeKeepAlivedAlertRegisteredBean(beanId);
		}else if(beanId.startsWith(CustomerBeanPrefixConstast.MYSQL_DS_PREFIX)){
			removeMySQLDataSourceRegisteredBean(beanId);
		}else if(beanId.startsWith(CustomerBeanPrefixConstast.ORACLE_DS_PREFIX)){
			removeOracleDataSourceRegisteredBean(beanId);
		}
		
		isSucess = true;
		return isSucess;
	}
	
	
	/**
	 * 删除Oracle注册Bean
	 * @param beanId
	 */
	private void removeOracleRegisteredBean(String beanId){
		if(oracleRegisteredMap == null){
			return ;
		}
		if(oracleRegisteredMap.containsKey(beanId)){
			oracleRegisteredMap.remove(beanId);
		}
	}
	
	/**
	 * 删除KeepAlived注册Bean
	 * @param beanId
	 */
	private void removeKeepAlivedRegisteredBean(String beanId){
		if(keepAlivedRegisteredMap == null){
			return ;
		}
		if(keepAlivedRegisteredMap.containsKey(beanId)){
			keepAlivedRegisteredMap.remove(beanId);
		}
	}
	
	/**
	 * 删除MySQL注册Bean
	 * @param beanId
	 */
	private void removeMySQLRegisteredBean(String beanId){
		if(mysqlRegisteredMap == null){
			return ;
		}
		if(mysqlRegisteredMap.containsKey(beanId)){
			mysqlRegisteredMap.remove(beanId);
		}
	}
	
	/**
	 * 删除Host注册Bean
	 * @param beanId
	 */
	private void removeHostRegisteredBean(String beanId){
		if(hostRegisteredMap == null){
			return ;
		}
		if(hostRegisteredMap.containsKey(beanId)){
			hostRegisteredMap.remove(beanId);
		}
	}
	
	
	/**
	 * 删除oracle alert注册Bean
	 * @param beanId
	 */
	private void removeOracleAlertRegisteredBean(String beanId){
		if(oraAlertRegisteredMap == null){
			return ;
		}
		if(oraAlertRegisteredMap.containsKey(beanId)){
			oraAlertRegisteredMap.remove(beanId);
		}
	}
	
	/**
	 * 删除MySQL alert注册Bean
	 * @param beanId
	 */
	private void removeMySQLAlertRegisteredBean(String beanId){
		if(mysqlAlertRegisteredMap == null){
			return ;
		}
		if(mysqlAlertRegisteredMap.containsKey(beanId)){
			mysqlAlertRegisteredMap.remove(beanId);
		}
	}
	
	/**
	 * 删除KeepAlived alert注册Bean
	 * @param beanId
	 */
	private void removeKeepAlivedAlertRegisteredBean(String beanId){
		if(keepAlivedAlertRegisteredMap == null){
			return ;
		}
		if(keepAlivedAlertRegisteredMap.containsKey(beanId)){
			keepAlivedAlertRegisteredMap.remove(beanId);
		}
	}
	
	
	/**
	 * 删除MySQL DataSource 注册Bean
	 * @param beanId
	 */
	private void removeOracleDataSourceRegisteredBean(String beanId){
		if(oraDataSourceRegisteredMap == null){
			return ;
		}
		if(oraDataSourceRegisteredMap.containsKey(beanId)){
			oraDataSourceRegisteredMap.remove(beanId);
		}
	}
	
	/**
	 * 删除MySQL DataSource 注册Bean
	 * @param beanId
	 */
	private void removeMySQLDataSourceRegisteredBean(String beanId){
		if(mysqlDataSourceRegisteredMap == null){
			return ;
		}
		if(mysqlDataSourceRegisteredMap.containsKey(beanId)){
			mysqlDataSourceRegisteredMap.remove(beanId);
		}
	}
	

	@SuppressWarnings("rawtypes")
	public DynamicBeanBO getBeanProperty(String beanId){
		if(StringUtils.isBlank(beanId)){
			return null;
		}
		
		if(beanId.startsWith(CustomerBeanPrefixConstast.HOST_PREFIX)){
			return hostRegisteredMap.get(beanId);
		}else if(beanId.startsWith(CustomerBeanPrefixConstast.MYSQL_PREFIX)){
			return mysqlRegisteredMap.get(beanId);
		}else if(beanId.startsWith(CustomerBeanPrefixConstast.ORACLE_PREFIX)){
			return oracleRegisteredMap.get(beanId);
		}else if(beanId.startsWith(CustomerBeanPrefixConstast.KEEP_ALIVED_PREFIX)){
			return keepAlivedRegisteredMap.get(beanId);
		}else if(beanId.startsWith(CustomerBeanPrefixConstast.ORACLE_ALERT_PREFIX)){
			return oraAlertRegisteredMap.get(beanId);
		}else if(beanId.startsWith(CustomerBeanPrefixConstast.MYSQL_ALERT_PREFIX)){
			return mysqlAlertRegisteredMap.get(beanId);
		}else if(beanId.startsWith(CustomerBeanPrefixConstast.KEEP_ALIVED_ALERT_PREFIX)){
			return keepAlivedAlertRegisteredMap.get(beanId);
		}else if(beanId.startsWith(CustomerBeanPrefixConstast.ORACLE_DS_PREFIX)){
			return oraDataSourceRegisteredMap.get(beanId);
		}else if(beanId.startsWith(CustomerBeanPrefixConstast.MYSQL_DS_PREFIX)){
			return mysqlDataSourceRegisteredMap.get(beanId);
		}
		
		
		return null;
	}



}
