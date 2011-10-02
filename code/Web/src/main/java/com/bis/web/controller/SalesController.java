package com.bis.web.controller;


import com.bis.domain.SalesTransaction;
import com.bis.sales.services.SalesService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Calendar;

@Controller
@RequestMapping("/Sales")
public class SalesController {

    protected final Logger logger = Logger.getLogger(getClass());

    private SalesService salesService;

    @Autowired
    public SalesController(SalesService salesService){
        this.salesService = salesService;
    }

    @RequestMapping(value = "/show/{id}",method = RequestMethod.GET)
    public ModelAndView show(@PathVariable("id") int transactionId) {
        SalesTransaction salesTransaction = salesService.getSalesTransaction(transactionId);
        return new ModelAndView("Sales/show", "SalesTransaction", salesTransaction);
    }

    @RequestMapping(value = "/createForm",method = RequestMethod.GET)
    public ModelAndView createForm() {
        SalesTransaction salesTransaction = new SalesTransaction();
        salesTransaction.setTransactionType('S');
        salesTransaction.setDate(Calendar.getInstance().getTime());
        return new ModelAndView("Sales/createForm", "SalesTransaction", salesTransaction);
    }

    @RequestMapping(value = "/updateForm/{id}",method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") int transactionId) {
        SalesTransaction salesTransaction = salesService.getSalesTransaction(transactionId);
        return new ModelAndView("Sales/updateForm", "SalesTransaction", salesTransaction);
    }

    @RequestMapping(value = "/addSalesTransaction",method = RequestMethod.POST)
    public String addSalesTransaction(@Valid SalesTransaction salesTransaction, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        salesService.addSalesTransaction(salesTransaction);
        return "redirect:/Sales/show/"+salesTransaction.getTransactionId();
    }

    @RequestMapping(value = "/updateSalesTransaction",method = RequestMethod.POST)
    public String updateSalesTransaction(@Valid SalesTransaction salesTransaction, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        salesService.updateSalesTransaction(salesTransaction);
        return "redirect:/Sales/show/"+salesTransaction.getTransactionId();
    }
}
