package com.bis.web.viewmodel;

import com.bis.domain.PaymentHistoryProcurement;
import com.bis.domain.ProcurementTransaction;
import com.bis.domain.ProcurementTransactionType;

import java.util.ArrayList;
import java.util.List;

public class ProcurementBillingDetails {

    private List<ProcurementTransaction> procurementTransactions = new ArrayList<ProcurementTransaction>();

    private List<PaymentHistoryProcurement> procurementPayments = new ArrayList<PaymentHistoryProcurement>();

    public ProcurementBillingDetails(List<ProcurementTransaction> procurementTransactions, List<PaymentHistoryProcurement> procurementPayments) {
        this.procurementTransactions = procurementTransactions;
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
}
