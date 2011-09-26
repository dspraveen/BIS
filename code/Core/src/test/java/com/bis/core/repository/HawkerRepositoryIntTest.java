package com.bis.core.repository;

import com.bis.domain.Hawker;
import com.bis.testcommon.BaseIntTest;
import com.bis.testcommon.HawkerBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class HawkerRepositoryIntTest extends BaseIntTest {

    @Autowired
    private HawkerRepository hawkerRepository;

    @Test
    public void shouldPerformCRUDOnOItem(){
        Hawker hawker = new HawkerBuilder().withDefaults().build();
        hawkerRepository.save(hawker);

        Hawker dbHawker = hawkerRepository.get(hawker.getHawkerId());
        assertEquals(hawker, dbHawker);

        dbHawker.setHawkerName("name");
        hawkerRepository.update(dbHawker);

        Hawker updatedHawker = hawkerRepository.get(dbHawker.getHawkerId());
        assertEquals("name", updatedHawker.getHawkerName());

        hawkerRepository.delete(hawker);
        assertNull(hawkerRepository.get(hawker.getHawkerId()));
    }

    @Test
    public void shouldGetAllItems(){
        Hawker hawkerOne = new HawkerBuilder().withDefaults().build();
        Hawker hawkerTwo = new HawkerBuilder().withDefaults().build();
        hawkerRepository.save(hawkerOne);
        hawkerRepository.save(hawkerTwo);
        List<Hawker> all = hawkerRepository.getAll();
        assertTrue(all.contains(hawkerOne));
        assertTrue(all.contains(hawkerTwo));
        hawkerRepository.delete(hawkerOne);
        hawkerRepository.delete(hawkerTwo);
    }

}
