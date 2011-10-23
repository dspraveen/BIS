package com.bis.procurement.services;

import com.bis.domain.PaymentHistoryProcurement;

import java.util.Date;
import java.util.List;

public interface ProcurementPaymentService {
    void addProcurementPayment(PaymentHistoryProcurement paymentHistoryProcurement);
    void updateProcurementPayment(PaymentHistoryProcurement paymentHistoryProcurement);
    PaymentHistoryProcurement getProcurementPayment(int paymentId);
    List<PaymentHistoryProcurement> getProcurementPayments(Date fromDate, Date toDate);
    List<PaymentHistoryProcurement> getProcurementPayments(int vendorId, Date fromDate, Date toDate);
}
