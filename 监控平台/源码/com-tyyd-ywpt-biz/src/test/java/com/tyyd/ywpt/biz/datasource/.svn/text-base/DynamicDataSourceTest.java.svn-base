/**   
* @Title: DynamicDataSourceTest.java 
* @Package com.tyyd.ywpt.biz.datasource 
* @Description:  
* @author wangyu   
* @date 2014-7-7 上午9:01:30 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.datasource;

import javax.annotation.Resource;

import org.junit.Test;

import com.tyyd.ywpt.biz.BaseBizTest;
import com.tyyd.ywpt.dao.admin.user.UserDAO;
import com.tyyd.ywpt.dao.admin.user.dataobject.UserDomain;
import com.tyyd.ywpt.dao.mysql.MySQLMonitorCollectDAO;
import com.tyyd.ywpt.dao.oracle.OracleMonitorCollect;
import com.tyyd.ywpt.tools.ds.DynamicDataSourceInterface;
import com.tyyd.ywpt.tools.ds.dataobject.DynamicDataSourceDomain;
import com.tyyd.ywpt.tools.ds.impl.DynamicDataSourceContextHolder;

/**
 * @author wangyu
 *
 */
public class DynamicDataSourceTest extends BaseBizTest {

	@Resource
	private MySQLMonitorCollectDAO mySQLMonitorCollectDAO;
	
	@Resource
	private OracleMonitorCollect oracleMonitorCollect;
	
	@Resource
	private DynamicDataSourceInterface dynamicDataSourceInterface;
	
	@Resource
	private UserDAO userDAO;
	
	
	@Test
	public void testDs(){
		
		//注册MySQL的源
		DynamicDataSourceDomain mysqlDs = getMySQLDs();
		dynamicDataSourceInterface.regAnNewDataSource("dataSource_mysql_10.100.10.130_3306_information_schema", mysqlDs);
		
		//注册Oracle源
		DynamicDataSourceDomain oracleDs = getOracleDs();
		dynamicDataSourceInterface.regAnNewDataSource("dataSource_oracle_192.168.10.17_1521_YDPT1", oracleDs);
		
		//查询Oracle返回数据
		DynamicDataSourceContextHolder.setDataSource("dataSource_oracle_192.168.10.17_1521_YDPT1");
		String oracleSysdate = oracleMonitorCollect.getSysdate();
		System.out.println("Oracle="+oracleSysdate);
		
		//查询MySQL返回数据
		DynamicDataSourceContextHolder.setDataSource("dataSource_mysql_10.100.10.130_3306_information_schema");
		String mySQLSysdate = mySQLMonitorCollectDAO.getCurrentDate();
		System.out.println("MySQL="+mySQLSysdate);
		
		//查询default的源数据
		//DynamicDataSourceContextHolder.setDataSource("dataSource_mysql_10.100.10.130_3306_information_schema");
		//可能是一个方法体内不需要再次切换
		DynamicDataSourceContextHolder.setDataSource("dataSource");
		
		
		//加入一条记录
		addUserTest();
		UserDomain userDomain = userDAO.getUserById("0010");
		System.out.println(userDomain.getUserName());
		//done...
		
	}
	
	
	
	/**
	 * 获取MySQL DS
	 * @return
	 */
	private DynamicDataSourceDomain getMySQLDs(){
		DynamicDataSourceDomain domain = new DynamicDataSourceDomain();
		domain.setPort("3306");
		domain.setIpAddr("10.100.10.130");
		domain.setUserName("root");
		domain.setPasswd("tyyddba");
		domain.setInstanceId("information_schema");
		domain.setDbType("mysql");
		return domain;
	}
	
	
	/**
	 * 获取Oracle DS
	 * @return
	 */
	private DynamicDataSourceDomain getOracleDs(){
		DynamicDataSourceDomain domain = new DynamicDataSourceDomain();
		domain.setPort("1521");
		domain.setIpAddr("192.168.10.17");
		domain.setUserName("rnd");
		domain.setPasswd("rnd");
		domain.setInstanceId("YDPT1");
		domain.setDbType("oracle");
		return domain;
	}
	
	
	@Test
	public void addUserTest(){
		for(int i=1;i<2;i++){
			UserDomain userDomain = new UserDomain();
			userDomain.setEmail("newshake@163.com"+i);
			userDomain.setLoginName("newshake"+i);
			userDomain.setMobileMail("15382328086@189.cn"+i);
			userDomain.setPasswd("123456"+i);
			userDomain.setUserName("王宇"+i);
			userDomain.setId("001"+i);
			userDAO.addUser(userDomain);
		}
	}
	
}
