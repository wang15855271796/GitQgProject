package com.puyue.www.qiaoge.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;

/**
 * 
 * @author Administrator
 * 
 */

public class DateUtils {

	private static final String SUNDAY = "周日";
	private static final String SATARDAY = "周六";
	private static final String FRIDAY = "周五";
	private static final String THUSDAY = "周四";
	private static final String WENSDAY = "周三";
	private static final String TUSDAY = "周二";
	private static final String MONDAY = "周一";

	public static final String DATE_FORMAT_BAR = "yyyy-MM-dd";

	public static final String DATE_FORMAT_MONTH = "yyyy-MM";

	public static final String DATE_FORMAT_HOUR = "yyyy-MM-dd HH";

	public static final String DATE_FORMAT_SIMPLE = "yyyy/MM/dd HH:mm";
	public static final String DATE_FORMAT_MINUTE = "yyyy-MM-dd HH:mm";
	public static final String DATE_FORMAT_MINUTE2 = "yyyy.MM.dd HH:mm";
	public static final String DATE_FORMAT_MINUTEW = "yyyy.MM.dd";

	public static final String DATE_FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss";

	public static final String DATE_FORMAT_MILLISECOND = "yyyy-MM-dd HH:mm:ss.SSS";

	public static final String DATE_FORMAT_CHINESE = "yyyy年MM月dd日";

	public static final String DATE_FORMAT_MINUTE_CHINESE = "yyyy年MM月dd日HH时mm分";
	public static final String DATE_FORMAT_Second_CHINESE = "yyyy年MM月dd日HH时mm分ss秒";
	public static final String DATE_FORMAT_SPRIT = "yyyy/MM/dd";


	// public static final String TIME_ON_WORK = " 08:00:00";
	public static final String TIME_ON_WORK = " 00:00:00";
	// public static final String TIME_OFF_WORK = " 18:00:00";

	public static final String TIME_OFF_WORK = " 24:00:00";
	private static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat w = new SimpleDateFormat("MM月dd日");
	private static SimpleDateFormat w2 = new SimpleDateFormat("HH:mm");

	/**
	 * 时间格式
	 */
	public static final String DATE_FORMAT_NORMAL = "yyyy-MM";

	/**
	 * 时间格式（年月日）
	 */
	public static final String DATE_FORMAT_YYMMDD = "yyyy-MM-dd";

	@SuppressLint("SimpleDateFormat")
//	 public static void main(String[] args) throws ParseException {
////	 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////	 Date date = format.parse("2015-08-19 12:35:35");
////	 System.out.println(getTime("2015-08-19 14:35:35","HH:mm"));
////		 getMonthTime("2016-03-20 15:53:28", DATE_FORMAT_NORMAL,"yyyy-MM");
//		 getShopListHeader("2014-12-20 15:53:28", DATE_FORMAT_NORMAL);
//	 }

	/**
	 * 格式化时间到店面列表项标题需要的样式</br>
	 * 本年本月显示“本月”；本来其他月份只显示月份；非本年日期全部显示年份+月份
	 *
	 * @param dateTime 待计算时间
	 * @param format 时间格式
	 * @return
	 * @throws ParseException
	 */
	public static String getShopListHeader(String dateTime, String format) throws ParseException {
		String headerText="";
		//判断是否是本年的时间
		if(isThisYear(dateTime, format)){
			//是今年的时间
			if(isThisMonth(dateTime, format)){
				//是今年本月的时间：显示“本月”
				headerText="本月";
			}else{
				//是今年的其他月份:只显示月份
				headerText = getTime(dateTime, format, "M月");
			}
		}else{
			//非今年的时间：显示年+月
			headerText = getTime(dateTime, format, "yyyy年M月");
		}
		System.out.println("headerText="+headerText);
		return headerText;
	}

