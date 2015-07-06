/**   
* @Title: HourServerQuotaTogetherTask.java 
* @Package com.tyyd.ywpt.schedule.together.task.server 
* @Description:  
* @author wangyu   
* @date 2014-9-23 下午2:32:37 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.together.task.server;

import com.tyyd.ywpt.biz.dict.SysTypeEnum;
import com.tyyd.ywpt.biz.dict.TogetherStatTypeEnum;
import com.tyyd.ywpt.schedule.together.impl.AbstractQuotaTogetherTask;

/**
 * @author wangyu
 *
 */
public class ServerQuotaHourTogetherTask extends AbstractQuotaTogetherTask {

	@Override
	public void doQuotaTask() {
		doTask(SysTypeEnum.server.getVal(),TogetherStatTypeEnum.HOUR.getVal());
	}

}
