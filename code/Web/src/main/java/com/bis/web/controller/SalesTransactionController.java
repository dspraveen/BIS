package com.bis.web.controller;


import com.bis.common.DateUtils;
import com.bis.core.services.HawkerMasterService;
import com.bis.core.services.ItemMasterService;
import com.bis.domain.*;
import com.bis.inventory.services.StockService;
import com.bis.sales.services.SalesTransactionService;
import com.bis.web.SalesTransactionHandler;
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
@RequestMapping("/sales")
public class SalesTransactionController {

    private SalesTransactionService salesTransactionService;
    private ItemMasterService itemMasterService;
    private HawkerMasterService hawkerMasterService;
    private SalesTransactionHandler salesTransactionHandler;

    @Autowired
    public SalesTransactionController(SalesTransactionService salesTransactionService, ItemMasterService itemMasterService, HawkerMasterService hawkerMasterService, StockService stockService) {
        this.salesTransactionService = salesTransactionService;
        this.itemMasterService = itemMasterService;
        this.hawkerMasterService = hawkerMasterService;
        this.salesTransactionHandler = new SalesTransactionHandler(salesTransactionService, stockService);
    }


    SalesTransactionController(SalesTransactionService salesTransactionService, ItemMasterService itemMasterService, HawkerMasterService hawkerMasterService, SalesTransactionHandler salesTransactionHandler) {
        this.salesTransactionService = salesTransactionService;
        this.itemMasterService = itemMasterService;
        this.hawkerMasterService = hawkerMasterService;
        this.salesTransactionHandler = salesTransactionHandler;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable("id") int transactionId) {
        SalesTransaction salesTransaction = salesTransactionService.getSalesTransaction(transactionId);
        return new ModelAndView("sales/show", "salesTransaction", salesTransaction);
    }

    @RequestMapping(value = "/createForm", method = RequestMethod.GET)
    public ModelAndView createForm() {
        TransactionDetailGrid salesTransaction = new TransactionDetailGrid();
        salesTransaction.setType('S');
        salesTransaction.setTransactionDate(DateUtils.currentDate());
        return new ModelAndView("sales/processTransaction", "salesTransaction", salesTransaction);
    }

    @RequestMapping(value = "/addSalesTransaction", method = RequestMethod.POST)
    public String addSalesTransaction(@Valid TransactionDetailGrid transactionDetailGrid, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        SalesTransaction salesTransaction = transactionDetailGrid.buildSalesTransaction();
        if (salesTransaction.getTransactionId() == null || salesTransaction.getTransactionId() < 1) {
            salesTransactionHandler.addNewTransaction(salesTransaction);
        } else {
            salesTransactionHandler.updateTransaction(salesTransaction);
        }
        return "redirect:/sales/show/" + salesTransaction.getTransactionId();
    }

    @RequestMapping(value = "/updateForm/{id}", method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") int transactionId) {
        SalesTransaction salesTransaction = salesTransactionService.getSalesTransaction(transactionId);
        TransactionDetailGrid transactionDetailGrid = new TransactionDetailGrid(salesTransaction);
        return new ModelAndView("sales/processTransaction", "salesTransaction", transactionDetailGrid);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        return new ModelAndView("sales/list");
    }

    @RequestMapping(value = "/transactionsInRange", method = RequestMethod.GET)
    public ModelAndView transactionsBetween(@RequestParam(value = "fromDate", required = true) Date fromDate, @RequestParam(value = "toDate", required = true) Date toDate) {
        List<SalesTransaction> salesTransactions = salesTransactionService.getSalesTransactions(fromDate, toDate);
        return new ModelAndView("sales/transactionsInRange", "salesTransactions", salesTransactions);
    }

    @RequestMapping(value = "/fetchTransactionDetails", method = RequestMethod.POST)
    public ModelAndView fetchTransactionDetails(@Valid TransactionDetailGrid salesTransactionGrid, BindingResult bindingResult, Model uiModel) {
        if (salesTransactionGrid.getTransactionId() != null && salesTransactionGrid.getTransactionId() > 0) {
            SalesTransaction salesTransaction = salesTransactionService.getSalesTransaction(salesTransactionGrid.getTransactionId());
            for (StDetails stDetails : salesTransaction.getTransactionDetails()) {
                TransactionDetailRow row = new TransactionDetailRow();
                row.setDetailsId(stDetails.getDetailsId());
                row.setTransactionId(stDetails.getTransactionId());
                row.setDateOfPublishing(stDetails.getDateOfPublishing());
                row.setAmount(stDetails.getAmount());
                row.setQuantity(stDetails.getQuantity());
                row.setItemCode(stDetails.getItem().getItemCode());
                row.setMrp(itemMasterService.getItemPrice(stDetails.getItem().getItemCode()));
                row.setPricePerItem(stDetails.getPricePerItem());
                row.setDiscount((row.getMrp() - row.getPricePerItem()) * 100 / row.getMrp());
                salesTransactionGrid.getTransactionDetails().add(row);
            }
        } else {
            Float hawkerDiscount = salesTransactionGrid.getTargetId() != null && salesTransactionGrid.getTargetId() > 0 ? hawkerMasterService.get(salesTransactionGrid.getTargetId()).getHawkerDiscount() : null;
            salesTransactionGrid.addNewItem(hawkerDiscount);
        }
        return new ModelAndView("sales/editSalesTransactionDetails", "salesTransactionGrid", salesTransactionGrid);
    }

