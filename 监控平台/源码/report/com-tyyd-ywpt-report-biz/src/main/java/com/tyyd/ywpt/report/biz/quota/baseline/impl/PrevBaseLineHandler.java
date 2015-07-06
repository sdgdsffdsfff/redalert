/**   
* @Title: OneDayBaseLineHandler.java 
* @Package com.tyyd.ywpt.report.biz.quota.baseline.impl 
* @Description:  
* @author wangyu   
* @date 2015-3-31 下午2:24:24 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.quota.baseline.impl;

import com.tyyd.ywpt.report.biz.quota.baseline.BaseLineAbstractHandler;

/**
 * 日内
 * @author wangyu
 *
 */
public class PrevBaseLineHandler extends BaseLineAbstractHandler {

	
	@Override
	public Integer getRoleType() {
		return RoleLevel.Prev.getDayType();
	}


}
