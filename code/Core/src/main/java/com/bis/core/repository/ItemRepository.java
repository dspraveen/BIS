package com.bis.core.repository;

import com.bis.domain.Item;
import com.bis.repository.BaseRepository;

public class ItemRepository extends BaseRepository<Item>{
    protected ItemRepository() {
        super(Item.class);
    }
}
