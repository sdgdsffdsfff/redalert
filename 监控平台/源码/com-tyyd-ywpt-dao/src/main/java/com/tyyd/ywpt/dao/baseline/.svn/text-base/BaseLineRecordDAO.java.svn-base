/**   
* @Title: BaseLineRecordDAO.java 
* @Package com.tyyd.ywpt.dao.baseline 
* @Description:  
* @author wangyu   
* @date 2015-3-10 上午10:49:57 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.baseline;

import java.util.List;

import com.tyyd.ywpt.dao.baseline.dataobject.BaseLineRecordDomain;

/**
 * @author wangyu
 *
 */
public interface BaseLineRecordDAO {

	/**
	 * 主机累计类型的计算
	 * @param hostId
	 * @param quotaId
	 */
	public void saveHostTotalTypeBaseLine(String hostId,String quotaId);
	
	
	/**
	 * 主机直接比大小计算 
	 * @param hostId
	 * @param quotaId
	 */
	public void saveHostDirectCompareBaseLine(String hostId,String quotaId);
	
	
	/**
	 * Oracle累计类型的计算
	 * @param hostId
	 * @param quotaId
	 */
	public void saveOracleTotalTypeBaseLine(String dbId,String quotaId);
	
	
	/**
	 * Oracle直接比大小计算 
	 * @param hostId
	 * @param quotaId
	 */
	public void saveOracleDirectCompareBaseLine(String dbId,String quotaId);
	
	
	/**
	 * MySQL累计类型的计算
	 * @param hostId
	 * @param quotaId
	 */
	public void saveMySQLTotalTypeBaseLine(String dbId,String quotaId);
	
	
	/**
	 * MySQL直接比大小计算 
	 * @param hostId
	 * @param quotaId
	 */
	public void saveMySQLDirectCompareBaseLine(String dbId,String quotaId);
	
	
	/**
	 * 基线查询
	 * @param monitorId
	 * @param configType
	 * @param quotaId
	 * @return
	 */
	public List<BaseLineRecordDomain> listBaseLineRecord(String monitorId,Integer configType,String quotaId); 
	
	
	/**
	 * 删除基线数据
	 * @param monitorId
	 * @param configType
	 * @param quotaId
	 */
	public void delBaseLineRecord(String monitorId,Integer configType,String quotaId);
}
