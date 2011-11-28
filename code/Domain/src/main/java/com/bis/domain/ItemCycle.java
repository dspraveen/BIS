package com.bis.domain;


public enum ItemCycle {

    WEEKLY('W'),MONTHLY('M'),FORTNIGHT('F'),DAILY('D');

    private char code;

    ItemCycle(char code) {
        this.code = code;
    }

    public char getCode() {
        return code;
    }

    public static String  getNameByCode(char code){
        for (ItemCycle itemCycle : ItemCycle.values()) {
            if(itemCycle.getCode()==code){
                return itemCycle.name();
            }
        }
        throw new RuntimeException("Invalid code");
    }
}
