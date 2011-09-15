package com.bis.web.controller;

import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppTest extends TestCase{

    @Test
    @Ignore
    public void testGetHello() throws Exception {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println("abcd");


    }
}
