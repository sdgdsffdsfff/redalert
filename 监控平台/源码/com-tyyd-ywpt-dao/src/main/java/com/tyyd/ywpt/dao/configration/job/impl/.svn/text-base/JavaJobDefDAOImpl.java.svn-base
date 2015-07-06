/**   
* @Title: JavaJobDefDAOImpl.java 
* @Package com.tyyd.ywpt.dao.configration.job.impl 
* @Description:  
* @author wangyu   
* @date 2014-11-18 下午5:44:44 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.configration.job.impl;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.configration.job.JavaJobDefDAO;
import com.tyyd.ywpt.dao.configration.job.dataobject.JavaJobDefDomain;

/**
 * @author wangyu
 *
 */
@SuppressWarnings("rawtypes")
public class JavaJobDefDAOImpl extends TyydBaseDAO implements JavaJobDefDAO {
	
	private final static String context_space = "com.tyyd.ywpt.dao.configration.job.dataobject.JavaJobDefDomain";
	
	
	@Override
	public JavaJobDefDomain getJobDefByJobId(Long jobId) {
		return this.getSqlSessionTemplate().selectOne(context_space+".get_job_def_by_id",jobId);
	}

}
