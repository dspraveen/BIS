package com.bis.web.controller;

import com.bis.common.DateUtils;
import com.bis.core.services.ItemMasterService;
import com.bis.core.services.VendorMasterService;
import com.bis.domain.*;
import com.bis.inventory.services.StockService;
import com.bis.procurement.services.ProcurementTransactionService;
import com.bis.web.ProcurementTransactionHandler;
import com.bis.web.viewmodel.TransactionDetailGrid;
import com.bis.web.viewmodel.TransactionDetailRow;
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

    private ProcurementTransactionService procurementTransactionService;
    private ItemMasterService itemMasterService;
    private VendorMasterService vendorMasterService;
    private ProcurementTransactionHandler procurementTransactionHandler;

    @Autowired
    public ProcurementTransactionController(ProcurementTransactionService procurementTransactionService, ItemMasterService itemMasterService, VendorMasterService vendorMasterService, StockService stockService) {
        this.procurementTransactionService = procurementTransactionService;
        this.itemMasterService = itemMasterService;
        this.vendorMasterService = vendorMasterService;
        this.procurementTransactionHandler = new ProcurementTransactionHandler(procurementTransactionService, stockService);
    }


    ProcurementTransactionController(ProcurementTransactionService procurementTransactionService, ItemMasterService itemMasterService, VendorMasterService vendorMasterService, ProcurementTransactionHandler procurementTransactionHandler) {
        this.procurementTransactionService = procurementTransactionService;
        this.itemMasterService = itemMasterService;
        this.vendorMasterService = vendorMasterService;
        this.procurementTransactionHandler = procurementTransactionHandler;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable("id") int transactionId) {
        ProcurementTransaction procurementTransaction = procurementTransactionService.getProcurementTransaction(transactionId);
        return new ModelAndView("procurement/show", "procurementTransaction", procurementTransaction);
    }

    @RequestMapping(value = "/createForm", method = RequestMethod.GET)
    public ModelAndView createForm() {
        TransactionDetailGrid procurementTransaction = new TransactionDetailGrid();
        procurementTransaction.setType('P');
        procurementTransaction.setTransactionDate(DateUtils.currentDate());
        return new ModelAndView("procurement/processTransaction", "procurementTransaction", procurementTransaction);
    }

    @RequestMapping(value = "/addProcurementTransaction", method = RequestMethod.POST)
    public String addProcurementTransaction(@Valid TransactionDetailGrid transactionDetailGrid, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        ProcurementTransaction procurementTransaction = transactionDetailGrid.buildProcurementTransaction();
        if (procurementTransaction.getTransactionId() == null || procurementTransaction.getTransactionId() < 1) {
            procurementTransactionHandler.addNewTransaction(procurementTransaction);
        } else {
            procurementTransactionHandler.updateTransaction(procurementTransaction);
        }
        return "redirect:/procurement/show/" + procurementTransaction.getTransactionId();
    }

    @RequestMapping(value = "/updateForm/{id}", method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") int transactionId) {
        ProcurementTransaction procurementTransaction = procurementTransactionService.getProcurementTransaction(transactionId);
        TransactionDetailGrid transactionDetailGrid = new TransactionDetailGrid(procurementTransaction);
        return new ModelAndView("procurement/processTransaction", "procurementTransaction", transactionDetailGrid);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        return new ModelAndView("procurement/list");
    }

    @RequestMapping(value = "/transactionsInRange", method = RequestMethod.GET)
    public ModelAndView transactionsBetween(@RequestParam(value = "fromDate", required = true) Date fromDate, @RequestParam(value = "toDate", required = true) Date toDate, @RequestParam(value = "vendorId", required = false, defaultValue = "-1") int vendorId) {

        List<ProcurementTransaction> procurementTransactions;
        if (vendorId < 1) {
            procurementTransactions = procurementTransactionService.getProcurementTransactions(fromDate, toDate);
        } else {
            Vendor vendor = vendorMasterService.get(vendorId);
            procurementTransactions = procurementTransactionService.getProcurementTransactions(fromDate, toDate, vendor);
        }
        return new ModelAndView("procurement/transactionsInRange", "procurementTransactions", procurementTransactions);
    }

    @RequestMapping(value = "/fetchTransactionDetails", method = RequestMethod.POST)
    public ModelAndView fetchTransactionDetails(@Valid TransactionDetailGrid procurementTransactionGrid, BindingResult bindingResult, Model uiModel) {
        if (procurementTransactionGrid.getTransactionId() != null && procurementTransactionGrid.getTransactionId() > 0) {
            ProcurementTransaction procurementTransaction = procurementTransactionService.getProcurementTransaction(procurementTransactionGrid.getTransactionId());
            for (PtDetails ptDetails : procurementTransaction.getTransactionDetails()) {
                float itemPrice = itemMasterService.getItemPrice(ptDetails.getItem().getItemCode());
                procurementTransactionGrid.addProcurementTransactionDetail(ptDetails, itemPrice);
            }
        } else {
            Float vendorDiscount = procurementTransactionGrid.getTargetId() != null && procurementTransactionGrid.getTargetId() > 0 ? vendorMasterService.get(procurementTransactionGrid.getTargetId()).getVendorDiscount() : null;
            procurementTransactionGrid.addNewItem(vendorDiscount);
        }
        return new ModelAndView("procurement/editProcurementTransactionDetails", "procurementTransactionGrid", procurementTransactionGrid);
    }


    @RequestMapping(value = "/addItem", method = RequestMethod.POST)
    public ModelAndView addNewItem(@Valid TransactionDetailGrid procurementTransactionGrid, BindingResult bindingResult, Model uiModel) {
        Float vendorDiscount = procurementTransactionGrid.getTargetId() != null && procurementTransactionGrid.getTargetId() > 0 ? vendorMasterService.get(procurementTransactionGrid.getTargetId()).getVendorDiscount() : null;
        procurementTransactionGrid.addNewItem(vendorDiscount);
        return new ModelAndView("procurement/editProcurementTransactionDetails", "procurementTransactionGrid", procurementTransactionGrid);
    }

    @RequestMapping(value = "/deleteItem", method = RequestMethod.POST)
    public ModelAndView deleteItem(@Valid TransactionDetailGrid procurementTransactionGrid, BindingResult bindingResult, Model uiModel) {
        procurementTransactionGrid.deleteItems();
        return new ModelAndView("procurement/editProcurementTransactionDetails", "procurementTransactionGrid", procurementTransactionGrid);
    }

    @RequestMapping(value = "/itemChanged", method = RequestMethod.POST)
    public ModelAndView itemChanged(@Valid TransactionDetailGrid procurementTransactionGrid, BindingResult bindingResult, Model uiModel) {
        TransactionDetailRow effectedRow = procurementTransactionGrid.getEffectedRow(procurementTransactionGrid);
        if (effectedRow != null) effectedRow.updateItemPrice(itemMasterService.getItemPrice(effectedRow.getItemCode()));
        return new ModelAndView("procurement/editProcurementTransactionDetails", "procurementTransactionGrid", procurementTransactionGrid);
    }

    @RequestMapping(value = "/itemDiscountChanged", method = RequestMethod.POST)
    public ModelAndView itemDiscountChanged(@Valid TransactionDetailGrid procurementTransactionGrid, BindingResult bindingResult, Model uiModel) {
        TransactionDetailRow effectedRow = procurementTransactionGrid.getEffectedRow(procurementTransactionGrid);
        if (effectedRow != null) effectedRow.updateDiscount();
        return new ModelAndView("procurement/editProcurementTransactionDetails", "procurementTransactionGrid", procurementTransactionGrid);
    }

    @RequestMapping(value = "/itemPriceChanged", method = RequestMethod.POST)
    public ModelAndView itemPriceChanged(@Valid TransactionDetailGrid procurementTransactionGrid, BindingResult bindingResult, Model uiModel) {
        TransactionDetailRow effectedRow = procurementTransactionGrid.getEffectedRow(procurementTransactionGrid);
        if (effectedRow != null) effectedRow.updatePricePerItem();
        return new ModelAndView("procurement/editProcurementTransactionDetails", "procurementTransactionGrid", procurementTransactionGrid);
    }

    @RequestMapping(value = "/itemQuantityChanged", method = RequestMethod.POST)
    public ModelAndView itemQuantityChanged(@Valid TransactionDetailGrid procurementTransactionGrid, BindingResult bindingResult, Model uiModel) {
        TransactionDetailRow effectedRow = procurementTransactionGrid.getEffectedRow(procurementTransactionGrid);
        if (effectedRow != null) effectedRow.updateQuantity();
        return new ModelAndView("procurement/editProcurementTransactionDetails", "procurementTransactionGrid", procurementTransactionGrid);
    }

    @RequestMapping(value = "/vendorChanged", method = RequestMethod.POST)
    public ModelAndView vendorChanged(@Valid TransactionDetailGrid procurementTransactionGrid, BindingResult bindingResult, Model uiModel) {
        for (TransactionDetailRow row : procurementTransactionGrid.getTransactionDetails()) {
            row.updateVendorDiscount(vendorMasterService.get(procurementTransactionGrid.getTargetId()).getVendorDiscount());
        }
        return new ModelAndView("procurement/editProcurementTransactionDetails", "procurementTransactionGrid", procurementTransactionGrid);
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
