/**   
* @Title: BaseLineAbstractHandler.java 
* @Package com.tyyd.ywpt.report.biz.quota.baseline 
* @Description:  
* @author wangyu   
* @date 2015-3-31 上午11:35:43 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.biz.quota.baseline;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tyyd.ywpt.biz.util.DateUtils;
import com.tyyd.ywpt.dao.baseline.dataobject.BaseLineRecordDomain;
import com.tyyd.ywpt.report.biz.quota.dto.baseline.BaseLineReportFormatDataBO;

/**
 * @author wangyu
 *
 */
public abstract class BaseLineAbstractHandler {

	public static final Logger LOGGER = Logger.getLogger(BaseLineAbstractHandler.class);  
	
	/**
	 * 最小基线时间
	 */
	private static final String MIN_BASElINE_TIME = "00:00";
	
	/**
	 * 最大基线时间
	 */
	private static final String MAX_BASElINE_TIME = "23:59";
	
	
	/**
	 * 下个处理对象
	 */
	private BaseLineAbstractHandler nextHandler;
	
	
	/**
	 * 具体处理
	 * @param dayBetweenType 日期类型 (0:1天内，1:隔天，2：超过1天)
	 *  
	 */
	public void handler(String currentYmd, String startHm, 
			String endHm, List<BaseLineRecordDomain> baseLineList,
			List<BaseLineReportFormatDataBO> maxValResult,
			List<BaseLineReportFormatDataBO> avgValResult){
		
		if(this.getRoleType() == null){
			return ;
		}
		
		//两个日期相差的类型(0:1前，1:隔天，2：1天以上)
		int dayType = 0;
		
		//如果对应的日期类型一致	
		if(this.getRoleType().intValue() == dayType){
			
			//处理具体的内容
			this.handlerBaseLine(currentYmd, startHm, endHm, baseLineList, maxValResult, avgValResult);
			
		}else{
			
			//传递给下一个接收者
			this.nextHandler.handler(currentYmd, startHm, endHm, baseLineList, maxValResult, avgValResult);
		}
			
		
	}

	/**
	 * @param startYmd
	 * @param startHm
	 * @param endYmd
	 * @param endHm
	 * @param baseLineList
	 * @param maxValResult
	 * @param avgValResult
	 * @param baseLineType 1：上轨，2：中轨，3：下轨
	 * @param betweenDayType 相差的日期
	 */
	private void handlerBaseLine(String currentYmd, String startHm,
			String endHm, List<BaseLineRecordDomain> baseLineList,
			List<BaseLineReportFormatDataBO> maxValResult,
			List<BaseLineReportFormatDataBO> avgValResult) {

		boolean isValid = paramCheck(currentYmd, startHm, endHm, baseLineList);
		
		if(!isValid){
			LOGGER.error("参数有误，请先检查");
			return;
		}
		
		//定义上下轨及中轨的数据
		List<BaseLineReportFormatDataBO> maxList = new ArrayList<BaseLineReportFormatDataBO>();
		List<BaseLineReportFormatDataBO> avgList = new ArrayList<BaseLineReportFormatDataBO>();
		
		//丢给具体的处理类处理
		startHm = this.getStartHm(startHm);
		endHm = this.getEndHm(endHm);
		
		for(BaseLineRecordDomain domain : baseLineList){
			String baseTime = domain.getBaseTime();
			
			//比较基线时间与最小的时间
			int isBiger = baseTime.compareToIgnoreCase(startHm);
			
			//比较基线时间与最大的时间
			int isSmaller = baseTime.compareToIgnoreCase(endHm);
			
			if(isBiger >= 0 && isSmaller <= 0){
				//中轨
				avgList.add(this.convertBaseLineToAreaRangeReportData(currentYmd,domain,LineTrack.Middle.getTrackType()));
				
				//上下轨
				maxList.add(this.convertBaseLineToAreaRangeReportData(currentYmd,domain,LineTrack.TopAndBottom.getTrackType()));
			}
		}
		
		
		//合并数据
		//中轨
		mergeValResult(avgValResult,avgList);
		
		//上下轨
		mergeValResult(maxValResult,maxList);
	}
	
	
	
	/**
	 * 合并数据
	 * @param maxValResult
	 * @param minList
	 */
	private List<BaseLineReportFormatDataBO> mergeValResult(List<BaseLineReportFormatDataBO> valResult,
			List<BaseLineReportFormatDataBO> list) {
		valResult.addAll(list);
		return valResult;
	}
	

