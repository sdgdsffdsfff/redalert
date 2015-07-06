/**   
* @Title: MySQLStatScheduleManagerImpl.java 
* @Package com.tyyd.ywpt.schedule.stat.mysql.impl 
* @Description:  
* @author wangyu   
* @date 2014-12-10 上午11:51:11 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.stat.mysql.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.tyyd.ywpt.biz.dict.MySQLStatExtendsEnum;
import com.tyyd.ywpt.biz.dict.SysTypeEnum;
import com.tyyd.ywpt.dao.configration.db.DbConfigDAO;
import com.tyyd.ywpt.dao.configration.db.MySQLDbStatExtendsDAO;
import com.tyyd.ywpt.dao.configration.db.dataobject.DbConfigDomain;
import com.tyyd.ywpt.dao.configration.db.dataobject.MySQLDbStatExtendsDomain;
import com.tyyd.ywpt.dao.core.collect.even.mysql.MysqlQuotaCollectDayDAO;
import com.tyyd.ywpt.schedule.stat.mysql.MySQLStatScheduleManager;

/**
 * @author wangyu
 *
 */
public class MySQLStatScheduleManagerImpl implements MySQLStatScheduleManager {

	
	@Resource
	private DbConfigDAO dbConfigDAO;
	
	@Resource
	private MysqlQuotaCollectDayDAO mysqlQuotaCollectDayDAO;
	
	@Resource
	private MySQLDbStatExtendsDAO mySQLDbStatExtendsDAO;
	
	
	@Override
	public void refreshMySQLStat() {
		
		List<DbConfigDomain> list = dbConfigDAO.listNormalDbConfig(SysTypeEnum.MySQL.getVal());
		
		if(CollectionUtils.isNotEmpty(list)){
			for(DbConfigDomain dbConfigDomain : list){
				MySQLDbStatExtendsDomain domain = new MySQLDbStatExtendsDomain();
				//threads connects
				Float threadsConnects = mysqlQuotaCollectDayDAO
						.getNoCalQuotaValue(dbConfigDomain.getDbId(),MySQLStatExtendsEnum.THREADS_CONNECTED.getQuotaId());

				//avtive connects
				Float activeConnects = mysqlQuotaCollectDayDAO
						.getNoCalQuotaValue(dbConfigDomain.getDbId(), MySQLStatExtendsEnum.THREADS_RUNNING.getQuotaId());
				
				//qps
				Float qps = mysqlQuotaCollectDayDAO
						.getNeedCalQuotaValue(dbConfigDomain.getDbId(), MySQLStatExtendsEnum.COM_SELECT.getQuotaId());
				
				//tps
				Float insertCom = mysqlQuotaCollectDayDAO
						.getNeedCalQuotaValue(dbConfigDomain.getDbId(), MySQLStatExtendsEnum.COM_INSERT.getQuotaId());
				
				Float updateCom = mysqlQuotaCollectDayDAO
						.getNeedCalQuotaValue(dbConfigDomain.getDbId(), MySQLStatExtendsEnum.COM_UPDATE.getQuotaId());
				
				Float deleteCom = mysqlQuotaCollectDayDAO
						.getNeedCalQuotaValue(dbConfigDomain.getDbId(), MySQLStatExtendsEnum.COM_DELETE.getQuotaId());
				
				Float replaceCom = mysqlQuotaCollectDayDAO
						.getNeedCalQuotaValue(dbConfigDomain.getDbId(), MySQLStatExtendsEnum.COM_REPLACE.getQuotaId());
				
				insertCom = checkNull(insertCom);
				updateCom = checkNull(updateCom);
				deleteCom = checkNull(deleteCom);
				replaceCom = checkNull(replaceCom);
				
				
				Float tps =  insertCom + updateCom + deleteCom + replaceCom;
				
				//bytes recevied
				Float byteRecv = mysqlQuotaCollectDayDAO
						.getNeedCalQuotaValue(dbConfigDomain.getDbId(), MySQLStatExtendsEnum.BYTES_RECEIVED.getQuotaId());
				
				//bytes sent 
				Float byteSent = mysqlQuotaCollectDayDAO
						.getNeedCalQuotaValue(dbConfigDomain.getDbId(), MySQLStatExtendsEnum.BYTES_SENT.getQuotaId());
				
				
				domain.setDbId(dbConfigDomain.getDbId());
				domain.setThreadsConnects(floatToInteger(threadsConnects));
				domain.setActiveConnects(floatToInteger(activeConnects));
				domain.setQps(qps);
				domain.setTps(tps);
				domain.setBytesReceived(byteRecv);
				domain.setBytesSent(byteSent);
				
				//保存扩展信息
				saveMySQLDbStatExtends(domain);
				
			}
		}
		
		
		
	}


	/**
	 * @param insertCom
	 * @return
	 */
	private Float checkNull(Float value) {
		
		if(value == null){
			return 0f;
		}
		
		return value;
	}


	/**
	 * 保存扩展信息
	 * @param domain
	 */
	private void saveMySQLDbStatExtends(MySQLDbStatExtendsDomain domain) {
		if(domain == null){
			return;
		}
		MySQLDbStatExtendsDomain mySQLDbStatExtendsDomain = mySQLDbStatExtendsDAO.getMySQLDbStatExtendsDomain(domain.getDbId());
		if(mySQLDbStatExtendsDomain == null){
			mySQLDbStatExtendsDAO.addMySQLDbStatExtends(domain);
		}else{
			mySQLDbStatExtendsDAO.updateMySQLDbStatExtends(domain);
		}
		
	}

	
	private Integer floatToInteger(Float f){
		if(f == null){
			return 0;
		}
		
		String f1 = f.toString();
		String[] array = StringUtils.split(f1,".");
		if(array == null || array.length != 2){
			return 0;
		}
		
		return NumberUtils.toInt(array[0],0);
		
	}
	
}
