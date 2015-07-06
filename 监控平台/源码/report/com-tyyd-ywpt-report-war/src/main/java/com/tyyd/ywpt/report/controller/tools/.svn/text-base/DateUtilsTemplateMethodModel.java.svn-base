/**   
* @Title: DateUtilsTemplateMethodModel.java 
* @Package com.tyyd.ywpt.report.controller.tools 
* @Description:  
* @author wangyu   
* @date 2014-10-28 下午4:55:19 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.controller.tools;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tyyd.ywpt.biz.util.DateUtils;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * @author wangyu
 *
 */
public class DateUtilsTemplateMethodModel implements TemplateMethodModel {

	@Override
	public String exec(List args) throws TemplateModelException {
		String gmtCreatedDate = (String)args.get(0);
        if(StringUtils.isBlank(gmtCreatedDate)){
        	return StringUtils.EMPTY;
        }
        
        Date d1 = DateUtils.StringToDate(gmtCreatedDate, "yyyy-MM-dd HH:mm:ss");
        String result = DateUtils.showTime(d1, "yyyy-MM-dd HH:mm:ss");
        return result;
	}

}
