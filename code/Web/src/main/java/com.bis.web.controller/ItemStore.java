package com.bis.web.controller;

import com.bis.domain.Item;

import java.util.HashMap;
import java.util.Map;

public class ItemStore {

    private Map<Integer,Item> itemMap = new HashMap<Integer, Item>();
    private static ItemStore itemStore;


    private ItemStore() {
        itemMap.put(1,item(1,"name1","description1",'W','R'));
        itemMap.put(2,item(2,"name2","description1",'W','R'));
        itemMap.put(3,item(3,"name3","description1",'W','R'));
    }

    private Item item(int code, String name, String description, char type, char returnable) {
        Item item = new Item();
        item.setItemCode(code);
        item.setItemName(name);
        item.setDescription(description);
        item.setItemLife(type);
        item.setReturnable(returnable);
        return item;
    }

    public static ItemStore getInstance(){
        if(itemStore == null) {
            itemStore = new ItemStore();
        }
        return itemStore;
    }

    public Map<Integer, Item> getItemMap() {
        return itemMap;
    }
}
