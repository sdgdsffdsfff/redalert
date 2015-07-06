/**   
 * @Title: SSHQuotaCollectManagerImpl.java 
 * @Package com.tyyd.ywpt.biz.ssh.impl 
 * @Description:  
 * @author wangyu   
 * @date 2014-6-25 下午2:33:50 
 * @CopyRight 天翼阅读
 * @version V1.0   
 */
package com.tyyd.ywpt.biz.ssh.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyyd.ywpt.biz.ssh.SSHQuotaCollectManager;
import com.tyyd.ywpt.biz.ssh.dataobject.AIXSSHConstast;
import com.tyyd.ywpt.biz.ssh.dataobject.LinuxSSHConstast;
import com.tyyd.ywpt.biz.ssh.dataobject.SSHCmdEnum;
import com.tyyd.ywpt.biz.ssh.dataobject.SSHResultBO;
import com.tyyd.ywpt.biz.util.DateUtils;

/**
 * @author wangyu
 * 
 */
public class SSHQuotaCollectManagerImpl extends AbstractSSHConnectManager
		implements SSHQuotaCollectManager {

	/* 
	 * 
	 * AIX : $1,$2,$3,$4,$5
	 *  00:00:00    %usr    %sys    %wio   %idle   physc
		00:05:01      28      23       8      42    8.00
		
	    Linux : $1,$3,$5,$6,$8
	    12时00分01秒     CPU     %user     %nice   %system   %iowait    %steal     %idle
		12时10分01秒     all      0.01      0.00      0.01      0.00      0.00     99.98
		
	 */
	@Override
	public Map<String,Object> getCpuQuota() {
		Map<String,Object> result = new HashMap<String,Object>();
		// 先判断操作系统
		String osVersion = getOsVersion();
		SSHResultBO cpuResult = null;
		if(osVersion.equalsIgnoreCase(SSHCmdEnum.AIX)){
			cpuResult = longConnectionWithCmd(AIXSSHConstast.AIX_CPU_CMD);
		}else if(osVersion.equalsIgnoreCase(SSHCmdEnum.Linux)){
			cpuResult = longConnectionWithCmd(LinuxSSHConstast.LINUX_CPU_CMD);
		}
		if(cpuResult.isSucess()){
			result = convertCpuShellToMap(cpuResult.getResult(),osVersion);
		}
		return result;
	}

	/**
	 * CPU shell内容转化
	 * @param shellStr
	 * @param osVersion
	 * @return
	 */
	private Map<String,Object> convertCpuShellToMap(String shellStr,String osVersion){
		Map<String,Object> result = new HashMap<String,Object>();
		String[] cpuInfo = shellStr.split(SSHCmdEnum.SPACE);
		//最多返回5项
		if(cpuInfo == null || cpuInfo.length != SSHCmdEnum.CPU_INFO_RESULT_LENGTH){
			return null;
		}
		//采集的时间点
		result.put("collect_time", cpuInfo[0]);
		//用户CPU利用率
		result.put("usr_cpu", cpuInfo[1]);
		//系统CPU利用率
		result.put("sys_cpu", cpuInfo[2]);
		//io等待率
		result.put("iowait_cpu", cpuInfo[3]);
		//cpu空闲率
		result.put("idle_cpu", cpuInfo[4]);
		if(osVersion.equalsIgnoreCase(SSHCmdEnum.AIX)){
			result.put("collect_time", DateUtils.aixCpuDateFormat(cpuInfo[0]));
		}else if(osVersion.equalsIgnoreCase(SSHCmdEnum.Linux)){
			result.put("collect_time", DateUtils.linuxCpuDateFormat(cpuInfo[0]));
		}
		return result;
	}
	
	
	
	private String getOsVersion() {
		SSHResultBO result = longConnectionWithCmd(SSHCmdEnum.OS_VERSION_CMD);
		if (result.isSucess()) {
			return result.getResult();
		}
		return "";
	}

	/* 
	 * Linux :
	 * Mem:  32818036k total, 10522968k used, 22295068k free,   169128k buffers
	 * 
	 */
	@Override
	public Map<String, Object> getMemoryQuota() {
		Map<String,Object> result = new HashMap<String,Object>();
		// 先判断操作系统
		String osVersion = getOsVersion();
		SSHResultBO cpuResult = null;
		
		//取memory
		if(osVersion.equalsIgnoreCase(SSHCmdEnum.AIX)){
			cpuResult = longConnectionWithCmd(AIXSSHConstast.AIX_MEMORY_SH);
		}else if(osVersion.equalsIgnoreCase(SSHCmdEnum.Linux)){
			cpuResult = longConnectionWithCmd(LinuxSSHConstast.LINUX_MEMORY_SH);
		}
		Map<String,Object> memoryResult = convertMemoryShellToMap(cpuResult.getResult(),osVersion);
		result.put("memory", memoryResult);
		
		//取memory swap
		cpuResult = null; 
		if(osVersion.equalsIgnoreCase(SSHCmdEnum.AIX)){
			cpuResult = longConnectionWithCmd(AIXSSHConstast.AIX_MEMORY_SWAP_SH);
		}else if(osVersion.equalsIgnoreCase(SSHCmdEnum.Linux)){
			cpuResult = longConnectionWithCmd(LinuxSSHConstast.LINUX_MEMORY_SWAP_SH);
		}
		Map<String,Object> memorySwapResult = convertMemorySwapShellToMap(cpuResult.getResult(),osVersion);
		result.put("memory_swap", memorySwapResult);
		
		//取memory swap in out的值
		cpuResult = null; 
		 if(osVersion.equalsIgnoreCase(SSHCmdEnum.Linux)){
			cpuResult = longConnectionWithCmd(LinuxSSHConstast.LINUX_MEMORY_SWAP_IN_OUT_SH);
		}
		 Map<String,Object> memorySwapInOutResult =  convertMemorySwapInOutShellToMap(cpuResult.getResult(),osVersion);
		 result.put("memory_in_out", memorySwapInOutResult);
		 
		return result;
	}

	
	/**
	 * memory 转化
	 * @param shellStr
	 * @param osVersion
	 * @return
	 */
	private Map<String,Object> convertMemoryShellToMap(String shellStr,String osVersion){
		Map<String,Object> result = new HashMap<String,Object>();
		String[] cpuInfo = shellStr.split(SSHCmdEnum.SPACE);
		//最多返回5项  total used free buffers cached
		if(cpuInfo == null){
			return null;
		}
		//total used free buffers cached
		result.put("total", cpuInfo[0]);
		//used
		result.put("used", cpuInfo[1]);
		//free
		result.put("free", cpuInfo[2]);
		
		if(osVersion.equalsIgnoreCase(SSHCmdEnum.Linux)){
			//buffers
			result.put("buffers", cpuInfo[3]);
			//cached
			result.put("cached", cpuInfo[4]);
		}
		return result;
	}
	
	
	
	/**
	 * Memory Swap 转化
	 * @param shellStr
	 * @param osVersion
	 * @return
	 */
	private Map<String,Object> convertMemorySwapShellToMap(String shellStr,String osVersion){
		Map<String,Object> result = new HashMap<String,Object>();
		String[] cpuInfo = shellStr.split(SSHCmdEnum.SPACE);
		//total used free 
		if(cpuInfo == null){
			return null;
		}
		//total 
		result.put("total", cpuInfo[0]);
		//used
		result.put("used", cpuInfo[1]);
		//free
		result.put("free", cpuInfo[2]);
		
		return result;
	}
	
	
	
	private Map<String,Object> convertMemorySwapInOutShellToMap(String shellStr,String osVersion){
		Map<String,Object> result = new HashMap<String,Object>();
		String[] cpuInfo = shellStr.split(SSHCmdEnum.SPACE);
		//最多返回5项  in out
		if(cpuInfo == null){
			return null;
		}
		
		//in
		result.put("in", cpuInfo[0]);
		//out
		result.put("out", cpuInfo[1]);
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.biz.ssh.SSHQuotaCollectManager#getDiskQuota()
	 */
	@Override
	public List<Map<String,Object>> getDiskQuota() {
		
		// 先判断操作系统
		String osVersion = getOsVersion();
		SSHResultBO cpuResult = null;
		
		//Used Available Capacity Mounted
		if(osVersion.equalsIgnoreCase(SSHCmdEnum.AIX)){
			cpuResult = longConnectionWithCmd(AIXSSHConstast.AIX_DISK_SH);
		}else if(osVersion.equalsIgnoreCase(SSHCmdEnum.Linux)){
			cpuResult = longConnectionWithCmd(LinuxSSHConstast.LINUX_DISK_SH);
		}
		
		
		return convertDiskShellToMap(cpuResult.getResult(),osVersion);
		
	}

	
	private List<Map<String,Object>> convertDiskShellToMap(String shellStr,String osVersion){
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		String[] bigCatalog = shellStr.split(SSHCmdEnum.CTLF);
		//Used Available Capacity Mounted
		if(bigCatalog == null){
			return null;
		}
		
		for(String info : bigCatalog){
			String[] disk = info.split(SSHCmdEnum.SPACE);
			
			Map<String,Object> result = new HashMap<String,Object>();
			//Used
			result.put("Used", disk[0]);
			//Available
			result.put("Available", disk[1]);
			//Capacity
			result.put("Capacity", disk[2]);
			//Mounted
			result.put("Mounted", disk[3]);
			list.add(result);
			
		}
		return list;
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see com.tyyd.ywpt.biz.ssh.SSHQuotaCollectManager#getNetWorkQuota()
	 */
	@Override
	public Map<String, Object> getNetWorkQuota() {
		
		Map<String,Object> result = new HashMap<String,Object>();
		// 先判断操作系统
		String osVersion = getOsVersion();
		SSHResultBO cpuResult = null;
		
		//network REV 单位G
		if(osVersion.equalsIgnoreCase(SSHCmdEnum.AIX)){
			cpuResult = longConnectionWithCmd(AIXSSHConstast.AIX_NETWORK_REV_SH);
		}else if(osVersion.equalsIgnoreCase(SSHCmdEnum.Linux)){
			cpuResult = longConnectionWithCmd(LinuxSSHConstast.LINUX_NETWORK_REV_SH);
		}
		result.put("rev", cpuResult.getResult().trim());
		
		//network SEND 单位G
		cpuResult = null;
		if(osVersion.equalsIgnoreCase(SSHCmdEnum.AIX)){
			cpuResult = longConnectionWithCmd(AIXSSHConstast.AIX_NETWORK_SEND_SH);
		}else if(osVersion.equalsIgnoreCase(SSHCmdEnum.Linux)){
			cpuResult = longConnectionWithCmd(LinuxSSHConstast.LINUX_NETWORK_SEND_SH);
		}
		result.put("send", cpuResult.getResult().trim());
		
		return result;
	}
	
	

	
}


