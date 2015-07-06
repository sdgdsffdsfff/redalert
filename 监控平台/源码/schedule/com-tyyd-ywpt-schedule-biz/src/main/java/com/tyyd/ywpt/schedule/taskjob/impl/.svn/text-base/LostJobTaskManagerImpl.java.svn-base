/**   
* @Title: LostJobTaskManagerImpl.java 
* @Package com.tyyd.ywpt.schedule.taskjob.impl 
* @Description:  
* @author wangyu   
* @date 2014-11-25 下午2:00:38 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.taskjob.impl;

import java.net.MalformedURLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.caucho.hessian.client.HessianProxyFactory;
import com.tyyd.ywpt.biz.dict.SysTypeEnum;
import com.tyyd.ywpt.biz.quartzschedule.TaskRefreshRegeditManager;
import com.tyyd.ywpt.biz.quartzschedule.dataobject.TaskRefreshResponseDTO;
import com.tyyd.ywpt.biz.util.IpAddressUtils;
import com.tyyd.ywpt.dao.configration.db.DbConfigDAO;
import com.tyyd.ywpt.dao.configration.db.dataobject.DbConfigDomain;
import com.tyyd.ywpt.dao.configration.host.HostConfigDAO;
import com.tyyd.ywpt.dao.configration.host.dataobject.HostConfigDomain;
import com.tyyd.ywpt.dao.configration.schedule.DeamonScheduleconfigDAO;
import com.tyyd.ywpt.dao.configration.schedule.LostTaskJobDAO;
import com.tyyd.ywpt.dao.configration.schedule.dataobject.DeamonScheduleconfigDomain;
import com.tyyd.ywpt.dao.configration.schedule.dataobject.LostTaskJobDomain;
import com.tyyd.ywpt.dao.server.ServerRegDAO;
import com.tyyd.ywpt.dao.server.dataobject.ServerRegDomain;
import com.tyyd.ywpt.schedule.taskjob.LostJobTaskManager;
import com.tyyd.ywpt.tools.email.EmailSendService;
import com.tyyd.ywpt.tools.email.dataobject.MailRecvMessage;
import com.tyyd.ywpt.tools.encrypt.MD5Utils;

/**
 * @author wangyu
 *
 */
public class LostJobTaskManagerImpl implements LostJobTaskManager {

	
	public static final Logger LOGGER = Logger.getLogger(LostJobTaskManagerImpl.class);
	
	private final static String mails[] = {"zf_zhouf@163.com","yangcj@yutian.com.cn","chenronghua@yutian.com.cn","newshake@163.com"};

	/**
	 * hessian地址
	 */
	private final static String HESSIAN_PATTERN = "http://%s:%s/remote/regeditServer";
	
	
	@Resource
	private LostTaskJobDAO lostTaskJobDAO;
	
	@Resource
	private EmailSendService emailSendService;
	
	@Resource
	private DeamonScheduleconfigDAO deamonScheduleconfigDAO;
	
	@Resource
	private HostConfigDAO hostConfigDAO;
	
	@Resource
	private DbConfigDAO dbConfigDAO;
	
	@Resource
	private ServerRegDAO serverRegDAO;
	
	
	
	public void collectDeadTask() {
		LOGGER.info("收集挂起任务开始...");
		lostTaskJobDAO.collectLostTaskJob();
		LOGGER.info("收集挂起任务结束...");
	}

