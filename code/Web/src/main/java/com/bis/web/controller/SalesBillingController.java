package com.bis.web.controller;

import com.bis.common.DateUtils;
import com.bis.core.services.HawkerMasterService;
import com.bis.domain.BillingSales;
import com.bis.domain.Hawker;
import com.bis.domain.PaymentHistorySales;
import com.bis.domain.SalesTransaction;
import com.bis.sales.services.SalesBillingService;
import com.bis.sales.services.SalesPaymentService;
import com.bis.sales.services.SalesTransactionService;
import com.bis.web.viewmodel.SalesBillingDetails;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/salesBilling")
public class SalesBillingController {

    protected final Logger logger = Logger.getLogger(getClass());
    private SalesBillingService salesBillingService;
    private HawkerMasterService hawkerMasterService;
    private SalesTransactionService salesTransactionService;
    private SalesPaymentService salesPaymentService;

    @Autowired
    public SalesBillingController(HawkerMasterService hawkerMasterService, SalesBillingService salesBillingService, SalesTransactionService salesTransactionService, SalesPaymentService salesPaymentService) {
        this.hawkerMasterService = hawkerMasterService;
        this.salesBillingService = salesBillingService;
        this.salesTransactionService = salesTransactionService;
        this.salesPaymentService = salesPaymentService;
    }

    @RequestMapping(value = "/billSelectHawker", method = RequestMethod.GET)
    public ModelAndView list() {
        BillingSales billingSales = new BillingSales();
        return new ModelAndView("salesBilling/billSelectHawker","BillingSales",billingSales);
    }

    @RequestMapping(value = "/generateBill",method = RequestMethod.GET)
    public ModelAndView generateBill(@RequestParam(value = "hawkerId", required = true)int hawkerId) {
        Hawker hawker = hawkerMasterService.get(hawkerId);
        Date nextBillDate = salesBillingService.getNextBillDate(hawkerMasterService.get(hawkerId));
        Date currentDate = DateUtils.currentDate();
        List<SalesTransaction> salesTransactions = salesTransactionService.getSalesTransactions(nextBillDate, currentDate, hawker);
        List<PaymentHistorySales> salesPayments = salesPaymentService.getSalesPayments(hawker, nextBillDate, currentDate);
        SalesBillingDetails salesBillingDetails = new SalesBillingDetails(salesTransactions, salesPayments);
        return new ModelAndView("salesBilling/showBill","SalesBillingDetails",salesBillingDetails);
    }

    @RequestMapping(value = "/showLastBill",method = RequestMethod.GET)
    public ModelAndView showLastBill(@RequestParam(value = "hawkerId", required = true)int hawkerId) {
        Hawker hawker = hawkerMasterService.get(hawkerId);
        BillingSales billingSales = salesBillingService.getLastBill(hawker);
        return new ModelAndView("salesBilling/showBill","BillingSales",billingSales);
    }

    @ModelAttribute("hawkers")
    public List<Hawker> hawkers() {
        return hawkerMasterService.getAll();
    }
}
