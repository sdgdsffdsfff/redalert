/**   
* @Title: JobTypePrefixEnum.java 
* @Package com.tyyd.ywpt.biz.dict 
* @Description:  
* @author wangyu   
* @date 2014-7-10 下午5:15:08 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.dict;


/**
 * @author wangyu
 *
 */
public enum JobTypePrefixEnum {

	server_cpu(1,1),
	server_memory(2,1),
	server_network(3,1),
	server_disk(4,1),
	Oracle_quota(5,2),
	Oracle_sql(6,2),
	Oracle_awr(7,2),
	Oracle_alert(8,4),
	MySQL_Slow_SQL(9,3),
	MySQL_quota(10,3),
	MySQL_heartbeat(11,7),
	Oracle_heartbeat(12,6),
	server_heartbeat(13,1),
	MySQL_alert(14,5),
	Oracle_tabspace(15,2),
	MySQL_tabspace(16,3),
	Oracle_longtime_SQL(17,2),
	Oracle_long_trans(18,2),
	Oracle_high_level_index(19,2),
	keepalived_heartbeat(20,8),
	alert_keepalived(21,9),
	MySQL_SLAVE_STATE(23,3),
	MySQL_Long_sql(24,3),
	MySQL_SQL_Many_Rows(25,3);
	
	
	private Integer val;
	
	private Integer targetVal;

	/**
	 * @return the val
	 */
	public Integer getVal() {
		return val;
	}

	/**
	 * @param val the val to set
	 */
	public void setVal(Integer val) {
		this.val = val;
	}

	/**
	 * @return the targetVal
	 */
	public Integer getTargetVal() {
		return targetVal;
	}

	/**
	 * @param targetVal the targetVal to set
	 */
	public void setTargetVal(Integer targetVal) {
		this.targetVal = targetVal;
	}

	/**
	 * @param val
	 * @param targetVal
	 */
	private JobTypePrefixEnum(Integer val, Integer targetVal) {
		this.val = val;
		this.targetVal = targetVal;
	}
	
	
	public static Integer getEnum(Integer val){
		JobTypePrefixEnum[] enums = JobTypePrefixEnum.class.getEnumConstants();
		for(JobTypePrefixEnum sysType : enums){
			if(sysType.getVal().intValue() == val.intValue()){
				return sysType.getTargetVal();
			}
		}
		return 0;
	}
	
	
	
	
	/**
	 * SQL 转化
	 * 
	 * 
SELECT id,
CASE WHEN 'host_' = bean_prefix THEN 1
     WHEN 'oracle_' =  bean_prefix THEN 2
     WHEN 'mysql_' =  bean_prefix THEN 3
     WHEN 'alert_ora_' =  bean_prefix THEN 4
     WHEN 'alert_mysql_' =  bean_prefix THEN 5
     WHEN 'datasource_oracle_' =  bean_prefix THEN 6
     WHEN 'datasource_mysql_' =  bean_prefix THEN 7
     WHEN 'keepalived_' =  bean_prefix THEN 8
     WHEN 'alert_keepalived_' =  bean_prefix THEN 9
ELSE 0 END bean_type  ,bean_prefix
     FROM b_java_job_def 
	 * 
	 */
	
}
