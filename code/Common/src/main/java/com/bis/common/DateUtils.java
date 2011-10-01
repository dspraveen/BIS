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
}
