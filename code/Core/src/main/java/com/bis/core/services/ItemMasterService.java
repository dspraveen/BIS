package com.bis.core.services;


import com.bis.domain.Item;

public interface ItemMasterService {

    void create(Item item);
    void update(Item item);
    Item get(int itemCode);
}
