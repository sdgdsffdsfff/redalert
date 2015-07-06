/**   
* @Title: GeneralQuotaTogetherDomain.java 
* @Package com.tyyd.ywpt.dao.core.collect.together.general.dataobject 
* @Description:  
* @author wangyu   
* @date 2014-9-25 上午11:30:31 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.together.general.dataobject;


/**
 * @author wangyu
 *
 */
public class GeneralQuotaTogetherDomain {

	
	/**
     * 采集值
     */
    private Float quotaValue;

    /**
     * 创建时间
     */
    private String gmtCreated;

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
	 * @return the gmtCreated
	 */
	public String getGmtCreated() {
		return gmtCreated;
	}

	/**
	 * @param gmtCreated the gmtCreated to set
	 */
	public void setGmtCreated(String gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

    
}
