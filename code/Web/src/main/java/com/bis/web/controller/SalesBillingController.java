package com.bis.web.controller;

import com.bis.core.services.HawkerMasterService;
import com.bis.domain.BillingSales;
import com.bis.domain.Hawker;
import com.bis.sales.services.SalesBillingService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/salesBilling")
public class SalesBillingController {

    protected final Logger logger = Logger.getLogger(getClass());
    private SalesBillingService salesBillingService;
    private HawkerMasterService hawkerMasterService;

    @Autowired
    public SalesBillingController(HawkerMasterService hawkerMasterService, SalesBillingService salesBillingService) {
        this.hawkerMasterService = hawkerMasterService;
        this.salesBillingService = salesBillingService;
    }

    @RequestMapping(value = "/billSelectHawker", method = RequestMethod.GET)
    public ModelAndView list() {
        BillingSales billingSales = new BillingSales();
        return new ModelAndView("salesBilling/billSelectHawker","BillingSales",billingSales);
    }

    @RequestMapping(value = "/generateBill",method = RequestMethod.GET)
    public ModelAndView generateBill(@RequestParam(value = "hawkerId", required = true)int hawkerId) {
        Hawker hawker = hawkerMasterService.get(hawkerId);
        BillingSales billingSales = salesBillingService.generateSalesBill(hawker);
        salesBillingService.addSalesBill(billingSales);
        return new ModelAndView("salesBilling/showBill","BillingSales",billingSales);
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
