/**   
 * @Title: IpAddressUtils.java 
 * @Package com.tyyd.ywpt.biz.util 
 * @Description:  
 * @author wangyu   
 * @date 2014-11-26 下午1:12:10 
 * @CopyRight 天翼阅读
 * @version V1.0   
 */
package com.tyyd.ywpt.biz.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import org.apache.commons.lang.StringUtils;

/**
 * @author wangyu
 * 
 */
public class IpAddressUtils {

	/**
	 * 获取本地IP地址
	 */
	public static String getLocalIp() {
		String ip = "";
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return ip;
	}

	public static String[] getAllIp() {

		StringBuilder IFCONFIG = new StringBuilder();
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()
							&& !inetAddress.isLinkLocalAddress()
							&& inetAddress.isSiteLocalAddress()) {
						IFCONFIG.append(inetAddress.getHostAddress().toString()
								+ ":");
					}
				}
			}
		} catch (SocketException ex) {
			ex.printStackTrace();
		}
		String[] result = StringUtils.split(IFCONFIG.toString(), ":");
		return result;
	}

	public static void main(String[] args) {
		String result[] = getAllIp();
		for(String bo : result){
			System.out.println(bo.toString());
		}
	}
}
