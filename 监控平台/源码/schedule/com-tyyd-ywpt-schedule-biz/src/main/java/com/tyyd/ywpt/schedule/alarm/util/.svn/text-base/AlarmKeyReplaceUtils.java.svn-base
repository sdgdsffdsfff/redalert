/**   
* @Title: AlarmKeyReplaceUtils.java 
* @Package com.tyyd.ywpt.schedule.alarm.util 
* @Description:  
* @author wangyu   
* @date 2014-8-12 下午3:55:21 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.alarm.util;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.tyyd.ywpt.biz.util.DateUtils;


/**
 * @author wangyu
 *
 */
public class AlarmKeyReplaceUtils {
	
	private String ipAddr;		//IP地址
	private String hostName;	//主机名
	private String dbName;		//库名
	private Integer dbPort;		//端口
	private String errorMsg;	//日志内容
	private String quotaId;		//指标ID
	private String quotaName;	//指标名
	private Float thresholdValue;	//预警值
	private String thresholdValueUnitName;	//预警单位
	private String tbSpaceName;	//表空间名
	private String diskName; 	//磁盘名
	private Date gmtCreated; //创建时间
	private Long waringQuotaValue; //预警阈值
	private Float maxTableSpace; //最大的表空间大小
	private String alertType; //告警类型
	
	
	/**
	 * 适合磁盘预警
	 * @param ipAddr
	 * @param hostName
	 * @param thresholdValue
	 * @param thresholdValueUnitName
	 * @param diskName
	 */
	public AlarmKeyReplaceUtils(String ipAddr, String hostName,
			Float thresholdValue, String thresholdValueUnitName, String diskName,Date gmtCreated,Long waringQuotaValue) {
		this.ipAddr = ipAddr;
		this.hostName = hostName;
		this.thresholdValue = thresholdValue;
		this.thresholdValueUnitName = thresholdValueUnitName;
		this.diskName = diskName;
		this.gmtCreated = gmtCreated;
		this.waringQuotaValue = waringQuotaValue;
	}


	/**
	 * 适合表空间预警
	 * @param ipAddr
	 * @param hostName
	 * @param dbName
	 * @param dbPort
	 * @param thresholdValue
	 * @param thresholdValueUnitName
	 * @param tbSpaceName
	 */
	public AlarmKeyReplaceUtils(String ipAddr, String hostName, String dbName,
			Integer dbPort, Float thresholdValue, String thresholdValueUnitName,
			String tbSpaceName,Date gmtCreated,Long waringQuotaValue,Float maxTableSpace) {
		this.ipAddr = ipAddr;
		this.hostName = hostName;
		this.dbName = dbName;
		this.dbPort = dbPort;
		this.thresholdValue = thresholdValue;
		this.thresholdValueUnitName = thresholdValueUnitName;
		this.tbSpaceName = tbSpaceName;
		this.gmtCreated = gmtCreated;
		this.waringQuotaValue = waringQuotaValue;
		this.maxTableSpace = maxTableSpace;
	}


	/**
	 * 适合指标预警
	 * @param ipAddr
	 * @param hostName
	 * @param dbName
	 * @param dbPort
	 * @param quotaId
	 * @param quotaName
	 * @param quotaValue
	 * @param unitName
	 */
	public AlarmKeyReplaceUtils(String ipAddr, String hostName, String dbName,
			Integer dbPort, String quotaId, String quotaName, Float thresholdValue,
			String thresholdValueUnitName,Date gmtCreated,Long waringQuotaValue) {
		this.ipAddr = ipAddr;
		this.hostName = hostName;
		this.dbName = dbName;
		this.dbPort = dbPort;
		this.quotaId = quotaId;
		this.quotaName = quotaName;
		this.thresholdValue = thresholdValue;
		this.thresholdValueUnitName = thresholdValueUnitName;
		this.gmtCreated = gmtCreated;
		this.waringQuotaValue = waringQuotaValue;
	}


	/**
	 * 适合告警日志
	 * @param ipAddr
	 * @param hostName
	 * @param dbName
	 * @param dbPort
	 * @param errorMsg
	 */
	public AlarmKeyReplaceUtils(String ipAddr, String hostName, String dbName,
			Integer dbPort, String errorMsg,Date gmtCreated,String alertType) {
		this.ipAddr = ipAddr;
		this.hostName = hostName;
		this.dbName = dbName;
		this.dbPort = dbPort;
		this.errorMsg = errorMsg;
		this.gmtCreated = gmtCreated;
		this.alertType = alertType;
	}


	/**
	 * 适合数据库信息
	 * @param ipAddr
	 * @param hostName
	 * @param dbName
	 * @param dbPort
	 */
	public AlarmKeyReplaceUtils(String ipAddr, String hostName, String dbName,
			Integer dbPort,Date gmtCreated) {
		this.ipAddr = ipAddr;
		this.hostName = hostName;
		this.dbName = dbName;
		this.dbPort = dbPort;
		this.gmtCreated = gmtCreated;
	}


	/**
	 * 适合主机信息
	 * @param ipAddr
	 * @param hostName
	 */
	public AlarmKeyReplaceUtils(String ipAddr, String hostName,Date gmtCreated) {
		this.ipAddr = ipAddr;
		this.hostName = hostName;
		this.gmtCreated = gmtCreated;
	}

