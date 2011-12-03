package com.bis.web.viewmodel;

import com.bis.domain.*;

import java.util.ArrayList;
import java.util.List;

public class SalesBillingDetails {

    private Float paymentAmount = 0f;
    private String receiptNum;
    private Character mode;
    private String remarks;
    private Hawker hawker = new Hawker();
    private List<SalesTransaction> salesTransactions = new ArrayList<SalesTransaction>();
    private List<PaymentHistorySales> salesPayments = new ArrayList<PaymentHistorySales>();
    private BillingSales lastBill;

    public SalesBillingDetails() {
    }

    public double getBillAmount() {
        if (lastBill != null) {
            return lastBill.getBalanceAmount() + getSalesTransactionTotal() - (getReturnTransactionTotal() + getPaymentTotal());
        }
        return getSalesTransactionTotal() - (getReturnTransactionTotal() + getPaymentTotal());
    }

    public SalesBillingDetails(List<SalesTransaction> salesTransactions, List<PaymentHistorySales> paymentHistorySales, BillingSales lastBill) {
        this.salesTransactions = salesTransactions;
        this.salesPayments = paymentHistorySales;
        this.lastBill = lastBill;
    }

    public double getSalesTransactionTotal() {
        double total = 0f;
        for (SalesTransaction salesTransaction : salesTransactions) {
            if (SalesTransactionType.SALES.getCode() == salesTransaction.getTransactionType())
                total += salesTransaction.getTotalAmount();
        }
        return total;
    }

    public double getReturnTransactionTotal() {
        double total = 0f;
        for (SalesTransaction salesTransaction : salesTransactions) {
            if (SalesTransactionType.RETURNS.getCode() == salesTransaction.getTransactionType())
                total += salesTransaction.getTotalAmount();
        }
        return total;
    }

    public double getPaymentTotal() {
        double total = 0f;
        for (PaymentHistorySales payment : salesPayments) {
            total += payment.getAmount();
        }
        return total;
    }

    public List<SalesTransaction> getSalesTransactions() {
        return salesTransactions;
    }

    public List<PaymentHistorySales> getSalesPayments() {
        return salesPayments;
    }

    public Float getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Float paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getReceiptNum() {
        return receiptNum;
    }

    public void setReceiptNum(String receiptNum) {
        this.receiptNum = receiptNum;
    }

    public Character getMode() {
        return mode;
    }

    public void setMode(Character mode) {
        this.mode = mode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Hawker getHawker() {
        return hawker;
    }

    public void setHawker(Hawker hawker) {
        this.hawker = hawker;
    }

    public void setLastBill(BillingSales lastBill) {
        this.lastBill = lastBill;
    }

    public BillingSales getLastBill() {
        return lastBill;
    }
}
