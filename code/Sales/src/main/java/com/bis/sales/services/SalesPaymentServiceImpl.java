package com.bis.sales.services;

import com.bis.common.DateUtils;
import com.bis.domain.Hawker;
import com.bis.domain.PaymentHistorySales;
import com.bis.sales.repository.SalesPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SalesPaymentServiceImpl implements SalesPaymentService {

    private SalesPaymentRepository salesPaymentRepository;

    @Autowired
    public SalesPaymentServiceImpl(SalesPaymentRepository salesPaymentRepository) {
        this.salesPaymentRepository = salesPaymentRepository;
    }

    @Override
    public void addSalesPayment(PaymentHistorySales paymentHistorySales) {
        paymentHistorySales.setDate(DateUtils.addTimeToDate(paymentHistorySales.getDate()));
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

    public List<PaymentHistorySales> getSalesPayments( Hawker hawker, Date fromDate, Date toDate) {
        return salesPaymentRepository.getSalesPayments(hawker, fromDate, toDate);
    }
}
