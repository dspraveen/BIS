package com.bis.sales.services;

import com.bis.domain.SalesTransaction;

public interface SalesService {

    void addSalesTransaction(SalesTransaction salesTransaction);
    void updateSalesTransaction(SalesTransaction salesTransaction);
    SalesTransaction getSalesTransaction(int transactionId);
}
