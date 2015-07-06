/**   
* @Title: DynamicDsContextHolder.java 
* @Package com.tyyd.ywpt.biz.ds.impl 
* @Description:  
* @author wangyu   
* @date 2014-7-2 上午10:11:18 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.tools.ds.impl;

/**
 * @author wangyu
 *
 */
public class DynamicDataSourceContextHolder {

	private static final  ThreadLocal<String> thread = new ThreadLocal<String>(); 
	
	/**
	 * 设置数据源的BeanId
	 * @param beanId
	 */
	public static void setDataSource(String beanId){
		thread.set(beanId);
	}
	
	/**
	 * 获取数据源的BeanId
	 * @return
	 */
	public static String getDataSource(){
		return thread.get();
	}

	/**
	 * 删除数据源
	 */
	public static void removeDataSource(){
		thread.remove();
	}
}
