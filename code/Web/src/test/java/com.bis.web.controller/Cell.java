package com.bis.web.controller;

public class Cell {

    private String name;

    private String value;

    public Cell(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
