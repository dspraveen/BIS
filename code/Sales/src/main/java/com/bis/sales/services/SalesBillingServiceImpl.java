package com.bis.sales.services;

import com.bis.domain.PaymentHistorySales;
import com.bis.sales.repository.SalesBillingRepository;
import com.bis.sales.repository.SalesPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SalesBillingServiceImpl implements SalesBillingService{

    private SalesPaymentRepository salesPaymentRepository;
    private SalesBillingRepository salesBillingRepository;

    @Autowired
    public SalesBillingServiceImpl(SalesPaymentRepository salesPaymentRepository, SalesBillingRepository salesBillingRepository) {
        this.salesPaymentRepository = salesPaymentRepository;
        this.salesBillingRepository = salesBillingRepository;
    }


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
