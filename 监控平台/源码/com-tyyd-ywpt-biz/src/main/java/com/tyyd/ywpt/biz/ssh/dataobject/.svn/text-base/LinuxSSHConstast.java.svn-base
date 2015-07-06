/**   
 * @Title: LinuxSSHConstast.java 
 * @Package com.tyyd.ywpt.biz.ssh.dataobject 
 * @Description:  
 * @author wangyu   
 * @date 2014-6-30 下午3:44:31 
 * @CopyRight 天翼阅读
 * @version V1.0   
 */
package com.tyyd.ywpt.biz.ssh.dataobject;

/**
 * @author wangyu Linux主机层的指标定义
 */
public class LinuxSSHConstast {

	// 获取CPU信息,Linux版
	public final static String LINUX_CPU_CMD = "sar|awk '{a[NR]=$0} END{print a[NR=FNR-1]}'|awk '{ print $1,$3,$5,$6,$8}'";

	// total,used,free,buffers,cached
	public final static String LINUX_MEMORY_SH = "free -m |grep 'Mem'|awk '{print $2,$3,$4,$6,$7}'";

	// 虚拟内存的使用 (total,used,free)
	public final static String LINUX_MEMORY_SWAP_SH = "free -m|grep 'Swap'|awk '{print $2,$3,$4}'";

	// 虚拟内存的in，out
	public final static String LINUX_MEMORY_SWAP_IN_OUT_SH = "vmstat -SM|awk '{ if (NR==3) print $7,$8}";

	// 磁盘(Used Available Capacity Mounted)
	public final static String LINUX_DISK_SH = "df -Pm | grep /dev |awk '{print $3,$4,$5,$6}'| sed 's/%//g'";

	// 接受的网络包流量,G为单位
	public final static String LINUX_NETWORK_REV_SH = "ifconfig bond0|grep bytes|awk '{print $2}'|sed 's/bytes://g'|awk '{print $1/1024/1024}'";

	// 接受的网络包流量,G为单位
	public final static String LINUX_NETWORK_SEND_SH = "ifconfig bond0|grep bytes|awk '{print $6}'|sed 's/bytes://g'|awk '{print $1/1024/1024}'";

}
