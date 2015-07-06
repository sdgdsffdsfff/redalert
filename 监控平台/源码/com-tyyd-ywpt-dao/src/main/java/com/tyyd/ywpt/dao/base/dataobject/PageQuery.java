/**   
* @Title: PageQuery.java 
* @Package com.tyyd.ywpt.dao.base 
* @Description:  
* @author wangyu   
* @date 2014-6-18 下午1:02:44 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.base.dataobject;


/**
 * @author wangyu
 * 所有的数据都从1开始，包括分页数
 */
public class PageQuery<T> {

	/**
	 * 记录从1开始
	 */
	private final static Integer FIRST_ROW_INDEX = 0;
	
	/**
	 * 分页从1开始
	 */
	private final static Integer FIRST_PAGE_INDEX = 1;
	
	/**
	 * 开始
	 */
	private Integer startRowNumber;
	
	/**
	 * 结束
	 */
	private Integer endRowNumber;
	
	/**
	 * 总计
	 */
	private Integer totalRows;
	
	/**
	 * 当前页
	 */
	private Integer currentPage;
	
	/**
	 * 总页数
	 */
	private Integer totalPage;
	
	/**
	 * 数据集
	 */
	private T records ;
	
	
	/**
	 * 每页数
	 */
	private Integer pageSize = 10;

	/**
	 * @return the startRowNumber
	 */
	public Integer getStartRowNumber() {
		if(startRowNumber == null || startRowNumber.intValue()<0){
			return FIRST_ROW_INDEX;
		}
		return startRowNumber;
	}

	/**
	 * @param startRowNumber the startRowNumber to set
	 */
	public void setStartRowNumber(Integer startRowNumber) {
		this.startRowNumber = startRowNumber;
	}

	/**
	 * @return the endRowNumber
	 */
	public Integer getEndRowNumber() {
		if(endRowNumber != null && endRowNumber.intValue() > totalRows.intValue()){
			endRowNumber = totalRows;
		}
		return endRowNumber;
	}

	/**
	 * @param endRowNumber the endRowNumber to set
	 */
	public void setEndRowNumber(Integer endRowNumber) {
		this.endRowNumber = endRowNumber;
	}

	/**
	 * @return the totalRows
	 */
	public Integer getTotalRows() {
		return totalRows;
	}

	/**
	 * @param totalRows the totalRows to set
	 */
	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}

	/**
	 * @return the currentPage
	 */
	public Integer getCurrentPage() {
		if(currentPage== null || currentPage.intValue()<=0){
			currentPage = FIRST_PAGE_INDEX;
		}
		
		Integer totalPage = getTotalPage();
		if(currentPage >= totalPage){
			currentPage = totalPage;
		}
		
		return currentPage;
	}

	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the totalPage
	 */
	public Integer getTotalPage() {
		totalPage = totalRows/pageSize;
		if(totalRows%pageSize != 0){
			totalPage = totalPage + 1;
		}
		return totalPage;
	}

	/**
	 * @return the records
	 */
	public T getRecords() {
		return records;
	}

	/**
	 * @param records the records to set
	 */
	public void setRecords(T records) {
		this.records = records;
	}

	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	} 

	
	
	
}
