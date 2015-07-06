/**   
* @Title: HostStatPandectDAO.java 
* @Package com.tyyd.ywpt.dao.stat.server 
* @Description:  
* @author wangyu   
* @date 2015-2-2 上午11:00:03 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.stat.server;

import java.util.List;

import com.tyyd.ywpt.dao.stat.server.domain.HostStatPandectDomain;

/**
 * @author wangyu
 *
 */
public interface HostStatPandectDAO {

	
	/**
	 * 主机层的指标总览
	 * @return
	 */
	public List<HostStatPandectDomain> listHostStatPandect();
	
}
