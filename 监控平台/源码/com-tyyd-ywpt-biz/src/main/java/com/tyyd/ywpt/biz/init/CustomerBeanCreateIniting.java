/**   
* @Title: CustomerBeanCreateIniting.java 
* @Package com.tyyd.ywpt.biz.init 
* @Description:  
* @author wangyu   
* @date 2014-7-8 下午2:48:38 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import com.tyyd.ywpt.biz.bean.DynamicHostBeanRegisterBinding;
import com.tyyd.ywpt.biz.bean.dataobject.CustomerBeanPrefixConstast;
import com.tyyd.ywpt.biz.dict.LogTypeEnum;
import com.tyyd.ywpt.biz.dict.SysTypeEnum;
import com.tyyd.ywpt.biz.ssh.invoke.ServerSshImpl;
import com.tyyd.ywpt.biz.ssh.invoke.keepalived.impl.KeepAlivedSshImpl;
import com.tyyd.ywpt.biz.ssh.invoke.mysql.impl.MySQLSshImpl;
import com.tyyd.ywpt.biz.ssh.invoke.oracle.impl.OracleSshImpl;
import com.tyyd.ywpt.dao.configration.db.DbConfigDAO;
import com.tyyd.ywpt.dao.configration.db.dataobject.DbConfigDomain;
import com.tyyd.ywpt.dao.configration.host.HostConfigDAO;
import com.tyyd.ywpt.dao.configration.host.dataobject.HostConfigDomain;
import com.tyyd.ywpt.dao.configration.hostdiralert.HostDirAlertConfigDAO;
import com.tyyd.ywpt.dao.configration.hostdiralert.dataobject.HostDirAlertConfigDomain;

/**
 * @author wangyu
 *
 */
public class CustomerBeanCreateIniting implements InitializingBean {

	public static final Logger LOGGER = Logger.getLogger(CustomerBeanCreateIniting.class);  
	
	private static final Integer DEFAULT_SSH_PORT = 22;
	private String serverId;
	
	private DbConfigDAO dbConfigDAO;
	
	private HostConfigDAO hostConfigDAO;
	
	private HostDirAlertConfigDAO hostDirAlertConfigDAO;
	
	
	private DynamicHostBeanRegisterBinding dynamicHostBeanRegisterBinding;
	
	@Override
	public void afterPropertiesSet() throws Exception {

		//注册Bean
		registerHostBean();
		registerOracleBean();
		registerMySQLBean();
		registerKeepAlivedBean();
		
		
		//注册Oracle告警
		registerOracleAlertBean();
		registerMySQLAlertBean();
		registerKeepAlivedAlertBean();
	}

	
	/**
	 * 注册主机的Bean
	 */
	private void registerHostBean(){
		List<HostConfigDomain> hostList = hostConfigDAO.listHostConfig(serverId);
		if(hostList == null){
			return ;
		}
		
		for(HostConfigDomain domain : hostList){
			Map<String,Object> propertyMap = new HashMap<String,Object>();
			propertyMap.put("connectIpAddr", domain.getIpAddr());
			propertyMap.put("connectUserName", domain.getUserAccount());
			propertyMap.put("connectPassword", domain.getUserPasswd());
			propertyMap.put("connectPort", DEFAULT_SSH_PORT);
			boolean isSucess = dynamicHostBeanRegisterBinding.regSimpleBean(CustomerBeanPrefixConstast.HOST_PREFIX+domain.getHostId(), ServerSshImpl.class, domain, propertyMap);
			if(!isSucess){
				LOGGER.error("注册server bean 失败, 参数:"+propertyMap);
			}else{
				LOGGER.info("成功注册server bean , beanId="+CustomerBeanPrefixConstast.HOST_PREFIX+domain.getHostId());
			}
		}
		LOGGER.info("注册server bean size : "+hostList.size());
		//System.out.println("注册server bean size : "+hostList.size());
	}
	
	
	/**
	 * 注册oracle Bean
	 */
	private void registerOracleBean(){
		List<DbConfigDomain> dblist = dbConfigDAO.listDbConfigByServerId(serverId, SysTypeEnum.Oracle.getVal().toString());
		if(dblist == null){
			return ;
		}
		for(DbConfigDomain domain : dblist){
			Map<String,Object> propertyMap = new HashMap<String,Object>();
			propertyMap.put("connectIpAddr", domain.getIpAddr());
			propertyMap.put("connectUserName", domain.getHostUserName());
			propertyMap.put("connectPassword", domain.getHostUserPasswd());
			propertyMap.put("connectPort", DEFAULT_SSH_PORT);
			boolean isSucess = dynamicHostBeanRegisterBinding.regSimpleBean(CustomerBeanPrefixConstast.ORACLE_PREFIX+domain.getDbId(), OracleSshImpl.class, domain, propertyMap);
			if(!isSucess){
				LOGGER.error("注册oracle bean 失败, 参数:"+propertyMap);
			}else{
				LOGGER.info("成功注册oracle bean , beanId="+CustomerBeanPrefixConstast.ORACLE_PREFIX+domain.getDbId());
			}
		}
		
		LOGGER.info("注册oracle bean size : "+dblist.size());
		//System.out.println("注册oracle bean size : "+dblist.size());
	}
	
	
	/**
	 * 注册mysql的Bean
	 */
	private void registerMySQLBean(){
		List<DbConfigDomain> dblist = dbConfigDAO.listDbConfigByServerId(serverId, SysTypeEnum.MySQL.getVal().toString());
		if(dblist == null){
			return ;
		}
		for(DbConfigDomain domain : dblist){
			Map<String,Object> propertyMap = new HashMap<String,Object>();
			propertyMap.put("connectIpAddr", domain.getIpAddr());
			propertyMap.put("connectUserName", domain.getHostUserName());
			propertyMap.put("connectPassword", domain.getHostUserPasswd());
			propertyMap.put("connectPort", DEFAULT_SSH_PORT);
			boolean isSucess = dynamicHostBeanRegisterBinding.regSimpleBean(CustomerBeanPrefixConstast.MYSQL_PREFIX+domain.getDbId(), MySQLSshImpl.class, domain, propertyMap);
			if(!isSucess){
				LOGGER.error("注册mysql bean 失败, 参数:"+propertyMap);
			}else{
				LOGGER.info("成功注册mysql bean , beanId="+CustomerBeanPrefixConstast.MYSQL_PREFIX+domain.getDbId());
			}
		}
		
		LOGGER.info("注册mysql bean size : "+dblist.size());
		//System.out.println("注册mysql bean size : "+dblist.size());
	}

	
	
