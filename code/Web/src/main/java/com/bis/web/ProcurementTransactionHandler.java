package com.bis.web;

import com.bis.domain.ProcurementTransaction;
import com.bis.domain.PtDetails;
import com.bis.inventory.services.StockService;
import com.bis.procurement.services.ProcurementTransactionService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ProcurementTransactionHandler {

    private ProcurementTransactionService procurementTransactionService;
    private StockService stockService;

    public ProcurementTransactionHandler(ProcurementTransactionService procurementTransactionService, StockService stockService) {
        this.procurementTransactionService = procurementTransactionService;
        this.stockService = stockService;
    }

    @Transactional
    public void addNewTransaction(ProcurementTransaction procurementTransaction) {
        List<PtDetails> transactionDetails = procurementTransaction.getTransactionDetails();
        for (PtDetails details : transactionDetails) {
            stockService.addStock(details.getItem().getItemCode(), details.getDateOfPublishing(), details.getQuantity());
        }
        procurementTransactionService.addProcurementTransaction(procurementTransaction);
    }

    @Transactional
    public void updateTransaction(ProcurementTransaction procurementTransaction) {
        List<PtDetails> updatedTransactionDetails = procurementTransaction.getTransactionDetails();
        ProcurementTransaction originalTransaction = procurementTransactionService.getProcurementTransaction(procurementTransaction.getTransactionId());
        for (PtDetails updatedDetail : updatedTransactionDetails) {
            PtDetails originalDetail = originalTransaction.getPtDetails(updatedDetail.getDetailsId());
            if (originalDetail != null) {
                Integer quantity = updatedDetail.getQuantity() - originalDetail.getQuantity();
                if (quantity > 0) {
                    stockService.addStock(updatedDetail.getItem().getItemCode(), updatedDetail.getDateOfPublishing(), quantity);
                } else if (quantity < 0) {
                    stockService.reduceStock(updatedDetail.getItem().getItemCode(), updatedDetail.getDateOfPublishing(), originalDetail.getQuantity() - updatedDetail.getQuantity());
                }
            } else {
                stockService.addStock(updatedDetail.getItem().getItemCode(), updatedDetail.getDateOfPublishing(), updatedDetail.getQuantity());
            }
        }
        for (PtDetails detailsDB : originalTransaction.getTransactionDetails()) {
            PtDetails ptDetails = procurementTransaction.getPtDetails(detailsDB.getDetailsId());
            if (ptDetails == null) {
                stockService.reduceStock(detailsDB.getItem().getItemCode(), detailsDB.getDateOfPublishing(), detailsDB.getQuantity());
            }
        }
        procurementTransactionService.updateProcurementTransaction(procurementTransaction);
    }

}