    @RequestMapping(value = "/addItem", method = RequestMethod.POST)
    public ModelAndView addNewItem(@Valid TransactionDetailGrid salesTransactionGrid, BindingResult bindingResult, Model uiModel) {
        Float salesDiscount = salesTransactionGrid.getTargetId() != null && salesTransactionGrid.getTargetId() > 0 ? hawkerMasterService.get(salesTransactionGrid.getTargetId()).getHawkerDiscount() : null;
        salesTransactionGrid.addNewItem(salesDiscount);
        return new ModelAndView("sales/editSalesTransactionDetails", "salesTransactionGrid", salesTransactionGrid);
    }

    @RequestMapping(value = "/deleteItem", method = RequestMethod.POST)
    public ModelAndView deleteItem(@Valid TransactionDetailGrid salesTransactionGrid, BindingResult bindingResult, Model uiModel) {
        salesTransactionGrid.deleteItems();
        return new ModelAndView("sales/editSalesTransactionDetails", "salesTransactionGrid", salesTransactionGrid);
    }

    @RequestMapping(value = "/itemChanged", method = RequestMethod.POST)
    public ModelAndView itemChanged(@Valid TransactionDetailGrid salesTransactionGrid, BindingResult bindingResult, Model uiModel) {
        TransactionDetailRow effectedRow = salesTransactionGrid.getEffectedRow(salesTransactionGrid);
        if (effectedRow != null) effectedRow.updateItemPrice(itemMasterService.getItemPrice(effectedRow.getItemCode()));
        return new ModelAndView("sales/editSalesTransactionDetails", "salesTransactionGrid", salesTransactionGrid);
    }

    @RequestMapping(value = "/itemDiscountChanged", method = RequestMethod.POST)
    public ModelAndView itemDiscountChanged(@Valid TransactionDetailGrid salesTransactionGrid, BindingResult bindingResult, Model uiModel) {
        TransactionDetailRow effectedRow = salesTransactionGrid.getEffectedRow(salesTransactionGrid);
        if (effectedRow != null) effectedRow.updateDiscount();
        return new ModelAndView("sales/editSalesTransactionDetails", "salesTransactionGrid", salesTransactionGrid);
    }

    @RequestMapping(value = "/itemPriceChanged", method = RequestMethod.POST)
    public ModelAndView itemPriceChanged(@Valid TransactionDetailGrid salesTransactionGrid, BindingResult bindingResult, Model uiModel) {
        TransactionDetailRow effectedRow = salesTransactionGrid.getEffectedRow(salesTransactionGrid);
        if (effectedRow != null) effectedRow.updateDiscount();
        return new ModelAndView("sales/editSalesTransactionDetails", "salesTransactionGrid", salesTransactionGrid);
    }

    @RequestMapping(value = "/itemQuantityChanged", method = RequestMethod.POST)
    public ModelAndView itemQuantityChanged(@Valid TransactionDetailGrid salesTransactionGrid, BindingResult bindingResult, Model uiModel) {
        TransactionDetailRow effectedRow = salesTransactionGrid.getEffectedRow(salesTransactionGrid);
        if (effectedRow != null) effectedRow.updateDiscount();
        return new ModelAndView("sales/editSalesTransactionDetails", "salesTransactionGrid", salesTransactionGrid);
    }

    @RequestMapping(value = "/vendorChanged", method = RequestMethod.POST)
    public ModelAndView vendorChanged(@Valid TransactionDetailGrid salesTransactionGrid, BindingResult bindingResult, Model uiModel) {
        for (TransactionDetailRow row : salesTransactionGrid.getTransactionDetails()) {
            row.updateVendorDiscount(hawkerMasterService.get(salesTransactionGrid.getTargetId()).getHawkerDiscount());
        }
        return new ModelAndView("sales/editSalesTransactionDetails", "salesTransactionGrid", salesTransactionGrid);
    }


    @ModelAttribute("items")
    public List<Item> items() {
        return itemMasterService.getAll();
    }

    @ModelAttribute("hawkers")
    public List<Hawker> vendors() {
        return hawkerMasterService.getAll();
    }


    @ModelAttribute("salesTransactionType")
    public Map<Character, String> salesTransactionType() {
        Map<Character, String> salesTransactionTypes = new HashMap<Character, String>();
        for (SalesTransactionType salesTransactionType : SalesTransactionType.values()) {
            salesTransactionTypes.put(salesTransactionType.getCode(), salesTransactionType.toString());
        }
        return salesTransactionTypes;
    }
}