	/**
	 * 
	 * 参数校验
	 * @param startYmd
	 * @param startHm
	 * @param endYmd
	 * @param endHm
	 * @param baseLineList
	 * @param baseLineType
	 * @param betweenDayType
	 * @return
	 */
	private boolean paramCheck(String currentYmd, String startHm, 
			String endHm, List<BaseLineRecordDomain> baseLineList) {

		boolean flag = Boolean.FALSE;
		
		if(StringUtils.isBlank(currentYmd) 
				|| StringUtils.isBlank(startHm) 
				|| StringUtils.isBlank(endHm)){
			
			LOGGER.error("日期有为空，无法进行基线计算");
			return flag;
		}
		
		if(CollectionUtils.isEmpty(baseLineList)){
			LOGGER.error("基线数据为空，无法进行基线计算");
			return flag;
		}
		
		return Boolean.TRUE;
	}

	
	/**
	 * 数据转化
	 * @param startYmd
	 * @param domain
	 * @return
	 */
	private BaseLineReportFormatDataBO convertBaseLineToAreaRangeReportData(
			String currentYmd,BaseLineRecordDomain domain,Integer baseLineType) {

		if(domain == null){
			return null;
		}

		BaseLineReportFormatDataBO dataBo = new BaseLineReportFormatDataBO();
		
		//时间戳
		long timeStamp = DateUtils.StringToDate(currentYmd +" "+domain.getBaseTime(), "yyyy-MM-dd HH:mm").getTime();
		
		int length = 3;
		if(baseLineType == 2){
			length = 2;
		}
		
		Object[] areaRangeArray = new Object[length];
		
		//时间
		areaRangeArray[0] = timeStamp;
		
		if(baseLineType == 2){
			//中轨
			areaRangeArray[1] = domain.getAvgVal().doubleValue();
			
		}else{
			//下轨
			areaRangeArray[1] = domain.getMinVal().doubleValue();
			
			//上轨
			areaRangeArray[2] = domain.getMaxVal().doubleValue();
		}
		
		dataBo.setArrays(areaRangeArray);
		
		return dataBo;
	}
	
	
	

	/**
	 * 设置角色
	 * @param roleType
	 */
	protected abstract Integer getRoleType() ;
	
	/**
	 * 开始时间
	 * @param startHm
	 * @return
	 */
	private String getStartHm(String startHm){
		if(StringUtils.isBlank(startHm)){
			return MIN_BASElINE_TIME;
		}
		return startHm;
	}
	
	/**
	 * 结束时间
	 * @param endHm
	 * @return
	 */
	private String getEndHm(String endHm){
		if(StringUtils.isBlank(endHm)){
			return MAX_BASElINE_TIME;
		}
		return endHm;
	}
	

	/**
	 * @param nextHandler the nextHandler to set
	 */
	public void setNextHandler(BaseLineAbstractHandler nextHandler) {
		this.nextHandler = nextHandler;
	}

	/**
	 * @return the nextHandler
	 */
	public BaseLineAbstractHandler getNextHandler() {
		return nextHandler;
	}

	
	protected enum LineTrack{
		
		TopAndBottom(1,"上下轨"),
		Middle(2,"中轨");
		
		
		private Integer trackType;
		private String mark;
		/**
		 * @return the trackType
		 */
		public Integer getTrackType() {
			return trackType;
		}
		/**
		 * @param trackType the trackType to set
		 */
		public void setTrackType(Integer trackType) {
			this.trackType = trackType;
		}
		/**
		 * @return the mark
		 */
		public String getMark() {
			return mark;
		}
		/**
		 * @param mark the mark to set
		 */
		public void setMark(String mark) {
			this.mark = mark;
		}
		/**
		 * @param trackType
		 * @param mark
		 */
		private LineTrack(Integer trackType, String mark) {
			this.trackType = trackType;
			this.mark = mark;
		}
		
	};
	
	
	/**
	 * 日期类型定义
	 * @author wangyu
	 */
	protected enum RoleLevel{
		
		Prev(0,"前段"),
		Middle(1,"中间段"),
		Last(2,"末段");
		
		private Integer dayType;
		private String mark;
		/**
		 * @return the dayType
		 */
		public Integer getDayType() {
			return dayType;
		}
		/**
		 * @param dayType the dayType to set
		 */
		public void setDayType(Integer dayType) {
			this.dayType = dayType;
		}
		/**
		 * @return the mark
		 */
		public String getMark() {
			return mark;
		}
		/**
		 * @param mark the mark to set
		 */
		public void setMark(String mark) {
			this.mark = mark;
		}
		/**
		 * @param dayType
		 * @param mark
		 */
		private RoleLevel(Integer dayType, String mark) {
			this.dayType = dayType;
			this.mark = mark;
		}
		
	};
	 
}
