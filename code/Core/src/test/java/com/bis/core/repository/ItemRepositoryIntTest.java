package com.bis.core.repository;

import com.bis.domain.Item;
import com.bis.testcommon.BaseIntTest;
import com.bis.testcommon.ItemBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemRepositoryIntTest extends BaseIntTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void shouldPerformCRUDOnOItem(){
        Item item = new ItemBuilder().withDefaults().build();
        itemRepository.save(item);

        Item dbItem = itemRepository.get(item.getItemCode());
        Assert.assertEquals(item,dbItem);

        dbItem.setDescription("new");
        itemRepository.save(dbItem);

        Item updatedItem = itemRepository.get(dbItem.getItemCode());
        Assert.assertEquals("new",updatedItem.getDescription());

        itemRepository.delete(item);
        Assert.assertNull(itemRepository.get(item.getItemCode()));
    }

}
