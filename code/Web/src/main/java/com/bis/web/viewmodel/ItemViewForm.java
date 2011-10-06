package com.bis.web.viewmodel;

import com.bis.domain.Item;

public class ItemViewForm {

    private Item item;
    private float itemPrice;

    public ItemViewForm() {
        item = new Item();
    }

    public ItemViewForm(Item item, float itemPrice) {
        this.item = item;
        this.itemPrice = itemPrice;
    }

    public Item getItem() {
        return item;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }
}
