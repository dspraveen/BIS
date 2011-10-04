package com.bis.domain;


public enum PaymentMode {

    CASH('C'),CHEQUE('Q'),DEMANDDRAFT('D');

    private char code;

    PaymentMode(char c) {
        this.code = c;
    }

    public char getCode() {
        return code;
    }
}
