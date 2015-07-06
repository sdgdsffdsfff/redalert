/**   
* @Title: DynamicDataSourceRegBean.java 
* @Package com.tyyd.ywpt.tools.ds 
* @Description:  
* @author wangyu   
* @date 2014-7-2 上午11:12:28 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.init;

import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.tyyd.ywpt.biz.ds.DataSourceBizDefManager;
import com.tyyd.ywpt.tools.ds.dataobject.DynamicDataSourceDomain;
import com.tyyd.ywpt.tools.ds.impl.SingleDataSourceBeanRegUtils;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class DynamicDataSourceInjectBean extends AbstractCustomerBeanIniting implements ApplicationListener{

	public static final Logger LOGGER = Logger.getLogger(DynamicDataSourceInjectBean.class);  
	
	private SingleDataSourceBeanRegUtils singleDataSourceBeanRegUtils;
	
	private DataSourceBizDefManager dataSourceBizDefManager;
	
	private String serverId;
	
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if(event instanceof ContextRefreshedEvent){
			loadAll();
		}
	}


	/**
	 * 
	 */
	private void loadAll() {
		//启动的时候一次性加载到内存配置
		Map<String,DynamicDataSourceDomain> mysqlDefMap = dataSourceBizDefManager.getMySQLDsDef(serverId);
		Map<String,DynamicDataSourceDomain> oracleDefMap = dataSourceBizDefManager.getOracleDsDef(serverId);
		
		//MySQL数据源注册
		if(mysqlDefMap != null && !mysqlDefMap.isEmpty()){
			regDataSource(mysqlDefMap);
			LOGGER.info("注册Mysql dataSource size : "+mysqlDefMap.size());
			//System.out.println("注册Mysql dataSource size : "+mysqlDefMap.size());
		}
		
		
		//Oracle数据源注册
		if(oracleDefMap != null && !oracleDefMap.isEmpty()){
			regDataSource(oracleDefMap);
			LOGGER.info("注册oracle dataSource size : "+mysqlDefMap.size());
			//System.out.println("注册oracle dataSource size : "+mysqlDefMap.size());
		}
		
	}


	/**
	 * @param dbDefMap
	 */
	private void regDataSource(Map<String, DynamicDataSourceDomain> dbDefMap) {
		Iterator<String> iter = dbDefMap.keySet().iterator();  
        
        while (iter.hasNext()) {  
            String beanId = iter.next();
            DynamicDataSourceDomain domain = dbDefMap.get(beanId);
        	singleDataSourceBeanRegUtils.regDataSource(beanId, domain);
        	
        	 //注册到自定义的Bean管理中心
        	getCustomerBeanListFactory().beanDefinitionBuild(beanId,domain);
        }
        
        //启用数据源
        singleDataSourceBeanRegUtils.startDataSource();
        
	}

	
	/**
	 * @return the singleDataSourceBeanRegUtils
	 */
	public SingleDataSourceBeanRegUtils getSingleDataSourceBeanRegUtils() {
		return singleDataSourceBeanRegUtils;
	}


	/**
	 * @param singleDataSourceBeanRegUtils the singleDataSourceBeanRegUtils to set
	 */
	public void setSingleDataSourceBeanRegUtils(
			SingleDataSourceBeanRegUtils singleDataSourceBeanRegUtils) {
		this.singleDataSourceBeanRegUtils = singleDataSourceBeanRegUtils;
	}


	/**
	 * @return the dataSourceBizDefManager
	 */
	public DataSourceBizDefManager getDataSourceBizDefManager() {
		return dataSourceBizDefManager;
	}


	/**
	 * @param dataSourceBizDefManager the dataSourceBizDefManager to set
	 */
	public void setDataSourceBizDefManager(
			DataSourceBizDefManager dataSourceBizDefManager) {
		this.dataSourceBizDefManager = dataSourceBizDefManager;
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
	
	
	
}
