package com.utils;

import com.domain.DateInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * <p>Description: [时间工具类]</p>
 * Created on 2017年2月14日
 * @author  <a href="mailto: zuorenzhi@clt.com">左仁智</a>
 * @version 1.0 
 * Copyright (c) 2017 北京柯莱特科技有限公司 交付部
 */
public class DateUtils {

	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * yyyy-MM-dd
	 */
	private static String YYYYMMDD= "yyyy-MM-dd";
	/**
	 * 一天的毫秒总数
	 */
	private static final long MILLISECONDS = 1000*3600*24;

	/**
	 * <p>Discription:[date转str]</p>
	 * Created on 2017年2月14日
	 * @param pattern
	 * @param date
	 * @return
	 * @author:[左仁智]
	 */
	public static final String dateToStr(String pattern, Date date) {
		String returnValue = "";
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			returnValue = df.format(date);
		}
		return returnValue;
	}
	public static final String dateToStr(Date date) {
		return dateToStr(YYYYMMDDHHMMSS,date);
	}

	/**
	 * <p>Discription:[str转date]</p>
	 * Created on 2017年2月14日
	 * @throws ParseException
	 * @author:[左仁智]
	 */
	public static final Date strToDate(String pattern, String strDate)throws ParseException {
		Date date = null;
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			return null;
		}
		return date;
	}
	
	public static final Date strToDate(String strDate)throws ParseException{
		return strToDate(YYYYMMDDHHMMSS, strDate);
	}

	/**
	 * <p>Discription:[获取昨天/今天/明天]</p>
	 * Created on 2017年2月14日
	 * @param calendarType  时间类型，譬如：Calendar.DAY_OF_YEAR
	 * @param addNum 时间数字，譬如：-1 昨天，0 今天，1 明天
	 * @return
	 * @author:[左仁智]
	 */
	public static final Date getDateFromNow(int calendarType, int addNum){
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendarType, calendar.get(calendarType) + addNum);
		return calendar.getTime();
	}
	
	/**
	* 计算两个日期之间相差的天数  
	* @param startDate 较小的时间 
	* @param endDate  较大的时间 
	* @return 相差天数 
	* @throws ParseException  
	*/
	public static int daysDifference(Date startDate,Date endDate) throws ParseException{
		startDate=strToDate(YYYYMMDD, dateToStr(YYYYMMDD, startDate));  
		endDate=strToDate(YYYYMMDD, dateToStr(YYYYMMDD, endDate));   
		long startTime = getMilliseconds(startDate);
		long endTime = getMilliseconds(endDate);
		return getBetweenDays(startTime, endTime);
	} 
	/**
	 * <p>Discription:[获取两个时间的天数差]</p>
	 * Created on 2017年2月14日
	 * @param startTime
	 * @param endTime
	 * @return
	 * @author:[左仁智]
	 */
	public static int getBetweenDays(long startTime,long endTime){
		long betweenDays=(endTime-startTime)/MILLISECONDS;
		return Integer.parseInt(String.valueOf(betweenDays));
	}
	
	/**
	 * <p>Discription:[获取Date的毫秒数]</p>
	 * Created on 2017年2月14日
	 * @param date
	 * @return
	 * @author:[左仁智]
	 */
	public static long getMilliseconds(Date date){
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(date);
		return calendar.getTimeInMillis();
	}
	
	/**
	 * <p>Discription:[取系统时间给年月天赋值]</p>
	 * Created on 2017年2月14日
	 * @author:[左仁智]
	 */
	public static DateInfo assembleYMD() {
		return assembleYMD(new Date());
	}
	/**
	 * <p>Discription:[取系统时间给时分秒赋值]</p>
	 * Created on 2017年2月14日
	 * @author:[左仁智]
	 */
	public static DateInfo assembleHMS() {
		return assembleHMS(new Date());
	}
	/**
	 * <p>Discription:[取date给年月天赋值]</p>
	 * Created on 2017年2月14日
	 * @param date
	 * @author:[左仁智]
	 */
	public static DateInfo assembleYMD(Date date) {
		DateInfo timeDate = new DateInfo();
		timeDate.setYear(getDateValue(date, Calendar.YEAR));
		timeDate.setMonth(getDateValue(date, Calendar.MONTH));
		timeDate.setDay(getDateValue(date, Calendar.DAY_OF_MONTH));
		return timeDate;
	}
	/**
	 * <p>Discription:[取date给时分秒赋值]</p>
	 * Created on 2017年2月14日
	 * @param date
	 * @return
	 * @author:[左仁智]
	 */
	public static DateInfo assembleHMS(Date date) {
		DateInfo timeDate = new DateInfo();
		timeDate.setHour(getDateValue(date, Calendar.HOUR_OF_DAY));
		timeDate.setMinute(getDateValue(date, Calendar.MINUTE));
		timeDate.setSeconds(getDateValue(date, Calendar.SECOND));
		return timeDate;
	}
	/**
	 * <p>Discription:[在当前时间基础上加或减时间]</p>
	 * Created on 2017年2月14日
	 * @author:[左仁智]
	 */
	public static Date addTime(Date date, int days, int hour, int minute) {
		return addTime(date, days, hour, minute,0);
	}

	/**
	 * <p>Discription:[在当前时间基础上加或减时间]</p>
	 * Created on 2017年2月14日
	 * @return
	 * @author:[左仁智]
	 */
	public static Date addTime(Date date, int days, int hour, int minute,int seconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		calendar.add(Calendar.HOUR, hour);
		calendar.add(Calendar.MINUTE, minute);
		calendar.add(Calendar.SECOND, seconds);
		return calendar.getTime();
	}
	/**
	 * <p>Discription:[获取给定时间的年月日时分秒]</p>
	 * Created on 2017年1月24日
	 * @author:[左仁智]
	 */
	public static int getDateValue(Date date,int field) {
		Calendar cal = Calendar.getInstance();// 使用日历类
		cal.setTime(date);
		switch (field) {
		case Calendar.YEAR:
			return cal.get(Calendar.YEAR);
		case Calendar.MONTH:
			return cal.get(Calendar.MONTH)+1;// 得到月，因为从0开始的，所以要加1
		case Calendar.DAY_OF_MONTH:
			return cal.get(Calendar.DAY_OF_MONTH);//同DATE：值均为5
		case Calendar.HOUR:
			return cal.get(Calendar.HOUR);//12
		case Calendar.HOUR_OF_DAY:
			return cal.get(Calendar.HOUR_OF_DAY);//24
		case Calendar.MINUTE:
			return cal.get(Calendar.MINUTE);
		case Calendar.SECOND:
			return cal.get(Calendar.SECOND);
		default:
			return -1;
		}
	}
	
	/**
	 * Discription:  判断当前时间是否在给定区间之内
	 * Created on: 2017/8/4 11:49
	 * @param:  beginHour ,endHour
	 * @return:  boolean
	 * @author: zuorenzhi
	 */
	public static boolean betweenAnd(int beginHour ,int endHour){
		Date curDate = new Date();

		Calendar begin = Calendar.getInstance();
		begin.set(Calendar.HOUR_OF_DAY, beginHour);
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);

		Calendar end = Calendar.getInstance();
		end.set(Calendar.HOUR_OF_DAY, endHour);
		end.set(Calendar.MINUTE, 0);
		end.set(Calendar.SECOND, 0);
		// == -1 ：比参数小， != -1 大于等于参数；  ==1 比参数大， != 1 小于等于参数
