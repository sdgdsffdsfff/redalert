/**   
 * @Title: NodeEventListener.java 
 * @Package com.tyyd.ywpt.distcenter.zk.config.impl 
 * @Description:  
 * @author wangyu   
 * @date 2015-1-12 下午2:41:07 
 * @CopyRight 天翼阅读
 * @version V1.0   
 */
package com.tyyd.ywpt.distcenter.zk.config.impl;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher.Event.KeeperState;

/**
 * @author wangyu
 * 
 */
public class NodeEventListener implements CuratorListener {

	@Override
	public void eventReceived(CuratorFramework client, CuratorEvent event)
			throws Exception {

		System.out.println(event.toString() + ".......................");
		final WatchedEvent watchedEvent = event.getWatchedEvent();
		if (watchedEvent != null) {
			System.out.println(watchedEvent.getState()
					+ "=======================" + watchedEvent.getType());
			if (watchedEvent.getState() == KeeperState.SyncConnected) {
				switch (watchedEvent.getType()) {
				case NodeChildrenChanged:
					// TODO
					break;
				case NodeDataChanged:
					// TODO
					System.out.println(watchedEvent.getPath());
					break;
				default:
					break;
				}
			}

		}

	}

}
