package com.bis.sales.services;


import com.bis.domain.SalesTransaction;
import com.bis.domain.StDetails;
import com.bis.sales.repository.SalesRepository;

import java.util.List;

public class SalesServiceImpl implements SalesService {

    private SalesRepository salesRepository;

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
}
