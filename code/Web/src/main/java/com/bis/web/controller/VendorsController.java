package com.bis.web.controller;

import com.bis.core.services.VendorMasterService;
import com.bis.domain.BillingCycle;
import com.bis.domain.Vendor;
import com.bis.web.viewmodel.VendorList;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/vendor")
public class VendorsController extends BaseController {

    protected final Logger logger = Logger.getLogger(getClass());

    private VendorMasterService vendorMasterService;

    @Autowired
    public VendorsController(VendorMasterService vendorMasterService) {
        this.vendorMasterService = vendorMasterService;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable("id") int vendorCode) {
        Vendor vendor = vendorMasterService.get(vendorCode);
        return new ModelAndView("vendor/show", "vendor", vendor);
    }

    @RequestMapping(value = "/createForm", method = RequestMethod.GET)
    public ModelAndView createForm() {
        Vendor vendor = new Vendor();
        vendor.setBillingCycle('W');
        return new ModelAndView("vendor/createForm", "vendor", vendor);
    }

    @RequestMapping(value = "/updateForm/{id}", method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") int vendorCode) {
        Vendor vendor = vendorMasterService.get(vendorCode);
        return new ModelAndView("vendor/updateForm", "vendor", vendor);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid Vendor vendor, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        vendorMasterService.create(vendor);
        return "redirect:/vendor/show/" + vendor.getVendorId();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Valid Vendor vendor, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        vendorMasterService.update(vendor);
        return "redirect:/vendor/show/" + vendor.getVendorId();
    }

    @RequestMapping(value = "/list")
    public ModelAndView list(@RequestParam(value = "selectedVendorId", defaultValue = "0", required = false) int selectedVendorId) {
        List<Vendor> all = vendorMasterService.getAll();
        Collections.sort(all, new Comparator<Vendor>() {
            @Override
            public int compare(Vendor o1, Vendor o2) {
                return o1.getVendorName().compareToIgnoreCase(o2.getVendorName());
            }
        });
        return new ModelAndView("vendor/list", "vendorList", new VendorList(selectedVendorId, all));
    }

    @RequestMapping(value = "/discount", method = RequestMethod.GET)
    public ModelAndView getVendorDiscount(@RequestParam(value = "selectedVendorId", defaultValue = "0", required = false) int selectedVendorId, HttpServletResponse response) {
        Vendor vendor = vendorMasterService.get(selectedVendorId);
        Map<String, Float> discount = new HashMap<String, Float>();
        discount.put("discount", vendor.getVendorDiscount());
        return json(discount, response);
    }

    @ModelAttribute("billingCycles")
    public Map<Character, String> billingCycles() {
        Map<Character, String> billingCycles = new HashMap<Character, String>();
        for (BillingCycle billingCycle : BillingCycle.values()) {
            billingCycles.put(billingCycle.getCode(), billingCycle.toString());
        }
        return billingCycles;
    }
}
