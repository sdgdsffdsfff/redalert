/**   
* @Title: BaseLineHandlerManagerImpl.java 
* @Package com.tyyd.ywpt.report.biz.quota.baseline.impl 
* @Description:  
* @author wangyu   
* @date 2015-3-31 下午4:16:12 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.quota.baseline.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;

import com.tyyd.ywpt.dao.baseline.dataobject.BaseLineRecordDomain;
import com.tyyd.ywpt.report.biz.quota.baseline.BaseLineAbstractHandler;
import com.tyyd.ywpt.report.biz.quota.baseline.BaseLineHandlerManager;
import com.tyyd.ywpt.report.biz.quota.dto.baseline.BaseLineReportFormatDataBO;

/**
 * @author wangyu
 *
 */
public class BaseLineHandlerManagerImpl implements BaseLineHandlerManager,InitializingBean{

	/**
	 * 前段
	 */
	@Resource
	private BaseLineAbstractHandler prevBaseLineHandler;
	
	/**
	 * 中段
	 */
	@Resource
	private BaseLineAbstractHandler middleBaseLineHandler;
	
	/**
	 * 末段
	 */
	@Resource
	private BaseLineAbstractHandler lastBaseLineHandler;
	
	
	@Override
	public void handler(String currentYmd, String startHm, String endHm,
			List<BaseLineRecordDomain> baseLineList,
			List<BaseLineReportFormatDataBO> maxValResult,
			List<BaseLineReportFormatDataBO> avgValResult) {

		this.prevBaseLineHandler.handler(currentYmd, startHm, endHm,
				baseLineList, maxValResult, avgValResult);

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.prevBaseLineHandler.setNextHandler(this.middleBaseLineHandler);
		this.middleBaseLineHandler.setNextHandler(this.lastBaseLineHandler);
	}

}
