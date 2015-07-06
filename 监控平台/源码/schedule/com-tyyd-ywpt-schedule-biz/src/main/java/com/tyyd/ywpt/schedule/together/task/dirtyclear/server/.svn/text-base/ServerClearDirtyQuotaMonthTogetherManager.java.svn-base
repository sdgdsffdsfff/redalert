/**   
* @Title: MySQLClearDirtyQuotaDayTogetherManager.java 
* @Package com.tyyd.ywpt.schedule.together.task.dirtyclear.mysql 
* @Description:  
* @author wangyu   
* @date 2014-9-24 下午3:08:33 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.together.task.dirtyclear.server;

import com.tyyd.ywpt.biz.dict.SysTypeEnum;
import com.tyyd.ywpt.biz.dict.TogetherStatTypeEnum;
import com.tyyd.ywpt.schedule.together.impl.AbstractClearDirtyQuotaTogetherManager;

/**
 * @author wangyu
 *
 */
public class ServerClearDirtyQuotaMonthTogetherManager extends AbstractClearDirtyQuotaTogetherManager{

	@Override
	public void clearQuotaDirtyData() {
		doTask(SysTypeEnum.server.getVal(),TogetherStatTypeEnum.MONTH.getVal());
	}

}
