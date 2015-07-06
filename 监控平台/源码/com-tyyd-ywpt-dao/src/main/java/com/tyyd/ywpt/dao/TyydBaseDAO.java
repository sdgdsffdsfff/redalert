/**   
* @Title: TyydBaseDAO.java 
* @Package com.tyyd.ywpt.dao 
* @Description:  
* @author wangyu   
* @date 2014-6-18 上午10:48:21 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.tyyd.ywpt.dao.base.dataobject.PageQuery;

/**
 * @author wangyu
 *
 */
public class TyydBaseDAO<T> {

	private SqlSessionTemplate sqlSessionTemplate;

	/**
	 * @return the sqlSessionTemplate
	 */
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	/**
	 * @param sqlSessionTemplate the sqlSessionTemplate to set
	 */
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}  
	
	/**
	 * 查询分页
	 * @param statement
	 * @param pageQuery
	 * @param parameterMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageQuery<T> selectPages(String statement,PageQuery<T> pageQuery,Map<String,Object> parameterMap){
		Integer totalRows = this.getTotalRows(statement, pageQuery, parameterMap);
		Map<String ,Object> parameter = new HashMap<String ,Object>();
		parameter.putAll(parameterMap);
		pageQuery.setTotalRows(totalRows);
		
		Integer startRowNumber = (pageQuery.getCurrentPage() -1) * pageQuery.getPageSize() ;
		if(startRowNumber <= 0){
			startRowNumber = 0;
		}
		//Integer endRowNumber = pageQuery.getCurrentPage() * pageQuery.getPageSize();
		
		parameter.put("startRowNumber", startRowNumber);
		parameter.put("endRowNumber", pageQuery.getPageSize());
		
		List<Object> records = getSqlSessionTemplate().selectList(statement, parameter);
		pageQuery.setRecords((T)records);
		return pageQuery;
	}
	
	/**
	 * 查询总记录数
	 * @param queryKey
	 * @param pageQuery
	 * @param parameterMap
	 * @return
	 */
	private Integer getTotalRows(String statement,PageQuery<T> pageQuery,Map<String,Object> parameterMap){
		Map<String ,Object> parameter = new HashMap<String ,Object>();
		parameter.putAll(parameterMap);
		Integer totalRows = (Integer)getSqlSessionTemplate().selectOne(statement+"_count", parameter);
		return totalRows;
	}
	
}
