package com.bis.common;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Date;

public class DateUtilsTest {

    @Test
    public void shouldCheckIfGreater() {
        Date currentDate = DateUtils.currentDate();
        Assert.assertTrue(DateUtils.isGreaterOrEqual(currentDate, DateUtils.addDays(currentDate, -2)));
    }

    @Test
     public void testDateTimeAdd() {
        //Date currentDate = DateUtils.currentDate();
        Date dateOne = DateUtils.addDays(DateUtils.currentDate(),-2);
        Date date = DateUtils.addTimeToDate(dateOne);
        System.out.println(date);
    }

    @Test
    public void testInfinityDate() {
        Date infinity = DateUtils.infinityDate();
        Date date = DateUtils.currentDate();
        Assert.assertTrue(infinity.after(date));
    }




}

