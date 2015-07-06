/**   
* @Title: PlaceHolderUtils.java 
* @Package com.tyyd.ywpt.tools.sysproperties 
* @Description:  
* @author wangyu   
* @date 2014-7-17 下午1:32:37 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.tools.sysproperties;

import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wangyu
 *
 */
public class PlaceHolderUtils {


	private static final Pattern PLACEHOLDER_PATTERN = Pattern.compile("[$][{][^{}]+[}]");

	private static final Pattern PLACEHOLDER_VALUEKEY_PATTERN = Pattern.compile("[^${}]+");

	/**
	 * 
	 * @param properties
	 */
	public static void convertProperties(Properties properties) {

		for (Entry<Object, Object> entry : properties.entrySet()) {
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();

			value = resolveValue(value, properties);
			properties.put(key, value);
		}

	}

	/**
	 * 
	 * @author:hongxia.huhx
	 * @date:2010-5-21
	 * @param value
	 * @param properties
	 * @return
	 */
	public static String resolveValue(String value, Properties properties) {

		Matcher matcherOne = PLACEHOLDER_PATTERN.matcher(value);

		while (matcherOne.find()) {
			String group = matcherOne.group();

			Matcher valueKeyMatcher = PLACEHOLDER_VALUEKEY_PATTERN.matcher(group);
			if (valueKeyMatcher.find()) {
				String valueKey = valueKeyMatcher.group();
				value = value.replace(group, (String) properties.get(valueKey));
			}
		}

		Matcher matcherTwo = PLACEHOLDER_PATTERN.matcher(value);
		if (matcherTwo.find()) {
			value = resolveValue(value, properties);
		}

		return value;

	}

	
}
