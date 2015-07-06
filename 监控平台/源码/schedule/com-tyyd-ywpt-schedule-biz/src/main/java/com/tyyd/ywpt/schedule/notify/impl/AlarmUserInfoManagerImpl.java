/**   
* @Title: AlarmUserInfoManagerImpl.java 
* @Package com.tyyd.ywpt.schedule.notify.impl 
* @Description:  
* @author wangyu   
* @date 2014-8-14 上午9:04:19 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.notify.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.tyyd.ywpt.dao.admin.user.dataobject.UserDomain;
import com.tyyd.ywpt.dao.alert.recevie.AlertNotifyConfigDAO;
import com.tyyd.ywpt.dao.alert.recevie.dataobject.AlertNotifyConfigDomain;
import com.tyyd.ywpt.dao.base.dataobject.PageQuery;
import com.tyyd.ywpt.schedule.notify.AlarmUserInfoManager;
import com.tyyd.ywpt.schedule.notify.dataobject.AlarmUserDTO;

/**
 * @author wangyu
 *
 */
public class AlarmUserInfoManagerImpl implements AlarmUserInfoManager {

	@Resource
	private AlertNotifyConfigDAO alertNotifyConfigDAO; 
	
	private static ConcurrentHashMap<String,AlarmUserDTO> alarmUserKey = new ConcurrentHashMap<String,AlarmUserDTO>();
	
	
	@Override
	public void refreshAlarmUserMap() {
		if(alarmUserKey == null){
			alarmUserKey = new ConcurrentHashMap<String,AlarmUserDTO>();
		}
		alarmUserKey.clear();
		
		PageQuery<List<AlertNotifyConfigDomain>> pageQuery = new PageQuery<List<AlertNotifyConfigDomain>>();
		int indx = 1;
		while(true){
			pageQuery.setCurrentPage(indx);
			pageQuery.setPageSize(10);
			pageQuery = alertNotifyConfigDAO.listAlertNotifyMonitorPageQuery(pageQuery);
			
			for(AlertNotifyConfigDomain domain : pageQuery.getRecords()){
				String monitorId = domain.getMonitorId();
				Integer configType = domain.getConfigType();
				
				List<UserDomain> userList = alertNotifyConfigDAO.listUserInfoByMonitorConfig(monitorId, configType);
				AlarmUserDTO dto = convertToUserInfo(userList);
				dto.setConfigType(configType);
				dto.setMonitorId(monitorId);
				String key = getKey(configType,monitorId);
				alarmUserKey.put(key, dto);
			}
			
			int totalPages = pageQuery.getTotalPage();
			if(indx >= totalPages){
				break;
			}
			
			indx++;
		}
		
	}

	/**
	 * @param configType
	 * @return
	 */
	private String getKey(Integer configType,String monitorId) {
		if(configType.intValue() == alarmKey.server.getConfigType().intValue()){
			return alarmKey.server.getKey()+monitorId;
		}else if(configType.intValue() == alarmKey.oracle.getConfigType().intValue()){
			return alarmKey.oracle.getKey()+monitorId;
		}else if(configType.intValue() == alarmKey.mysql.getConfigType().intValue()){
			return alarmKey.mysql.getKey()+monitorId;
		}
		return StringUtils.EMPTY;
	}

	/**
	 * @param userList
	 */
	private AlarmUserDTO convertToUserInfo(List<UserDomain> userList) {
		AlarmUserDTO userDto = new AlarmUserDTO();
		if(!CollectionUtils.isEmpty(userList)){
			String[] mobiles = new String[userList.size()];
			String[] mails = new String[userList.size()];
			String mobileMails[] = new String[userList.size()];
			
			for(int i=0;i<userList.size();i++){
				UserDomain domain = (UserDomain)userList.get(i);
				mobiles[i] = domain.getPhone();
				mails[i] = domain.getEmail();
				mobileMails[i] = domain.getMobileMail();
			}
			
			userDto.setMails(clearBlank(mails));
			userDto.setMobiles(clearBlank(mobiles));
			userDto.setMobileMails(clearBlank(mobileMails));
		}
		return userDto;
	}

	@Override
	public AlarmUserDTO getUserInfoByMonitor(String monitorId,
			Integer configType) {
		String key = getKey(configType, monitorId);
		if(alarmUserKey!= null && alarmUserKey.containsKey(key)){
			return alarmUserKey.get(key);
		}
		return null;
	}
	
	
	private String[] clearBlank(String[] input){
		if(ArrayUtils.isEmpty(input)){
			return null;
		}
		
		List<String> list = new ArrayList<String>();
		for(String tmp : input){
			if(StringUtils.isNotBlank(tmp)){
				list.add(StringUtils.trim(tmp));
			}
		}
		
		String[] result = new String[list.size()];
		if(result.length>0){
			result = list.toArray(result);
		}
		return result;
		
	}

	

	private enum alarmKey{
		
		server(1,"alarm_server_"),
		oracle(2,"alarm_ora_"),
		mysql(3,"alarm_mysql_");
		
		private String key;
		private Integer configType;
		/**
		 * @return the key
		 */
		public String getKey() {
			return key;
		}
		/**
		 * @return the configType
		 */
		public Integer getConfigType() {
			return configType;
		}
		/**
		 * @param configType
		 * @param key
		 */
		private alarmKey(Integer configType, String key) {
			this.configType = configType;
			this.key = key;
		}
	
	}
	
	
	public static void main(String[] args) {
		AlarmUserInfoManagerImpl imp = new AlarmUserInfoManagerImpl();
		String[] result = imp.clearBlank(new String[]{"1","x2","3"," aa ","","566"});
		System.out.println(result);
	}

	
}
