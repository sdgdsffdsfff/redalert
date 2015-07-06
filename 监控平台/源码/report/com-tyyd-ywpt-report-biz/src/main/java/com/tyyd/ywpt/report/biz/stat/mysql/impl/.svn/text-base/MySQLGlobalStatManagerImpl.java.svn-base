/**   
* @Title: MySQLGlobalStatManagerImpl.java 
* @Package com.tyyd.ywpt.report.biz.stat.mysql.impl 
* @Description:  
* @author wangyu   
* @date 2014-12-11 下午3:11:32 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.stat.mysql.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;

import com.tyyd.ywpt.biz.util.DateUtils;
import com.tyyd.ywpt.dao.stat.mysql.MySQLGlobalStatDAO;
import com.tyyd.ywpt.dao.stat.mysql.dataobject.MySQLGlobalStatDomain;
import com.tyyd.ywpt.report.biz.stat.mysql.MySQLGlobalStatManager;
import com.tyyd.ywpt.report.biz.stat.mysql.dto.MySQLGlobalStatDTO;

/**
 * @author wangyu
 *
 */
public class MySQLGlobalStatManagerImpl implements MySQLGlobalStatManager {

	
	@Resource
	private MySQLGlobalStatDAO mySQLGlobalStatDAO;
	
	@Override
	public List<MySQLGlobalStatDTO> listMySQLGlobalStat() {
		
		List<MySQLGlobalStatDomain> list = mySQLGlobalStatDAO.listMySQLGlobalStat();
		
		List<MySQLGlobalStatDTO> result = new ArrayList<MySQLGlobalStatDTO>();
		if(CollectionUtils.isNotEmpty(list)){
			for(MySQLGlobalStatDomain domain : list){
				MySQLGlobalStatDTO dto = new MySQLGlobalStatDTO();
				try {
					BeanUtils.copyProperties(dto, domain);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				
				//转化
				dto = convertDTO(dto);
				if(dto == null){
					continue;
				}
				
				result.add(dto);
			}
		}
		
		
		return result;
	}

	/**
	 * 
	 * @param dto
	 * @return
	 */
	private MySQLGlobalStatDTO convertDTO(MySQLGlobalStatDTO dto) {
		
		if(dto == null){
			return null;
		}
		
		if(dto.getIsMaster() == 1){
			dto.setIsMasterRemark("是");
		}else{
			dto.setIsMasterRemark("否");
		}
		
		if(dto.getIsSlave() == 1){
			dto.setIsSlaveRemark("是");
		}else{
			dto.setIsSlaveRemark("否");
		}
		
		dto.setStartupTimeFormat(DateUtils.DateToString(dto.getStartupTime(), "yyyy-MM-dd HH:mm:ss"));
		
		
		return dto;
	}

}
