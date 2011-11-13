package com.bis.procurement.services;


import com.bis.domain.ProcurementTransaction;
import com.bis.domain.Vendor;

import java.util.Date;
import java.util.List;

public interface ProcurementTransactionService {
    void addProcurementTransaction(ProcurementTransaction procurementTransaction);
    void updateProcurementTransaction(ProcurementTransaction procurementTransaction);
    ProcurementTransaction getProcurementTransaction(int transactionId);
    List<ProcurementTransaction> getProcurementTransactions(Date fromDate, Date toDate);
    List<ProcurementTransaction> getProcurementTransactions(Date fromDate, Date toDate, Vendor vendor);
}
