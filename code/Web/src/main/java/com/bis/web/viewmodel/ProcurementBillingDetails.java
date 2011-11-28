package com.bis.web.viewmodel;

import com.bis.domain.PaymentHistoryProcurement;
import com.bis.domain.ProcurementTransaction;
import com.bis.domain.ProcurementTransactionType;
import com.bis.domain.Vendor;

import java.util.ArrayList;
import java.util.List;

public class ProcurementBillingDetails {

    private Float paymentAmount;
    private String receiptNum;
	private Character mode;
	private String remarks;
    private Vendor vendor = new Vendor();

    private List<ProcurementTransaction> procurementTransactions = new ArrayList<ProcurementTransaction>();

    private List<PaymentHistoryProcurement> procurementPayments = new ArrayList<PaymentHistoryProcurement>();

    public ProcurementBillingDetails(List<ProcurementTransaction> procurementTransactions, List<PaymentHistoryProcurement> procurementPayments) {
        this.procurementTransactions = procurementTransactions;
        this.procurementPayments = procurementPayments;
    }

    public ProcurementBillingDetails() {

    }

    public void setProcurementTransactions(List<ProcurementTransaction> procurementTransactions){
        this.procurementTransactions = procurementTransactions;
    }

    public void setProcurementPayments(List<PaymentHistoryProcurement> procurementPayments){
        this.procurementPayments = procurementPayments;
    }
    public double getBillAmount() {
        return getPurchaseTransactionTotal() - (getReturnTransactionTotal()+getPaymentTotal());
    }

    public double getPurchaseTransactionTotal() {
        double total = 0f;
        for (ProcurementTransaction procurementTransaction : procurementTransactions) {
            if (ProcurementTransactionType.PURCHASE.getCode() == procurementTransaction.getTransactionType())
                total += procurementTransaction.getTotalAmount();
        }
        return total;
    }

    public double getReturnTransactionTotal() {
        double total = 0f;
        for (ProcurementTransaction procurementTransaction : procurementTransactions) {
            if (ProcurementTransactionType.RETURNS.getCode() == procurementTransaction.getTransactionType())
                total += procurementTransaction.getTotalAmount();
        }
        return total;
    }

    public double getPaymentTotal() {
        double total = 0f;
        for (PaymentHistoryProcurement payment : procurementPayments) {
            total += payment.getAmount();
        }
        return total;
    }

    public List<ProcurementTransaction> getProcurementTransactions() {
        return procurementTransactions;
    }

    public List<PaymentHistoryProcurement> getProcurementPayments() {
        return procurementPayments;
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

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}