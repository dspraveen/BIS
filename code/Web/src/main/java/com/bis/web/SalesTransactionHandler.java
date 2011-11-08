package com.bis.web;

import com.bis.domain.SalesTransaction;
import com.bis.domain.StDetails;
import com.bis.inventory.services.StockService;
import com.bis.sales.services.SalesTransactionService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class SalesTransactionHandler {

    private SalesTransactionService salesTransactionService;
    private StockService stockService;

    public SalesTransactionHandler(SalesTransactionService salesTransactionService, StockService stockService) {
        this.salesTransactionService = salesTransactionService;
        this.stockService = stockService;
    }

    @Transactional
    public void addNewTransaction(SalesTransaction salesTransaction) {
        List<StDetails> transactionDetails = salesTransaction.getTransactionDetails();
        for (StDetails details : transactionDetails) {
            stockService.reduceStock(details.getItem().getItemCode(), details.getDateOfPublishing(), details.getQuantity());
        }
        salesTransactionService.addSalesTransaction(salesTransaction);
    }

    @Transactional
    public void updateTransaction(SalesTransaction salesTransaction) {
        List<StDetails> updatedTransactionDetails = salesTransaction.getTransactionDetails();
        SalesTransaction originalTransaction = salesTransactionService.getSalesTransaction(salesTransaction.getTransactionId());
        for (StDetails  updatedDetail : updatedTransactionDetails) {
            StDetails originalDetail = originalTransaction.getStDetails(updatedDetail.getDetailsId());
            if (originalDetail != null) {
                Integer quantity = updatedDetail.getQuantity() - originalDetail.getQuantity();
                if (quantity > 0) {
                    stockService.reduceStock(updatedDetail.getItem().getItemCode(), updatedDetail.getDateOfPublishing(), quantity);
                } else if (quantity < 0) {
                    stockService.addStock(updatedDetail.getItem().getItemCode(), updatedDetail.getDateOfPublishing(), originalDetail.getQuantity() - updatedDetail.getQuantity());
                }
            } else {
                stockService.addStock(updatedDetail.getItem().getItemCode(), updatedDetail.getDateOfPublishing(), updatedDetail.getQuantity());
            }
        }
        for (StDetails detailsDB : originalTransaction.getTransactionDetails()) {
            StDetails stDetails = salesTransaction.getStDetails(detailsDB.getDetailsId());
            if (stDetails == null) {
                stockService.addStock(detailsDB.getItem().getItemCode(), detailsDB.getDateOfPublishing(), detailsDB.getQuantity());
            }
        }
        salesTransactionService.updateSalesTransaction(salesTransaction);
    }

}
