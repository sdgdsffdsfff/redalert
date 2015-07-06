/**   
* @Title: DbTableSpaceDAO.java 
* @Package com.tyyd.ywpt.dao.core.collect.datafile 
* @Description:  
* @author wangyu   
* @date 2014-6-23 下午2:54:49 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.datafile;

import java.util.List;

import com.tyyd.ywpt.dao.core.collect.datafile.dataobject.DbTableSpaceDomain;

/**
 * @author wangyu
 *
 */
public interface DbTableSpaceDAO {

	/**
	 * 添加一个表空间
	 * @param domain
	 */
	public void addDbTableSpace(DbTableSpaceDomain domain);
	
	/**
	 * 某个DB的数据库表空间
	 * @param monitorId
	 * @return
	 */
	public List<DbTableSpaceDomain> listDbTableSpace(String monitorId);
	
	
	/**
	 * 删除表空间
	 * @param monitorId
	 */
	public void deleteDbTableSpace(String monitorId);
	
	
	/**
	 * 表空间信息
	 * @param dbId
	 * @return
	 */
	public DbTableSpaceDomain totalTbsInfo(String dbId);
	
	/**
	 * 更新表空间
	 * @param dbId
	 * @param tbsName
	 */
	public void updateDbTableSpaceInfo(String dbId,String tbsName,Float usedTbs,Float maxTbs,Float usePercent);
	
	
	/**
	 * 获取表空间数据
	 * @param hostId
	 * @param diskName
	 * @return
	 */
	public DbTableSpaceDomain getDbTableSpaceMonitorById(String dbId,String tbsName);

}
