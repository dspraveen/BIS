package com.bis.core.services;

import com.bis.core.repository.ItemRepository;
import com.bis.domain.Item;
import com.bis.domain.ItemReturnType;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class ItemMasterServiceImpl implements ItemMasterService {

    private ItemRepository itemRepository;

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

    @Override
    public List<Item> getAll() {
        return itemRepository.getAll();
    }

    @Override
    public List<Item> getAllReturnableItems() {
        return (List<Item>) CollectionUtils.select(getAll(), new Predicate() {
            @Override
            public boolean evaluate(Object object) {
                Item item = (Item) object;
                return ItemReturnType.YES.getCode() == item.getReturnable();
            }
        });
    }
}
