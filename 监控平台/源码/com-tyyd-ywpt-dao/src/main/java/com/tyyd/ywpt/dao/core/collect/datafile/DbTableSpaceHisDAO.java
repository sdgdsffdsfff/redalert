/**   
* @Title: DbTableSpaceHisDAO.java 
* @Package com.tyyd.ywpt.dao.core.collect.datafile 
* @Description:  
* @author wangyu   
* @date 2014-8-7 下午4:58:21 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.datafile;

import java.util.List;

import com.tyyd.ywpt.dao.core.collect.datafile.dataobject.DbTableSpaceHisDomain;
import com.tyyd.ywpt.dao.core.collect.disk.dataobject.DiskMonitorHisDomain;

/**
 * @author wangyu
 *
 */
public interface DbTableSpaceHisDAO {

	/**
	 * 未处理的任务
	 * @return
	 */
	@Deprecated
	public List<DbTableSpaceHisDomain> listUnCompletedTask();
	
	
	/**
	 * 关闭正常任务
	 */
	@Deprecated
	public void closeNormalUnCompletedTask();
	
	
	/**
	 * 关闭不监控的任务
	 */
	@Deprecated
	public void closeNoMonitorTask();
	
	/**
	 * 关闭任务
	 * @param id
	 */
	public void closeUnCompletedTaskById(String id);
	
	
	/**
	 * 关闭过期任务数据
	 */
	@Deprecated
	public void closeExpiredData();
	
	/**
	 * 返回1000行，未处理的数据
	 */
	public List<DbTableSpaceHisDomain> getTbsMonitorUnCompletedData();
	
	
	/**
	 * 查询未处理的表空间数据
	 * @param id
	 * @return
	 */
	public DbTableSpaceHisDomain getTbsMonitorUnCompletedObj(String id);
	
	/**
	 * 获取最后一次时间
	 * @param hostId
	 * @return
	 */
	public String getMaxSnapGmtDate(String dbId);
	
	/**
	 * 获取最大的snapId
	 * @param hostId
	 * @param gmtCreated
	 * @return
	 */
	public Long getMaxSnapId(String dbId,String gmtCreated);
	
	/**
	 * 获取表空间列表
	 * @param hostId
	 * @param snapId
	 * @return
	 */
	public List<DbTableSpaceHisDomain> listLastTbsMonitorHis(String dbId,Long snapId);
}
