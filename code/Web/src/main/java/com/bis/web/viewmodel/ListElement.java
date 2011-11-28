package com.bis.web.viewmodel;

public class ListElement {
    private String key;

    private String value;

    public ListElement(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
