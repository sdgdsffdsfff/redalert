/**   
* @Title: AlertDisplayDTO.java 
* @Package com.tyyd.ywpt.report.biz.alert.dto 
* @Description:  
* @author wangyu   
* @date 2014-10-27 上午10:05:42 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.alert.dto;

import java.util.Date;

/**
 * @author wangyu
 *
 */
public class AlertDisplayDTO {

	private Long id;

    /**
     * 主机ID
     */
    private String monitorId;


    /**
     * 指标ID
     */
    private String quotaId;

    /**
     * 指标名
     */
    private String quotaName;

    /**
     * 采集值
     */
    private Float quotaValue;
    
    /**
     * 单位
     */
    private String quotaUnit;

    /**
     * 系统类型 见字典（sys_type）
     */
    private Integer sysType;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;


    /**
     * 创建时间
     */
    private Date gmtCreated;
    
    private String gmtCreatedStr;

    
    /**
     * 预警级别，1：warn、2：critical
     */
    private Integer noticeLevel;
    
    
    /**
     * 分类. 1：心跳检查、2：指标、3：磁盘、4：表空间
     */
    private Integer monitorType;
    
    
    
    /**
     * IP
     */
    private String ipAddr;
    
    
    /**
     * 主机ID
     */
    private String hostId;
    
    /**
     * db_ID
     */
    private String dbId;
    
    /**
     * 别名
     */
    private String nickName;
    
    /**
     * 端口
     */
    private Integer port;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the monitorId
	 */
	public String getMonitorId() {
		return monitorId;
	}

	/**
	 * @param monitorId the monitorId to set
	 */
	public void setMonitorId(String monitorId) {
		this.monitorId = monitorId;
	}

	/**
	 * @return the quotaId
	 */
	public String getQuotaId() {
		return quotaId;
	}

	/**
	 * @param quotaId the quotaId to set
	 */
	public void setQuotaId(String quotaId) {
		this.quotaId = quotaId;
	}

	/**
	 * @return the quotaName
	 */
	public String getQuotaName() {
		return quotaName;
	}

	/**
	 * @param quotaName the quotaName to set
	 */
	public void setQuotaName(String quotaName) {
		this.quotaName = quotaName;
	}

	/**
	 * @return the quotaValue
	 */
	public Float getQuotaValue() {
		return quotaValue;
	}

	/**
	 * @param quotaValue the quotaValue to set
	 */
	public void setQuotaValue(Float quotaValue) {
		this.quotaValue = quotaValue;
	}

	/**
	 * @return the quotaUnit
	 */
	public String getQuotaUnit() {
		return quotaUnit;
	}

	/**
	 * @param quotaUnit the quotaUnit to set
	 */
	public void setQuotaUnit(String quotaUnit) {
		this.quotaUnit = quotaUnit;
	}

	/**
	 * @return the sysType
	 */
	public Integer getSysType() {
		return sysType;
	}

	/**
	 * @param sysType the sysType to set
	 */
	public void setSysType(Integer sysType) {
		this.sysType = sysType;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the gmtCreated
	 */
	public Date getGmtCreated() {
		return gmtCreated;
	}

	/**
	 * @param gmtCreated the gmtCreated to set
	 */
	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

	/**
	 * @return the noticeLevel
	 */
	public Integer getNoticeLevel() {
		return noticeLevel;
	}

	/**
	 * @param noticeLevel the noticeLevel to set
	 */
	public void setNoticeLevel(Integer noticeLevel) {
		this.noticeLevel = noticeLevel;
	}

	/**
	 * @return the monitorType
	 */
	public Integer getMonitorType() {
		return monitorType;
	}

	/**
	 * @param monitorType the monitorType to set
	 */
	public void setMonitorType(Integer monitorType) {
		this.monitorType = monitorType;
	}

	/**
	 * @return the ipAddr
	 */
	public String getIpAddr() {
		return ipAddr;
	}

	/**
	 * @param ipAddr the ipAddr to set
	 */
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	/**
	 * @return the hostId
	 */
	public String getHostId() {
		return hostId;
	}

	/**
	 * @param hostId the hostId to set
	 */
	public void setHostId(String hostId) {
		this.hostId = hostId;
	}

	/**
	 * @return the dbId
	 */
	public String getDbId() {
		return dbId;
	}

	/**
	 * @param dbId the dbId to set
	 */
	public void setDbId(String dbId) {
		this.dbId = dbId;
	}

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	 * @return the gmtCreatedStr
	 */
	public String getGmtCreatedStr() {
		return gmtCreatedStr;
	}

	/**
	 * @param gmtCreatedStr the gmtCreatedStr to set
	 */
	public void setGmtCreatedStr(String gmtCreatedStr) {
		this.gmtCreatedStr = gmtCreatedStr;
	}
    
    
}
