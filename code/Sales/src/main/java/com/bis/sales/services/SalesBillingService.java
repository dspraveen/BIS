package com.bis.sales.services;

import com.bis.domain.BillingSales;
import com.bis.domain.Hawker;

import java.util.Date;
import java.util.List;

public interface SalesBillingService {

    void addSalesBill(BillingSales billingSales);

    void updateSalesBill(BillingSales billingSales);

    BillingSales getSalesBill(int billId);

    List<BillingSales> getSalesBillList(Date fromDate, Date toDate);

    void generateBill(Hawker hawker);
}
