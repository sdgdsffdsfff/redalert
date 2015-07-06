/**   
* @Title: HostDirAlertConfigDAO.java 
* @Package com.tyyd.ywpt.dao.configration.hostdiralert 
* @Description:  
* @author wangyu   
* @date 2014-6-20 下午5:08:21 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.configration.hostdiralert;

import java.util.List;

import com.tyyd.ywpt.dao.base.dataobject.PageQuery;
import com.tyyd.ywpt.dao.configration.hostdiralert.dataobject.HostDirAlertConfigDomain;

/**
 * @author wangyu
 *
 */
public interface HostDirAlertConfigDAO {

	/**
	 * 添加一个主机文件监控
	 * @param domain
	 */
	public void addHostDirAlertConfig(HostDirAlertConfigDomain domain);
	
	/**
	 * 删除主机文件的监控
	 * @param id
	 */
	public void delHostDirAlertConfig(String id);
	
	
	/**
	 * 获取文件监控详情
	 * @param id
	 * @return
	 */
	public HostDirAlertConfigDomain  getHostDirAlertConfig(String id);
	
	/**
	 * 更新主机目录的信息
	 * @param domain
	 */
	public void updateHostDirAlertConfig(HostDirAlertConfigDomain domain);
	
	
	/**
	 * 分页
	 * @param pageQuery
	 * @param hostDir
	 * @return
	 */
	public PageQuery<List<HostDirAlertConfigDomain>> listHostDirAlertConfig(PageQuery<List<HostDirAlertConfigDomain>> pageQuery,String hostDir,String hostId);
	
	
	/**
	 * 获取某种类型的日志监控
	 * @param configType
	 * @param logType
	 * @param serverId
	 * @return
	 */
	public List<HostDirAlertConfigDomain> listHostAlertConfig(Integer configType,Integer logType,String serverId);
	
	
	public List<HostDirAlertConfigDomain> listDbAlertConfig(Integer configType,Integer logType,String serverId);
	
}
