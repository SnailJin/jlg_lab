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
    public static FastDateFormat getFastDateFormat(final String pattern,final TimeZone timeZone,final Locale locale){
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
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        return calendar.getTime();
    }
    /**
     * 获取一天的结束时间
     * 一天23：59：59
     * @param time
     * @return
     */
    public static Date getEndDay(Date time){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.add(Calendar.DATE,1);
        calendar.setTimeInMillis( calendar.getTime().getTime()-1000);
        return calendar.getTime();
    }

    /**
     * 相隔时间
     * @param startDate
     * @param endDate
     * @return
     */
    public static int betweenDay(Date startDate,Date endDate){
        //根据0点时间计算
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);
        int day = (int)(toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24);
        return day;
    }

    /**
     * 是不是昨天
     * @param time
     * @return
     */
    public static boolean isYesterday(Date time){
        if(time == null){
            return false;
        }
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        String nowDay = format(calendar.getTime(),yyyyMMddHH);
        String compareDay = format(time,yyyyMMddHH);
        //比较
        return nowDay.equals(compareDay);
    }


    public static void main(String[] args) throws ParseException {
        Date date = DateTools.getEndDay(new Date());
        System.out.println(date);
        System.out.println(DateTools.format(date));
        System.out.println(DateTools.getYear(date));
        System.out.println(DateTools.getMonth(date));
        System.out.println(DateTools.getDay(date));
    }
}
