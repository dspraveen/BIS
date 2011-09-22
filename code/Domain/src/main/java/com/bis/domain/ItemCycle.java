package com.bis.domain;


public enum ItemCycle {

    WEEKLY('W'),MONTHLY('M'),FORTNIGHT('F');

    private char code;

    ItemCycle(char code) {
        this.code = code;
    }

    public char getCode() {
        return code;
    }
}
