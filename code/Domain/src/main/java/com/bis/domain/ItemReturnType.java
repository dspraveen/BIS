package com.bis.domain;

public enum ItemReturnType {

    YES('Y'),NO('N');
    private char code;

    ItemReturnType(char code) {
        this.code = code;
    }

    public char getCode() {
        return code;
    }
}
