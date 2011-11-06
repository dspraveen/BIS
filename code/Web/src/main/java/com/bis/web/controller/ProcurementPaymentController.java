package com.bis.web.controller;

import com.bis.common.DateUtils;
import com.bis.core.services.VendorMasterService;
import com.bis.domain.PaymentHistoryProcurement;
import com.bis.domain.PaymentMode;
import com.bis.domain.Vendor;
import com.bis.procurement.services.ProcurementPaymentService;
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
@RequestMapping("/procurementPayment")
public class ProcurementPaymentController {

    protected final Logger logger = Logger.getLogger(getClass());

    private ProcurementPaymentService procurementPaymentService;
    private VendorMasterService vendorMasterService;

    @Autowired
    public ProcurementPaymentController(ProcurementPaymentService procurementPaymentService, VendorMasterService vendorMasterService) {
        this.procurementPaymentService = procurementPaymentService;
        this.vendorMasterService = vendorMasterService;
    }

    @RequestMapping(value = "/show/{id}",method = RequestMethod.GET)
    public ModelAndView show(@PathVariable("id") int paymentId) {
        PaymentHistoryProcurement paymentHistoryProcurement = procurementPaymentService.getProcurementPayment(paymentId);
        return new ModelAndView("procurementPayment/show", "PaymentHistoryProcurement", paymentHistoryProcurement);
    }

    @RequestMapping(value = "/createProcurementPayment",method = RequestMethod.GET)
    public ModelAndView createForm() {
        PaymentHistoryProcurement paymentHistoryProcurement = new PaymentHistoryProcurement();
        paymentHistoryProcurement.setMode('C');
        paymentHistoryProcurement.setDate(DateUtils.currentDate());
        return new ModelAndView("procurementPayment/createProcurementPayment", "PaymentHistoryProcurement", paymentHistoryProcurement);
    }

    @RequestMapping(value = "/updateForm/{id}",method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") int paymentId) {
        PaymentHistoryProcurement paymentHistoryProcurement = procurementPaymentService.getProcurementPayment(paymentId);
        return new ModelAndView("procurementPayment/updateForm", "PaymentHistoryProcurement", paymentHistoryProcurement);
    }

    @RequestMapping(value = "/createProcurementPayment",method = RequestMethod.POST)
    public String addProcurementPayment(@Valid PaymentHistoryProcurement paymentHistoryProcurement, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        procurementPaymentService.addProcurementPayment(paymentHistoryProcurement);
        return "redirect:/procurementPayment/show/" + paymentHistoryProcurement.getPaymentId();
    }

    @RequestMapping(value = "/updateProcurementPayment",method = RequestMethod.POST)
    public String updateProcurementPayment(@Valid PaymentHistoryProcurement paymentHistoryProcurement, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        procurementPaymentService.updateProcurementPayment(paymentHistoryProcurement);
        return "redirect:/procurementPayment/show/" + paymentHistoryProcurement.getPaymentId();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        return new ModelAndView("procurementPayment/list");
    }

    @RequestMapping(value = "/paymentsInRange", method = RequestMethod.GET)
    public ModelAndView transactionsBetween(@RequestParam(value = "fromDate", required = true)Date fromDate, @RequestParam(value = "toDate", required = true)Date toDate ) {
        List<PaymentHistoryProcurement> paymentHistoryProcurements = procurementPaymentService.getProcurementPayments(fromDate, toDate);
        return new ModelAndView("procurementPayment/paymentsInRange","paymentHistoryProcurements",paymentHistoryProcurements);
    }

    @ModelAttribute("PaymentMode")
    public Map<Character,String> paymentMode(){
        Map<Character,String> paymentModes = new HashMap<Character,String>();
        for(PaymentMode paymentMode: PaymentMode.values()){
            paymentModes.put(paymentMode.getCode(), paymentMode.toString());
        }
        return paymentModes;
    }

    @ModelAttribute("vendors")
    public List<Vendor> vendors() {
        return vendorMasterService.getAll();
    }
}
