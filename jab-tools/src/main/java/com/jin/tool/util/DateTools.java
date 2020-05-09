package com.jin.tool.util;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * 时间工具类
 * @author jinliguang
 * @version 1.0.0
 * @see org.apache.commons.lang3.time.FastDateFormat
 * @see org.apache.commons.lang3.time.DateUtils
 * @sine 2019-05-25
 */
public class DateTools {

    private static final String defaultFormate =  "yyyy-MM-dd:HH:mm:ss";
    /**
     * 时间格式
     */
    public static final String yyyyMMddHHmmss ="yyyy-MM-dd HH:mm:ss";
    public static final String yyyyMMddHH ="yyyy-MM-dd";
    public static final String YEAR ="yyyy";
    public static final String MONTH ="MM";
    public static final String DAY ="dd";
    public static final String DEFULT_FORMATE = yyyyMMddHHmmss;

    /**
     * 时区
     */
    private static final TimeZone DEFULT_TIME_ZONE = TimeZone.getDefault();
    private static final TimeZone SHANGHAI_TIME_ZONE = TimeZone.getTimeZone("Asia/Shanghai");
    /**
     * 语言
     */
    private static final Locale DEFULT_LOCALE = Locale.getDefault();


    /**
     * date 转stirng
     * @param date
     * @param pattern 正则匹配
     * @return
     */
    public static String format(Date date,final String pattern){
        if(Utils.isNull(date)){
            throw new IllegalArgumentException("参数错误!");
        }
        return getFastDateFormat(pattern,DEFULT_TIME_ZONE,DEFULT_LOCALE).format(date);
    }
    /**
     * date 转 string
     * yyyy-MM-dd HH:mm：ss
     * @param date
     * @return
     */
    public static String format(Date date){
        return format(date,DEFULT_FORMATE);
    }


    /**
     * 获取 getFastDateFormat 实例
     * @param pattern
     * @param timeZone
     * @param locale
     * @return
     */
    public static FastDateFormat getFastDateFormat(final String pattern, final TimeZone timeZone, final Locale locale){
        return FastDateFormat.getInstance(pattern,timeZone,locale);
    }

    /**
     *  获取年
     * @param time
     * @return
     */
    public static String getYear(Date time){
        return format(time,YEAR);
    }

    /**
     * 获取月份
     * @param time
     * @return
     */
    public static String getMonth(Date time){
        return format(time,MONTH);
    }
    /**
     * 获取天
     * @param time
     * @return
     */
    public static String getDay(Date time){
        return format(time,DAY);
    }

    /**
     * string 转 date 函数
     * @param str 字符串
     * @param pattern 正则表达式
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String str,String pattern) throws ParseException {
        if(Utils.isNull(str)){
            throw new IllegalArgumentException("参数错误!");
        }
        return DateUtils.parseDate(str,pattern);
    }
    /**
     * string 转 date 函数
     */
    public static Date parseDate(String str) throws ParseException {
        return parseDate(str,DEFULT_FORMATE);
    }

    /**
     * 获取一天的开始时间
     * 一天0点
     * @param time
     * @return
     */
    public static Date getStartDay(Date time){
        if(Utils.isNull(time)){
            throw new IllegalArgumentException("参数错误!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
    /**
     * 获取一天的结束时间
     * 一天23：59：59
     * @param time
     * @return
     */
    public static Date getEndDay(Date time){
        if(Utils.isNull(time)){
            throw new IllegalArgumentException("参数错误!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis( time.getTime());
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DATE,1);
        calendar.setTimeInMillis( calendar.getTimeInMillis()-1000);
        return calendar.getTime();
    }



    /**
     * 是不是昨天
     * @param time
     * @return
     */
    public static boolean isYesterday(Date time){
        Date now=new Date();
        //比较
        return betweenDay(time,now) == 1;
    }

    /**
     * 相隔天数
     * @param startDate
     * @param endDate
     * @return
     */
    public static int betweenDay(Date startDate,Date endDate){
        if(Utils.isNull(startDate) ||  Utils.isNull(endDate)  || startDate.compareTo(endDate)>=0){
            throw new IllegalArgumentException("参数错误!");
        }
        //根据0点时间计算
        startDate = getStartDay(startDate);
        endDate = getStartDay(endDate);
        int day = Integer.parseInt((endDate.getTime() -startDate.getTime()) / (1000 * 60 * 60 * 24)+"");
        return day;
    }

    /**
     * 相隔月份
     * @param startDate
     * @param endDate
     * @return
     */
    public static int betweenMonth(Date startDate,Date endDate){
        if(Utils.isNull(startDate) ||  Utils.isNull(endDate)  || startDate.compareTo(endDate)>=0){
            throw new IllegalArgumentException("参数错误!");
        }
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);

        int month = toCalendar.get(Calendar.MONTH) - fromCalendar.get(Calendar.MONTH);
        int year = toCalendar.get(Calendar.YEAR) - fromCalendar.get(Calendar.YEAR);
        return ( month + 12* year);
    }

    /**
     * 获取一个月天数
     * @param date
     * @return
     */
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static void main(String[] args) throws ParseException {
        Date date = DateTools.getEndDay(new Date());
        Date startTime = parseDate("2019-07-01 00:00:00");
        Date endTime =  parseDate("2019-08-19 59:00:00");
        System.out.println(betweenMonth(startTime,endTime));
    }
}
