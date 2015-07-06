/**   
* @Title: HessianTest.java 
* @Package com.tyyd.ywpt.biz.hessian 
* @Description:  
* @author wangyu   
* @date 2014-11-18 下午2:12:45 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.hessian;

import java.net.MalformedURLException;

import org.junit.Test;

import com.caucho.hessian.client.HessianProxyFactory;
import com.tyyd.ywpt.biz.quartzschedule.TaskRefreshRegeditManager;
import com.tyyd.ywpt.biz.quartzschedule.dataobject.TaskRefreshResponseDTO;

/**
 * @author wangyu
 *
 */
public class HessianTest {

	private static String url = "http://192.168.10.3:9090/remote/regeditServer"; 
	private static String mdkey = "a4e12669cb2d8cddade5cd0e81f0e160"; 
	
	private TaskRefreshRegeditManager getInterface(){
		HessianProxyFactory factory = new HessianProxyFactory(); 
        TaskRefreshRegeditManager hello = null;
		try {
			hello = (TaskRefreshRegeditManager) factory.create(TaskRefreshRegeditManager.class, url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return hello;
	}
	
	
	@Test
	public void testSayHello(){
		TaskRefreshRegeditManager hello = getInterface();
        System.out.println(hello.sayHello("wangyu")); 
	}
	
	@Test
	public void testBean(){
		
		TaskRefreshRegeditManager hello = getInterface();
        
//		//先查找 Mysql
//		TaskRefreshResponseDTO dto = hello.existsBean("datasource_mysql_24",mdkey);
//		System.out.println(dto.toString()); 
//        
//        //删除
//		dto = hello.delBean("datasource_mysql_24", mdkey);
//		System.out.println(dto.toString()); 
//		
//		//查找
//		dto = hello.existsBean("datasource_mysql_24",mdkey);
//		System.out.println(dto.toString()); 
//		
//		//添加
//		dto = hello.addBean("24", 3, 11, "datasource_mysql_24", mdkey);
//		System.out.println(dto.toString()); 
//		
//		
//		//查找
//		dto = hello.existsBean("datasource_mysql_24",mdkey);
//		System.out.println(dto.toString()); 
		
		
		//先查找 Oracle 
		TaskRefreshResponseDTO dto = hello.existsBean("datasource_oracle_60",mdkey);
		System.out.println(dto.toString()); 
        
        //删除
		dto = hello.delBean("datasource_oracle_60", mdkey);
		System.out.println(dto.toString()); 
		
		//查找
		dto = hello.existsBean("datasource_oracle_60",mdkey);
		System.out.println(dto.toString()); 
		
		//添加
		dto = hello.addBean("60", 2, 12, "datasource_oracle_60", mdkey);
		System.out.println(dto.toString()); 
		
		
		//查找
		dto = hello.existsBean("datasource_oracle_60",mdkey);
		System.out.println(dto.toString()); 
		
		
	}
	
	@Test
	public void testDelTask(){
		TaskRefreshRegeditManager hello = getInterface();
        
		TaskRefreshResponseDTO dto = hello.delTask("1508", mdkey);
		System.out.println(dto.toString()); 
		
		
	}
	
	@Test
	public void testRegTask(){
		TaskRefreshRegeditManager hello = getInterface();
        
		
		String newJobId = "1496";
		String newJobName = "host_26_主机cpu_monitor"; 
		String newJobGroup = "主机cpu_monitor_group";
		String cronExpression = "10 0/2 * * * ?";
		String newbeanId = "host_26";
		String monitorId ="26";
		String configType = "1";
		Integer jobType = 1;
		String oldJobId = "1496";
		String oldJobName = "host_26_主机cpu_monitor";
		String oldJobGroup = "主机cpu_monitor_group";
		
		TaskRefreshResponseDTO dto = hello.regeditTask(newJobId, newJobName,
				newJobGroup, cronExpression, newbeanId, monitorId, configType,
				jobType, oldJobId, oldJobName, oldJobGroup, mdkey);
		System.out.println(dto.toString()); 
		
		
		
	}
	
}
