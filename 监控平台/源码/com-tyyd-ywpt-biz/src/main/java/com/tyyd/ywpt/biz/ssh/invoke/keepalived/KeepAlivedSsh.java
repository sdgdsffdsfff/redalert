/**   
* @Title: KeepAlivedSsh.java 
* @Package com.tyyd.ywpt.biz.ssh.invoke 
* @Description:  
* @author wangyu   
* @date 2014-11-20 下午3:09:55 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.ssh.invoke.keepalived;

/**
 * @author wangyu
 *
 */
public interface KeepAlivedSsh {

	/**
	 * 监测进程状态
	 * @param dbId
	 */
	public void checkKeepAlivedStatus(String dbId);
	
	
	/**
	 * 监测日志
	 * @param dbId
	 * @param logPath
	 * @param keyWord
	 */
	public void checkKeepAlivedLog(String dbId,String logPath,String keyWord);
}
