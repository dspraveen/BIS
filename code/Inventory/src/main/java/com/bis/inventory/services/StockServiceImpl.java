package com.bis.inventory.services;


import com.bis.domain.Stock;
import com.bis.inventory.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StockServiceImpl implements StockService{

    private StockRepository stockRepository;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public Stock getStock(int itemCode, Date dateOfPublishing) {
        return stockRepository.getStock(itemCode,dateOfPublishing);
    }

    @Override
    public List<Stock> getAllStock(int itemCode) {
        return stockRepository.getAllStock(itemCode);
    }

    @Override
    public void addStock( int itemCode, Date dateOfPublishing, int quantity) {
        Stock stock = stockRepository.getStock(itemCode, dateOfPublishing);
        if(stock == null){
            Stock newStock = new Stock(itemCode, dateOfPublishing, quantity);
            stockRepository.save(newStock);
        }else {
            stock.setQuantity(stock.getQuantity()+quantity);
            stockRepository.update(stock);
        }
        return;
    }

    @Override
    public void reduceStock( int itemCode, Date dateOfPublishing, int quantity) {
        Stock stock = stockRepository.getStock(itemCode, dateOfPublishing);
        if(stock == null){
            Stock newStock = new Stock(itemCode, dateOfPublishing, quantity);
            stockRepository.save(newStock);
        }else {
            int effectiveQty = stock.getQuantity() - quantity;
            if(effectiveQty <0){
                throw new RuntimeException("Stock not available");
            }
            stock.setQuantity(effectiveQty);
            stockRepository.update(stock);
        }
        return;
    }

    @Override
    public List<Stock> readExpiredStock(int itemCode) {
        return null;
    }
}
