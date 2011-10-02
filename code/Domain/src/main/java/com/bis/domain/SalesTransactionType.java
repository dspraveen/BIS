package com.bis.domain;

public enum SalesTransactionType {

    SALES('S'),RETURNS('R'),SCRAP('C');

    private char code;

    SalesTransactionType(char code) {
        this.code = code;
    }

    public char getCode() {
        return code;
    }

}
