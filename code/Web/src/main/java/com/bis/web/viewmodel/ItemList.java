package com.bis.web.viewmodel;

import com.bis.domain.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemList {

    private int selectedItemCode;
    private float selectedItemPrice;
    private Item selectedItem;
    private Map<Integer, String> itemNames = new HashMap<Integer, String>();

    public ItemList(int selectedItemCode, List<ItemViewForm> items) {
        this.selectedItemCode = selectedItemCode;
        fetchSelectedItemDetails(selectedItemCode, items);
    }

    private void fetchSelectedItemDetails(int selectedItemCode, List<ItemViewForm> itemViewForms) {
        for (ItemViewForm itemViewForm : itemViewForms) {
            itemNames.put(itemViewForm.getItem().getItemCode(), itemViewForm.getItem().getItemName());
            if (itemViewForm.getItem().getItemCode().equals(selectedItemCode)) {
                this.selectedItem = itemViewForm.getItem();
                this.selectedItemPrice = itemViewForm.getItemPrice();
            }
        }
        if (this.selectedItem == null) {
            this.selectedItem = itemViewForms.get(0).getItem();
            this.selectedItemPrice = itemViewForms.get(0).getItemPrice();
        }
    }

    public Item getSelectedItem() {
        return selectedItem;
    }

    public float getSelectedItemPrice() {
        return selectedItemPrice;
    }

    public int getCount() {
        return itemNames.size();
    }

    public int getSelectedItemCode() {
        return selectedItemCode;
    }

    public Map<Integer, String> getItemNames() {
        return itemNames;
    }

    public void setSelectedItemCode(int selectedItemCode) {
        this.selectedItemCode = selectedItemCode;
    }
}
