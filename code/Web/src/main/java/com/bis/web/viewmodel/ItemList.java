package com.bis.web.viewmodel;

import com.bis.domain.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemList {

    private int selectedItemCode;
    private List<Item> items = new ArrayList<Item>();

    public ItemList(int selectedItemCode, List<Item> items) {
        this.selectedItemCode = selectedItemCode;
        this.items = items;
    }

    public Item getSelectedItem() {
        for (Item item : items) {
            if (selectedItemCode == item.getItemCode()) {
                return item;
            }
        }
        return items.get(0);
    }

    public List<Item> getItems() {
        return items;
    }

    public int getCount() {
        return items.size();
    }

    public int getSelectedItemCode() {
        return selectedItemCode;
    }

    public void setSelectedItemCode(int selectedItemCode) {
        this.selectedItemCode = selectedItemCode;
    }
}
