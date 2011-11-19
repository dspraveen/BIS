package com.bis.domain;


public enum BillingCycle {

    WEEKLY('W'),MONTHLY('M'),FORTNIGHT('F');

    private char code;

    BillingCycle(char code) {
        this.code = code;
    }

    public char getCode() {
        return code;
    }

    public static String  getNameByCode(char code){
        for (BillingCycle billingCycle : BillingCycle.values()) {
            if(billingCycle.getCode()==code){
                return billingCycle.name();
            }
        }
        throw new RuntimeException("Invalid code");
    }
}
