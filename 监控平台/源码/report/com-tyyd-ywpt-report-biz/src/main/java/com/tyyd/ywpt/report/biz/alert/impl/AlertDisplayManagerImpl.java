/**   
* @Title: AlertDisplayManagerImpl.java 
* @Package com.tyyd.ywpt.report.biz.alert.impl 
* @Description:  
* @author wangyu   
* @date 2014-10-27 上午10:27:33 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.alert.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.tyyd.ywpt.biz.dict.SysTypeEnum;
import com.tyyd.ywpt.biz.util.DateUtils;
import com.tyyd.ywpt.dao.alert.record.AlertRecordDAO;
import com.tyyd.ywpt.dao.alert.record.dataobject.AlertRecordDomain;
import com.tyyd.ywpt.report.biz.alert.AlertDisplayManager;
import com.tyyd.ywpt.report.biz.alert.dto.AlertDisplayDTO;

/**
 * @author wangyu
 *
 */
public class AlertDisplayManagerImpl implements AlertDisplayManager{

	private final static Integer LIMIT_PAGE_SIZE = 15;
	
	@Resource
	private AlertRecordDAO alertRecordDAO;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AlertDisplayDTO> listLookingAllAlert(Integer pageSize) {
		pageSize = checkPageSize(pageSize);
		//先判断日期
		String gmtCreated = alertRecordDAO.getLimitGmtCreatedDate(pageSize);
		
		if(StringUtils.isBlank(gmtCreated)){
			return Collections.EMPTY_LIST;
		}
		
		List<AlertRecordDomain> list = alertRecordDAO.listAlertRecordByAllLooking(gmtCreated, pageSize);
		List<AlertDisplayDTO> result = toConvertDTO(list);
		return result;
	}

	/**
	 * @param list
	 * @return
	 */
	private List<AlertDisplayDTO> toConvertDTO(List<AlertRecordDomain> list) {
		List<AlertDisplayDTO> result = new ArrayList<AlertDisplayDTO>();
		if(CollectionUtils.isNotEmpty(list)){
			for(AlertRecordDomain domain : list){
				AlertDisplayDTO dto = new AlertDisplayDTO();
				try {
					BeanUtils.copyProperties(dto, domain);
					String gmtCreated = DateUtils.DateToString(domain.getGmtCreated(), "yyyy-MM-dd HH:mm:ss");
					dto.setGmtCreatedStr(gmtCreated);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				result.add(dto);
			}
		}
		
		return result;
	}

	@Override
	public List<AlertDisplayDTO> listHostAlert(String hostId, Integer pageSize,String startDate,String endDate) {
		pageSize = checkPageSize(pageSize);
		List<AlertRecordDomain> list = alertRecordDAO.listAlertRecordByHost(hostId, pageSize,startDate,endDate);
		
		List<AlertDisplayDTO> result = toConvertDTO(list);
		return result;
	}

	/**
	 * @param pageSize
	 * @return
	 */
	private Integer checkPageSize(Integer pageSize) {
		if(pageSize == null || pageSize == 0){
			pageSize = LIMIT_PAGE_SIZE;
		}
		
		return pageSize;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AlertDisplayDTO> listDbAlert(String dbId, Integer dbType,
			Integer pageSize,String startDate,String endDate) {
		
		pageSize = checkPageSize(pageSize);
		if(dbType == null 
				|| ( dbType.intValue() != SysTypeEnum.Oracle.getVal().intValue() 
						&& dbType.intValue() != SysTypeEnum.MySQL.getVal().intValue() )){
			return Collections.EMPTY_LIST;
		}
		
		List<AlertRecordDomain> list = alertRecordDAO.listAlertRecordByDb(dbId, dbType, pageSize,startDate,endDate);
		List<AlertDisplayDTO> result = toConvertDTO(list);
		return result;
	}

}
