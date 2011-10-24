package com.bis.sales.services;

import com.bis.common.DateUtils;
import com.bis.domain.*;
import com.bis.sales.repository.SalesBillingRepository;
import com.bis.sales.repository.SalesPaymentRepository;
import com.bis.sales.repository.SalesTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SalesBillingServiceImpl implements SalesBillingService {

    private SalesPaymentRepository salesPaymentRepository;
    private SalesBillingRepository salesBillingRepository;
    private SalesTransactionRepository salesTransactionRepository;

    @Autowired
    public SalesBillingServiceImpl(SalesPaymentRepository salesPaymentRepository, SalesBillingRepository salesBillingRepository) {
        this.salesPaymentRepository = salesPaymentRepository;
        this.salesBillingRepository = salesBillingRepository;
    }


    private Float getTotalAmountForCycle(int hawkerId, Date fromDate, Date toDate) {
        Float totalAmount = null;
        List<PaymentHistorySales> salesPayments = salesPaymentRepository.getSalesPayments(hawkerId, fromDate, toDate);
        for (PaymentHistorySales salesPayment : salesPayments) {
            totalAmount += salesPayment.getAmount();
        }
        return totalAmount;
    }

    @Override
    public void addSalesBill(BillingSales billingSales) {
        salesBillingRepository.save(billingSales);
    }

    @Override
    public void updateSalesBill(BillingSales billingSales) {
        salesBillingRepository.update(billingSales);
    }

    @Override
    public BillingSales getSalesBill(int billId) {
        return salesBillingRepository.get(billId);
    }

    @Override
    public List<BillingSales> getSalesBillList(Date fromDate, Date toDate) {
        return salesBillingRepository.getSalesBillList(fromDate, toDate);
    }

    @Override
    public void generateBill(Hawker hawker) {
        // Steps
        Float totalAmount = null;
        // get the last bill endDate
        Date lastBillEndDate = salesBillingRepository.getLastBillEndDate(hawker);
        // fetch all the transactions between today and last endDate
        List<SalesTransaction> salesTransactions = salesTransactionRepository.getSalesTransactions(lastBillEndDate, DateUtils.currentDate());
        // generate Bill
        for (SalesTransaction salesTransaction : salesTransactions) {
            if (salesTransaction.getTransactionType().equals(SalesTransactionType.SALES)
                    || salesTransaction.getTransactionType().equals(SalesTransactionType.SCRAP)) {
                totalAmount += salesTransaction.getTotalAmount();
            }
            if (salesTransaction.getTransactionType().equals(SalesTransactionType.RETURNS)) {
                totalAmount -= salesTransaction.getTotalAmount();
            }
        }
        // check if any payments were made for this time span and reduce that amount
        totalAmount -= this.getTotalAmountForCycle(hawker.getHawkerId(), lastBillEndDate, DateUtils.currentDate());
        BillingSales billingSales = new BillingSales(hawker.getHawkerId(), lastBillEndDate, DateUtils.currentDate(), totalAmount);
        salesBillingRepository.save(billingSales);
    }
}
