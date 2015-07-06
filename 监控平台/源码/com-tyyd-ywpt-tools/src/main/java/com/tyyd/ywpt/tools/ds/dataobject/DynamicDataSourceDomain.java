/**   
* @Title: DynamicDataSourceDomain.java 
* @Package com.tyyd.ywpt.tools.ds.dataobject 
* @Description:  
* @author wangyu   
* @date 2014-7-2 上午11:35:55 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.tools.ds.dataobject;

import org.apache.commons.lang.StringUtils;

/**
 * @author wangyu
 *
 */
public class DynamicDataSourceDomain {

	private String hostId;
	
	private String dbId;
	
	/**
	 * ipaddr
	 */
	private String ipAddr;
	
	/**
	 * 数据库类型：oracle mysql
	 */
	private String dbType;
	
	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 密码
	 */
	private String passwd;
	
	
	/**
	 * 实例id oracle有用
	 */
	private String instanceId;
	
	
	/**
	 * 端口
	 */
	private String port;
	
	
	/**
	 * rac主机配置
	 */
	private String[] racIp;
	
	
	/**
	 * 端口
	 */
	private String[] racPort;


	/**
	 * @return the ipAddr
	 */
	public String getIpAddr() {
		return ipAddr;
	}

	
	

	/**
	 * @return the racIp
	 */
	public String[] getRacIp() {
		return racIp;
	}




	/**
	 * @param racIp the racIp to set
	 */
	public void setRacIp(String[] racIp) {
		this.racIp = racIp;
	}




	/**
	 * @return the racPort
	 */
	public String[] getRacPort() {
		return racPort;
	}




	/**
	 * @return the hostId
	 */
	public String getHostId() {
		return hostId;
	}




	/**
	 * @param hostId the hostId to set
	 */
	public void setHostId(String hostId) {
		this.hostId = hostId;
	}




	/**
	 * @return the dbId
	 */
	public String getDbId() {
		return dbId;
	}




	/**
	 * @param dbId the dbId to set
	 */
	public void setDbId(String dbId) {
		this.dbId = dbId;
	}




	/**
	 * @param racPort the racPort to set
	 */
	public void setRacPort(String[] racPort) {
		this.racPort = racPort;
	}




	/**
	 * @param ipAddr the ipAddr to set
	 */
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}


	/**
	 * @return the dbType
	 */
	public String getDbType() {
		return dbType;
	}


	/**
	 * @param dbType the dbType to set
	 */
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}


	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}


	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}


	/**
	 * @return the passwd
	 */
	public String getPasswd() {
		return passwd;
	}


	/**
	 * @param passwd the passwd to set
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}


	/**
	 * @return the instanceId
	 */
	public String getInstanceId() {
		return instanceId;
	}


	/**
	 * @param instanceId the instanceId to set
	 */
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}


	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}


	/**
	 * @param port the port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}
	
	
	public enum dbEnum{
		
		Oracle("oracle"),
		MySQL("mysql");
		
		/**
		 * @param database
		 */
		private dbEnum(String database) {
			this.database = database;
		}

		private String database;

		/**
		 * @return the database
		 */
		public String getDatabase() {
			return database;
		}

		
	}
	
	public String toCreateConnectUrl(){
		StringBuffer sb = new StringBuffer();
		if(dbType.equalsIgnoreCase(dbEnum.MySQL.getDatabase())){
			//jdbc:mysql://<host>:<port>/<database_name>?property1=value1&property2=value2
			String serviceName = StringUtils.isBlank(instanceId)?"information_schema":instanceId;
			sb.append("jdbc:mysql://"+ipAddr+":"+port+"/"+serviceName+"?user="+userName+"&password="+passwd);
			sb.append("&useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false");
		}else if(dbType.equalsIgnoreCase(dbEnum.Oracle.getDatabase())){
			//rac版本
			//jdbc:oracle:thin:@(description=(address_list= (address=(host=192.18.137.231) (protocol=tcp)(port=1521))(address=(host=192.18.137.230)(protocol=tcp) (port=1521)) (load_balance=yes)(failover=yes))(connect_data=(service_name= slrac.beta.com)))
			//普通oracle
			//jdbc:oracle:thin:username/password@x.x.x.1:1521:SID  jdbc:oracle:thin:@<host>:<port>:<SID>
			
			if(racIp != null && racPort != null && racIp.length == racPort.length){
				sb.append("jdbc:oracle:thin:@(description=");
				for(int i=0;i<racIp.length;i++){
					sb.append("(address=(host="+racIp[i]+")(protocol=tcp) (port="+racPort[i]+")) ");
				}
				sb.append("(load_balance=yes)(failover=yes))(connect_data=(service_name= "+instanceId+")))");
			}else{
				sb.append("jdbc:oracle:thin:@"+ipAddr+":"+port+":"+instanceId);
			}
		}
		return sb.toString();
	}
	
}
