/**   
* @Title: HostDAO.java 
* @Package com.tyyd.ywpt.dao.configration.host 
* @Description:  
* @author wangyu   
* @date 2014-6-20 上午9:51:08 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.configration.host;

import java.util.List;

import com.tyyd.ywpt.dao.base.dataobject.PageQuery;
import com.tyyd.ywpt.dao.configration.host.dataobject.HostConfigDomain;

/**
 * @author wangyu
 *
 */
public interface HostConfigDAO {

	/**
	 * 添加一个主机
	 * @param domain
	 */
	public void addHost(HostConfigDomain domain);
	
	/**
	 * 删除主机
	 * @param hostId
	 */
	public void delHost(String hostId);
	
	/**
	 * @param hostId
	 * @return
	 */
	public HostConfigDomain getHostById(String hostId);
	
	/**
	 * 主机分页
	 * @param pageQuery
	 * @param ipAddr
	 * @param idc
	 * @param hostName
	 * @return
	 */
	public PageQuery<List<HostConfigDomain>> listHost(PageQuery<List<HostConfigDomain>> pageQuery,String ipAddr,String idc,String hostName);
	
	/**
	 * 更新主机信息
	 * @param domain
	 */
	public void updateHostInfo(HostConfigDomain domain);
	
	
	/**
	 * 更新主机监控的状态
	 * @param domain
	 */
	public void updateHostMonitorStatus(HostConfigDomain domain);
	
	
	/**
	 * 加载所有的主机配置
	 * @return
	 */
	public List<HostConfigDomain> listHostConfig(String serverId);
	
	
	/**
	 * 查询所有正常的主机列表
	 * @return
	 */
	public List<HostConfigDomain> listNormalHostConfig();
}