	private final static String[] key_patten = new String[]{
				"%{hostName}",
				"%{dbName}",
				"%{hostInfo}",
				"%{dbInfo}",
				"%{errorMsg}",
				"%{quotaInfo}",
				"%{thresholdValue}",
				"%{tbspaceName}",
				"%{diskName}",
				"%{waringQuotaValue}",
				"%{maxTableSpace}",
				"%{alertType}"
	};
	
	/**
	 * 
	 * @param pattenText
	 * @return
	 */
	public String formatMsgText(String pattenText){
		for(int i=0;i<key_patten.length;i++){
			int indx = pattenText.indexOf(key_patten[i]);
			if(indx != -1){
				String key = key_patten[i];
				String targetText = getTarget(key);
				pattenText = doReplace(key,pattenText, targetText); 
			}
		}
		
		return pattenText;
	}
	
	
	
	/**
	 * 替换
	 * @param key
	 * @param orgText
	 * @param targetText
	 * @return
	 */
	private String doReplace(String key,String orgText,String targetText){
		int indx = orgText.indexOf(key);
		while(indx != -1){
			orgText = orgText.replace(key, targetText); 
			indx = orgText.indexOf(key);
		}
		return orgText;
	}
	
	
	/**
	 * 转换 
	 * @param key
	 * @return
	 */
	private String getTarget(String key){
		if(StringUtils.isBlank(key)){
			return StringUtils.EMPTY;
		}
		if(key.equalsIgnoreCase("%{hostName}")){
			return getHostName();
		}if(key.equalsIgnoreCase("%{dbName}")){
			return getDbName();
		}if(key.equalsIgnoreCase("%{hostInfo}")){
			return getHostInfo();
		}else if(key.equalsIgnoreCase("%{dbInfo}")){
			return getDbInfo(); 
		}else if(key.equalsIgnoreCase("%{errorMsg}")){
			return getErrorMsg(); 
		}else if(key.equalsIgnoreCase("%{quotaInfo}")){
			return getQuotaInfo(); 
		}else if(key.equalsIgnoreCase("%{thresholdValue}")){
			return getThresholdValue(); 
		}else if(key.equalsIgnoreCase("%{tbspaceName}")){
			return getTbSpaceName(); 
		}else if(key.equalsIgnoreCase("%{diskName}")){
			return getDiskName(); 
		}else if(key.equalsIgnoreCase("%{waringQuotaValue}")){
			return getWaringQuotaValue(); 
		}else if(key.equalsIgnoreCase("%{maxTableSpace}")){
			return getMaxTableSpace(); 
		}else if(key.equalsIgnoreCase("%{alertType}")){
			return getAlertType();
		}
		
		
		return StringUtils.EMPTY;
	}
	
	/**
	 * 主机信息
	 * @return
	 */
	private String getHostInfo(){
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(ipAddr);
		sb.append("]");
		sb.append(" ");
		sb.append(getTriggerTime(gmtCreated));
		sb.append(" ");
		return sb.toString();
	}  
	
	
	/**
	 * 主机名
	 * @return
	 */
	private String getHostName(){
		return hostName;
	}
	
	
	/**
	 * 数据库名
	 * @return
	 */
	private String getDbName(){
		return dbName;
	}
	
	/**
	 * 数据库信息
	 * @return
	 */
	private String getDbInfo(){
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(ipAddr);
		sb.append(":");
		sb.append(dbPort);
		sb.append("]");
		sb.append(" ");
		sb.append(getTriggerTime(gmtCreated));
		sb.append(" ");
		return sb.toString();
	}
	
	/**
	 * 告警日志
	 * @param errorMsg
	 * @return
	 */
	private String getErrorMsg(){
		return errorMsg;
	}
	
	
	/**
	 * 告警类型
	 * @return
	 */
	private String getAlertType(){
		return alertType;
	}
	
	/**
	 * 获取指标信息
	 * @param quotaId
	 * @param quotaName
	 * @return
	 */
	private String getQuotaInfo(){
		StringBuffer sb = new StringBuffer();
		sb.append("指标ID:");
		sb.append(quotaId);
		sb.append(",");
		sb.append("指标名:");
		sb.append(quotaName);
		return sb.toString();
	}
	
	/**
	 * 阈值
	 * @param thresholdValue
	 * @param thresholdValueUnitName
	 * @return
	 */
	private String getThresholdValue(){
		StringBuffer sb = new StringBuffer();
		sb.append(thresholdValue);
		sb.append(thresholdValueUnitName);
		return sb.toString();
	}
	
	
	/**
	 * 表空间名
	 * @param tbSpaceName
	 * @return
	 */
	private String getTbSpaceName(){
		return tbSpaceName;
	}
	
	
	/**
	 * 磁盘名
	 * @param diskName
	 * @return
	 */
	private String getDiskName(){
		return diskName;
	}
	
	/**
	 * 获取触发时间
	 * @param gmtCreated
	 * @return
	 */
	private String getTriggerTime(Date gmtCreated) {
		String triggerTime = "";
		if(gmtCreated != null){
			triggerTime = DateUtils.DateToString(gmtCreated, "yyyy-MM-dd HH:mm:ss");
		}
		return triggerTime;
	}
	
	
	/**
	 * 阈值
	 * @return
	 */
	private String getWaringQuotaValue(){
		StringBuffer sb = new StringBuffer();
		sb.append(waringQuotaValue);
		sb.append(thresholdValueUnitName);
		return sb.toString();
	}
	
	
	/**
	 * 表空间最大多少
	 * @return
	 */
	private String getMaxTableSpace(){
		StringBuffer sb = new StringBuffer();
		sb.append(maxTableSpace);
		return sb.toString();
	}
}
