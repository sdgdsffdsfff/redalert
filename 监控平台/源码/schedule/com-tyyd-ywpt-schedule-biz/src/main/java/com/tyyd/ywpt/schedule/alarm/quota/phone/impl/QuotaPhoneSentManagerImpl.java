/**   
* @Title: QuotaPhoneSentManagerImpl.java 
* @Package com.tyyd.ywpt.schedule.alarm.quota.phone.impl 
* @Description:  
* @author wangyu   
* @date 2014-8-28 上午9:35:59 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.alarm.quota.phone.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.tyyd.ywpt.dao.core.quota.QuotaModelDAO;
import com.tyyd.ywpt.dao.core.quota.dataobject.QuotaModelDomain;
import com.tyyd.ywpt.schedule.alarm.quota.phone.QuotaPhoneSentManager;

/**
 * @author wangyu
 *
 */
public class QuotaPhoneSentManagerImpl implements QuotaPhoneSentManager{

	private static Map<String, QuotaModelDomain> requirePhoneCollections = new ConcurrentHashMap<String, QuotaModelDomain>();

	@Resource
	private QuotaModelDAO quotaModelDAO;
	
	@Override
	public void refreshQuotaPhone() {
		List<QuotaModelDomain> list = quotaModelDAO.listPhoneSentQuota();
		if(CollectionUtils.isEmpty(list)){
			return ;
		}
		if(requirePhoneCollections == null){
			requirePhoneCollections = new ConcurrentHashMap<String, QuotaModelDomain>();
		}
		requirePhoneCollections.clear();
		
		for(QuotaModelDomain domain : list){
			requirePhoneCollections.put(domain.getId(), domain);
		}
	}

	@Override
	public boolean requirePhoneSent(String quotaId) {
		if(requirePhoneCollections == null){
			return Boolean.FALSE;
		}
		
		return requirePhoneCollections.containsKey(quotaId);
	}
	
	
}
