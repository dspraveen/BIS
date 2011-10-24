package com.bis.procurement.services;

import com.bis.domain.BillingProcurement;
import com.bis.procurement.repository.ProcurementBillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcurementBillingServiceImpl implements ProcurementBillingService{

    private ProcurementBillingRepository procurementBillingRepository;

    @Autowired
    public ProcurementBillingServiceImpl(ProcurementBillingRepository procurementBillingRepository) {
        this.procurementBillingRepository = procurementBillingRepository;
    }

    @Override
    public void createProcurementBill(BillingProcurement billingProcurement) {
        procurementBillingRepository.save(billingProcurement);
    }
}
