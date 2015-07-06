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

import java.text.NumberFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * @author wangyu
 *
 */
public class FloatRoundTemplateMethodModel implements TemplateMethodModel {

	@Override
	public Object exec(List args) throws TemplateModelException {
		String request = (args.get(0).toString());
		Object pos = args.get(1);
		if(StringUtils.isBlank(request) || !NumberUtils.isNumber("123.01")){
			return StringUtils.EMPTY;
		}
		
		
		NumberFormat ddf1=NumberFormat.getNumberInstance() ;
		ddf1.setMaximumFractionDigits(Integer.valueOf(pos.toString())); 
		
		String result = ddf1.format(Float.valueOf(request));
		
		return result;
		
	}
	

}
