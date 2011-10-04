package com.bis.pb.services;


import com.bis.domain.BillingProcurement;
import com.bis.domain.PaymentHistoryProcurement;

import java.util.Date;
import java.util.List;

public interface ProcurementBillingService {
    void createProcurementBill(BillingProcurement billingProcurement);
}
