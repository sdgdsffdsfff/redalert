/**   
* @Title: StandardZookeeperDataTypeBO.java 
* @Package com.tyyd.ywpt.distcenter.zk.data.dataobject 
* @Description:  
* @author wangyu   
* @date 2015-1-8 下午5:35:36 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.distcenter.zk.data.dataobject;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author wangyu
 *
 */
public class StandardZookeeperDataTypeBO {

	private String key;
	
	private Object value;
	
	/**
	 * 栏目
	 */
	private String column;

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * @return the column
	 */
	public String getColumn() {
		return column;
	}

	/**
	 * @param column the column to set
	 */
	public void setColumn(String column) {
		this.column = column;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	
}
