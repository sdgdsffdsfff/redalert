/**   
* @Title: DbConfigDAO.java 
* @Package com.tyyd.ywpt.dao.configration.db 
* @Description:  
* @author wangyu   
* @date 2014-6-20 下午3:00:51 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.configration.db;

import java.util.List;

import com.tyyd.ywpt.dao.base.dataobject.PageQuery;
import com.tyyd.ywpt.dao.configration.db.dataobject.DbConfigDomain;

/**
 * @author wangyu
 *
 */
public interface DbConfigDAO {

	
	/**
	 * 添加一个数据库配置
	 * @param domain
	 */
	public void addDataBaseConfig(DbConfigDomain domain);
	
	/**
	 * 删除数据库配置
	 * @param dbConfigId
	 */
	public void delDataBaseConfig(String dbId);
	
	/**
	 * 查询数据库配置
	 * @param dbId
	 * @return
	 */
	public DbConfigDomain getDataBaseConfig(String dbId);
	
	/**
	 * 数据库配置分页
	 * @param pageQuery
	 * @param ipAddr
	 * @return
	 */
	public PageQuery<List<DbConfigDomain>> listDataBaseConfig(PageQuery<List<DbConfigDomain>> pageQuery,String ipAddr);
	
	
	/**
	 * 更新数据库配置
	 * @param domain
	 */
	public void updateDataBaseConfig(DbConfigDomain domain);
	
	
	/**
	 * 更新监控状态
	 * @param domain
	 */
	public void updateDataBaseMonitorStatus(DbConfigDomain domain);
	
	
	
	/**
	 * 获取DB配置
	 * @param serverId
	 * @param dbType
	 * @return
	 */
	public List<DbConfigDomain> listDbConfigByServerId(String serverId,String dbType);
	
	
	/**
	 * 正常的主机
	 * @param dbType
	 * @return
	 */
	public List<DbConfigDomain> listNormalDbConfig(Integer dbType);
	
}
