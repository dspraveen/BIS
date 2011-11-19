package com.bis.web.controller;

import com.bis.common.DateUtils;
import com.bis.core.services.VendorMasterService;
import com.bis.domain.BillingProcurement;
import com.bis.domain.PaymentHistoryProcurement;
import com.bis.domain.ProcurementTransaction;
import com.bis.domain.Vendor;
import com.bis.procurement.services.ProcurementBillingService;
import com.bis.procurement.services.ProcurementPaymentService;
import com.bis.procurement.services.ProcurementTransactionService;
import com.bis.web.viewmodel.ProcurementBillingDetails;
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
@RequestMapping("/procurementBilling")
public class ProcurementBillingController {

    protected final Logger logger = Logger.getLogger(getClass());
    private ProcurementBillingService procurementBillingService;
    private VendorMasterService vendorMasterService;
    private ProcurementTransactionService procurementTransactionService;
    private ProcurementPaymentService procurementPaymentService;

    @Autowired
    public ProcurementBillingController(ProcurementBillingService procurementBillingService, VendorMasterService vendorMasterService,ProcurementTransactionService procurementTransactionService, ProcurementPaymentService procurementPaymentService) {
        this.procurementBillingService = procurementBillingService;
        this.vendorMasterService = vendorMasterService;
        this.procurementTransactionService = procurementTransactionService;
        this.procurementPaymentService = procurementPaymentService;
    }

    @RequestMapping(value = "/billSelectVendor", method = RequestMethod.GET)
    public ModelAndView list() {
        BillingProcurement billingProcurement = new BillingProcurement();
        return new ModelAndView("procurementBilling/billSelectVendor","BillingProcurement",billingProcurement);
    }

    @RequestMapping(value = "/generateBill",method = RequestMethod.GET)
    public ModelAndView generateBill(@RequestParam(value = "vendorId", required = true)int vendorId) {
        Vendor vendor = vendorMasterService.get(vendorId);
        Date nextBillDate =  procurementBillingService.getNextBillDate(vendor);
        Date currentDate = DateUtils.currentDate();
        List<ProcurementTransaction> procurementTransactions = procurementTransactionService.getProcurementTransactions(nextBillDate, currentDate, vendor);
        List<PaymentHistoryProcurement> procurementPayments = procurementPaymentService.getProcurementPayments(vendor, nextBillDate, currentDate);
        ProcurementBillingDetails procurementBillingDetails = new ProcurementBillingDetails(procurementTransactions, procurementPayments);
        return new ModelAndView("procurementBilling/showBill","ProcurementBillingDetails",procurementBillingDetails);
    }

    @RequestMapping(value = "/showLastBill",method = RequestMethod.GET)
    public ModelAndView showLastBill(@RequestParam(value = "vendorId", required = true)int vendorId) {
        Vendor vendor = vendorMasterService.get(vendorId);
        BillingProcurement billingProcurement = procurementBillingService.getLastBill(vendor);
        return new ModelAndView("procurementBilling/showBill","BillingProcurement",billingProcurement);
    }

    @ModelAttribute("vendors")
    public List<Vendor> vendors() {
        return vendorMasterService.getAll();
    }
}