	@Override
	public void notifyTask() {
		//获取需要通知的任务
		List<LostTaskJobDomain> list = lostTaskJobDAO.getUnAlertTask();
		LOGGER.info("挂起任务通知开始...");
		if(CollectionUtils.isNotEmpty(list)){
			LOGGER.info("本次挂起任务通知总计"+list.size()+"...");
			for(LostTaskJobDomain domain : list){
				String content = domain.getJobName()+",超过该任务配置的3倍的时间周期未正常运行，该任务目前挂起，预计修复进程会在2分钟内启动";
				
				//发送mail
				MailRecvMessage mailMessage = new MailRecvMessage();
				mailMessage.setRecv(mails);
				mailMessage.setTitle("异常任务通知");
				mailMessage.setContent(content);
				
				//emailSendService.sendMail(mailMessage);
				
				
				//关闭任务
				lostTaskJobDAO.closeNotify(domain.getId());
				
			}
		}
		LOGGER.info("挂起任务通知结束...");
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void regTask() {
		//获取任务
		List<LostTaskJobDomain> list = lostTaskJobDAO.getUnRegTask();
		LOGGER.info("挂起任务修复开始...");
		if(CollectionUtils.isNotEmpty(list)){
			for(LostTaskJobDomain domain : list){
				//找到对应的serverId
				String deamonId = domain.getDeamonId();
				LOGGER.info("获取deamonId : "+deamonId+",jobName : "+domain.getJobName());
				
				DeamonScheduleconfigDomain deamonScheduleconfigDomain = deamonScheduleconfigDAO.getScheduleconfigById(deamonId);
				//如果任务不存在
				if(deamonScheduleconfigDomain == null ){
					lostTaskJobDAO.closeTaskJob(domain.getId());
					continue;
				}
				
				
				
				String serverId = getServerId(deamonScheduleconfigDomain);
				
				LOGGER.info("获取ServerId : "+serverId);
				
				//获取注册地址
				LOGGER.info("获取注册地址...");
				String hessianUrl = getServerAddress(serverId);
				LOGGER.info(hessianUrl);
				
				//获取md5
				LOGGER.info("获取MD5 key...");
				String[] localAddressMdKeyArray = getLocalIpMdKey();
				
				
				//hessian服务调用
				LOGGER.info("获取Hessian 对象...");
				TaskRefreshRegeditManager hessianServer = getInterface(hessianUrl);
				
				
				//验证握手
				String localAddressMdKey = getCanUseMd5Key(hessianServer,localAddressMdKeyArray);
				LOGGER.info("授权码为"+localAddressMdKey);
				
				//查询返回值
				LOGGER.info("远程调用,修复进程...");
				TaskRefreshResponseDTO dto = regTask(hessianServer,deamonScheduleconfigDomain,localAddressMdKey);
				
				
				//关闭任务退出
				if(dto.isSuccess()){
					LOGGER.info("修复成功 ["+deamonId+"]...");
					lostTaskJobDAO.closeTaskJob(domain.getId());
				}else{
					LOGGER.info("修复失败...,失败原因:"+dto.getRemark());
					lostTaskJobDAO.failTaskJob(domain.getId());
				}
			}
		}
		LOGGER.info("挂起任务修复结束...");
	}
	
	
	
	/**
	 * @param hessianServer
	 * @param localAddressMdKeyArray
	 * @return
	 */
	private String getCanUseMd5Key(TaskRefreshRegeditManager hessianServer,
			String[] localAddressMdKeyArray) {
		
		for(String key : localAddressMdKeyArray){
			if(StringUtils.isNotBlank(key)){
				boolean isSuccess = hessianServer.isPrivsRole(key);
				if(isSuccess){
					return key;
				}
			}
		}
		return StringUtils.EMPTY;
	}

	/**
	 * @return
	 */
	private String[] getLocalIpMdKey() {
		String ips[] = IpAddressUtils.getAllIp();
		if(ArrayUtils.isEmpty(ips)){
			return ArrayUtils.EMPTY_STRING_ARRAY;
		}
		String keys [] = new String[ips.length];
		for(int i=0;i<ips.length;i++){
			LOGGER.info("Ip : "+ips[i]);
			String mdKey = MD5Utils.getMD5Format(ips[i]);
			keys[i] = mdKey;
		}
		
		return keys;
	}

	/**
	 * @param hessianServer
	 * @param deamonScheduleconfigDomain
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private TaskRefreshResponseDTO regTask(TaskRefreshRegeditManager hessianServer,
			DeamonScheduleconfigDomain deamonScheduleconfigDomain,String localAddressMdKey) {
		
		if(hessianServer == null){
			return null;
		}
		
		TaskRefreshResponseDTO dto = hessianServer.regeditTask(
				deamonScheduleconfigDomain.getId(),
				deamonScheduleconfigDomain.getJobName(),
				deamonScheduleconfigDomain.getJobGroup(),
				deamonScheduleconfigDomain.getQuartzConf(),
				deamonScheduleconfigDomain.getBeanId(),
				deamonScheduleconfigDomain.getMonitorId(),
				String.valueOf(deamonScheduleconfigDomain.getConfigType()),
				deamonScheduleconfigDomain.getJobId(),
				deamonScheduleconfigDomain.getId(),
				deamonScheduleconfigDomain.getJobName(),
				deamonScheduleconfigDomain.getJobGroup(), localAddressMdKey);

		return dto;
	}

	/**
	 * 获取hessian接口
	 * @param url
	 * @return
	 */
	private TaskRefreshRegeditManager getInterface(String url){
		HessianProxyFactory factory = new HessianProxyFactory(); 
        TaskRefreshRegeditManager hello = null;
		try {
			hello = (TaskRefreshRegeditManager) factory.create(TaskRefreshRegeditManager.class, url);
		} catch (MalformedURLException e) {
			LOGGER.error("获取hessian服务异常", e);
		}
		
		return hello;
	}
	
	

	/**
	 * @param serverId
	 * @return
	 */
	private String getServerAddress(String serverId) {
		ServerRegDomain serverRegDomain = serverRegDAO.getServerRegByName(serverId);
		
		if(serverRegDomain == null 
				|| StringUtils.isBlank(serverRegDomain.getIp()) 
				|| serverRegDomain.getPort() == null){
			return StringUtils.EMPTY;
		}
		
		String url = String.format(HESSIAN_PATTERN, serverRegDomain.getIp(),serverRegDomain.getPort());
		return url;
	}

	/**
	 * @param deamonScheduleconfigDomain
	 * @return
	 */
	private String getServerId(
			DeamonScheduleconfigDomain deamonScheduleconfigDomain) {
		
		String serverId = "";
		
		if(deamonScheduleconfigDomain == null || deamonScheduleconfigDomain.getConfigType() == null ){
			return StringUtils.EMPTY;
		}
		
		
		try{
		
			if(deamonScheduleconfigDomain.getConfigType().intValue() == SysTypeEnum.server.getVal()){
				HostConfigDomain hostConfigDomain = hostConfigDAO.getHostById(deamonScheduleconfigDomain.getMonitorId());
				serverId = hostConfigDomain.getServerId();
			}else{
				DbConfigDomain dbConfigDomain = dbConfigDAO.getDataBaseConfig(deamonScheduleconfigDomain.getMonitorId());
				serverId = dbConfigDomain.getServerId();
			}
		
		}catch(Exception e){
			LOGGER.error(deamonScheduleconfigDomain.toString(), e);
		}
		return serverId;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void clearHangTask() {
		//获取任务
		List<DeamonScheduleconfigDomain> list = deamonScheduleconfigDAO.listHangScheduleTasks();
		LOGGER.info("清理Hang任务开始...");
		if(CollectionUtils.isNotEmpty(list)){
			for(DeamonScheduleconfigDomain domain : list){
				//找到对应的serverId
				String deamonId = domain.getId();
				DeamonScheduleconfigDomain deamonScheduleconfigDomain = deamonScheduleconfigDAO.getHangScheduleconfigById(deamonId);
				
				if(deamonScheduleconfigDomain == null){
					LOGGER.error(String.format("deamonId=%s,获取hang对象为空，请检查", deamonId));
					continue;
				}
				
				String serverId = getServerId(deamonScheduleconfigDomain);
				
				//获取注册地址
				LOGGER.info("获取注册地址...");
				String hessianUrl = getServerAddress(serverId);
				LOGGER.info(hessianUrl);
				
				//获取md5
				LOGGER.info("获取MD5 key...");
				String[] localAddressMdKeyArray = getLocalIpMdKey();
				
				
				//hessian服务调用
				LOGGER.info("获取Hessian 对象...");
				TaskRefreshRegeditManager hessianServer = getInterface(hessianUrl);
				
				if(hessianServer == null){
					LOGGER.error(String.format("deamonId=%s,addr=%s,获取hessianServer对象为空，请检查", deamonId,hessianUrl));
					continue;
				}
				
				//验证握手
				String localAddressMdKey = getCanUseMd5Key(hessianServer,localAddressMdKeyArray);
				LOGGER.info("授权码为"+localAddressMdKey);
				
				//查询返回值
				LOGGER.info("远程调用,清理进程...");
				TaskRefreshResponseDTO dto = hessianServer.delHangStatusTask(deamonId, localAddressMdKey);
				
				//关闭任务退出
				if(dto.isSuccess()){
					LOGGER.info("清理成功 ["+deamonId+"]...");
				}else{
					LOGGER.info("清理失败...,失败原因:"+dto.getRemark());
				}
			}
		}
		LOGGER.info("清理Hang任务结束...");
	
	}


}
