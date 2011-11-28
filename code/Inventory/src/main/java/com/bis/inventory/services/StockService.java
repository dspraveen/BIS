/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bis.inventory.services;


import com.bis.domain.Stock;
import java.util.List;
import java.util.Date;

public interface StockService {
    
    Stock getStock( int itemCode, Date dateOfPublishing);
    List<Stock> getAllStock();
    void addStock( int itemCode, Date dateOfPublishing, int quantity);
    void reduceStock(  int itemCode, Date dateOfPublishing, int quantity);
    List<Stock> readExpiredStock( int itemCode);
    List<Stock> getAllStock(int itemCode, Date fromDateOfPublish, Date endDateOfPublish);
}
