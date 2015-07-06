/**   
* @Title: JavaJobDefDAO.java 
* @Package com.tyyd.ywpt.dao.configration.job 
* @Description:  
* @author wangyu   
* @date 2014-11-18 下午5:42:21 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.configration.job;

import com.tyyd.ywpt.dao.configration.job.dataobject.JavaJobDefDomain;

/**
 * @author wangyu
 *
 */
public interface JavaJobDefDAO {

	public JavaJobDefDomain getJobDefByJobId(Long jobId);
}
