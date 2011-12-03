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
        for (PaymentHistorySales salesPayment : salesPayments) {
            totalAmount = totalAmount + salesPayment.getAmount();
        }
        return totalAmount;
    }

    public BillingSales generateSalesBill(Hawker hawker) {
        Float totalAmount = 0f;
        Float salesAmount = 0f;
        Date fromDate = null;
        List<SalesTransaction> salesTransactions = null;
        BillingSales billingSales = salesBillingRepository.getLastBill(hawker);
        if (billingSales != null) {
            totalAmount = billingSales.getBalanceAmount();
            fromDate = billingSales.getEndDate();
            salesTransactions = salesTransactionRepository.getSalesTransactions(hawker, fromDate, DateUtils.currentDate());
        } else {
            salesTransactions = salesTransactionRepository.getSalesTransactions(hawker, DateUtils.currentDate());
            if (salesTransactions != null) {
                fromDate = DateUtils.addSecond(salesTransactions.get(0).getDate(), 1);
            }
        }
        if (salesTransactions != null) {
            for (SalesTransaction salesTransaction : salesTransactions) {
                if (salesTransaction.getTransactionType().equals('S')
                        || salesTransaction.getTransactionType().equals('C')) {
                    salesAmount += salesTransaction.getTotalAmount();
                }
                if (salesTransaction.getTransactionType().equals('R')) {
                    salesAmount -= salesTransaction.getTotalAmount();
                }
            }
        }
        totalAmount += salesAmount;
        totalAmount -= this.getTotalAmountForCycle(hawker, fromDate, DateUtils.currentDate());
        return new BillingSales(fromDate, DateUtils.currentDate(), totalAmount, hawker, salesAmount);
    }

    @Override
    public BillingSales getLastBill(Hawker hawker) {
        return salesBillingRepository.getLastBill(hawker);
    }

    @Override
    public Date getNextBillDate(Hawker hawker) {
        Date nextBillDate = null;
        List<SalesTransaction> salesTransactions = null;
        BillingSales billingSales = salesBillingRepository.getLastBill(hawker);
        if (billingSales != null) {
            nextBillDate = billingSales.getEndDate();
            nextBillDate = DateUtils.addSecond(nextBillDate,1);
        } else {
            salesTransactions = salesTransactionRepository.getSalesTransactions(hawker, DateUtils.currentDate());
            if (salesTransactions != null) {
                nextBillDate = salesTransactions.get(0).getDate();
            }
        }
        return nextBillDate;
    }

    @Override
    public List<BillingSales> getSalesBillList(Hawker hawker, Date fromDate, Date toDate) {
         return salesBillingRepository.getSalesBillList(hawker, fromDate, toDate);
    }
}
