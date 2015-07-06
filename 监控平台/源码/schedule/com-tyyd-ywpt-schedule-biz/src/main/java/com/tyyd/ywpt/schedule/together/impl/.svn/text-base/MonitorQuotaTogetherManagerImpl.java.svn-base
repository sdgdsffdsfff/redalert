/**   
* @Title: MonitorQuotaTogetherManagerImpl.java 
* @Package com.tyyd.ywpt.schedule.together.impl 
* @Description:  
* @author wangyu   
* @date 2014-9-23 上午10:20:39 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.together.impl;

import javax.annotation.Resource;

import com.tyyd.ywpt.biz.dict.SysTypeEnum;
import com.tyyd.ywpt.biz.dict.TogetherStatTypeEnum;
import com.tyyd.ywpt.dao.core.collect.together.host.HostQuotaTogetherDAO;
import com.tyyd.ywpt.dao.core.collect.together.mysql.MySQLQuotaTogetherDAO;
import com.tyyd.ywpt.dao.core.collect.together.oracle.OracleQuotaTogetherDAO;
import com.tyyd.ywpt.schedule.together.MonitorQuotaTogetherManager;

/**
 * @author wangyu
 *
 */
public class MonitorQuotaTogetherManagerImpl implements
		MonitorQuotaTogetherManager {

	@Resource
	private HostQuotaTogetherDAO hostQuotaTogetherDAO;
	
	@Resource
	private MySQLQuotaTogetherDAO mysqlQuotaTogetherDAO;
	
	@Resource
	private OracleQuotaTogetherDAO oracleQuotaTogetherDAO;
	
	
	@Override
	public void quotaTogether(String startDate, String endDate,
			Integer sysType, Integer statType) {

		//主机
		if(SysTypeEnum.server.getVal().intValue() == sysType.intValue()){
			hostQuotaTogether(startDate,endDate,statType);
			
		}else if(SysTypeEnum.Oracle.getVal().intValue() == sysType.intValue()){
			oracleQuotaTogether(startDate,endDate,statType);
			
		}else if(SysTypeEnum.MySQL.getVal().intValue() == sysType.intValue()){
			mysqlQuotaTogether(startDate,endDate,statType);
			
		}
		
	}


	/**
	 * Mysql
	 * @param startDate
	 * @param endDate
	 * @param statType
	 */
	private void mysqlQuotaTogether(String startDate, String endDate,
			Integer statType) {
		if(TogetherStatTypeEnum.HOUR.getVal().intValue() == statType.intValue()){
			mysqlQuotaTogetherDAO.mysqlQuotaHoursTogether(startDate, endDate);
			
		}else if(TogetherStatTypeEnum.DAY.getVal().intValue() == statType.intValue()){
			mysqlQuotaTogetherDAO.mysqlQuotaDayTogether(startDate, endDate);
			
		}else if(TogetherStatTypeEnum.WEEK.getVal().intValue() == statType.intValue()){
			mysqlQuotaTogetherDAO.mysqlQuotaWeekTogether(startDate, endDate);
			
		}else if(TogetherStatTypeEnum.MONTH.getVal().intValue() == statType.intValue()){
			mysqlQuotaTogetherDAO.mysqlQuotaMonthTogether(startDate, endDate);
			
		}
	}


	/**
	 * Oracle
	 * @param startDate
	 * @param endDate
	 * @param statType
	 */
	private void oracleQuotaTogether(String startDate, String endDate,
			Integer statType) {
		
		if(TogetherStatTypeEnum.HOUR.getVal().intValue() == statType.intValue()){
			oracleQuotaTogetherDAO.oracleQuotaHoursTogether(startDate, endDate);
			
		}else if(TogetherStatTypeEnum.DAY.getVal().intValue() == statType.intValue()){
			oracleQuotaTogetherDAO.oracleQuotaDayTogether(startDate, endDate);
			
		}else if(TogetherStatTypeEnum.WEEK.getVal().intValue() == statType.intValue()){
			oracleQuotaTogetherDAO.oracleQuotaWeekTogether(startDate, endDate);
			
		}else if(TogetherStatTypeEnum.MONTH.getVal().intValue() == statType.intValue()){
			oracleQuotaTogetherDAO.oracleQuotaMonthTogether(startDate, endDate);
			
		}
		
	}


	/**
	 * 主机
	 * @param startDate
	 * @param endDate
	 * @param statType
	 */
	private void hostQuotaTogether(String startDate, String endDate,
			Integer statType) {
		
		if(TogetherStatTypeEnum.HOUR.getVal().intValue() == statType.intValue()){
			hostQuotaTogetherDAO.hostQuotaHoursTogether(startDate, endDate);
			
		}else if(TogetherStatTypeEnum.DAY.getVal().intValue() == statType.intValue()){
			hostQuotaTogetherDAO.hostQuotaDayTogether(startDate, endDate);
			
		}else if(TogetherStatTypeEnum.WEEK.getVal().intValue() == statType.intValue()){
			hostQuotaTogetherDAO.hostQuotaWeekTogether(startDate, endDate);
			
		}else if(TogetherStatTypeEnum.MONTH.getVal().intValue() == statType.intValue()){
			hostQuotaTogetherDAO.hostQuotaMonthTogether(startDate, endDate);
			
		}
		
	}


	@Override
	public void clearQuotaTogether(String deleteFromDate, Integer sysType,
			Integer statType) {

		// 主机
		if (SysTypeEnum.server.getVal().intValue() == sysType.intValue()) {
			clearHostQuotaTogether(deleteFromDate, statType);

		} else if (SysTypeEnum.Oracle.getVal().intValue() == sysType.intValue()) {
			clearOracleQuotaTogether(deleteFromDate, statType);

		} else if (SysTypeEnum.MySQL.getVal().intValue() == sysType.intValue()) {
			clearMySQLQuotaTogether(deleteFromDate, statType);

		}

	}


	/**
	 * 清理MySQL
	 * @param deleteFromDate
	 * @param statType
	 */
	private void clearMySQLQuotaTogether(String deleteFromDate, Integer statType) {
		if(TogetherStatTypeEnum.HOUR.getVal().intValue() == statType.intValue()){
			mysqlQuotaTogetherDAO.clearMySQLQuotaHoursTogether(deleteFromDate);
			
		}else if(TogetherStatTypeEnum.DAY.getVal().intValue() == statType.intValue()){
			mysqlQuotaTogetherDAO.clearMySQLQuotaDayTogether(deleteFromDate);
			
		}else if(TogetherStatTypeEnum.WEEK.getVal().intValue() == statType.intValue()){
			mysqlQuotaTogetherDAO.clearMySQLQuotaWeekTogether(deleteFromDate);
			
		}else if(TogetherStatTypeEnum.MONTH.getVal().intValue() == statType.intValue()){
			mysqlQuotaTogetherDAO.clearMySQLQuotaMonthTogether(deleteFromDate);
			
		}
	}


	/**
	 * 清理ORACLE
	 * @param deleteFromDate
	 * @param statType
	 */
	private void clearOracleQuotaTogether(String deleteFromDate,
			Integer statType) {
		if(TogetherStatTypeEnum.HOUR.getVal().intValue() == statType.intValue()){
			oracleQuotaTogetherDAO.clearOracleQuotaHoursTogether(deleteFromDate);
			
		}else if(TogetherStatTypeEnum.DAY.getVal().intValue() == statType.intValue()){
			oracleQuotaTogetherDAO.clearOracleQuotaDayTogether(deleteFromDate);
			
		}else if(TogetherStatTypeEnum.WEEK.getVal().intValue() == statType.intValue()){
			oracleQuotaTogetherDAO.clearOracleQuotaWeekTogether(deleteFromDate);
			
		}else if(TogetherStatTypeEnum.MONTH.getVal().intValue() == statType.intValue()){
			oracleQuotaTogetherDAO.clearOracleQuotaMonthTogether(deleteFromDate);
			
		}
	}


	/**
	 * 清理主机
	 * @param deleteFromDate
	 * @param statType
	 */
	private void clearHostQuotaTogether(String deleteFromDate, Integer statType) {
		if(TogetherStatTypeEnum.HOUR.getVal().intValue() == statType.intValue()){
			hostQuotaTogetherDAO.clearHostQuotaHoursTogether(deleteFromDate);
			
		}else if(TogetherStatTypeEnum.DAY.getVal().intValue() == statType.intValue()){
			hostQuotaTogetherDAO.clearHostQuotaDayTogether(deleteFromDate);
			
		}else if(TogetherStatTypeEnum.WEEK.getVal().intValue() == statType.intValue()){
			hostQuotaTogetherDAO.clearHostQuotaWeekTogether(deleteFromDate);
			
		}else if(TogetherStatTypeEnum.MONTH.getVal().intValue() == statType.intValue()){
			hostQuotaTogetherDAO.clearHostQuotaMonthTogether(deleteFromDate);
			
		}
	}

	
	
	
}