	/**
	 * 注册KeepAlived Bean
	 */
	private void registerKeepAlivedBean(){
		List<DbConfigDomain> dblist = dbConfigDAO.listDbConfigByServerId(serverId, SysTypeEnum.KEEPALIVED.getVal().toString());
		if(dblist == null){
			return ;
		}
		for(DbConfigDomain domain : dblist){
			Map<String,Object> propertyMap = new HashMap<String,Object>();
			propertyMap.put("connectIpAddr", domain.getIpAddr());
			propertyMap.put("connectUserName", domain.getHostUserName());
			propertyMap.put("connectPassword", domain.getHostUserPasswd());
			propertyMap.put("connectPort", DEFAULT_SSH_PORT);
			boolean isSucess = dynamicHostBeanRegisterBinding.regSimpleBean(CustomerBeanPrefixConstast.KEEP_ALIVED_PREFIX+domain.getDbId(), KeepAlivedSshImpl.class, domain, propertyMap);
			if(!isSucess){
				LOGGER.error("注册keepAlived bean 失败, 参数:"+propertyMap);
			}else{
				LOGGER.info("成功注册keepAlived bean , beanId="+CustomerBeanPrefixConstast.KEEP_ALIVED_PREFIX+domain.getDbId());
			}
		}
		
		LOGGER.info("注册keepAlived bean size : "+dblist.size());
	}

	/**
	 * 注册oracle alert 的Bean
	 */
	private void registerOracleAlertBean(){
		List<HostDirAlertConfigDomain> oracleAlertList = hostDirAlertConfigDAO.listDbAlertConfig(SysTypeEnum.Oracle.getVal(), LogTypeEnum.Oracle_Alert.getVal(), serverId);
		if(oracleAlertList == null){
			return ;
		}
		for(HostDirAlertConfigDomain domain : oracleAlertList){
			Map<String,Object> propertyMap = new HashMap<String,Object>();
			propertyMap.put("connectIpAddr", domain.getIpAddr());
			propertyMap.put("connectUserName", domain.getHostUserName());
			propertyMap.put("connectPassword", domain.getHostUserPasswd());
			propertyMap.put("connectPort", DEFAULT_SSH_PORT);
			boolean isSucess = dynamicHostBeanRegisterBinding.regSimpleBean(CustomerBeanPrefixConstast.ORACLE_ALERT_PREFIX+domain.getId(), OracleSshImpl.class, domain, propertyMap);
			if(!isSucess){
				LOGGER.error("注册oracle alert bean 失败, 参数:"+propertyMap);
			}else{
				LOGGER.info("成功注册oracle alert bean , beanId="+CustomerBeanPrefixConstast.ORACLE_ALERT_PREFIX+domain.getMonitorId());
			}
		}
		
		LOGGER.info("注册oracle alert bean size : "+oracleAlertList.size());
		//System.out.println("注册oracle alert bean size : "+oracleAlertList.size());
	}
	
	
	
