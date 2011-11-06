package com.bis.web.viewmodel;

import com.bis.domain.ProcurementTransaction;
import com.bis.domain.PtDetails;
import com.bis.domain.SalesTransaction;
import com.bis.domain.StDetails;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionDetailGrid {

    private Integer transactionId;

    private Integer targetId;

    private Date transactionDate;

    private char type;

    private int effectedRowId;

    private String changeType;

    List<TransactionDetailRow> transactionDetails = new ArrayList<TransactionDetailRow>();

    public TransactionDetailGrid() {
    }

    public TransactionDetailGrid(ProcurementTransaction procurementTransaction) {
        transactionId = procurementTransaction.getTransactionId();
        targetId = procurementTransaction.getVendor().getVendorId();
        transactionDate = procurementTransaction.getDate();
        type = procurementTransaction.getTransactionType();
    }

    public TransactionDetailGrid(SalesTransaction salesTransaction) {
        transactionId = salesTransaction.getTransactionId();
        targetId = salesTransaction.getHawker().getHawkerId();
        transactionDate = salesTransaction.getDate();
        type = salesTransaction.getTransactionType();
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public int getEffectedRowId() {
        return effectedRowId;
    }

    public void setEffectedRowId(int effectedRowId) {
        this.effectedRowId = effectedRowId;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }


    public List<TransactionDetailRow> getTransactionDetails() {
        return transactionDetails;
    }

    public void addNewItem(Float vendorDiscount) {
        TransactionDetailRow transactionDetailRow = new TransactionDetailRow();
        transactionDetailRow.setDiscount(vendorDiscount);
        transactionDetails.add(transactionDetailRow);
    }

    public void deleteItems() {
        List<TransactionDetailRow> rowsToDelete = new ArrayList<TransactionDetailRow>();
        for (TransactionDetailRow row : transactionDetails) {
            if (row.isChecked()) {
                rowsToDelete.add(row);
            }
        }
        transactionDetails.removeAll(rowsToDelete);
    }

    public TransactionDetailRow getEffectedRow(TransactionDetailGrid procurementTransactionGrid) {
        for (TransactionDetailRow row : procurementTransactionGrid.getTransactionDetails()) {
            if (procurementTransactionGrid.getEffectedRowId() == procurementTransactionGrid.getTransactionDetails().indexOf(row)) {
                Integer itemCode = row.getItemCode();
                if (itemCode != null && itemCode > 0) {
                    return row;
                }
            }
        }
        return null;
    }

    public ProcurementTransaction buildProcurementTransaction() {
        ProcurementTransaction procurementTransaction = new ProcurementTransaction();
        procurementTransaction.setTransactionId(transactionId);
        procurementTransaction.setDate(transactionDate);
        procurementTransaction.setTransactionType(type);
        procurementTransaction.getVendor().setVendorId(targetId);
        for (TransactionDetailRow row : transactionDetails) {
            PtDetails ptDetails = new PtDetails();
            ptDetails.setDetailsId(row.getDetailsId());
            ptDetails.setTransactionId(row.getTransactionId());
            ptDetails.setDateOfPublishing(row.getDateOfPublishing());
            ptDetails.setAmount(row.getAmount());
            ptDetails.setQuantity(row.getQuantity());
            ptDetails.getItem().setItemCode(row.getItemCode());
            procurementTransaction.getTransactionDetails().add(ptDetails);
        }
        procurementTransaction.calculateAndSetTotalAmount();
        return procurementTransaction;
    }

    public SalesTransaction buildSalesTransaction() {
        SalesTransaction salesTransaction = new SalesTransaction();
        salesTransaction.setTransactionId(transactionId);
        salesTransaction.setDate(transactionDate);
        salesTransaction.setTransactionType(type);
        salesTransaction.getHawker().setHawkerId(targetId);
        for (TransactionDetailRow row : transactionDetails) {
            StDetails stDetails = new StDetails();
            stDetails.setDetailsId(row.getDetailsId());
            stDetails.setTransactionId(row.getTransactionId());
            stDetails.setDateOfPublishing(row.getDateOfPublishing());
            stDetails.setAmount(row.getAmount());
            stDetails.setQuantity(row.getQuantity());
            stDetails.getItem().setItemCode(row.getItemCode());
            salesTransaction.getTransactionDetails().add(stDetails);
        }
        salesTransaction.calculateAndSetTotalAmount();
        return salesTransaction;
    }
}
