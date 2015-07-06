/**   
* @Title: NetsenseManagerImpl.java 
* @Package com.tyyd.ywpt.schedule.netsense.impl 
* @Description:  
* @author wangyu   
* @date 2015-1-22 下午5:08:20 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.netsense.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.tyyd.ywpt.dao.external.netsense.NetSenseDAO;
import com.tyyd.ywpt.dao.external.netsense.dataobject.NetSenseDataDomain;
import com.tyyd.ywpt.schedule.netsense.NetsenseManager;
import com.tyyd.ywpt.schedule.netsense.dataobject.NetSenseDataBO;
import com.tyyd.ywpt.schedule.netsense.dataobject.NetSenseWholeBO;
import com.tyyd.ywpt.tools.http.CommonHttpUtils;

/**
 * @author wangyu
 *
 */
public class NetsenseManagerImpl implements NetsenseManager {
	
	public static final Logger LOGGER = Logger.getLogger(NetsenseManagerImpl.class);

	private NetSenseDAO netSenseDAO;
	
	private CommonHttpUtils commonHttpUtils;
	
	private final static String address_template = "http://monitor.netsense.cn/apps/getdata/index/7/?lastId=%s&code=2dcbd425ce1d281865ebec6088125622";
	
	@Override
	public void saveNetSenseMonitorData() {
		
		//获取最新的一次ID
		Long lastId = netSenseDAO.getLastId();
		if(lastId == null){
			lastId = 0l;
		}
		
		
		//从接口中拿到通用数据
		String address = String.format(address_template, lastId.toString());
		LOGGER.info(address);
		
		String result = commonHttpUtils.getRequest(address);
		LOGGER.info(result);
		
		//json解析
		NetSenseWholeBO dataResult = JSON.parseObject(result, NetSenseWholeBO.class);
		
		//入库
		if(dataResult != null && dataResult.getData()!=null){
			
			if(StringUtils.isBlank(dataResult.getLastId()) || dataResult.getLastId().equals(lastId)){
				return;
			}
			
			List<NetSenseDataBO> dataList = dataResult.getData();
			for(NetSenseDataBO bo : dataList){
				NetSenseDataDomain dest = new NetSenseDataDomain();
				try {
					BeanUtils.copyProperties(dest, bo);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				
				netSenseDAO.addNetSenseData(dest);
				
			}
			
		}
		
		
		//保存上次的ID纪录
		if(lastId.longValue() == 0l){
			netSenseDAO.addLastId(Long.valueOf(dataResult.getLastId()));
		}else{
			netSenseDAO.updateLastId(Long.valueOf(dataResult.getLastId()), lastId);
		}
		
	}

	/**
	 * @return the netSenseDAO
	 */
	public NetSenseDAO getNetSenseDAO() {
		return netSenseDAO;
	}

	/**
	 * @param netSenseDAO the netSenseDAO to set
	 */
	public void setNetSenseDAO(NetSenseDAO netSenseDAO) {
		this.netSenseDAO = netSenseDAO;
	}

	/**
	 * @return the commonHttpUtils
	 */
	public CommonHttpUtils getCommonHttpUtils() {
		return commonHttpUtils;
	}

	/**
	 * @param commonHttpUtils the commonHttpUtils to set
	 */
	public void setCommonHttpUtils(CommonHttpUtils commonHttpUtils) {
		this.commonHttpUtils = commonHttpUtils;
	}

	
	
}
