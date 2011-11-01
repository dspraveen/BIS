package com.bis.web.controller;

import com.bis.common.DateUtils;
import com.bis.core.services.ItemMasterService;
import com.bis.core.services.VendorMasterService;
import com.bis.domain.Item;
import com.bis.domain.ProcurementTransaction;
import com.bis.domain.ProcurementTransactionType;
import com.bis.domain.Vendor;
import com.bis.inventory.services.StockService;
import com.bis.procurement.services.ProcurementTransactionService;
import com.bis.web.ProcurementTransactionHandler;
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
@RequestMapping("/procurement")
public class ProcurementTransactionController {

    protected final Logger logger = Logger.getLogger(getClass());

    private ProcurementTransactionService procurementTransactionService;
    private ItemMasterService itemMasterService;
    private VendorMasterService vendorMasterService;
    private StockService stockService;
    private ProcurementTransactionHandler procurementTransactionHandler;

    @Autowired
    public ProcurementTransactionController(ProcurementTransactionService procurementTransactionService, ItemMasterService itemMasterService, VendorMasterService vendorMasterService, StockService stockService) {
        this.procurementTransactionService = procurementTransactionService;
        this.itemMasterService = itemMasterService;
        this.vendorMasterService = vendorMasterService;
        this.stockService = stockService;
        this.procurementTransactionHandler = new ProcurementTransactionHandler(procurementTransactionService,stockService);
    }


    ProcurementTransactionController(ProcurementTransactionService procurementTransactionService, ItemMasterService itemMasterService, VendorMasterService vendorMasterService, StockService stockService, ProcurementTransactionHandler procurementTransactionHandler) {
        this.procurementTransactionService = procurementTransactionService;
        this.itemMasterService = itemMasterService;
        this.vendorMasterService = vendorMasterService;
        this.stockService = stockService;
        this.procurementTransactionHandler = procurementTransactionHandler;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable("id") int transactionId) {
        ProcurementTransaction procurementTransaction = procurementTransactionService.getProcurementTransaction(transactionId);
        return new ModelAndView("procurement/show", "procurementTransaction", procurementTransaction);
    }

    @RequestMapping(value = "/createForm", method = RequestMethod.GET)
    public ModelAndView createForm() {
        ProcurementTransaction procurementTransaction = new ProcurementTransaction();
        procurementTransaction.setTransactionType('P');
        procurementTransaction.setDate(DateUtils.currentDate());
        return new ModelAndView("procurement/createForm", "procurementTransaction", procurementTransaction);
    }

    @RequestMapping(value = "/addProcurementTransaction", method = RequestMethod.POST)
    public String addProcurementTransaction(@Valid ProcurementTransaction procurementTransaction, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        procurementTransactionHandler.addNewTransaction(procurementTransaction);
        return "redirect:/procurement/show/" + procurementTransaction.getTransactionId();
    }

    @RequestMapping(value = "/updateForm/{id}", method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") int transactionId) {
        ProcurementTransaction procurementTransaction = procurementTransactionService.getProcurementTransaction(transactionId);
        return new ModelAndView("procurement/updateForm", "procurementTransaction", procurementTransaction);
    }

    @RequestMapping(value = "/updateProcurementTransaction", method = RequestMethod.POST)
    public String updateProcurementTransaction(@Valid ProcurementTransaction procurementTransaction, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        procurementTransactionHandler.updateTransaction(procurementTransaction);
        return "redirect:/procurement/show/" + procurementTransaction.getTransactionId();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        return new ModelAndView("procurement/list");
    }

    @RequestMapping(value = "/transactionsInRange", method = RequestMethod.GET)
    public ModelAndView transactionsBetween(@RequestParam(value = "fromDate", required = true)Date fromDate, @RequestParam(value = "toDate", required = true)Date toDate ) {
        List<ProcurementTransaction> procurementTransactions = procurementTransactionService.getProcurementTransactions(fromDate, toDate);
        return new ModelAndView("procurement/transactionsInRange","procurementTransactions",procurementTransactions);
    }

    @ModelAttribute("procurementTransactionType")
    public Map<Character, String> procurementTransactionType() {
        Map<Character, String> procurementTransactionTypes = new HashMap<Character, String>();
        for (ProcurementTransactionType procurementTransactionType : ProcurementTransactionType.values()) {
            procurementTransactionTypes.put(procurementTransactionType.getCode(), procurementTransactionType.toString());
        }
        return procurementTransactionTypes;
    }

    @ModelAttribute("items")
    public List<Item> items() {
        return itemMasterService.getAll();
    }

    @ModelAttribute("vendors")
    public List<Vendor> vendors() {
        return vendorMasterService.getAll();
    }
}
