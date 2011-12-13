package com.bis.procurement.services;

import com.bis.common.DateUtils;
import com.bis.domain.PaymentHistoryProcurement;
import com.bis.domain.Vendor;
import com.bis.procurement.repository.ProcurementPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProcurementPaymentServiceImpl implements ProcurementPaymentService {
    private ProcurementPaymentRepository procurementPaymentRepository;

    @Autowired
    public ProcurementPaymentServiceImpl(ProcurementPaymentRepository procurementPaymentRepository) {
        this.procurementPaymentRepository = procurementPaymentRepository;
    }

    @Override
    public void addProcurementPayment(PaymentHistoryProcurement paymentHistoryProcurement) {
        paymentHistoryProcurement.setDate(DateUtils.addTimeToDate(paymentHistoryProcurement.getDate()));
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
        return procurementPaymentRepository.getProcurementPayments(fromDate, toDate);
    }

    public List<PaymentHistoryProcurement> getProcurementPayments(Vendor vendor, Date fromDate, Date toDate) {
        return procurementPaymentRepository.getProcurementPayments(vendor, fromDate, toDate);
    }
}