//		if(curDate.compareTo(begin.getTime()) != -1 && curDate.compareTo(end.getTime()) != 1){
		if(curDate.compareTo(begin.getTime())  >= 0 && curDate.compareTo(end.getTime()) <= 0 ){
			return true;
		}
		return false;
	}

	public static boolean betweenAndV2(int beginHour ,int endHour){
		Date curDate = new Date();

		Calendar begin = Calendar.getInstance();
		begin.set(Calendar.HOUR_OF_DAY, beginHour);
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);

		Calendar end = Calendar.getInstance();
		end.set(Calendar.HOUR_OF_DAY, endHour);
		end.set(Calendar.MINUTE, 0);
		end.set(Calendar.SECOND, 0);
		if(curDate.after(begin.getTime()) && curDate.before(end.getTime()) ){
			return true;
		}
		return false;
	}

	/**
	 * Discription:  判断当前时间是否在给定区间之外
	 * Created on: 2017/8/4 11:49
	 * @param:  beginHour ,endHour
	 * @return:  boolean
	 * @author: zuorenzhi
	 */
	public static boolean notBetweenAnd(int beginHour ,int endHour){
		Date curDate = new Date();

		Calendar begin = Calendar.getInstance();
		begin.set(Calendar.HOUR_OF_DAY, beginHour);
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);

		Calendar end = Calendar.getInstance();
		end.set(Calendar.HOUR_OF_DAY, endHour);
		end.set(Calendar.MINUTE, 0);
		end.set(Calendar.SECOND, 0);

		if(curDate.before(begin.getTime()) || curDate.after(end.getTime())){
			return true;
		}
		return false;
	}

	/**两日期的day差值 3-1=2*/
	public static long differenceDays(String timeStart, String timeEnd){
		return ChronoUnit.DAYS.between(LocalDate.parse(timeStart), LocalDate.parse(timeEnd));
	}


	/**
	 * 收集起始时间到结束时间之间所有的时间并以字符串集合方式返回(基于jdk1.8)
	 * @param timeStart
	 * @param timeEnd
	 * @return
	 */
	public static List<String> collectLocalDates(String timeStart, String timeEnd){
		return collectLocalDates(LocalDate.parse(timeStart), LocalDate.parse(timeEnd));
	}

	/**
	 * 收集起始时间到结束时间之间所有的时间并以字符串集合方式返回
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<String> collectLocalDates(LocalDate start, LocalDate end){
		// 用起始时间作为流的源头，按照每次加一天的方式创建一个无限流
		return Stream.iterate(start, localDate -> localDate.plusDays(1))
				// 截断无限流，长度为起始时间和结束时间的差+1个
				.limit(ChronoUnit.DAYS.between(start, end) + 1)
				// 由于最后要的是字符串，所以map转换一下
				.map(LocalDate::toString)
				// 把流收集为List
				.collect(Collectors.toList());
	}



	public static void main(String[] args) {
//		System.out.println(getDateValue(new Date(), Calendar.YEAR));
//		int begin = 18;
//		int end = 20;
//		System.out.println(betweenAnd(begin,end));
//		System.out.println(betweenAndV2(begin,end));
//		System.out.println(notBetweenAnd(begin,end));


		String timeStart = "2018-03-25";
        String timeEnd = "2018-04-05";
//		String timeEnd = "2018-03-29";

		collectLocalDates(timeStart, timeEnd).forEach(System.out::println);
		long l = differenceDays(timeStart, timeEnd);
		System.out.println(l);
	}

}
