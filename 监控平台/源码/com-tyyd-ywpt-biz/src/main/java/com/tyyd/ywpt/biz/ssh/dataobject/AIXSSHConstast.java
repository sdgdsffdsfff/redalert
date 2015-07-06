/**   
 * @Title: AIXSSHConstast.java 
 * @Package com.tyyd.ywpt.biz.ssh.dataobject 
 * @Description:  
 * @author wangyu   
 * @date 2014-6-30 下午4:20:13 
 * @CopyRight 天翼阅读
 * @version V1.0   
 */
package com.tyyd.ywpt.biz.ssh.dataobject;

/**
 * @author wangyu
 * 
 */
public class AIXSSHConstast {

	// 获取CPU信息,AIX版
	public final static String AIX_CPU_CMD = "sar|awk '{a[NR]=$0} END{print a[NR=FNR-2]}'|awk '{ print $1,$2,$3,$4,$5}'";

	// 内存 total,used,free
	public final static String AIX_MEMORY_SH = "svmon -G -O unit=MB|grep memory|awk '{print $2,$3,$4}'";

	// 虚拟内存 total,used,free
	public final static String AIX_MEMORY_SWAP_SH = "svmon -G -O unit=MB|grep \"pg space\"|awk '{print $3,$4,$3-$4}'";

	// 磁盘(Used Available Capacity Mounted)
	public final static String AIX_DISK_SH = "df -Pm | grep /dev |awk '{print $3,$4,$5,$6}'| sed 's/%//g'";

	// 接受的网络包流量,G为单位
	public final static String AIX_NETWORK_REV_SH = "netstat -D|grep en_if7|awk '{print $2/1024/1024}'";

	// 接受的网络包流量,G为单位
	public final static String AIX_NETWORK_SEND_SH = "netstat -D|grep en_if7|awk '{print $3/1024/1024}'";

}
