package com.bis.procurement.services;

import com.bis.common.DateUtils;
import com.bis.domain.BillingProcurement;
import com.bis.domain.PaymentHistoryProcurement;
import com.bis.domain.ProcurementTransaction;
import com.bis.domain.Vendor;
import com.bis.procurement.repository.ProcurementBillingRepository;
import com.bis.procurement.repository.ProcurementPaymentRepository;
import com.bis.procurement.repository.ProcurementTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProcurementBillingServiceImpl implements ProcurementBillingService {

    private ProcurementBillingRepository procurementBillingRepository;
    private ProcurementTransactionRepository procurementTransactionRepository;
    private ProcurementPaymentRepository procurementPaymentRepository;

    @Autowired
    public ProcurementBillingServiceImpl(ProcurementBillingRepository procurementBillingRepository, ProcurementTransactionRepository procurementTransactionRepository, ProcurementPaymentRepository procurementPaymentRepository) {
        this.procurementBillingRepository = procurementBillingRepository;
        this.procurementTransactionRepository = procurementTransactionRepository;
        this.procurementPaymentRepository = procurementPaymentRepository;
    }

    public ProcurementBillingServiceImpl(ProcurementBillingRepository procurementBillingRepository) {
        this.procurementBillingRepository = procurementBillingRepository;
    }

    public void addProcurementBill(BillingProcurement billingProcurement) {
        procurementBillingRepository.save(billingProcurement);
    }

    @Override
    public void updateProcurementBill(BillingProcurement billingProcurement) {
        procurementBillingRepository.save(billingProcurement);
    }

    @Override
    public BillingProcurement getProcurementBill(int billID) {
        return procurementBillingRepository.get(billID);
    }

    @Override
    public List<BillingProcurement> getProcurementBillList(Date fromDate, Date toDate) {
        return procurementBillingRepository.getProcurementBillList(fromDate, toDate);
    }

    private Float getTotalAmountForCycle(Vendor vendor, Date fromDate, Date toDate) {
        Float totalAmount = 0F;
        List<PaymentHistoryProcurement> procurementPayments = procurementPaymentRepository.getProcurementPayments(vendor, fromDate, toDate);
        for (PaymentHistoryProcurement paymentHistoryProcurement : procurementPayments) {
            totalAmount = totalAmount + paymentHistoryProcurement.getAmount();
        }
        return totalAmount;
    }

    @Override
    public BillingProcurement generateProcurementBill(Vendor vendor) {
        // Steps
        Float totalAmount = 0f;
        Date fromDate = null;
        List<ProcurementTransaction> procurementTransactions = null;
        // get last bill end date
        BillingProcurement billingProcurement = procurementBillingRepository.getLastBill(vendor);
        // this amount should include the balance amount of the previous bill
        if (billingProcurement != null) {
            totalAmount = billingProcurement.getBalanceAmount();
            fromDate = billingProcurement.getEndDate();
            // fetch all the transactions between today and last endDate
            procurementTransactions = procurementTransactionRepository.getProcurementTransactions(vendor, fromDate, DateUtils.getNowDate());
        } else {
            // fetch all the transactions till today
            procurementTransactions = procurementTransactionRepository.getProcurementTransactions(vendor, DateUtils.getNowDate());
            if (procurementTransactions != null) {
                fromDate = procurementTransactions.get(0).getDate();
            }
        }
        // generate bill
        if (procurementTransactions != null) {
            for (ProcurementTransaction procurementTransaction : procurementTransactions) {
                if (procurementTransaction.getTransactionType().equals('S')) {
                    totalAmount += procurementTransaction.getTotalAmount();
                } else if (procurementTransaction.getTransactionType().equals('R')) {
                    totalAmount -= procurementTransaction.getTotalAmount();
                }
            }
        }
        // get the total payment made during this cycle
        totalAmount -= getTotalAmountForCycle(vendor, fromDate, DateUtils.getNowDate());
        return new BillingProcurement(fromDate, DateUtils.getNowDate(), totalAmount, vendor);
    }

    @Override
    public BillingProcurement getLastBill(Vendor vendor) {
        return procurementBillingRepository.getLastBill(vendor);
    }
}
