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
}
