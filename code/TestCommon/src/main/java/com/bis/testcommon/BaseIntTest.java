package com.bis.testcommon;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.jdbc.Work;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import static org.apache.commons.io.FileUtils.readFileToString;

@ContextConfiguration("classpath:testApplicationContext.xml")
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class BaseIntTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    @Before
    public void setUp() throws SQLException {
        final Session session = sessionFactory.openSession();
        session.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                try {
                    String bisRoot = System.getenv("BIS_ROOT");
                    if(StringUtils.isEmpty(bisRoot)) throw new Exception("Please set BIS_ROOT env on your machine");
                    connection.createStatement().execute("DROP SCHEMA IF EXISTS BIS");
                    connection.createStatement().execute("CREATE SCHEMA IF NOT EXISTS BIS");
                    connection.createStatement().execute("SET SCHEMA BIS");
                    System.out.println(new File("../database/Release_1.0/create_tables.sql").getAbsolutePath());
                    connection.createStatement().execute(readFileToString(new File(bisRoot+"/database/Release_1.0/create_tables.sql")).replaceAll("FLOAT\\(.*,.*\\)", "FLOAT"));
                    connection.createStatement().execute(readFileToString(new File(bisRoot+"/database/Release_Current/create_tables.sql")).replaceAll("FLOAT\\(.*,.*\\)", "FLOAT"));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        session.close();
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
