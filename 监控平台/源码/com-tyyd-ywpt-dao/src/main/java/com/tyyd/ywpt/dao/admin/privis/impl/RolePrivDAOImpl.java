/**   
* @Title: RolePrivDAOImpl.java 
* @Package com.tyyd.ywpt.dao.admin.privis.impl 
* @Description:  
* @author wangyu   
* @date 2014-6-18 上午11:08:26 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.admin.privis.impl;

import java.util.List;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.admin.privis.RolePrivDAO;
import com.tyyd.ywpt.dao.admin.privis.dataobject.RolePrivDomain;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class RolePrivDAOImpl extends TyydBaseDAO implements RolePrivDAO {

	private static final String CONTEXT_SPACE = "com.tyyd.ywpt.dao.admin.privis.dataobject.RolePrivDomain.";
	
	@Override
	public List<RolePrivDomain> queryPrivByRoleId(String roleId) {
		List<RolePrivDomain> list = getSqlSessionTemplate().selectList(CONTEXT_SPACE+"query_priv_by_role", roleId);
		return list;
	}

	@Override
	//@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void addPrivsRole(RolePrivDomain rolePriv) {
		getSqlSessionTemplate().insert(CONTEXT_SPACE+"add_role_priv", rolePriv);
	}

	@Override
	public void delPrivsRole(String roleId) {
		getSqlSessionTemplate().delete(CONTEXT_SPACE+".del_privs_by_role", roleId);
	}

}
