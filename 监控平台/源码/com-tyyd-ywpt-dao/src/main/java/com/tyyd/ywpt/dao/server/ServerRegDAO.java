/**   
* @Title: ServerRegDAO.java 
* @Package com.tyyd.ywpt.dao.server 
* @Description:  
* @author wangyu   
* @date 2014-6-22 下午2:02:08 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.server;

import java.util.List;

import com.tyyd.ywpt.dao.server.dataobject.ServerRegDomain;

/**
 * @author wangyu
 *
 */
public interface ServerRegDAO {

	/**
	 * 添加一台监控服务
	 * @param domain
	 */
	public void addServerReg(ServerRegDomain domain);

	
	/**
	 * 修改配置
	 * @param domain
	 */
	public void updateServerRegInfo(ServerRegDomain domain);
	
	
	/**
	 * 删除服务
	 * @param serverId
	 */
	public void deleteServerReg(String serverId);
	
	
	/**
	 * 服务列表
	 * @return
	 */
	public List<ServerRegDomain> listServerReg();
	
	/**
	 * 服务内容
	 * @param serverId
	 * @return
	 */
	public ServerRegDomain getServerRegById(String serverId);
	
	
	/**
	 * 服务内容
	 * @param serverId
	 * @return
	 */
	public ServerRegDomain getServerRegByName(String serverName);
}