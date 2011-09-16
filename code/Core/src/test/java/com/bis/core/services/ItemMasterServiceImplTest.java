package com.bis.core.services;

import com.bis.core.repository.ItemRepository;
import com.bis.domain.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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
    public void testGet() throws Exception {
        Item item = new Item();
        Mockito.when(itemRepository.get(12)).thenReturn(item);
        Assert.assertEquals(item,itemMasterService.get(12));
    }
}
