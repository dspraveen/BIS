package com.bis.web.controller;

import com.bis.core.services.VendorMasterService;
import com.bis.domain.BillingProcurement;
import com.bis.domain.Vendor;
import com.bis.procurement.services.ProcurementBillingService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/procurementBilling")
public class ProcurementBillingController {

    protected final Logger logger = Logger.getLogger(getClass());
    private ProcurementBillingService procurementBillingService;
    private VendorMasterService vendorMasterService;

    @Autowired
    public ProcurementBillingController(ProcurementBillingService procurementBillingService, VendorMasterService vendorMasterService) {
        this.procurementBillingService = procurementBillingService;
        this.vendorMasterService = vendorMasterService;
    }

    @RequestMapping(value = "/billSelectVendor", method = RequestMethod.GET)
    public ModelAndView list() {
        BillingProcurement billingProcurement = new BillingProcurement();
        return new ModelAndView("procurementBilling/billSelectVendor","BillingProcurement",billingProcurement);
    }

    @ModelAttribute("vendors")
    public List<Vendor> vendors() {
        return vendorMasterService.getAll();
    }
}
