package com.qin.util;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * 时间格式化工具类 线程安全
 * */
public class DateUtils {

    public static final String STANDARD="yyyy-MM-dd:HH-mm-ss";

    //String2Date
    public static Date string2Date(String strDate){
        DateTimeFormatter dateTimeFormatter= DateTimeFormat.forPattern(STANDARD);
        DateTime dateTime=dateTimeFormatter.parseDateTime(strDate);
        return dateTime.toDate();
    }
    public static Date string2Date(String strDate,String format){
        DateTimeFormatter dateTimeFormatter= DateTimeFormat.forPattern(format);
        DateTime dateTime=dateTimeFormatter.parseDateTime(strDate);
        return dateTime.toDate();
    }

    //Date2String
    public static String date2String(Date date){
        if(date==null)
            return StringUtils.EMPTY;
        DateTime dateTime=new DateTime(date);
        return dateTime.toString(STANDARD);
    }
    public static String date2String(Date date,String format){
        if(date==null)
            return StringUtils.EMPTY;
        DateTime dateTime=new DateTime(date);
        return dateTime.toString(format);
    }
}
