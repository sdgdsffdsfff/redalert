/**   
* @Title: CreateScript.java 
* @Package com.tyyd.ywpt.biz.encrypt 
* @Description:  
* @author wangyu   
* @date 2014-11-21 下午5:39:25 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.encrypt;

/**
 * @author wangyu
 *
 */
public class CreateScript {

	public static void main(String[] args) {
		
		String str1 = " change message message varchar(1024) comment '短信信息内容';";
		String str2 = " change calls calls varchar(64) comment '接口调用方应用名称';";
		String str3 = " change type `type` varchar(2) comment '部门类型：1--天阅  2--听书  3--政企';";
		String str4 = " add gmt_submit timestamp null default null comment '短信提交至网关时间';";
		String prefix = "alter table t_sms_message";
		
		int max = 128;
		String[] array1 = new String[max];
		String[] array2 = new String[max];
		String[] array3 = new String[max];
		String[] array4 = new String[max];
		
		for(int i=0;i<max;i++){
			String script1 = prefix+i+str1;
			String script2= prefix+i+str2;
			String script3 = prefix+i+str3;
			String script4 = prefix+i+str4;
			
			array1[i] = script1;
			array2[i] = script2;
			array3[i] = script3;
			array4[i] = script4;
		}
		
//		printArray(array1);
//		
//		printArray(array2);
		
		printArray(array3);
		
//		printArray(array4);
		
	}
	
	
	public static void printArray(String[] array){
		if(array == null || array.length == 0){
			return;
		}
		
		for(int i=0;i<array.length;i++){
			System.out.println(array[i]);
		}
		
		System.out.println("\n\n\n\n");
	}
}
