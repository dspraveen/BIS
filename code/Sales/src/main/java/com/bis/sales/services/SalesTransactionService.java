package com.bis.sales.services;

import com.bis.domain.SalesTransaction;

import java.util.Date;
import java.util.List;

public interface SalesTransactionService {
    void addSalesTransaction(SalesTransaction salesTransaction);
    void updateSalesTransaction(SalesTransaction salesTransaction);
    SalesTransaction getSalesTransaction(int transactionId);
    List<SalesTransaction> getSalesTransactions(Date fromDate, Date toDate);
    List<SalesTransaction> getSalesTransactions(Date fromDate, Date toDate, int hawkerId);
}
