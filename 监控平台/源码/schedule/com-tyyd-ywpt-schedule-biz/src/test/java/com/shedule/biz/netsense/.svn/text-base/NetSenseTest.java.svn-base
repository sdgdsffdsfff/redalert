/**   
* @Title: NetSenseTest.java 
* @Package com.shedule.biz.netsense 
* @Description:  
* @author wangyu   
* @date 2015-1-22 下午6:06:06 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.shedule.biz.netsense;

import javax.annotation.Resource;

import org.junit.Test;

import com.shedule.biz.BaseScheduleBizTest;
import com.tyyd.ywpt.schedule.netsense.NetsenseManager;
import com.tyyd.ywpt.tools.http.HttpService;
import com.tyyd.ywpt.tools.phone.SmsMessageUtils;

/**
 * @author wangyu
 *
 */
public class NetSenseTest extends BaseScheduleBizTest{

	@Resource
	private NetsenseManager netsenseManager;
	
	@Resource
	private HttpService httpService;
	
	@Resource
	private SmsMessageUtils smsMessageUtils;
	
	@Test
	public void testSaveNetSenseMonitorData(){
		netsenseManager.saveNetSenseMonitorData();
		
	} 
	
	
	@Test
	public void testHttpGet(){
		for(int i=0;i<10;i++){
			String result = null;
			if(i%2 == 0){
				result = httpService.getRequest("http://www.baidu.com/");
			}else{
				result = httpService.getRequest("http://http://xueqiu.com/");
			}
			System.out.println("第"+i+"次");
			System.out.println(result);
		}
	}
	
	
	@Test
	public void textSend(){
		for(int i=0;i<1;i++){
			smsMessageUtils.sendMessage(new String[]{"18072770513"}, "测试");
		}
	}
}
