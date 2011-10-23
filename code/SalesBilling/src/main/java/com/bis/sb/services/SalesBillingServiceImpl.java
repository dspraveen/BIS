package com.bis.sb.services;

import com.bis.domain.PaymentHistorySales;
import com.bis.sb.repository.SalesBillingRepository;
import com.bis.sb.repository.SalesPaymentRepository;

import java.util.Date;
import java.util.List;

//@Service
public class SalesBillingServiceImpl implements SalesBillingService, SalesPaymentService{
    public SalesBillingServiceImpl(SalesPaymentRepository salesPaymentRepository, SalesBillingRepository salesBillingRepository) {
        this.salesPaymentRepository = salesPaymentRepository;
        this.salesBillingRepository = salesBillingRepository;
    }

    private SalesPaymentRepository salesPaymentRepository;
    private SalesBillingRepository salesBillingRepository;

    @Override
    public void addSalesPayment(PaymentHistorySales paymentHistorySales) {
        salesPaymentRepository.save(paymentHistorySales);
    }

    @Override
    public void updateSalesPayment(PaymentHistorySales paymentHistorySales) {
        salesPaymentRepository.update(paymentHistorySales);
    }

    @Override
    public PaymentHistorySales getSalesPayment(int paymentId) {
        return salesPaymentRepository.get(paymentId);
    }

    @Override
    public List<PaymentHistorySales> getSalesPayments(Date fromDate, Date toDate) {
        return salesPaymentRepository.getSalesPayments(fromDate, toDate);
    }

    @Override
    public List<PaymentHistorySales> getSalesPayments(int hawkerId, Date fromDate, Date toDate) {
        return salesPaymentRepository.getSalesPayments(hawkerId, fromDate, toDate);
    }
}
