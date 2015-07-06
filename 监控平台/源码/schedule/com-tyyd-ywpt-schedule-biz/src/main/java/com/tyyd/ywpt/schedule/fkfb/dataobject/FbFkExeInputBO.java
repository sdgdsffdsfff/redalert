/**   
* @Title: FbFkExeInputBO.java 
* @Package com.tyyd.ywpt.schedule.fkfb.dataobject 
* @Description:  
* @author wangyu   
* @date 2015-4-17 下午5:51:29 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.fkfb.dataobject;

/**
 * @author wangyu
 *
 */
public class FbFkExeInputBO {

	private String account;
	private String passwd;
	private Integer port;
	private String ipAddr;
	
	private Integer ddlType;
	private String schemaPrefix;
	private Integer schemaCurrentIndex;
	private String tablePrefix;
	private Integer tableCurrentIndex;
	private String ddlScript;
	
	private Long taskId;
	private String dbId;
	private Integer execType;
	
	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * @return the passwd
	 */
	public String getPasswd() {
		return passwd;
	}
	/**
	 * @param passwd the passwd to set
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
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
	 * @return the ddlType
	 */
	public Integer getDdlType() {
		return ddlType;
	}
	/**
	 * @param ddlType the ddlType to set
	 */
	public void setDdlType(Integer ddlType) {
		this.ddlType = ddlType;
	}
	/**
	 * @return the schemaPrefix
	 */
	public String getSchemaPrefix() {
		return schemaPrefix;
	}
	/**
	 * @param schemaPrefix the schemaPrefix to set
	 */
	public void setSchemaPrefix(String schemaPrefix) {
		this.schemaPrefix = schemaPrefix;
	}
	/**
	 * @return the schemaCurrentIndex
	 */
	public Integer getSchemaCurrentIndex() {
		return schemaCurrentIndex;
	}
	/**
	 * @param schemaCurrentIndex the schemaCurrentIndex to set
	 */
	public void setSchemaCurrentIndex(Integer schemaCurrentIndex) {
		this.schemaCurrentIndex = schemaCurrentIndex;
	}
	/**
	 * @return the tablePrefix
	 */
	public String getTablePrefix() {
		return tablePrefix;
	}
	/**
	 * @param tablePrefix the tablePrefix to set
	 */
	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}
	/**
	 * @return the tableCurrentIndex
	 */
	public Integer getTableCurrentIndex() {
		return tableCurrentIndex;
	}
	/**
	 * @param tableCurrentIndex the tableCurrentIndex to set
	 */
	public void setTableCurrentIndex(Integer tableCurrentIndex) {
		this.tableCurrentIndex = tableCurrentIndex;
	}
	/**
	 * @return the ddlScript
	 */
	public String getDdlScript() {
		return ddlScript;
	}
	/**
	 * @param ddlScript the ddlScript to set
	 */
	public void setDdlScript(String ddlScript) {
		this.ddlScript = ddlScript;
	}
	/**
	 * @return the taskId
	 */
	public Long getTaskId() {
		return taskId;
	}
	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
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
	 * @return the execType
	 */
	public Integer getExecType() {
		return execType;
	}
	/**
	 * @param execType the execType to set
	 */
	public void setExecType(Integer execType) {
		this.execType = execType;
	}
	
	
	
	
	
}
