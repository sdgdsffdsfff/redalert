/**   
* @Title: NoticeLevelTemplateMethodModel.java 
* @Package com.tyyd.ywpt.report.controller.tools 
* @Description:  
* @author wangyu   
* @date 2014-10-28 下午5:01:41 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.controller.tools;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tyyd.ywpt.biz.dict.AlertRecordNoticeLevelEnum;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * @author wangyu
 *
 */
public class NoticeLevelTemplateMethodModel implements TemplateMethodModel {

	@Override
	public Object exec(List args) throws TemplateModelException {
		String request = (args.get(0).toString());
		if(StringUtils.isBlank(request)){
			return StringUtils.EMPTY;
		}
		
		return AlertRecordNoticeLevelEnum.getEnum(Integer.valueOf(request));
		
	}

}
