package com.bis.testcommon;

import org.hibernate.SessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSessionFactory {

    public  static SessionFactory getSessionFactory(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("testApplicationContext.xml");
        return (SessionFactory) context.getBean("sessionFactory");
    }
}
