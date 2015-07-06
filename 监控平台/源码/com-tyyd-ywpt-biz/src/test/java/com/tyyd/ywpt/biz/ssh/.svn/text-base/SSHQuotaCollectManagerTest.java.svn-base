/**   
* @Title: SSHQuotaCollectManagerTest.java 
* @Package com.tyyd.ywpt.biz.ssh 
* @Description:  
* @author wangyu   
* @date 2014-6-25 下午4:11:06 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.ssh;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.tyyd.ywpt.biz.BaseBizTest;

/**
 * @author wangyu
 *
 */
public class SSHQuotaCollectManagerTest extends BaseBizTest{

	@Resource
	private SSHQuotaCollectManager linuxSSHQuotaCollectManager;
	
	@Resource
	private SSHQuotaCollectManager aixSSHQuotaCollectManager;
	
	
	@Test
	public void testLinuxCpuInfo(){
		List<Map<String,Object>> list = linuxSSHQuotaCollectManager.getDiskQuota();
		for(Map<String,Object> map : list){
			System.out.println(map.toString());
		}
	}
	
	@Test
	public void testAixCpuInfo(){
		Map<String,Object> hashMap = aixSSHQuotaCollectManager.getCpuQuota();
		System.out.println(hashMap.toString());
	}
}
