package com.bis.domain;

public enum ProcurementTransactionType {
    PURCHASE('S'),RETURNS('R'),SCRAP('C');

    private char code;

    ProcurementTransactionType(char code) {
        this.code = code;
    }

    public char getCode() {
        return code;
    }
}
