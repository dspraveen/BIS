package com.bis.web;

import com.bis.common.DateUtils;
import com.bis.domain.*;
import com.bis.inventory.services.StockService;
import com.bis.procurement.services.ProcurementTransactionService;
import com.bis.web.viewmodel.ListElement;
import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.bis.common.DateUtils.defaultFormat;

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
            if (ProcurementTransactionType.PURCHASE.getCode() == procurementTransaction.getTransactionType())
                stockService.addStock(details.getItem().getItemCode(), details.getDateOfPublishing(), details.getQuantity());
            else if (ProcurementTransactionType.RETURNS.getCode() == procurementTransaction.getTransactionType())
                stockService.reduceStock(details.getItem().getItemCode(), details.getDateOfPublishing(), details.getQuantity());
        }
        procurementTransactionService.addProcurementTransaction(procurementTransaction);
    }

    @Transactional
    public void updateTransaction(final ProcurementTransaction procurementTransaction) {
        ProcurementTransaction currentTransaction = procurementTransactionService.getProcurementTransaction(procurementTransaction.getTransactionId());
        if (ProcurementTransactionType.PURCHASE.getCode() == procurementTransaction.getTransactionType()) {
            handlePurchaseTransaction(procurementTransaction, currentTransaction);
        } else if (ProcurementTransactionType.RETURNS.getCode() == procurementTransaction.getTransactionType()) {
            handleReturnsTransaction(procurementTransaction, currentTransaction);
        } else {
            throw new RuntimeException("Invalid transaction type");
        }
        procurementTransactionService.updateProcurementTransaction(procurementTransaction);
    }


    public List<ListElement> getStockDetails(Item item) {
        Date currentDate = DateUtils.currentDate();
        List<Stock> stockList = stockService.getAllStock(item.getItemCode(), DateUtils.addMonth(currentDate, -1), DateUtils.infinityDate());
        List<ListElement> stockDetails = new ArrayList<ListElement>();
        if (stockList.isEmpty()) return stockDetails;
        Date latestPublicationDate = stockList.get(0).getDateOfPublishing();
        if (!DateUtils.isGreaterOrEqual(latestPublicationDate, currentDate)) {
            Date date = getNextDateOfPublish(latestPublicationDate, item.getItemLife());
            stockDetails.add(new ListElement(defaultFormat(date), defaultFormat(date)));
        }
        for (Stock stock : stockList) {
            String dateString = defaultFormat(stock.getDateOfPublishing());
            stockDetails.add(new ListElement(dateString, dateString + ":" + stock.getQuantity()));
        }
        return stockDetails;
    }

    private Date getNextDateOfPublish(Date dateOfPublishing, char itemLife) {
        if (ItemCycle.FORTNIGHT.getCode() == itemLife) return DateUtils.addDays(dateOfPublishing, 15);
        if (ItemCycle.MONTHLY.getCode() == itemLife) return DateUtils.addMonth(dateOfPublishing, 1);
        if (ItemCycle.WEEKLY.getCode() == itemLife) return DateUtils.addDays(dateOfPublishing, 7);
        if (ItemCycle.DAILY.getCode() == itemLife) return DateUtils.addDays(dateOfPublishing, 1);
        throw new RuntimeException("Invalid item life");
    }

    private void handleReturnsTransaction(ProcurementTransaction procurementTransaction, final ProcurementTransaction currentTransaction) {
        CollectionUtils.forAllDo(procurementTransaction.getTransactionDetails(), new Closure() {
            @Override
            public void execute(Object input) {
                PtDetails detail = (PtDetails) input;
                PtDetails oldDetail = currentTransaction.getPtDetails(detail.getDetailsId());
                if (oldDetail == null) {
                    stockService.reduceStock(detail.getItem().getItemCode(), detail.getDateOfPublishing(), detail.getQuantity());
                } else {
                    stockService.updateStock(detail.getItem().getItemCode(), detail.getDateOfPublishing(), oldDetail.getQuantity() - detail.getQuantity());
                }
            }
        });
    }

    private void handlePurchaseTransaction(ProcurementTransaction procurementTransaction, final ProcurementTransaction currentTransaction) {
        CollectionUtils.forAllDo(procurementTransaction.getTransactionDetails(), new Closure() {
            @Override
            public void execute(Object input) {
                PtDetails detail = (PtDetails) input;
                PtDetails oldDetail = currentTransaction.getPtDetails(detail.getDetailsId());
                if (oldDetail == null)
                    stockService.addStock(detail.getItem().getItemCode(), detail.getDateOfPublishing(), detail.getQuantity());
                else
                    stockService.updateStock(detail.getItem().getItemCode(), detail.getDateOfPublishing(), detail.getQuantity() - oldDetail.getQuantity());
            }
        });
    }

}
