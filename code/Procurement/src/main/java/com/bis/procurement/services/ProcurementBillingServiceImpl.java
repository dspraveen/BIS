package com.bis.procurement.services;

import com.bis.domain.BillingProcurement;
import com.bis.domain.PaymentHistoryProcurement;
import com.bis.procurement.repository.ProcurementBillingRepository;
import com.bis.procurement.repository.ProcurementPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProcurementBillingServiceImpl implements ProcurementBillingService{

    private ProcurementBillingRepository procurementBillingRepository;
    private ProcurementPaymentRepository procurementPaymentRepository;

    @Autowired
    public ProcurementBillingServiceImpl(ProcurementPaymentRepository procurementPaymentRepository, ProcurementBillingRepository procurementBillingRepository) {
        this.procurementPaymentRepository = procurementPaymentRepository;
        this.procurementBillingRepository = procurementBillingRepository;
    }

    @Override
    public void createProcurementBill(BillingProcurement billingProcurement) {
        procurementBillingRepository.save(billingProcurement);
    }
}
