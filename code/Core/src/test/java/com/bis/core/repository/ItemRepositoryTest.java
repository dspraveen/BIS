package com.bis.core.repository;

import com.bis.testcommon.TestSessionFactory;
import org.junit.Before;
import org.junit.Test;

public class ItemRepositoryTest {

    private ItemRepository itemRepository;

    @Before
    public void setup(){
        itemRepository = new ItemRepository();
        itemRepository.setSessionFactory(TestSessionFactory.getSessionFactory());
    }

    @Test
    public void shouldSaveItem(){
        itemRepository.get(2);
    }
}
