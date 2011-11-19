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

    public static String  getNameByCode(char code){
        for (ProcurementTransactionType procurementTransactionType : ProcurementTransactionType.values()) {
            if(procurementTransactionType.getCode()==code){
                return procurementTransactionType.name();
            }
        }
        throw new RuntimeException("Invalid code");
    }
}
