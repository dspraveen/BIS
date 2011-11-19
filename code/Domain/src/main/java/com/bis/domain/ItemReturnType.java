package com.bis.domain;

public enum ItemReturnType {

    YES('Y'), NO('N');
    private char code;

    ItemReturnType(char code) {
        this.code = code;
    }

    public char getCode() {
        return code;
    }

    public static String getNameByCode(char code) {
        for (ItemReturnType itemReturnType : ItemReturnType.values()) {
            if (itemReturnType.getCode() == code) {
                return itemReturnType.name();
            }
        }
        throw new RuntimeException("Invalid code");
    }
}
