package com.bis.inventory.services;

import com.bis.domain.Stock;
import com.bis.inventory.repository.StockRepository;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Date;

import static org.mockito.Mockito.when;


public class StockServiceImplTest {

    @Mock
    private StockRepository mockRepository;
    private StockServiceImpl stockService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        stockService = new StockServiceImpl(mockRepository);
    }

    @Test
    public void shouldGetStockFromRepository(){
        Date date = Calendar.getInstance().getTime();
        Stock stock = new Stock();
        when(mockRepository.getStock(123, date)).thenReturn(stock);

        Stock actual = stockService.getStock(123, date);

        Assert.assertEquals(stock,actual);
    }
}
