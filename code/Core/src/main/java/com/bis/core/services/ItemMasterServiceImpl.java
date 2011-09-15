package com.bis.core.services;

import com.bis.core.repository.ItemRepository;
import com.bis.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemMasterServiceImpl implements ItemMasterService{

    private ItemRepository itemRepository;

    @Autowired
    public ItemMasterServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void create(Item item) {
        itemRepository.save(item);
    }

    @Override
    public void update(Item item) {
        itemRepository.update(item);
    }

    @Override
    public Item get(int itemCode) {
        return itemRepository.get(itemCode);
    }
}
