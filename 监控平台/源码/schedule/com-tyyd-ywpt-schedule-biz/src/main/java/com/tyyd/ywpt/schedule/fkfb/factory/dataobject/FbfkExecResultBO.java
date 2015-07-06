/**   
* @Title: FbfkExecResultBO.java 
* @Package com.tyyd.ywpt.schedule.fkfb.factory.dataobject 
* @Description:  
* @author wangyu   
* @date 2015-4-16 下午2:59:33 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.fkfb.factory.dataobject;

/**
 * @author wangyu
 *
 */
public class FbfkExecResultBO {

	private boolean isSuccess;
	
	private String result;

	/**
	 * @return the isSuccess
	 */
	public boolean isSuccess() {
		return isSuccess;
	}

	/**
	 * @param isSuccess the isSuccess to set
	 */
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}
	
	
}
