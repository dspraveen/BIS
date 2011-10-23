package com.bis.web.controller;

import java.util.ArrayList;
import java.util.List;

public class Row {
    private String name;
    private List<Cell> cells = new ArrayList<Cell>();

    public Row(String name, List<Cell> cells) {
        this.name = name;
        this.cells = cells;
    }

    public String getName() {
        return name;
    }

    public List<Cell> getColumns() {
        return cells;
    }
}
