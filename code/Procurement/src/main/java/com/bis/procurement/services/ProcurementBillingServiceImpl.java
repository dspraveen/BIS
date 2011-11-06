package com.bis.procurement.services;

import com.bis.common.DateUtils;
import com.bis.domain.*;
import com.bis.procurement.repository.ProcurementBillingRepository;
import com.bis.procurement.repository.ProcurementPaymentRepository;
import com.bis.procurement.repository.ProcurementTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProcurementBillingServiceImpl implements ProcurementBillingService{

    private ProcurementBillingRepository procurementBillingRepository;
    private ProcurementTransactionRepository procurementTransactionRepository;
    private ProcurementPaymentRepository procurementPaymentRepository;

    @Autowired
    public ProcurementBillingServiceImpl(ProcurementBillingRepository procurementBillingRepository, ProcurementTransactionRepository procurementTransactionRepository, ProcurementPaymentRepository procurementPaymentRepository) {
        this.procurementBillingRepository = procurementBillingRepository;
        this.procurementTransactionRepository = procurementTransactionRepository;
        this.procurementPaymentRepository = procurementPaymentRepository;
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

    private Float getTotalAmountForCycle(int vendorID, Date fromDate, Date toDate) {
        Float totalAmount = null;
        List<PaymentHistoryProcurement> procurementPayments = procurementPaymentRepository.getProcurementPayments(vendorID, fromDate, toDate);
        for(PaymentHistoryProcurement paymentHistoryProcurement : procurementPayments){
            totalAmount += paymentHistoryProcurement.getAmount();
        }
        return totalAmount;
    }

    @Override
    public void generateProcurementBill(Vendor vendor) {
        // Steps
        Float totalAmount = null;
        // get last bill end date
        BillingProcurement billingProcurement = procurementBillingRepository.getLastBill(vendor);
        // this amount should include the balance amount of the previous bill
        totalAmount = billingProcurement.getBalanceAmount();
        // fetch all the transactions between today and last endDate
        List<ProcurementTransaction> procurementTransactions = procurementTransactionRepository.getProcurementTransactions(vendor.getVendorId(), billingProcurement.getEndDate(), DateUtils.currentDate());
        // generate bill
        for(ProcurementTransaction procurementTransaction : procurementTransactions){
            if(procurementTransaction.getTransactionType().equals(ProcurementTransactionType.PURCHASE)){
                totalAmount += procurementTransaction.getTotalAmount();
            }
            else if(procurementTransaction.getTransactionType().equals(ProcurementTransactionType.RETURNS)){
                totalAmount -= procurementTransaction.getTotalAmount();
            }
        }
        // get the total payment made during this cycle
        totalAmount -= this.getTotalAmountForCycle(vendor.getVendorId(),billingProcurement.getEndDate(),DateUtils.currentDate());
        BillingProcurement billingProcurementNew = new BillingProcurement(billingProcurement.getEndDate(), DateUtils.currentDate(),totalAmount, vendor);
        procurementBillingRepository.save(billingProcurementNew);
    }
}
