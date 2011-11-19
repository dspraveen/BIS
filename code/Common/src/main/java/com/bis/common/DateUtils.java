package com.bis.common;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public  static Date currentDate(){
        return Calendar.getInstance().getTime();
    }

    public static Date addMinutes(Date date, int minutes){
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.MINUTE,minutes);
        return instance.getTime();
    }

    public static Date getNowDate(){
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        instance.set(Calendar.MILLISECOND, 0);
        return instance.getTime();
    }

    public static Date addSecond(Date date,int seconds) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.SECOND,seconds);
        return instance.getTime();
    }
}
