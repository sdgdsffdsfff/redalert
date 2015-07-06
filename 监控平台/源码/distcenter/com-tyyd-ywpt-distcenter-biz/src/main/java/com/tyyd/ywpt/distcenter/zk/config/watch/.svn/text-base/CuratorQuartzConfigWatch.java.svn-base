/**   
 * @Title: CuratorQuartzConfigWatch.java 
 * @Package com.tyyd.ywpt.distcenter.zk.config.watch 
 * @Description:  
 * @author wangyu   
 * @date 2015-1-12 下午4:39:28 
 * @CopyRight 天翼阅读
 * @version V1.0   
 */
package com.tyyd.ywpt.distcenter.zk.config.watch;

import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher.Event.EventType;

/**
 * @author wangyu
 * 
 */
public class CuratorQuartzConfigWatch implements CuratorWatcher {

	@Override
	public void process(WatchedEvent event) throws Exception {

		if (event.getPath().startsWith("/tyyd/configuration")
				&& EventType.NodeChildrenChanged == event.getType()) {
			
			//把改变的那个path拿到
			
			
		}

	}

}
