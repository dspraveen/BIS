package com.bis.procurement.services;


import com.bis.domain.BillingProcurement;
import com.bis.domain.Vendor;

import java.util.Date;
import java.util.List;

public interface ProcurementBillingService {
    void addProcurementBill(BillingProcurement billingProcurement);
    void updateProcurementBill(BillingProcurement billingProcurement);
    BillingProcurement getProcurementBill(int billID);
    List<BillingProcurement> getProcurementBillList(Date fromDate, Date toDate);
    void generateProcurementBill(Vendor vendor);
}
