package com.bis.web.controller;

import com.bis.common.DateUtils;
import com.bis.core.services.VendorMasterService;
import com.bis.domain.*;
import com.bis.procurement.services.ProcurementBillingService;
import com.bis.procurement.services.ProcurementPaymentService;
import com.bis.procurement.services.ProcurementTransactionService;
import com.bis.web.viewmodel.ProcurementBillingDetails;
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
@RequestMapping("/procurementBilling")
public class ProcurementBillingController {

    protected final Logger logger = Logger.getLogger(getClass());
    private ProcurementBillingService procurementBillingService;
    private VendorMasterService vendorMasterService;
    private ProcurementTransactionService procurementTransactionService;
    private ProcurementPaymentService procurementPaymentService;

    @Autowired
    public ProcurementBillingController(ProcurementBillingService procurementBillingService, VendorMasterService vendorMasterService, ProcurementTransactionService procurementTransactionService, ProcurementPaymentService procurementPaymentService) {
        this.procurementBillingService = procurementBillingService;
        this.vendorMasterService = vendorMasterService;
        this.procurementTransactionService = procurementTransactionService;
        this.procurementPaymentService = procurementPaymentService;
    }

    @RequestMapping(value = "/billSelectVendor", method = RequestMethod.GET)
    public ModelAndView list() {
        ProcurementBillingDetails procurementBillingDetails = new ProcurementBillingDetails();
        return new ModelAndView("procurementBilling/billSelectVendor", "ProcurementBillingDetails", procurementBillingDetails);
    }

    @RequestMapping(value = "/generateBill", method = RequestMethod.GET)
    public ModelAndView generateBill(@RequestParam(value = "vendorId", required = true) int vendorId) {
        Vendor vendor = vendorMasterService.get(vendorId);
        Date nextBillDate = procurementBillingService.getNextBillDate(vendor);
        Date currentDate = DateUtils.currentDate();
        List<ProcurementTransaction> procurementTransactions = procurementTransactionService.getProcurementTransactions(nextBillDate, currentDate, vendor);
        List<PaymentHistoryProcurement> procurementPayments = procurementPaymentService.getProcurementPayments(vendor, nextBillDate, currentDate);
        ProcurementBillingDetails procurementBillingDetails = new ProcurementBillingDetails(procurementTransactions, procurementPayments);
        return new ModelAndView("procurementBilling/showBill", "ProcurementBillingDetails", procurementBillingDetails);
    }

    @RequestMapping(value = "/showLastBill", method = RequestMethod.GET)
    public ModelAndView showLastBill(@RequestParam(value = "vendorId", required = true) int vendorId) {
        Vendor vendor = vendorMasterService.get(vendorId);
        BillingProcurement billingProcurement = procurementBillingService.getLastBill(vendor);
        return new ModelAndView("procurementBilling/showBill", "BillingProcurement", billingProcurement);
    }

    @RequestMapping(value = "/saveProcurementBill", method = RequestMethod.POST)
    public String addProcurementBill(@Valid ProcurementBillingDetails procurementBillingDetails, BindingResult bindingResult, Model uiModel) {
        double balanceAmount = 0f;
        uiModel.asMap().clear();
        Date nextBillDate = procurementBillingService.getNextBillDate(procurementBillingDetails.getVendor());
        Date currentDate = DateUtils.currentDate();
        List<ProcurementTransaction> procurementTransactions = procurementTransactionService.getProcurementTransactions(nextBillDate, currentDate, procurementBillingDetails.getVendor());
        if (procurementTransactions != null) {
            procurementBillingDetails.setProcurementTransactions(procurementTransactions);
        }
        List<PaymentHistoryProcurement> procurementPayments = procurementPaymentService.getProcurementPayments(procurementBillingDetails.getVendor(), nextBillDate, currentDate);
        if (procurementPayments != null) {
            procurementBillingDetails.setProcurementPayments(procurementPayments);
        }
        balanceAmount = procurementBillingDetails.getBillAmount();
        if(procurementBillingDetails.getPaymentAmount() != null){
            balanceAmount = balanceAmount - procurementBillingDetails.getPaymentAmount();
        }

        BillingProcurement billingProcurement = new BillingProcurement(nextBillDate, currentDate, (float) balanceAmount, procurementBillingDetails.getVendor());
        if (procurementBillingDetails.getPaymentAmount() != null) {
            PaymentHistoryProcurement paymentHistoryProcurement = new PaymentHistoryProcurement();
            paymentHistoryProcurement.setAmount(procurementBillingDetails.getPaymentAmount());
            paymentHistoryProcurement.setDate(currentDate);
            paymentHistoryProcurement.setMode(procurementBillingDetails.getMode());
            paymentHistoryProcurement.setReceiptNum(procurementBillingDetails.getReceiptNum());
            paymentHistoryProcurement.setRemarks(procurementBillingDetails.getRemarks());
            paymentHistoryProcurement.setVendor(procurementBillingDetails.getVendor());
            procurementPaymentService.addProcurementPayment(paymentHistoryProcurement);
        }
        procurementBillingService.addProcurementBill(billingProcurement);
        return "redirect:/procurementBilling/show/" + billingProcurement.getBillId();
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public ModelAndView showBill(@PathVariable("id") int billId) {
        BillingProcurement billingProcurement = procurementBillingService.getProcurementBill(billId);
        return new ModelAndView("procurementBilling/show", "BillingProcurement", billingProcurement);
    }

    @ModelAttribute("vendors")
    public List<Vendor> vendors() {
        return vendorMasterService.getAll();
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
