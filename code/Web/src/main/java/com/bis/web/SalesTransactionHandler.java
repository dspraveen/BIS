package com.bis.web;

import com.bis.common.DateUtils;
import com.bis.domain.SalesTransaction;
import com.bis.domain.SalesTransactionType;
import com.bis.domain.StDetails;
import com.bis.domain.Stock;
import com.bis.inventory.services.StockService;
import com.bis.sales.services.SalesTransactionService;
import com.bis.web.viewmodel.ListElement;
import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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
            if (SalesTransactionType.SALES.getCode() == salesTransaction.getTransactionType())
                stockService.reduceStock(details.getItem().getItemCode(), details.getDateOfPublishing(), details.getQuantity());
            else if (SalesTransactionType.RETURNS.getCode() == salesTransaction.getTransactionType())
                stockService.addStock(details.getItem().getItemCode(), details.getDateOfPublishing(), details.getQuantity());
        }
        salesTransactionService.addSalesTransaction(salesTransaction);
    }

    @Transactional
    public void updateTransaction(final SalesTransaction salesTransaction) {
        final SalesTransaction currentTransaction = salesTransactionService.getSalesTransaction(salesTransaction.getTransactionId());
        if (SalesTransactionType.SALES.getCode() == salesTransaction.getTransactionType()) {
            handleSalesTransaction(salesTransaction, currentTransaction);
        } else if (SalesTransactionType.RETURNS.getCode() == salesTransaction.getTransactionType()) {
            handleReturnsTransaction(salesTransaction, currentTransaction);
        }
        salesTransactionService.updateSalesTransaction(salesTransaction);
    }


    public List<ListElement> getStockDetails(int itemCode) {
        Date currentDate = DateUtils.currentDate();
        List<Stock> stockList = stockService.getAllStock(itemCode, DateUtils.addMonth(currentDate, -1), DateUtils.infinityDate());
        List<ListElement> stockDetails = new ArrayList<ListElement>();
        for (Stock stock : stockList) {
            if (stock.getQuantity() > 0) {
                String dateString = DateUtils.defaultFormat(stock.getDateOfPublishing());
                stockDetails.add(new ListElement(dateString, dateString + ":" + stock.getQuantity()));
            }
        }
        return stockDetails;
    }

    private void handleReturnsTransaction(SalesTransaction salesTransaction, final SalesTransaction currentTransaction) {
        CollectionUtils.forAllDo(salesTransaction.getTransactionDetails(), new Closure() {
            @Override
            public void execute(Object input) {
                StDetails detail = (StDetails) input;
                StDetails oldDetail = currentTransaction.getStDetails(detail.getDetailsId());
                if (oldDetail == null) {
                    stockService.addStock(detail.getItem().getItemCode(), detail.getDateOfPublishing(), detail.getQuantity());
                } else {
                    stockService.updateStock(detail.getItem().getItemCode(), detail.getDateOfPublishing(), detail.getQuantity() - oldDetail.getQuantity());
                }
            }
        });
    }

    private void handleSalesTransaction(SalesTransaction salesTransaction, final SalesTransaction currentTransaction) {
        CollectionUtils.forAllDo(salesTransaction.getTransactionDetails(), new Closure() {
            @Override
            public void execute(Object input) {
                StDetails detail = (StDetails) input;
                StDetails oldDetail = currentTransaction.getStDetails(detail.getDetailsId());
                if (oldDetail == null) {
                    stockService.reduceStock(detail.getItem().getItemCode(), detail.getDateOfPublishing(), detail.getQuantity());
                } else {
                    stockService.updateStock(detail.getItem().getItemCode(), detail.getDateOfPublishing(), oldDetail.getQuantity() - detail.getQuantity());
                }
            }
        });
    }


}
