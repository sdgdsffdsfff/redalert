/**   
* @Title: DistCenterConfigAdapterManagerImpl.java 
* @Package com.tyyd.ywpt.distcenter.zk.data.impl 
* @Description:  
* @author wangyu   
* @date 2015-1-7 下午3:14:28 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.distcenter.zk.data.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.tyyd.ywpt.dao.distcenter.DistCenterDAO;
import com.tyyd.ywpt.dao.distcenter.dataobject.DistCenterDomain;
import com.tyyd.ywpt.distcenter.zk.data.DistCenterConfigAdapterManager;
import com.tyyd.ywpt.distcenter.zk.data.dataobject.StandardZookeeperDataTypeBO;

/**
 * @author wangyu
 *
 */
public class DistCenterConfigAdapterManagerImpl implements
		DistCenterConfigAdapterManager {

	@Resource
	private DistCenterDAO distCenterDAO;
	
	@Override
	public List<StandardZookeeperDataTypeBO> listDistConfigAdapter() {
		List<StandardZookeeperDataTypeBO> result = new ArrayList<StandardZookeeperDataTypeBO>();
		List<DistCenterDomain> list = distCenterDAO.listAllConfig();
		if(CollectionUtils.isNotEmpty(list)){
			for(DistCenterDomain domain : list){
				String keyValue = domain.getKeyValue();
				Object value = null;
				
				//键值类型转化
				if(StringUtils.isNotBlank(domain.getKeyType())){
					if("int".equals(domain.getKeyType())){
						value = Integer.valueOf(keyValue);
					}else if("float".equals(domain.getKeyType())){
						value = Float.valueOf(keyValue);
					}else if("double".equals(domain.getKeyType())){
						value = Double.valueOf(keyValue);
					}else if("string".equals(domain.getKeyType())){
						value = keyValue;
					}else if("bool".equals(domain.getKeyType()) 
							|| "boolean".equals(domain.getKeyType())){
						value = Boolean.valueOf(keyValue);
					}
				}
				
				StandardZookeeperDataTypeBO bo = new StandardZookeeperDataTypeBO();
				bo.setKey(domain.getKeyName());
				bo.setValue(value);
				bo.setColumn(domain.getKeyColumn());
				result.add(bo);
			}
		}
		
		
		return result;
	}

}
