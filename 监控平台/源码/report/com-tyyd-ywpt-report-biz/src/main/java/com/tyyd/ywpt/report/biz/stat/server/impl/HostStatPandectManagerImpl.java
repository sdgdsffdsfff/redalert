/**   
* @Title: HostStatPandectManagerImpl.java 
* @Package com.tyyd.ywpt.report.biz.stat.server.impl 
* @Description:  
* @author wangyu   
* @date 2015-2-2 上午11:12:43 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.stat.server.impl;

import java.util.List;

import javax.annotation.Resource;

import com.tyyd.ywpt.dao.stat.server.HostStatPandectDAO;
import com.tyyd.ywpt.dao.stat.server.domain.HostStatPandectDomain;
import com.tyyd.ywpt.report.biz.stat.server.HostStatPandectManager;

/**
 * @author wangyu
 *
 */
public class HostStatPandectManagerImpl implements HostStatPandectManager {

	@Resource
	private HostStatPandectDAO hostStatPandectDAO;
	
	@Override
	public List<HostStatPandectDomain> listHostStatPandect() {
		List<HostStatPandectDomain> list = hostStatPandectDAO.listHostStatPandect();
		return list;
	}

}
