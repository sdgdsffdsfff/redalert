/**   
* @Title: HostStatPandectDAOImpl.java 
* @Package com.tyyd.ywpt.dao.stat.server.impl 
* @Description:  
* @author wangyu   
* @date 2015-2-2 上午11:02:21 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.stat.server.impl;

import java.util.List;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.stat.server.HostStatPandectDAO;
import com.tyyd.ywpt.dao.stat.server.domain.HostStatPandectDomain;

/**
 * @author wangyu
 *
 */
public class HostStatPandectDAOImpl extends TyydBaseDAO implements HostStatPandectDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.stat.server.domain.HostStatPandectDomain";

	@Override
	public List<HostStatPandectDomain> listHostStatPandect() {
		
		return this.getSqlSessionTemplate().selectList(context_space + ".list_host_stat_pandect");
	}

}
