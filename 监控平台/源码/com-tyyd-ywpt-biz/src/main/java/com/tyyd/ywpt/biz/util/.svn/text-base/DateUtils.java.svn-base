/**   
 * @Title: DateUtils.java 
 * @Package com.tyyd.ywpt.biz.util 
 * @Description:  
 * @author wangyu   
 * @date 2014-6-25 下午3:43:12 
 * @CopyRight 天翼阅读
 * @version V1.0   
 */
package com.tyyd.ywpt.biz.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author wangyu
 * 
 */
public class DateUtils {

	private final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	private final static String YYYY_MM_DD = "yyyy-MM-dd";
	private final static String YYYY_MM_DD_HH_MM_SS_CN = "yyyy-MM-dd HH时mm分ss秒";

	
	/** 
     * 将日期字符串转化为日期。失败返回null。 
     * @param date 日期字符串 
     * @param dateStyle 日期风格 
     * @return 日期 
     */ 
	public static Date StringToDate(String date, String pattern) {
		Date myDate = null;
		if (date != null) {
			try {
				myDate = getDateFormat(pattern).parse(date);
			} catch (Exception e) {
			}
		}
		return myDate;
	}

	private static SimpleDateFormat getDateFormat(String pattern)
			throws RuntimeException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		dateFormat.applyPattern(pattern);
		return dateFormat;
	}
	
	/** 
     * 将日期转化为日期字符串。失败返回null。 
     * @param date 日期 
     * @param pattern 日期格式 
     * @return 日期字符串 
     */  
    public static String DateToString(Date date, String pattern) {  
        String dateString = null;  
        if (date != null) {  
            try {  
                dateString = getDateFormat(pattern).format(date);  
            } catch (Exception e) {  
            }  
        }  
        return dateString;  
    }  
  
    
    public static String getCurrentDate(){
    	return DateToString(new Date(),YYYY_MM_DD);
    }
    
    public static String getCurrentDateTime(){
    	return DateToString(new Date(),YYYY_MM_DD_HH_MM_SS);
    }
    
    /**
     * AIX CPU的时间转化
     * @param date
     * @return
     */
    public static String aixCpuDateFormat(String date) {
    	String cupdate = getCurrentDate() + " " +date;
		return cupdate;
	}
    
    
    /**
     * linux CPU的时间转化
     * @param date
     * @return
     */
    public static String linuxCpuDateFormat(String date) {
    	String cupdate = getCurrentDate() + " " +date;
		Date myDate = null;
		if (date != null) {
			try {
				myDate = getDateFormat(YYYY_MM_DD_HH_MM_SS_CN).parse(cupdate);
			} catch (Exception e) {
			}
		}
		return DateToString(myDate,YYYY_MM_DD_HH_MM_SS);
	}
    
    
    /**
     * 取delayDay的日期
     * @param delayDay
     * @return
     */
    public static Date getDelayDay(int delayDay){
    	Calendar c = Calendar.getInstance();
    	c.add(Calendar.DATE, delayDay);
    	return c.getTime();
    }
    
    /**
     * 获取两日前
     * @return
     */
    public static String getBeforeTwoDay(){
    	Date date = getDelayDay(-2);
    	return DateToString(date,YYYY_MM_DD);
    }
    
    
    /**
     * 加几个小时
     * @param startDate
     * @param hours
     * @return
     */
    public static String addHour(Date startDate,int hours){
    	Calendar c = Calendar.getInstance();
    	c.setTime(startDate);
    	c.add(Calendar.HOUR, hours);
    	Date newDate = c.getTime();
    	return DateToString(newDate, YYYY_MM_DD_HH_MM_SS);
    }
    
    /**
     * 加几日
     * @param startDate
     * @param hours
     * @return
     */
    public static String addDate(Date startDate,int delay){
    	Calendar c = Calendar.getInstance();
    	c.setTime(startDate);
    	c.add(Calendar.DATE, delay);
    	Date newDate = c.getTime();
    	return DateToString(newDate, YYYY_MM_DD_HH_MM_SS);
    }
    
    
    public static String addDateByFormat(Date startDate,int delay,String format){
    	Calendar c = Calendar.getInstance();
    	c.setTime(startDate);
    	c.add(Calendar.DATE, delay);
    	Date newDate = c.getTime();
    	return DateToString(newDate, format);
    }
    
    /**
     * 加几周
     * @param startDate
     * @param hours
     * @return
     */
    public static String addWeek(Date startDate,int delay){
    	Calendar c = Calendar.getInstance();
    	c.setTime(startDate);
    	c.add(Calendar.WEEK_OF_MONTH, delay);
    	Date newDate = c.getTime();
    	return DateToString(newDate, YYYY_MM_DD_HH_MM_SS);
    }
    
    
    /**
     * 加几月
     * @param startDate
     * @param hours
     * @return
     */
    public static String addMonth(Date startDate,int delay){
    	Calendar c = Calendar.getInstance();
    	c.setTime(startDate);
    	c.add(Calendar.MONTH, delay);
    	Date newDate = c.getTime();
    	return DateToString(newDate, YYYY_MM_DD_HH_MM_SS);
    }
    
    
    /**
     * 加几分钟
     * @param startDate
     * @param hours
     * @return
     */
    public static String addMonute(Date startDate,int delay){
    	Calendar c = Calendar.getInstance();
    	c.setTime(startDate);
    	c.add(Calendar.MINUTE, delay);
    	Date newDate = c.getTime();
    	return DateToString(newDate, YYYY_MM_DD_HH_MM_SS);
    }
    
    public static String getStandardDate(Date date){
    	if(date == null){
    		return null;
    	}
    	
    	return DateToString(date, YYYY_MM_DD_HH_MM_SS);
    }
    
    
    /**
     * 等于0 start = end
     * 小于0 start < end
     * 大于0 start > end
     * @param startDate
     * @param endDate
     * @return
     */
    public static int compareDate(String startDate,String endDate){
    	Date start = StringToDate(startDate, YYYY_MM_DD_HH_MM_SS);
    	Date end = StringToDate(endDate, YYYY_MM_DD_HH_MM_SS);
    	return start.compareTo(end);
    }
    
    /**
     * 等于0 start = end
     * 小于0 start < end
     * 大于0 start > end
     * @param startDate
     * @param endDate
     * @return
     */
    public static int compareYmdDate(String startDate,String endDate){
    	Date start = StringToDate(startDate, YYYY_MM_DD);
    	Date end = StringToDate(endDate, YYYY_MM_DD);
    	return start.compareTo(end);
    }
  
    
	public static String showTime(Date ctime, String format) {
		String r = "";
		
		if (ctime == null)
			return r;
		
		if (format == null)
			format = "yyyy-MM-dd HH:mm";
		
		long nowtimelong = System.currentTimeMillis();
		long ctimelong = ctime.getTime();
		long result = Math.abs(nowtimelong - ctimelong);

		if (result < 60000){// 一分钟内
			long seconds = result / 1000;
			r = seconds + "秒钟前";
		} else if (result >= 60000 && result < 3600000){// 一小时内
			long seconds = result / 60000;
			r = seconds + "分钟前";
		} else if (result >= 3600000 && result < 86400000){ // 一天内
			long seconds = result / 3600000;
			r = seconds + "小时前";
		} else{
			r = DateToString(ctime, YYYY_MM_DD_HH_MM_SS);
		}
		return r;
	}
	
	
	/**
	 * 两个日期差的天数
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int daysBetween(Date d1,Date d2){
		
		String s1 = DateToString(d1, YYYY_MM_DD);
		String s2 = DateToString(d2, YYYY_MM_DD);
		
		Date newDate1 = StringToDate(s1, YYYY_MM_DD);
		Date newDate2 = StringToDate(s2, YYYY_MM_DD);
		
		long between_days = (newDate2.getTime()-newDate1.getTime())/(1000*3600*24);  
        int delay = Integer.parseInt(String.valueOf(between_days)) + 1;
        
        return delay;
        
	}
	
	
	/**
	 * 两个日期差的天数
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int daysBetweenByStrFormat(String s1,String s2){
		
		Date newDate1 = StringToDate(s1, YYYY_MM_DD);
		Date newDate2 = StringToDate(s2, YYYY_MM_DD);
		
		long between_days = (newDate2.getTime()-newDate1.getTime())/(1000*3600*24);  
        int delay = Integer.parseInt(String.valueOf(between_days));
        
        return delay;
        
	}
	
	
	/**
	 * timeStamp 转 Date
	 * @param timeStamp
	 * @return
	 */
	public static Date convertTimeStampToDate(Long timeStamp){
		Date d = new Date();
		d.setTime(timeStamp);
		
		return d;
	}
	
	
	/**
	 * timeStamp 转化 成字符串输出
	 * @param timeStamp
	 * @return
	 */
	public static String convertTimeStampToDateString(Long timeStamp){
		Date d = convertTimeStampToDate(timeStamp);
		return DateToString(d, YYYY_MM_DD_HH_MM_SS);
	}
    
    public static void main(String[] args) {
//    	Date date = StringToDate("2014-12-30 13:16:01",YYYY_MM_DD_HH_MM_SS);
//    	System.out.println(addHour(date,4));
//    	System.out.println(addDate(date,1));
//    	System.out.println(addWeek(date,1));
//    	System.out.println(addMonth(date,1));
    	
//    	System.out.println(daysBetweenByStrFormat("2014-12-29","2014-12-29"));
    	
    	System.out.println(convertTimeStampToDateString(1427268240000l));
    }
}
