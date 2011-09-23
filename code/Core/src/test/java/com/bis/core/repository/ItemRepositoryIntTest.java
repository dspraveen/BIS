package com.bis.core.repository;

import com.bis.domain.Item;
import com.bis.testcommon.BaseIntTest;
import com.bis.testcommon.ItemBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ItemRepositoryIntTest extends BaseIntTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void shouldPerformCRUDOnOItem(){
        Item item = new ItemBuilder().withDefaults().build();
        itemRepository.save(item);

        Item dbItem = itemRepository.get(item.getItemCode());
        assertEquals(item, dbItem);

        dbItem.setDescription("new");
        itemRepository.update(dbItem);

        Item updatedItem = itemRepository.get(dbItem.getItemCode());
        assertEquals("new", updatedItem.getDescription());

        itemRepository.delete(item);
        assertNull(itemRepository.get(item.getItemCode()));
    }

    @Test
    public void shouldGetAllItems(){
        Item itemOne = new ItemBuilder().withDefaults().build();
        Item itemTwo = new ItemBuilder().withDefaults().build();
        itemRepository.save(itemOne);
        itemRepository.save(itemTwo);
        List<Item> all = itemRepository.getAll();
        assertTrue(all.contains(itemOne));
        assertTrue(all.contains(itemTwo));
        itemRepository.delete(itemOne);
        itemRepository.delete(itemTwo);
    }

}
