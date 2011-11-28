package com.bis.web.controller;

import com.bis.common.DateUtils;
import com.bis.core.services.HawkerMasterService;
import com.bis.domain.*;
import com.bis.sales.services.SalesBillingService;
import com.bis.sales.services.SalesPaymentService;
import com.bis.sales.services.SalesTransactionService;
import com.bis.web.viewmodel.SalesBillingDetails;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        SalesBillingDetails salesBillingDetails = new SalesBillingDetails();
        return new ModelAndView("salesBilling/billSelectHawker","SalesBillingDetails",salesBillingDetails);
    }

    @RequestMapping(value = "/generateBill",method = RequestMethod.GET)
    public ModelAndView generateBill(@RequestParam(value = "hawkerId", required = true)int hawkerId) {
        Hawker hawker = hawkerMasterService.get(hawkerId);
        Date nextBillDate = salesBillingService.getNextBillDate(hawker);
        BillingSales lastBill = salesBillingService.getLastBill(hawker);
        Date currentDate = DateUtils.currentDate();
        List<SalesTransaction> salesTransactions = salesTransactionService.getSalesTransactions(nextBillDate, currentDate, hawker);
        List<PaymentHistorySales> salesPayments = salesPaymentService.getSalesPayments(hawker, nextBillDate, currentDate);
        SalesBillingDetails salesBillingDetails = new SalesBillingDetails(salesTransactions, salesPayments, lastBill);
        return new ModelAndView("salesBilling/showBill","SalesBillingDetails",salesBillingDetails);
    }

    @RequestMapping(value = "/showLastBill",method = RequestMethod.GET)
    public ModelAndView showLastBill(@RequestParam(value = "hawkerId", required = true)int hawkerId) {
        Hawker hawker = hawkerMasterService.get(hawkerId);
        BillingSales billingSales = salesBillingService.getLastBill(hawker);
        return new ModelAndView("salesBilling/showBill","BillingSales",billingSales);
    }

    @RequestMapping(value = "/saveSalesBill", method = RequestMethod.POST)
    public String addProcurementBill(@Valid SalesBillingDetails salesBillingDetails, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        Date currentDate = DateUtils.currentDate();
        BillingSales billingSales = salesBillingService.generateSalesBill(salesBillingDetails.getHawker());
        if (salesBillingDetails.getPaymentAmount() != null) {
            PaymentHistorySales paymentHistorySales = new PaymentHistorySales();
            paymentHistorySales.setAmount(salesBillingDetails.getPaymentAmount());
            paymentHistorySales.setDate(currentDate);
            paymentHistorySales.setMode(salesBillingDetails.getMode());
            paymentHistorySales.setReceiptNum(salesBillingDetails.getReceiptNum());
            paymentHistorySales.setRemarks(salesBillingDetails.getRemarks());
            paymentHistorySales.setHawker(salesBillingDetails.getHawker());
            salesPaymentService.addSalesPayment(paymentHistorySales);
        }
        billingSales.setBalanceAmount(billingSales.getBalanceAmount() - salesBillingDetails.getPaymentAmount());
        salesBillingService.addSalesBill(billingSales);
        return "redirect:/salesBilling/show/" + billingSales.getBillId();
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public ModelAndView showBill(@PathVariable("id") int billId) {
        BillingSales billingSales = salesBillingService.getSalesBill(billId);
        return new ModelAndView("salesBilling/show", "BillingSales", billingSales);
    }

    @ModelAttribute("hawkers")
    public List<Hawker> hawkers() {
        return hawkerMasterService.getAll();
    }

    @ModelAttribute("PaymentMode")
    public Map<Character, String> paymentMode() {
        Map<Character, String> paymentModes = new HashMap<Character, String>();
        for (PaymentMode paymentMode : PaymentMode.values()) {
            paymentModes.put(paymentMode.getCode(), paymentMode.toString());
        }
        return paymentModes;
    }
}
