package com.bis.core.repository;

import com.bis.domain.Item;
import com.bis.testcommon.BaseIntTest;
import com.bis.testcommon.ItemBuilder;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

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

    @Test
    public void shouldSetItemPrice(){
        Item item = new ItemBuilder().withDefaults().build();
        itemRepository.save(item);
        itemRepository.setItemPrice(item.getItemCode(), 12);
        float updatedPrice = itemRepository.setItemPrice(item.getItemCode(), 24);
        Assert.assertEquals(24f,updatedPrice);
    }

    @Test
    public void shouldGetItemPrice(){
        Item item = new ItemBuilder().withDefaults().build();
        itemRepository.save(item);
        itemRepository.setItemPrice(item.getItemCode(), 24);
        float price = itemRepository.getPrice(item.getItemCode());
        Assert.assertEquals(24f,price);
    }

}
