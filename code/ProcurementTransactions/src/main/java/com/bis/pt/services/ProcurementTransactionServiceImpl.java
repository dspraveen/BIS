package com.bis.pt.services;

import com.bis.domain.ProcurementTransaction;
import com.bis.pt.repository.ProcurementTransactionRepository;

import java.util.Date;
import java.util.List;

public class  ProcurementTransactionServiceImpl implements ProcurementTransactionService{

    private ProcurementTransactionRepository procurementTransactionRepository;

    public ProcurementTransactionServiceImpl(ProcurementTransactionRepository procurementTransactionRepository){
        this.procurementTransactionRepository = procurementTransactionRepository;
    }

    @Override
    public void addProcurementTransaction(ProcurementTransaction procurementTransaction) {
        procurementTransaction.calculateAndSetTotalAmount();
        procurementTransactionRepository.save(procurementTransaction);
    }

    @Override
    public void updateProcurementTransaction(ProcurementTransaction procurementTransaction) {
        procurementTransaction.calculateAndSetTotalAmount();
        procurementTransactionRepository.update(procurementTransaction);
    }

    @Override
    public ProcurementTransaction getProcurementTransaction(int transactionId) {
        return procurementTransactionRepository.get(transactionId);
    }

    public List<ProcurementTransaction> getProcurementTransactions(Date fromDate, Date toDate){
        return procurementTransactionRepository.getProcurementTransactions(fromDate, toDate);
    }
}
