package com.bis.web.controller;

import com.bis.common.DateUtils;
import com.bis.core.services.ItemMasterService;
import com.bis.core.services.VendorMasterService;
import com.bis.domain.*;
import com.bis.inventory.services.StockService;
import com.bis.procurement.services.ProcurementTransactionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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

    @Autowired
    public ProcurementTransactionController(ProcurementTransactionService procurementTransactionService, ItemMasterService itemMasterService, VendorMasterService vendorMasterService, StockService stockService) {
        this.procurementTransactionService = procurementTransactionService;
        this.itemMasterService = itemMasterService;
        this.vendorMasterService = vendorMasterService;
        this.stockService = stockService;
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

    @RequestMapping(value = "/updateForm/{id}", method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") int transactionId) {
        ProcurementTransaction procurementTransaction = procurementTransactionService.getProcurementTransaction(transactionId);
        return new ModelAndView("procurement/updateForm", "procurementTransaction", procurementTransaction);
    }

    @RequestMapping(value = "/addProcurementTransaction", method = RequestMethod.POST)
    public String addProcurementTransaction(@Valid ProcurementTransaction procurementTransaction, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        procurementTransactionService.addProcurementTransaction(procurementTransaction);
        addStock(procurementTransaction);
        return "redirect:/procurement/show/" + procurementTransaction.getTransactionId();
    }

    private void addStock(ProcurementTransaction procurementTransaction) {
        List<PtDetails> transactionDetails = procurementTransaction.getTransactionDetails();
        for (PtDetails details : transactionDetails) {
            stockService.addStock(details.getItem().getItemCode(), details.getDateOfPublishing(), details.getQuantity());
        }
    }

    @RequestMapping(value = "/updateSalesTransaction", method = RequestMethod.POST)
    public String updateProcurementTransaction(@Valid ProcurementTransaction procurementTransaction, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        procurementTransactionService.updateProcurementTransaction(procurementTransaction);
        updateStock(procurementTransaction);
        return "redirect:/procurement/show/" + procurementTransaction.getTransactionId();
    }

    private void updateStock(ProcurementTransaction procurementTransaction) {
        List<PtDetails> transactionDetails = procurementTransaction.getTransactionDetails();
        ProcurementTransaction procurementTransactionDB = procurementTransactionService.getProcurementTransaction(procurementTransaction.getTransactionId());
        for (PtDetails details : transactionDetails) {
            PtDetails detailsDB = procurementTransactionDB.getPtDetails(details);
            if (detailsDB != null) {
                Integer quantity = details.getQuantity() - detailsDB.getQuantity();
                if (quantity > 0) {
                    // stock needs to be reduced from the database.
                    stockService.reduceStock(details.getItem().getItemCode(), details.getDateOfPublishing(), quantity);
                } else if (quantity < 0) {
                    stockService.addStock(details.getItem().getItemCode(), details.getDateOfPublishing(), quantity);
                }
            } else {
                stockService.addStock(details.getItem().getItemCode(), details.getDateOfPublishing(), details.getQuantity());
            }
        }
        List<PtDetails> transactionDetailsDB = procurementTransactionDB.getTransactionDetails();
        for (PtDetails detailsDB : transactionDetailsDB) {
            if (!transactionDetails.contains(detailsDB)) {
                stockService.addStock(detailsDB.getItem().getItemCode(), detailsDB.getDateOfPublishing(), detailsDB.getQuantity());
            }
        }

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
