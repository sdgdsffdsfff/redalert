/**   
* @Title: UserDomain.java 
* @Package com.tyyd.ywpt.dao.admin.privis.dataobject 
* @Description:  
* @author wangyu   
* @date 2014-6-16 上午11:14:35 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.dao.admin.user.dataobject;

import java.util.Date;


/**
 * @author wangyu
 *
 */
public class UserDomain {

	/**
	 * 用户ID
	 */
	private String id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 139邮箱
     */
    private String mobileMail;
    
    /**
     * 手机
     */
    private String phone;

    /**
     * 状态 0:正常,1:删除
     */
    private String status;

    /**
     * 密码
     */
    private String passwd;

    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 修改时间
     */
    private Date gmtModifed;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileMail() {
		return mobileMail;
	}

	public void setMobileMail(String mobileMail) {
		this.mobileMail = mobileMail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Date getGmtCreated() {
		return gmtCreated;
	}

	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

	public Date getGmtModifed() {
		return gmtModifed;
	}

	public void setGmtModifed(Date gmtModifed) {
		this.gmtModifed = gmtModifed;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
    
    
    
    
}
