package com.bis.domain;

public enum SalesTransactionType {

    SALES('S'), RETURNS('R'), SCRAP('C');

    private char code;

    SalesTransactionType(char code) {
        this.code = code;
    }

    public char getCode() {
        return code;
    }

    public static String getNameByCode(char code) {
        for (SalesTransactionType paymentMode : SalesTransactionType.values()) {
            if (paymentMode.getCode() == code) {
                return paymentMode.name();
            }
        }
        throw new RuntimeException("Invalid code");
    }

}
