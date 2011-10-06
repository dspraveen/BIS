package com.bis.core.services;

import com.bis.core.repository.ItemRepository;
import com.bis.domain.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;

public class ItemMasterServiceImplTest {
    @Mock
    private ItemRepository itemRepository;
    private ItemMasterServiceImpl itemMasterService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        itemMasterService = new ItemMasterServiceImpl(itemRepository);
    }

    @Test
    public void shouldCreateNewItem() throws Exception {
        Item item = new Item();
        itemMasterService.create(item);
        verify(itemRepository).save(item);
    }

    @Test
    public void shouldUpdateItem() throws Exception {
        Item item = new Item();
        itemMasterService.update(item);
        verify(itemRepository).update(item);
    }

    @Test
    public void shouldGetItem() throws Exception {
        Item item = new Item();
        Mockito.when(itemRepository.get(12)).thenReturn(item);
        Assert.assertEquals(item,itemMasterService.get(12));
    }

    @Test
    public void shouldGetAllItem() throws Exception {
        ArrayList<Item> items = new ArrayList<Item>();
        Mockito.when(itemRepository.getAll()).thenReturn(items);
        Assert.assertEquals(items,itemMasterService.getAll());
    }

    @Test
    public void shouldSetItemPrice(){
        int  itemCode = 101;
        float price = 10f;
        Mockito.when(itemRepository.setItemPrice(itemCode, price)).thenReturn(price);
        Assert.assertTrue(price==itemMasterService.setItemPrice(itemCode, price));
    }

    @Test
    public void shouldGetItemPrice(){
        int  itemCode = 101;
        float price = 10f;
        Mockito.when(itemRepository.getPrice(itemCode)).thenReturn(price);
        Assert.assertTrue(price==itemMasterService.getItemPrice(itemCode));
    }
}
