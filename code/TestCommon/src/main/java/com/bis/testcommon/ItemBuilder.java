package com.bis.testcommon;

import com.bis.domain.Item;

public class ItemBuilder {
    private Item  item = new Item();

    public Item build(){
        return this.item;
    }

    public ItemBuilder withDefaults(){
       Item item = new Item("testName","old",'W','Y');
        item.setItemName("name");
        item.setDescription("description");
        item.setItemLife('W');
        item.setReturnable('Y');
        return this;
    }
}