	/**
	 * 注册MySQL alert 的Bean
	 */
	private void registerMySQLAlertBean(){
		List<HostDirAlertConfigDomain> mysqlAlertList = hostDirAlertConfigDAO.listDbAlertConfig(SysTypeEnum.MySQL.getVal(), LogTypeEnum.MYSQL_ALERT.getVal(), serverId);
		if(mysqlAlertList == null){
			return ;
		}
		for(HostDirAlertConfigDomain domain : mysqlAlertList){
			Map<String,Object> propertyMap = new HashMap<String,Object>();
			propertyMap.put("connectIpAddr", domain.getIpAddr());
			propertyMap.put("connectUserName", domain.getHostUserName());
			propertyMap.put("connectPassword", domain.getHostUserPasswd());
			propertyMap.put("connectPort", DEFAULT_SSH_PORT);
			boolean isSucess = dynamicHostBeanRegisterBinding.regSimpleBean(CustomerBeanPrefixConstast.MYSQL_ALERT_PREFIX+domain.getId(), MySQLSshImpl.class, domain, propertyMap);
			if(!isSucess){
				LOGGER.error("注册mysql alert bean 失败, 参数:"+propertyMap);
			}else{
				LOGGER.info("成功注册mysql alert bean , beanId="+CustomerBeanPrefixConstast.MYSQL_ALERT_PREFIX+domain.getMonitorId());
			}
		}
		
		LOGGER.info("注册mysql alert bean size : "+mysqlAlertList.size());
		//System.out.println("注册mysql alert bean size : "+mysqlAlertList.size());
	}
	
	/**
	 * KeepAlived日志告警
	 */
	private void registerKeepAlivedAlertBean(){
		List<HostDirAlertConfigDomain> mysqlAlertList = hostDirAlertConfigDAO.listDbAlertConfig(SysTypeEnum.KEEPALIVED.getVal(), LogTypeEnum.KEEP_ALIVED_ALERT.getVal(), serverId);
		if(mysqlAlertList == null){
			return ;
		}
		for(HostDirAlertConfigDomain domain : mysqlAlertList){
			Map<String,Object> propertyMap = new HashMap<String,Object>();
			propertyMap.put("connectIpAddr", domain.getIpAddr());
			propertyMap.put("connectUserName", domain.getHostUserName());
			propertyMap.put("connectPassword", domain.getHostUserPasswd());
			propertyMap.put("connectPort", DEFAULT_SSH_PORT);
			boolean isSucess = dynamicHostBeanRegisterBinding.regSimpleBean(CustomerBeanPrefixConstast.KEEP_ALIVED_ALERT_PREFIX+domain.getId(), KeepAlivedSshImpl.class, domain, propertyMap);
			if(!isSucess){
				LOGGER.error("注册KeepAlived alert bean 失败, 参数:"+propertyMap);
			}else{
				LOGGER.info("成功注册KeepAlived alert bean , beanId="+CustomerBeanPrefixConstast.KEEP_ALIVED_ALERT_PREFIX+domain.getMonitorId());
			}
		}
		
		LOGGER.info("注册KeepAlived alert bean size : "+mysqlAlertList.size());
		//System.out.println("注册mysql alert bean size : "+mysqlAlertList.size());
	}
	
	/**
	 * @return the serverId
	 */
	public String getServerId() {
		return serverId;
	}


	/**
	 * @param serverId the serverId to set
	 */
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}


	/**
	 * @return the dbConfigDAO
	 */
	public DbConfigDAO getDbConfigDAO() {
		return dbConfigDAO;
	}


	/**
	 * @param dbConfigDAO the dbConfigDAO to set
	 */
	public void setDbConfigDAO(DbConfigDAO dbConfigDAO) {
		this.dbConfigDAO = dbConfigDAO;
	}


	/**
	 * @return the hostConfigDAO
	 */
	public HostConfigDAO getHostConfigDAO() {
		return hostConfigDAO;
	}


	/**
	 * @param hostConfigDAO the hostConfigDAO to set
	 */
	public void setHostConfigDAO(HostConfigDAO hostConfigDAO) {
		this.hostConfigDAO = hostConfigDAO;
	}


	/**
	 * @return the dynamicHostBeanRegisterBinding
	 */
	public DynamicHostBeanRegisterBinding getDynamicHostBeanRegisterBinding() {
		return dynamicHostBeanRegisterBinding;
	}


	/**
	 * @param dynamicHostBeanRegisterBinding the dynamicHostBeanRegisterBinding to set
	 */
	public void setDynamicHostBeanRegisterBinding(
			DynamicHostBeanRegisterBinding dynamicHostBeanRegisterBinding) {
		this.dynamicHostBeanRegisterBinding = dynamicHostBeanRegisterBinding;
	}


	/**
	 * @return the hostDirAlertConfigDAO
	 */
	public HostDirAlertConfigDAO getHostDirAlertConfigDAO() {
		return hostDirAlertConfigDAO;
	}


	/**
	 * @param hostDirAlertConfigDAO the hostDirAlertConfigDAO to set
	 */
	public void setHostDirAlertConfigDAO(HostDirAlertConfigDAO hostDirAlertConfigDAO) {
		this.hostDirAlertConfigDAO = hostDirAlertConfigDAO;
	}
	
	
}
