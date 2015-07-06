/**   
* @Title: OracleSnapEventDetailDomain.java 
* @Package com.tyyd.ywpt.dao.stat.oracle.dataobject.snap 
* @Description:  
* @author wangyu   
* @date 2015-5-12 上午10:12:45 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.stat.oracle.dataobject.snap;

/**
 * @author wangyu
 *
 */
public class OracleSnapEventClassDetailDomain {

	/**
	 * 事件名
	 */
	private String eventName;
	
	 
	/**
	 * 等待次数
	 */
	private Integer waits;
	
	
	/**
	 * 超时的次数
	 */
	private Integer timeouts;
	
	/**
	 * 等待时间
	 */
	private Float waitTime;

	/**
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * @param eventName the eventName to set
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	/**
	 * @return the waits
	 */
	public Integer getWaits() {
		return waits;
	}

	/**
	 * @param waits the waits to set
	 */
	public void setWaits(Integer waits) {
		this.waits = waits;
	}

	/**
	 * @return the timeouts
	 */
	public Integer getTimeouts() {
		return timeouts;
	}

	/**
	 * @param timeouts the timeouts to set
	 */
	public void setTimeouts(Integer timeouts) {
		this.timeouts = timeouts;
	}

	/**
	 * @return the waitTime
	 */
	public Float getWaitTime() {
		return waitTime;
	}

	/**
	 * @param waitTime the waitTime to set
	 */
	public void setWaitTime(Float waitTime) {
		this.waitTime = waitTime;
	}
	
	
}
