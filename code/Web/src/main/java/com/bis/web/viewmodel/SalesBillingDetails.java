package com.bis.web.viewmodel;

import com.bis.domain.PaymentHistorySales;
import com.bis.domain.SalesTransaction;
import com.bis.domain.SalesTransactionType;

import java.util.ArrayList;
import java.util.List;

public class SalesBillingDetails {

    private List<SalesTransaction> salesTransactions = new ArrayList<SalesTransaction>();
    private List<PaymentHistorySales> salesPayments = new ArrayList<PaymentHistorySales>();

    public double getBillAmount() {
        return getSalesTransactionTotal() - (getReturnTransactionTotal()+getPaymentTotal());
    }

    public SalesBillingDetails(List<SalesTransaction> salesTransactions, List<PaymentHistorySales> paymentHistorySales) {
        this.salesTransactions = salesTransactions;
        this.salesPayments = paymentHistorySales;
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
}
