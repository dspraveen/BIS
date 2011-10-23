package com.bis.web.controller;

import com.bis.common.DateUtils;
import com.bis.domain.PaymentHistorySales;
import com.bis.domain.PaymentMode;
import com.bis.sales.services.SalesBillingService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/*@Controller
@RequestMapping("/salesPayment")*/
public class SalesPaymentController{

    protected final Logger logger = Logger.getLogger(getClass());

    private SalesBillingService salesBillingService;

    @Autowired
    public SalesPaymentController(SalesBillingService salesBillingService) {
        this.salesBillingService = salesBillingService;
    }

    @RequestMapping(value = "/show/{id}",method = RequestMethod.GET)
    public ModelAndView show(@PathVariable("id") int paymentId) {
        PaymentHistorySales paymentHistorySales = salesBillingService.getSalesPayment(paymentId);
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
        PaymentHistorySales paymentHistorySales = salesBillingService.getSalesPayment(paymentId);
        return new ModelAndView("salesPayment/updateForm", "PaymentHistorySales", paymentHistorySales);
    }

    @RequestMapping(value = "/addSalesPayment",method = RequestMethod.POST)
    public String addSalesPayment(@Valid PaymentHistorySales paymentHistorySales, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        salesBillingService.addSalesPayment(paymentHistorySales);
        return "redirect:/salesPayment/show/"+paymentHistorySales.getPaymentId();
    }

    @RequestMapping(value = "/updateSalesPayment",method = RequestMethod.POST)
    public String updateSalesPayment(@Valid PaymentHistorySales paymentHistorySales, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        salesBillingService.updateSalesPayment(paymentHistorySales);
        return "redirect:/salesPayment/show/" + paymentHistorySales.getPaymentId();
    }

    @ModelAttribute("PaymentMode")
    public Map<Character,String> paymentMode(){
        Map<Character,String> paymentModes = new HashMap<Character,String>();
        for(PaymentMode paymentMode: PaymentMode.values()){
            paymentModes.put(paymentMode.getCode(), paymentMode.toString());
        }
        return paymentModes;
    }
}
