package com.bis.sb.services;


import com.bis.domain.PaymentHistorySales;
import java.util.Date;
import java.util.List;

public interface SalesPaymentService {
    void addSalesPayment(PaymentHistorySales paymentHistorySales);
    void updateSalesPayment(PaymentHistorySales paymentHistorySales);
    PaymentHistorySales getSalesPayment(int paymentId);
    List<PaymentHistorySales> getSalesPayments(Date fromDate, Date toDate);
    List<PaymentHistorySales> getSalesPayments(int hawkerId, Date fromDate, Date toDate);
}
