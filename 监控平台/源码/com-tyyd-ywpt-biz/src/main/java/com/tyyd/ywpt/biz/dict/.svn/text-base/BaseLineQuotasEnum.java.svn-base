/**   
* @Title: BaseLineQuotasEnum.java 
* @Package com.tyyd.ywpt.biz.dict 
* @Description:  
* @author wangyu   
* @date 2015-3-25 下午3:58:35 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.dict;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @author wangyu
 *
 */
public class BaseLineQuotasEnum {

	/**
	 * 主机
	 */
	private final static Map<String,String> serverQuotaMap = new HashMap<String,String>();
	
	
	/**
	 * oracle
	 */
	private final static Map<String,String> oracleQuotaMap = new HashMap<String,String>();
	
	
	/**
	 * mysql
	 */
	private final static Map<String,String> mysqlQuotaMap = new HashMap<String,String>();
	

	static{
		
		//主机
		serverQuotaMap.put("1","cpu使用率中user类");
		serverQuotaMap.put("4","cpu使用率中iowait类");
		serverQuotaMap.put("5","cpu使用率中load类");
		serverQuotaMap.put("16","网络接收总量");
		serverQuotaMap.put("17","网络发送总量");
		serverQuotaMap.put("100","内存使用百分比");
		serverQuotaMap.put("101","swap使用百分比");
		
		
		//oracle
		oracleQuotaMap.put("2022","每秒提交数量");
		oracleQuotaMap.put("2024","每秒回滚数量");
		oracleQuotaMap.put("2046","每秒硬解析次数");
		oracleQuotaMap.put("2121","每秒sql执行数");
		oracleQuotaMap.put("2030","每秒逻辑读");
		oracleQuotaMap.put("2004","每秒物理读");
		oracleQuotaMap.put("2006","每秒物理写");
		oracleQuotaMap.put("77777778","总线程数");
		oracleQuotaMap.put("2016","每秒日志产生量");
		
		
		//Mysql
		mysqlQuotaMap.put("60","insert语句执行次数");
		mysqlQuotaMap.put("61","select语句执行次数");
		mysqlQuotaMap.put("62","update语句执行次数");
		mysqlQuotaMap.put("63","delete语句执行次数");
		mysqlQuotaMap.put("64","replace语句执行次数");
		mysqlQuotaMap.put("44","InnoDB已经完成的逻辑读请求数");
		mysqlQuotaMap.put("45","innodb物理读请求数");
		mysqlQuotaMap.put("46","innodbreadiops");
		mysqlQuotaMap.put("47","innodbwriteiops");
		mysqlQuotaMap.put("18","己建立连接数");
		mysqlQuotaMap.put("42","发送给所有客户端的字节数");
		mysqlQuotaMap.put("43","从所有客户端接收到的字节数");
		
		
	}
	
	
	@SuppressWarnings("unchecked")
	public static Map<String,String> getQuotas(String configType){
		if(StringUtils.isNotBlank(configType)){
			if("1".equals(configType)){
				return serverQuotaMap;
			}else if("2".equals(configType)){
				return oracleQuotaMap;
			}else if("3".equals(configType)){
				return mysqlQuotaMap;
			}
		}
		return MapUtils.EMPTY_MAP;
	}
	
	
}
