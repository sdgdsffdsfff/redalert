/**   
* @Title: AbstractAlarmAnalyseBase.java 
* @Package com.tyyd.ywpt.schedule.alarm 
* @Description:  
* @author wangyu   
* @date 2014-8-5 上午11:18:36 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.schedule.alarm;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import com.tyyd.ywpt.biz.util.DateUtils;
import com.tyyd.ywpt.dao.alert.record.AlertRecordDAO;
import com.tyyd.ywpt.dao.alert.record.MonitorAlertRecordDAO;
import com.tyyd.ywpt.dao.alert.record.dataobject.AlertRecordDomain;
import com.tyyd.ywpt.dao.alert.record.dataobject.MoitorAlertRecordDomain;
import com.tyyd.ywpt.schedule.alarm.dataobject.AlaramContent;
import com.tyyd.ywpt.schedule.alarm.util.AlarmContentObjUtils;
import com.tyyd.ywpt.schedule.alarm.util.AlarmKeyReplaceUtils;
import com.tyyd.ywpt.tools.bean.SpringContextHolder;

/**
 * @author wangyu
 *
 */
public abstract class AbstractAlarmAnalyseBase<T> implements InitializingBean {

	public static final Logger LOGGER = Logger.getLogger(AbstractAlarmAnalyseBase.class);
	
	private String worker;
	
	public void doTask(){
		long start = System.currentTimeMillis();
		LOGGER.info(getWorker() + " 任务开始...");
		
		//获取数据
		LOGGER.info(String.format("%s 获取待处理的任务...", getWorker()));
		List<T> taskList = listUnCompetedTask();
		
		//分析
		LOGGER.info(String.format("%s 开始分析数据...", getWorker()));
		List<AlertRecordDomain> alertDataList = analyData(taskList);
		
		//归并告警
		LOGGER.info(String.format("%s 开始归并告警数据...", getWorker()));
		List<MoitorAlertRecordDomain> monitorAlertList = reduceAlertRecord(taskList);
		
		//处理关闭数据开始
		LOGGER.info(String.format("%s 关闭任务...", getWorker()));
		closeUnCompletedTask(taskList);
		
		//处理
		LOGGER.info(String.format("%s 处理正常任务...", getWorker()));
		closeNormalUnCompletedTask();
		
		//入库
		LOGGER.info(String.format("%s 开始保存数据...", getWorker()));
		saveDataToDB(alertDataList);
		
		//存入归并数据
		LOGGER.info(String.format("%s 开始保存数据...", getWorker()));
		saveMonitorDataToDB(monitorAlertList);
		
		long end = System.currentTimeMillis();
		long milis = (end - start)/1000;
		LOGGER.info(String.format("%s 任务结束...,共花费[%d]秒时间", getWorker(), milis));
	}
	
	



	/**
	 * 处理正常任务
	 */
	public abstract void closeNormalUnCompletedTask() ;
		
	/**
	 * 获取消息内容
	 * @param domain
	 * @return
	 */
	protected AlaramContent getAlaramContent(T domain,AlaramContent patternContent){
		AlarmKeyReplaceUtils utils = initAlarmKeyReplaceUtils(domain);
		AlarmContentObjUtils contentObjUtils = new AlarmContentObjUtils();
		contentObjUtils.setUtils(utils);
		contentObjUtils.setAlaramContent(patternContent);
		return contentObjUtils.getAlaramContent();
	}
	
	/**
	 * 初始化填充对象
	 * @param domain
	 * @return
	 */
	protected abstract AlarmKeyReplaceUtils initAlarmKeyReplaceUtils(T domain);
	/**
	 * 查询未处理的任务
	 * @return
	 */
	public abstract List<T> listUnCompetedTask();
	
	/**
	 * 关闭已处理的任务
	 */
	public abstract void closeUnCompletedTask(List<T> dataList);
	
	/**
	 * 分析具体的类型数据
	 */
	public abstract List<AlertRecordDomain> analyData(List<T> taskList);
	
	
	/**
	 * 归并
	 */
	public abstract List<MoitorAlertRecordDomain> reduceAlertRecord(List<T> taskList);
	
	/**
	 * 保存数据入库
	 * @param alertDataList
	 */
	protected void saveDataToDB(List<AlertRecordDomain> alertDataList) {
		for(AlertRecordDomain domain : alertDataList){
			getAlertRecordDAO().addAlertRecord(domain);
		}
	}

	/**
	 * 保存归并数据
	 * @param monitorAlertList
	 */
	protected void saveMonitorDataToDB(List<MoitorAlertRecordDomain> monitorAlertList) {
		for(MoitorAlertRecordDomain domain : monitorAlertList){
			
			//内容前后去空格
			domain.setContent(StringUtils.trimToEmpty(domain.getContent()));
			//判断是否存在
			boolean isExists = getMonitorAlertRecordDAO().isExistMonitorAlertRecord(domain);
			//如果不存在，则存储
			if(!isExists){
				getMonitorAlertRecordDAO().addMonitorAlertRecord(domain);
			}else{
				//更新最后一次时间
				getMonitorAlertRecordDAO().updateLastTime(domain);
			}
		}
	}

	/**
	 * @return the worker
	 */
	public String getWorker() {
		return worker;
	}

	/**
	 * @param worker the worker to set
	 */
	public void setWorker(String worker) {
		this.worker = worker;
	}
	
	/**
	 * 初始化
	 * @param worker
	 */
	public abstract void init();
	
	public AlertRecordDAO getAlertRecordDAO(){
		return SpringContextHolder.getBean("alertRecordDAO");
	}
	
	public MonitorAlertRecordDAO getMonitorAlertRecordDAO(){
		return SpringContextHolder.getBean("monitorAlertRecordDAO");
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}
	
	
	/**
	 * 格式化主机层信息
	 * @param ipAddr
	 * @param nickName
	 * @param dbId
	 * @param port
	 * @return
	 */
	protected String formatMasterInfo(String ipAddr,String nickName,String dbId,String port,Date gmtCreated) {
		StringBuffer sb = new StringBuffer();
		if(StringUtils.isNotBlank(nickName)){
			sb.append(nickName);
		}
		if(StringUtils.isNotBlank(ipAddr)){
			sb.append("[");
			sb.append(ipAddr);
			if(StringUtils.isNotBlank(dbId) && StringUtils.isNotBlank(port)){
				sb.append(":");
				sb.append(port);
			}
			sb.append("]");
		}
		
		if(gmtCreated != null){
			String triggerTime = getTriggerTime(gmtCreated);
			sb.append(" ");
			sb.append(triggerTime);
		}
		
		return sb.toString();
	}
	
	
	
	/**
	 * 获取触发时间
	 * @param gmtCreated
	 * @return
	 */
	protected String getTriggerTime(Date gmtCreated) {
		String triggerTime = "";
		if(gmtCreated != null){
			triggerTime = DateUtils.DateToString(gmtCreated, "yyyy-MM-dd HH:mm:ss");
		}
		return triggerTime;
	}
}
