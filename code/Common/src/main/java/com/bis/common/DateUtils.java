package com.bis.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Date currentDate() {
        return Calendar.getInstance().getTime();
    }

    public static Date infinityDate() {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR,9000);
        instance.set(Calendar.MONTH,12);
        instance.set(Calendar.DATE,31);
        instance.set(Calendar.HOUR_OF_DAY,23);
        instance.set(Calendar.MINUTE,59);
        instance.set(Calendar.SECOND,59);
        return instance.getTime();
    }

    public static Date addMinutes(Date date, int minutes) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.MINUTE, minutes);
        return instance.getTime();
    }

    public static Date getNowDate() {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        instance.set(Calendar.MILLISECOND, 0);
        return instance.getTime();
    }

    public static Date addSecond(Date date, int seconds) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.SECOND, seconds);
        return instance.getTime();
    }

    public static Date addDays(Date date, int days) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.DATE, days);
        return instance.getTime();
    }


    public static boolean isGreaterOrEqual(Date dateOne, Date dateTwo) {
        return dateOne.compareTo(dateTwo) >= 0;
    }

    public static Date addTimeToDate(Date date) {
        Calendar currentDate = Calendar.getInstance();

        Calendar dateValue = Calendar.getInstance();
        dateValue.setTime(date);
        dateValue.set(Calendar.HOUR_OF_DAY, currentDate.get(Calendar.HOUR_OF_DAY));
        dateValue.set(Calendar.MINUTE, currentDate.get(Calendar.MINUTE));
        dateValue.set(Calendar.SECOND, currentDate.get(Calendar.SECOND));

        return dateValue.getTime();
    }

    public static Date addMonth(Date date, int month) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.MONTH, month);
        return instance.getTime();
    }

    public static String defaultFormat(Date date) {
        if (date == null) return null;
        return new SimpleDateFormat("dd-MM-yyyy").format(date);
    }

    public static Date setTimeToZero(Date date) {

        Calendar dateValue = Calendar.getInstance();
        dateValue.setTime(date);
        dateValue.set(Calendar.HOUR_OF_DAY, 0);
        dateValue.set(Calendar.MINUTE, 0);
        dateValue.set(Calendar.SECOND, 0);
        dateValue.set(Calendar.MILLISECOND, 0);

        return dateValue.getTime();
    }

}
