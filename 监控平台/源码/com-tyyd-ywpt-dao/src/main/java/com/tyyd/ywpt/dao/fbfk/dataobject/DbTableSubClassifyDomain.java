/**   
* @Title: DbTableSubClassifyDomain.java 
* @Package com.tyyd.ywpt.dao.fbfk.dataobject 
* @Description:  
* @author wangyu   
* @date 2015-4-15 上午10:08:37 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.fbfk.dataobject;

import java.util.Date;

/**
 * @author wangyu
 *
 */
public class DbTableSubClassifyDomain {

	
	
	/**
	 * 编码
	 */
	private Integer dbTableSubClassifyId;
	
	
	/**
	 * 名称
	 */
	private String name;
	
	
	/**
	 * 总的数据库数
	 */
	private Integer totalDbSize;
	
	
	/**
	 * 总的表数
	 */
	private Integer totalDbTableSize;
	
	/**
	 * 总的实例数
	 */
	private Integer totalDbInstanceSize;
	
	/**
	 * 每个实例下的数据库数
	 */
	private Integer everyDbInstanceSize;
	
	/**
	 * 每个数据库下的表数
	 */
	private Integer everyDbTableSize;
	
	/**
	 * 数据库从几开始
	 */
	private Integer dbStartIndex;
	
	
	/**
	 * 表从几开始
	 */
	private Integer tableStartIndex;
	
	/**
	 * 状态 0:正常,1:停用
	 */
	private Integer status;
	

	/**
	 * 创建时间
	 */
	private Date gmtCreated;
	
	
	/**
	 * 修改时间
	 */
	private Date gmtModifed;


	/**
	 * @return the dbTableSubClassifyId
	 */
	public Integer getDbTableSubClassifyId() {
		return dbTableSubClassifyId;
	}


	/**
	 * @param dbTableSubClassifyId the dbTableSubClassifyId to set
	 */
	public void setDbTableSubClassifyId(Integer dbTableSubClassifyId) {
		this.dbTableSubClassifyId = dbTableSubClassifyId;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the totalDbSize
	 */
	public Integer getTotalDbSize() {
		return totalDbSize;
	}


	/**
	 * @param totalDbSize the totalDbSize to set
	 */
	public void setTotalDbSize(Integer totalDbSize) {
		this.totalDbSize = totalDbSize;
	}


	/**
	 * @return the totalDbTableSize
	 */
	public Integer getTotalDbTableSize() {
		return totalDbTableSize;
	}


	/**
	 * @param totalDbTableSize the totalDbTableSize to set
	 */
	public void setTotalDbTableSize(Integer totalDbTableSize) {
		this.totalDbTableSize = totalDbTableSize;
	}


	/**
	 * @return the everyDbTableSize
	 */
	public Integer getEveryDbTableSize() {
		return everyDbTableSize;
	}


	/**
	 * @param everyDbTableSize the everyDbTableSize to set
	 */
	public void setEveryDbTableSize(Integer everyDbTableSize) {
		this.everyDbTableSize = everyDbTableSize;
	}


	/**
	 * @return the dbStartIndex
	 */
	public Integer getDbStartIndex() {
		return dbStartIndex;
	}


	/**
	 * @param dbStartIndex the dbStartIndex to set
	 */
	public void setDbStartIndex(Integer dbStartIndex) {
		this.dbStartIndex = dbStartIndex;
	}


	/**
	 * @return the tableStartIndex
	 */
	public Integer getTableStartIndex() {
		return tableStartIndex;
	}


	/**
	 * @param tableStartIndex the tableStartIndex to set
	 */
	public void setTableStartIndex(Integer tableStartIndex) {
		this.tableStartIndex = tableStartIndex;
	}


	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}


	/**
	 * @return the gmtCreated
	 */
	public Date getGmtCreated() {
		return gmtCreated;
	}


	/**
	 * @param gmtCreated the gmtCreated to set
	 */
	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}


	/**
	 * @return the gmtModifed
	 */
	public Date getGmtModifed() {
		return gmtModifed;
	}


	/**
	 * @param gmtModifed the gmtModifed to set
	 */
	public void setGmtModifed(Date gmtModifed) {
		this.gmtModifed = gmtModifed;
	}


	/**
	 * @return the totalDbInstanceSize
	 */
	public Integer getTotalDbInstanceSize() {
		return totalDbInstanceSize;
	}


	/**
	 * @param totalDbInstanceSize the totalDbInstanceSize to set
	 */
	public void setTotalDbInstanceSize(Integer totalDbInstanceSize) {
		this.totalDbInstanceSize = totalDbInstanceSize;
	}


	/**
	 * @return the everyDbInstanceSize
	 */
	public Integer getEveryDbInstanceSize() {
		return everyDbInstanceSize;
	}


	/**
	 * @param everyDbInstanceSize the everyDbInstanceSize to set
	 */
	public void setEveryDbInstanceSize(Integer everyDbInstanceSize) {
		this.everyDbInstanceSize = everyDbInstanceSize;
	}
	
	
	
	
	
}
