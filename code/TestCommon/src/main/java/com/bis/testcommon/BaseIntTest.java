package com.bis.testcommon;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration("classpath:testApplicationContext.xml")
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class BaseIntTest extends AbstractTransactionalJUnit4SpringContextTests{


}
