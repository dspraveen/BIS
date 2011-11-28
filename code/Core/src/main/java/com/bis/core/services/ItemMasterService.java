package com.bis.core.services;


import com.bis.domain.Item;

import java.util.List;

public interface ItemMasterService {

    void create(Item item);
    void update(Item item);
    Item get(int itemCode);
    List<Item> getAll();
}
