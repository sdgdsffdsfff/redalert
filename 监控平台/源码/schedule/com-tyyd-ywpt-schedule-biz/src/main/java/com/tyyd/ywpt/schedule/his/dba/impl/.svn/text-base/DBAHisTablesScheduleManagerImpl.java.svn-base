/**   
* @Title: DBAHisTablesScheduleManagerImpl.java 
* @Package com.tyyd.ywpt.schedule.his.dba.impl 
* @Description:  
* @author wangyu   
* @date 2015-2-4 下午2:18:27 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.his.dba.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tyyd.ywpt.dao.dba.his.DBAHisTablesDAO;
import com.tyyd.ywpt.dao.dba.his.dataobject.DBAStatSnapDomain;
import com.tyyd.ywpt.schedule.his.dba.DBAHisTablesScheduleManager;

/**
 * @author wangyu
 *
 */
public class DBAHisTablesScheduleManagerImpl implements
		DBAHisTablesScheduleManager {
	
	public static final Logger LOGGER = Logger.getLogger(DBAHisTablesScheduleManagerImpl.class);
	
	@Resource
	private DBAHisTablesDAO dBAHisTablesDAO;

	@Override
	public void doClearHisDataTask() {
		
		LOGGER.info("清理历史数据开始...");
		long start = System.currentTimeMillis();
		
		//获取snap列表
		List<DBAStatSnapDomain> snapList = this.getHisDbaSnapList();
		
		if(CollectionUtils.isNotEmpty(snapList)){
			for(DBAStatSnapDomain domain : snapList){
				//move 数据
				
				String[] srcTables = dbaHisTables.getSrcTabels();
				for(String tableName : srcTables){
					moveSrcToDest(tableName,domain);
					LOGGER.info(String.format(
							"清理%s,dbId=%s,snapId=%s,instanceId=%s",
							tableName,
							domain.getDbId(), 
							domain.getSnapId(),
							domain.getInstanceId()));
				}
			}
		}
		
		
		//move snap id
		if(CollectionUtils.isNotEmpty(snapList)){
			for(DBAStatSnapDomain domain : snapList){
				dBAHisTablesDAO.moveDBAHisTable("dba_hist_snapshot",
						"dba_hist_snapshot_his", domain.getDbId(),
						domain.getSnapId(), domain.getInstanceId());
				LOGGER.info(String.format(
						"清理%s,dbId=%s,snapId=%s,instanceId=%s",
						"dba_hist_snapshot",
						domain.getDbId(), 
						domain.getSnapId(),
						domain.getInstanceId()));
			}
		}
		
		LOGGER.info(String.format("清理历史数据结束...,共话费%d秒", (System.currentTimeMillis() - start)/1000));
	}

	/**
	 * @param tableName
	 * @param domain
	 */
	private void moveSrcToDest(String tableName, DBAStatSnapDomain domain) {
		dBAHisTablesDAO.moveDBAHisTable(tableName,
				dbaHisTables.getDestTableName(tableName), domain.getDbId(),
				domain.getSnapId(), domain.getInstanceId());
	}

	/**
	 * @return
	 */
	private List<DBAStatSnapDomain> getHisDbaSnapList() {
		return dBAHisTablesDAO.listHisSnap();
	}

	
	

	enum dbaHisTables{
		
		dba_hist_active_sess_history("dba_hist_active_sess_history","dba_hist_active_sess_history_his"),
		dba_hist_buffer_pool_stat("dba_hist_buffer_pool_stat","dba_hist_buffer_pool_stat_his"),
		dba_hist_osstat("dba_hist_osstat","dba_hist_osstat_his"),
		dba_hist_seg_stat("dba_hist_seg_stat","dba_hist_seg_stat_his"),
		dba_hist_sgastat("dba_hist_sgastat","dba_hist_sgastat_his"),
		dba_hist_sqlstat("dba_hist_sqlstat","dba_hist_sqlstat_his"),
		dba_hist_sysmetric_summary("dba_hist_sysmetric_summary","dba_hist_sysmetric_summary_his"),
		dba_hist_sysstat("dba_hist_sysstat","dba_hist_sysstat_his"),
		dba_hist_system_event("dba_hist_system_event","dba_hist_system_event_his"),
		dba_hist_undostat("dba_hist_undostat","dba_hist_undostat_his"),
		dba_hist_waitstat("dba_hist_waitstat","dba_hist_waitstat_his");
		
		
		
		private String srcTable;
		private String destTable;
		
		
		
		/**
		 * @return the srcTable
		 */
		public String getSrcTable() {
			return srcTable;
		}
		/**
		 * @param srcTable the srcTable to set
		 */
		public void setSrcTable(String srcTable) {
			this.srcTable = srcTable;
		}
		/**
		 * @return the destTable
		 */
		public String getDestTable() {
			return destTable;
		}
		/**
		 * @param destTable the destTable to set
		 */
		public void setDestTable(String destTable) {
			this.destTable = destTable;
		}
		/**
		 * @param srcTable
		 * @param destTable
		 */
		private dbaHisTables(String srcTable, String destTable) {
			this.srcTable = srcTable;
			this.destTable = destTable;
		}
		
		
		
		/**
		 * 获取目标
		 * @param val
		 * @return
		 */
		public static String getDestTableName(String val){
			dbaHisTables[] enums = dbaHisTables.class.getEnumConstants();
			for(dbaHisTables statType : enums){
				if(statType.getSrcTable().equals(val)){
					return statType.getDestTable();
				}
			}
			return StringUtils.EMPTY;
		}
		
		
		/**
		 * 获取源表列表
		 * @return
		 */
		public static String[] getSrcTabels(){
			dbaHisTables[] enums = dbaHisTables.class.getEnumConstants();
			String[] tables = new String[enums.length];
			for(int i=0;i<enums.length;i++){
				dbaHisTables statType = enums[i];
				tables[i] = statType.getSrcTable();
			}
			
			return tables;
		}
		
	}
	
}
