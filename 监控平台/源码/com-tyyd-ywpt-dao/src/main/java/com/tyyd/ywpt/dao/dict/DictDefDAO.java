/**   
* @Title: DictDefDAO.java 
* @Package com.tyyd.ywpt.dao.dict 
* @Description:  
* @author wangyu   
* @date 2014-6-22 下午2:49:22 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.dict;

import java.util.List;

import com.tyyd.ywpt.dao.dict.dataobject.DictDefDomain;

/**
 * @author wangyu
 *
 */
public interface DictDefDAO {

	/**
	 * 查询所有的字典
	 * @return
	 */
	public List<DictDefDomain> listDictDef();
	
	
	
}