	/**
	 * 判断是否是本年的时间
	 * @param dateTime
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	@SuppressLint("SimpleDateFormat")
	@SuppressWarnings("deprecation")
	public static boolean isThisYear(String dateTime, String format) throws ParseException{
		Calendar cal = Calendar.getInstance();
		// 使用格林尼治时域，防止不同时间戳间的时差
		cal.setTimeZone(TimeZone.getTimeZone("GMT"));
		cal.setTimeInMillis(System.currentTimeMillis());
		int currentYear=cal.get(Calendar.YEAR);
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date date = dateFormat.parse(dateTime);
		return date.getYear()==(currentYear-1900);
	}

	/**
	 * 判断是否是本年本月的时间
	 * @param dateTime
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	@SuppressLint("SimpleDateFormat")
	@SuppressWarnings("deprecation")
	public static boolean isThisMonth(String dateTime, String format) throws ParseException{
		if(!isThisYear(dateTime, format)){
			//不是本年的时间，显然不是本年本月的时间
			return false;
		}
		Calendar cal = Calendar.getInstance();
		// 使用格林尼治时域，防止不同时间戳间的时差
		cal.setTimeZone(TimeZone.getTimeZone("GMT"));
		cal.setTimeInMillis(System.currentTimeMillis());
		int currentMonth=cal.get(Calendar.MONTH);
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date date = dateFormat.parse(dateTime);
		return date.getMonth()==currentMonth;
	}

	/**
	 * 获取精确到月份的时间
	 *
	 * @param dateTime
	 *            待计算时间
	 * @param formatFrom
	 *            原时间格式
	 * @param formatTo 目标时间格式
	 * @return
	 * @throws ParseException
	 */
	@SuppressLint("SimpleDateFormat")
	public static final long getMonthTime(String dateTime, String formatFrom,String formatTo)
			throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatFrom);
		Date date = dateFormat.parse(dateTime);
		dateFormat = new SimpleDateFormat(formatTo);
		String afterDateTime = dateFormat.format(date);
		long time = dateFormat.parse(afterDateTime).getTime();
		return time;
	}

	/**
	 * 格式化时间
	 * @param dateTime
	 * @param formatFrom
	 * @param formatTo
	 * @return
	 * @throws ParseException
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getTime(String dateTime, String formatFrom,
								 String formatTo) throws ParseException {
		if (isBlank(dateTime) || isBlank(formatFrom)
				|| isBlank(formatTo)) {
			return "";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatFrom);
		Date date = dateFormat.parse(dateTime);
		dateFormat = new SimpleDateFormat(formatTo);
		return dateFormat.format(date);
	}

	/**
	 * 字符串空判断
	 * <pre>is null or its length is 0 or it is made by space</pre>
	 *
	 * <pre>
	 * isBlank(null) = true;
	 * isBlank(&quot;&quot;) = true;
	 * isBlank(&quot;  &quot;) = true;
	 * isBlank(&quot;a&quot;) = false;
	 * isBlank(&quot;a &quot;) = false;
	 * isBlank(&quot; a&quot;) = false;
	 * isBlank(&quot;a b&quot;) = false;
	 * </pre>
	 *
	 * @param str
	 * @return if string is null or its size is 0 or it is made by space, return
	 *         true, else return false.
	 */
	public static boolean isBlank(String str)
	{
		return (str == null || str.trim().length() == 0);
	}



	/**
	 * 获取系统当前时间long型
	 * 
	 * @return
	 */
	public static long getCurrentDateTime() {
		return new Date().getTime();
	}

	/**
	 * 返回当前时间
	 * 
	 * @return 返回当前时间
	 */
	public static Date getNewCurrentDateTime() {
		Calendar calNow = Calendar.getInstance();
		Date dtNow = calNow.getTime();

		return dtNow;
	}

	/**
	 * 返回当前时间
	 * 
	 * @return 返回当前时间
	 */
	public static Date getCurrentDate() {
		Calendar calNow = Calendar.getInstance();
		return calNow.getTime();
	}


	/**
	 * 根据指定的时间格式，格式化当前输入的时间;
	 * DATE_FORMAT_BAR = "yyyy-MM-dd";DATE_FORMAT_MONTH = "yyyy-MM";
	 * DATE_FORMAT_HOUR = "yyyy-MM-dd HH";DATE_FORMAT_SIMPLE = "yyyy/MM/dd HH:mm";DATE_FORMAT_MINUTE = "yyyy-MM-dd HH:mm";DATE_FORMAT_MINUTE2 = "yyyy.MM.dd HH:mm";
	 * DATE_FORMAT_MINUTEW = "yyyy.MM.dd";DATE_FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss";DATE_FORMAT_MINUTE_CHINESE = "yyyy年MM月dd日HH时mm分";DATE_FORMAT_SPRIT = "yyyy/MM/dd";
	 *  DATE_FORMAT_MILLISECOND = "yyyy-MM-dd HH:mm:ss.SSS";DATE_FORMAT_CHINESE = "yyyy年MM月dd日"; DATE_FORMAT_Second_CHINESE = "yyyy年MM月dd日HH时mm分ss秒";
	 * @param l 时间
	 * @param format 时间格式
	 * @return
	 */
	public static String formatDate(long l,String format) {
		return new SimpleDateFormat(format).format(new Date(l));
	}

	/**
	 * 根据指定的时间格式，格式化当前输入的时间;
	 * DATE_FORMAT_BAR = "yyyy-MM-dd";DATE_FORMAT_MONTH = "yyyy-MM";
	 * DATE_FORMAT_HOUR = "yyyy-MM-dd HH";DATE_FORMAT_SIMPLE = "yyyy/MM/dd HH:mm";DATE_FORMAT_MINUTE = "yyyy-MM-dd HH:mm";DATE_FORMAT_MINUTE2 = "yyyy.MM.dd HH:mm";
	 * DATE_FORMAT_MINUTEW = "yyyy.MM.dd";DATE_FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss";DATE_FORMAT_MINUTE_CHINESE = "yyyy年MM月dd日HH时mm分";DATE_FORMAT_SPRIT = "yyyy/MM/dd";
	 *  DATE_FORMAT_MILLISECOND = "yyyy-MM-dd HH:mm:ss.SSS";DATE_FORMAT_CHINESE = "yyyy年MM月dd日"; DATE_FORMAT_Second_CHINESE = "yyyy年MM月dd日HH时mm分ss秒";
	 * @param l 时间
	 * @param format 时间格式
	 * @return
	 */
	public static String formatDate(Date date, String format) {
		if (date == null)
			return "";
		return new SimpleDateFormat(format).format(date);
	}
	
	

	/**
	 * 根据指定的格式时间得到当前时间
	 *  * DATE_FORMAT_BAR = "yyyy-MM-dd";DATE_FORMAT_MONTH = "yyyy-MM";
	 * DATE_FORMAT_HOUR = "yyyy-MM-dd HH";DATE_FORMAT_SIMPLE = "yyyy/MM/dd HH:mm";DATE_FORMAT_MINUTE = "yyyy-MM-dd HH:mm";DATE_FORMAT_MINUTE2 = "yyyy.MM.dd HH:mm";
	 * DATE_FORMAT_MINUTEW = "yyyy.MM.dd";DATE_FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss";DATE_FORMAT_MINUTE_CHINESE = "yyyy年MM月dd日HH时mm分";DATE_FORMAT_SPRIT = "yyyy/MM/dd";
	 *  DATE_FORMAT_MILLISECOND = "yyyy-MM-dd HH:mm:ss.SSS";DATE_FORMAT_CHINESE = "yyyy年MM月dd日"; DATE_FORMAT_Second_CHINESE = "yyyy年MM月dd日HH时mm分ss秒";
	 * @param format 时间格式
	 *            
	 * @return
	 */
	public static String getDateOfFormat(String format) {
		return new SimpleDateFormat(format).format(new Date());
	}

	

	
	

	/**
	 * 格式化日期：时分秒
	 * 
	 * @return
	 */
	public static String formatTime() {
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}

	/**
	 * 用于图片命名，精确到毫秒
	 * 
	 * @return
	 */
	public static String picMillisecondName() {
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	}

	/**
	 * 用于图片命名，精确到秒
	 * 
	 * @return
	 */
	public static String picSecondName() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

	
	/**
	 * 计算某年某月某日某点到当日的天数
	 * @param s
	 * @return
	 */
	public static int calDays(String s) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_SECOND);
		long l = 0l;
		try {
			l = (new Date()).getTime() - (sdf.parse(s)).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (int) (l / 1000 / 60 / 60 / 24);
	}

	/**
	 * 比较日期的大小；当天之前：-1，当天：0，当天以后：1+.
	 * 
	 * @param sqlDate
	 * @return
	 */
	public static int compareToDate(String sqlDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String temp = null;
		try {
			if (!TextUtils.isEmpty(sqlDate)&&!"null".equals(sqlDate)) {
				temp = sdf.format(sdf.parse(sqlDate));
			} else {
				return 1;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return temp.compareTo(sdf.format(new Date()));
	}

	/**
	 * 比较日期的大小；当天之前：-1，当天：0，当天以后：1+.
	 * 
	 * @param sqlDate
	 * @return
	 */
	public static int compareToDateByFormat(String sqlDate, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String temp = null;
		try {
			if (!TextUtils.isEmpty(sqlDate)&&!"null".equals(sqlDate)) {
				temp = sdf.format(sdf.parse(sqlDate));
			} else {
				return 1;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return temp.compareTo(sdf.format(new Date()));
	}

	/**
	 * 比较日期的大小；当天之前：-1，当天：0，当天以后：1+.
	 * 
	 * @param firstDate
	 * @param secondDate
	 * @return
	 */
	public static int compareToDate(String firstDate, String secondDate) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateTime2=null,dateTime1=null;
		int result=0;
		try {
			if (firstDate != null && !firstDate.equals("")) {
				 dateTime1 = dateFormat.parse(firstDate);

			}
			if (secondDate != null && !secondDate.equals("")) {
				 dateTime2 = dateFormat.parse(secondDate);
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (dateTime1!=null&&dateTime2!=null){
			result = dateTime1.compareTo(dateTime2);
		}
		return result;
	}

	/**
	 * 比较日期的大小；当天之前：-1，当天：0，当天以后：1+.
	 * 
	 * @param firstDate
	 * @param secondDate
	 * @return
	 */
	public static int compareToSimpleDate(String firstDate, String secondDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_BAR);
		String temp1 = null;
		String temp2 = null;
		try {
			if (firstDate != null && !firstDate.equals("")) {
				temp1 = sdf.format(sdf.parse(firstDate));
			} else {
				return 1;
			}
			if (secondDate != null && !secondDate.equals("")) {
				temp2 = sdf.format(sdf.parse(secondDate));
			} else {
				return 1;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return temp1.compareTo(temp2);
	}

	/**
	 * 比较日期的大小；当天之前：-1，当天：0，当天以后：1+.
	 * 
	 * @param sqlDate
	 * @return
	 */
	public static int compareToDate(Long sqlDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_BAR);
		String temp = null;
		if (sqlDate != null && !sqlDate.equals("")) {
			temp = sdf.format(new Date(sqlDate));
		} else {
			return 1;
		}
		return temp.compareTo(sdf.format(new Date()));
	}



	/**
	 * 判断某一时间是否在指定的时间段内(包含与起止时间相等的情况)
	 * 
	 * @param time
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static boolean betweenScope(Timestamp time, Timestamp beginTime,
			Timestamp endTime) {
		return (!time.before(beginTime) && !time.after(endTime));
	}

	/**
	 * 判断某一时间是否在指定的时间段内(包含与起止时间相等的情况)
	 * 
	 * @param date
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static boolean betweenScope(Date date, Date beginDate, Date endDate) {
		return (!date.before(beginDate) && !date.after(endDate));
	}

	/**
	 * 判断某一时间是否在指定的时间段内(包含与起止时间相等的情况)
	 * 
	 * @param date
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static boolean betweenScope(Date date, String beginDate,
			String endDate) {
		return (!date.before(parseDate(beginDate)) && !date
				.after(parseDate(endDate)));
	}

	/**
	 * 判断某一时间是否在指定的时间前(包含与起止时间相等的情况)
	 */
	public static boolean beforeScope(String date, String beforeDate) {
		return parseDate(date).before(parseDate(beforeDate));
	}

	/**
	 * 判断某一时间是否在指定的时间后(包含与起止时间相等的情况)
	 */
	public static boolean afterScope(String date, String afterDate) {
		return parseDate(date).after(parseDate(afterDate));
	}

	
	
	/**
	 * 判断某一时间是否在指定的时间段内(包含与起止时间相等的情况)
	 * 
	 * @param date
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static boolean betweenScope(String date, String beginDate,
			String endDate) {
		if (TextUtils.isEmpty(beginDate) || "null".equals(beginDate)
				|| TextUtils.isEmpty(endDate) || "null".equals(endDate)) {
			return false;
		}
		return betweenScope(parseDate(date), beginDate, endDate);
	}
	
	/**
	 * 返回这种格式 DATE_FORMAT_BAR = "yyyy-MM-dd"; 的时间
	 * @param strDate
	 * @return
	 */
	public static Date parseDate(String strDate) {
		if (strDate == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_BAR);
		try {
			return sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 判断某一时间是否在指定的时间段内(包含与起止时间相等的情况)
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static boolean compareDate(Date beginDate, Date endDate) {
		return !beginDate.after(endDate);
	}

	/**
	 * 判断两个时间是否超过24个小时
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static boolean isExceed24(Date beginDate, Date endDate) {
		if (endDate != null && beginDate != null) {
			int hours = 0;
			try {
				long m = endDate.getTime() - beginDate.getTime();

				int s = (int) (m / 1000);
				hours = s / 3600;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (hours > 24) {

				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * 判断两个时间是否超过24个小时
	 *
	 * @param
	 * @param
	 * @return
	 */
//	public static boolean isExceed24(long current, long start) {
//		if (current != 0 && start != 0) {
//			int hours = 0;
//			try {
////				long m = endDate.getTime() - beginDate.getTime();
//
////				int s = (int) (m / 1000);
//				hours = s / 3600;
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			if (hours > 24) {
//
//				return true;
//			} else {
//				return false;
//			}
//		}
//		return false;
//	}


	/**
	 * 判断两个时间是否超过2个小时
	 *
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static boolean isExceed2(Date beginDate, Date endDate) {
		if (endDate != null && beginDate != null) {
			int hours = 0;
			try {
				long m = endDate.getTime() - beginDate.getTime();
				long abs = Math.abs(m);
				int s = (int) (abs / 1000);
				hours = s / 3600;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (hours > 1) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}


	/**
	 * 判断某一时间是否在指定的时间之后(包含与指定时间相等的情况)
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static boolean compareDate(String beginDate, Date endDate) {
		if ("".equals(beginDate) || beginDate == null) {
			return false;
		}
		return compareDate(parseDate(beginDate), endDate);
	}

	/**
	 * 将时间转为时间差
	 * @param date
	 * @return
	 */
	public static Timestamp dateToTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

	/**
	 * String-->Timestamp, String的类型必须为：yyyy-MM-dd hh:mm:ss[.f...]
	 * 这样的格式，中括号表示可选，否则报错
	 * 
	 * @param s
	 * @return
	 */
	public static Timestamp stringToTimestamp(String s) {
		return Timestamp.valueOf(s);
	}

	/**
	 * 根据指定的格式，将时间差转为特定的时间
	 * @param timestamp
	 * @param pattern
	 * @return
	 */
	public static String timestampToString(Timestamp timestamp, String pattern) {
		return new SimpleDateFormat(pattern).format(timestamp);
	}

	

	

	/**
	 * chenpeng
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateToMinute(Date date) {
		if (date == null)
			return "";
		return new SimpleDateFormat(DATE_FORMAT_MINUTE_CHINESE).format(date);
	}

	/**
	 * 验证时间格式
	 * @param date
	 * @return
	 */
	public static boolean isDateStringValid(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_BAR);
		// 输入对象不为空
		try {
			sdf.parse(date);
			return true;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return false;
		}

	}

	/**
	 * 将日期转换成星期
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateToWeek(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		String today = "";
		switch (day) {
		case 7:
			today = SATARDAY;
			break;
		case 1:
			today = SUNDAY;
			break;
		case 2:
			today = MONDAY;
			break;
		case 3:
			today = TUSDAY;
			break;
		case 4:
			today = WENSDAY;
			break;
		case 5:
			today = THUSDAY;
			break;
		case 6:
			today = FRIDAY;
			break;
		default:
			break;
		}
		return today;
	}

	/**
	 * 根据当前时间，返回特定的时间，如，具体的时间，几天前，几小时前，几分钟前，刚刚
	 * @param date
	 * @return
	 */
	public static String getTimeStringByDate(Date date) {
		long interval = System.currentTimeMillis() - date.getTime();
		long second = interval / 1000;
		long minute = second / 60;
		long hour = minute / 60;
		long day = hour / 24;

		if (day > 5) {
			return sdf.format(date);
		} else if (day > 0) {
			return day + "天前";
		} else if (hour > 0) {
			return hour + "小时前";
		} else if (minute > 0) {
			return minute + "分钟前";
		} else
			return "刚刚";
	}

	/*
	 * 该方法用于判定两个时间间隔是否足够长，如果足够长，则返回对应的时间字符串，如果不是，则返回空串， 该方法主要用于聊天程序时间的显示上
	 */
	public static String getStringByTimeInterval(Date prevDate, Date currentDate) {
		if (prevDate == null) {
			return getTimeStringByDate(currentDate);
		}

		long interval = currentDate.getTime() - prevDate.getTime();
		interval = Math.abs(interval);

		if (interval <= 1000 * 60) {
			return "";
		}

		return getTimeStringByDate(currentDate);
	}

	/**
	 * 时间戳转换成字符窜，时间格式自定义
	 * DATE_FORMAT_BAR = "yyyy-MM-dd";DATE_FORMAT_MONTH = "yyyy-MM";
	 * DATE_FORMAT_HOUR = "yyyy-MM-dd HH";DATE_FORMAT_SIMPLE = "yyyy/MM/dd HH:mm";DATE_FORMAT_MINUTE = "yyyy-MM-dd HH:mm";DATE_FORMAT_MINUTE2 = "yyyy.MM.dd HH:mm";
	 * DATE_FORMAT_MINUTEW = "yyyy.MM.dd";DATE_FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss";DATE_FORMAT_MINUTE_CHINESE = "yyyy年MM月dd日HH时mm分";DATE_FORMAT_SPRIT = "yyyy/MM/dd";
	 *  DATE_FORMAT_MILLISECOND = "yyyy-MM-dd HH:mm:ss.SSS";DATE_FORMAT_CHINESE = "yyyy年MM月dd日"; DATE_FORMAT_Second_CHINESE = "yyyy年MM月dd日HH时mm分ss秒";
	 * @param time 时间戳
	 * @param foramt 时间格式
	 * @return
	 */
	public static String formatTimeStamp(long time, String foramt) {
		Date d = new Date(time);
		SimpleDateFormat sf = new SimpleDateFormat(foramt);
		return sf.format(d);
	}

	

	/**
	 * 时间戳转换成字符窜，时间格式自定义
	 * DATE_FORMAT_BAR = "yyyy-MM-dd";DATE_FORMAT_MONTH = "yyyy-MM";
	 * DATE_FORMAT_HOUR = "yyyy-MM-dd HH";DATE_FORMAT_SIMPLE = "yyyy/MM/dd HH:mm";DATE_FORMAT_MINUTE = "yyyy-MM-dd HH:mm";DATE_FORMAT_MINUTE2 = "yyyy.MM.dd HH:mm";
	 * DATE_FORMAT_MINUTEW = "yyyy.MM.dd";DATE_FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss";DATE_FORMAT_MINUTE_CHINESE = "yyyy年MM月dd日HH时mm分";DATE_FORMAT_SPRIT = "yyyy/MM/dd";
	 *  DATE_FORMAT_MILLISECOND = "yyyy-MM-dd HH:mm:ss.SSS";DATE_FORMAT_CHINESE = "yyyy年MM月dd日"; DATE_FORMAT_Second_CHINESE = "yyyy年MM月dd日HH时mm分ss秒";
	 * @param time 时间戳
	 * @param foramt 时间格式
	 * @return
	 */
	public static String formatTimeStamp(String time, String foramt) {
		if (TextUtils.isEmpty(time) || "null".equals(time)) {
			return "";
		}
		long tt = Long.parseLong(time);
		Date d = new Date(tt);
		SimpleDateFormat sf = new SimpleDateFormat(foramt);
		return sf.format(d);

	}
	
	
	/**
	 *  由一种时间格式转化成另一种时间格式,tiemForamt1 转化成timeFormat2
	 * @param date
	 * @param tiemForamt1
	 * @param timeFormat2
	 * @return
	 */
	public static String conversionTimeFormat(String date,String tiemForamt1,String timeFormat2) {
		String dateReturn = null;
		try {
			Date dateS = new SimpleDateFormat(tiemForamt1, Locale.getDefault())
					.parse(date);
			dateReturn = new SimpleDateFormat(timeFormat2, Locale.getDefault())
					.format(dateS);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return dateReturn;

	}
	
	/**
	 * 由两个时间点，得到相差的天数
	 * @param fDate
	 * @param oDate
	 * @return
	 */
	public static int daysOfTwo(Date fDate, Date oDate) {
		long intervalMilli = oDate.getTime() - fDate.getTime();
		return (int) (intervalMilli / (24 * 60 * 60 * 1000));
	}
	
	/**
	 * 截取毫秒
	 * @param s
	 * @return
	 */
	public static String getMiliTime(String s) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			s = String.valueOf(simpledateformat.parse(s).getTime()).substring(0, 10);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return s;
	}

	/**
	 * 生成十位时间戳
	 *
	 * @param time
	 * @return
	 */
	public static String getTimeStamp(String time) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long unixTimestamp = date.getTime() / 1000;

		return unixTimestamp + "";
	}
	
}
