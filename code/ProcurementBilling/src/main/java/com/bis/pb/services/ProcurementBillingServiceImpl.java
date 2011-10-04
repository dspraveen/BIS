package com.bis.pb.services;

import com.bis.domain.BillingProcurement;
import com.bis.domain.PaymentHistoryProcurement;
import com.bis.pb.repository.ProcurementBillingRepository;
import com.bis.pb.repository.ProcurementPaymentRepository;

import java.util.Date;
import java.util.List;

public class ProcurementBillingServiceImpl implements ProcurementBillingService, ProcurementPaymentService{

    private ProcurementBillingRepository procurementBillingRepository;
    private ProcurementPaymentRepository procurementPaymentRepository;

    public ProcurementBillingServiceImpl(ProcurementPaymentRepository procurementPaymentRepository, ProcurementBillingRepository procurementBillingRepository) {
        this.procurementPaymentRepository = procurementPaymentRepository;
        this.procurementBillingRepository = procurementBillingRepository;
    }

    @Override
    public void addProcurementPayment(PaymentHistoryProcurement paymentHistoryProcurement) {
        procurementPaymentRepository.save(paymentHistoryProcurement);
    }

    @Override
    public void updateProcurementPayment(PaymentHistoryProcurement paymentHistoryProcurement) {
        procurementPaymentRepository.update(paymentHistoryProcurement);
    }

    @Override
    public PaymentHistoryProcurement getProcurementPayment(int paymentId) {
        return procurementPaymentRepository.get(paymentId);
    }

    @Override
    public List<PaymentHistoryProcurement> getProcurementPayments(Date fromDate, Date toDate) {
        return procurementPaymentRepository.getProcurementPayments(fromDate,toDate);
    }

    @Override
    public List<PaymentHistoryProcurement> getProcurementPayments(int vendorId, Date fromDate, Date toDate) {
        return procurementPaymentRepository.getProcurementPayments(vendorId, fromDate, toDate);
    }

    @Override
    public void createProcurementBill(BillingProcurement billingProcurement) {
        procurementBillingRepository.save(billingProcurement);
    }
}
