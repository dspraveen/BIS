package com.bis.web.viewmodel;

import com.bis.domain.Item;

import java.util.List;

public class ItemList {

    private int selectedItemCode;

    private List<Item> items;

    public ItemList(int selectedItemCode, List<Item> items) {
        this.selectedItemCode = selectedItemCode;
        this.items = items;
    }

    public int getSelectedItemCode() {
        return selectedItemCode;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setSelectedItemCode(int selectedItemCode) {
        this.selectedItemCode = selectedItemCode;
    }

    public Item getSelectedItem() {
        for (Item item : items) {
            if (item.getItemCode().equals(selectedItemCode)) {
                return item;
            }
        }
        return items.get(0);
    }

    public int getCount() {
        return items.size();
    }
}
