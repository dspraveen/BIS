package com.bis.web.controller;

import com.bis.common.DateUtils;
import com.bis.core.services.HawkerMasterService;
import com.bis.core.services.VendorMasterService;
import com.bis.domain.*;
import com.bis.sales.services.SalesPaymentService;
import com.bis.sales.services.SalesTransactionService;
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
@RequestMapping("/salesPayment")
public class SalesPaymentController{

    protected final Logger logger = Logger.getLogger(getClass());

    private SalesPaymentService salesPaymentService;
    private HawkerMasterService hawkerMasterService;

    @Autowired
    public SalesPaymentController(SalesPaymentService salesPaymentService, HawkerMasterService hawkerMasterService) {
        this.salesPaymentService = salesPaymentService;
        this.hawkerMasterService = hawkerMasterService;
    }

    @RequestMapping(value = "/show/{id}",method = RequestMethod.GET)
    public ModelAndView show(@PathVariable("id") int paymentId) {
        PaymentHistorySales paymentHistorySales = salesPaymentService.getSalesPayment(paymentId);
        return new ModelAndView("salesPayment/show", "PaymentHistorySales", paymentHistorySales);
    }

    @RequestMapping(value = "/createForm",method = RequestMethod.GET)
    public ModelAndView createForm() {
        PaymentHistorySales paymentHistorySales = new PaymentHistorySales();
        paymentHistorySales.setMode('C');
        paymentHistorySales.setDate(DateUtils.currentDate());
        return new ModelAndView("salesPayment/createForm", "PaymentHistorySales", paymentHistorySales);
    }


    @RequestMapping(value = "/updateForm/{id}",method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") int paymentId) {
        PaymentHistorySales paymentHistorySales = salesPaymentService.getSalesPayment(paymentId);
        return new ModelAndView("salesPayment/updateForm", "PaymentHistorySales", paymentHistorySales);
    }

    @RequestMapping(value = "/addSalesPayment",method = RequestMethod.POST)
    public String addSalesPayment(@Valid PaymentHistorySales paymentHistorySales, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        salesPaymentService.addSalesPayment(paymentHistorySales);
        return "redirect:/salesPayment/show/"+paymentHistorySales.getPaymentId();
    }

    @RequestMapping(value = "/updateSalesPayment",method = RequestMethod.POST)
    public String updateSalesPayment(@Valid PaymentHistorySales paymentHistorySales, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        salesPaymentService.updateSalesPayment(paymentHistorySales);
        return "redirect:/salesPayment/show/" + paymentHistorySales.getPaymentId();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        return new ModelAndView("salesPayment/list");
    }

    @RequestMapping(value = "/transactionsInRange", method = RequestMethod.GET)
    public ModelAndView transactionsBetween(@RequestParam(value = "fromDate", required = true)Date fromDate, @RequestParam(value = "toDate", required = true)Date toDate ) {
        List<PaymentHistorySales> salesPayments = salesPaymentService.getSalesPayments(fromDate, toDate);
        return new ModelAndView("salesPayment/transactionsInRange","paymentHistorySales",salesPayments);
    }



    @ModelAttribute("PaymentMode")
    public Map<Character,String> paymentMode(){
        Map<Character,String> paymentModes = new HashMap<Character,String>();
        for(PaymentMode paymentMode: PaymentMode.values()){
            paymentModes.put(paymentMode.getCode(), paymentMode.toString());
        }
        return paymentModes;
    }

    @ModelAttribute("hawkers")
    public List<Hawker> hawkers() {
        return hawkerMasterService.getAll();
    }
}
