/**   
* @Title: OracleActiceSessionDomain.java 
* @Package com.tyyd.ywpt.dao.stat.oracle.dataobject 
* @Description:  
* @author wangyu   
* @date 2015-5-12 上午10:03:57 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.stat.oracle.dataobject.snap;

/**
 * @author wangyu
 *
 */
public class OracleSnapActiceSessionDomain {

	
	private String sqlId;
	
	/**
	 * session类型
	 */
	private String sessionState;
	
	/**
	 * 事件
	 */
	private String event;
	
	/**
	 * 次数
	 */
	private Integer counts;

	/**
	 * @return the sqlId
	 */
	public String getSqlId() {
		return sqlId;
	}

	/**
	 * @param sqlId the sqlId to set
	 */
	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}

	/**
	 * @return the sessionState
	 */
	public String getSessionState() {
		return sessionState;
	}

	/**
	 * @param sessionState the sessionState to set
	 */
	public void setSessionState(String sessionState) {
		this.sessionState = sessionState;
	}

	/**
	 * @return the event
	 */
	public String getEvent() {
		return event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(String event) {
		this.event = event;
	}

	/**
	 * @return the counts
	 */
	public Integer getCounts() {
		return counts;
	}

	/**
	 * @param counts the counts to set
	 */
	public void setCounts(Integer counts) {
		this.counts = counts;
	}
	
	
}
