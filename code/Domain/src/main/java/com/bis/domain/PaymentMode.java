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

    public static String  getNameByCode(char code){
        for (PaymentMode paymentMode : PaymentMode.values()) {
            if(paymentMode.getCode()==code){
                return paymentMode.name();
            }
        }
        throw new RuntimeException("Invalid code");
    }
}
