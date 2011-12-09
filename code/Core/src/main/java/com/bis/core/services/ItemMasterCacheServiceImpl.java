package com.bis.core.services;

import com.bis.common.BisCacheManager;
import com.bis.core.repository.ItemRepository;
import com.bis.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemMasterCacheServiceImpl extends ItemMasterServiceImpl {

    public static final String ITEMS = "items";
    private BisCacheManager bisCacheManager;

    @Autowired
    public ItemMasterCacheServiceImpl(ItemRepository itemRepository, BisCacheManager bisCacheManager) {
        super(itemRepository);
        this.bisCacheManager = bisCacheManager;
    }

    @Override
    public void create(Item item) {
        super.create(item);
        bisCacheManager.remove(ITEMS);
    }

    @Override
    public void update(Item item) {
        super.update(item);
        bisCacheManager.remove(ITEMS);
    }

    @Override
    public Item get(int itemCode) {
        if (bisCacheManager.cached(ITEMS)) {
            List<Item> items = (List<Item>) bisCacheManager.get(ITEMS);
            for (Item item : items) {
                if (itemCode == item.getItemCode()) return item;
            }
        }
        return super.get(itemCode);
    }

    @Override
    public List<Item> getAll() {
        if (bisCacheManager.notCached(ITEMS)) {
            bisCacheManager.cache(ITEMS, super.getAll());
        }
        return (List<Item>) bisCacheManager.get(ITEMS);
    }

    @Override
    public List<Item> getAllReturnableItems() {
        return super.getAllReturnableItems();
    }
}
