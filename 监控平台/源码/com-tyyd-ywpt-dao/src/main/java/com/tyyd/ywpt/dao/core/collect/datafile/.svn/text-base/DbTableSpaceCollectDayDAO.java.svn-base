/**   
* @Title: DbTableSpaceCollectDayDAO.java 
* @Package com.tyyd.ywpt.dao.core.collect.datafile 
* @Description:  
* @author wangyu   
* @date 2015-6-1 上午11:19:00 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.core.collect.datafile;

import java.util.List;

import com.tyyd.ywpt.dao.core.collect.datafile.dataobject.DbTableSpaceCollectDayDomain;

/**
 * @author wangyu
 *
 */
public interface DbTableSpaceCollectDayDAO {

	/**
	 * 收集表空间数据
	 * @param dbId
	 * @param collectDate
	 */
	public void collectTbsInfo(String dbId,String collectDate,String snapId, Float lastDayUsed);
	
	
	/**
	 * 获取当前时间段内的最大快照采集时间
	 * @param dbId
	 * @param collectDate
	 * @return
	 */
	public String getMaxSnapId(String dbId,String startDate,String endDate);
	
	/**
	 * 删除当前快照时间
	 * @param dbId
	 * @param collectDate
	 */
	public void delCurrentTbsData(String dbId,String collectDate);
	
	
	/**
	 * 获取最大的采集时间
	 * @param dbId
	 * @return
	 */
	public String getMaxTbsCollectDate(String dbId);
	
	
	/**
	 * 从his中拿最小的时间
	 * @param dbId
	 * @return
	 */
	public String getMinTbsHisCollectDate(String dbId,String strDate);
	/**
	 * 昨日的数据
	 * @param dbId
	 * @param collectDate
	 * @return
	 */
	public List<DbTableSpaceCollectDayDomain> getLastDayTbsInfo(String dbId,String collectDate);
	
	
	/**
	 * 更新
	 * @param dbId
	 * @param collectDate
	 * @param diskName
	 * @param lastDayUsed
	 */
	public void updateTbsInfo(String dbId,String collectDate,String tbsName,Float lastDayUsed);
	
	
	/**
	 * 表空间总量
	 * @param hostId
	 * @return
	 */
	public List<DbTableSpaceCollectDayDomain> sumTbsTotal(String dbId);
	
	/**
	 * 表空间增量
	 * @param hostId
	 * @return
	 */
	public List<DbTableSpaceCollectDayDomain> sumTbsDelta(String dbId);
	
	
	/**
	 * 某个表空间的增量
	 * @param hostId
	 * @param diskName
	 * @return
	 */
	public List<DbTableSpaceCollectDayDomain> sumTbsDeltaByTbsName(String dbId,String tbsName);
	
	
	/**
	 * 某个表空间的总量
	 * @param hostId
	 * @param diskName
	 * @return
	 */
	public List<DbTableSpaceCollectDayDomain> sumTbsTotalByTbs(String dbId,String tbsName);
	
}
