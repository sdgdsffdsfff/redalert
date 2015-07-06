/**   
* @Title: FbfkDdlTypeEnum.java 
* @Package com.tyyd.ywpt.schedule.fkfb.build 
* @Description:  
* @author wangyu   
* @date 2015-4-17 上午11:19:06 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.fkfb.build.jdbc;

/**
 * @author wangyu
 *
 */
public enum FbfkDdlTypeEnum {

	CreateTable(1,"建表"),
	AlertTableFiled(2,"修改字段"),
	CreateIndex(3,"创建索引、删除索引"),
	AlertComments(4,"修改注释"),
	AddFileds(5,"新增字段");
	
	
	private Integer ddlType;
	private String remark;
	
	
	/**
	 * @return the ddlType
	 */
	public Integer getDdlType() {
		return ddlType;
	}
	/**
	 * @param ddlType the ddlType to set
	 */
	public void setDdlType(Integer ddlType) {
		this.ddlType = ddlType;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @param ddlType
	 * @param remark
	 */
	private FbfkDdlTypeEnum(Integer ddlType, String remark) {
		this.ddlType = ddlType;
		this.remark = remark;
	}
	
	
}
