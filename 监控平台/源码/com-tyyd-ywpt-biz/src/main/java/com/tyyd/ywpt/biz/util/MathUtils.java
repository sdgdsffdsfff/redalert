/**   
* @Title: MathUtils.java 
* @Package com.tyyd.ywpt.biz.util 
* @Description:  
* @author wangyu   
* @date 2015-6-1 下午6:07:28 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.util;

import java.math.BigDecimal;

/**
 * @author wangyu
 *
 */
public class MathUtils {

	public static float round(float value,int digits){
		BigDecimal bg = new BigDecimal(value);  
        float f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue(); 
        return f1;
	}
	
	public static void main(String[] args) {
		float f = MathUtils.round(1233.2225f, 2);
		System.out.println(f);
	}
}
