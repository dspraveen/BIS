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
    public SalesBillingServiceImpl(SalesPaymentRepository salesPaymentRepository, SalesBillingRepository salesBillingRepository, SalesTransactionRepository salesTransactionRepository) {
        this.salesPaymentRepository = salesPaymentRepository;
        this.salesBillingRepository = salesBillingRepository;
        this.salesTransactionRepository = salesTransactionRepository;
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

    private Float getTotalAmountForCycle(Hawker hawker, Date fromDate, Date toDate) {
        Float totalAmount = 0F;
        List<PaymentHistorySales> salesPayments = salesPaymentRepository.getSalesPayments(hawker, fromDate, toDate);
        System.out.println("shashi Payments");
        for (PaymentHistorySales salesPayment : salesPayments) {
            totalAmount = totalAmount + salesPayment.getAmount();
        }
        return totalAmount;
    }

    public BillingSales generateSalesBill(Hawker hawker) {
        // Steps
        Float totalAmount = 0f;
        Date fromDate = null;
        List<SalesTransaction> salesTransactions = null;
        // get the last bill endDate
        BillingSales billingSales = salesBillingRepository.getLastBill(hawker);
            System.out.println("shashi enters");
        // this amount should include the balance amount of the previous bill
        if (billingSales != null) {
            totalAmount = billingSales.getBalanceAmount();
            fromDate = billingSales.getEndDate();
            salesTransactions = salesTransactionRepository.getSalesTransactions(hawker, fromDate, DateUtils.getNowDate());
            System.out.println("shashi first");
        } else {
            // fetch all the transactions till today
            salesTransactions = salesTransactionRepository.getSalesTransactions(hawker, DateUtils.getNowDate());
            System.out.println("shashi second");
            if (salesTransactions != null) {
                fromDate = salesTransactions.get(0).getDate();
            }
        }
        // generate Bill
        if (salesTransactions != null) {
            for (SalesTransaction salesTransaction : salesTransactions) {
                if (salesTransaction.getTransactionType().equals('S')
                        || salesTransaction.getTransactionType().equals('C')) {
                    totalAmount += salesTransaction.getTotalAmount();
                }
                if (salesTransaction.getTransactionType().equals('R')) {
                    totalAmount -= salesTransaction.getTotalAmount();
                }
            }
        }
        // check if any payments were made for this time span and reduce that amount
        totalAmount -= this.getTotalAmountForCycle(hawker, fromDate, DateUtils.getNowDate());
        return new BillingSales(fromDate, DateUtils.getNowDate(), totalAmount, hawker);
    }

    @Override
    public BillingSales getLastBill(Hawker hawker) {
        return salesBillingRepository.getLastBill(hawker);
    }
}
