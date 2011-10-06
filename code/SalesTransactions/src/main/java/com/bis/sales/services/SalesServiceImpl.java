package com.bis.sales.services;


import com.bis.domain.SalesTransaction;
import com.bis.sales.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SalesServiceImpl implements SalesService {

    private SalesRepository salesRepository;

    @Autowired
    public SalesServiceImpl(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    @Override
    public void addSalesTransaction(SalesTransaction salesTransaction) {
        salesTransaction.calculateAndSetTotalAmount();
        salesRepository.save(salesTransaction);
    }

    @Override
    public void updateSalesTransaction(SalesTransaction salesTransaction) {
        salesTransaction.calculateAndSetTotalAmount();
        salesRepository.update(salesTransaction);
    }

    @Override
    public SalesTransaction getSalesTransaction(int transactionId) {
        return salesRepository.get(transactionId);
    }
    @Override
    public List<SalesTransaction> getSalesTransactions(Date fromDate, Date toDate){
        return salesRepository.getSalesTransactions(fromDate, toDate);
    }
}
