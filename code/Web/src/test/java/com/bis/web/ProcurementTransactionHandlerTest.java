package com.bis.web;

import com.bis.common.DateUtils;
import com.bis.domain.Item;
import com.bis.domain.ProcurementTransaction;
import com.bis.domain.PtDetails;
import com.bis.inventory.services.StockService;
import com.bis.procurement.services.ProcurementTransactionService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.mockito.Mockito.when;

@Ignore
public class ProcurementTransactionHandlerTest {
    @Mock
    private ProcurementTransactionService procurementTransactionService;
    @Mock
    private StockService stockService;
    private ProcurementTransactionHandler procurementTransactionHandler;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        procurementTransactionHandler = new ProcurementTransactionHandler(procurementTransactionService, stockService);
    }

    @Test
    public void shouldAddToStockWhenNewItemAddedToUpdatedTransaction() {
        Date dateOfPublish = DateUtils.currentDate();
        ProcurementTransaction updatedTransaction = new ProcurementTransaction();
        updatedTransaction.setTransactionId(1);
        updatedTransaction.getTransactionDetails().add(procurementTransactionDetailBuilder(1, 10, dateOfPublish, 1));
        updatedTransaction.getTransactionDetails().add(procurementTransactionDetailBuilder(2, 10, dateOfPublish, null));

        ProcurementTransaction originalTransaction = new ProcurementTransaction();
        originalTransaction.getTransactionDetails().add(procurementTransactionDetailBuilder(1, 10, dateOfPublish, 1));

        when(procurementTransactionService.getProcurementTransaction(updatedTransaction.getTransactionId())).thenReturn(originalTransaction);
        procurementTransactionHandler.updateTransaction(updatedTransaction);
        Mockito.verify(stockService).addStock(2, dateOfPublish, 10);
        Mockito.verify(procurementTransactionService).updateProcurementTransaction(updatedTransaction);
    }

    @Test
    public void shouldAddToStockWhenItemQtyIncreasedToUpdatedTransaction() {
        Date dateOfPublish = DateUtils.currentDate();
        ProcurementTransaction updatedTransaction = new ProcurementTransaction();
        updatedTransaction.setTransactionId(1);
        updatedTransaction.getTransactionDetails().add(procurementTransactionDetailBuilder(1, 20, dateOfPublish, 1));

        ProcurementTransaction originalTransaction = new ProcurementTransaction();
        originalTransaction.getTransactionDetails().add(procurementTransactionDetailBuilder(1, 10, dateOfPublish, 1));

        when(procurementTransactionService.getProcurementTransaction(updatedTransaction.getTransactionId())).thenReturn(originalTransaction);
        procurementTransactionHandler.updateTransaction(updatedTransaction);
        Mockito.verify(stockService).addStock(1, dateOfPublish, 10);
        Mockito.verify(procurementTransactionService).updateProcurementTransaction(updatedTransaction);
    }

    @Test
    public void shouldReduceToStockWhenItemQtyReducedToUpdatedTransaction() {
        Date dateOfPublish = DateUtils.currentDate();
        ProcurementTransaction updatedTransaction = new ProcurementTransaction();
        updatedTransaction.setTransactionId(1);
        updatedTransaction.getTransactionDetails().add(procurementTransactionDetailBuilder(1, 10, dateOfPublish, 1));

        ProcurementTransaction originalTransaction = new ProcurementTransaction();
        originalTransaction.getTransactionDetails().add(procurementTransactionDetailBuilder(1, 20, dateOfPublish, 1));

        when(procurementTransactionService.getProcurementTransaction(updatedTransaction.getTransactionId())).thenReturn(originalTransaction);
        procurementTransactionHandler.updateTransaction(updatedTransaction);
        Mockito.verify(stockService).reduceStock(1, dateOfPublish, 10);
        Mockito.verify(procurementTransactionService).updateProcurementTransaction(updatedTransaction);
    }

    @Test
    public void shouldReduceToStockWhenItemDeletedFromUpdatedTransaction() {
        Date dateOfPublish = DateUtils.currentDate();
        ProcurementTransaction updatedTransaction = new ProcurementTransaction();
        updatedTransaction.setTransactionId(1);
        updatedTransaction.getTransactionDetails().add(procurementTransactionDetailBuilder(1, 10, dateOfPublish, 1));

        ProcurementTransaction originalTransaction = new ProcurementTransaction();
        originalTransaction.getTransactionDetails().add(procurementTransactionDetailBuilder(1, 10, dateOfPublish, 1));
        originalTransaction.getTransactionDetails().add(procurementTransactionDetailBuilder(2, 10, dateOfPublish, 2));

        when(procurementTransactionService.getProcurementTransaction(updatedTransaction.getTransactionId())).thenReturn(originalTransaction);
        procurementTransactionHandler.updateTransaction(updatedTransaction);
        Mockito.verify(stockService).reduceStock(2, dateOfPublish, 10);
        Mockito.verify(procurementTransactionService).updateProcurementTransaction(updatedTransaction);
    }

    private PtDetails procurementTransactionDetailBuilder(int itemCode, int quantity, Date dateOfPublish, Integer detailsId) {
        PtDetails ptDetails = new PtDetails();
        Item item = new Item();
        item.setItemCode(itemCode);
        ptDetails.setItem(item);
        ptDetails.setQuantity(quantity);
        ptDetails.setDateOfPublishing(dateOfPublish);
        ptDetails.setDetailsId(detailsId);
        return ptDetails;
    }
}
