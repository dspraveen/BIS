package com.bis.core.services;

import com.bis.core.repository.HawkerRepository;
import com.bis.domain.Hawker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;

public class HawkerMasterServiceImplTest {
    @Mock
    private HawkerRepository hawkerRepository;
    private HawkerMasterService hawkerMasterService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        hawkerMasterService = new HawkerMasterServiceImpl(hawkerRepository);
    }

    @Test
    public void shouldCreateNewHawker() throws Exception {
        Hawker hawker = new Hawker();
        hawkerMasterService.create(hawker);
        verify(hawkerRepository).save(hawker);
    }

    @Test
    public void shouldUpdateHawker() throws Exception {
        Hawker hawker = new Hawker();
        hawkerMasterService.update(hawker);
        verify(hawkerRepository).update(hawker);
    }

    @Test
    public void shouldGetHawker() throws Exception {
        Hawker hawker = new Hawker();
        Mockito.when(hawkerRepository.get(12)).thenReturn(hawker);
        Assert.assertEquals(hawker,hawkerMasterService.get(12));
    }

    @Test
    public void shouldGetAllHawkers() throws Exception {
        ArrayList<Hawker> hawkers = new ArrayList<Hawker>();
        Mockito.when(hawkerRepository.getAll()).thenReturn(hawkers);
        Assert.assertEquals(hawkers,hawkerMasterService.getAll());
    }
}
