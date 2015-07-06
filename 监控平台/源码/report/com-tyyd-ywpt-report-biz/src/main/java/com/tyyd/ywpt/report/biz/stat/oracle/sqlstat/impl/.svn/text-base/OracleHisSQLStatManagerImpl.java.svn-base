/**   
* @Title: OracleHisSQLStatManagerImpl.java 
* @Package com.tyyd.ywpt.report.biz.stat.oracle.sqlstat.impl 
* @Description:  
* @author wangyu   
* @date 2014-12-3 下午2:40:00 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.stat.oracle.sqlstat.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.tyyd.ywpt.biz.dict.SQLStatTypeEnum;
import com.tyyd.ywpt.biz.util.DateUtils;
import com.tyyd.ywpt.dao.configration.db.DbConfigDAO;
import com.tyyd.ywpt.dao.configration.db.dataobject.DbConfigDomain;
import com.tyyd.ywpt.dao.stat.oracle.OracleSQLStatDAO;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.OracleDbaHisActiveSessEventDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.OracleDbaHisActiveSessSampleDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.OracleDbaHisSQLStatDomain;
import com.tyyd.ywpt.dao.stat.oracle.dataobject.OracleDbaSqlStatListDomain;
import com.tyyd.ywpt.report.biz.quota.dto.GeneralQuotaReportListBO;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleDataBO;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleGeneralReportDataBO;
import com.tyyd.ywpt.report.biz.quota.impl.AbstractGeneralQuotaReportManager;
import com.tyyd.ywpt.report.biz.stat.oracle.sqlstat.OracleHisSQLStatManager;

/**
 * @author wangyu
 *
 */
public class OracleHisSQLStatManagerImpl extends AbstractGeneralQuotaReportManager<OracleDbaHisSQLStatDomain> implements OracleHisSQLStatManager {

	@Resource
	private OracleSQLStatDAO oracleSQLStatDAO;
	
	@Resource
	private DbConfigDAO dbConfigDAO;
	
	@SuppressWarnings("unchecked")
	private List<GeneralQuotaReportListBO> listSQLStatReportData(
			String beginTime, String endTime, String statType, Long databaseId,
			Long instanceId, String sqlId) {

		//检查
		if(StringUtils.isBlank(beginTime)){
			beginTime = DateUtils.getBeforeTwoDay() + " 00:00:01";
		}
		
		if(StringUtils.isBlank(endTime)){
			endTime = DateUtils.getCurrentDate() + " 23:59:59";
		}
		
		if(instanceId == null || databaseId == null || StringUtils.isBlank(sqlId)){
			return Collections.EMPTY_LIST;
		}
		
		if(!SQLStatTypeEnum.isExists(statType)){
			return Collections.EMPTY_LIST;
		}
		
		List<OracleDbaHisSQLStatDomain> list = oracleSQLStatDAO.listOracleSqlStat(beginTime, endTime, statType, databaseId, instanceId, sqlId);
		List<GeneralQuotaReportListBO> dataList = convertReportDbData(list);
		
		return dataList;
	}

	@Override
	public MultipleGeneralReportDataBO listSQLStatReportData(
			String beginTime, String endTime, String[] statType,
			Long databaseId, Long instanceId, String sqlId) {

		
		MultipleGeneralReportDataBO multipleGeneralReportDataBO = new MultipleGeneralReportDataBO();
		
		if(ArrayUtils.isEmpty(statType)){
			return null;
		}
		
		List<MultipleDataBO> arrays = new ArrayList<MultipleDataBO>();
		
		for(int i=0;i<statType.length;i++){
			String key = statType[i];
			if(SQLStatTypeEnum.isExists(key)){
				MultipleDataBO multipleDataBO = new MultipleDataBO();
				multipleDataBO.setName(SQLStatTypeEnum.getEnum(key));
				
				List<GeneralQuotaReportListBO> result = listSQLStatReportData(beginTime, endTime, key, databaseId, instanceId, sqlId);
				multipleDataBO.setData(convertGeneralQuotaReportData(result));
				arrays.add(multipleDataBO);
			}
		}
		
		MultipleDataBO[] seriesData = convertMultipleData(arrays);
		
		multipleGeneralReportDataBO.setSeriesData(seriesData);
		multipleGeneralReportDataBO.setReportName("多指标报表");
		
		return multipleGeneralReportDataBO;
	}

	


	@Override
	public List<OracleDbaHisActiveSessSampleDomain> listSampleTimes(
			String beginTime, String endTime, String dbId, Integer minCounts) {
		
		DbConfigDomain dbConfigDomain = dbConfigDAO.getDataBaseConfig(dbId);
		if(dbConfigDomain == null || dbConfigDomain.getOraDataBaseId() == null){
			return null;
		}
		
		return oracleSQLStatDAO.listSampleTimes(beginTime, endTime, dbConfigDomain.getOraDataBaseId(), dbConfigDomain.getOraInstanceId(), minCounts);
	}

	@Override
	public List<OracleDbaHisActiveSessEventDomain> listEventsBySampleTime(
			String sampleTime, String dbId) {
		
		DbConfigDomain dbConfigDomain = dbConfigDAO.getDataBaseConfig(dbId);
		if(dbConfigDomain == null || dbConfigDomain.getOraDataBaseId() == null || StringUtils.isBlank(sampleTime)){
			return null;
		}
		
		return oracleSQLStatDAO.listEventsBySampleTime(sampleTime, dbConfigDomain.getOraDataBaseId(), dbConfigDomain.getOraInstanceId());
		
	}


	@Override
	protected GeneralQuotaReportListBO copyPropertiesFromDomain(
			OracleDbaHisSQLStatDomain domain) {
		GeneralQuotaReportListBO dto = new GeneralQuotaReportListBO();
		dto.setQuotaValue(domain.getQuotaValue());
		dto.setGmtCreatedDate(domain.getUnixTimeStamp());
		
		StringBuffer id = new StringBuffer();
		id.append(domain.getDbId());
		id.append("_");
		id.append(domain.getInstanceId());
		id.append("_");
		id.append(domain.getSnapId());
		id.append("_");
		id.append(domain.getSqlId());
		id.append("_");
		id.append(domain.getPlanHashValue());
		
		return dto;
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.report.biz.stat.oracle.sqlstat.OracleHisSQLStatManager#listOracleSqlStatBySqlId(java.lang.String, java.lang.Long, java.lang.Long, java.lang.Integer)
	 */
	@Override
	public List<OracleDbaSqlStatListDomain> listOracleSqlStatBySqlId(
			String sqlId, String dbId, Integer times) {
		
		
		if(StringUtils.isBlank(dbId) || StringUtils.isBlank(sqlId) || null == times){
			return null;
		}
		
		DbConfigDomain dbConfigDomain = dbConfigDAO.getDataBaseConfig(dbId);
		
		Long databaseId = 0l;
		Long instanceId = 0l; 
		
		if(dbConfigDomain != null){
			databaseId = dbConfigDomain.getOraDataBaseId();
			instanceId = dbConfigDomain.getOraInstanceId();
		}
		
		return oracleSQLStatDAO.listOracleSqlStatBySqlId(sqlId, databaseId, instanceId, times);
		
	}
	
}
