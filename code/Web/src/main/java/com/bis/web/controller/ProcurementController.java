package com.bis.web.controller;

import com.bis.common.DateUtils;
import com.bis.core.services.ItemMasterService;
import com.bis.core.services.VendorMasterService;
import com.bis.domain.Item;
import com.bis.domain.ProcurementTransaction;
import com.bis.domain.ProcurementTransactionType;
import com.bis.domain.Vendor;
import com.bis.pt.services.ProcurementTransactionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.JstlView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/procurement")
public class ProcurementController {

    protected final Logger logger = Logger.getLogger(getClass());

    private ProcurementTransactionService procurementTransactionService;
    private ItemMasterService itemMasterService;
    private VendorMasterService vendorMasterService;

    @Autowired
    public ProcurementController(ProcurementTransactionService procurementTransactionService, ItemMasterService itemMasterService, VendorMasterService vendorMasterService){
        this.procurementTransactionService = procurementTransactionService;
        this.itemMasterService = itemMasterService;
        this.vendorMasterService = vendorMasterService;
    }

    @RequestMapping(value = "/show/{id}",method = RequestMethod.GET)
    public ModelAndView show(@PathVariable("id") int transactionId) {
        ProcurementTransaction procurementTransaction = procurementTransactionService.getProcurementTransaction(transactionId);
        return new ModelAndView("procurement/show", "procurementTransaction", procurementTransaction);
    }

    @RequestMapping(value = "/createForm",method = RequestMethod.GET)
    public ModelAndView createForm() {
        ProcurementTransaction procurementTransaction = new ProcurementTransaction();
        procurementTransaction.setTransactionType('P');
        procurementTransaction.setDate(DateUtils.currentDate());
        return new ModelAndView("procurement/createForm", "procurementTransaction", procurementTransaction);
    }

    @RequestMapping(value = "/updateForm/{id}",method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") int transactionId) {
        ProcurementTransaction procurementTransaction = procurementTransactionService.getProcurementTransaction(transactionId);
        return new ModelAndView("procurement/updateForm", "procurementTransaction", procurementTransaction);
    }

    @RequestMapping(value = "/addProcurementTransaction",method = RequestMethod.POST)
    public String addProcurementTransaction(@Valid ProcurementTransaction procurementTransaction, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        procurementTransactionService.addProcurementTransaction(procurementTransaction);
        return "redirect:/procurement/show/"+procurementTransaction.getTransactionId();
    }

    @RequestMapping(value = "/updateSalesTransaction",method = RequestMethod.POST)
    public String updateProcurementTransaction(@Valid ProcurementTransaction procurementTransaction, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        procurementTransactionService.updateProcurementTransaction(procurementTransaction);
        return "redirect:/procurement/show/"+procurementTransaction.getTransactionId();
    }



    @ModelAttribute("procurementTransactionType")
    public Map<Character,String> procurementTransactionType(){
        Map<Character,String> procurementTransactionTypes = new HashMap<Character,String>();
        for(ProcurementTransactionType procurementTransactionType: ProcurementTransactionType.values()){
            procurementTransactionTypes.put(procurementTransactionType.getCode(), procurementTransactionType.toString());
        }
        return procurementTransactionTypes;
    }

    @ModelAttribute("items")
    public List<Item> items(){
        return itemMasterService.getAll();
    }

    @ModelAttribute("vendors")
    public List<Vendor> vendors(){
        return vendorMasterService.getAll();
    }
}
