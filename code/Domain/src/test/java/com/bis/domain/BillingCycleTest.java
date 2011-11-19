package com.bis.domain;

import junit.framework.Assert;
import org.junit.Test;

public class BillingCycleTest {

    @Test
    public void shouldGetNameByCode(){
        Assert.assertEquals("WEEKLY", BillingCycle.getNameByCode('W'));
    }
}
